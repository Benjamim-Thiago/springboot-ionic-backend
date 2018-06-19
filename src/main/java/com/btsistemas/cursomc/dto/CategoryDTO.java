package com.btsistemas.cursomc.dto;

import com.btsistemas.cursomc.domain.Category;
import java.io.Serializable;

/**
 *
 * @author ben
 */
public class CategoryDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String description;

    public CategoryDTO() {
    }

    public CategoryDTO(Category category) {
        id = category.getId();
        description = category.getDescription();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
