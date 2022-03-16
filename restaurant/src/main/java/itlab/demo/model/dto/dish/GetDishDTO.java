package itlab.demo.model.dto.dish;

import itlab.demo.model.dto.menu.GetMenuDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetDishDTO {
    private Long id;
    private GetMenuDTO menu;
}
