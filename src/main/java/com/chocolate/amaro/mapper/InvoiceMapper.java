package com.chocolate.amaro.mapper;


import com.chocolate.amaro.model.entity.Invoice;
import com.chocolate.amaro.model.entity.Trolley;
import com.chocolate.amaro.model.response.InvoiceResponse;
import com.chocolate.amaro.repository.ITrolleyRepository;
import com.chocolate.amaro.service.abstraction.ITrolleyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InvoiceMapper {
    @Autowired
    private ITrolleyRepository trolleyRepository;

    public InvoiceResponse invoiceEntityTo(Invoice saved, Long cartId) {
        Trolley trolley = trolleyRepository.getById(cartId);
        InvoiceResponse response = new InvoiceResponse();
        response.setId(saved.getId());
        response.setTotalPrice(saved.getTotalPrice());
        response.setQuantity(saved.getQuantity());
        response.setLastname(saved.getLastname());
        response.setUsername(saved.getUsername());
        response.setNameUser(saved.getNameUser());
        response.setTimestamp(saved.getTimestamp());
        response.setProductList(trolley.getProducts());
        return response;
    }
}
