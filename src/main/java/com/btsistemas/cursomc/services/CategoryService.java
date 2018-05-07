package com.btsistemas.cursomc.services;

import com.btsistemas.cursomc.domain.Category;
import com.btsistemas.cursomc.repositories.CategoryRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repo;

    public Category search(Integer id) {
        Optional<Category> obj = repo.findById(id);
        return obj.orElse(null);
    }

}
