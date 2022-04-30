/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

/**
 *
 * @author Thiágo
 */
import entidade.Telefone;

import java.util.HashMap;
import java.util.Map;

/**
 * https://www.mballem.com/
 */
public class TelefoneConverter {

    public Map converterToMap(Telefone telefone) {
        Map mapTelefone = new HashMap();
        mapTelefone.put("telefone", telefone.getTelefone());
        mapTelefone.put("celular", telefone.getCelular());

        return mapTelefone;
    }

    public Telefone converterToTelefone(HashMap hashMap) {
        Telefone telefone = new Telefone();
        telefone.setTelefone((String) hashMap.get("telefone"));
        telefone.setCelular((String) hashMap.get("celular"));

        return telefone;
    }
}
