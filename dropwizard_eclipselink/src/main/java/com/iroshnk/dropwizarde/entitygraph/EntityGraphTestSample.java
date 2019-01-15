package com.iroshnk.dropwizarde.entitygraph;

import com.iroshnk.dropwizarde.model.Order;
import com.iroshnk.dropwizarde.persistence.PersistenceManager;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import java.util.HashMap;
import java.util.Map;

public class EntityGraphTestSample {
    public static void main(String[] args) {
        Map database = new HashMap(4);
        database.put("driver", "com.mysql.jdbc.Driver");
        database.put("url", "jdbc:mysql://localhost:3306/dropwizarde");
        database.put("user", "root");
        database.put("password", "password");

        PersistenceManager.initialize(database);

        EntityManager em = PersistenceManager.getEntityManagerFactory().createEntityManager();

        EntityGraph graph = em.getEntityGraph("graph.Order.items");

        Map hints = new HashMap();
        hints.put("javax.persistence.fetchgraph", graph);

        Order order= em.find(Order.class, 1, hints);
    }
}
