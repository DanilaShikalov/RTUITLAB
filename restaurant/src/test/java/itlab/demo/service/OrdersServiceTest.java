package itlab.demo.service;

import itlab.demo.dao.EmployeesRepository;
import itlab.demo.dao.OrdersRepository;
import itlab.demo.mapper.OrdersMapper;
import itlab.demo.model.dto.orders.PostOrdersDTO;
import itlab.demo.model.entity.EmployeesEntity;
import itlab.demo.model.entity.OrdersEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Date;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class OrdersServiceTest {
    @Mock
    private OrdersRepository ordersRepository;
    @Mock
    private OrdersMapper ordersMapper;
    private AutoCloseable autoCloseable;
    private OrdersService underTest;
    @Mock
    private OrdersEntity ordersEntity;
    @Mock
    private EmployeesRepository employeesRepository;
    @Mock
    private EmployeesEntity employeesEntity;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new OrdersService(ordersRepository, ordersMapper, employeesRepository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void canCreateOrders() {
        // given
        PostOrdersDTO postOrdersDTO = new PostOrdersDTO(ordersEntity.getId_employees(), Date.valueOf("2020-11-11"), true, Date.valueOf("2020-11-11"));
        OrdersEntity orders = ordersMapper.dtoToEntity(postOrdersDTO);
        given(employeesRepository.findById(ordersEntity.getId_employees())).willReturn(Optional.of(employeesEntity));
        //when
        underTest.createOrders(postOrdersDTO);

        //then
        ArgumentCaptor<OrdersEntity> ordersEntityArgumentCaptor =
                ArgumentCaptor.forClass(OrdersEntity.class);
        verify(ordersRepository).save(ordersEntityArgumentCaptor.capture());

        OrdersEntity ordersEntity = ordersEntityArgumentCaptor.getValue();
        assertThat(ordersEntity).isEqualTo(orders);
    }

    @Test
    void getOrders() {
        // given
        Long id = ordersEntity.getId();
        given(ordersRepository.findById(id)).willReturn(Optional.of(ordersEntity));
        //when
        underTest.getOrders(id);

        //then
        ArgumentCaptor<Long> longArgumentCaptor =
                ArgumentCaptor.forClass(Long.class);
        verify(ordersRepository).findById(longArgumentCaptor.capture());
        Long capturedId = longArgumentCaptor.getValue();
        assertThat(capturedId).isEqualTo(id);
    }

    @Test
    void canGetAllOrders() {
        underTest.getAllOrders();

        verify(ordersRepository).findAll();
    }

    @Test
    void deleteOrders() {
        // given
        Long id = ordersEntity.getId();
        given(ordersRepository.findById(id)).willReturn(Optional.of(ordersEntity));
        // when
        underTest.deleteOrders(id);

        // then
        ArgumentCaptor<Long> longArgumentCaptor =
                ArgumentCaptor.forClass(Long.class);
        verify(ordersRepository).deleteById(longArgumentCaptor.capture());
        Long capturedId = longArgumentCaptor.getValue();
        assertThat(capturedId).isEqualTo(id);
    }

}