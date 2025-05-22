package herencia;

import java.time.LocalDate;


public class Bebida extends Producto implements Imprimible {
    private boolean gaseoso;
    private boolean lacteo;
    private String medida;

    public Bebida(String nombre, double precio, LocalDate fechaCaducidad, boolean gaseoso,
                  boolean lacteo, String medida) {
        super(nombre, precio, fechaCaducidad);
        this.gaseoso = gaseoso;
        this.lacteo = lacteo;
        this.medida = medida;
    }

    //Sobrescribe el método obtenerCaducidad de Producto
    @Override
    public LocalDate obtenerCaducidad() {
    	LocalDate fecha = fechaCaducidad;

        if (lacteo) {
            fecha = fecha.plusDays(10);
        } else {
            fecha = fecha.plusDays(20);
        }

        return fecha;
    }

    //Sobrescribe el método detalleProducto de Producto
    @Override
    public void detalleProducto() {
        aplicarDescuentoSiCercaCaducidad();
        if (!estado.equals("CADUCADO")) {
            if (estado.equals("OFERTA")) {
                System.out.println("€OFERTA€");
            }
            System.out.println("Nombre: " + nombre);
            System.out.println("Precio: " + precio + " €");
            if (gaseoso) {
                System.out.println("El producto contiene gas.");
            } else {
                System.out.println("El producto no contiene gas.");
            }
            if (lacteo) {
                System.out.println("El producto contiene lácteo.");
            } else {
                System.out.println("El producto no contiene lácteo.");
            }
            System.out.println("Medida: " + medida);
            System.out.println("Caduca: " + obtenerCaducidad());
            System.out.println("Estado: " + estado);
            System.out.println("FIN");
            System.out.println(""); // Línea en blanco
        }
    }
    
    //Sobrescribe el método imprimir de la interfaz
    @Override
    public void imprimir() {
        System.out.println("TICKET DE BEBIDA");
        System.out.println("Producto: " + nombre);
        System.out.println("Precio Final: " + precio + " €");
        System.out.println("Estado: " + estado);
        System.out.println("Fecha de caducidad: " + obtenerCaducidad());
    }
}
