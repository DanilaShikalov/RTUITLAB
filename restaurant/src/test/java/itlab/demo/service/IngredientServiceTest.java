package itlab.demo.service;

import itlab.demo.dao.IngredientRepository;
import itlab.demo.dao.MenuRepository;
import itlab.demo.dao.WarehouseRepository;
import itlab.demo.mapper.IngredientMapper;
import itlab.demo.model.dto.ingredient.PostIngredientDTO;
import itlab.demo.model.entity.IngredientEntity;
import itlab.demo.model.entity.MenuEntity;
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
class IngredientServiceTest {
    @Mock
    private IngredientRepository ingredientRepository;
    @Mock
    private IngredientMapper ingredientMapper;
    private AutoCloseable autoCloseable;
    private IngredientService underTest;
    @Mock
    private IngredientEntity ingredientEntity;
    @Mock
    private MenuRepository menuRepository;
    @Mock
    private WarehouseRepository warehouseRepository;
    @Mock
    private MenuEntity menuEntity;
    @Mock
    private WarehouseEntity warehouseEntity;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new IngredientService(ingredientRepository, ingredientMapper, menuRepository, warehouseRepository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void canCreateIngredient() {
        // given
        PostIngredientDTO postIngredientDTO = new PostIngredientDTO(ingredientEntity.getId_menu(), ingredientEntity.getId_warehouse());
        IngredientEntity ingredient = ingredientMapper.dtoToEntity(postIngredientDTO);
        given(menuRepository.findById(ingredientEntity.getId_menu())).willReturn(Optional.of(menuEntity));
        given(warehouseRepository.findById(ingredientEntity.getId_warehouse())).willReturn(Optional.of(warehouseEntity));
        //when
        underTest.createIngredient(postIngredientDTO);

        //then
        ArgumentCaptor<IngredientEntity> ingredientEntityArgumentCaptor =
                ArgumentCaptor.forClass(IngredientEntity.class);
        verify(ingredientRepository).save(ingredientEntityArgumentCaptor.capture());

        IngredientEntity ingredientEntity = ingredientEntityArgumentCaptor.getValue();
        assertThat(ingredientEntity).isEqualTo(ingredient);
    }

    @Test
    void getIngredient() {
        // given
        Long id = ingredientEntity.getId();
        given(ingredientRepository.findById(id)).willReturn(Optional.of(ingredientEntity));
        //when
        underTest.getIngredient(id);

        //then
        ArgumentCaptor<Long> longArgumentCaptor =
                ArgumentCaptor.forClass(Long.class);
        verify(ingredientRepository).findById(longArgumentCaptor.capture());
        Long capturedId = longArgumentCaptor.getValue();
        assertThat(capturedId).isEqualTo(id);
    }

    @Test
    void canGetAllIngredient() {
        underTest.getAllIngredient();

        verify(ingredientRepository).findAll();
    }

    @Test
    void deleteIngredient() {
        // given
        Long id = ingredientEntity.getId();
        given(ingredientRepository.findById(id)).willReturn(Optional.of(ingredientEntity));
        // when
        underTest.deleteIngredient(id);

        // then
        ArgumentCaptor<Long> longArgumentCaptor =
                ArgumentCaptor.forClass(Long.class);
        verify(ingredientRepository).deleteById(longArgumentCaptor.capture());
        Long capturedId = longArgumentCaptor.getValue();
        assertThat(capturedId).isEqualTo(id);
    }
}