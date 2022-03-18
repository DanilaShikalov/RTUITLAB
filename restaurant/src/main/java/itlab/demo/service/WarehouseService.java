package itlab.demo.service;

import itlab.demo.config.MQConfig;
import itlab.demo.dao.UnitRepository;
import itlab.demo.dao.WarehouseRepository;
import itlab.demo.exception.UnitNotFoundException;
import itlab.demo.exception.WarehouseNotFoundException;
import itlab.demo.exception.WarehouseNotRemoveException;
import itlab.demo.mapper.WarehouseMapper;
import itlab.demo.model.dto.ingredient.GetIngredientsMenuDTO;
import itlab.demo.model.dto.warehouse.*;
import itlab.demo.model.entity.WarehouseEntity;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WarehouseService {
    private final WarehouseRepository warehouseRepository;
    private final WarehouseMapper warehouseMapper;
    private final UnitRepository unitRepository;

    public WarehouseService(WarehouseRepository warehouseRepository, WarehouseMapper warehouseMapper, UnitRepository unitRepository) {
        this.warehouseRepository = warehouseRepository;
        this.warehouseMapper = warehouseMapper;
        this.unitRepository = unitRepository;
    }

    @Autowired
    private RabbitTemplate template;

    public PostedWarehouseDTO createWarehouse(PostWarehouseDTO postWarehouseDTO) {
        unitRepository.findById(postWarehouseDTO.getId_unit()).orElseThrow(() -> new UnitNotFoundException(postWarehouseDTO.getId_unit()));
        WarehouseEntity entity = warehouseMapper.dtoToEntity(postWarehouseDTO);
        WarehouseEntity save = warehouseRepository.save(entity);
        return warehouseMapper.entityToCreatedDTO(save);
    }

    public GetWarehouseDTO getWarehouse(Long id) {
        WarehouseEntity warehouseEntity = warehouseRepository.findById(id).orElseThrow(() -> new WarehouseNotFoundException(id));
        return warehouseMapper.entityToDTO(warehouseEntity);
    }

    public List<GetWarehouseDTO> getAllWarehouse()
    {
        List<WarehouseEntity> list = warehouseRepository.findAll();
        return warehouseMapper.listEntityToListDTO(list);
    }

    public void deleteWarehouse(Long id)
    {
        warehouseRepository.findById(id).orElseThrow(() -> new WarehouseNotFoundException(id));
        try {
            warehouseRepository.deleteById(id);
        } catch (Exception e) {
            WarehouseEntity warehouseEntity = warehouseRepository.findById(id).orElseThrow(() -> new WarehouseNotFoundException(id));
            List<GetIngredientsMenuDTO> ingredients = warehouseMapper.entityToMenuWarehouseDTO(warehouseEntity).getIngredients();
            List<Long> ids = new ArrayList<>();
            for (GetIngredientsMenuDTO x: ingredients) {
                ids.add(x.getMenu().getId());
            }
            throw new WarehouseNotRemoveException(ids);
        }
    }

    public PuttedWarehouseDTO putWarehouse(PutWarehouseDTO putWarehouseDTO, Long id)
    {
        unitRepository.findById(putWarehouseDTO.getId_unit()).orElseThrow(() -> new UnitNotFoundException(putWarehouseDTO.getId_unit()));
        warehouseRepository.findById(id).orElseThrow(() -> new WarehouseNotFoundException(id));
        WarehouseEntity put = warehouseMapper.putDTOToEntity(putWarehouseDTO);
        put.setId(id);
        warehouseRepository.save(put);
        return warehouseMapper.entityToPutDTO(put);
    }

    public List<GetLowWarehouseDTO> getLowWarehouse()
    {
        return warehouseMapper.listLowEntityToListLowDTO(warehouseRepository.findAll().stream().filter(x -> x.getAmount() == 0).collect(Collectors.toList()));
    }

    public void lowScheduled()
    {
        List<GetLowWarehouseDTO> list = getLowWarehouse();
        if (!list.isEmpty()) {
            for (GetLowWarehouseDTO x : list) {
                template.convertAndSend(MQConfig.EXCHANGE_LOW,
                        MQConfig.ROUTING_KEY_LOW, x);
            }
        }
    }

    public void changeLowCase(GetLowWarehouseDTO getLowWarehouseDTO)
    {
        PutWarehouseDTO putWarehouseDTO = PutWarehouseDTO.builder()
                .id_unit(getLowWarehouseDTO.getId_unit())
                .id_product(getLowWarehouseDTO.getId_product())
                .title(getLowWarehouseDTO.getTitle())
                .weight(getLowWarehouseDTO.getWeight())
                .amount(getLowWarehouseDTO.getAmount())
                .price(getLowWarehouseDTO.getPrice())
                .build();
        putWarehouse(putWarehouseDTO, getLowWarehouseDTO.getId());
    }
}
