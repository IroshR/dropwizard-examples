package com.iroshnk.dropwizardmongo.core;

import com.iroshnk.dropwizardmongo.api.request.StatusUpdateAPI;
import com.iroshnk.dropwizardmongo.api.response.CreationResponseAPI;
import com.iroshnk.dropwizardmongo.persistence.MongoPersistenceManager;
import com.iroshnk.dropwizardmongo.persistence.MongoUtils;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;

/**
 * Created by HP on 9/17/2017.
 */
public abstract class Management {
    protected String baseCollection;
    protected String historyCollection;

    protected Management(String baseCollection, String historyCollection) {
        this.baseCollection = baseCollection;
        this.historyCollection = historyCollection;
    }

    public CreationResponseAPI create(String api) {
        Document document = Document.parse(api);


        document.append("_id", MongoPersistenceManager.getNextSequence(baseCollection));
        MongoUtils.fillCreateData(document);

        MongoCollection collection = MongoPersistenceManager.getCollection(baseCollection);

        collection.insertOne(document);

        CreationResponseAPI response = new CreationResponseAPI();
        response.setSuccess(true);
        response.setId(document.getInteger("_id"));
        response.setMessage(document.toJson());

        return response;

    }

    public CreationResponseAPI update(String api) {
        Document document = Document.parse(api);

        MongoUtils.fillUpdateData(document);

        MongoCollection<Document> collection = MongoPersistenceManager.getCollection(baseCollection);

        Document query = new Document("_id", document.get("_id"));

        Document old = collection.findOneAndReplace(query, document);
        old.append("reference", old.get("_id"));
        old.append("_id", MongoPersistenceManager.getNextSequence(historyCollection));

        collection = MongoPersistenceManager.getCollection(historyCollection);

        collection.insertOne(old);

        CreationResponseAPI response = new CreationResponseAPI();
        response.setSuccess(true);
        response.setId(document.getInteger("_id"));
        response.setMessage(document.toJson());

        return response;
    }

    public CreationResponseAPI updateStatus(StatusUpdateAPI api) {
        MongoCollection<Document> collection = MongoPersistenceManager.getCollection(baseCollection);

        Document query = new Document("_id", api.primary_id);
        Document data = new Document("status", api.status);
        Document update = new Document("$set", data);

        UpdateResult updateResult = collection.updateOne(query, update);

        CreationResponseAPI response = new CreationResponseAPI();
        response.setSuccess(updateResult.getModifiedCount() > 0);

        response.setId(api.primary_id);

        return response;

    }
}
