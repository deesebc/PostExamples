package es.home.example.knowledge.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
public class Saludo {
    private @Getter @Setter String saludo;
    private @Setter @Getter String nombre;
}
