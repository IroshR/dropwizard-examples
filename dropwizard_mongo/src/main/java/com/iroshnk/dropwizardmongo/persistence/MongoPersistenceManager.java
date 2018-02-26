package com.iroshnk.dropwizardmongo.persistence;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.Map;

/**
 * Created by HP on 9/16/2017.
 */
public class MongoPersistenceManager {
    private static MongoClient mongoClient;
    private static MongoDatabase database;
    private static MongoCollection<Document> counters;

    private MongoPersistenceManager() {
    }

    public static MongoDatabase getDatabase() {
        return database;
    }

    public static MongoCollection<Document> getCollection(String collectionName) {
        return database.getCollection(collectionName);
    }

    public static void initialize(Map mongoDatabase) {
        MongoClientURI uri = new MongoClientURI((String) mongoDatabase.get("url"));
        mongoClient = new MongoClient(uri);
        database = mongoClient.getDatabase(uri.getDatabase());
        counters = database.getCollection(CollectionNames.COUNTERS);
        ensureIndexes();
    }

    public static Object getNextSequence(String name) {

        Document searchQuery = new Document("_id", name);
        Document increase = new Document("seq", 1);
        Document updateQuery = new Document("$inc", increase);
        Document result = counters.findOneAndUpdate(searchQuery, updateQuery);
        if(result == null) {
            Document document = new Document("_id", name);
            document.put("seq", 2);
            counters.insertOne(document);
            return 1;
        }

        return result.get("seq");
    }

    private static void ensureIndexes() {
        //database.getCollection(CollectionNames.USERS).createIndex(new Document("collection_name", 1));
    }
}
