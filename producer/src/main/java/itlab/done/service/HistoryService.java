package itlab.done.service;

import itlab.done.config.MQConfig;
import itlab.done.dao.HistoryRepository;
import itlab.done.dao.ProductRepository;
import itlab.done.exception.HistoryNotFoundException;
import itlab.done.exception.ProductAmountZeroException;
import itlab.done.exception.ProductNotFoundException;
import itlab.done.mapper.HistoryMapper;
import itlab.done.model.dto.history.*;
import itlab.done.model.entity.HistoryEntity;
import itlab.done.model.entity.ProductEntity;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class HistoryService {
    private final HistoryRepository historyRepository;
    private final HistoryMapper historyMapper;
    private final ProductRepository productRepository;

    public HistoryService(HistoryRepository historyRepository, HistoryMapper historyMapper, ProductRepository productRepository) {
        this.historyRepository = historyRepository;
        this.historyMapper = historyMapper;
        this.productRepository = productRepository;
    }

    @Autowired
    private RabbitTemplate template;

    public PostedHistoryDTO createHistory(PostHistoryDTO postHistoryDTO) {
        ProductEntity product = productRepository.findById(postHistoryDTO.getId_product()).orElseThrow(() -> new ProductNotFoundException(postHistoryDTO.getId_product()));
        if (product.getAmount() < postHistoryDTO.getAmount())
            throw new ProductAmountZeroException(product.getAmount());
        product.setAmount(product.getAmount() - postHistoryDTO.getAmount());
        productRepository.save(product);
        HistoryEntity entity = historyMapper.dtoToEntity(postHistoryDTO);
        HistoryEntity save = historyRepository.save(entity);
        return historyMapper.entityToCreatedDTO(save);
    }

    public GetHistoryDTO getHistory(Long id) {
        HistoryEntity historyEntity = historyRepository.findById(id).orElseThrow(() -> new HistoryNotFoundException(id));
        return historyMapper.entityToDTO(historyEntity);
    }

    public List<GetHistoryDTO> getAllHistory()
    {
        List<HistoryEntity> list = historyRepository.findAll();
        return historyMapper.listEntityToListDTO(list);
    }

    public void deleteHistory(Long id)
    {
        historyRepository.findById(id).orElseThrow(() -> new HistoryNotFoundException(id));
        historyRepository.deleteById(id);
    }

    public PuttedHistoryDTO putHistory(PutHistoryDTO putHistoryDTO, Long id)
    {
        productRepository.findById(putHistoryDTO.getId_product()).orElseThrow(() -> new ProductNotFoundException(putHistoryDTO.getId_product()));
        historyRepository.findById(id).orElseThrow(() -> new HistoryNotFoundException(id));
        HistoryEntity put = historyMapper.putDTOToEntity(putHistoryDTO);
        put.setId(id);
        historyRepository.save(put);
        return historyMapper.entityToPutDTO(put);
    }

    public void upLowCase(GetLowWarehouseDTO x)
    {
        if (productRepository.findById(x.getId_product()).orElseThrow(() -> new ProductNotFoundException(x.getId_product())).getAmount() > 0)
        {
            x.setAmount(x.getAmount() + 1);
            PostHistoryDTO postHistoryDTO = PostHistoryDTO.builder().id_product(x.getId_product())
                    .amount(1)
                    .date_delivery(new Date(System.currentTimeMillis()))
                    .build();
            createHistory(postHistoryDTO);
            template.convertAndSend(MQConfig.EXCHANGE_UP,
                    MQConfig.ROUTING_KEY_UP, x);
        }
    }
}
