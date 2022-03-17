package itlab.demo.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrdersEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long id_employees;
    private Date date_open;
    private boolean status;
    private Date date_close;
    @OneToMany(targetEntity = DishEntity.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_orders", referencedColumnName = "id")
    private List<DishEntity> dishes;
}
