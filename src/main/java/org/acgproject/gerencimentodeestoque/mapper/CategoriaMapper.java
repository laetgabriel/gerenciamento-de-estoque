package org.acgproject.gerencimentodeestoque.mapper;

import org.acgproject.gerencimentodeestoque.dto.CategoriaDTO;
import org.acgproject.gerencimentodeestoque.model.entities.Categoria;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    public static List<CategoriaDTO> toDTOList(List<Categoria> categorias) {
        if (categorias == null) {
            return null;
        }
        return categorias.stream().map(categoria -> toDTO(categoria)).collect(Collectors.toList());
    }

    public static List<Categoria> toEntityList(List<CategoriaDTO> categoriasDTO) {
        if (categoriasDTO == null) {
            return null;
        }
        return categoriasDTO.stream().map(CategoriaMapper::toEntity).collect(Collectors.toList());
    }
}
