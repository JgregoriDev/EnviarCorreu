/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

/**
 *
 * @author w10
 */
import Dades.PersonesDades;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.postgresql.util.PSQLException;

public class ControladorBD extends ConectarBD {

    ArrayList<PersonesDades> llistaPersones = null;
    public ControladorBD(){
        super();
    }

    public Connection DBConnection() throws ClassCastException, SQLException {
        try {
            Connection c = null;
            try {
                Class.forName("org.postgresql.Driver");
            } catch (ClassNotFoundException ex) {
                System.out.println("El driver no ha pogut ser conectat " + ex.getLocalizedMessage());
            }
            //Les dades de conexio les hereda de la super classe 
            c = DriverManager.getConnection(url, user, password);
            System.out.println("Conectat");
            return c;
        } catch (SQLException ex) {
            System.out.println("No t'has pogut conectar a la base de dades");
        }
        return null;
    }

    public ArrayList<PersonesDades> carregarArrayRegistreBD(Connection con) throws ClassCastException, SQLException, NullPointerException, PSQLException{

        try {

            Connection c = con;
            if (c == null) {
                System.out.println("No s'ha conectat");
            } else {
                llistaPersones = new ArrayList<>();
                System.out.println("S'ha connectat");
//              Per a que les " siguen llegibles dins del string utilitzem la barra invertida. p. exemp:  \"  
                String sql = "select * from \"TaulaDades\";";
                System.out.println("" + sql);
                PreparedStatement pst = c.prepareStatement(sql);
                ResultSet rs = pst.executeQuery();
                System.out.println("Carregant ArrayList");
                while (rs.next()) {
                    llistaPersones.add(new PersonesDades(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
                }
                rs.close();
                c.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ControladorBD.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("" + ex.getMessage());
        }

        return llistaPersones;
    }

    public boolean insertarRegistres(String fecha, String Dni,Connection con) throws ClassCastException, SQLException {
        boolean insertExitos = false;
        try {
            Connection c = con;
            if (c != null) {
                Statement statement = c.createStatement();
                int n = statement.executeUpdate("INSERT INTO taulacorreusfecha(dni,fecha) VALUES ('" + Dni + "','" + fecha + "')");
//                System.out.println("n=" + n);
                statement.close();
                c.close();
            }
            return insertExitos;
        } catch (SQLException ex) {
            Logger.getLogger(ControladorBD.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }

}
