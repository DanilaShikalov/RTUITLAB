package itlab.demo.service;

import itlab.demo.dao.IngredientRepository;
import itlab.demo.dao.MenuRepository;
import itlab.demo.dao.WarehouseRepository;
import itlab.demo.exception.IngredientNotFoundException;
import itlab.demo.exception.MenuNotFoundException;
import itlab.demo.exception.WarehouseNotFoundException;
import itlab.demo.mapper.IngredientMapper;
import itlab.demo.model.dto.ingredient.*;
import itlab.demo.model.entity.IngredientEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class IngredientService {
    private final IngredientRepository ingredientRepository;
    private final IngredientMapper ingredientMapper;
    private final MenuRepository menuRepository;
    private final WarehouseRepository warehouseRepository;

    public PostedIngredientDTO createIngredient(PostIngredientDTO postIngredientDTO) {
        menuRepository.findById(postIngredientDTO.getId_menu()).orElseThrow(() -> new MenuNotFoundException(postIngredientDTO.getId_menu()));
        warehouseRepository.findById(postIngredientDTO.getId_warehouse()).orElseThrow(() -> new WarehouseNotFoundException(postIngredientDTO.getId_warehouse()));
        IngredientEntity entity = ingredientMapper.dtoToEntity(postIngredientDTO);
        IngredientEntity save = ingredientRepository.save(entity);
        return ingredientMapper.entityToCreatedDTO(save);
    }

    public GetIngredientDTO getIngredient(Long id) {
        IngredientEntity ingredientEntity = ingredientRepository.findById(id).orElseThrow(() -> new IngredientNotFoundException(id));
        return ingredientMapper.entityToDTO(ingredientEntity);
    }

    public List<GetIngredientDTO> getAllIngredient()
    {
        List<IngredientEntity> list = ingredientRepository.findAll();
        return ingredientMapper.listEntityToListDTO(list);
    }

    public void deleteIngredient(Long id)
    {
        ingredientRepository.findById(id).orElseThrow(() -> new IngredientNotFoundException(id));
        ingredientRepository.deleteById(id);
    }

    public PuttedIngredientDTO putIngredient(PutIngredientDTO putIngredientDTO, Long id)
    {
        menuRepository.findById(putIngredientDTO.getId_menu()).orElseThrow(() -> new MenuNotFoundException(putIngredientDTO.getId_menu()));
        warehouseRepository.findById(putIngredientDTO.getId_warehouse()).orElseThrow(() -> new WarehouseNotFoundException(putIngredientDTO.getId_warehouse()));
        ingredientRepository.findById(id).orElseThrow(() -> new IngredientNotFoundException(id));
        IngredientEntity put = ingredientMapper.putDTOToEntity(putIngredientDTO);
        put.setId(id);
        ingredientRepository.save(put);
        return ingredientMapper.entityToPutDTO(put);
    }
}
