package itlab.demo.service;

import itlab.demo.dao.EmployeesRepository;
import itlab.demo.dao.PositionsRepository;
import itlab.demo.mapper.EmployeesMapper;
import itlab.demo.model.dto.employees.PostEmployeesDTO;
import itlab.demo.model.entity.EmployeesEntity;
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
class EmployeesServiceTest {
    @Mock
    private EmployeesRepository employeesRepository;
    @Mock
    private EmployeesMapper employeesMapper;
    private AutoCloseable autoCloseable;
    private EmployeesService underTest;
    @Mock
    private EmployeesEntity employeesEntity;
    @Mock
    private PositionsRepository positionsRepository;
    @Mock
    private PositionsEntity positionsEntity;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new EmployeesService(employeesRepository, employeesMapper, positionsRepository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void canCreateEmployees() {
        // given
        PostEmployeesDTO postEmployeesDTO = new PostEmployeesDTO(employeesEntity.getId_position(), "Vanya", "Popov", "Mixa", "88005553535");
        EmployeesEntity employees = employeesMapper.dtoToEntity(postEmployeesDTO);
        given(positionsRepository.findById(employeesEntity.getId_position())).willReturn(Optional.of(positionsEntity));
        //when
        underTest.createEmployees(postEmployeesDTO);

        //then
        ArgumentCaptor<EmployeesEntity> employeesEntityArgumentCaptor =
                ArgumentCaptor.forClass(EmployeesEntity.class);
        verify(employeesRepository).save(employeesEntityArgumentCaptor.capture());

        EmployeesEntity employeesEntity = employeesEntityArgumentCaptor.getValue();
        assertThat(employeesEntity).isEqualTo(employees);
    }

    @Test
    void getEmployees() {
        // given
        Long id = employeesEntity.getId();
        given(employeesRepository.findById(id)).willReturn(Optional.of(employeesEntity));
        //when
        underTest.getEmployees(id);

        //then
        ArgumentCaptor<Long> longArgumentCaptor =
                ArgumentCaptor.forClass(Long.class);
        verify(employeesRepository).findById(longArgumentCaptor.capture());
        Long capturedId = longArgumentCaptor.getValue();
        assertThat(capturedId).isEqualTo(id);
    }

    @Test
    void canGetAllEmployees() {
        underTest.getAllEmployees();

        verify(employeesRepository).findAll();
    }

    @Test
    void deleteEmployees() {
        // given
        Long id = employeesEntity.getId();
        given(employeesRepository.findById(id)).willReturn(Optional.of(employeesEntity));
        // when
        underTest.deleteEmployees(id);

        // then
        ArgumentCaptor<Long> longArgumentCaptor =
                ArgumentCaptor.forClass(Long.class);
        verify(employeesRepository).deleteById(longArgumentCaptor.capture());
        Long capturedId = longArgumentCaptor.getValue();
        assertThat(capturedId).isEqualTo(id);
    }
}