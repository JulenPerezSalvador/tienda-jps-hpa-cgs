package es.iesclaradelrey.da2d1a.common.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Rol {

    @Id
    @Column(length = 6)
    private String id;

    @Column(length = 100, nullable = false)
    private String descripcion;
}
