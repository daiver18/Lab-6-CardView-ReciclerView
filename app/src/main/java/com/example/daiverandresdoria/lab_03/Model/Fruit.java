package com.example.daiverandresdoria.lab_03.Model;

public class Fruit {
    private String name;
    private String descripcion;
    private int cantidad;
    private int icon_background;
    private int icon;

    public static final int Limite_cantidad=10;
    public static final int Reset_cantidad=0;

    public Fruit(String name, String descripcion,int cantidad, int icon_background, int icon) {
        this.name = name;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.icon_background = icon_background;
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getIcon_background() {
        return icon_background;
    }

    public void setIcon_background(int icon_background) {
        this.icon_background = icon_background;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    //metodo de limite de cantidad
    public void Limit(int cantidad){
        if (this.cantidad < this.Limite_cantidad){
            this.cantidad += cantidad;
        }
    }

    //reset cantidad
    public void Reset(){
        this.cantidad=Reset_cantidad;
    }
}
