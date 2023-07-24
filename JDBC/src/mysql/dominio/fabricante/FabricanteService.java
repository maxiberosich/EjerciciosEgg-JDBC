/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mysql.dominio.fabricante;

import mysql.persistencia.FabricanteDAO;

/**
 *
 * @author Max
 */
public class FabricanteService {
    private final FabricanteDAO fDao;

    public FabricanteService() {
        this.fDao = new FabricanteDAO();
    }

    public void guardarFabricante(String nombre) throws Exception{
        try {
            if(nombre == null){
                throw new Exception("Debe ingresar el nombre del fabricante.");
            }
            
            Fabricante fabricante = new Fabricante(nombre);
            fDao.guardarFabricante(fabricante);
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void mostrarFabricantes() throws Exception{
        try {
            for(Fabricante f: fDao.listarFabricantes()){
                System.out.println("<>  " + f.getNombreF());
            }
        } catch (Exception e) {
            throw e;
        }
    }
    
    public Fabricante buscarFabricantePorCodigo(int cod) throws Exception{
        try {
            if(cod < 0){
                throw new Exception("Debe ingresar un codigo valido");
            }
            Fabricante fabricante = fDao.buscarFabricantePorCodigo(cod);
            return fabricante;
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void modificarFabricante(Fabricante fabricante) throws Exception{
        try {
            if(fabricante == null){
                throw new Exception("Debe ingresar un fabricante");
            }
            fDao.modificarFabricante(fabricante);
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void eliminarFabricante(Fabricante fabricante) throws Exception{
        try {
            if(fabricante == null){
                throw new Exception("Debe ingresar un fabricante");
            }
            fDao.eliminarFabricante(fabricante);
        } catch (Exception e) {
            throw e;
        }
    }
    
    
}
