package com.mycompany.rmi;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class CriptografiaServer {

    public CriptografiaServer() {
        try {

            Registry registry = LocateRegistry.createRegistry(5555);

            Naming.rebind("rmi://localhost:5555/criptografiaServer", new Criptografia());

        } catch (Exception e) {
            System.out.println("Erro: " + e);
        }
    }

    public static void main(String[] args) {
        new CriptografiaServer();
    }
}
