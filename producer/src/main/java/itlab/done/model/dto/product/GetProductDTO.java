package itlab.done.model.dto.product;

import itlab.done.model.dto.history.GetHistoryDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetProductDTO {
    private Long id;
    private String title;
    private int weight;
    private int amount;
    List<GetHistoryDTO> history;
}
