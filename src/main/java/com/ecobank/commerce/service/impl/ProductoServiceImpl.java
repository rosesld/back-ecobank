package com.ecobank.commerce.service.impl;

import com.ecobank.commerce.dto.request.RegistroProductoDTO;
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

    public ProductoServiceImpl(ProductoRepository productoRepository, VendedorRepository vendedorRepository, ImagenRepository imagenRepository, GuardarArchivoLocalService guardarArchivoLocalService, CategoriaRepository categoriaRepository) {
        this.productoRepository = productoRepository;
        this.vendedorRepository = vendedorRepository;
        this.imagenRepository = imagenRepository;
        this.guardarArchivoLocalService = guardarArchivoLocalService;
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public RegistroProductoResponse saveProducto (RegistroProductoDTO registroProductoDTO, List<MultipartFile> archivos){
        if(registroProductoDTO.getNombreProducto().isEmpty()){
            throw new IllegalArgumentException("El campo nombre no debe estar vacío");
        }
        if(registroProductoDTO.getDescripcionProducto().isEmpty()){
            throw new IllegalArgumentException("El campo descripcion no puede estar vacío");
        }
        if(registroProductoDTO.getPrecioProducto() == null){
            throw new IllegalArgumentException("El precio NO debe venir vacío");
        }
        if(registroProductoDTO.getPrecioProducto().compareTo(BigDecimal.ZERO) < 0){
            throw new IllegalArgumentException("El precio debe ser mayor a 0");
        }
        if(registroProductoDTO.getStockProducto() < 0){
            throw new IllegalArgumentException("El stock NO puede ser menor a 0");
        }
        if(registroProductoDTO.getStockProducto() == null){
            throw new IllegalArgumentException("El stock NO debe venir vacío");
        }
        if(registroProductoDTO.getDescuentoProducto().compareTo(BigDecimal.ZERO) < 0){
            throw new IllegalArgumentException("El descuento debe ser mayor a 0");
        }

        Optional<Vendedor> vendedorOptional = vendedorRepository.findById(registroProductoDTO.getVendedor().getVendedorId());
        if(!vendedorOptional.isPresent()){
            throw new IllegalArgumentException("El vendedor asociado no existe");
        }

        Optional<Categoria> categoriaOptional = categoriaRepository.findById(registroProductoDTO.getCategoria().getCategoriaId());
        if(!categoriaOptional.isPresent()){
            throw new IllegalArgumentException("La categoria asociada no existe");
        }

        Categoria categoria = categoriaOptional.get();
        Vendedor vendedor = vendedorOptional.get();
        Producto producto = ProductoMapper.toEntity(registroProductoDTO, vendedor, categoria);
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

        imagenRepository.saveAll(imagenes);
        productoGuardado.setImagenes(imagenes);

        return ProductoMapper.toDto(productoGuardado, vendedor);
    }

    @Override
    public List<RegistroProductoResponse> listaProductos() {
        return productoRepository.findAll().stream()
                .filter(producto -> producto.getProductoStock() != null && producto.getProductoStock() >= 0)
                .map(producto -> {
                    RegistroProductoResponse response = new RegistroProductoResponse();
                    response.setProductoId(producto.getProductoId());
                    response.setNombreProducto(producto.getProductoNombre());
                    response.setDescripcionProducto(producto.getProductoDescripcion());
                    response.setPrecioProducto(producto.getProductoPrecio());
                    response.setDescuentoProducto(producto.getProductoDescuento());
                    response.setStockPorducto(producto.getProductoStock());
                    response.setFechaCreacionProducto(producto.getProductoFechaCreacion());

                    List<String> urlsImagenes = producto.getImagenes().stream()
                            .map(imagen -> imagen.getImagenUrl())
                            .collect(Collectors.toList());

                    response.setUrlsImagenes(urlsImagenes);

                    if(producto.getVendedor() != null) {
                        response.setNombrePyme(producto.getVendedor().getNombrePyme());
                    }

                return  response;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<RegistroProductoResponse> buscarProductoPorNombre(String nombreProducto) {
        return productoRepository.findAll().stream()
                .filter(producto -> producto.getProductoStock() != null && producto.getProductoStock() >= 0)
                .filter(producto -> producto.getProductoNombre() != null &&
                        producto.getProductoNombre().toLowerCase().contains(nombreProducto.toLowerCase()))
                .map(producto -> {
                    RegistroProductoResponse response = new RegistroProductoResponse();
                    response.setProductoId(producto.getProductoId());
                    response.setNombreProducto(producto.getProductoNombre());
                    response.setDescripcionProducto(producto.getProductoDescripcion());
                    response.setPrecioProducto(producto.getProductoPrecio());
                    response.setDescuentoProducto(producto.getProductoDescuento());
                    response.setStockPorducto(producto.getProductoStock());
                    response.setFechaCreacionProducto(producto.getProductoFechaCreacion());

                    List<String> urlsImagenes = producto.getImagenes().stream()
                            .map(imagen -> imagen.getImagenUrl())
                            .collect(Collectors.toList());

                    response.setUrlsImagenes(urlsImagenes);

                    if(producto.getVendedor() != null) {
                        response.setNombrePyme(producto.getVendedor().getNombrePyme());
                    }

                    return  response;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<RegistroProductoResponse> buscarPorPrecio(BigDecimal min, BigDecimal max) {
        return productoRepository.findAll().stream()
                .filter(producto -> producto.getProductoStock() != null && producto.getProductoStock() > 0)
                .filter(producto -> {
                    BigDecimal precio = producto.getProductoPrecio();
                    return precio != null &&
                            (min == null || precio.compareTo(min) >= 0) &&
                            (max == null || precio.compareTo(max) <= 0);
                })
                .map(producto -> {
                    RegistroProductoResponse response = new RegistroProductoResponse();
                    response.setProductoId(producto.getProductoId());
                    response.setNombreProducto(producto.getProductoNombre());
                    response.setDescripcionProducto(producto.getProductoDescripcion());
                    response.setPrecioProducto(producto.getProductoPrecio());
                    response.setDescuentoProducto(producto.getProductoDescuento());
                    response.setStockPorducto(producto.getProductoStock());
                    response.setFechaCreacionProducto(producto.getProductoFechaCreacion());

                    List<String> urlsImagenes = producto.getImagenes().stream()
                            .map(imagen -> imagen.getImagenUrl())
                            .collect(Collectors.toList());

                    response.setUrlsImagenes(urlsImagenes);

                    if (producto.getVendedor() != null) {
                        response.setNombrePyme(producto.getVendedor().getNombrePyme());
                    }

                    return response;
                })
                .collect(Collectors.toList());
    }
}
