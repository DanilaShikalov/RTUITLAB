package itlab.demo.mapper;

import itlab.demo.model.dto.orders.*;
import itlab.demo.model.entity.OrdersEntity;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface OrdersMapper {
    OrdersEntity dtoToEntity(PostOrdersDTO postClientDTO);

    PostedOrdersDTO entityToCreatedDTO(OrdersEntity ordersEntity);

    OrdersEntity putDTOToEntity(PutOrdersDTO putOrdersDTO);

    PuttedOrdersDTO entityToPutDTO(OrdersEntity ordersEntity);

    GetOrdersDTO entityToDTO(OrdersEntity ordersEntity);

    List<GetOrdersDTO> listEntityToListDTO(List<OrdersEntity> listOrders);
}
