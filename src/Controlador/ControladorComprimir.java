/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Model.ModelTxt;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.Zip4jConstants;

/**
 *
 * @author w10
 */
public class ControladorComprimir {

    File f;
    String password;
    String RutaZip;

    //con
    public String getRutaZip() {
        return RutaZip;
    }

    public ControladorComprimir(File f, String password) {
        this.f = f;
        this.password = password;
        cridarCompresio();
    }
// Aquest metode realitza l'accio de cridar el metode que s'encarrega de comprimir

    public void cridarCompresio() {
        String archiuRuta = f.getAbsolutePath().substring(0, f.getAbsolutePath().length() - 4) + ".zip";
        System.out.println("ZIP Ruta:" + archiuRuta);
        if (!new File(archiuRuta).exists()) {
            comprimirArchiuContrasenya(archiuRuta);

        } else {
            System.out.println("Ja existeix el archiu");
        }
    }
//Comprimeix amb contrasenya

    public void comprimirArchiuContrasenya(String archiuRuta) {
        System.out.println("No existeix comprimint");
        this.RutaZip = archiuRuta;
        try {
            ZipFile zipFile = new ZipFile(archiuRuta);
            ArrayList filesToAdd = new ArrayList();
            //Afegeix un archiu al array    
            filesToAdd.add(f);
            System.out.println("Configurar parametros de comprision");
            ZipParameters parameters = new ZipParameters();
            //
            parameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE); //
            parameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL);
            if (password.length() > 0) {
                parameters.setEncryptFiles(true);
                parameters.setEncryptionMethod(Zip4jConstants.ENC_METHOD_AES);
                parameters.setAesKeyStrength(Zip4jConstants.AES_STRENGTH_256);
                System.out.println("Posant contrasenya " + password);
                parameters.setPassword(password);
            }
            zipFile.addFiles(filesToAdd, parameters);
            System.out.println("Creant el archiu");

        } catch (ZipException e) {
            ModelTxt modelTxt = new ModelTxt();
            modelTxt.cridarControladorTxtErrors("27/03/2019", "Error l'archiu no ha pogut ser comprimit");
            e.printStackTrace();
        }
    }

}
