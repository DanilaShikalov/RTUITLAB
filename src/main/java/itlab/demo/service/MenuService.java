package itlab.demo.service;

import itlab.demo.dao.EmployeesRepository;
import itlab.demo.dao.MenuRepository;
import itlab.demo.dao.UnitRepository;
import itlab.demo.exception.*;
import itlab.demo.mapper.EmployeesMapper;
import itlab.demo.mapper.MenuMapper;
import itlab.demo.model.dto.dish.GetDishOrdersDTO;
import itlab.demo.model.dto.employees.*;
import itlab.demo.model.dto.menu.*;
import itlab.demo.model.entity.EmployeesEntity;
import itlab.demo.model.entity.MenuEntity;
import itlab.demo.model.entity.WarehouseEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class MenuService {
    private final MenuRepository menuRepository;
    private final MenuMapper menuMapper;
    private final UnitRepository unitRepository;

    public PostedMenuDTO createMenu(PostMenuDTO postMenuDTO) {
        unitRepository.findById(postMenuDTO.getId_unit()).orElseThrow(() -> new UnitNotFoundException(postMenuDTO.getId_unit()));
        MenuEntity entity = menuMapper.dtoToEntity(postMenuDTO);
        MenuEntity save = menuRepository.save(entity);
        return menuMapper.entityToCreatedDTO(save);
    }

    public GetMenuDTO getMenu(Long id) {
        MenuEntity menuEntity = menuRepository.findById(id).orElseThrow(() -> new MenuNotFoundException(id));
        return menuMapper.entityToDTO(menuEntity);
    }

    public List<GetMenuDTO> getAllMenu()
    {
        List<MenuEntity> list = menuRepository.findAll();
        return menuMapper.listEntityToListDTO(list);
    }

    public void deleteMenu(Long id)
    {
        menuRepository.findById(id).orElseThrow(() -> new MenuNotFoundException(id));
        try {
            menuRepository.deleteById(id);
        } catch (Exception e) {
            MenuEntity menuEntity = menuRepository.findById(id).orElseThrow(() -> new MenuNotFoundException(id));
            List<GetDishOrdersDTO> dishes = menuMapper.entityToMenuOrdersDTO(menuEntity).getDishes();
            List<Long> ids = new ArrayList<>();
            for (GetDishOrdersDTO x: dishes) {
                ids.add(x.getOrders().getId());
            }
            throw new MenuNotRemoveException(ids);
        }
    }

    public PuttedMenuDTO putMenu(PutMenuDTO putMenuDTO, Long id)
    {
        unitRepository.findById(putMenuDTO.getId_unit()).orElseThrow(() -> new UnitNotFoundException(putMenuDTO.getId_unit()));
        menuRepository.findById(id).orElseThrow(() -> new MenuNotFoundException(id));
        MenuEntity put = menuMapper.putDTOToEntity(putMenuDTO);
        put.setId(id);
        menuRepository.save(put);
        return menuMapper.entityToPutDTO(put);
    }
}
