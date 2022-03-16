package itlab.done.service;

import itlab.done.dao.HistoryRepository;
import itlab.done.dao.ProductRepository;
import itlab.done.exception.HistoryNotFoundException;
import itlab.done.exception.ProductAmountZeroException;
import itlab.done.exception.ProductNotFoundException;
import itlab.done.mapper.HistoryMapper;
import itlab.done.model.dto.history.*;
import itlab.done.model.entity.HistoryEntity;
import itlab.done.model.entity.ProductEntity;
import lombok.AllArgsConstructor;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.sql.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class HistoryService {
    private final HistoryRepository historyRepository;
    private final HistoryMapper historyMapper;
    private final ProductRepository productRepository;

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

    public List<GetHistoryLowDTO> getRequests() throws JSONException {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<GetHistoryLowDTO>> responseEntity = restTemplate.exchange("http://restaurant:8081/api/warehouse/low",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<GetHistoryLowDTO>>() {
                });
        List<GetHistoryLowDTO> list = responseEntity.getBody();
        JSONObject warehouseJSON = new JSONObject();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        assert list != null;
        PostHistoryDTO postHistoryDTO;
        Date currentSqlDate = new Date(System.currentTimeMillis());
        for (GetHistoryLowDTO getHistoryLowDTO: list) {
            warehouseJSON.put("id_unit", getHistoryLowDTO.getId_unit());
            warehouseJSON.put("id_product", getHistoryLowDTO.getId_product());
            warehouseJSON.put("title", getHistoryLowDTO.getTitle());
            warehouseJSON.put("weight", getHistoryLowDTO.getWeight());
            warehouseJSON.put("amount", getHistoryLowDTO.getAmount() + 1);
            warehouseJSON.put("price", getHistoryLowDTO.getPrice());
            HttpEntity<String> request = new HttpEntity<String>(warehouseJSON.toString(), headers);
            restTemplate.put("http://restaurant:8081/api/warehouse/" + getHistoryLowDTO.getId(), request);
            postHistoryDTO = PostHistoryDTO.builder().id_product(getHistoryLowDTO.getId_product())
                    .amount(1)
                    .date_delivery(currentSqlDate)
                    .build();
            this.createHistory(postHistoryDTO);
        }
        return list;
    }
}
