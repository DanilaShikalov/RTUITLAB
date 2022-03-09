package itlab.demo.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "positions")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PositionsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "positions_id_generator")
    @SequenceGenerator(name = "positions_id_generator", sequenceName = "positions_id_seq", allocationSize = 1)
    private Long id;
    private String position;
    private int salary;
    @OneToMany(targetEntity = EmployeesEntity.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_position", referencedColumnName = "id")
    private List<EmployeesEntity> employees;
}
