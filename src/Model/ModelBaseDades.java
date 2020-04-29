/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Controlador.ControladorBD;
import Dades.PersonesDades;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.postgresql.util.PSQLException;

/**
 *
 * @author w10
 */
public class ModelBaseDades {

    private ArrayList<PersonesDades> llistaPersones;

    public ArrayList<PersonesDades> getLlistaPersones() {
        return llistaPersones;
    }
    
    public ModelBaseDades() {
        llistaPersones = new ArrayList<>();
    }
    
    public void carregarLlistaDades()throws ClassNotFoundException, SQLException,PSQLException{
            ControladorBD cbd = new ControladorBD();
            System.out.println("Omplint ArrayList");
            Connection con =cbd.DBConnection();
            llistaPersones = cbd.carregarArrayRegistreBD(con);
            System.out.println("Tamany llista " + llistaPersones.size());
    }
    public boolean Insertar(String dni, String fecha)throws ClassNotFoundException, SQLException{
        boolean n=false;
        ControladorBD cbd = new ControladorBD();
        Connection con =cbd.DBConnection();
        if(cbd.insertarRegistres(fecha, dni, con))
            n=true;
        return n;
    }
    public int ElementsLlista(){
        return llistaPersones.size();
    }
}
