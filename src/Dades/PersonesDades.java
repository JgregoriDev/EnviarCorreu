/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dades;

import java.io.File;

/**
 *
 * @author w10
 */
public class PersonesDades {
   private String DNI;
   private String nom; 
   private String cognom1; 
   private String cognom2; 
   private String correu;  
   private String Ruta;
   private boolean correuExisteix;

    public PersonesDades(String DNI, String nom, String cognom1, String cognom2, String correu) {
        this.DNI = DNI;
        this.nom = nom;
        this.cognom1 = cognom1;
        this.cognom2 = cognom2;
        this.correu = correu;
    }
 
    public void setCorreu(String correu) {
        this.correu = correu;
    }
    
    public String getRuta() {
        return Ruta;
    }

    public void setRuta(String Ruta) {
        this.Ruta = Ruta;
    }

   
    public String getNom() {
        return nom;
    }

    public String getCognom1() {
        return cognom1;
    }

    public String getCognom2() {
        return cognom2;
    }

    public String getCorreu() {
        return correu;
    }

    public String getDNI() {
        return DNI;
    }

    public boolean isCorreuExisteix() {
        return correuExisteix;
    }

    public void setCorreuExisteix(boolean correuExisteix) {
        this.correuExisteix = correuExisteix;
    }
   
}
