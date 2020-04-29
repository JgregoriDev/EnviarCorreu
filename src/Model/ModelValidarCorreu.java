/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Controlador.ControladorValidarCorreu;

/**
 *
 * @author w10
 */
public class ModelValidarCorreu {

    public boolean validarCorreu(String correu) {
        ControladorValidarCorreu cvc = new ControladorValidarCorreu();
        return cvc.AdreçaValida(correu);
    }
}
