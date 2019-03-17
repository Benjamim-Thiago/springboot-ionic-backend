package com.btsistemas.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.btsistemas.cursomc.domain.RequestSale;
import com.btsistemas.cursomc.repositories.RequestSaleRepository;
import com.btsistemas.cursomc.services.exceptions.ObjectNotFoundException;


@Service
public class RequestSaleService {

    @Autowired
    private RequestSaleRepository repo;

    public RequestSale find(Integer id) {
        Optional<RequestSale> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + RequestSale.class.getName()));

    }

}
