package itlab.demo.model.dto.menu;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PuttedMenuDTO {
    private Long id;
    private Long id_unit;
    private String title;
    private int price;
    private int weight;
}
