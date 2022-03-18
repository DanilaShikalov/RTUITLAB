package itlab.demo.listener;

import itlab.demo.config.MQConfig;
import itlab.demo.model.dto.warehouse.GetLowWarehouseDTO;
import itlab.demo.service.WarehouseService;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class MessageListener {
    private final WarehouseService warehouseService;

    @RabbitListener(queues = MQConfig.QUEUE_UP)
    public void listener(GetLowWarehouseDTO message) {
        System.out.println(message);
        warehouseService.changeLowCase(message);
    }
}
