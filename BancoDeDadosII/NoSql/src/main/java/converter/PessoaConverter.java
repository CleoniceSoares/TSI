package converter;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Thiágo
 */
import com.mongodb.DBObject;
import entidade.Pessoa;

import java.util.HashMap;
import java.util.Map;

/**
 * https://www.mballem.com/
 */
public class PessoaConverter {

    public Map converterToMap(Pessoa pessoa) {
        Map mapPessoa = new HashMap();
        mapPessoa.put("nome", pessoa.getNome());
        mapPessoa.put("sobrenome", pessoa.getSobrenome());
        mapPessoa.put("idade", pessoa.getIdade());
        mapPessoa.put("telefone",
                new TelefoneConverter().converterToMap(pessoa.getTelefone())
        );

        return mapPessoa;
    }

    public Pessoa converterToPessoa(DBObject dbo) {
        Pessoa pessoa = new Pessoa();
        pessoa.setId(dbo.get("_id").toString());
        pessoa.setNome((String) dbo.get("nome"));
        pessoa.setSobrenome((String) dbo.get("sobrenome"));
        pessoa.setIdade((Integer) dbo.get("idade"));
        pessoa.setTelefone(new TelefoneConverter().converterToTelefone(
                (HashMap) dbo.get("telefone"))
        );

        return pessoa;
    }
}
