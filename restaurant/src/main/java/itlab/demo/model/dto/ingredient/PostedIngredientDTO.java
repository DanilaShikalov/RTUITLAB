package itlab.demo.model.dto.ingredient;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostedIngredientDTO {
    private Long id;
    private Long id_menu;
    private Long id_warehouse;
}
