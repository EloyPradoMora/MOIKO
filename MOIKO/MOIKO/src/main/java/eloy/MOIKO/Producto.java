package eloy.MOIKO;

import java.time.LocalDateTime;

public class Producto {
    String codigo;
    String nombre;
    int stock;
    int precio;
    String rut;
    String mail;

    public Producto(String codigo, String nombre, int stock, int precio, String rut, String mail) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.stock = stock;
        this.precio = precio;
        this.rut = rut;
        this.mail = mail;
    }

    public String createCode(){
        String codigoCorrecto = String.valueOf(nombre.charAt(0)).toUpperCase();
        String fechaHoraRaw = LocalDateTime.now().toString();
        String trimmedTime = fechaHoraRaw.substring(2);
        String refinedTime = trimmedTime.split("T")[0].split("-")[2];
        refinedTime += trimmedTime.split("T")[0].split("-")[1];
        refinedTime += trimmedTime.split("T")[0].split("-")[0];

        refinedTime += trimmedTime.split("T")[1].split(":")[0];
        refinedTime += trimmedTime.split("T")[1].split(":")[1];
        codigoCorrecto += refinedTime;
        return codigoCorrecto;
    }

    public boolean isNombreValido() {
        return nombre.length() <= 30;
    }

    public boolean isStockValido() {
        return false;
    }

    public boolean isPrecioValido() {
        return false;
    }

    public boolean isMailValido() {
        return false;
    }

    public boolean verificarRutEstructura(){
        return false;
    }

    public boolean verificarRutEnProveedorCSV(){
        return false;
    }

    public String getNombre(){
        return nombre;
    }

    public String getMail() {
        return mail;
    }
}