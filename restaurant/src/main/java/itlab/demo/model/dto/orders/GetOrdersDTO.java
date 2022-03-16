package itlab.demo.model.dto.orders;

import itlab.demo.model.dto.dish.GetDishDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetOrdersDTO {
    private Long id;
    private Date date_open;
    private boolean status;
    private Date date_close;
    private List<GetDishDTO> dishes;
}
