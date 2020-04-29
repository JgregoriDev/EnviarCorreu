/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Dades.PersonesDades;
import Model.ModelBaseDades;
import Model.ModelComprimir;
import Model.ModelCorreuElectronic;
import Model.ModelTxt;
import Model.ModelValidarCorreu;
//import com.sun.xml.internal.ws.model.RuntimeModeler;
import java.awt.Color;
import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;

/**
 *
 * @author w10
 */
public class PanelBarraProgress extends javax.swing.JPanel implements Runnable {

    /**
     * Creates new form PanelBarraProgress
     */
    Thread th;
    JButton button;
    private String Assumpte = null;
    private String Missatge = null;
    int numElements;
    private ArrayList<PersonesDades> llistaPersones;
//    private ArrayList<File> llistaPDF;
    private File f;
    private boolean finalitzar;
    File filezip;
    private ModelValidarCorreu mvc;

    public boolean isFinalitzar() {
        return finalitzar;
    }

    public PanelBarraProgress(JButton B_seguent, ArrayList<PersonesDades> llista, String Assumpte, String Missatge) {
        initComponents();

        th = new Thread((Runnable) this);
        mvc = new ModelValidarCorreu();

        this.button = B_seguent;
        this.numElements = numElements;
        this.llistaPersones = llista;
        th.start();
        TextProcessos.setText("Conexió de la base de dades realitzada de manera correcta");
        f = null;
        this.Assumpte = Assumpte;
        this.Missatge = Missatge;
        llistaErrors = new ArrayList<PersonesDades>();
        finalitzar = false;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jProgressBar1 = new javax.swing.JProgressBar();
        jScrollPane1 = new javax.swing.JScrollPane();
        TextProcessos = new javax.swing.JTextArea();

        jProgressBar1.setStringPainted(true);

        TextProcessos.setEditable(false);
        TextProcessos.setColumns(20);
        TextProcessos.setRows(5);
        jScrollPane1.setViewportView(TextProcessos);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea TextProcessos;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
    public void validarCorreus() {
        System.out.println("");
        int j = 0;
        System.out.println("" + llistaPersones.size());
        for (PersonesDades personesDades : llistaPersones) {
            j++;
            if (personesDades.getCorreu().charAt(personesDades.getCorreu().length() - 1) == ' ') {
                personesDades.setCorreu(personesDades.getCorreu().substring(0, personesDades.getCorreu().length() - 1));
                Calendar fecha = new GregorianCalendar();
                ModelTxt mt = new ModelTxt();
                mt.cridarWarningEspaiBlanc(personesDades, prepararData());
            }
            if (personesDades.getCorreu().contains("@bellreguard.net")) {
                personesDades.setCorreuExisteix(true);
                TextProcessos.append("\n" + personesDades.getCorreu() + " el correu existeix");
            } else {
                personesDades.setCorreuExisteix(mvc.validarCorreu(personesDades.getCorreu()));
                if (personesDades.isCorreuExisteix()) {
                    System.out.println(j + ". " + personesDades.getCorreu() + " el correu existeix");
                    TextProcessos.append("\n" + personesDades.getCorreu() + " el correu existeix");
                } else {

                    TextProcessos.append("\n" + personesDades.getCorreu() + " el correu no existeix");
                    System.out.println(j + ". " + personesDades.getCorreu() + " el correu no existeix");

                }
            }

        }
    }

    @Override
    public void run() {
        TextProcessos.setText(TextProcessos.getText().concat("\nValidant correus electronics"));
        validarCorreus();
        TextProcessos.append("\nS'han acabat de validar els correu electronics.");
        procesIteracio();
    }

    public void procesIteracio() {
        try {
            int iteracio = 1;
            System.out.println("llista=" + llistaPersones.size());
            int PerCentatge = 100 / llistaPersones.size();

            ModelTxt modelTxt = new ModelTxt();
            int i = 0;
            for (PersonesDades personesDades : llistaPersones) {
                i++;

                System.out.println("dormint " + 200);
                Thread.sleep(200);
                if (personesDades.isCorreuExisteix()) {
                    Preparar(personesDades, iteracio);
                } else {

                    modelTxt.cridarControladorTxtErrors(prepararData(), personesDades, "Error el correu " + personesDades.getCorreu() + " no existeix");
                    llistaErrors.add(personesDades);

                }
                jProgressBar1.setValue(jProgressBar1.getValue() + PerCentatge);
                if (iteracio >= llistaPersones.size()) {
                    Thread.sleep(500);
                    jProgressBar1.setValue(100);
                    System.out.println("Progress 100%");
                    getjProgressBar1().setString("Procés al 100%.");
                    button.setText("Finalitzar");

                    button.setEnabled(true);
                }
                iteracio++;
            }

            if (jProgressBar1.getValue() == 100) {
//                TextProcessos.setForeground(Color.GREEN);
                finalitzar = true;
                Calendar Data = new GregorianCalendar();
                modelTxt.cridarControladorTxtCompletat(prepararData(), llistaPersones.size() - llistaErrors.size(), llistaErrors.size());
                modelTxt.cridarResultatsCompletats(Missatge, llistaPersones.size() - llistaErrors.size(), llistaErrors.size());
                TextProcessos.setText(TextProcessos.getText().concat("\nProcés completat en brevetat podràs desbloquejar el botó"));
            }

        } catch (InterruptedException ex) {
            System.out.println("" + ex.getLocalizedMessage());
        } catch (ArithmeticException ae) {
            TextProcessos.append("\n" + "No s'ha conectat de manera correcta a la base de dades");
//            JOptionPane.showMessageDialog(null, "Error No s'ha pogut omplir les llistes a partir de la base de dades", "Error BD Apaga", JOptionPane.ERROR_MESSAGE);
        }
    }

    public JProgressBar getjProgressBar1() {
        return jProgressBar1;
    }

    //S'encarrega de cridar el metode de comprimir i envia
    public void Preparar(PersonesDades pd, int iteracio) {
        System.out.println(iteracio + ". Correu: " + pd.getCorreu());
        System.out.println("");
        iteracio++;
        try {
            String correu = pd.getCorreu().substring(pd.getCorreu().length() - 16, pd.getCorreu().length());
            if (correu.equals("@bellreguard.net")) {
                System.out.println("El courreu conte el domini @bellreguard.net.");
                enviar(pd);
                insertarBD(pd.getDNI());
            } else {
                TextProcessos.append("\nComprimint archiu pdf");
                comprimir(pd);
                TextProcessos.append("\nArchiu comprimit" + pd.getRuta());

                String nomZip = pd.getRuta().substring(0, pd.getRuta().length() - 4).concat(".zip");

                if (new File(nomZip).exists()) {
                    System.out.println("Zip archivo:" + nomZip);
                    pd.setRuta(nomZip);
                    insertarBD(pd.getDNI());
                    TextProcessos.append("\n" + "Enviant l'Archiu Zip" + pd.getRuta() + " al correu " + pd.getCorreu() + "");
                    enviar(pd);
                } else {
                    TextProcessos.setText(TextProcessos.getText().concat("\nNo s'ha pogut enviar el correu a" + pd.getCorreu() + " ja que el archiu no exixsteix"));
                    System.out.println("El archiu no existeix per lo tant no enviarem");
                }
            }
        } catch (NullPointerException e) {
            String error = "";
            System.out.println("L'arxiu del usuari amb DNI:" + pd.getDNI() + " no existeix ");
            ModelTxt mt = new ModelTxt();
            System.out.println("Ruta " + pd.getRuta() + " Correu " + pd.getCorreu());
            if (pd.getRuta() == null) {
                error = "L'arxiu del usuari amb DNI:" + pd.getDNI() + " no existeix o o bé el DNI no ha sigut trobat en els archiu pdf";
            }
            TextProcessos.append(error);
            System.out.println("Error");
            llistaErrors.add(pd);
            mt.cridarControladorTxtErrors(prepararData(), pd, "Error " + error);
        }
    }
    private ArrayList<PersonesDades> llistaErrors;
//

    public void insertarBD(String DNI) {
        boolean n;
        try {
            ModelBaseDades m = new ModelBaseDades();
            n = m.Insertar(DNI, prepararData());
            if (n) {
                TextProcessos.append("Actualizant informacio de la base de dades");
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PanelBarraProgress.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error driver no trobat", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex) {
            Logger.getLogger(PanelBarraProgress.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error en l'insercio a la base de dades", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    //Metode encarregat de cridar i omplir 
    public void comprimir(PersonesDades pd) {
//        System.out.println("Ruta d'archiu a comprimir "+f.getAbsolutePath());
        System.out.println("Contrasenya:" + pd.getDNI());

        if (pd.getRuta() == null || pd.getRuta().equals("")) {

        } else {
//            TextProcessos.setText(TextProcessos.getText().concat("\nComprimint l'arxiu: " + pd.getRuta()));
            TextProcessos.append("\nComprimint l'arxiu: " + pd.getRuta());
            File archiuNC = new File(pd.getRuta());

            System.out.println("Archiu no comprimit ruta:" + archiuNC);

            ModelComprimir mc = new ModelComprimir(archiuNC, pd.getDNI());
            TextProcessos.append("\n" + "L'arxiu: " + pd.getRuta() + " ha sigut comprimit de manera correcta");
            System.out.println("Ruta " + pd.getRuta());
            File filezip = mc.getArchiuZip();
            this.filezip = filezip;
        }
    }

    public void enviar(PersonesDades pd) {
        try {
            ModelCorreuElectronic mce = new ModelCorreuElectronic();

            if (pd.getRuta().equals("") || pd.getRuta() == null || pd.getRuta().equals("null")) {
                TextProcessos.setForeground(Color.red);
//                TextProcessos.setText(TextProcessos.getText().concat("\nEl archiu no " + pd.getRuta() + " ha pogut ser enviat a" + pd.getCorreu() + " ja que no ha coincidit amb el DNI"));
                System.out.println("No s'ha pogut enviar un correu a " + pd.getCorreu() + " ja que no conte un el archiu a enviar");
            } else {
                //TextProcessos.setText(TextProcessos.getText().concat("\nEnviant el archiu:" + pd.getRuta() + " al" + pd.getCorreu()));
                System.out.println("Ruta Zip a enviar:" + pd.getRuta());
                try {
                    String n = mce.enviarCorreu(pd,
                            Assumpte,
                            Missatge);
                    ModelTxt mt = new ModelTxt();
                    TextProcessos.append("\n" + n.concat("" + pd.getCorreu()));
                    mt.cridarEnviatsSatisfactoris(prepararData(), pd);
                } catch (MessagingException me) {
                    System.out.println("" + me.getMessage());
                } catch (Exception e) {
                    System.out.println("" + e.getMessage());
                }
            }
        } catch (NullPointerException e) {
            TextProcessos.setText(TextProcessos.getText().concat("\nError el archiu no cumpleix els reguisits establits per el format de la nomina o bé no existeix"));
        }
    }

    public String prepararData() {
        Calendar data = new GregorianCalendar();
        int año = data.get(Calendar.YEAR);
        int mes = data.get(Calendar.MONTH);
        mes += 1;
        int dia = data.get(Calendar.DAY_OF_MONTH);
        int hora = data.get(Calendar.HOUR_OF_DAY);
        int minuts = data.get(Calendar.MINUTE);
        String mmes = "";
        String ddia = "";
        String mminuts = "";
        String hhora = "";
        //Li concadene un 0 ja que en el cas que siga menor que 10 sols mostrara un valor menor de 0 
//        per exemple si son les 10:07 mostrara 10:7 per aquest motiu concadene un 0 als distins camps
        if (dia < 10) {
            ddia = "0" + dia;
        } else {
            ddia = "" + dia;
        }
        if (mes < 10) {
            mmes = "0" + mes;
        } else {
            mmes = "" + mes;
        }
        if (hora < 10) {
            hhora = "0" + hora;
        } else {
            hhora = "" + hora;

        }
        if (minuts < 10) {
            mminuts = "0" + minuts;
        } else {
            mminuts = "" + minuts;

        }
        String dataTemps = "Dia: " + ddia + "/" + mmes + "/" + año + " Hora:" + hhora + ":" + mminuts + "";
        return dataTemps;
    }
}