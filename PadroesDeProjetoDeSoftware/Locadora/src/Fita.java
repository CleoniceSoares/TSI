/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author cleon
 */
public class Fita {

    public static final int NORMAL = 0;
    public static final int LANÇAMENTO = 1;
    public static final int INFANTIL = 2;
    private String título;
    private int códigoDePreço;

    public Fita(String título, int códigoDePreço) {
        this.título = título;
        this.códigoDePreço = códigoDePreço;
    }

    public String getTítulo() {
        return título;
    }

    public int getCódigoDePreço() {
        return códigoDePreço;
    }

    public void setCódigoDePreço(int códigoDePreço) {
        this.códigoDePreço = códigoDePreço;
    }
}
