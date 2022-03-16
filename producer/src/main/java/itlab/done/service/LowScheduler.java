package itlab.done.service;

import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class LowScheduler {
    private final HistoryService historyService;

    @Scheduled(initialDelay = 5000L, fixedDelay = 10000L)
    public void lowMethod(){
        System.out.println(historyService.getRequests());
    }
}
