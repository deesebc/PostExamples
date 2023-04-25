package com.home.example.pojo;

import java.io.Serializable;
import org.apache.camel.dataformat.bindy.annotation.CsvRecord;
import org.apache.camel.dataformat.bindy.annotation.DataField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@CsvRecord(separator = ",")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book implements Serializable {

  private static final long serialVersionUID = -3017204044751660248L;
  @DataField(pos = 1, trim = true)
  private Integer id;
  @DataField(pos = 2, trim = true)
  private String author;
  @DataField(pos = 3, trim = true)
  private String name;
}
