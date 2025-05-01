package com.ecobank.commerce.service.impl;

import com.ecobank.commerce.dto.request.RegistroProductoDTO;
import com.ecobank.commerce.dto.response.ProductoPageResponse;
import com.ecobank.commerce.dto.response.RegistroProductoResponse;
import com.ecobank.commerce.mapper.ProductoMapper;
import com.ecobank.commerce.model.Categoria;
import com.ecobank.commerce.model.Imagen;
import com.ecobank.commerce.model.Producto;
import com.ecobank.commerce.model.Vendedor;
import com.ecobank.commerce.repository.CategoriaRepository;
import com.ecobank.commerce.repository.ImagenRepository;
import com.ecobank.commerce.repository.ProductoRepository;
import com.ecobank.commerce.repository.VendedorRepository;
import com.ecobank.commerce.service.services.ProductoService;
import com.ecobank.commerce.util.GuardarArchivoLocalService;
import com.ecobank.security.utils.JwtUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductoServiceImpl implements ProductoService{

    private final ProductoRepository productoRepository;
    private final VendedorRepository vendedorRepository;
    private final ImagenRepository imagenRepository;
    private final GuardarArchivoLocalService guardarArchivoLocalService;
    private final CategoriaRepository categoriaRepository;
    private final JwtUtils jwtUtils;

    public ProductoServiceImpl(ProductoRepository productoRepository, VendedorRepository vendedorRepository, ImagenRepository imagenRepository, GuardarArchivoLocalService guardarArchivoLocalService, CategoriaRepository categoriaRepository, JwtUtils jwtUtils) {
        this.productoRepository = productoRepository;
        this.vendedorRepository = vendedorRepository;
        this.imagenRepository = imagenRepository;
        this.guardarArchivoLocalService = guardarArchivoLocalService;
        this.categoriaRepository = categoriaRepository;
        this.jwtUtils = jwtUtils;
    }

    @Override
    public RegistroProductoResponse saveProducto(RegistroProductoDTO registroProductoDTO, List<MultipartFile> archivos, Long usuarioId) {

        // Validaciones de campos
        if (registroProductoDTO.getNombreProducto().isEmpty()) {
            throw new IllegalArgumentException("El campo nombre no debe estar vacío");
        }
        if (registroProductoDTO.getDescripcionProducto().isEmpty()) {
            throw new IllegalArgumentException("El campo descripcion no puede estar vacío");
        }
        if (registroProductoDTO.getPrecioProducto() == null) {
            throw new IllegalArgumentException("El precio NO debe venir vacío");
        }
        if (registroProductoDTO.getPrecioProducto().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("El precio debe ser mayor a 0");
        }
        if (registroProductoDTO.getStockProducto() < 0) {
            throw new IllegalArgumentException("El stock NO puede ser menor a 0");
        }
        if (registroProductoDTO.getStockProducto() == null) {
            throw new IllegalArgumentException("El stock NO debe venir vacío");
        }
        if (registroProductoDTO.getDescuentoProducto().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("El descuento debe ser mayor a 0");
        }

        // Obtener el vendedor asociado al usuarioId
        Optional<Vendedor> vendedorOptional = vendedorRepository.findByUsuarioUsuarioId(usuarioId);
        if (!vendedorOptional.isPresent()) {
            throw new IllegalArgumentException("El vendedor asociado al usuario no existe");
        }

        // Obtener la categoría asociada al producto
        Optional<Categoria> categoriaOptional = categoriaRepository.findById(registroProductoDTO.getCategoriaId());
        if (!categoriaOptional.isPresent()) {
            throw new IllegalArgumentException("La categoría asociada no existe");
        }

        Categoria categoria = categoriaOptional.get();
        Vendedor vendedor = vendedorOptional.get();

        // Crear el producto a partir del DTO y el vendedor y categoría obtenidos
        Producto producto = ProductoMapper.toEntity(registroProductoDTO, vendedor, categoria);

        // Guardar el producto en la base de datos
        Producto productoGuardado = productoRepository.save(producto);

        // Subir imágenes
        List<Imagen> imagenes = new ArrayList<>();
        for (MultipartFile archivo : archivos) {
            String url = guardarArchivoLocalService.saveFile(archivo); // ejemplo: retorna "/static/imagen1.jpg"
            Imagen img = new Imagen();
            img.setImagenUrl(url);
            img.setProducto(productoGuardado);
            imagenes.add(img);
        }

        // Guardar las imágenes en la base de datos
        imagenRepository.saveAll(imagenes);

        // Asignar las imágenes al producto guardado
        productoGuardado.setImagenes(imagenes);

        // Retornar el producto guardado mapeado a DTO
        return ProductoMapper.toDto(productoGuardado, vendedor);
    }
    public ProductoPageResponse listaProductosFiltrados(
            String nombre,
            BigDecimal precioMin,
            BigDecimal precioMax,
            Long categoriaId,
            int page,
            int size,
            String sort
    ) {
        // 1. Parseamos el sort dinámico
        String[] sortParams = sort != null ? sort.split(",") : new String[]{"productoNombre", "asc"};
        String sortBy = sortParams[0];
        Sort.Direction direction = (sortParams.length > 1 && sortParams[1].equalsIgnoreCase("desc"))
                ? Sort.Direction.DESC
                : Sort.Direction.ASC;

        // Validar campos permitidos para evitar errores
        List<String> camposValidos = List.of("productoNombre", "productoPrecio", "productoFechaCreacion");
        if (!camposValidos.contains(sortBy)) {
            sortBy = "productoNombre"; // fallback
        }

        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));

        // 2. Repositorio: pasamos todos los filtros
        Page<Producto> productos = productoRepository.buscarConFiltros(nombre, precioMin, precioMax, categoriaId, pageable);

        // 3. Mapeamos a la respuesta
        List<RegistroProductoResponse> items = productos.map(producto -> {
            RegistroProductoResponse response = new RegistroProductoResponse();
            response.setProductoId(producto.getProductoId());
            response.setNombreProducto(producto.getProductoNombre());
            response.setDescripcionProducto(producto.getProductoDescripcion());
            response.setPrecioProducto(producto.getProductoPrecio());
            response.setDescuentoProducto(producto.getProductoDescuento());
            response.setStockProducto(producto.getProductoStock());
            response.setFechaCreacionProducto(producto.getProductoFechaCreacion());

            List<String> urlsImagenes = producto.getImagenes().stream()
                    .map(Imagen::getImagenUrl)
                    .collect(Collectors.toList());
            response.setUrlsImagenes(urlsImagenes);

            if (producto.getVendedor() != null) {
                response.setNombrePyme(producto.getVendedor().getNombrePyme());
            }

            return response;
        }).getContent(); // getContent() para obtener los productos reales

        // 4. Crear y devolver la respuesta con paginación
        ProductoPageResponse pageResponse = new ProductoPageResponse(
                productos.getTotalElements(),
                productos.getTotalPages(),
                productos.getNumber(),
                items
        );

        return pageResponse;
    }

    public RegistroProductoResponse obtenerProductoPorId(Long id) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        RegistroProductoResponse response = new RegistroProductoResponse();
        response.setProductoId(producto.getProductoId());
        response.setNombreProducto(producto.getProductoNombre());
        response.setDescripcionProducto(producto.getProductoDescripcion());
        response.setPrecioProducto(producto.getProductoPrecio());
        response.setDescuentoProducto(producto.getProductoDescuento());
        response.setStockProducto(producto.getProductoStock());
        response.setFechaCreacionProducto(producto.getProductoFechaCreacion());

        List<String> urlsImagenes = producto.getImagenes().stream()
                .map(Imagen::getImagenUrl)
                .collect(Collectors.toList());
        response.setUrlsImagenes(urlsImagenes);

        if (producto.getVendedor() != null) {
            response.setNombrePyme(producto.getVendedor().getNombrePyme());
            response.setDescripcionPyme(producto.getVendedor().getDescripcionPyme());
            response.setRazonSocialVendedor(producto.getVendedor().getVendedorRazonSocial());
        }

        return response;
    }

}
