package com.juliorocha.cursomc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.juliorocha.cursomc.domain.Categoria;
import com.juliorocha.cursomc.repositories.CategoriaRepository;
import com.juliorocha.cursomc.services.exceptions.ObjNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repo;
	
	public Categoria buscar( Integer id) {
		
		Categoria obj = repo.findOne(id);
		
		if (obj == null) {
			throw new ObjNotFoundException("Objeto não encontrado Id: " + id +
					                       ", Tipo: " + Categoria.class.getName());
		}
		
		return obj;
		
	}
	
}
