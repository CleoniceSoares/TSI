package dao;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Thiágo
 */
import converter.PessoaConverter;
import entidade.Pessoa;
import com.mongodb.DBObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * https://www.mballem.com/
 */
public class PessoaDAO extends EntidadeDAO {

    public PessoaDAO() {
        super(Pessoa.class);
    }

    public void save(Pessoa pessoa) {
        Map mapPessoa =
                new PessoaConverter().converterToMap(pessoa);

        save(mapPessoa);
    }

    public void update(Pessoa oldPessoa, Pessoa newPessoa) {
        Map query =
                new PessoaConverter().converterToMap(oldPessoa);

        Map map =
                new PessoaConverter().converterToMap(newPessoa);

        update(query, map);
    }

    public void delete(Pessoa pessoa) {
        Map map =
                new PessoaConverter().converterToMap(pessoa);

        delete(map);
    }

    public Pessoa findPessoa(Map mapKeyValue) {
        DBObject dbObject = findOne(mapKeyValue);

        Pessoa pessoa =
                new PessoaConverter().converterToPessoa(dbObject);

        return pessoa;
    }

    public List findPessoas() {
        List dbObject = findAll();

        List pessoas = new ArrayList();

        for (Object dbo : dbObject) {
            Pessoa pessoa = new PessoaConverter().converterToPessoa((DBObject) dbo);

            pessoas.add(pessoa);
        }

        return pessoas;
    }

    public List findPessoas(Map mapKeyValue) {
        List dbObject = findKeyValue(mapKeyValue);

        List pessoas = new ArrayList();

        for (Object dbo : dbObject) {
            Pessoa pessoa = new PessoaConverter().converterToPessoa((DBObject) dbo);

            pessoas.add(pessoa);
        }

        return pessoas;
    }
}
