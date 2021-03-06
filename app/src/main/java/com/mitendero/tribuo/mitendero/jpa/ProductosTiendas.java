package com.mitendero.tribuo.mitendero.jpa;
// Generated Apr 14, 2017 8:01:04 PM by Hibernate Tools 4.3.1


/**
 * ProductosTiendas generated by hbm2java
 */

public class ProductosTiendas implements java.io.Serializable {


    private ProductosTiendasId id;
    private Productos productos;
    private Sucursales sucursales;
    private int cantidad;
    private int precio;

    public ProductosTiendas() {
    }

    public ProductosTiendas(ProductosTiendasId id, Productos productos, Sucursales sucursales, int cantidad, int precio) {
        this.id = id;
        this.productos = productos;
        this.sucursales = sucursales;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    public ProductosTiendasId getId() {
        return this.id;
    }

    public void setId(ProductosTiendasId id) {
        this.id = id;
    }

    public Productos getProductos() {
        return this.productos;
    }

    public void setProductos(Productos productos) {
        this.productos = productos;
    }

    public Sucursales getSucursales() {
        return this.sucursales;
    }

    public void setSucursales(Sucursales sucursales) {
        this.sucursales = sucursales;
    }

    public int getCantidad() {
        return this.cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getPrecio() {
        return this.precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }


}


