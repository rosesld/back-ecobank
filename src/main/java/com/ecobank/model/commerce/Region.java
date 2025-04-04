package com.ecobank.model.commerce;

import jakarta.persistence.*;

@Entity
@Table(name = "regiones")
public class Region {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "region_id")
    private Long regionId;

    @Column(name = "region_nombre", nullable = false, length = 100, unique = true)
    private String regionNombre;

    //TODO: A ?

    public Region(){}

    public Region(Long regionId, String regionNombre) {
        this.regionId = regionId;
        this.regionNombre = regionNombre;
    }

    public Long getRegionId() {
        return this.regionId;
    }

    public String getRegionNombre() {
        return this.regionNombre;
    }

    public void setRegionNombre(String regionNombre) {
        this.regionNombre = regionNombre;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Region{");
        sb.append("regionId=").append(regionId);
        sb.append(", regionNombre='").append(regionNombre).append('\'');
        sb.append('}');
        return sb.toString();
    }


}
