package herencia;

import java.util.*;
import java.io.*;
import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {
    	String nombreCliente = "";
    	boolean nombreValido = false;
    	
        //Carga los clientes desde el fichero
        List<Cliente> clientes = cargarClientes("clientes.txt");

        //Crear fechas de ejemplo
        LocalDate fechaCad1 = LocalDate.of(2025, 4, 25); 
        LocalDate fechaEnvase1 = LocalDate.of(2025, 4, 2);

        Producto comida1 = new Comida("Ensalada vegetal", 5.0, fechaCad1, true, 150, true, fechaEnvase1);

        LocalDate fechaCad2 = LocalDate.of(2025, 5, 1); 
        Producto bebida1 = new Bebida("Zumo de naranja", 2.5, fechaCad2, false, true, "500cc");

        List<Producto> productos = new ArrayList<>();
        productos.add(comida1);
        productos.add(bebida1);

        //Muestra la carta 
        System.out.println("**CARTA DE PRODUCTOS**");
        System.out.println("");
     
        //Bucle para mostrar detalles de los productos
        for (int i = 0; i < productos.size(); i++) {
            Producto p = productos.get(i);  // Obtener cada producto
            p.detalleProducto();  // Muestra los detalles del producto
        }


        //Muestra el ticket
        System.out.println("\n **TICKET FINAL DE PEDIDOS**");
        for (int i = 0; i < productos.size(); i++) {
            Producto p = productos.get(i);
            p.aplicarDescuentoSiCercaCaducidad(); // actualiza el estado según la caducidad
            if (!p.getEstado().equals("CADUCADO") && p instanceof Imprimible) {
                ((Imprimible) p).imprimir();
            }
        }

        //Valida los datos con excepciones
        Scanner scanner = new Scanner(System.in);

        try {
        	while (!nombreValido) {
        	    System.out.print("Introduce tu nombre: ");
        	    nombreCliente = scanner.nextLine().trim(); // quitamos espacios al principio y final

        	    if (nombreCliente.isEmpty()) {
        	        System.out.println("Error: El nombre no puede estar vacío.");
        	        continue;
        	    }

        	    if (nombreCliente.matches(".*\\d.*")) {
                    System.out.println("Error: El nombre no puede contener números. Vuelve a intentarlo.");
                } else {
                    nombreValido = true;
                }
        	}

        	System.out.print("Introduce tu número de teléfono: ");
            String telefono = scanner.nextLine();
            if (!telefono.matches("\\d{9}")) {
                throw new ExcepcionTelefonoInvalido("El número debe tener 9 dígitos.");
            }

            System.out.print("Introduce el importe a pagar: ");
            double importePagado = Double.parseDouble(scanner.nextLine());

            //Calcula el total real con descuentos 
            double totalCompra = 0.0;
            for (int i = 0; i < productos.size(); i++) {
                Producto p = productos.get(i);
                p.aplicarDescuentoSiCercaCaducidad();
                if (!p.getEstado().equals("CADUCADO")) {
                    totalCompra += p.getPrecio();
                }
            }

            if (importePagado < totalCompra) {
                throw new ExcepcionImporteInsuficiente("Insertar más dinero. Total: " + totalCompra + " €");
            }

            System.out.print("Introduce el número de tarjeta (16 dígitos): ");
            String tarjeta = scanner.nextLine();
            if (!tarjeta.matches("\\d{16}")) {
                throw new ExcepcionTarjetaInvalida("La tarjeta debe tener 16 dígitos.");
            }

            System.out.print("Introduce el número de cuenta (20 dígitos): ");
            String cuenta = scanner.nextLine();
            if (!cuenta.matches("\\d{20}")) {
                throw new ExcepcionCuentaInvalida("La cuenta debe tener 20 dígitos.");
            }

            //Si los datos son correctos guarda el cliente
            Cliente nuevo = new Cliente(nombreCliente, telefono);
            clientes.add(nuevo);

            System.out.println("Datos correctos. ¡Gracias por su compra!");

        } catch (ExcepcionTelefonoInvalido | ExcepcionImporteInsuficiente |
                 ExcepcionTarjetaInvalida | ExcepcionCuentaInvalida e) {
            System.out.println("Error: " + e.getMessage());

        } catch (NumberFormatException e) {
            System.out.println("Error de formato numérico. Introduce un número válido.");
        }

        scanner.close();

        //Guarda los clientes y los productos antes de cerrar
        guardarClientes(clientes, "clientes.txt");
        guardarProductos(productos, "productos.txt");
    }

    //Método para guardar y cargar ficheros
    public static void guardarClientes(List<Cliente> clientes, String archivo) {
        try (PrintWriter writer = new PrintWriter(archivo)) {
        	for (int i = 0; i < clientes.size(); i++) {
        	    Cliente c = clientes.get(i);
        	    writer.println(c.toString());
        	}
        } catch (Exception e) {
            System.out.println("Error al guardar cliente: " + e.getMessage());
        }
    }

    public static List<Cliente> cargarClientes(String archivo) {
        List<Cliente> clientes = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(archivo))) {
            while (scanner.hasNextLine()) {
                Cliente c = Cliente.fromString(scanner.nextLine());
                if (c != null) clientes.add(c);
            }
        } catch (Exception e) {
            System.out.println("No se pudo cargar el cliente.");
        }
        return clientes;
    }

    public static void guardarProductos(List<Producto> productos, String archivo) {
        try (PrintWriter writer = new PrintWriter(archivo)) {
        	for (int i = 0; i < productos.size(); i++) {
        	    Producto p = productos.get(i);
        	    writer.println(p.getNombre() + ", " + p.getPrecio() + ", " + p.getEstado());
        	}
        } catch (Exception e) {
            System.out.println("Error al guardar producto: " + e.getMessage());
        }
    }
}




