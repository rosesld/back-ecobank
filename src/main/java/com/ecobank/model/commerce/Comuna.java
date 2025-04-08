package com.ecobank.model.commerce;

import jakarta.persistence.*;

@Entity
@Table(name = "comunas")
public class Comuna {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comuna_id")
    private Long comunaId;

    @Column(name = "comuna_nombre", nullable=false, length = 100)
    private String comunaNombre;

    //TODO: RELACION CON LA TABLA REGION, traer llave foranea de region
    @ManyToOne
    @JoinColumn(name = "region_id", referencedColumnName = "region_id", nullable = false)
    private Region region;

    public Comuna (){}

    public Comuna(Long comunaId, String comunaNombre) {
        this.comunaId = comunaId;
        this.comunaNombre = comunaNombre;
    }

    public Long getComunaId() {
        return comunaId;
    }

    public String getComunaNombre() {
        return comunaNombre;
    }

    public void setComunaNombre(String comunaNombre) {
        this.comunaNombre = comunaNombre;
    }

    public void setComunaId(Long comunaId) {
        this.comunaId = comunaId;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Comuna{");
        sb.append("comunaId=").append(comunaId);
        sb.append(", comunaNombre='").append(comunaNombre).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
