package itlab.done.model.dto.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PuttedProductDTO {
    private Long id;
    private String title;
    private int weight;
    private int amount;
}
