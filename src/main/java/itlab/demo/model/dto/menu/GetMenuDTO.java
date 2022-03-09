package itlab.demo.model.dto.menu;

import itlab.demo.model.dto.ingredient.GetIngredientDTO;
import itlab.demo.model.dto.unit.GetUnitDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetMenuDTO {
    private Long id;
    private String title;
    private int price;
    private int weight;
    private List<GetIngredientDTO> ingredient;
    private GetUnitDTO unit;
}
