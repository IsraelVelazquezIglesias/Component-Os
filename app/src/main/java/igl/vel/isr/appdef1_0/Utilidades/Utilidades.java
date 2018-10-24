package igl.vel.isr.appdef1_0.Utilidades;

public class Utilidades {
    //CONSTANTES CAMPOS TABLA CARRO
    public static final String TABLA_CARRO = "carro";
    public static final String CAMPO_NOMBRE = "nombre";
    public static final String CAMPO_PRECIO = "precio";
    public static final String CAMPO_CANTIDAD = "cantidad";
    public static final String CAMPO_TOTAL = "subtotal";
    public static final String CREAR_TABLA_COMPRAS = "CREATE TABLE "+TABLA_CARRO+" ("+CAMPO_NOMBRE+" TEXT, "+CAMPO_PRECIO+" DOUBLE, "+CAMPO_CANTIDAD+" INT, "+CAMPO_TOTAL+" DOUBLE)";
}
