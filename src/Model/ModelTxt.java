/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Controlador.ControladorTxt;
import Dades.PersonesDades;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author w10
 */
public class ModelTxt {
    public void cridarControladorTxtCompletat(String Data,int acertats, int fallos){
        ControladorTxt ct=new ControladorTxt();
        ct.escriureResultatsCompletats(Data,acertats, fallos);
    }
    public void cridarControladorTxtErrors(String Data,PersonesDades pd, String error){
        ControladorTxt ct=new ControladorTxt();
        ct.escriureErrorsTXT(Data, pd,error);
    }
    public void cridarWarningEspaiBlanc(PersonesDades pd, String Data){
        ControladorTxt ct=new ControladorTxt();
        ct.avisosEspaiBlan(Data, pd);
    }
    public void cridarEnviatsSatisfactoris(String data, PersonesDades pd){
        ControladorTxt ct=new ControladorTxt();
        ct.logEnviats(data, pd);
    }
    public void cridarResultatsCompletats(String data,int acertats,int fallos){
        ControladorTxt ct=new ControladorTxt();
        ct.ResultatsCompletats(data, acertats, fallos);
    }
    public void cridarControladorTxtErrors(String Data, String error){
        ControladorTxt ct=new ControladorTxt();
        ct.escriureErrorsTXT(Data,error);
    }
    
 
}
