package com.juliorocha.cursomc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.juliorocha.cursomc.domain.Cliente;
import com.juliorocha.cursomc.repositories.ClienteRepository;
import com.juliorocha.cursomc.services.exceptions.ObjNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repo;
	
	public Cliente buscar( Integer id) {
		
		Cliente obj = repo.findOne(id);
		
		if (obj == null) {
			throw new ObjNotFoundException("Objeto não encontrado Id: " + id +
					                       ", Tipo: " + Cliente.class.getName());
		}
		
		return obj;
		
	}
	
}
