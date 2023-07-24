/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mysql.persistencia;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;
import mysql.dominio.producto.Producto;

/**
 *
 * @author Max
 */
public final class ProductoDAO extends DAO {

    public void guardarProducto(Producto producto) throws Exception {
        try {
            if (producto == null) {
                throw new Exception("Debe ingresar un producto");
            }

            String sql = "INSERT INTO Producto (nombre,precio,codigo_fabricante)"
                    + "VALUES ( '" + producto.getNombre() + "' , '" + producto.getPrecio() + "' , '"
                    + producto.getCodigo_fabricante() + "' );";
            insertarModificarEliminar(sql);
        } catch (Exception e) {
            throw e;
        }
    }

    public void modificarProducto(Producto producto) throws Exception {
        try {
            if (producto == null) {
                throw new Exception("Debe ingresar el producto que desea modificar");
            }
            
            String sql = "UPDATE producto SET " + "nombre = '" + producto.getNombre() + "',"
                    + "precio = '" + producto.getPrecio()
                    + "', codigo_fabricante = '" + producto.getCodigo_fabricante() + "' "
                    + "WHERE codigo = '" + producto.getCodigo() + "' ;";
            insertarModificarEliminar(sql);
        } catch (Exception e) {
            throw e;
        }
    }

    public void eliminarProducto(String nombre, int codigo_fabricante) throws Exception {
        try {
            if (nombre == null) {
                throw new Exception("Debe ingresar el nombre del producto que desea eliminar.");
            }
            if (codigo_fabricante < 1) {
                throw new Exception("Debe ingresar un codigo fabricante.");
            }

            if (buscarProductoPorNombreYFabricante(nombre, codigo_fabricante) != null) {
                String sql = "DELETE FROM producto WHERE nombre = '" + nombre + "' AND "
                        + "codigo_fabricante = '" + codigo_fabricante + "';";
                insertarModificarEliminar(sql);
            }else{
                System.out.println("No existe el producto que desea eliminar");
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public Producto buscarProductoPorNombreYFabricante(String nombre, int codigo_fabricante) throws Exception {
        try {
            if (nombre == null) {
                throw new Exception("Debe ingresar el nombre del producto que desea eliminar.");
            }
            if (codigo_fabricante < 1) {
                throw new Exception("Debe ingresar un codigo fabricante.");
            }

            String sql = "SELECT * FROM producto WHERE nombre = '" + nombre + "' "
                    + " AND codigo_fabricante = " + codigo_fabricante;
            consultarBase(sql);

            Producto producto = null;

            while (resultado.next()) {
                producto = new Producto();
                producto.setCodigo(resultado.getInt(1));
                producto.setNombre(resultado.getString(2));
                producto.setPrecio(resultado.getDouble(3));
                producto.setCodigo_fabricante(resultado.getInt(4));
            }

            desconectarBase();

            return producto;

        } catch (Exception e) {
            desconectarBase();
            throw e;
        }
    }
    
    public Producto buscarProductoPorCodigo(int codigo) throws Exception {
        try {
            if (codigo < 1) {
                throw new Exception("Debe ingresar un codigo.");
            }

            String sql = "SELECT * FROM producto WHERE codigo = '" + codigo + "';";
            consultarBase(sql);

            Producto producto = null;

            while (resultado.next()) {
                producto = new Producto();
                producto.setCodigo(resultado.getInt(1));
                producto.setNombre(resultado.getString(2));
                producto.setPrecio(resultado.getDouble(3));
                producto.setCodigo_fabricante(resultado.getInt(4));
            }

            desconectarBase();

            return producto;

        } catch (Exception e) {
            desconectarBase();
            throw e;
        }
    }

    public Collection<Producto> listarProductos() throws Exception {
        try {
            String sql = "SELECT * FROM producto;";

            consultarBase(sql);

            Producto producto = null;
            Collection<Producto> productos = new ArrayList();
            while (resultado.next()) {
                producto = new Producto();
                producto.setCodigo(resultado.getInt(1));
                producto.setNombre(resultado.getString(2));
                producto.setPrecio(resultado.getDouble(3));
                producto.setCodigo_fabricante(resultado.getInt(4));
                productos.add(producto);
            }

            desconectarBase();
            return productos;
        } catch (Exception e) {
            e.printStackTrace();
            desconectarBase();
            throw new Exception("Error de sistema");
        }
    }

    public Collection<Producto> listarPortatilesProductos() throws Exception {
        try {
            String sql = "SELECT * FROM producto WHERE nombre LIKE '%Portatil%';";

            consultarBase(sql);

            Producto producto = null;
            Collection<Producto> productos = new ArrayList();
            while (resultado.next()) {
                producto = new Producto();
                producto.setCodigo(resultado.getInt(1));
                producto.setNombre(resultado.getString(2));
                producto.setPrecio(resultado.getDouble(3));
                producto.setCodigo_fabricante(resultado.getInt(4));
                productos.add(producto);
            }

            desconectarBase();
            return productos;
        } catch (Exception e) {
            e.printStackTrace();
            desconectarBase();
            throw new Exception("Error de sistema");
        }
    }

    public Producto mostrarProductoMasBarato() throws Exception {
        try {
            String sql = "SELECT * FROM producto WHERE precio = (SELECT MIN(precio) FROM producto);";

            consultarBase(sql);

            Producto producto = null;
            while (resultado.next()) {
                producto = new Producto();
                producto.setCodigo(resultado.getInt(1));
                producto.setNombre(resultado.getString(2));
                producto.setPrecio(resultado.getDouble(3));
                producto.setCodigo_fabricante(resultado.getInt(4));
            }

            desconectarBase();
            return producto;
        } catch (Exception e) {
            e.printStackTrace();
            desconectarBase();
            throw new Exception("Error de sistema");
        }
    }

}
