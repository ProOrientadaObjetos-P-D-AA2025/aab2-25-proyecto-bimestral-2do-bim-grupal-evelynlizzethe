package Vista;

import Controlador.ClienteControlador;
import Modelo.*;
import java.util.List;
import java.util.Scanner;

public class Ejecutor {

    static ClienteControlador clienteControlador = new ClienteControlador();
    static List<PlanMovil> planes = new java.util.ArrayList<>();
    static List<Factura> facturas = new java.util.ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n===== MENU =====");
            System.out.println("1. Crear cliente");
            System.out.println("2. Crear plan movil");
            System.out.println("3. Generar factura");
            System.out.println("4. Mostrar clientes y planes");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opcion: ");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    crearCliente(sc);
                    break;
                case 2:
                    crearPlan(sc);
                    break;
                case 3:
                    generarFactura();
                    break;
                case 4:
                    mostrarClientesYPlanes();
                    break;
                case 5:
                    System.out.println("Hasta pronto!");
                    break;
                default:
                    System.out.println("Opcion invalida.");
            }

        } while (opcion != 5);
        sc.close();
    }

    static void crearCliente(Scanner sc) {
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();

        System.out.print("Cedula/Pasaporte: ");
        String cedula = sc.nextLine();

        System.out.print("Ciudad: ");
        String ciudad = sc.nextLine();

        System.out.print("Marca celular: ");
        String marca = sc.nextLine();

        System.out.print("Modelo celular: ");
        String modelo = sc.nextLine();

        System.out.print("Numero celular: ");
        String numero = sc.nextLine();

        Cliente c = new Cliente(nombre, cedula, ciudad, marca, modelo, numero);
        clienteControlador.crearCliente(c);
        System.out.println("Cliente registrado con ID: " + c.getIdCliente());
    }

    static void crearPlan(Scanner sc) {
        if (clienteControlador.obtenerClientes().isEmpty()) {
            System.out.println("Primero debes registrar un cliente.");
            return;
        }

        System.out.print("ID del cliente: ");
        int idCliente = sc.nextInt();
        sc.nextLine();

        Cliente cliente = clienteControlador.buscarPorId(idCliente);
        if (cliente == null) {
            System.out.println("Cliente no encontrado.");
            return;
        }

        System.out.println("Tipo de plan:");
        System.out.println("1. Postpago Minutos ");
        System.out.println("2. Postpago Minutos-Megas");
        System.out.println("3. Postpago Minutos-Megas Economico");
        System.out.println("4. Postpago Solo Megas");
        System.out.print("Selecciona una opcion: ");
        int tipo = sc.nextInt();
        sc.nextLine();

        PlanMovil plan = null;

        switch (tipo) {
            case 1:
                System.out.print("Minutos nacionales: ");
                int mn = sc.nextInt();
                System.out.print("Costo minuto nacional: ");
                double cmn = sc.nextDouble();
                System.out.print("Minutos internacionales: ");
                int mi = sc.nextInt();
                System.out.print("Costo minuto internacional: ");
                double cmi = sc.nextDouble();
                sc.nextLine();
                plan = new PlanPostPagoMinutos(idCliente, mn, cmn, mi, cmi);
                break;
            case 2:
                System.out.print("Minutos: ");
                int m = sc.nextInt();
                System.out.print("Costo minuto: ");
                double cm = sc.nextDouble();
                System.out.print("Gigas: ");
                double g = sc.nextDouble();
                System.out.print("Costo por giga: ");
                double cg = sc.nextDouble();
                sc.nextLine();
                plan = new PlanPostPagoMinutosMegas(idCliente, m, cm, g, cg);
                break;
            case 3:
                System.out.print("Minutos: ");
                int me = sc.nextInt();
                System.out.print("Costo minuto: ");
                double cme = sc.nextDouble();
                System.out.print("Gigas: ");
                double ge = sc.nextDouble();
                System.out.print("Costo por giga: ");
                double cge = sc.nextDouble();
                System.out.print("Descuento (%): ");
                double d = sc.nextDouble();
                sc.nextLine();
                plan = new PlanPostPagoMinutosMegasEconomico(idCliente, me, cme, ge, cge, d);
                break;
            case 4:
                System.out.print("Gigas: ");
                double g4 = sc.nextDouble();
                System.out.print("Costo por giga: ");
                double cg4 = sc.nextDouble();
                System.out.print("Tarifa base: ");
                double tb = sc.nextDouble();
                sc.nextLine();
                plan = new PlanPostPagosMegas(idCliente, g4, cg4, tb);
                break;
            default:
                System.out.println("Tipo invalido.");
                return;
        }

        planes.add(plan);
        System.out.println("Plan registrado exitosamente.");
    }

    static void generarFactura() {
        if (planes.isEmpty()) {
            System.out.println("No hay planes registrados.");
            return;
        }
        facturas.clear(); 

        for (PlanMovil plan : planes) {
            Cliente cliente = clienteControlador.buscarPorId(plan.getIdCliente());
            if (cliente != null) {
                Factura f = new Factura(cliente, plan);
                facturas.add(f);
                System.out.println("Factura generada para cliente: " + cliente.getNombres() +
                        " - Total a pagar: $" + String.format("%.2f", f.getTotalPagar()));
            }
        }
    }

    static void mostrarClientesYPlanes() {
        List<Cliente> clientes = clienteControlador.obtenerClientes();
        if (clientes.isEmpty()) {
            System.out.println("No hay clientes registrados.");
            return;
        }
        for (Cliente c : clientes) {
            System.out.println("Cliente ID: " + c.getIdCliente() + " - " + c.getNombres());
            for (PlanMovil p : planes) {
                if (p.getIdCliente() == c.getIdCliente()) {
                    System.out.println("  Plan: " + p.getTipoPlan() +
                            " - Pago mensual: $" + String.format("%.2f", p.calcularPagoMensual()));
                }
            }
        }
    }

    public void mostrarMenu() {
        
    }
}
