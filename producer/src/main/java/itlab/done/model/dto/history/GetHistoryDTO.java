package itlab.done.model.dto.history;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetHistoryDTO {
    private Long id;
    private int amount;
    private Date date_delivery;
}
