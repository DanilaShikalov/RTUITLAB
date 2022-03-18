package itlab.demo.dao;

import itlab.demo.RestaurantApp;
import itlab.demo.model.entity.UnitEntity;
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
@SpringBootTest(classes = RestaurantApp.class)
@Transactional
class UnitRepositoryTest {
    @Autowired
    private UnitRepository unitRepository;
    @Autowired
    private DataSource dataSource;

    @PostConstruct
    public void initData() throws SQLException {
        var populator = new CompositeDatabasePopulator();
        populator.addPopulators(new ResourceDatabasePopulator(new ClassPathResource("itlab/demo/dao/dataUnit.sql")));

        populator.populate(dataSource.getConnection());
    }

    @Test
    public void getUnitTest() {
        UnitEntity unitEntity = new UnitEntity(null,"test");
        unitRepository.save(unitEntity);
        UnitEntity foundEntity = unitRepository.findById(unitEntity.getId()).orElse(null);
        assertNotNull(foundEntity);
        assertEquals(unitEntity.getUnit(), foundEntity.getUnit());
    }

    @Test
    public void postUnitTest() {
        UnitEntity unitEntity = new UnitEntity(null,"test");
        UnitEntity unitEntityTest = unitRepository.save(unitEntity);
        assertNotNull(unitEntityTest);
        assertEquals(unitEntity.getUnit(), unitEntityTest.getUnit());
    }

    @Test
    public void putUnitTest() {
        UnitEntity unitEntity = new UnitEntity(null,"pivo");
        unitRepository.save(unitEntity);
        unitEntity.setUnit("test");
        unitRepository.save(unitEntity);
        UnitEntity unitEntityTest = unitRepository.findById(unitEntity.getId()).orElseThrow(null);
        assertNotNull(unitEntityTest);
        assertEquals(unitEntity.getUnit(), unitEntityTest.getUnit());
    }

    @Test
    public void deleteUnitTest() {
        UnitEntity deleteEntity = new UnitEntity(null,"delete");
        UnitEntity unitEntity = unitRepository.save(deleteEntity);
        unitRepository.deleteById(deleteEntity.getId());
        Optional<UnitEntity> unitEntityTest = unitRepository.findById(unitEntity.getId());
        assertNotNull(unitEntityTest);
        assertEquals("success", (unitEntityTest.isPresent()) ? "not success" : "success");
    }
}