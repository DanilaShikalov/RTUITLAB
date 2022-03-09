package itlab.demo.service;

import itlab.demo.dao.UnitRepository;
import itlab.demo.dao.WarehouseRepository;
import itlab.demo.mapper.WarehouseMapper;
import itlab.demo.model.dto.warehouse.PostWarehouseDTO;
import itlab.demo.model.entity.UnitEntity;
import itlab.demo.model.entity.WarehouseEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class WarehouseServiceTest {
    @Mock
    private WarehouseRepository warehouseRepository;
    @Mock
    private WarehouseMapper warehouseMapper;
    private AutoCloseable autoCloseable;
    private WarehouseService underTest;
    @Mock
    private WarehouseEntity warehouseEntity;
    @Mock
    private UnitRepository unitRepository;
    @Mock
    private UnitEntity unitEntity;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new WarehouseService(warehouseRepository, warehouseMapper, unitRepository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void canCreateWarehouse() {
        // given
        PostWarehouseDTO postWarehouseDTO = new PostWarehouseDTO(warehouseEntity.getId_unit(), "Литр", 123, 123, 123);
        WarehouseEntity warehouse = warehouseMapper.dtoToEntity(postWarehouseDTO);
        Long id = warehouseEntity.getId_unit();
        given(unitRepository.findById(id)).willReturn(Optional.of(unitEntity));
        //when
        underTest.createWarehouse(postWarehouseDTO);

        //then
        ArgumentCaptor<WarehouseEntity> warehouseEntityArgumentCaptor =
                ArgumentCaptor.forClass(WarehouseEntity.class);
        verify(warehouseRepository).save(warehouseEntityArgumentCaptor.capture());

        WarehouseEntity warehouseEntity = warehouseEntityArgumentCaptor.getValue();
        assertThat(warehouseEntity).isEqualTo(warehouse);
    }

    @Test
    void getWarehouse() {
        // given
        Long id = warehouseEntity.getId();
        given(warehouseRepository.findById(id)).willReturn(Optional.of(warehouseEntity));
        //when
        underTest.getWarehouse(id);

        //then
        ArgumentCaptor<Long> longArgumentCaptor =
                ArgumentCaptor.forClass(Long.class);
        verify(warehouseRepository).findById(longArgumentCaptor.capture());
        Long capturedId = longArgumentCaptor.getValue();
        assertThat(capturedId).isEqualTo(id);
    }

    @Test
    void canGetAllWarehouse() {
        underTest.getAllWarehouse();

        verify(warehouseRepository).findAll();
    }

    @Test
    void deleteWarehouse() {
        // given
        Long id = warehouseEntity.getId();
        given(warehouseRepository.findById(id)).willReturn(Optional.of(warehouseEntity));
        // when
        underTest.deleteWarehouse(id);

        // then
        ArgumentCaptor<Long> longArgumentCaptor =
                ArgumentCaptor.forClass(Long.class);
        verify(warehouseRepository).deleteById(longArgumentCaptor.capture());
        Long capturedId = longArgumentCaptor.getValue();
        assertThat(capturedId).isEqualTo(id);
    }
}