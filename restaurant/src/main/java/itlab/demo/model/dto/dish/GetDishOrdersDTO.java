package itlab.demo.model.dto.dish;

import itlab.demo.model.dto.orders.GetOrdersMenuDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetDishOrdersDTO {
    private Long id;
    private GetOrdersMenuDTO orders;
}
