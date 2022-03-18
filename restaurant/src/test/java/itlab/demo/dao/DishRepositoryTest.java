package itlab.demo.dao;

import itlab.demo.RestaurantApp;
import itlab.demo.model.entity.DishEntity;
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

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RestaurantApp.class)
@Transactional
class DishRepositoryTest {
    @Autowired
    private DishRepository dishRepository;
    @Autowired
    private DataSource dataSource;

    @PostConstruct
    public void initData() throws SQLException {

        var populator = new CompositeDatabasePopulator();
        populator.addPopulators(new ResourceDatabasePopulator(new ClassPathResource("itlab/demo/dao/dataDish.sql")));

        populator.populate(dataSource.getConnection());
    }

    @Test
    public void getDishTest() {

        DishEntity dishEntity = new DishEntity(null, 1L, 1L, null, null);

        dishRepository.save(dishEntity);

        DishEntity foundEntity = dishRepository.findById(dishEntity.getId()).orElse(null);
        assertNotNull(foundEntity);
        assertEquals(dishEntity.getId_menu(), foundEntity.getId_menu());
    }

    @Test
    public void postDishTest() {
        DishEntity dishEntity = new DishEntity(null, 1L, 1L, null, null);

        dishRepository.save(dishEntity);

        DishEntity dishEntityTest = dishRepository.save(dishEntity);
        dishEntityTest.setId(dishEntity.getId());
        assertNotNull(dishEntityTest);
        assertThat(dishEntity).isEqualTo(dishEntityTest);
    }

    @Test
    public void putDishTest() {
        DishEntity dishEntity = new DishEntity(null, 1L, 1L, null, null);

        dishRepository.save(dishEntity);
        dishEntity.setId_menu(2L);
        dishRepository.save(dishEntity);
        DishEntity dishEntityTest = dishRepository.findById(dishEntity.getId()).orElseThrow(null);
        assertNotNull(dishEntityTest);
        assertEquals(dishEntity.getId_menu(), dishEntityTest.getId_menu());
    }

    @Test
    public void deleteDishTest() {
        DishEntity dishEntity = new DishEntity(null, 1L, 1L, null, null);

        DishEntity dishEntityTest = dishRepository.save(dishEntity);
        dishRepository.deleteById(dishEntity.getId());
        Optional<DishEntity> dishEntityTesting = dishRepository.findById(dishEntity.getId());
        assertNotNull(dishEntityTest);
        assertEquals("success", (dishEntityTesting.isPresent()) ? "not success" : "success");
    }
}