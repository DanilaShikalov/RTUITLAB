package itlab.demo.service;

import itlab.demo.dao.DishRepository;
import itlab.demo.dao.MenuRepository;
import itlab.demo.dao.OrdersRepository;
import itlab.demo.dao.WarehouseRepository;
import itlab.demo.exception.DishNotFoundException;
import itlab.demo.exception.MenuNotFoundException;
import itlab.demo.exception.OrdersNotFoundException;
import itlab.demo.exception.WarehouseAmountZeroException;
import itlab.demo.mapper.DishMapper;
import itlab.demo.model.dto.dish.*;
import itlab.demo.model.entity.DishEntity;
import itlab.demo.model.entity.IngredientEntity;
import itlab.demo.model.entity.MenuEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class DishService {
    private final DishRepository dishRepository;
    private final DishMapper dishMapper;
    private final MenuRepository menuRepository;
    private final OrdersRepository ordersRepository;
    private final WarehouseRepository warehouseRepository;

    public PostedDishDTO createDish(PostDishDTO postDishDTO) {
        ordersRepository.findById(postDishDTO.getId_orders()).orElseThrow(() -> new OrdersNotFoundException(postDishDTO.getId_orders()));
        MenuEntity menu = menuRepository.findById(postDishDTO.getId_menu()).orElseThrow(() -> new MenuNotFoundException(postDishDTO.getId_menu()));
        for (IngredientEntity x : menu.getIngredient()) {
            if (x.getWarehouse().getAmount() <= 0)
                throw new WarehouseAmountZeroException(x.getWarehouse().getTitle());
            else x.getWarehouse().setAmount(x.getWarehouse().getAmount() - 1);
            warehouseRepository.save(x.getWarehouse());
        }
        DishEntity entity = dishMapper.dtoToEntity(postDishDTO);
        DishEntity save = dishRepository.save(entity);
        return dishMapper.entityToCreatedDTO(save);
    }

    public GetDishDTO getDish(Long id) {
        DishEntity dishEntity = dishRepository.findById(id).orElseThrow(() -> new DishNotFoundException(id));
        return dishMapper.entityToDTO(dishEntity);
    }

    public List<GetDishDTO> getAllDish()
    {
        List<DishEntity> list = dishRepository.findAll();
        return dishMapper.listEntityToListDTO(list);
    }

    public void deleteDish(Long id)
    {
        DishEntity dish = dishRepository.findById(id).orElseThrow(() -> new DishNotFoundException(id));
        MenuEntity menu = dish.getMenu();
        for (IngredientEntity x : menu.getIngredient()) {
            x.getWarehouse().setAmount(x.getWarehouse().getAmount() + 1);
            warehouseRepository.save(x.getWarehouse());
        }
        dishRepository.findById(id).orElseThrow(() -> new DishNotFoundException(id));
        dishRepository.deleteById(id);
    }

    public PuttedDishDTO putDish(PutDishDTO putDishDTO, Long id)
    {
        ordersRepository.findById(putDishDTO.getId_orders()).orElseThrow(() -> new OrdersNotFoundException(putDishDTO.getId_orders()));
        menuRepository.findById(putDishDTO.getId_menu()).orElseThrow(() -> new MenuNotFoundException(putDishDTO.getId_menu()));
        dishRepository.findById(id).orElseThrow(() -> new DishNotFoundException(id));
        DishEntity put = dishMapper.putDTOToEntity(putDishDTO);
        put.setId(id);
        dishRepository.save(put);
        return dishMapper.entityToPutDTO(put);
    }
}
