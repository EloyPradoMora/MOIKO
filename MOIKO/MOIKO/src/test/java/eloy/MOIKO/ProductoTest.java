package eloy.MOIKO;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class ProductoTest {
    Producto p = new Producto();
    @BeforeAll
    static void setUp(){
        System.out.println("@BeforeAll executed");
    }

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
        Assertions.assertTrue(producto.getNombre().length <= 30);
    }

    @Test
    void testNombreCorto(){
        Producto producto = new Producto("COD", "Pc del Eloy", 10, 500, "16.827.524-1", "eloy@ufro.com");
        Assertions.assertTrue(producto.getNombre().length <= 30);
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
    void testPrecio(){
        Assertions.assertTrue(p.getPrecio() instanceof Integer);
    }

    @Test
    void testRUT(){
        Assertions.assertTrue(p.verificarRut("20645322-2"));
        assertFalse(p.verificarRut("20645322-3"));
    }

    @Test
    void testEmail(){
        assertEquals(2, p.getMail().split("@").length());
    }
}
