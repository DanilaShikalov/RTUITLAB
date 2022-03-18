package itlab.demo.mapper;

import itlab.demo.model.dto.orders.*;
import itlab.demo.model.entity.OrdersEntity;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OrdersMapperTest {
    private final OrdersMapper ordersMapper = Mappers.getMapper(OrdersMapper.class);

    @Test
    void dtoToEntity() {
        PostOrdersDTO postOrdersDTO = new PostOrdersDTO(1L, null, true, null);
        OrdersEntity ordersEntity = ordersMapper.dtoToEntity(postOrdersDTO);
        assertEquals(postOrdersDTO.isStatus(), ordersEntity.isStatus());
    }

    @Test
    void entityToCreatedDTO() {
        OrdersEntity ordersEntity = new OrdersEntity(null, 1L, null, true, null, null);
        PostedOrdersDTO postedOrdersDTO = ordersMapper.entityToCreatedDTO(ordersEntity);
        assertEquals(ordersEntity.isStatus(), postedOrdersDTO.isStatus());
    }

    @Test
    void putDTOToEntity() {
        PutOrdersDTO putOrdersDTO = new PutOrdersDTO(1L, null, true, null);
        OrdersEntity ordersEntity = ordersMapper.putDTOToEntity(putOrdersDTO);
        assertEquals(putOrdersDTO.isStatus(), ordersEntity.isStatus());
    }

    @Test
    void entityToPutDTO() {
        OrdersEntity ordersEntity = new OrdersEntity(null, 1L, null, true, null, null);
        PuttedOrdersDTO puttedOrdersDTO = ordersMapper.entityToPutDTO(ordersEntity);
        assertEquals(ordersEntity.isStatus(), puttedOrdersDTO.isStatus());
    }

    @Test
    void entityToDTO() {
        OrdersEntity ordersEntity = new OrdersEntity(null, 1L, null, true, null, null);
        GetOrdersDTO getOrdersDTO = ordersMapper.entityToDTO(ordersEntity);
        assertEquals(ordersEntity.getId(), getOrdersDTO.getId());
    }

    @Test
    void listEntityToListDTO() {
        List<OrdersEntity> list = List.of(new OrdersEntity(1L, 1L, null, true, null, null));
        List<GetOrdersDTO> list1 = ordersMapper.listEntityToListDTO(list);
        assertEquals(list.get(0).getId(), list1.get(0).getId());
    }
}