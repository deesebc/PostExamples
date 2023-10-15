package com.home.example.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.home.example.entity.Book;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
//warning: we need componentModel.CDI to use @Inject
@Mapper(componentModel = "CDI")
public interface BookMapper {
    void update(@MappingTarget Book entity, Book updateEntity);

    @Mapping(source = "newBook", target = ".")
    Book copy(Book newBook);
}
