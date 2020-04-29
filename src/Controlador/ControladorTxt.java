/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Dades.PersonesDades;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author w10
 */
public class ControladorTxt {

    public void escriureErrorsTXT(String data, PersonesDades pd, String Error) {
        try (FileWriter fw = new FileWriter("log-Errors.txt", true);
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter out = new PrintWriter(bw)) {
            out.println("==================================================");
            out.println("Fecha " + data);
            out.println("DNI:" + pd.getDNI() + " Nom: " + pd.getNom() + " Correu:" + pd.getCorreu() + "·Ruta:" + pd.getRuta());
            out.println("" + Error + "");
            out.println("==================================================");
            out.close();
            bw.close();
//            out.println("Més text");
        } catch (IOException e) {
        }
    }
     public void escriureErrorsTXT(String data,String Error) {
        try (FileWriter fw = new FileWriter("log-Errors.txt", true);
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter out = new PrintWriter(bw)) {
            out.println("==================================================");
            out.println("Fecha " + data);
            out.println("" + Error + "");
            out.println("==================================================");
            out.close();
            bw.close();
//            out.println("Més text");
        } catch (IOException e) {
        }
    }
    
    public void escriureResultatsCompletats(String Data, int i, int errors) {
        try (FileWriter fw = new FileWriter("log-Errors.txt", true);
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter out = new PrintWriter(bw)) {

            out.println("Procés completat " + Data);
            out.println("Persones que han recibit la nómina al correu de manera correcta " + i + " i errors en el enviament de correus=" + errors + "\n");
            out.close();
            bw.close();
        } catch (IOException e) {
            //exception handling left as an exercise for the reader
        }
    }

    public void logEnviats(String Data, PersonesDades pd) {
        try (FileWriter fw = new FileWriter("log-Enviats.txt", true);
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter out = new PrintWriter(bw)) {
            out.println("==================================================");
            out.println("Correu enviat " + Data);
            out.println("S'ha envia el archiu de manera correcta a " + pd.getCorreu() + "");
            out.println("DNI: " + pd.getDNI() + "\nNom:" + pd.getNom() + "\nRuta del archiu enviat: " + pd.getRuta() + "\n ");
            out.println("==================================================");
            out.close();
            bw.close();
        } catch (IOException e) {
        }
    }
     public void ResultatsCompletats(String Data, int i, int errors) {
        try (FileWriter fw = new FileWriter("log-Enviats.txt", true);
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter out = new PrintWriter(bw)) {

            out.println("Procés completat " + Data);
            out.println("Persones que han recibit la nómina al correu de manera correcta " + i + " i errors en el enviament de correus=" + errors + "\n");
            out.close();
            bw.close();
        } catch (IOException e) {
        }
    }

    public void avisosEspaiBlan(String Data, PersonesDades pd) {
        try (FileWriter fw = new FileWriter("log-Warnings.txt", true);
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter out = new PrintWriter(bw)) {
            out.println("Avis de correcció " + Data);
            out.println("El correu " + pd.getCorreu() + " conté un espai en blanc al final, seria adequat corregir espai blanc al final.\n");
            out.close();
            bw.close();
        } catch (IOException e) {
        }
    }
}
