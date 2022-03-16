package itlab.done.model.dto.history;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetHistoryLowDTO {
    private Long id;
    private Long id_unit;
    private Long id_product;
    private String title;
    private int weight;
    private int amount;
    private int price;
}
