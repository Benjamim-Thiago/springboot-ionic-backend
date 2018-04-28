package com.btsistemas.cursomc.resources;

import com.btsistemas.cursomc.domain.Category;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/categories")
public class CategoryResource {
    
    @RequestMapping(method = RequestMethod.GET)
    public List<Category> listar() {
        Category c1 = new Category(1, "Informática");
        Category c2 = new Category(2, "Escritório");
        
        List<Category> listing = new ArrayList<>();
        listing.add(c1);
        listing.add(c2);
        return listing; 
    }
}
