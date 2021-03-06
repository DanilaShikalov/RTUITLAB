package itlab.demo.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "dish")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DishEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long id_orders;
    private Long id_menu;
    @ManyToOne(targetEntity = MenuEntity.class)
    @JoinColumn(name = "id_menu", referencedColumnName = "id", insertable=false, updatable=false)
    private MenuEntity menu;
    @ManyToOne(targetEntity = OrdersEntity.class)
    @JoinColumn(name = "id_orders", referencedColumnName = "id", insertable=false, updatable=false)
    private OrdersEntity orders;
}
