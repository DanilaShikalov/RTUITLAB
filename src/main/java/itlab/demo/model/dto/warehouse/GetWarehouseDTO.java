package itlab.demo.model.dto.warehouse;

import itlab.demo.model.dto.unit.GetUnitDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetWarehouseDTO {
    private Long id;
    private String title;
    private int weight;
    private int amount;
    private int price;
    private GetUnitDTO unit;
}
