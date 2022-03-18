package itlab.demo.dao;

import itlab.demo.RestaurantApp;
import itlab.demo.model.entity.IngredientEntity;
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
class IngredientRepositoryTest {
    @Autowired
    private IngredientRepository ingredientRepository;
    @Autowired
    private DataSource dataSource;

    @PostConstruct
    public void initData() throws SQLException {

        var populator = new CompositeDatabasePopulator();
        populator.addPopulators(new ResourceDatabasePopulator(new ClassPathResource("itlab/demo/dao/dataIngredient.sql")));

        populator.populate(dataSource.getConnection());
    }

    @Test
    public void getIngredientTest() {

        IngredientEntity ingredientEntity = new IngredientEntity(null, 1L, 1L, null, null);

        ingredientRepository.save(ingredientEntity);

        IngredientEntity foundEntity = ingredientRepository.findById(ingredientEntity.getId()).orElse(null);
        assertNotNull(foundEntity);
        assertEquals(ingredientEntity.getId_warehouse(), foundEntity.getId_warehouse());
    }

    @Test
    public void postIngredientTest() {
        IngredientEntity ingredientEntity = new IngredientEntity(null, 1L, 1L, null, null);

        ingredientRepository.save(ingredientEntity);

        IngredientEntity ingredientEntityTest = ingredientRepository.save(ingredientEntity);
        ingredientEntityTest.setId(ingredientEntity.getId());
        assertNotNull(ingredientEntityTest);
        assertThat(ingredientEntity).isEqualTo(ingredientEntityTest);
    }

    @Test
    public void putIngredientTest() {
        IngredientEntity ingredientEntity = new IngredientEntity(null, 1L, 1L, null, null);

        ingredientRepository.save(ingredientEntity);
        ingredientEntity.setId_warehouse(1L);
        ingredientRepository.save(ingredientEntity);
        IngredientEntity ingredientEntityTest = ingredientRepository.findById(ingredientEntity.getId()).orElseThrow(null);
        assertNotNull(ingredientEntityTest);
        assertEquals(ingredientEntity.getId_warehouse(), ingredientEntityTest.getId_warehouse());
    }

    @Test
    public void deleteIngredientTest() {
        IngredientEntity ingredientEntity = new IngredientEntity(null, 1L, 1L, null, null);

        IngredientEntity ingredientEntityTest = ingredientRepository.save(ingredientEntity);
        ingredientRepository.deleteById(ingredientEntity.getId());
        Optional<IngredientEntity> ingredientEntityTesting = ingredientRepository.findById(ingredientEntity.getId());
        assertNotNull(ingredientEntityTest);
        assertEquals("success", (ingredientEntityTesting.isPresent()) ? "not success" : "success");
    }
}