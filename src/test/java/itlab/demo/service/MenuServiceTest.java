package itlab.demo.service;

import itlab.demo.dao.MenuRepository;
import itlab.demo.dao.UnitRepository;
import itlab.demo.mapper.MenuMapper;
import itlab.demo.model.dto.menu.PostMenuDTO;
import itlab.demo.model.entity.MenuEntity;
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
class MenuServiceTest {
    @Mock
    private MenuRepository menuRepository;
    @Mock
    private MenuMapper menuMapper;
    private AutoCloseable autoCloseable;
    private MenuService underTest;
    @Mock
    private MenuEntity menuEntity;
    @Mock
    private UnitRepository unitRepository;
    @Mock
    private UnitEntity unitEntity;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new MenuService(menuRepository, menuMapper, unitRepository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void canCreateMenu() {
        // given
        PostMenuDTO postMenuDTO = new PostMenuDTO(menuEntity.getId_unit(), "Пиво", 250, 500);
        MenuEntity menu = menuMapper.dtoToEntity(postMenuDTO);
        given(unitRepository.findById(menuEntity.getId_unit())).willReturn(Optional.of(unitEntity));
        //when
        underTest.createMenu(postMenuDTO);

        //then
        ArgumentCaptor<MenuEntity> menuEntityArgumentCaptor =
                ArgumentCaptor.forClass(MenuEntity.class);
        verify(menuRepository).save(menuEntityArgumentCaptor.capture());

        MenuEntity menuEntity = menuEntityArgumentCaptor.getValue();
        assertThat(menuEntity).isEqualTo(menu);
    }

    @Test
    void getMenu() {
        // given
        Long id = menuEntity.getId();
        given(menuRepository.findById(id)).willReturn(Optional.of(menuEntity));
        //when
        underTest.getMenu(id);

        //then
        ArgumentCaptor<Long> longArgumentCaptor =
                ArgumentCaptor.forClass(Long.class);
        verify(menuRepository).findById(longArgumentCaptor.capture());
        Long capturedId = longArgumentCaptor.getValue();
        assertThat(capturedId).isEqualTo(id);
    }

    @Test
    void canGetAllMenu() {
        underTest.getAllMenu();

        verify(menuRepository).findAll();
    }

    @Test
    void deleteMenu() {
        // given
        Long id = menuEntity.getId();
        given(menuRepository.findById(id)).willReturn(Optional.of(menuEntity));
        // when
        underTest.deleteMenu(id);

        // then
        ArgumentCaptor<Long> longArgumentCaptor =
                ArgumentCaptor.forClass(Long.class);
        verify(menuRepository).deleteById(longArgumentCaptor.capture());
        Long capturedId = longArgumentCaptor.getValue();
        assertThat(capturedId).isEqualTo(id);
    }
}