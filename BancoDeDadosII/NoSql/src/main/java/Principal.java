/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Thiágo
 */

import dao.PessoaDAO;
import entidade.Pessoa;
import entidade.Telefone;

import java.util.*;
import org.bson.types.ObjectId;

public class Principal {

    public static void main(String[] args) {
        save();
        //findPessoas();
        //update();
        //delete();
    }

    private static void save() {
        Telefone telefone1 = new Telefone("985641321", "984625130");
        Pessoa pessoa1 = new Pessoa("Maria", "de Morais", 20, telefone1);
        new PessoaDAO().save(pessoa1);

        Telefone telefone2 = new Telefone("984654321", "954321087");
        Pessoa pessoa2 = new Pessoa("Pedro", "da Souza", 23, telefone2);
        new PessoaDAO().save(pessoa2);

        Telefone telefone3 = new Telefone("9662015747", "987403654");
        Pessoa pessoa3 = new Pessoa("Lux", "Demacia", 17, telefone3);
        new PessoaDAO().save(pessoa3);
    }
    
    private static void findPessoas() {
        List pessoas = new PessoaDAO().findPessoas();
        for (Object pessoa : pessoas) {
            System.out.println(pessoa.toString());
        }
    }

    private static void update() {
        Map map = new HashMap();
        map.put("nome", "Lux");
        Pessoa query = new PessoaDAO().findPessoa(map);

        Telefone telefone = new Telefone("985641321", "984625130");
        Pessoa pessoa = new Pessoa("Lux", "de Morais", 25, telefone);
        new PessoaDAO().update(query, pessoa);

        Pessoa newPessoa = new PessoaDAO().findPessoa(map);
        System.out.printf("Old:> " + query + "\nNew:> " + newPessoa.toString());
    }
    
    private static void delete() {
        Map map = new HashMap();
        map.put("nome", "Maria");
        List query = new PessoaDAO().findPessoas(map);
        new PessoaDAO().delete(map);
    } 

}
