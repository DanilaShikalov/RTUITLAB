package itlab.demo.model.dto.warehouse;

import itlab.demo.model.dto.ingredient.GetIngredientsMenuDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetWarehouseMenuDTO {
    private Long id;
    private String title;
    private String weight;
    private int amount;
    private int price;
    List<GetIngredientsMenuDTO> ingredients;
}
