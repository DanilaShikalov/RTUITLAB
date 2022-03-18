package itlab.demo.dao;

import itlab.demo.RestaurantApp;
import itlab.demo.model.entity.OrdersEntity;
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
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RestaurantApp.class)
@Transactional
class OrdersRepositoryTest {
    @Autowired
    private OrdersRepository ordersRepository;
    @Autowired
    private DataSource dataSource;

    @PostConstruct
    public void initData() throws SQLException {

        var populator = new CompositeDatabasePopulator();
        populator.addPopulators(new ResourceDatabasePopulator(new ClassPathResource("itlab/demo/dao/dataOrders.sql")));

        populator.populate(dataSource.getConnection());
    }

    @Test
    public void getOrdersTest() {

        OrdersEntity ordersEntity = new OrdersEntity(null, 1L, null, true, null, null);

        ordersRepository.save(ordersEntity);

        OrdersEntity foundEntity = ordersRepository.findById(ordersEntity.getId()).orElse(null);
        assertNotNull(foundEntity);
        assertEquals(ordersEntity.isStatus(), foundEntity.isStatus());
    }

    @Test
    public void postOrdersTest() {
        OrdersEntity ordersEntity = new OrdersEntity(null, 1L, null, true, null, null);

        ordersRepository.save(ordersEntity);

        OrdersEntity ordersEntityTest = ordersRepository.save(ordersEntity);
        ordersEntityTest.setId(ordersEntity.getId());
        assertNotNull(ordersEntityTest);
        assertThat(ordersEntity).isEqualTo(ordersEntityTest);
    }

    @Test
    public void putOrdersTest() {
        OrdersEntity ordersEntity = new OrdersEntity(null, 1L, null, true, null, null);

        ordersRepository.save(ordersEntity);
        ordersEntity.setStatus(false);
        ordersRepository.save(ordersEntity);
        OrdersEntity ordersEntityTest = ordersRepository.findById(ordersEntity.getId()).orElseThrow(null);
        assertNotNull(ordersEntityTest);
        assertEquals(ordersEntity.isStatus(), ordersEntityTest.isStatus());
    }

    @Test
    public void deleteOrdersTest() {
        OrdersEntity ordersEntity = new OrdersEntity(null, 1L, null, true, null, null);

        OrdersEntity ordersEntityTest = ordersRepository.save(ordersEntity);
        ordersRepository.deleteById(ordersEntity.getId());
        Optional<OrdersEntity> ordersEntityTesting = ordersRepository.findById(ordersEntity.getId());
        assertNotNull(ordersEntityTest);
        assertEquals("success", (ordersEntityTesting.isPresent()) ? "not success" : "success");
    }
}