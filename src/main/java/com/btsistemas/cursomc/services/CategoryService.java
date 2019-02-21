package com.btsistemas.cursomc.services;

import com.btsistemas.cursomc.domain.Category;
import com.btsistemas.cursomc.dto.CategoryDTO;
import com.btsistemas.cursomc.repositories.CategoryRepository;
import com.btsistemas.cursomc.services.exceptions.DataIntegrityException;
import com.btsistemas.cursomc.services.exceptions.ObjectNotFoundException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository repo;

	public List<Category> all() {
		return repo.findAll();
	}

	public Category find(Integer id) {
		Optional<Category> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Category.class.getName()));

	}

	public Category insert(Category obj) {
		obj.setId(null);
		return repo.save(obj);
	}

	public Category update(Category obj) {
		Category newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}

	// Metodo chamado no metodo acima
	private void updateData(Category newObj, Category obj) {
		newObj.setDescription(obj.getDescription());;
	}

	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir uma categoria que tenha produtos");
		}

	}

	public Page<Category> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}

	public Category toDtoForCategory(CategoryDTO categoryDTO) {
		return new Category(categoryDTO.getId(), categoryDTO.getDescription());
	}
}
