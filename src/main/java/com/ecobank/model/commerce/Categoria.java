package com.ecobank.model.commerce;

import jakarta.persistence.*;

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
