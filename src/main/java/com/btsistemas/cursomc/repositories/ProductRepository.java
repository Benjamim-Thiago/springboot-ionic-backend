/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.btsistemas.cursomc.repositories;

import com.btsistemas.cursomc.domain.Category;
import com.btsistemas.cursomc.domain.Product;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly=true)
public interface ProductRepository extends JpaRepository<Product, Integer> {
	/*@Query("SELECT DISTINCT obj FROM Product obj " +
			"INNER JOIN obj.categories cat " + 
			"WHERE LOWER(obj.description) LIKE LOWER(CONCAT('%',:description, '%')) AND cat IN :categories")
	Page<Product> search(@Param("description") String name, @Param("categories") List<Category> categories,Pageable pageRequest);*/
	
	
	Page<Product> findDistinctByDescriptionIgnoreCaseContainingAndCategoriesIn(String name, List<Category> categories,Pageable pageRequest);
}
