package org.acgproject.gerencimentodeestoque.mapper;

import org.acgproject.gerencimentodeestoque.dto.CategoriaDTO;
import org.acgproject.gerencimentodeestoque.model.entities.Categoria;
import org.modelmapper.ModelMapper;

public class CategoriaMapper {

    private static final ModelMapper modelMapper = new ModelMapper();

    public static CategoriaDTO toDTO(Categoria categoria) {
        if (categoria == null) {
            return null;
        }
        return modelMapper.map(categoria, CategoriaDTO.class);
    }

    public static Categoria toEntity(CategoriaDTO categoriaDTO) {
        if (categoriaDTO == null) {
            return null;
        }
        return modelMapper.map(categoriaDTO, Categoria.class);
    }
}
