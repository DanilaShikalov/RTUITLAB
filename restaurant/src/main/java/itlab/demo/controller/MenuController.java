package itlab.demo.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import itlab.demo.model.DemoResponse;
import itlab.demo.model.dto.menu.*;
import itlab.demo.service.MenuService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/menu")
@Api(tags = "Контроллер меню")
@AllArgsConstructor
public class MenuController {
    private MenuService menuService;

    @PostMapping
    @ApiOperation(value = "Запись заявок",
            notes = "Запись запроса в бд и получение созданного объекта")
    public DemoResponse<PostedMenuDTO> createRequest(@RequestBody PostMenuDTO postMenuDTO) {
        PostedMenuDTO request = menuService.createMenu(postMenuDTO);
        return DemoResponse.ok(request);
    }

    @GetMapping("{id}")
    @ApiOperation(value = "Получение заявки",
            notes = "Получение данных запроса по идентификатору")
    public DemoResponse<GetMenuDTO> getRequest(@ApiParam("Идентификатор запроса") @PathVariable Long id) {
        return DemoResponse.ok(menuService.getMenu(id));
    }

    @GetMapping
    @ApiOperation(value = "Получение заявки",
            notes = "Получение данных запроса по идентификатору")
    public DemoResponse<List<GetMenuDTO>> getAllRequest() {
        return DemoResponse.ok(menuService.getAllMenu());
    }

    @DeleteMapping("{id}")
    @ApiOperation(value = "Удаление заявки", notes = "Удаление данных происходит по идентификатору")
    public DemoResponse<Void> deleteRequest(@ApiParam("Индентификатор запроса") @PathVariable Long id)
    {
        menuService.deleteMenu(id);
        return DemoResponse.ok();
    }

    @PutMapping("{id}")
    @ApiOperation(value = "Изменение заявки", notes = "Изменение данных происходит по идентификатору и телу запроса")
    public DemoResponse<PuttedMenuDTO> putRequest(@RequestBody PutMenuDTO putMenuDTO, @ApiParam("Индентификатор запроса") @PathVariable Long id)
    {
        return DemoResponse.ok(menuService.putMenu(putMenuDTO, id));
    }
}
