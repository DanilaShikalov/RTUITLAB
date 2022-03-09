package itlab.demo.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import itlab.demo.model.DemoResponse;
import itlab.demo.model.dto.positions.*;
import itlab.demo.service.PositionsService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/positions")
@Api(tags = "Контроллер позиций из меню")
@AllArgsConstructor
public class PositionsController {
    private PositionsService positionsService;

    @PostMapping
    @ApiOperation(value = "Запись заявок",
            notes = "Запись запроса в бд и получение созданного объекта")
    public DemoResponse<PostedPositionsDTO> createRequest(@RequestBody PostPositionsDTO postPositionsDTO) {
        PostedPositionsDTO request = positionsService.createPositions(postPositionsDTO);
        return DemoResponse.ok(request);
    }

    @GetMapping("{id}")
    @ApiOperation(value = "Получение заявки",
            notes = "Получение данных запроса по идентификатору")
    public DemoResponse<GetPositionsDTO> getRequest(@ApiParam("Идентификатор запроса") @PathVariable Long id) {
        return DemoResponse.ok(positionsService.getPositions(id));
    }

    @GetMapping
    @ApiOperation(value = "Получение заявки",
            notes = "Получение данных запроса по идентификатору")
    public DemoResponse<List<GetPositionsDTO>> getAllRequest() {
        return DemoResponse.ok(positionsService.getAllPositions());
    }

    @DeleteMapping("{id}")
    @ApiOperation(value = "Удаление заявки", notes = "Удаление данных происходит по идентификатору")
    public DemoResponse<Void> deleteRequest(@ApiParam("Индентификатор запроса") @PathVariable Long id)
    {
        positionsService.deletePositions(id);
        return DemoResponse.ok();
    }

    @PutMapping("{id}")
    @ApiOperation(value = "Изменение заявки", notes = "Изменение данных происходит по идентификатору и телу запроса")
    public DemoResponse<PuttedPositionsDTO> putRequest(@RequestBody PutPositionsDTO putPositionsDTO, @ApiParam("Индентификатор запроса") @PathVariable Long id)
    {
        return DemoResponse.ok(positionsService.putPositions(putPositionsDTO, id));
    }
}
