 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dades;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author w10
 */
public class ContingutMissatge {
    private String Assumpte;
    private String Missatge;
    private String correuElectronic;
    private String password;

    public ContingutMissatge(String Assumpte, String Missatge, String correuElectronic, String password) {
        this.Assumpte = Assumpte;
        this.Missatge = Missatge;
        this.correuElectronic = correuElectronic;
        this.password = password;
       
    }
    public ArrayList<File> getLlistaPdf() {
        return llistaPdf;
    }

    public void setLlistaPdf(ArrayList<File> llistaPdf) {
        this.llistaPdf = llistaPdf;
    }

    public ArrayList<String> getLlistaCorreus() {
        return llistaCorreus;
    }

    public void setLlistaCorreus(ArrayList<String> llistaCorreus) {
        this.llistaCorreus = llistaCorreus;
    }
    private ArrayList<File> llistaPdf;
    private ArrayList<String> llistaCorreus;

    
    public ContingutMissatge() {
    }

    public String getAssumpte() {
        return Assumpte;
    }

    public void setAssumpte(String Assumpte) {
        this.Assumpte = Assumpte;
    }

    public String getMissatge() {
        return Missatge;
    }

    public void setMissatge(String Missatge) {
        this.Missatge = Missatge;
    }

    public String getCorreuElectronic() {
        return correuElectronic;
    }

    public void setCorreuElectronic(String correuElectronic) {
        this.correuElectronic = correuElectronic;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
}
