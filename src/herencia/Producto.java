package herencia;

import java.time.LocalDate;

public abstract class Producto {
    protected String nombre;
    protected double precio;
    protected LocalDate fechaCaducidad;
    protected String estado;

    public Producto(String nombre, double precio, LocalDate fechaCaducidad) {
        this.nombre = nombre;
        this.precio = precio;
        this.fechaCaducidad = fechaCaducidad;
        this.estado = "BUENO";
    }

    public abstract LocalDate obtenerCaducidad();
    public abstract void detalleProducto();

    public String getEstado() {
        return estado;
    }

    public double getPrecio() {
        return precio;
    }
    
    public String getNombre () {
    	return nombre;
    }

    //Método para calcular el 30 % de descuento si la fecha de caducidad es inferior a 5 días
    public void aplicarDescuentoSiCercaCaducidad() {
        LocalDate hoy = LocalDate.now();
        LocalDate caducidad = obtenerCaducidad();

        long diferencia = hoy.until(caducidad).getDays(); 

        if (diferencia < 0) {
            estado = "CADUCADO";
        } else if (diferencia < 5) {
            estado = "OFERTA";
            precio *= 0.7;  // Aplica un 30% de descuento
        }
    }

}
