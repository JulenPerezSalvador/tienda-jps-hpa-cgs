package es.iesclaradelrey.da2d1a.common.entities;

import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 13, unique = true)
    private String codigoEan;

    @Column(nullable = false, length = 200)
    private String nombre;

    @Column(nullable = false, length = 4000)
    private String descripcion;

    @Column(length = 500)
    private String imagen;

    @Column(nullable = false)
    private Double precio;

    @Column(nullable = false)
    private Integer descuento;

    @ManyToOne(optional = false)//No puede estar vacio es decir not null
    @JoinColumn(name = "marca_id")
    private Marca marca;

    @ManyToMany
    @JoinTable(
            name = "productos_categorias",
            joinColumns = @JoinColumn(name = "producto_id"),
            inverseJoinColumns = @JoinColumn(name = "categoria_id")
    )
    @ToString.Exclude
    private List<Categoria> categorias = new ArrayList<>();
}