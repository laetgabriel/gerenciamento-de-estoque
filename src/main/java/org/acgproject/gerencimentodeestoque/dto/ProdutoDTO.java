package org.acgproject.gerencimentodeestoque.dto;

import org.acgproject.gerencimentodeestoque.model.entities.Categoria;
import org.acgproject.gerencimentodeestoque.model.entities.Fornecedor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

public class ProdutoDTO {

    private Integer id;
    private String nome;
    private BigDecimal preco;
    private Integer quantidade = 0;
    private LocalDate dataCadastro;
    private FornecedorDTO fornecedor;
    private CategoriaDTO categoria;

    public ProdutoDTO() {}

    public ProdutoDTO(Integer id, String nome, BigDecimal preco, Integer quantidade, LocalDate dataCadastro, FornecedorDTO fornecedor, CategoriaDTO categoria) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
        this.dataCadastro = dataCadastro;
        this.fornecedor = fornecedor;
        this.categoria = categoria;
    }

    public ProdutoDTO(String nome, BigDecimal preco, Integer quantidade, LocalDate dataCadastro, FornecedorDTO fornecedor, CategoriaDTO categoria) {
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
        this.dataCadastro = dataCadastro;
        this.fornecedor = fornecedor;
        this.categoria = categoria;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public LocalDate getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDate dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public FornecedorDTO getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(FornecedorDTO fornecedor) {
        this.fornecedor = fornecedor;
    }

    public CategoriaDTO getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaDTO categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return nome;
    }
}
