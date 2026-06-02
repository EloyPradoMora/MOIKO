package eloy.MOIKO;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

public class ProductoTest {
    Producto p = new Producto();
    @BeforeAll
    static void setUp(){
        System.out.println("@BeforeAll executed");
    }

    @Test
    void testCodigo(){

        Assertions.assertEquals("J1011221226", p.createCode());
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
        Assertions.assertEquals(2, p.getMail().split("@").length());
    }
}
