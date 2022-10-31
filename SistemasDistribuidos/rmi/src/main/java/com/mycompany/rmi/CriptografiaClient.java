package com.mycompany.rmi;

import javax.swing.*;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.NotBoundException;

import java.net.MalformedURLException;

public class CriptografiaClient {

    public static void main(String[] args) throws RemoteException, MalformedURLException {

        String texto, resp = "";

        int opcao = JOptionPane.showConfirmDialog(null, "Sim = Criptografia" + "\nNão = Descriptografia",
                "Escolha uma opção", 0);

        try {
            CriptografiaInterface cripto = (CriptografiaInterface) Naming
                    .lookup("rmi://localhost:5555/" + "criptografiaServer");

            if (opcao == 0) {
                texto = JOptionPane.showInputDialog("Informe o TEXTO. Sem acento e caracteres especiais.");
                resp = cripto.criptografar(texto);
            } else {
                texto = JOptionPane.showInputDialog("Informe o TEXTO. Sem acento e caracteres especiais.");
                resp = cripto.descriptografar(texto);
            }
        } catch (RemoteException re) {
            System.out.println();
            System.out.println("RemoteException");
            System.out.println(re);
        } catch (NotBoundException nbe) {
            System.out.println();
            System.out.println("NotBoundException");
            System.out.println(nbe);
        } catch (java.lang.ArithmeticException ae) {
            System.out.println();
            System.out.println("java.lang.ArithmeticException");
            System.out.println(ae);
        } catch (java.lang.StringIndexOutOfBoundsException str) {
            System.out.println();
            System.out.println("java.lang.StringIndexOutOfBoundsException");
            System.out.println(str);
        } catch (java.lang.ArrayIndexOutOfBoundsException arr) {
            System.out.println();
            System.out.println("java.lang.ArrayIndexOutOfBoundsException");
            System.out.println(arr);
        }
        JTextArea outputArea = new JTextArea();
        outputArea.setText(resp);

        JOptionPane.showMessageDialog(null, outputArea, "Texto Criptografado", JOptionPane.INFORMATION_MESSAGE);
    }
}
