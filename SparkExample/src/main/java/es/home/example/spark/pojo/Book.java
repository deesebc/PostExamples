/*
 */
package es.home.example.spark.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
public class Book {
    @Getter
    @Setter
    private Integer id;
    @Getter
    @Setter
    private String author;
    @Getter
    @Setter
    private String name;

}
