package itlab.demo.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import itlab.demo.model.DemoResponse;
import itlab.demo.model.dto.warehouse.*;
import itlab.demo.service.WarehouseService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/warehouse")
@Api(tags = "Контроллер склада продуктов")
@AllArgsConstructor
public class WarehouseController {
    private final WarehouseService warehouseService;
    @PostMapping
    @ApiOperation(value = "Запись заявок",
            notes = "Запись запроса в бд и получение созданного объекта")
    public DemoResponse<PostedWarehouseDTO> createRequest(@RequestBody PostWarehouseDTO postWarehouseDTO) {
        PostedWarehouseDTO request = warehouseService.createWarehouse(postWarehouseDTO);
        return DemoResponse.ok(request);
    }

    @GetMapping("{id}")
    @ApiOperation(value = "Получение заявки",
            notes = "Получение данных запроса по идентификатору")
    public DemoResponse<GetWarehouseDTO> getRequest(@ApiParam("Идентификатор запроса") @PathVariable Long id) {
        return DemoResponse.ok(warehouseService.getWarehouse(id));
    }

    @GetMapping
    @ApiOperation(value = "Получение заявки",
            notes = "Получение данных запроса по идентификатору")
    public DemoResponse<List<GetWarehouseDTO>> getAllRequest() {
        return DemoResponse.ok(warehouseService.getAllWarehouse());
    }

    @DeleteMapping("{id}")
    @ApiOperation(value = "Удаление заявки", notes = "Удаление данных происходит по идентификатору")
    public DemoResponse<Void> deleteRequest(@ApiParam("Индентификатор запроса") @PathVariable Long id)
    {
        warehouseService.deleteWarehouse(id);
        return DemoResponse.ok();
    }

    @PutMapping("{id}")
    @ApiOperation(value = "Изменение заявки", notes = "Изменение данных происходит по идентификатору и телу запроса")
    public DemoResponse<PuttedWarehouseDTO> putRequest(@RequestBody PutWarehouseDTO putWarehouseDTO, @ApiParam("Индентификатор запроса") @PathVariable Long id)
    {
        return DemoResponse.ok(warehouseService.putWarehouse(putWarehouseDTO, id));
    }

    @GetMapping("/low")
    @ApiOperation(value = "", notes = "")
    public List<GetLowWarehouseDTO> getLowWarehouse()
    {
        return warehouseService.getLowWarehouse();
    }
}
