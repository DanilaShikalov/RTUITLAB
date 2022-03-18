package itlab.done.listener;

import itlab.done.config.MQConfig;
import itlab.done.model.dto.history.GetLowWarehouseDTO;
import itlab.done.service.HistoryService;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class MessageListener {
    private final HistoryService historyService;

    @RabbitListener(queues = MQConfig.QUEUE_LOW)
    public void listener(GetLowWarehouseDTO message) {
        historyService.upLowCase(message);
        System.out.println(message);
    }
}
