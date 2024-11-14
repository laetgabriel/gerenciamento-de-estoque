package org.acgproject.gerencimentodeestoque.db;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class DB {
    private static EntityManagerFactory emf = null;

    private DB() {}

    public static EntityManager getConexao(){
        if (emf == null){
            emf = Persistence.createEntityManagerFactory("gerenciamentoestoque");
        }
        return emf.createEntityManager();
    }

}

