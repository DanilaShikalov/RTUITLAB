package itlab.demo.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "employees")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long id_position;
    private String name;
    private String surname;
    private String second_name;
    private String number;
    @ManyToOne(targetEntity = PositionsEntity.class)
    @JoinColumn(name = "id_position", referencedColumnName = "id", insertable=false, updatable=false)
    private PositionsEntity position;
    @OneToMany(targetEntity = OrdersEntity.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_employees", referencedColumnName = "id")
    private List<OrdersEntity> orders;
}
