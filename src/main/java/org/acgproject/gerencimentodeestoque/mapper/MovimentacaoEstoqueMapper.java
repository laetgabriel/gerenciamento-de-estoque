package org.acgproject.gerencimentodeestoque.mapper;

import org.acgproject.gerencimentodeestoque.dto.MovimentacaoEstoqueDTO;
import org.acgproject.gerencimentodeestoque.dto.ProdutoDTO;
import org.acgproject.gerencimentodeestoque.model.entities.MovimentacaoEstoque;
import org.acgproject.gerencimentodeestoque.model.entities.Produto;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class MovimentacaoEstoqueMapper {

    private static final ModelMapper modelMapper = new ModelMapper();

    public static MovimentacaoEstoqueDTO toDTO(MovimentacaoEstoque movimentacaoEstoque) {
        if (movimentacaoEstoque == null) {
            return null;
        }

        MovimentacaoEstoqueDTO movimentacaoEstoqueDTO = modelMapper.map(movimentacaoEstoque, MovimentacaoEstoqueDTO.class);

        Produto produto = movimentacaoEstoque.getProduto();
        if (produto != null) {
            movimentacaoEstoqueDTO.setProdutoDTO(ProdutoMapper.toDTO(produto));
        }

        return movimentacaoEstoqueDTO;
    }

    public static MovimentacaoEstoque toEntity(MovimentacaoEstoqueDTO movimentacaoEstoqueDTO) {
        if (movimentacaoEstoqueDTO == null) {
            return null;
        }

        MovimentacaoEstoque movimentacaoEstoque = modelMapper.map(movimentacaoEstoqueDTO, MovimentacaoEstoque.class);

        ProdutoDTO produtoDTO = movimentacaoEstoqueDTO.getProdutoDTO();
        if (produtoDTO != null && produtoDTO.getId() != null) {
            Produto produto = new Produto();
            produto.setId(produtoDTO.getId());
            movimentacaoEstoque.setProduto(produto);
        }

        return movimentacaoEstoque;
    }

    public static List<MovimentacaoEstoqueDTO> toDTOList(List<MovimentacaoEstoque> movimentacoesEstoque) {
        if (movimentacoesEstoque == null) {
            return null;
        }
        return movimentacoesEstoque.stream().map(MovimentacaoEstoqueMapper::toDTO).collect(Collectors.toList());
    }

    public static List<MovimentacaoEstoque> toEntityList(List<MovimentacaoEstoqueDTO> movimentacaoEstoqueDTOS) {
        if (movimentacaoEstoqueDTOS == null) {
            return null;
        }
        return movimentacaoEstoqueDTOS.stream().map(MovimentacaoEstoqueMapper::toEntity).collect(Collectors.toList());
    }
}
