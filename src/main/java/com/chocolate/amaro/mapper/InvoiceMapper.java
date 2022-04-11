package com.chocolate.amaro.mapper;


import com.chocolate.amaro.model.entity.Invoice;
import com.chocolate.amaro.model.response.InvoiceResponse;
import org.springframework.stereotype.Component;

@Component
public class InvoiceMapper {

    public InvoiceResponse invoiceEntityTo(Invoice saved) {
        InvoiceResponse response = new InvoiceResponse();
        response.setId(saved.getId());
        response.setTotalPrice(saved.getTotalPrice());
        response.setQuantity(saved.getQuantity());
        response.setLastname(saved.getLastname());
        response.setUsername(saved.getUsername());
        response.setNameUser(saved.getNameUser());
        response.setTimestamp(saved.getTimestamp());
        return response;
    }
}
