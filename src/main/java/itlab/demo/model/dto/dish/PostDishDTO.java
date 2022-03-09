package itlab.demo.model.dto.dish;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostDishDTO {
    private Long id_orders;
    private Long id_menu;
}
