package herencia;

import java.io.Serializable;

public class Cliente implements Serializable {

    private String nombre;
    private String telefono;

    public Cliente(String nombre, String telefono) {
        this.nombre = nombre;
        this.telefono = telefono;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    //Sobrescribe para pasar a una cadena de texto
    @Override
    public String toString() {
        return "Nombre: " + nombre + ", Teléfono: " + telefono;
    }

    //Método para convertir la cadena de texto en un objeto de la clase clientoe
    public static Cliente fromString(String linea) {
        String[] datos = linea.split(","); //Divide la cadena de texto en 2 partes dependiendo de la ","
        
        if (datos.length == 2) {
            return new Cliente(datos[0].trim(), datos[1].trim());
        } else {
            System.out.println("Error: El formato no es correcto (nombre,telefono)");
            return null;
        }
    }

}
