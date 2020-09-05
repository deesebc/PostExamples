package com.example.home.ApacheCamelRestExample.pojo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity(name = "BOOK")
@Table(name = "BOOK")
@NamedQuery(name = "findBookById", query = "SELECT b FROM BOOK b WHERE b.id = :id ")
public class Book {

    @JsonProperty
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @JsonProperty
    private String name;
    @JsonProperty
    private String author;
}
