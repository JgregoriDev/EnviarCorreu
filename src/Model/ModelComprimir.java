/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Controlador.ControladorComprimir;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author w10
 */
public class ModelComprimir {
    File f;
    private File ArchiuZip;
    private String password;
    public ModelComprimir(File f,String password) {
        this.f=f;
        this.password=password;
        comprimirPDFs();
        
    }
    public void comprimirPDFs(){
        System.out.println("Comprimint "+f.getAbsolutePath());
        ControladorComprimir cc=new ControladorComprimir(f, password);
//        ArchiuZip=new File(cc.getRutaZip());
//        System.out.println(""+ArchiuZip.getAbsolutePath());
    }

    public File getArchiuZip() {
        return ArchiuZip;
    }

  
    
    
}
