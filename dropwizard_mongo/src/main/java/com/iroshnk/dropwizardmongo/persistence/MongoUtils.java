package com.iroshnk.dropwizardmongo.persistence;

import org.bson.Document;

import java.util.Date;

/**
 * Created by HP on 9/16/2017.
 */
public class MongoUtils {
    public static Object getField(Document document, String path) {
        String[] paths = path.split("/");
        int count = 1;
        for (String key : paths) {
            if (paths.length == count) {
                return document.get(key);
            } else {
                document = document.get(key, Document.class);
                count++;
                if (document == null) {
                    return null;
                }
            }
        }
        return null;
    }


    public static Document addField(Document document, Object value, String path) {
        String[] paths = path.split("/");
        int count = 1;
        Document temp = document;
        for (String key : paths) {
            if (paths.length == count) {
                temp.append(key, value);

                return document;
            }
            Document sub = (Document) temp.get(key);
            if (sub == null) {
                sub = new Document();
                temp.append(key, sub);
                temp = sub;
            } else {
                temp = sub;
            }

        }
        return document;
    }

    public static Document fillCreateData(Document document) {
        document.append("created_date", new Date());
        document.append("status", 1);

        return document;

    }

    public static Document fillUpdateData(Document document) {
        document.append("updated_date", new Date());

        return document;

    }
}
