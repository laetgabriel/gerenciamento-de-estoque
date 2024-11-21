package org.acgproject.gerencimentodeestoque.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.TypedQuery;
import org.acgproject.gerencimentodeestoque.dao.FornecedorDAO;
import org.acgproject.gerencimentodeestoque.db.DB;
import org.acgproject.gerencimentodeestoque.dto.FornecedorDTO;
import org.acgproject.gerencimentodeestoque.mapper.FornecedorMapper;
import org.acgproject.gerencimentodeestoque.model.entities.Fornecedor;

import java.util.List;

public class FornecedorDAOImpl implements FornecedorDAO {

    @Override
    public void inserirFornecedor(FornecedorDTO fornecedorDTO) throws PersistenceException {
        try(EntityManager em = DB.getConexao()){
            Fornecedor fornecedor = FornecedorMapper.toEntity(fornecedorDTO);
            em.getTransaction().begin();
            em.persist(fornecedor);
            em.getTransaction().commit();
        } catch (PersistenceException e){
            throw new PersistenceException(e);
        }
    }

    @Override
    public void alterarFornecedor(FornecedorDTO fornecedorDTO) throws PersistenceException {
        try(EntityManager em = DB.getConexao()){
            Fornecedor fornecedor = FornecedorMapper.toEntity(fornecedorDTO);
            em.getTransaction().begin();
            em.merge(fornecedor);
            em.getTransaction().commit();
        } catch (PersistenceException e){
            throw new PersistenceException(e);
        }
    }

    @Override
    public void excluirFornecedor(Integer id) throws PersistenceException {
        try(EntityManager em = DB.getConexao()){
            Fornecedor fornecedor = em.find(Fornecedor.class, id);
            em.getTransaction().begin();
            em.remove(fornecedor);
            em.getTransaction().commit();
        }catch (PersistenceException e){
            throw new PersistenceException(e);
        }
    }

    @Override
    public FornecedorDTO consultarFornecedor(Integer id) {
        try(EntityManager em = DB.getConexao()){
            Fornecedor fornecedor = em.find(Fornecedor.class, id);
            return FornecedorMapper.toDTO(fornecedor);
        }
    }

    @Override
    public FornecedorDTO consultarFornecedorPorNome(String nome) {
        try (EntityManager entityManager = DB.getConexao()) {
            TypedQuery<Fornecedor> query = entityManager.createQuery("from Fornecedor where nome = :nome", Fornecedor.class);
            query.setParameter("nome", nome);

            return FornecedorMapper.toDTO(query.getResultList().getFirst());
        }
    }

    @Override
    public List<FornecedorDTO> listarTodosOsFornecedores() {
        try(EntityManager em = DB.getConexao()){
            List<Fornecedor> fornecedores = em.createQuery("from Fornecedor", Fornecedor.class).getResultList();
            return FornecedorMapper.toDTOList(fornecedores);
        }
    }
}
