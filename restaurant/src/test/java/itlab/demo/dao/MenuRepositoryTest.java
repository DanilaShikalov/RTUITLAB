package itlab.demo.dao;

import itlab.demo.RestaurantApp;
import itlab.demo.model.entity.MenuEntity;
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
class MenuRepositoryTest {
    @Autowired
    private MenuRepository menuRepository;
    @Autowired
    private DataSource dataSource;

    @PostConstruct
    public void initData() throws SQLException {

        var populator = new CompositeDatabasePopulator();
        populator.addPopulators(new ResourceDatabasePopulator(new ClassPathResource("itlab/demo/dao/dataMenu.sql")));

        populator.populate(dataSource.getConnection());
    }

    @Test
    public void getMenuTest() {

        MenuEntity menuEntity = new MenuEntity(null, 1L, "pizza", 500, 1000, null, null, null);

        menuRepository.save(menuEntity);

        MenuEntity foundEntity = menuRepository.findById(menuEntity.getId()).orElse(null);
        assertNotNull(foundEntity);
        assertEquals(menuEntity.getTitle(), foundEntity.getTitle());
    }

    @Test
    public void postMenuTest() {
        MenuEntity menuEntity = new MenuEntity(null, 1L, "pizza", 500, 1000, null, null, null);

        menuRepository.save(menuEntity);

        MenuEntity menuEntityTest = menuRepository.save(menuEntity);
        menuEntityTest.setId(menuEntity.getId());
        assertNotNull(menuEntityTest);
        assertThat(menuEntity).isEqualTo(menuEntityTest);
    }

    @Test
    public void putMenuTest() {
        MenuEntity menuEntity = new MenuEntity(null, 1L, "pizza", 500, 1000, null, null, null);

        menuRepository.save(menuEntity);
        menuEntity.setTitle("beer");
        menuRepository.save(menuEntity);
        MenuEntity menuEntityTest = menuRepository.findById(menuEntity.getId()).orElseThrow(null);
        assertNotNull(menuEntityTest);
        assertEquals(menuEntity.getTitle(), menuEntityTest.getTitle());
    }

    @Test
    public void deleteMenuTest() {
        MenuEntity menuEntity = new MenuEntity(null, 1L, "pizza", 500, 1000, null, null, null);

        MenuEntity menuEntityTest = menuRepository.save(menuEntity);
        menuRepository.deleteById(menuEntity.getId());
        Optional<MenuEntity> menuEntityTesting = menuRepository.findById(menuEntity.getId());
        assertNotNull(menuEntityTest);
        assertEquals("success", (menuEntityTesting.isPresent()) ? "not success" : "success");
    }

}