package org.acgproject.gerencimentodeestoque.model.entities;

import jakarta.persistence.*;
import org.acgproject.gerencimentodeestoque.model.enums.TipoMovimentacao;

import java.time.LocalDate;

@Entity
public class MovimentacaoEstoque {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoMovimentacao tipoMovimentacao;

    @Column(nullable = false)
    private Integer quantidade;

    @Column(nullable = false)
    private LocalDate data;

    @JoinColumn(name = "id_produto", nullable = false)
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
}
