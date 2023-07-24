/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mysql.persistencia;

import java.util.ArrayList;
import java.util.Collection;
import mysql.dominio.fabricante.Fabricante;

/**
 *
 * @author Max
 */
public class FabricanteDAO extends DAO {
    
    public void guardarFabricante(Fabricante fabricante) throws Exception{
        try {
            if(fabricante == null){
                throw new Exception("Debe ingresar un fabricante");
            }
            
            String sql = "INSERT INTO Fabricante (nombre)"
                    + "VALUES ( '" + fabricante.getNombreF() + "' );";
            insertarModificarEliminar(sql);
        } catch (Exception e) {
            throw e;
        }
    }
    
    public Collection<Fabricante> listarFabricantes() throws Exception {
        try {
            String sql = "SELECT * FROM fabricante;";

            consultarBase(sql);

            Fabricante fabricante = null;
            Collection<Fabricante> fabricantes = new ArrayList();
            while (resultado.next()) {
                fabricante = new Fabricante();
                fabricante.setCodigoF(resultado.getInt(1));
                fabricante.setNombreF(resultado.getString(2));
                fabricantes.add(fabricante);
            }

            desconectarBase();
            return fabricantes;
        } catch (Exception e) {
            e.printStackTrace();
            desconectarBase();
            throw new Exception("Error de sistema");
        }
    }
    
    public Fabricante buscarFabricantePorCodigo(int codigo) throws Exception {
        try {
            if (codigo < 1) {
                throw new Exception("Debe ingresar un codigo.");
            }

            String sql = "SELECT * FROM fabricante WHERE codigo = '" + codigo + "';";
            consultarBase(sql);

            Fabricante fabricante = null;

            while (resultado.next()) {
                fabricante = new Fabricante();
                fabricante.setCodigoF(resultado.getInt(1));
                fabricante.setNombreF(resultado.getString(2));
            }

            desconectarBase();

            return fabricante;

        } catch (Exception e) {
            desconectarBase();
            throw e;
        }
    }
    
    public void modificarFabricante(Fabricante fabricante) throws Exception {
        try {
            if (fabricante == null) {
                throw new Exception("Debe ingresar el fabricante que desea modificar");
            }
            
            String sql = "UPDATE fabricante SET " + "nombre = '" + fabricante.getNombreF() 
                    + "' " + "WHERE codigo = '" + fabricante.getCodigoF() + "' ;";
            insertarModificarEliminar(sql);
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void eliminarFabricante(Fabricante f) throws Exception {
        try {
            if (f.getNombreF() == null) {
                throw new Exception("Debe ingresar el nombre del producto que desea eliminar.");
            }
            if (f.getCodigoF() < 1) {
                throw new Exception("Debe ingresar un codigo fabricante.");
            }

            if (buscarFabricantePorCodigo(f.getCodigoF()) != null) {
                String sql = "DELETE FROM fabricante WHERE nombre = '" + f.getNombreF() + "' AND "
                        + "codigo = '" + f.getCodigoF() + "';";
                insertarModificarEliminar(sql);
            }else{
                System.out.println("No existe el fabricante que desea eliminar");
            }
        } catch (Exception e) {
            throw e;
        }
    }
    
}
