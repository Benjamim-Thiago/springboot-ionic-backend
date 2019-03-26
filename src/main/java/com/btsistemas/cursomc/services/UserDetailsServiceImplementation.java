package com.btsistemas.cursomc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.btsistemas.cursomc.domain.Client;
import com.btsistemas.cursomc.repositories.ClientRepository;
import com.btsistemas.cursomc.security.UserSpringSecurity;

public class UserDetailsServiceImplementation implements UserDetailsService{

	@Autowired
	private ClientRepository repo;
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Client client =  repo.findByEmail(email);
		if(client==null) {
			throw new UsernameNotFoundException(email);
		}
		return new UserSpringSecurity(client.getId(), client.getEmail(), client.getPassword(), client.getProfiles());
	}

}
