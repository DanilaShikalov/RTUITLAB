package itlab.demo.model.dto.warehouse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetWarehouseProductDTO {
    private Long id;
    private String title;
    private int weight;
    private int amount;
}
