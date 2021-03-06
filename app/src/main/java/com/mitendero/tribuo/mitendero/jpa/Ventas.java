package com.mitendero.tribuo.mitendero.jpa;
// Generated Apr 14, 2017 8:01:04 PM by Hibernate Tools 4.3.1

import java.util.Date;

/**
 * Ventas generated by hbm2java
 */

public class Ventas implements java.io.Serializable {

    private Integer idVenta;
    private Productos productos;
    private Sucursales sucursales;
    private int cantidad;
    private Date fecha;

    public Ventas() {
    }

    public Ventas(Productos productos, Sucursales sucursales, int cantidad, Date fecha) {
        this.productos = productos;
        this.sucursales = sucursales;
        this.cantidad = cantidad;
        this.fecha = fecha;
    }

    public Integer getIdVenta() {
        return this.idVenta;
    }

    public void setIdVenta(Integer idVenta) {
        this.idVenta = idVenta;
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

    public Date getFecha() {
        return this.fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

}
