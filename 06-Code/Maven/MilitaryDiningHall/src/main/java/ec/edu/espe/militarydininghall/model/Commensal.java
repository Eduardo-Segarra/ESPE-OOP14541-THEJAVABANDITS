package ec.edu.espe.militarydininghall.model;

import ec.edu.espe.militarydininghall.controller.Dinner;


/**
 *
 * @author TheJavaBandits, DCCO-ESPE
 */
public class Commensal extends Dinner{

    public Commensal(String id, String name, String email, String password, String grade, String type, float balance) {
        super(id, name, email, password, grade, type, balance);
    }

}