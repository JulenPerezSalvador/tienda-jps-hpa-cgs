package es.iesclaradelrey.da2d1a.common.entities;

import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 100)
    private String nombre;

    @Column(length = 2000)
    private String descripcion;

    @Column(length = 500)
    private String imagen;

    @ToString.Exclude
    @ManyToMany(mappedBy = "categorias")
    private List<Producto> productos = new ArrayList<>();
}