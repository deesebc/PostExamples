package com.home.example.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.home.example.entity.BoardGame;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
//warning: we need componentModel.CDI to use @Inject
@Mapper(componentModel = "CDI")
public interface BoardGameMapper {
    void update(@MappingTarget BoardGame entity, BoardGame updateEntity);

    @Mapping(source = "newBoardGame", target = ".")
    BoardGame copy(BoardGame newBoardGame);
}
