package herencia;

import java.time.LocalDate;

public class Comida extends Producto implements Imprimible {
    private boolean perecedero;
    private float calorias;
    private boolean vegano;
    private LocalDate fechaEnvase;

    public Comida(String nombre, double precio, LocalDate fechaCaducidad, boolean perecedero,
                  float calorias, boolean vegano, LocalDate fechaEnvase) {
        super(nombre, precio, fechaCaducidad);
        this.perecedero = perecedero;
        this.calorias = calorias;
        this.vegano = vegano;
        this.fechaEnvase = fechaEnvase;
    }

    //Sobrescribe el método obtenerCaducidad de Producto
    @Override
    public LocalDate obtenerCaducidad() {
        LocalDate fecha;
        
        if (perecedero) {
            fecha = fechaEnvase.plusDays(10);
        } else {
            fecha = fechaCaducidad;
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
            if (perecedero) {
                System.out.println("El producto es perecedero.");
            } else {
                System.out.println("El producto no es perecedero.");
            }
            System.out.println("Calorías: " + calorias + " cal");
            if (vegano) {
                System.out.println("El producto es vegano.");
            } else {
                System.out.println("El producto no es vegano.");
            }
            System.out.println("Caduca: " + obtenerCaducidad());
            System.out.println("Estado: " + estado);
            System.out.println("FIN");
            System.out.println(""); // Línea en blanco
        }
    }
    
    //Sobrescribe el método imprimir de la interfaz
    @Override
    public void imprimir() {
        System.out.println("TICKET DE COMIDA");
        System.out.println("Producto: " + nombre);
        System.out.println("Precio Final: " + precio + " €");
        System.out.println("Estado: " + estado);
        System.out.println("Fecha de caducidad: " + obtenerCaducidad());
    }
}
