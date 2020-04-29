/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Dades.PersonesDades;
import Model.ModelTxt;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;

/**
 *
 * @author w10
 */
public class ControladorLlegirPDF {

    private String NIF;
    boolean NifDetectat;
    private ArrayList<PersonesDades> pd;

    public ControladorLlegirPDF(ArrayList<PersonesDades> pd) {
        NIF = "";
        NifDetectat = false;
        this.pd = pd;
    }

    public void LlegirPDF(File archiu) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        try (PDDocument document = PDDocument.load(archiu)) {

            document.getClass();
            boolean bucle = false;
            if (!document.isEncrypted()) {
                System.out.println("" + archiu.getName());
                PDFTextStripperByArea stripper = new PDFTextStripperByArea();
                stripper.setSortByPosition(true);

                PDFTextStripper tStripper = new PDFTextStripper();

                String pdfFileInText = tStripper.getText(document);
                // divideix amb un espai en blanc
                int s = 0, n = 0;
                String lines[] = pdfFileInText.split("\\r?\\n");
                //Comença per linea 0 i la linea 64 que conte el nif es la 63
                for (String line : lines) {
                    //Com suma un al començar
                    n++;

                    //Busca la coincidencia amb amb el dni
                    for (PersonesDades personad : pd) {
                        
                        if (line.contains(personad.getDNI())) {
                            System.out.println("La linea n " + n + " conte la paraula buscada");
                            s = n - 1;
                            NifDetectat = true;
                            break;
                        }
                    }

                }
                NIF = lines[s];
                System.out.println("Nif " + NIF);

            }

        } catch (IOException ex) {
            ModelTxt modelTxt=new ModelTxt();
            modelTxt.cridarControladorTxtErrors("27/03/2020","El document no ha pogut llegir");
            System.out.println("El document no ha pogut ser llegit");
        }
    }

    public String getNIF() {
        return NIF;
    }

}
