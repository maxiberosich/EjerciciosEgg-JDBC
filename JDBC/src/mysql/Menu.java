/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mysql;

import java.util.Scanner;
import mysql.dominio.fabricante.Fabricante;
import mysql.dominio.fabricante.FabricanteService;
import mysql.dominio.producto.Producto;
import mysql.dominio.producto.ProductoService;

/**
 *
 * @author Max
 */
public class Menu {

    private Scanner leer = new Scanner(System.in, "ISO-8859-1").useDelimiter("\n");
    private ProductoService ps;
    private FabricanteService fs;

    public Menu() {
        this.ps = new ProductoService();
        this.fs = new FabricanteService();
    }

    public void ejecutarMenu() {
        try {
            int opcMenu;
            do {
                System.out.println("Seleccione la opcion que desee:"
                        + "\n1- Mostrar el nombre de todos los productos"
                        + "\n2- Mostrar el nombre y precio de todos los productos"
                        + "\n3- Mostrar todos los productos que su precio este entre 120 y 202"
                        + "\n4- Buscar y listar todos los Portátiles de la tabla producto"
                        + "\n5- Mostrar el nombre y el precio del producto más barato"
                        + "\n6- Ingresar un producto a la base de datos"
                        + "\n7- Ingresar un fabricante a la base de datos"
                        + "\n8- Editar un producto con datos a elección"
                        + "\n9- Eliminar un producto"
                        + "\n10- Mostrar todos los fabricantes"
                        + "\n11- Modificar un fabricante"
                        + "\n12- Eliminar un fabricante"
                        + "\n0- Salir");
                opcMenu = leer.nextByte();
                switch (opcMenu) {
                    case 1:
                        ps.mostrarNombreProductos();
                        break;
                    case 2:
                        ps.mostrarNombreYPrecioProductos();
                        break;
                    case 3:
                        ps.mostrarNombreYPrecioEntre120Y202Productos();
                        break;
                    case 4:
                        ps.mostrarPortatilesDeProductos();
                        break;
                    case 5:
                        ps.mostrarNombreYPrecioProductoMasBarato();
                        break;
                    case 6:
                        System.out.println("Ingrese el nombre del nuevo producto:");
                        String nombre = leer.next();
                        System.out.println("Ingrese el precio del producto:");
                        Double precio = leer.nextDouble();
                        System.out.println("Ingrese codigo de fabricante:");
                        int codF = leer.nextInt();
                        ps.crearProducto(nombre, precio, codF);
                    case 7:
                        System.out.println("Ingrese el nombre del fabricante a cargar:");
                        String nombreF = leer.next();
                        fs.guardarFabricante(nombreF);
                        break;
                    case 8:
                        System.out.println("Ingrese el codigo del producto que desea modificar");
                        int cod = leer.nextInt();
                        Producto p = ps.buscarProductoPorCodigo(cod);
                        if (p == null) {
                            System.out.println("El producto no existe en la base de datos.");
                            break;
                        }
                        String opc;
                        System.out.println("Desea modificar el nombre S/N");
                        opc = leer.next();
                        if (opc.equalsIgnoreCase("s")) {
                            System.out.print("Ingrese el nuevo nombre: ");
                            String nombreP = leer.next();
                            p.setNombre(nombreP);
                        }
                        System.out.println("Desea modificar el precio S/N");
                        opc = leer.next();
                        if (opc.equalsIgnoreCase("s")) {
                            System.out.print("Ingrese el nuevo precio: ");
                            Double precioP = leer.nextDouble();
                            p.setPrecio(precioP);
                        }
                        System.out.println("Desea modificar el codigo de fabricante S/N");
                        opc = leer.next();
                        if (opc.equalsIgnoreCase("s")) {
                            System.out.print("Ingrese el nuevo codigo de fabricante: ");
                            int codigoFabricanteP = leer.nextInt();
                            p.setCodigo_fabricante(codigoFabricanteP);
                        }
                        ps.modificarProducto(p);
                        break;
                    case 9:
                        System.out.print("Ingrese el codigo del producto que desea eliminar: ");
                        int codP = leer.nextInt();
                        Producto prod = ps.buscarProductoPorCodigo(codP);
                        ps.eliminarProducto(prod);
                        break;
                    case 10:
                        fs.mostrarFabricantes();
                        break;
                    case 11:
                        System.out.print("Ingrese el codigo del fabricante que desea modificar: ");
                        int codFab = leer.nextInt();
                        Fabricante f = fs.buscarFabricantePorCodigo(codFab);
                        if (f != null) {
                            System.out.println("Ingrese el nuevo nombre del fabricante:");
                            String nombreFab = leer.next();
                            f.setNombreF(nombreFab);
                            fs.modificarFabricante(f);
                            System.out.println("Fabricante modificado correctamente");
                        } else {
                            System.out.println("El codigo del fabricante no existe en la base de datos");
                        }
                        break;
                    case 12:
                        System.out.print("Ingrese el codigo del fabricante que desea eliminar: ");
                        codFab = leer.nextInt();
                        f = fs.buscarFabricantePorCodigo(codFab);
                        if (f != null) {
                            fs.eliminarFabricante(f);
                            System.out.println("Fabricante eliminado correctamente");
                        } else {
                            System.out.println("El codigo del fabricante no existe en la base de datos");
                        }
                        break;
                }
            } while (opcMenu != 0);
        } catch (Exception e) {
        }
    }

}
