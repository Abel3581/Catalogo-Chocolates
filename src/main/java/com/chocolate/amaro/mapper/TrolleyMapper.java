package com.chocolate.amaro.mapper;


import com.chocolate.amaro.dto.TrolleyDto;
import com.chocolate.amaro.model.entity.Category;
import com.chocolate.amaro.model.entity.Product;
import com.chocolate.amaro.model.entity.Trolley;
import com.chocolate.amaro.model.entity.User;
import com.chocolate.amaro.repository.ITrolleyRepository;
import com.chocolate.amaro.service.abstraction.IUserService;
import com.chocolate.amaro.utils.EnumState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Component
public class TrolleyMapper {

    @Autowired
    private IUserService userService;

    @Autowired
    private ITrolleyRepository trolleyRepository;

    public Trolley convertTo(TrolleyDto cart) {

        Trolley trolley = new Trolley();
        User loggedUser = userService.getInfoUser();
        trolley.setProducts(cart.getProducts());
        trolley.setEnumState(EnumState.ACTIVE);
        trolley.setTimestamp(new Timestamp(System.currentTimeMillis()));
        trolley.setBuyer(loggedUser);

        return trolley;
    }

    public TrolleyDto convertToEntityToDto(Trolley entity) {

        TrolleyDto trolleyDto = new TrolleyDto();
        trolleyDto.setId(entity.getId());
        trolleyDto.setProducts(entity.getProducts());
        trolleyDto.setTimestamp(entity.getTimestamp());
        trolleyDto.setEnumState(entity.getEnumState());
        trolleyDto.setNameUser(entity.getBuyer().getUsername());
        trolleyDto.setAmount(entity.getAmount());
        trolleyDto.setQuantity(entity.getQuantity());
        trolleyDto.setProducts(entity.getProducts());

        return trolleyDto;
    }


    public TrolleyDto convertToEntity(Trolley entity) {
        TrolleyDto trolleyDto = new TrolleyDto();
        trolleyDto.setId(entity.getId());
        trolleyDto.setTimestamp(entity.getTimestamp());
        trolleyDto.setEnumState(EnumState.CLOSED);
        trolleyDto.setNameUser(entity.getBuyer().getUsername());
        trolleyDto.setAmount(entity.getAmount());
        trolleyDto.setQuantity(entity.getQuantity());
        trolleyDto.setProducts(entity.getProducts());
        return trolleyDto;
    }


}
