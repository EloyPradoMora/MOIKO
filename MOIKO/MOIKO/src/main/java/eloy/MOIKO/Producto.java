package eloy.MOIKO;

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
        return null;
    }

    public boolean isNombreValido() {
        return false;
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