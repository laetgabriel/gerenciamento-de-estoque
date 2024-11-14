package org.acgproject.gerencimentodeestoque.mapper;

import org.acgproject.gerencimentodeestoque.dto.FornecedorDTO;
import org.acgproject.gerencimentodeestoque.dto.ProdutoDTO;
import org.acgproject.gerencimentodeestoque.model.entities.Fornecedor;
import org.acgproject.gerencimentodeestoque.model.entities.Produto;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class FornecedorMapper {

    private static final ModelMapper modelMapper = new ModelMapper();

    public static FornecedorDTO toDTO(Fornecedor fornecedor) {
        if (fornecedor == null) {
            return null;
        }
        return modelMapper.map(fornecedor, FornecedorDTO.class);
    }

    public static Fornecedor toEntity(FornecedorDTO fornecedorDTO) {
        if (fornecedorDTO == null) {
            return null;
        }
        return modelMapper.map(fornecedorDTO, Fornecedor.class);
    }

    public static List<FornecedorDTO> toDTOList(List<Fornecedor> fornecedores) {
        if (fornecedores == null) {
            return null;
        }
        return fornecedores.stream().map(fornecedor -> toDTO(fornecedor)).collect(Collectors.toList());
    }

    public static List<Fornecedor> toEntityList(List<FornecedorDTO> fornecedoresDTO) {
        if (fornecedoresDTO == null) {
            return null;
        }
        return fornecedoresDTO.stream().map(FornecedorMapper::toEntity).collect(Collectors.toList());
    }
}