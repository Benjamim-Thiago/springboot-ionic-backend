package com.btsistemas.cursomc.services;

import com.btsistemas.cursomc.domain.Category;
import com.btsistemas.cursomc.repositories.CategoryRepository;
import com.btsistemas.cursomc.services.exceptions.ObjectNotFoundException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repo;

    public Category search(Integer id) {
        Optional<Category> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Category.class.getName()));

    }
    
    public Category insert(Category obj){
        obj.setId(null);
        return repo.save(obj);
    }

}
