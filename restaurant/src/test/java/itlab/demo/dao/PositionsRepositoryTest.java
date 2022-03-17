package itlab.demo.dao;

import itlab.demo.RestaurantApp;
import itlab.demo.model.entity.PositionsEntity;
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
class PositionsRepositoryTest {
    @Autowired
    private PositionsRepository positionsRepository;
    @Autowired
    private DataSource dataSource;

    @PostConstruct
    public void initData() throws SQLException {

        var populator = new CompositeDatabasePopulator();
        populator.addPopulators(new ResourceDatabasePopulator(new ClassPathResource("itlab/demo/dao/dataPositions.sql")));

        populator.populate(dataSource.getConnection());
    }

    @Test
    public void getPositionsTest() {

        PositionsEntity positionsEntity = new PositionsEntity(null, "официант", 10000, null);

        positionsRepository.save(positionsEntity);

        PositionsEntity foundEntity = positionsRepository.findById(positionsEntity.getId()).orElse(null);
        assertNotNull(foundEntity);
        assertEquals(positionsEntity.getPosition(), foundEntity.getPosition());
    }

    @Test
    public void postPositionsTest() {
        PositionsEntity positionsEntity = new PositionsEntity(null, "официант", 10000, null);

        positionsRepository.save(positionsEntity);

        PositionsEntity positionsEntityTest = positionsRepository.save(positionsEntity);
        positionsEntityTest.setId(positionsEntity.getId());
        assertNotNull(positionsEntityTest);
        assertThat(positionsEntity).isEqualTo(positionsEntityTest);
    }

    @Test
    public void putPositionsTest() {
        PositionsEntity positionsEntity = new PositionsEntity(null, "официант", 10000, null);

        positionsRepository.save(positionsEntity);
        positionsEntity.setPosition("test");
        positionsRepository.save(positionsEntity);
        PositionsEntity positionsEntityTest = positionsRepository.findById(positionsEntity.getId()).orElseThrow(null);
        assertNotNull(positionsEntityTest);
        assertEquals(positionsEntity.getPosition(), positionsEntityTest.getPosition());
    }

    @Test
    public void deletePositionsTest() {
        PositionsEntity positionsEntity = new PositionsEntity(null, "официант", 10000, null);

        PositionsEntity positionsEntityTest = positionsRepository.save(positionsEntity);
        positionsRepository.deleteById(positionsEntity.getId());
        Optional<PositionsEntity> positionsEntityTesting = positionsRepository.findById(positionsEntity.getId());
        assertNotNull(positionsEntityTest);
        assertEquals("success", (positionsEntityTesting.isPresent()) ? "not success" : "success");
    }
}