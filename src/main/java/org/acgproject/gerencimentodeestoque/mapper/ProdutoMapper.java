package org.acgproject.gerencimentodeestoque.mapper;

import org.acgproject.gerencimentodeestoque.dto.ProdutoDTO;
import org.acgproject.gerencimentodeestoque.model.entities.Produto;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class ProdutoMapper {

    private static final ModelMapper modelMapper = new ModelMapper();

    public static ProdutoDTO toDTO(Produto produto) {
        if (produto == null) {
            return null;
        }
        return modelMapper.map(produto, ProdutoDTO.class);
    }

    public static Produto toEntity(ProdutoDTO produtoDTO) {
        if (produtoDTO == null) {
            return null;
        }
        return modelMapper.map(produtoDTO, Produto.class);
    }

    public static List<ProdutoDTO> toDTOList(List<Produto> produtos) {
        if (produtos == null) {
            return null;
        }
        return produtos.stream().map(produto -> toDTO(produto)).collect(Collectors.toList());
    }

    public static List<Produto> toEntityList(List<ProdutoDTO> produtosDTO) {
        if (produtosDTO == null) {
            return null;
        }
        return produtosDTO.stream().map(ProdutoMapper::toEntity).collect(Collectors.toList());
    }
}
