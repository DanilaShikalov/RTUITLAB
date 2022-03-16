package itlab.demo.model.dto.positions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetPositionDTO {
    private Long id;
    private String position;
    private int salary;
}
