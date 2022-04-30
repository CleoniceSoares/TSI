package entidade;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Thiágo
 */
public class Telefone {
    private String telefone;
    private String celular;

    public Telefone() {
        super();
    }

    public Telefone(String telefone, String celular) {
        this.telefone = telefone;
        this.celular = celular;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    @Override
    public String toString() {
        return "Telefone{" +
                "telefone='" + telefone + '\'' +
                ", celular='" + celular + '\'' +
                '}';
    }
}
