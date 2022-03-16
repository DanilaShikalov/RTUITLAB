package itlab.demo.dao;

import itlab.demo.RestaurantApp;
import itlab.demo.model.entity.UnitEntity;
import itlab.demo.model.entity.WarehouseEntity;
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
import java.sql.SQLException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RestaurantApp.class)
class WarehouseRepositoryTest {
    @Autowired
    private WarehouseRepository warehouseRepository;
    @Autowired
    private DataSource dataSource;

    @PostConstruct
    public void initData() throws SQLException {

        var populator = new CompositeDatabasePopulator();
        populator.addPopulators(new ResourceDatabasePopulator(new ClassPathResource("itlab/demo/dao/dataWarehouse.sql")));

        populator.populate(dataSource.getConnection());
    }

    @Test
    public void getWarehouseTest() {

        WarehouseEntity warehouseEntity = new WarehouseEntity(null, 1L,
                1L, "торт", 500,
                5, 340, null, null);

        warehouseRepository.save(warehouseEntity);

        WarehouseEntity foundEntity = warehouseRepository.findById(warehouseEntity.getId()).orElse(null);
        assertNotNull(foundEntity);
        assertEquals(warehouseEntity.getTitle(), foundEntity.getTitle());
    }

    @Test
    public void postWarehouseTest() {
        WarehouseEntity warehouseEntity = new WarehouseEntity(null, 1L,
                1L, "торт", 500,
                5, 340, null, null);

        warehouseRepository.save(warehouseEntity);

        WarehouseEntity warehouseEntityTest = warehouseRepository.save(warehouseEntity);
        warehouseEntityTest.setId(warehouseEntity.getId());
        assertNotNull(warehouseEntityTest);
        assertThat(warehouseEntity).isEqualTo(warehouseEntityTest);
    }

    @Test
    public void putWarehouseTest() {
        WarehouseEntity warehouseEntity = new WarehouseEntity(null, 1L,
                1L, "торт", 500,
                5, 340, null, null);

        warehouseRepository.save(warehouseEntity);
        warehouseEntity.setTitle("test");
        warehouseRepository.save(warehouseEntity);
        WarehouseEntity warehouseEntityTest = warehouseRepository.findById(warehouseEntity.getId()).orElseThrow(null);
        assertNotNull(warehouseEntityTest);
        assertEquals(warehouseEntity.getTitle(), warehouseEntityTest.getTitle());
    }

    @Test
    public void deleteWarehouseTest() {
        WarehouseEntity warehouseEntity = new WarehouseEntity(null, 1L,
                1L, "торт", 500,
                5, 340, null, null);

        WarehouseEntity warehouseEntityTest = warehouseRepository.save(warehouseEntity);
        warehouseRepository.deleteById(warehouseEntity.getId());
        Optional<WarehouseEntity> warehouseEntityTesting = warehouseRepository.findById(warehouseEntity.getId());
        assertNotNull(warehouseEntityTest);
        assertEquals("success", (warehouseEntityTesting.isPresent()) ? "not success" : "success");
    }
}