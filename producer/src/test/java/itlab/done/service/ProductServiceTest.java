package itlab.done.service;

import itlab.done.dao.ProductRepository;
import itlab.done.mapper.ProductMapper;
import itlab.done.model.dto.product.PostProductDTO;
import itlab.done.model.entity.ProductEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {
    @Mock
    private ProductRepository productRepository;
    @Mock
    private ProductMapper productMapper;
    private AutoCloseable autoCloseable;
    private ProductService underTest;
    @Mock
    private ProductEntity productEntity;


    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new ProductService(productRepository, productMapper);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    public void canCreateProduct() {
        // given
        PostProductDTO postProductDTO = new PostProductDTO(productEntity.getTitle(), productEntity.getWeight(), productEntity.getAmount());
        ProductEntity product = productMapper.dtoToEntity(postProductDTO);
        //when
        underTest.createProduct(postProductDTO);

        //then
        ArgumentCaptor<ProductEntity> productEntityArgumentCaptor =
                ArgumentCaptor.forClass(ProductEntity.class);
        verify(productRepository).save(productEntityArgumentCaptor.capture());

        ProductEntity productEntity = productEntityArgumentCaptor.getValue();
        assertThat(productEntity).isEqualTo(product);
    }

    @Test
    void getProduct() {
        // given
        Long id = productEntity.getId();
        given(productRepository.findById(id)).willReturn(Optional.of(productEntity));
        //when
        underTest.getProduct(id);

        //then
        ArgumentCaptor<Long> longArgumentCaptor =
                ArgumentCaptor.forClass(Long.class);
        verify(productRepository).findById(longArgumentCaptor.capture());
        Long capturedId = longArgumentCaptor.getValue();
        assertThat(capturedId).isEqualTo(id);
    }

    @Test
    void canGetAllProduct() {
        underTest.getAllProduct();

        verify(productRepository).findAll();
    }
}