package itlab.demo.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "ingredient")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class IngredientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ingredient_id_generator")
    @SequenceGenerator(name = "ingredient_id_generator", sequenceName = "ingredient_id_seq", allocationSize = 1)
    private Long id;
    private Long id_menu;
    private Long id_warehouse;
    @ManyToOne(targetEntity = WarehouseEntity.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_warehouse", referencedColumnName = "id", insertable=false, updatable=false)
    private WarehouseEntity warehouse;
    @ManyToOne(targetEntity = MenuEntity.class)
    @JoinColumn(name = "id_menu", referencedColumnName = "id", insertable=false, updatable=false)
    private MenuEntity menu;
}
