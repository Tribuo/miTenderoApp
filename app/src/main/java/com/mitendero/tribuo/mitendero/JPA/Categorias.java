package com.mitendero.tribuo.mitendero.JPA;
// Generated Apr 14, 2017 8:01:04 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Categorias generated by hbm2java
 */

public class Categorias implements java.io.Serializable {


    private Integer idCategoria;
    private String nombreCategoria;
    private Set<Subcategorias> subcategoriases = new HashSet<Subcategorias>(0);

    public Categorias() {
    }


    public Categorias(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }

    public Categorias(String nombreCategoria, Set<Subcategorias> subcategoriases) {
        this.nombreCategoria = nombreCategoria;
        this.subcategoriases = subcategoriases;
    }


    public Integer getIdCategoria() {
        return this.idCategoria;
    }

    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }


    public String getNombreCategoria() {
        return this.nombreCategoria;
    }

    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }

    public Set<Subcategorias> getSubcategoriases() {
        return this.subcategoriases;
    }

    public void setSubcategoriases(Set<Subcategorias> subcategoriases) {
        this.subcategoriases = subcategoriases;
    }


}


