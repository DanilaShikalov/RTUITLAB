package itlab.demo.service;

import itlab.demo.dao.EmployeesRepository;
import itlab.demo.dao.OrdersRepository;
import itlab.demo.exception.EmployeesNotFoundException;
import itlab.demo.exception.OrdersNotFoundException;
import itlab.demo.mapper.OrdersMapper;
import itlab.demo.model.dto.orders.*;
import itlab.demo.model.entity.OrdersEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OrdersService {
    private final OrdersRepository ordersRepository;
    private final OrdersMapper ordersMapper;
    private final EmployeesRepository employeesRepository;

    public PostedOrdersDTO createOrders(PostOrdersDTO postOrdersDTO) {
        employeesRepository.findById(postOrdersDTO.getId_employees()).orElseThrow(() -> new EmployeesNotFoundException(postOrdersDTO.getId_employees()));
        OrdersEntity entity = ordersMapper.dtoToEntity(postOrdersDTO);
        OrdersEntity save = ordersRepository.save(entity);
        return ordersMapper.entityToCreatedDTO(save);
    }

    public GetOrdersDTO getOrders(Long id) {
        OrdersEntity ordersEntity = ordersRepository.findById(id).orElseThrow(() -> new OrdersNotFoundException(id));
        return ordersMapper.entityToDTO(ordersEntity);
    }

    public List<GetOrdersDTO> getAllOrders()
    {
        List<OrdersEntity> list = ordersRepository.findAll();
        return ordersMapper.listEntityToListDTO(list);
    }

    public void deleteOrders(Long id)
    {
        ordersRepository.findById(id).orElseThrow(() -> new OrdersNotFoundException(id));
        ordersRepository.deleteById(id);
    }

    public PuttedOrdersDTO putOrders(PutOrdersDTO putOrdersDTO, Long id)
    {
        employeesRepository.findById(putOrdersDTO.getId_employees()).orElseThrow(() -> new EmployeesNotFoundException(putOrdersDTO.getId_employees()));
        ordersRepository.findById(id).orElseThrow(() -> new OrdersNotFoundException(id));
        OrdersEntity put = ordersMapper.putDTOToEntity(putOrdersDTO);
        put.setId(id);
        ordersRepository.save(put);
        return ordersMapper.entityToPutDTO(put);
    }
}
