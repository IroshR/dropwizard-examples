package com.iroshnk.dropwizardmongo.core;

import com.iroshnk.dropwizardmongo.persistence.MongoPersistenceManager;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HP on 9/17/2017.
 */
public abstract class Search {
    protected abstract String getBaseCollectionName();

    public Document searchByPK(long pk) {
        MongoCollection<Document> collection = MongoPersistenceManager.getCollection(getBaseCollectionName());
        Document query = new Document("_id", pk);

        FindIterable<Document> docs = collection.find(query);

        return docs.first();
    }

    public Object search(String query, int limit, int skip) {
        MongoCollection<Document> collection = MongoPersistenceManager.getCollection(getBaseCollectionName());

        Document queryDoc = Document.parse(query);

        List<Document> results = new ArrayList<Document>();
        MongoCursor<Document> cursor = null;
        try {
            cursor = collection.find(queryDoc).limit(limit).skip(skip).iterator();

            while (cursor.hasNext()) {
                results.add(cursor.next());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cursor.close();
        }

        return results;
    }

    public Object search(String query, String projection, int limit, int skip) {
        MongoCollection<Document> collection = MongoPersistenceManager.getCollection(getBaseCollectionName());

        Document queryDoc = Document.parse(query);
        Document projectionDoc = Document.parse(projection);

        List<Document> results = new ArrayList<Document>();
        MongoCursor<Document> cursor = null;
        try {
            cursor = collection.find(queryDoc).projection(projectionDoc).limit(limit).skip(skip).iterator();

            while (cursor.hasNext()) {
                results.add(cursor.next());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cursor.close();
        }

        return results;
    }

    public Object search(String query, String projection, String orderBy, int limit, int skip) {
        MongoCollection<Document> collection = MongoPersistenceManager.getCollection(getBaseCollectionName());

        Document queryDoc = Document.parse(query);

        Document orderByDoc = null, projectionDoc = null;
        if(orderBy != null && orderBy.length() > 0) {
            orderByDoc = Document.parse(orderBy);
        }
        if(projection != null && projection.length() > 0) {
            projectionDoc = Document.parse(projection);
        }

        List<Document> results = new ArrayList<Document>();
        MongoCursor<Document> cursor = null;
        try {
            if(orderByDoc != null && projectionDoc != null)
                cursor = collection.find(queryDoc).projection(projectionDoc).sort(orderByDoc).limit(limit).skip(skip).iterator();
            else if (orderByDoc != null)
                cursor = collection.find(queryDoc).sort(orderByDoc).limit(limit).skip(skip).iterator();
            else if(projectionDoc != null)
                cursor = collection.find(queryDoc).projection(projectionDoc).limit(limit).skip(skip).iterator();
            else
                cursor = collection.find(queryDoc).limit(limit).skip(skip).iterator();
            while (cursor.hasNext()) {
                results.add(cursor.next());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cursor.close();
        }

        return results;
    }
}
