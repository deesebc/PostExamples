package com.home.example.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.home.example.entity.Book;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
@Mapper
public interface BookMapper {
    void update(@MappingTarget Book entity, Book updateEntity);
}
