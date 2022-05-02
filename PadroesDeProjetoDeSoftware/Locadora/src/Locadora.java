/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author cleon
 */
/**REFATORAR O CÓDIGO APLICANDO OS PADRÕES DE
RESPONSABILIDADE EXPERT, CREATOR, BAIXO ACOPLAMENTO E ALTA
COESÃO*/
public class Locadora {

    public static void main(String[] args) {
        
        Cliente c1 = new Cliente("Juliana");
        Cliente c2 = new Cliente("André");
        
        Fita f1 = new Fita("O Exorcista ", 0);
        Fita f2 = new Fita("Men in Black ", 0);
        Fita f3 = new Fita("Jurassic Park III ", 1);
        Fita f4 = new Fita("Planeta dos Macacos ", 1);
        Fita f5 = new Fita("Pateta no Planeta dos Macacos ", 2);
        
        Aluguel a1 = new Aluguel(c1, 10);
        Aluguel a2 = new Aluguel(c2, 5);
        
        a1.alugarFita(f1);
        a1.alugarFita(f2);
        a1.alugarFita(f3);
        
        a2.alugarFita(f4);
        a2.alugarFita(f5);
        
        System.out.println(al.extrato(c1));
        System.out.println(a2.extrato(c2));
    }
}
