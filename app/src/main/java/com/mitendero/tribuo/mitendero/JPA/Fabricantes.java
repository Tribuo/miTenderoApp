package com.mitendero.tribuo.mitendero.JPA;
// Generated Apr 14, 2017 8:01:04 PM by Hibernate Tools 4.3.1

import java.util.HashSet;
import java.util.Set;

/**
 * Fabricantes generated by hbm2java
 */

public class Fabricantes implements java.io.Serializable {

    private Integer idFabricante;
    private String nombreFabricante;
    private Set<Marcas> marcases = new HashSet<Marcas>(0);

    public Fabricantes() {
    }

    public Fabricantes(String nombreFabricante) {
        this.nombreFabricante = nombreFabricante;
    }

    public Fabricantes(String nombreFabricante, Set<Marcas> marcases) {
        this.nombreFabricante = nombreFabricante;
        this.marcases = marcases;
    }

    public Integer getIdFabricante() {
        return this.idFabricante;
    }

    public void setIdFabricante(Integer idFabricante) {
        this.idFabricante = idFabricante;
    }

    public String getNombreFabricante() {
        return this.nombreFabricante;
    }

    public void setNombreFabricante(String nombreFabricante) {
        this.nombreFabricante = nombreFabricante;
    }

    public Set<Marcas> getMarcases() {
        return this.marcases;
    }

    public void setMarcases(Set<Marcas> marcases) {
        this.marcases = marcases;
    }

}
