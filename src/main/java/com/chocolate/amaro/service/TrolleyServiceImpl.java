package com.chocolate.amaro.service;

import com.chocolate.amaro.dto.TrolleyDto;
import com.chocolate.amaro.mapper.TrolleyMapper;
import com.chocolate.amaro.model.entity.Product;
import com.chocolate.amaro.model.entity.Trolley;
import com.chocolate.amaro.model.entity.User;
import com.chocolate.amaro.repository.IProductRepository;
import com.chocolate.amaro.repository.ITrolleyRepository;
import com.chocolate.amaro.service.abstraction.ITrolleyService;
import com.chocolate.amaro.service.abstraction.IUserService;
import com.chocolate.amaro.utils.EnumState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TrolleyServiceImpl implements ITrolleyService {

    @Autowired
    private TrolleyMapper trolleyMapper;
    @Autowired
    private ITrolleyRepository trolleyRepository;
    @Autowired
    private IProductRepository productRepository;
    @Autowired
    private IUserService userService;


    @Override
    public TrolleyDto addCart(TrolleyDto cart) {
        User loggedUser = userService.getInfoUser();
        Trolley trolley = trolleyMapper.convertTo(cart);

        if(controlUniqueCartOpen(loggedUser))
            throw new RuntimeException("Error para agregar otro carrito primero cerrar el que se esta usando");

        Trolley saved = trolleyRepository.save(trolley);
        TrolleyDto result = trolleyMapper.convertToEntityToDto(saved);
        return result;
    }

    private boolean controlUniqueCartOpen(User user){
        user = userService.getInfoUser();
        List<Trolley> trolley = user.getCart();
        if(trolley.size() != 0){
            trolley.stream().filter(cart -> cart.getEnumState() ==
                    EnumState.ACTIVE) .collect(Collectors.toList());
        }else{
            return false;
        }
        return (trolley.size() > 0)?true:false;
    }

    @Override
    public TrolleyDto addProduct(Long cartId, Long productId) {
        Trolley entity = trolleyRepository.findById(cartId).get();
        Product product = productRepository.findById(productId).get();
        entity.addProduct(product);
        trolleyRepository.save(entity);
        TrolleyDto result = trolleyMapper.convertToEntityToDto(entity);
        return result;
    }

    @Override
    public TrolleyDto getCartById(Long id, User loggerUser) {
        Trolley trolley = getTrolley(id);
        if(trolley.getBuyer().getId() != loggerUser.getId()){
            throw new RuntimeException("Cart is not exist");
        }
        return trolleyMapper.convertToEntityToDto(trolley);

    }

    @Override
    public void removeProduct(Long cartID, Long productID) {
        Trolley cart = getTrolley(cartID);
        Product product = productRepository.findById(productID).get();
        if(cart.getProducts().size() <= 0)
            throw new RuntimeException("Product is Empty");
        cart.removeProduct(product);
        trolleyRepository.save(cart);
    }

    @Override
    public TrolleyDto BuyProductsById(Long id, User user) {
        Trolley trolley = getTrolley(id);
        if(trolley.getBuyer().getId() != user.getId()){
            throw new RuntimeException("Cart is not exist");
        }
        Trolley saved = trolleyRepository.getById(id);
        saved.setEnumState(EnumState.CLOSED);
        trolleyRepository.save(saved);
        TrolleyDto response = trolleyMapper.convertToEntity(saved);

        return response;
    }

    private Trolley getTrolley(Long id){
        Optional<Trolley> trolleyOptional = trolleyRepository.findById(id);
        if(trolleyOptional.isEmpty() || trolleyOptional.get().isSoftDelete()){
            throw new RuntimeException("Cart is not exists");
        }
        return trolleyOptional.get();
    }


}
