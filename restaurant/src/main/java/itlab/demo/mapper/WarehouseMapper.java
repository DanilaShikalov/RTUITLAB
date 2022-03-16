package itlab.demo.mapper;

import itlab.demo.model.dto.warehouse.*;
import itlab.demo.model.entity.WarehouseEntity;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface WarehouseMapper {
    WarehouseEntity dtoToEntity(PostWarehouseDTO postClientDTO);

    PostedWarehouseDTO entityToCreatedDTO(WarehouseEntity warehouseEntity);

    WarehouseEntity putDTOToEntity(PutWarehouseDTO putWarehouseDTO);

    PuttedWarehouseDTO entityToPutDTO(WarehouseEntity warehouseEntity);

    GetWarehouseDTO entityToDTO(WarehouseEntity warehouseEntity);

    GetWarehouseMenuDTO entityToMenuWarehouseDTO(WarehouseEntity warehouseEntity);

    List<GetWarehouseDTO> listEntityToListDTO(List<WarehouseEntity> listWarehouse);

    List<GetLowWarehouseDTO> listLowEntityToListLowDTO(List<WarehouseEntity> listWarehouse);
}
