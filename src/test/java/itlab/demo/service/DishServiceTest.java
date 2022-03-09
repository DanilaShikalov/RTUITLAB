package itlab.demo.service;

import itlab.demo.dao.DishRepository;
import itlab.demo.dao.MenuRepository;
import itlab.demo.dao.OrdersRepository;
import itlab.demo.dao.WarehouseRepository;
import itlab.demo.mapper.DishMapper;
import itlab.demo.model.dto.dish.PostDishDTO;
import itlab.demo.model.entity.DishEntity;
import itlab.demo.model.entity.MenuEntity;
import itlab.demo.model.entity.OrdersEntity;
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
class DishServiceTest {
    @Mock
    private DishRepository dishRepository;
    @Mock
    private DishMapper dishMapper;
    private AutoCloseable autoCloseable;
    private DishService underTest;
    @Mock
    private DishEntity dishEntity;
    @Mock
    private MenuRepository menuRepository;
    @Mock
    private OrdersRepository ordersRepository;
    @Mock
    private WarehouseRepository warehouseRepository;
    @Mock
    private MenuEntity menuEntity;
    @Mock
    private OrdersEntity ordersEntity;
    @Mock
    private WarehouseEntity warehouseEntity;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new DishService(dishRepository, dishMapper, menuRepository, ordersRepository, warehouseRepository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void canCreateDish() {
        // given
        PostDishDTO postDishDTO = new PostDishDTO(dishEntity.getId_orders(), dishEntity.getId_menu());
        DishEntity dish = dishMapper.dtoToEntity(postDishDTO);
        given(menuRepository.findById(dishEntity.getId_menu())).willReturn(Optional.of(menuEntity));
        given(ordersRepository.findById(dishEntity.getId_orders())).willReturn(Optional.of(ordersEntity));
        //when
        underTest.createDish(postDishDTO);

        //then
        ArgumentCaptor<DishEntity> dishEntityArgumentCaptor =
                ArgumentCaptor.forClass(DishEntity.class);
        verify(dishRepository).save(dishEntityArgumentCaptor.capture());

        DishEntity dishEntity = dishEntityArgumentCaptor.getValue();
        assertThat(dishEntity).isEqualTo(dish);
    }

    @Test
    void getDish() {
        // given
        Long id = dishEntity.getId();
        given(dishRepository.findById(id)).willReturn(Optional.of(dishEntity));
        //when
        underTest.getDish(id);

        //then
        ArgumentCaptor<Long> longArgumentCaptor =
                ArgumentCaptor.forClass(Long.class);
        verify(dishRepository).findById(longArgumentCaptor.capture());
        Long capturedId = longArgumentCaptor.getValue();
        assertThat(capturedId).isEqualTo(id);
    }

    @Test
    void canGetAllDish() {
        underTest.getAllDish();

        verify(dishRepository).findAll();
    }
}