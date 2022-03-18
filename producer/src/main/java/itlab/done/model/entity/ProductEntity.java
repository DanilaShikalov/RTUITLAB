package itlab.done.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "product")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private int weight;
    private int amount;
    @OneToMany(targetEntity = HistoryEntity.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_product", referencedColumnName = "id", insertable=false, updatable=false)
    private List<HistoryEntity> history;
}
