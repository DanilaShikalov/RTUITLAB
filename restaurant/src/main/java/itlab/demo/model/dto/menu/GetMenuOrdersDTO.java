package itlab.demo.model.dto.menu;

import itlab.demo.model.dto.dish.GetDishOrdersDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetMenuOrdersDTO {
    private Long id;
    private String title;
    private int price;
    private int weight;
    List<GetDishOrdersDTO> dishes;
}
