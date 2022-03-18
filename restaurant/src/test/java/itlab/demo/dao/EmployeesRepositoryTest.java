package itlab.demo.dao;

import itlab.demo.RestaurantApp;
import itlab.demo.model.entity.EmployeesEntity;
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
class EmployeesRepositoryTest {
    @Autowired
    private EmployeesRepository employeesRepository;
    @Autowired
    private DataSource dataSource;

    @PostConstruct
    public void initData() throws SQLException {

        var populator = new CompositeDatabasePopulator();
        populator.addPopulators(new ResourceDatabasePopulator(new ClassPathResource("itlab/demo/dao/dataEmployees.sql")));

        populator.populate(dataSource.getConnection());
    }

    @Test
    public void getEmployeesTest() {

        EmployeesEntity employeesEntity = new EmployeesEntity(null, 1L, "Danila", "Shikalov", "Denisovich", "88005553535", null, null);

        employeesRepository.save(employeesEntity);

        EmployeesEntity foundEntity = employeesRepository.findById(employeesEntity.getId()).orElse(null);
        assertNotNull(foundEntity);
        assertEquals(employeesEntity.getName(), foundEntity.getName());
    }

    @Test
    public void postEmployeesTest() {
        EmployeesEntity employeesEntity = new EmployeesEntity(null, 1L, "Danila", "Shikalov", "Denisovich", "88005553535", null, null);

        employeesRepository.save(employeesEntity);

        EmployeesEntity employeesEntityTest = employeesRepository.save(employeesEntity);
        employeesEntityTest.setId(employeesEntity.getId());
        assertNotNull(employeesEntityTest);
        assertThat(employeesEntity).isEqualTo(employeesEntityTest);
    }

    @Test
    public void putEmployeesTest() {
        EmployeesEntity employeesEntity = new EmployeesEntity(null, 1L, "Danila", "Shikalov", "Denisovich", "88005553535", null, null);

        employeesRepository.save(employeesEntity);
        employeesEntity.setName("August");
        employeesRepository.save(employeesEntity);
        EmployeesEntity employeesEntityTest = employeesRepository.findById(employeesEntity.getId()).orElseThrow(null);
        assertNotNull(employeesEntityTest);
        assertEquals(employeesEntity.getName(), employeesEntityTest.getName());
    }

    @Test
    public void deleteEmployeesTest() {
        EmployeesEntity employeesEntity = new EmployeesEntity(null, 1L, "Danila", "Shikalov", "Denisovich", "88005553535", null, null);

        EmployeesEntity employeesEntityTest = employeesRepository.save(employeesEntity);
        employeesRepository.deleteById(employeesEntity.getId());
        Optional<EmployeesEntity> employeesEntityTesting = employeesRepository.findById(employeesEntity.getId());
        assertNotNull(employeesEntityTest);
        assertEquals("success", (employeesEntityTesting.isPresent()) ? "not success" : "success");
    }

}