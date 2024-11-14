package org.acgproject.gerencimentodeestoque.conexao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Conexao {
    private static EntityManagerFactory emf = null;

    private Conexao() {}

    public static EntityManager getConexao(){
        if (emf == null){
            emf = Persistence.createEntityManagerFactory("gerenciamentoestoque");
        }
        return emf.createEntityManager();
    }
}

