package com.btsistemas.cursomc.services;

import com.btsistemas.cursomc.domain.Client;
import com.btsistemas.cursomc.repositories.CategoryRepository;
import com.btsistemas.cursomc.repositories.ClientRepository;
import com.btsistemas.cursomc.services.exceptions.ObjectNotFoundException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    @Autowired
    private ClientRepository repo;

    public List<Client> all() {
        List<Client> obj = repo.findAll();
        return obj;

    }

    public Client find(Integer id) {
        Optional<Client> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Client.class.getName()));

    }

}
