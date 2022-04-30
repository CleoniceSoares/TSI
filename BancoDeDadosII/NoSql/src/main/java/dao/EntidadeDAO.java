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
import dao.MongoConnection;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import dao.InterfaceDAO;

/**
 * https://www.mballem.com/
 */
public class EntidadeDAO implements InterfaceDAO {

    private Class persistentClass;
    private DBCollection dbCollection;

    public EntidadeDAO(Class persistentClass) {
        this.persistentClass = persistentClass;
        this.dbCollection =
                MongoConnection.getInstance()
                    .getDB().getCollection(persistentClass.getSimpleName());
    }

    protected DBCollection getDbCollection() {
        return dbCollection;
    }

    /**
     *
     * @param mapEntidade
     */
    @Override
    public void save(Map mapEntidade) {
        BasicDBObject document = new BasicDBObject(mapEntidade);

        dbCollection.save(document);

        System.out.println("Save :> " + document);
    }

    @Override
    public void update(Map mapQuery,
                       Map mapEntidade) {
        BasicDBObject query = new BasicDBObject(mapQuery);

        BasicDBObject entidade = new BasicDBObject(mapEntidade);

        dbCollection.update(query, entidade);
    }

    @Override
    public void delete(Map mapEntidade) {
        BasicDBObject entidade = new BasicDBObject(mapEntidade);

        dbCollection.remove(entidade);
    }

    @Override
    public DBObject findOne(Map mapEntidade) {
        BasicDBObject query = new BasicDBObject(mapEntidade);

        return dbCollection.findOne(query);
    }

    @Override
    public List findAll() {
        List list = new ArrayList();

        DBCursor cursor = dbCollection.find();

        while (cursor.hasNext()) {
            list.add(cursor.next());
        }

        return list;
    }

    @Override
    public List findKeyValue(Map keyValue) {
        List list = new ArrayList();

        DBCursor cursor = dbCollection.find(new BasicDBObject(keyValue));

        while (cursor.hasNext()) {
            list.add(cursor.next());
        }

        return list;
    }
}
    