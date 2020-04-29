/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Dades.PersonesDades;

/**
 *
 * @author w10
 */
import java.io.File;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.IllegalWriteException;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.JOptionPane;

public class ControladorEmail extends ConectarEmail {

    private String Assumpte;
    private String Missatge;
    private String destinatari;
    private File f;
    private String email;
    private String password;
    private PersonesDades pd;
    private String procesEx;

    public String getProcesEx() {
        return procesEx;
    }

    public void setProcesEx(String procesEx) {
        this.procesEx = procesEx;
    }

    public ControladorEmail(File f, String Assumpte, String Missatge, PersonesDades pd) {
        super();
        this.Assumpte = Assumpte;
        this.Missatge = Missatge;
        this.destinatari = pd.getCorreu();
        this.f = f;
        this.pd = pd;

        email = getEmail();
        password = getPassword();

    }

    public void enviarCorreu() throws MessagingException {
        System.out.println("Propietats correu electronic");
        Properties properties = new Properties();
        //Introduim la informacio necesaria per a conectarse
        //Codi login bellreguard
//        properties.put("mail.smtp.auth", "true");
//        properties.put("mail.smtp.starttls.enable", "true");
//        properties.put("mail.smtp.host", "mail.bellreguard.net");
//        properties.put("mail.smtp.port", "587");
        //codi login gmail
        properties.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
        properties.put("mail.smtp.port", "587"); //TLS Port
        properties.put("mail.smtp.auth", "true"); //enable authentication
        properties.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS
        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(email, password); //To change body of generated methods, choose Tools | Templates.
            }
        });
        System.out.println("Login exitos");
        Message message = prepareMessage(session, email, destinatari, f);
        System.out.println("Mensaje preparat de manera correcta");
        Transportar(message);
    }

    public Message prepareMessage(Session s, String userMail, String recipient, File f) {
        try {

            Message message = new MimeMessage(s);
            //Contingut del missatge a enviar
            BodyPart texto = new MimeBodyPart();
            texto.setText(Missatge);
            //Contingut del archiu adjunt
            BodyPart adjunto = new MimeBodyPart();
//            System.out.println("Preparant enviament de nomina " + f.getName());
            adjunto.setDataHandler(new DataHandler(new FileDataSource("" + f.getAbsolutePath())));

            adjunto.setFileName("" + f.getName());
            System.out.println("Paquete " + adjunto.getFileName());
            //adjuntem el contingut del missatge
            MimeMultipart mimeMultipart = new MimeMultipart();
            mimeMultipart.addBodyPart(texto);
            mimeMultipart.addBodyPart(adjunto);

            //Correu de la persona a conectarse+
            System.out.println("Procedencia= " + userMail);
            message.setFrom(new InternetAddress(userMail));
            //Correu de la persona per a enviar el missatge
            System.out.println("destinatari=" + recipient);

            message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            //Asunto
            System.out.println("asumpte=" + Assumpte);
            message.setSubject(Assumpte);
            //Mensaje amb el format introduit
            message.setContent(mimeMultipart);
            System.out.println("");
            System.out.println("Missatge preparat");
            return message;
        } catch (IllegalWriteException iw) {
            System.out.println("Error IllegalWriteException=el correu no existeix");
        } catch (MessagingException ex) {
            System.out.println("Error" + ex.getLocalizedMessage());
        }
        System.out.println("missatge no preparat");
        return null;
    }

    //Envia el missatge 
    private void Transportar(Message message) {

        if (message != null) {
            try {
                System.out.println("Enviant correu");
                Transport.send(message);
                System.out.println("correu enviat de manera satisfactoria");
                setProcesEx("Archiu enviat correctament");
            } catch (MessagingException ex) {
                System.out.println("Error al enviar" + ex.getMessage());
                setProcesEx("Error al enviar l'archiu");
            }

        } else {
            System.out.println("Message null");
        }
    }
}
