package itlab.demo.model.dto.orders;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetOrdersMenuDTO {
    private Long id;
    private Date date_open;
    private boolean status;
    private Date date_close;
}
