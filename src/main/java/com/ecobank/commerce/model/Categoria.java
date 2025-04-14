package com.ecobank.commerce.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "categories")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "categoria_id")
    private Long categoriaId;

    @Column(name = "categoria_nombre", nullable = false, length = 50)
    private String categoriaNombre;

    @Column(name = "categoria_descripcion", nullable = true, length = 200)
    private String categoriaDescripcion;

    //TODO: A ESPERAR TABLE INTERMEDIA ENTRE PRODUCTO CATEGORIA N:N
    @OneToMany(mappedBy = "categoria")
    private List<Producto> productos;

    public Categoria() {}

    public Categoria(Long categoriaId, String categoriaNombre, String categoriaDescripcion) {
        this.categoriaId = categoriaId;
        this.categoriaNombre = categoriaNombre;
        this.categoriaDescripcion = categoriaDescripcion;
    }

    public Long getCategoriaId() {
        return categoriaId;
    }

    public String getCategoriaNombre() {
        return categoriaNombre;
    }

    public void setCategoriaNombre(String categoriaNombre) {
        this.categoriaNombre = categoriaNombre;
    }

    public String getCategoriaDescripcion() {
        return categoriaDescripcion;
    }

    public void setCategoriaDescripcion(String categoriaDescripcion) {
        this.categoriaDescripcion = categoriaDescripcion;
    }

    public void setCategoriaId(Long categoriaId) {
        this.categoriaId = categoriaId;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Categoria{");
        sb.append("categoriaId=").append(categoriaId);
        sb.append(", categoriaNombre='").append(categoriaNombre).append('\'');
        sb.append(", categoriaDescripcion='").append(categoriaDescripcion).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
