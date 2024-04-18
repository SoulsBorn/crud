
package control;

import gui.OperacionesBD;
  import java.sql.SQLException;
import java.util.Scanner;

public class InterfazUsuario {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;

        while (continuar) {
            System.out.println("Selecciona una opción:");
            System.out.println("1. Insertar un nuevo auto");
            System.out.println("2. Modificar un auto existente");
            System.out.println("3. Eliminar un auto");
            System.out.println("4. Consultar autos");
            System.out.println("5. Salir");
            System.out.print("Opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir la nueva línea después de nextInt()

            switch (opcion) {
                case 1:
                    insertarAuto(scanner);
                    break;
                case 2:
                    modificarAuto(scanner);
                    break;
                case 3:
                    eliminarAuto(scanner);
                    break;
                case 4:
                    consultarAutos();
                    break;
                case 5:
                    continuar = false;
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }

        scanner.close();
    }

    public static void insertarAuto(Scanner scanner) {
        System.out.print("Marca del auto: ");
        String marca = scanner.nextLine();
        System.out.print("Modelo del auto: ");
        String modelo = scanner.nextLine();
        System.out.print("Color del auto: ");
        String color = scanner.nextLine();
        System.out.print("Cilindros del auto: ");
        int cilindros = scanner.nextInt();
        scanner.nextLine(); // Consumir la nueva línea después de nextInt()

        try {
            OperacionesBD.insertarAuto(marca, modelo, color, cilindros);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void modificarAuto(Scanner scanner) {
        System.out.print("ID del auto a modificar: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumir la nueva línea después de nextInt()

        try {
            OperacionesBD.consultarAutoPorId(id);
            System.out.println("Selecciona una opción para modificar:");
            System.out.println("1. Marca");
            System.out.println("2. Modelo");
            System.out.println("3. Color");
            System.out.println("4. Cilindros");
            System.out.print("Opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir la nueva línea después de nextInt()

            switch (opcion) {
                case 1:
                    System.out.print("Nueva marca del auto: ");
                    String nuevaMarca = scanner.nextLine();
                    OperacionesBD.modificarAuto(id, nuevaMarca, null, null, 0);
                    break;
                case 2:
                    System.out.print("Nuevo modelo del auto: ");
                    String nuevoModelo = scanner.nextLine();
                    OperacionesBD.modificarAuto(id, null, nuevoModelo, null, 0);
                    break;
                case 3:
                    System.out.print("Nuevo color del auto: ");
                    String nuevoColor = scanner.nextLine();
                    OperacionesBD.modificarAuto(id, null, null, nuevoColor, 0);
                    break;
                case 4:
                    System.out.print("Nuevos cilindros del auto: ");
                    int nuevosCilindros = scanner.nextInt();
                    scanner.nextLine(); // Consumir la nueva línea después de nextInt()
                    OperacionesBD.modificarAuto(id, null, null, null, nuevosCilindros);
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void eliminarAuto(Scanner scanner) {
        System.out.print("ID del auto a eliminar: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumir la nueva línea después de nextInt()

        try {
            OperacionesBD.eliminarAuto(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void consultarAutos() {
        try {
            OperacionesBD.consultarAutos();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

