package itlab.demo.service;

import itlab.demo.dao.UnitRepository;
import itlab.demo.mapper.UnitMapper;
import itlab.demo.model.dto.unit.PostUnitDTO;
import itlab.demo.model.entity.UnitEntity;
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
class UnitServiceTest {

    @Mock
    private UnitRepository unitRepository;
    @Mock
    private UnitMapper unitMapper;
    private AutoCloseable autoCloseable;
    private UnitService underTest;
    @Mock
    private UnitEntity unitEntity;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new UnitService(unitRepository, unitMapper);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void canCreateUnit() {
        // given
        PostUnitDTO postUnitDTO = new PostUnitDTO("Литр");
        UnitEntity unit = unitMapper.dtoToEntity(postUnitDTO);
        //when
        underTest.createUnit(postUnitDTO);

        //then
        ArgumentCaptor<UnitEntity> unitEntityArgumentCaptor =
                    ArgumentCaptor.forClass(UnitEntity.class);
        verify(unitRepository).save(unitEntityArgumentCaptor.capture());

        UnitEntity unitEntity = unitEntityArgumentCaptor.getValue();
        assertThat(unitEntity).isEqualTo(unit);
    }

    @Test
    void getUnit() {
        // given
        Long id = unitEntity.getId();
        given(unitRepository.findById(id)).willReturn(Optional.of(unitEntity));
        //when
        underTest.getUnit(id);

        //then
        ArgumentCaptor<Long> longArgumentCaptor =
                ArgumentCaptor.forClass(Long.class);
        verify(unitRepository).findById(longArgumentCaptor.capture());
        Long capturedId = longArgumentCaptor.getValue();
        assertThat(capturedId).isEqualTo(id);
    }

    @Test
    void canGetAllUnit() {
        underTest.getAllUnit();

        verify(unitRepository).findAll();
    }

    @Test
    void deleteUnit() {
        // given
        Long id = unitEntity.getId();
        given(unitRepository.findById(id)).willReturn(Optional.of(unitEntity));
        // when
        underTest.deleteUnit(id);

        // then
        ArgumentCaptor<Long> longArgumentCaptor =
                ArgumentCaptor.forClass(Long.class);
        verify(unitRepository).deleteById(longArgumentCaptor.capture());
        Long capturedId = longArgumentCaptor.getValue();
        assertThat(capturedId).isEqualTo(id);
    }
}