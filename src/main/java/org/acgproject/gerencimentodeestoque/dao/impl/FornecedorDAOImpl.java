package org.acgproject.gerencimentodeestoque.dao.impl;

import jakarta.persistence.EntityManager;
import org.acgproject.gerencimentodeestoque.dao.FornecedorDAO;
import org.acgproject.gerencimentodeestoque.db.DB;
import org.acgproject.gerencimentodeestoque.dto.FornecedorDTO;
import org.acgproject.gerencimentodeestoque.mapper.FornecedorMapper;
import org.acgproject.gerencimentodeestoque.model.entities.Fornecedor;

import java.util.List;

public class FornecedorDAOImpl implements FornecedorDAO {

    @Override
    public void inserirFornecedor(FornecedorDTO fornecedorDTO) {
        try(EntityManager em = DB.getConexao()){
            Fornecedor fornecedor = FornecedorMapper.toEntity(fornecedorDTO);
            em.getTransaction().begin();
            em.persist(fornecedor);
            em.getTransaction().commit();
        }
    }

    @Override
    public void alterarFornecedor(FornecedorDTO fornecedorDTO) {
        try(EntityManager em = DB.getConexao()){
            Fornecedor fornecedor = FornecedorMapper.toEntity(fornecedorDTO);
            em.getTransaction().begin();
            em.merge(fornecedor);
            em.getTransaction().commit();
        }
    }

    @Override
    public void excluirFornecedor(Integer id) {
        try(EntityManager em = DB.getConexao()){
            Fornecedor fornecedor = em.find(Fornecedor.class, id);
            em.getTransaction().begin();
            em.remove(fornecedor);
            em.getTransaction().commit();
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
    public List<FornecedorDTO> listarFornecedor() {
        try(EntityManager em = DB.getConexao()){
            List<Fornecedor> fornecedores = em.createQuery("from Fornecedor").getResultList();
            return FornecedorMapper.toDTOList(fornecedores);
        }
    }
}
