package itlab.demo.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "menu")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "menu_id_generator")
    @SequenceGenerator(name = "menu_id_generator", sequenceName = "menu_id_seq", allocationSize = 1)
    private Long id;
    private Long id_unit;
    private String title;
    private int price;
    private int weight;
    @OneToMany(targetEntity = IngredientEntity.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_menu", referencedColumnName = "id")
    private List<IngredientEntity> ingredient;
    @ManyToOne(targetEntity = UnitEntity.class)
    @JoinColumn(name = "id_unit", referencedColumnName = "id", insertable=false, updatable=false)
    private UnitEntity unit;
    @OneToMany(targetEntity = DishEntity.class)
    @JoinColumn(name = "id_menu", referencedColumnName = "id", insertable=false, updatable=false)
    private List<DishEntity> dishes;
}
