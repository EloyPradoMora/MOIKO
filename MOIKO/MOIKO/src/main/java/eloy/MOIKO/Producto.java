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
        return stock >= 0;
    }

    public boolean isPrecioValido() {
        return precio >= 0;
    }

    public boolean isMailValido() {
        return false;
    }

    public boolean verificarRutEstructura(){
        if (rut == null || !rut.contains("-")) {
            return false;
        }
        String rutLimpio = rut.replace(".", "").toUpperCase();
        String[] rutParts = rutLimpio.split("-");
        if (rutParts.length != 2) {
            return false;
        }
        String numerosDeRut = rutParts[0];
        char dvIngresado = rutParts[1].charAt(0);

        int rutNumerico = Integer.parseInt(numerosDeRut);
        char dvEsperado = calcularDigitoVerificador(rutNumerico);
        return dvIngresado == dvEsperado;
    }

    public char calcularDigitoVerificador(int rutNumerico){
        int suma = 0;
        int multiplicador = 2;

        while (rutNumerico > 0) {
            suma += (rutNumerico % 10) * multiplicador;
            multiplicador = (multiplicador == 7) ? 2 : multiplicador + 1;
            rutNumerico /= 10;
        }

        int resto = suma % 11;
        int dvCalculado = 11 - resto;

        if (dvCalculado == 11) {
            return '0';
        } else if (dvCalculado == 10) {
            return 'K';
        } else {
            return Character.forDigit(dvCalculado, 10);
        }
    }

    public boolean verificarRutEnProveedorCSV(){
        if (!verificarRutEstructura()) return false;
        return false;
    }

    public String getNombre(){
        return nombre;
    }

    public String getMail() {
        return mail;
    }
}