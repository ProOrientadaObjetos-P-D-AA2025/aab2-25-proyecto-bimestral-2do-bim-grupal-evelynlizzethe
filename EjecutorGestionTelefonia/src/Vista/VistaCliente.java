package Vista;

import Controlador.ClienteControlador;
import Modelo.Cliente;
import Modelo.Dispositivo;

import java.util.List;
import java.util.Scanner;

public class VistaCliente {
    private ClienteControlador controlador;
    private Scanner scanner;

    public VistaCliente() {
        controlador = new ClienteControlador();
        scanner = new Scanner(System.in);
    }

    public void mostrarMenu() {
        int opcion;
        do {
            System.out.println("\n--- Gestión de Clientes ---");
            System.out.println("1. Crear cliente");
            System.out.println("2. Listar clientes");
            System.out.println("3. Actualizar cliente");
            System.out.println("4. Eliminar cliente");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = Integer.parseInt(scanner.nextLine());

            switch (opcion) {
                case 1 -> crearCliente();
                case 2 -> listarClientes();
                case 3 -> actualizarCliente();
                case 4 -> eliminarCliente();
                case 5 -> System.out.println("Saliendo del módulo de clientes...");
                default -> System.out.println("Opción inválida.");
            }
        } while (opcion != 5);
    }

    private void crearCliente() {
        Cliente c = ingresarDatosCliente();
        controlador.crear(c);
        System.out.println("✅ Cliente creado correctamente.");
    }

    private void listarClientes() {
        List<Cliente> lista = controlador.listar();
        System.out.println("\n--- Lista de Clientes ---");
        for (Cliente c : lista) {
            mostrarCliente(c);
        }
    }

    private void actualizarCliente() {
        System.out.print("Ingrese la cédula/pasaporte del cliente a actualizar: ");
        String cedula = scanner.nextLine();

        Cliente c = ingresarDatosCliente();
        c.setCedulaPasaporte(cedula);
        controlador.actualizar(c);
        System.out.println("✅ Cliente actualizado correctamente.");
    }

    private void eliminarCliente() {
        System.out.print("Ingrese la cédula/pasaporte del cliente a eliminar: ");
        String cedula = scanner.nextLine();
        controlador.eliminar(cedula);
        System.out.println("✅ Cliente eliminado correctamente.");
    }

    private Cliente ingresarDatosCliente() {
        Cliente c = new Cliente();
        Dispositivo d = new Dispositivo();

        System.out.print("Nombres: ");
        c.setNombres(scanner.nextLine());

        System.out.print("Cédula/Pasaporte: ");
        c.setCedulaPasaporte(scanner.nextLine());

        System.out.print("Ciudad: ");
        c.setCiudad(scanner.nextLine());

        System.out.print("Email: ");
        c.setEmail(scanner.nextLine());

        System.out.print("Dirección: ");
        c.setDireccion(scanner.nextLine());

        System.out.print("Marca del celular: ");
        d.setMarca(scanner.nextLine());

        System.out.print("Modelo del celular: ");
        d.setModelo(scanner.nextLine());

        System.out.print("Número de celular: ");
        d.setNumero(scanner.nextLine());

        System.out.print("Pago mensual: ");
        d.setPago(Double.parseDouble(scanner.nextLine()));

        c.setDispositivo(d);

        return c;
    }

    private void mostrarCliente(Cliente c) {
        System.out.println("\nNombre: " + c.getNombres());
        System.out.println("Cédula/Pasaporte: " + c.getCedulaPasaporte());
        System.out.println("Ciudad: " + c.getCiudad());
        System.out.println("Email: " + c.getEmail());
        System.out.println("Dirección: " + c.getDireccion());
        System.out.println("Marca Celular: " + c.getDispositivo().getMarca());
        System.out.println("Modelo Celular: " + c.getDispositivo().getModelo());
        System.out.println("Número Celular: " + c.getDispositivo().getNumero());
        System.out.println("Pago Mensual: $" + c.getDispositivo().getPago());
    }
}

