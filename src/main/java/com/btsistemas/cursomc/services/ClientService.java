package com.btsistemas.cursomc.services;

import com.btsistemas.cursomc.domain.Client;
import com.btsistemas.cursomc.dto.ClientDTO;
import com.btsistemas.cursomc.repositories.ClientRepository;
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

	public Client update(Client obj) {
		Client newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}

	//Metodo chamado no metodo acima
	private void updateData(Client newObj, Client obj) {
		newObj.setName(obj.getName());	
		newObj.setEmail(obj.getEmail());
	}

	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir o cliente porque há entidades relacionadas");
		}

	}

	public Page<Client> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}

	public Client toDtoForClient(ClientDTO clientDTO) {
		return new Client(clientDTO.getId(), clientDTO.getName(),clientDTO.getEmail(), null, null);
	}

}
