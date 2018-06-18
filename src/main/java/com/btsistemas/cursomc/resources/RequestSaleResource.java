package com.btsistemas.cursomc.resources;

import com.btsistemas.cursomc.domain.RequestSale;
import com.btsistemas.cursomc.services.RequestSaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/requestSales")
public class RequestSaleResource {

    @Autowired
    private RequestSaleService service;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> find(@PathVariable Integer id) {
        RequestSale obj = service.search(id);
        return ResponseEntity.ok().body(obj);
    }
}
