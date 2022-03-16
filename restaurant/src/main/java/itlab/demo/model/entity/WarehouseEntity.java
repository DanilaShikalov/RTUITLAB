package itlab.demo.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "warehouse")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WarehouseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long id_unit;
    private Long id_product;
    private String title;
    private int weight;
    private int amount;
    private int price;
    @OneToOne(targetEntity = UnitEntity.class)
    @JoinColumn(name = "id_unit", referencedColumnName = "id", insertable=false, updatable=false)
    private UnitEntity unit;
    @OneToMany(targetEntity = IngredientEntity.class)
    @JoinColumn(name = "id_warehouse", referencedColumnName = "id", insertable=false, updatable=false)
    private List<IngredientEntity> ingredients;
}
