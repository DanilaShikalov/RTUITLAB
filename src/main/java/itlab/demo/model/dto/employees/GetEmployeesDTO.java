package itlab.demo.model.dto.employees;

import itlab.demo.model.dto.orders.GetOrdersDTO;
import itlab.demo.model.dto.positions.GetPositionDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetEmployeesDTO {
    private Long id;
    private String name;
    private String surname;
    private String second_name;
    private String number;
    private GetPositionDTO position;
    private List<GetOrdersDTO> orders;
}
