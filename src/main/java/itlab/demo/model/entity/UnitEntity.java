package itlab.demo.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "unit")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UnitEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "unit_id_generator")
    @SequenceGenerator(name = "unit_id_generator", sequenceName = "unit_id_seq", allocationSize = 1)
    private Long id;
    private String unit;
}
