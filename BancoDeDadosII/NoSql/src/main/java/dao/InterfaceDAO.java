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
import com.mongodb.DBObject;

import java.util.List;
import java.util.Map;

/**
 * https://www.mballem.com/
 */
public interface InterfaceDAO {
    void save(Map mapEntity);

    void update(Map mapQuery, Map mapEntity);

    void delete(Map mapEntity);

    DBObject findOne(Map mapEntity);

    List findAll();

    List findKeyValue(Map keyValue);
}
