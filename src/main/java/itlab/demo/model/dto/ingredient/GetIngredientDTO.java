package itlab.demo.model.dto.ingredient;

import itlab.demo.model.dto.warehouse.GetWarehouseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetIngredientDTO {
    private Long id;
    private GetWarehouseDTO warehouse;
}
