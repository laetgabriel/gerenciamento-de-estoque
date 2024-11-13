package org.acgproject.gerencimentodeestoque.db;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.acgproject.gerencimentodeestoque.model.entities.Produto;
import org.acgproject.gerencimentodeestoque.model.entities.Fornecedor;
import org.acgproject.gerencimentodeestoque.model.entities.Categoria;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.sql.Date;

public class DB {


    public static void main(String[] args) {
        final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("gerenciamentoestoque");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Categoria categoria = new Categoria(null, "A", "B");

        try {
            entityManager.getTransaction().begin();
            entityManager.persist(categoria);
            entityManager.getTransaction().commit();

            System.out.println("Categoria persistidos com sucesso.");
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}
