/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Controlador.ControladorEmail;
import Dades.PersonesDades;
import java.io.File;
import javax.mail.MessagingException;

/**
 *
 * @author w10
 */
public class ModelCorreuElectronic {
    public String enviarCorreu(PersonesDades pd,String Assumpte,String Missatge)throws MessagingException{
        System.out.println("ruta zip:"+pd.getRuta());
        ControladorEmail ce=new ControladorEmail(new File(pd.getRuta()),Assumpte, Missatge,pd);
        ce.enviarCorreu();
        return ce.getProcesEx();
    }
}
