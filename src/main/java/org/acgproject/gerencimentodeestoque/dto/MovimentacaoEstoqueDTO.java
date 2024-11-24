package org.acgproject.gerencimentodeestoque.dto;

import org.acgproject.gerencimentodeestoque.model.enums.TipoMovimentacao;

import java.time.LocalDate;

public class MovimentacaoEstoqueDTO {

    private Integer id;
    private TipoMovimentacao tipoMovimentacao;
    private Integer quantidade;
    private LocalDate data;
    private ProdutoDTO produtoDTO;

    public MovimentacaoEstoqueDTO() {}

    public MovimentacaoEstoqueDTO(Integer id, TipoMovimentacao tipoMovimentacao, Integer quantidade, LocalDate data, ProdutoDTO produtoDTO) {
        this.id = id;
        this.tipoMovimentacao = tipoMovimentacao;
        this.quantidade = quantidade;
        this.data = data;
        this.produtoDTO = produtoDTO;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public TipoMovimentacao getTipoMovimentacao() {
        return tipoMovimentacao;
    }

    public void setTipoMovimentacao(TipoMovimentacao tipoMovimentacao) {
        this.tipoMovimentacao = tipoMovimentacao;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public ProdutoDTO getProdutoDTO() {
        return produtoDTO;
    }

    public void setProdutoDTO(ProdutoDTO produtoDTO) {
        this.produtoDTO = produtoDTO;
    }

    @Override
    public String toString() {
        return "MovimentacaoEstoqueDTO{" +
                "id=" + id +
                ", tipoMovimentacao=" + tipoMovimentacao +
                ", quantidade=" + quantidade +
                ", data=" + data +
                ", produtoDTO=" + produtoDTO.getNome() +
                '}';
    }
}
