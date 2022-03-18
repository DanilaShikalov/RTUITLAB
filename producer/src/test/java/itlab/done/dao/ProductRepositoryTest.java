package itlab.done.dao;

import itlab.done.ProducerApp;
import itlab.done.model.entity.HistoryEntity;
import itlab.done.model.entity.ProductEntity;
import lombok.var;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.CompositeDatabasePopulator;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import javax.transaction.Transactional;
import java.sql.SQLException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProducerApp.class)
@Transactional
class ProductRepositoryTest {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private DataSource dataSource;

    @PostConstruct
    public void initData() throws SQLException {
        var populator = new CompositeDatabasePopulator();
        populator.addPopulators(new ResourceDatabasePopulator(new ClassPathResource("itlab/done/dao/dataProduct.sql")));

        populator.populate(dataSource.getConnection());
    }

    @Test
    public void getProductTest() {
        ProductEntity productEntity = new ProductEntity(null, "test", 500, 4, null);
        productRepository.save(productEntity);
        ProductEntity foundEntity = productRepository.findById(productEntity.getId()).orElse(null);
        assertNotNull(foundEntity);
        assertEquals(productEntity.getAmount(), foundEntity.getAmount());
    }

    @Test
    public void postProductTest() {
        ProductEntity productEntity = new ProductEntity(null, "test", 500, 4, null);
        ProductEntity productEntityTest = productRepository.save(productEntity);
        assertNotNull(productEntityTest);
        assertEquals(productEntity.getAmount(), productEntityTest.getAmount());
    }

    @Test
    public void putProductTest() {
        ProductEntity productEntity = new ProductEntity(null, "test", 500, 4, null);
        productRepository.save(productEntity);
        productEntity.setAmount(10);
        productRepository.save(productEntity);
        ProductEntity productEntityTest = productRepository.findById(productEntity.getId()).orElseThrow(null);
        assertNotNull(productEntityTest);
        assertEquals(productEntity.getAmount(), productEntityTest.getAmount());
    }

    @Test
    public void deleteProductTest() {
        ProductEntity deleteEntity = new ProductEntity(null, "test", 500, 4, null);
        ProductEntity productEntity = productRepository.save(deleteEntity);
        productRepository.deleteById(deleteEntity.getId());
        Optional<ProductEntity> productEntityTest = productRepository.findById(productEntity.getId());
        assertNotNull(productEntityTest);
        assertEquals("success", (productEntityTest.isPresent()) ? "not success" : "success");
    }
}