package org.acgproject.gerencimentodeestoque.model.entities;

import jakarta.persistence.*;
import org.acgproject.gerencimentodeestoque.model.enums.TipoMovimentacao;

import java.util.Date;

@Entity
@Table(name = "movimentacaoestoque")
public class MovimentacaoEstoque {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoMovimentacao tipoMovimentacao;

    @Column(nullable = false)
    private int quantidade;

    @Column(nullable = false)
    private Date data;

    @JoinColumn(name = "fk_Produto_Id", nullable = false)
    @ManyToOne
    private Produto produto;

    public MovimentacaoEstoque(){}

    @PrePersist
    @PreUpdate
    public void validarQuantidade() {
        if (quantidade <= 0) {
            throw new IllegalArgumentException("A quantidade deve ser maior que 0.");
        }
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public TipoMovimentacao getTipoMovimentacao() {
        return tipoMovimentacao;
    }

    public void setTipoMovimentacao(TipoMovimentacao tipoMovimentacao) {
        this.tipoMovimentacao = tipoMovimentacao;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
}
