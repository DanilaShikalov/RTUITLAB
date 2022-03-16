package itlab.done.mapper;

import itlab.done.model.dto.product.*;
import itlab.done.model.entity.ProductEntity;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductEntity dtoToEntity(PostProductDTO postClientDTO);

    PostedProductDTO entityToCreatedDTO(ProductEntity productEntity);

    ProductEntity putDTOToEntity(PutProductDTO putProductDTO);

    PuttedProductDTO entityToPutDTO(ProductEntity productEntity);

    GetProductDTO entityToDTO(ProductEntity productEntity);

    List<GetProductDTO> listEntityToListDTO(List<ProductEntity> listProduct);
}
