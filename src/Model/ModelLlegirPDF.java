/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Controlador.ControladorLlegirPDF;
import Dades.PersonesDades;
import java.io.File;
import java.util.ArrayList;

/**
 *
 * @author w10
 */
public class ModelLlegirPDF {

    private ArrayList<String> llistaNifs;
    ControladorLlegirPDF clpdf;
    String Nif;
    File filePdf;
    ArrayList<PersonesDades> pd;
    public ModelLlegirPDF(ArrayList <PersonesDades> pd) {
        llistaNifs = new ArrayList<>();
        Nif = "";
        this.pd=pd;
    }

    
    public void LlegirPDF(File archiu) {
        clpdf = new ControladorLlegirPDF(pd);

        System.out.println("RUTA PDF: " + archiu.getAbsolutePath());
        clpdf.LlegirPDF(archiu);
        Nif = clpdf.getNIF();
        String nomPDF = archiu.getAbsolutePath();
        filePdf = new File(nomPDF);
        llistaNifs.add(Nif);
        
    }

    public File getFilePdf() {
        return filePdf;
    }

    public void setFileZip(File fileZip) {
        this.filePdf = fileZip;
    }

//    public ArrayList arrayLlistaUsuaris(){
//        return clpdf.getLlistaUsuarisNomines();
//    }
    public String getNif() {
        return Nif;
    }

    public ArrayList<String> getLlistaNifs() {
        return llistaNifs;
    }

    public void setLlistaUsuaris(ArrayList<String> llistaUsuaris) {
        this.llistaNifs = llistaUsuaris;
    }

}
