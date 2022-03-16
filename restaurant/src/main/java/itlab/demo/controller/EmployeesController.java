package itlab.demo.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import itlab.demo.model.DemoResponse;
import itlab.demo.model.dto.employees.*;
import itlab.demo.service.EmployeesService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/employees")
@Api(tags = "Контроллер сотрудников")
@AllArgsConstructor
public class EmployeesController {
    private final EmployeesService employeesService;

    @PostMapping
    @ApiOperation(value = "Запись заявок",
            notes = "Запись запроса в бд и получение созданного объекта")
    public DemoResponse<PostedEmployeesDTO> createRequest(@RequestBody PostEmployeesDTO postEmployeesDTO) {
        PostedEmployeesDTO request = employeesService.createEmployees(postEmployeesDTO);
        return DemoResponse.ok(request);
    }

    @GetMapping("{id}")
    @ApiOperation(value = "Получение заявки",
            notes = "Получение данных запроса по идентификатору")
    public DemoResponse<GetEmployeesDTO> getRequest(@ApiParam("Идентификатор запроса") @PathVariable Long id) {
        return DemoResponse.ok(employeesService.getEmployees(id));
    }

    @GetMapping
    @ApiOperation(value = "Получение заявки",
            notes = "Получение данных запроса по идентификатору")
    public DemoResponse<List<GetEmployeesDTO>> getAllRequest() {
        return DemoResponse.ok(employeesService.getAllEmployees());
    }

    @DeleteMapping("{id}")
    @ApiOperation(value = "Удаление заявки", notes = "Удаление данных происходит по идентификатору")
    public DemoResponse<Void> deleteRequest(@ApiParam("Индентификатор запроса") @PathVariable Long id)
    {
        employeesService.deleteEmployees(id);
        return DemoResponse.ok();
    }

    @PutMapping("{id}")
    @ApiOperation(value = "Изменение заявки", notes = "Изменение данных происходит по идентификатору и телу запроса")
    public DemoResponse<PuttedEmployeesDTO> putRequest(@RequestBody PutEmployeesDTO putEmployeesDTO, @ApiParam("Индентификатор запроса") @PathVariable Long id)
    {
        return DemoResponse.ok(employeesService.putEmployees(putEmployeesDTO, id));
    }
}
