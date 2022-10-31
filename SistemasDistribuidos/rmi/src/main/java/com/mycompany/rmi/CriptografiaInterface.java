package com.mycompany.rmi;

public interface CriptografiaInterface extends java.rmi.Remote {

    public String criptografar(String texto) throws java.rmi.RemoteException;

    public String descriptografar(String texto) throws java.rmi.RemoteException;
}
