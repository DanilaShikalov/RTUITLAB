package itlab.demo.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import itlab.demo.model.DemoResponse;
import itlab.demo.model.dto.ingredient.*;
import itlab.demo.service.IngredientService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/ingredient")
@Api(tags = "Контроллер ингредиентов блюд")
@AllArgsConstructor
public class IngredientController {
    private final IngredientService ingredientService;

    @PostMapping
    @ApiOperation(value = "Запись заявок",
            notes = "Запись запроса в бд и получение созданного объекта")
    public DemoResponse<PostedIngredientDTO> createRequest(@RequestBody PostIngredientDTO postIngredientDTO) {
        PostedIngredientDTO request = ingredientService.createIngredient(postIngredientDTO);
        return DemoResponse.ok(request);
    }

    @GetMapping("{id}")
    @ApiOperation(value = "Получение заявки",
            notes = "Получение данных запроса по идентификатору")
    public DemoResponse<GetIngredientDTO> getRequest(@ApiParam("Идентификатор запроса") @PathVariable Long id) {
        return DemoResponse.ok(ingredientService.getIngredient(id));
    }

    @GetMapping
    @ApiOperation(value = "Получение заявки",
            notes = "Получение данных запроса по идентификатору")
    public DemoResponse<List<GetIngredientDTO>> getAllRequest() {
        return DemoResponse.ok(ingredientService.getAllIngredient());
    }

    @DeleteMapping("{id}")
    @ApiOperation(value = "Удаление заявки", notes = "Удаление данных происходит по идентификатору")
    public DemoResponse<Void> deleteRequest(@ApiParam("Индентификатор запроса") @PathVariable Long id)
    {
        ingredientService.deleteIngredient(id);
        return DemoResponse.ok();
    }

    @PutMapping("{id}")
    @ApiOperation(value = "Изменение заявки", notes = "Изменение данных происходит по идентификатору и телу запроса")
    public DemoResponse<PuttedIngredientDTO> putRequest(@RequestBody PutIngredientDTO putIngredientDTO, @ApiParam("Индентификатор запроса") @PathVariable Long id)
    {
        return DemoResponse.ok(ingredientService.putIngredient(putIngredientDTO, id));
    }
}
