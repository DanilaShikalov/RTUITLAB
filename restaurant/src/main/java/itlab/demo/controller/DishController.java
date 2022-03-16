package itlab.demo.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import itlab.demo.model.DemoResponse;
import itlab.demo.model.dto.dish.*;
import itlab.demo.service.DishService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/dish")
@Api(tags = "Контроллер блюд в заказе")
@AllArgsConstructor
public class DishController {
    private final DishService dishService;

    @PostMapping
    @ApiOperation(value = "Запись заявок",
            notes = "Запись запроса в бд и получение созданного объекта")
    public DemoResponse<PostedDishDTO> createRequest(@RequestBody PostDishDTO postDishDTO) {
        PostedDishDTO request = dishService.createDish(postDishDTO);
        return DemoResponse.ok(request);
    }

    @GetMapping("{id}")
    @ApiOperation(value = "Получение заявки",
            notes = "Получение данных запроса по идентификатору")
    public DemoResponse<GetDishDTO> getRequest(@ApiParam("Идентификатор запроса") @PathVariable Long id) {
        return DemoResponse.ok(dishService.getDish(id));
    }

    @GetMapping
    @ApiOperation(value = "Получение заявки",
            notes = "Получение данных запроса по идентификатору")
    public DemoResponse<List<GetDishDTO>> getAllRequest() {
        return DemoResponse.ok(dishService.getAllDish());
    }

    @DeleteMapping("{id}")
    @ApiOperation(value = "Удаление заявки", notes = "Удаление данных происходит по идентификатору")
    public DemoResponse<Void> deleteRequest(@ApiParam("Индентификатор запроса") @PathVariable Long id)
    {
        dishService.deleteDish(id);
        return DemoResponse.ok();
    }

    @PutMapping("{id}")
    @ApiOperation(value = "Изменение заявки", notes = "Изменение данных происходит по идентификатору и телу запроса")
    public DemoResponse<PuttedDishDTO> putRequest(@RequestBody PutDishDTO putDishDTO, @ApiParam("Индентификатор запроса") @PathVariable Long id)
    {
        return DemoResponse.ok(dishService.putDish(putDishDTO, id));
    }
}
