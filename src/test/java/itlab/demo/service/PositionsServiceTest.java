package itlab.demo.service;

import itlab.demo.dao.PositionsRepository;
import itlab.demo.mapper.PositionsMapper;
import itlab.demo.model.dto.positions.PostPositionsDTO;
import itlab.demo.model.entity.PositionsEntity;
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
class PositionsServiceTest {
    @Mock
    private PositionsRepository positionsRepository;
    @Mock
    private PositionsMapper positionsMapper;
    private AutoCloseable autoCloseable;
    private PositionsService underTest;
    @Mock
    private PositionsEntity positionsEntity;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new PositionsService(positionsRepository, positionsMapper);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void canCreatePositions() {
        // given
        PostPositionsDTO postPositionsDTO = new PostPositionsDTO("Официант", 25000);
        PositionsEntity positions = positionsMapper.dtoToEntity(postPositionsDTO);
        //when
        underTest.createPositions(postPositionsDTO);

        //then
        ArgumentCaptor<PositionsEntity> positionsEntityArgumentCaptor =
                ArgumentCaptor.forClass(PositionsEntity.class);
        verify(positionsRepository).save(positionsEntityArgumentCaptor.capture());

        PositionsEntity positionsEntity = positionsEntityArgumentCaptor.getValue();
        assertThat(positionsEntity).isEqualTo(positions);
    }

    @Test
    void getPositions() {
        // given
        Long id = positionsEntity.getId();
        given(positionsRepository.findById(id)).willReturn(Optional.of(positionsEntity));
        //when
        underTest.getPositions(id);

        //then
        ArgumentCaptor<Long> longArgumentCaptor =
                ArgumentCaptor.forClass(Long.class);
        verify(positionsRepository).findById(longArgumentCaptor.capture());
        Long capturedId = longArgumentCaptor.getValue();
        assertThat(capturedId).isEqualTo(id);
    }

    @Test
    void canGetAllPositions() {
        underTest.getAllPositions();

        verify(positionsRepository).findAll();
    }

    @Test
    void deletePositions() {
        // given
        Long id = positionsEntity.getId();
        given(positionsRepository.findById(id)).willReturn(Optional.of(positionsEntity));
        // when
        underTest.deletePositions(id);

        // then
        ArgumentCaptor<Long> longArgumentCaptor =
                ArgumentCaptor.forClass(Long.class);
        verify(positionsRepository).deleteById(longArgumentCaptor.capture());
        Long capturedId = longArgumentCaptor.getValue();
        assertThat(capturedId).isEqualTo(id);
    }
}