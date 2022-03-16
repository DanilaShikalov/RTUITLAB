package itlab.done.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "warehouse")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WarehouseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "warehouse_id_generator")
    @SequenceGenerator(name = "warehouse_id_generator", sequenceName = "warehouse_id_seq", allocationSize = 1)
    private Long id;
    private String title;
    private int weight;
    private int amount;
    private int price;
}
