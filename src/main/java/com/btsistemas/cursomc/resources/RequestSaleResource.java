package com.btsistemas.cursomc.resources;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.btsistemas.cursomc.domain.RequestSale;
import com.btsistemas.cursomc.services.RequestSaleService;

@RestController
@RequestMapping(value = "/requestSales")
public class RequestSaleResource {

    @Autowired
    private RequestSaleService service;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<RequestSale> find(@PathVariable Integer id) {
        RequestSale obj = service.find(id);
        return ResponseEntity.ok().body(obj);
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@Valid @RequestBody RequestSale obj) {
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
}
