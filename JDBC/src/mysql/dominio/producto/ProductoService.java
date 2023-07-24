/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mysql.dominio.producto;

import java.util.ArrayList;
import java.util.List;
import mysql.persistencia.ProductoDAO;

/**
 *
 * @author Max
 */
public class ProductoService {
    
    private ProductoDAO dao;
    
    public ProductoService(){
        this.dao = new ProductoDAO();
    }
    
    public void crearProducto(String nombre, double precio, int codigo_fabricante) throws Exception{
        
        try {
            //VALIDACION
            if(nombre == null || nombre.trim().isEmpty()){
                throw new Exception ("Debe indicar el nombre del producto");
            }
            if(precio == 0 || nombre.trim().isEmpty()){
                throw new Exception ("Debe indicar el precio del producto");
            }
            if(codigo_fabricante == 0 || nombre.trim().isEmpty()){
                throw new Exception ("Debe indicar el codigo del fabricante del producto");
            }
            if(precio < 0){
                throw new Exception("El precio no puede ser menor a 0");
            }
            if(dao.buscarProductoPorNombreYFabricante(nombre,codigo_fabricante) != null){
                throw new Exception("Ya existe un producto cargado del fabricante indicado");
            }
            
            //CREACION DEL PRODUCTO
            Producto producto = new Producto();
            producto.setNombre(nombre);
            producto.setPrecio(precio);
            producto.setCodigo_fabricante(codigo_fabricante);
            dao.guardarProducto(producto);
        } catch (Exception e) {
            throw e;
        }
        
    }
    
    public void mostrarNombreProductos() throws Exception{
        try {
            List<Producto> productos = new ArrayList(dao.listarProductos());
            for(Producto p: productos){
                System.out.println(p.getNombre());
            }
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void mostrarNombreYPrecioProductos() throws Exception{
        try {
            List<Producto> productos = new ArrayList(dao.listarProductos());
            for(Producto p: productos){
                System.out.println("Producto: " + p.getNombre() + " $" + p.getPrecio());
            }
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void mostrarNombreYPrecioEntre120Y202Productos() throws Exception{
        try {
            List<Producto> productos = new ArrayList(dao.listarProductos());
            for(Producto p: productos){
                if(p.getPrecio() >= 120 && p.getPrecio() <= 202){
                    System.out.println("Producto: " + p.getNombre() + " $" + p.getPrecio());
                }                
            }
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void mostrarPortatilesDeProductos() throws Exception{
        try {
            List<Producto> productos = new ArrayList(dao.listarPortatilesProductos());
            for(Producto p: productos){
                System.out.println("Producto: " + p.getNombre() + " $" + p.getPrecio());      
            }
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void mostrarNombreYPrecioProductoMasBarato() throws Exception{
        try {
            Producto p = dao.mostrarProductoMasBarato();
            System.out.println("El producto mas barato es: " + p.getNombre() + " con un precio de $" + p.getPrecio());
        } catch (Exception e) {
            throw e;
        }
    }
    
    public Producto buscarProductoPorCodigo(int codigoProducto) throws Exception{
        try {
            if(codigoProducto < 1){
                throw new Exception("Debe ingresar un codigo del producto.");
            }
            return dao.buscarProductoPorCodigo(codigoProducto);
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void modificarProducto(Producto producto) throws Exception{
        try {
            if(producto == null){
                throw new Exception("El producto esta vacio.");
            }
            if(producto.getNombre() == null || producto.getNombre().trim().isEmpty()){
                throw new Exception ("Debe indicar el nombre del producto");
            }
            if(producto.getPrecio() == 0 || producto.getNombre().trim().isEmpty()){
                throw new Exception ("Debe indicar el precio del producto");
            }
            if(producto.getCodigo_fabricante() == 0 || producto.getNombre().trim().isEmpty()){
                throw new Exception ("Debe indicar el codigo del fabricante del producto");
            }
            if(producto.getPrecio() < 0){
                throw new Exception("El precio no puede ser menor a 0");
            }
            dao.modificarProducto(producto);
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void eliminarProducto(Producto producto) throws Exception{
        try {
            if(producto == null){
                throw new Exception("El producto esta vacio.");
            }
            dao.eliminarProducto(producto.getNombre(), producto.getCodigo_fabricante());
        } catch (Exception e) {
            throw e;
        }
    }
    
}
