import java.util.Scanner;

public class InventarioSupermercado {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            final int MAX_PRODUCTOS = 5;
            String[] nombres = new String[MAX_PRODUCTOS];
            int[] cantidades = new int[MAX_PRODUCTOS];
            int total = 0;

            System.out.println("Registro de productos");
            for (int i = 0; i < MAX_PRODUCTOS; i++) {
                System.out.print("Ingrese el nombre del producto " + (i+1) + ": ");
                nombres[i] = sc.nextLine();
                System.out.print("Ingrese la cantidad de " + nombres[i] + ": ");
                cantidades[i] = sc.nextInt();
                while (cantidades[i] < 0) {
                    System.out.print("Cantidad no puede ser negativa, ingrese de nuevo: ");
                    cantidades[i] = sc.nextInt();
                }
                total += cantidades[i];
                sc.nextLine();
            }

            int opcion = 0;
            while (opcion != 5) {
                System.out.println("\nMenu:");
                System.out.println("1. Mostrar todos los productos y existencias");
                System.out.println("2. Buscar un producto por nombre");
                System.out.println("3. Actualizar inventario");
                System.out.println("4. Alerta de baja existencia");
                System.out.println("5. Salir");
                System.out.println("Total de productos en inventario: " + total);
                System.out.print("Elija una opcion: ");
                opcion = sc.nextInt();
                sc.nextLine();

                if (opcion == 1) {
                    for (int i = 0; i < MAX_PRODUCTOS; i++) {
                        System.out.println(nombres[i] + " -> " + cantidades[i]);
                    }
                } else if (opcion == 2) {
                    System.out.print("Ingrese el nombre a buscar: ");
                    String buscado = sc.nextLine();
                    boolean encontrado = false;
                    for (int i = 0; i < MAX_PRODUCTOS; i++) {
                        if (nombres[i].equalsIgnoreCase(buscado)) {
                            System.out.println("Cantidad: " + cantidades[i]);
                            encontrado = true;
                        }
                    }
                    if (!encontrado) {
                        System.out.println("Producto no encontrado");
                    }
                } else if (opcion == 3) {
                    System.out.print("Ingrese el nombre del producto a actualizar: ");
                    String prod = sc.nextLine();
                    boolean existe = false;
                    for (int i = 0; i < MAX_PRODUCTOS; i++) {
                        if (nombres[i].equalsIgnoreCase(prod)) {
                            existe = true;
                            System.out.println("Cantidad actual: " + cantidades[i]);
                            System.out.print("1. Aumentar  2. Disminuir: ");
                            int opAct = sc.nextInt();
                            System.out.print("Ingrese cantidad: ");
                            int cant = sc.nextInt();
                            sc.nextLine();
                            if (cant < 0) {
                                System.out.println("No se permiten cantidades negativas");
                            } else {
                                if (opAct == 1) {
                                    cantidades[i] += cant;
                                    total += cant;
                                } else if (opAct == 2) {
                                    if (cant > cantidades[i]) {
                                        System.out.println("No hay suficiente stock");
                                    } else {
                                        cantidades[i] -= cant;
                                        total -= cant;
                                    }
                                }
                            }
                        }
                    }
                    if (!existe) {
                        System.out.println("Producto no encontrado");
                    }
                } else if (opcion == 4) {
                    for (int i = 0; i < MAX_PRODUCTOS; i++) {
                        if (cantidades[i] < 10) {
                            System.out.println("Alerta: " + nombres[i] + " tiene " + cantidades[i]);
                        }
                    }
                } else if (opcion == 5) {
                    System.out.println("Saliendo...");
                } else {
                    System.out.println("Opcion no valida");
                }
            }
        }
    }
}