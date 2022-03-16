package itlab.demo.model.dto.positions;

import itlab.demo.model.dto.employees.GetEmployeeDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetPositionsDTO {
    private Long id;
    private String position;
    private int salary;
    private List<GetEmployeeDTO> employees;
}
