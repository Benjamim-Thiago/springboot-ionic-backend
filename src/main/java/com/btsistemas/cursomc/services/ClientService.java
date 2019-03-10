package com.btsistemas.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.btsistemas.cursomc.domain.Address;
import com.btsistemas.cursomc.domain.City;
import com.btsistemas.cursomc.domain.Client;
import com.btsistemas.cursomc.domain.enums.TypeClient;
import com.btsistemas.cursomc.dto.ClientDTO;
import com.btsistemas.cursomc.dto.ClientNewDTO;
import com.btsistemas.cursomc.repositories.AddressRepository;
import com.btsistemas.cursomc.repositories.ClientRepository;
import com.btsistemas.cursomc.services.exceptions.DataIntegrityException;
import com.btsistemas.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClientService {

	@Autowired
	private ClientRepository repo;
	@Autowired
	private AddressRepository addressRepository;

	public List<Client> all() {
		List<Client> obj = repo.findAll();
		return obj;

	}

	public Client find(Integer id) {
		Optional<Client> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Client.class.getName()));

	}

	@Transactional
	public Client insert(Client obj) {
		obj.setId(null);
		obj = repo.save(obj);
		addressRepository.saveAll(obj.getAddresses());
		return obj;
	}

	public Client update(Client obj) {
		Client newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}

	// Metodo chamado no metodo acima
	private void updateData(Client newObj, Client obj) {
		newObj.setName(obj.getName());
		newObj.setEmail(obj.getEmail());
	}

	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir o cliente porque há pedidos relacionados a este cliente");
		}

	}

	public Page<Client> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}

	public Client toDtoForClient(ClientDTO clientDTO) {
		return new Client(clientDTO.getId(), clientDTO.getName(), clientDTO.getEmail(), null, null);
	}

	public Client toDtoForClient(ClientNewDTO clientDTO) {
		Client client = new Client(null, clientDTO.getName(), clientDTO.getEmail(), clientDTO.getDocument(),
				TypeClient.toEnum(clientDTO.getTypeClient()));

		City city = new City(clientDTO.getCityId(), null, null);

		Address address = new Address(null, clientDTO.getPlace(), clientDTO.getNumber(), clientDTO.getComplement(),
				clientDTO.getNeighborhood(), clientDTO.getZipcode(), client, city);

		client.getAddresses().add(address);
		client.getPhones().add(clientDTO.getPhone1());

		if (clientDTO.getPhone2() != null) {
			client.getPhones().add(clientDTO.getPhone2());
		}
		if (clientDTO.getPhone3() != null) {
			client.getPhones().add(clientDTO.getPhone3());
		}

		return client;
	}

}
