package itlab.demo.model.dto.ingredient;

import itlab.demo.model.dto.menu.GetMenuWarehouseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetIngredientsMenuDTO {
    private Long id;
    private GetMenuWarehouseDTO menu;
}
