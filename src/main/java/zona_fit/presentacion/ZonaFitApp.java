package zona_fit.presentacion;

import zona_fit.datos.ClientesDAO;
import zona_fit.datos.IClienteDAO;
import zona_fit.dominio.Cliente;

import java.util.Scanner;

public class ZonaFitApp {
    public static void main(String[] args) {
        zonaFitApp();
    }
    private static void zonaFitApp(){
        var salir = false;
        var consola = new Scanner(System.in);
        //Creamos una clase de clienteDao
        IClienteDAO clienteDAO = new ClientesDAO();
        while (!salir){
            try {
            int opcion = mostrarMenu(consola);
             salir = ejecutarOpciones(consola, opcion, clienteDAO);
            } catch (Exception e) {
                System.out.println("Error al ejecutar opciones " + e.getMessage());
            }
            System.out.println();
        }

    }
    private static int mostrarMenu(Scanner consola){
        System.out.println("""
                *** Zona Fit (GYM)
                1.Listar Clientes
                2.Buscar Cliente
                3.Agregar Cliente
                4.Modificar Cliente
                5.Eliminar Cliente
                6.Salir
                Elija un opcion:\s
                """);
        var opcion = Integer.parseInt(consola.nextLine());
        return opcion;

    }
    private static boolean ejecutarOpciones(Scanner consola,
                                            int opcion,
                                            IClienteDAO clienteDAO
                                            ) {
        var salir = false;
        switch (opcion) {
            case 1 -> {//1.Listar clientes
                System.out.println("--- Listado de Clientes ---");
                var clientes = clienteDAO.listarClientes();
                clientes.forEach(System.out::println);

            }

        case 2 -> { //2.Buscar cliente por id
            System.out.println("Introduce el ID del cliente a buscar: ");
            var idCliente = Integer.parseInt(consola.nextLine());
            var cliente = new Cliente(idCliente);
            var encontrado = clienteDAO.buscarClientesPorId(cliente);
            if(encontrado)
                System.out.println("Cliente encontrado: " + cliente);
            else
                System.out.println("Cliente no encontrado");

        }
        case 3 ->{//3.Agregar cliente
            System.out.println("---Agregar cliente---");
            System.out.println("Nombre: ");
            var nombre = consola.nextLine();
            System.out.println("Apellido: ");
            var apellido = consola.nextLine();
            System.out.println("Membresia: ");
            var membresia = Integer.parseInt(consola.nextLine());
            //Creamos el objeto cliente (sin el id)
            var cliente = new Cliente(nombre,apellido,membresia);
            var agregado = clienteDAO.agregarCliente(cliente);
            if(agregado)
                System.out.println("Cliente agregado" + cliente);
            else
                System.out.println("Cliente no agregado" + cliente);




        }
        case 4 ->{//Modificar un cliente existente
            System.out.println("---Modificar cliente---");
            System.out.println("Id cliente: ");
            var idCliente = Integer.parseInt(consola.nextLine());
            System.out.println("Nombre: ");
            var nombre = consola.nextLine();
            System.out.println("Apellido");
            var apellido = consola.nextLine();
            System.out.println("Membresia: ");
            var membresia = Integer.parseInt(consola.nextLine());
            //Creamos el objeto a modificar
            var cliente = new Cliente(idCliente,nombre,apellido,membresia);
            var modificado = clienteDAO.modificarCliente(cliente);
            if(modificado)
                System.out.println("Cliente modificado " + cliente);
            else
                System.out.println("Cliente no modificado " + cliente);


        }
        case 5 ->{//5.Eliminar cliente
            System.out.println("---Eliminar cliente---");
            System.out.println("Id cliente: ");
            var idCliente = Integer.parseInt(consola.nextLine());
            var cliente = new Cliente(idCliente);
            var eliminado = clienteDAO.eliminarCliente(cliente);
            if(eliminado)
                System.out.println("Cliente eliminado " + cliente);
            else
                System.out.println("Cliente no eliminado " + cliente);


        }
        case 6 ->{//6.Salir del sistema
            System.out.println("Hasta pronto ");
            salir = true;


        }
            default -> System.out.println("Opcion no reconocida " + opcion);
    }
        return salir;
    }
}

