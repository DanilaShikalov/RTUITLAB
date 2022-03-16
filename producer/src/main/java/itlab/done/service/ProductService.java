package itlab.done.service;

import itlab.done.dao.ProductRepository;
import itlab.done.exception.ProductNotFoundException;
import itlab.done.exception.ProductNotRemoveException;
import itlab.done.mapper.ProductMapper;
import itlab.done.model.dto.product.*;
import itlab.done.model.entity.ProductEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public PostedProductDTO createProduct(PostProductDTO postProductDTO) {
        ProductEntity entity = productMapper.dtoToEntity(postProductDTO);
        ProductEntity save = productRepository.save(entity);
        return productMapper.entityToCreatedDTO(save);
    }

    public GetProductDTO getProduct(Long id) {
        ProductEntity productEntity = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
        return productMapper.entityToDTO(productEntity);
    }

    public List<GetProductDTO> getAllProduct()
    {
        List<ProductEntity> list = productRepository.findAll();
        return productMapper.listEntityToListDTO(list);
    }

    public void deleteProduct(Long id)
    {
        productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
        try {
            productRepository.deleteById(id);
        } catch (Exception e) {
            throw new ProductNotRemoveException(id);
        }
    }

    public PuttedProductDTO putProduct(PutProductDTO putProductDTO, Long id)
    {
        productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
        ProductEntity put = productMapper.putDTOToEntity(putProductDTO);
        put.setId(id);
        productRepository.save(put);
        return productMapper.entityToPutDTO(put);
    }
}
