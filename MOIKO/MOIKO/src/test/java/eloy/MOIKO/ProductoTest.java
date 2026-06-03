package eloy.MOIKO;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
    void testNombre(){
        Assertions.assertTrue(p.getNombre.lenth <= 30);
    }

    @Test
    void testStock(){
        Assertions.assertTrue(p.getStock instanceof Integer);
    }

    @Test
    void testPrecio(){
        Assertions.assertTrue(p.getPrecio instanceof Integer);
    }

    @Test
    void testRUT(){
        Assertions.assertTrue(p.verificarRut("20645322-2"));
        Assertions.assertFalse(p.verificarRut("20645322-3"));
    }

    @Test
    void testEmail(){
        assertEquals(2, p.getMail().split("@").length());
    }
}
