package itlab.demo.model.dto.employees;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostedEmployeesDTO {
    private Long id;
    private Long id_position;
    private String name;
    private String surname;
    private String second_name;
    private String number;
}
