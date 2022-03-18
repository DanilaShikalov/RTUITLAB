package itlab.done.dao;

import itlab.done.ProducerApp;
import itlab.done.model.entity.HistoryEntity;
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
class HistoryRepositoryTest {
    @Autowired
    private HistoryRepository historyRepository;
    @Autowired
    private DataSource dataSource;

    @PostConstruct
    public void initData() throws SQLException {
        var populator = new CompositeDatabasePopulator();
        populator.addPopulators(new ResourceDatabasePopulator(new ClassPathResource("itlab/done/dao/dataHistory.sql")));

        populator.populate(dataSource.getConnection());
    }

    @Test
    public void getHistoryTest() {
        HistoryEntity historyEntity = new HistoryEntity(null, 1L, 5, null);
        historyRepository.save(historyEntity);
        HistoryEntity foundEntity = historyRepository.findById(historyEntity.getId()).orElse(null);
        assertNotNull(foundEntity);
        assertEquals(historyEntity.getAmount(), foundEntity.getAmount());
    }

    @Test
    public void postHistoryTest() {
        HistoryEntity historyEntity = new HistoryEntity(null, 1L, 5, null);
        HistoryEntity historyEntityTest = historyRepository.save(historyEntity);
        assertNotNull(historyEntityTest);
        assertEquals(historyEntity.getAmount(), historyEntityTest.getAmount());
    }

    @Test
    public void putHistoryTest() {
        HistoryEntity historyEntity = new HistoryEntity(null, 1L, 5, null);
        historyRepository.save(historyEntity);
        historyEntity.setAmount(10);
        historyRepository.save(historyEntity);
        HistoryEntity historyEntityTest = historyRepository.findById(historyEntity.getId()).orElseThrow(null);
        assertNotNull(historyEntityTest);
        assertEquals(historyEntity.getAmount(), historyEntityTest.getAmount());
    }

    @Test
    public void deleteHistoryTest() {
        HistoryEntity deleteEntity = new HistoryEntity(null, 1L, 5, null);
        HistoryEntity historyEntity = historyRepository.save(deleteEntity);
        historyRepository.deleteById(deleteEntity.getId());
        Optional<HistoryEntity> historyEntityTest = historyRepository.findById(historyEntity.getId());
        assertNotNull(historyEntityTest);
        assertEquals("success", (historyEntityTest.isPresent()) ? "not success" : "success");
    }
}