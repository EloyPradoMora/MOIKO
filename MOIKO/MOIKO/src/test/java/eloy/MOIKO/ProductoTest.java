package eloy.MOIKO;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ProductoTest {
    @BeforeAll
    static void setUp(){
        System.out.println("@BeforeAll executed");
    }

    @TempDir
    Path tempDir;

    @Test
    void testCodigo(){
        Producto producto = new Producto("123", "computador", 10, 500000, "12345678-9", "test@mail.com");
        LocalDateTime fechaFija = LocalDateTime.of(2026, 10, 15, 14, 30, 0);

        try (MockedStatic<LocalDateTime> mockedLocalDateTime = Mockito.mockStatic(LocalDateTime.class)) {
            mockedLocalDateTime.when(LocalDateTime::now).thenReturn(fechaFija);
            String codigoGenerado = producto.createCode();
            String codigoEsperado = "C1510261430";
            assertEquals(codigoEsperado, codigoGenerado);
        }
    }

    @Test
    void testNombreDe30Caracteres(){
        String nombre = "A".repeat(30);
        Producto producto = new Producto("COD", nombre, 10, 500, "16.827.524-1", "test@mail.com");
        Assertions.assertTrue(producto.getNombre().length() <= 30);
    }

    @Test
    void testNombreCorto(){
        Producto producto = new Producto("COD", "Pc del Eloy", 10, 500, "16.827.524-1", "eloy@ufro.com");
        Assertions.assertTrue(producto.getNombre().length() <= 30);
    }

    @Test
    void testNombreLargo() {
        String nombre = "A".repeat(31);
        Producto producto = new Producto("COD", nombre, 10, 500, "16.827.524-1", "test@mail.com");
        assertFalse(producto.isNombreValido());
    }

    @Test
    void testStockYPrecioConValoresNegativos() {
        Producto productoInvalido = new Producto("COD", "Anillo Unico de Poder", -1, -1, "16.827.524-1", "gandalf@ainur.com");
        assertFalse(productoInvalido.isStockValido());
        assertFalse(productoInvalido.isPrecioValido());
    }

    @Test
    void testRutFormatoInvalido() {
        Producto producto = new Producto("COD", "Jabón", 10, 500, "20.645.322-3", "test@mail.com");
        assertFalse(producto.verificarRutEstructura());
    }

    @Test
    void testRutExisteEnCsv() throws IOException {
        Path rutaCsvTemporal = tempDir.resolve("proveedores_prueba.csv");
        List<String> lineasCsv = List.of(
                "20.645.322-2, Proveedor de MOIKO",
                "16.827.524-1, Proveedor Gimli y Cia."
        );
        Files.write(rutaCsvTemporal, lineasCsv);
        Producto producto = new Producto("COD", "Jabón", 10, 500, "20.645.322-2", "test@mail.com");

        String rutaAbsoluta = rutaCsvTemporal.toAbsolutePath().toString();

        assertTrue(producto.verificarRutEnProveedorCSV(rutaAbsoluta));
    }

    @Test
    void testEmailSinArroba(){
        Producto producto = new Producto("COD", "Jabón", 10, 500, "20.645.322-2", "testmail.com");
        assertNotEquals(2, producto.getMail().split("@").length);
    }

    @Test
    void testMailFormatoCorrecto() {
        Producto producto = new Producto("COD", "Jabón", 10, 500, "20.645.322-2", "test@mail.com");
        assertTrue(producto.isMailValido());
    }

    @Test
    void testMailFormatoIncorrecto() {
        Producto producto = new Producto("COD", "Jabón", 10, 500, "20.645.322-2", "test@com");
        assertFalse(producto.isMailValido());
    }
}
