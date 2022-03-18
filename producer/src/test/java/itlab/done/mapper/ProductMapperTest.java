package itlab.done.mapper;

import itlab.done.model.dto.product.*;
import itlab.done.model.entity.ProductEntity;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProductMapperTest {
    private final ProductMapper productMapper = Mappers.getMapper(ProductMapper.class);

    @Test
    void dtoToEntity() {
        PostProductDTO postProductDTO = new PostProductDTO("test", 500, 4);
        ProductEntity productEntity = productMapper.dtoToEntity(postProductDTO);
        assertEquals(postProductDTO.getAmount(), productEntity.getAmount());
    }

    @Test
    void entityToCreatedDTO() {
        ProductEntity productEntity = new ProductEntity(null, "test", 500, 4, null);
        PostedProductDTO postedProductDTO = productMapper.entityToCreatedDTO(productEntity);
        assertEquals(productEntity.getAmount(), postedProductDTO.getAmount());
        assertEquals(productEntity.getTitle(), postedProductDTO.getTitle());
    }

    @Test
    void putDTOToEntity() {
        PutProductDTO putProductDTO = new PutProductDTO("test", 500, 4);
        ProductEntity productEntity = productMapper.putDTOToEntity(putProductDTO);
        assertEquals(putProductDTO.getAmount(), productEntity.getAmount());
        assertEquals(putProductDTO.getTitle(), productEntity.getTitle());
    }

    @Test
    void entityToPutDTO() {
        ProductEntity productEntity = new ProductEntity(null, "test", 500, 4, null);
        PuttedProductDTO puttedProductDTO = productMapper.entityToPutDTO(productEntity);
        assertEquals(productEntity.getAmount(), puttedProductDTO.getAmount());
        assertEquals(productEntity.getTitle(), puttedProductDTO.getTitle());
    }

    @Test
    void entityToDTO() {
        ProductEntity productEntity = new ProductEntity(null, "test", 500, 4, null);
        GetProductDTO getProductDTO = productMapper.entityToDTO(productEntity);
        assertEquals(productEntity.getAmount(), getProductDTO.getAmount());
    }

    @Test
    void listEntityToListDTO() {
        List<ProductEntity> list = List.of(new ProductEntity(null, "test", 500, 4, null));
        List<GetProductDTO> list1 = productMapper.listEntityToListDTO(list);
        assertEquals(list.get(0).getAmount(), list1.get(0).getAmount());
    }
}