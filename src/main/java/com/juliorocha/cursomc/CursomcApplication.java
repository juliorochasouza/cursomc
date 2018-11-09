package com.juliorocha.cursomc;

import java.lang.reflect.Array;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.juliorocha.cursomc.domain.Categoria;
import com.juliorocha.cursomc.domain.Cidade;
import com.juliorocha.cursomc.domain.Cliente;
import com.juliorocha.cursomc.domain.Endereco;
import com.juliorocha.cursomc.domain.Estado;
import com.juliorocha.cursomc.domain.Produto;
import com.juliorocha.cursomc.domain.enums.TipoCliente;
import com.juliorocha.cursomc.repositories.CategoriaRepository;
import com.juliorocha.cursomc.repositories.CidadeRepository;
import com.juliorocha.cursomc.repositories.ClienteRepository;
import com.juliorocha.cursomc.repositories.EnderecoRepository;
import com.juliorocha.cursomc.repositories.EstadoRepository;
import com.juliorocha.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner{

	
	@Autowired
	private CategoriaRepository categoriaRespository;
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		/*
		 * INCLUI CATEGORIAS*/
		
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		/*
		 * INCLUI PRODUTOS*/
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		
		/*
		 * INCLUI RELACIONAMENTO DE CATEGORIAS COM PRODUTOS*/
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		/*
		 * INCLUI RELACIONAMETO DE PRODUTOS COM CATEGORIAS*/
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1,cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
	

		/*GRAVA CATEGORIAS NO BANCO H2*/
		categoriaRespository.save(Arrays.asList(cat1, cat2));
		
		
		/*GRAVA PRODUTOS NO BANCO H2*/
		produtoRepository.save(Arrays.asList(p1,p2,p3));

		/* INCLUI ESTADOS*/
		Estado est1 = new Estado(null,"Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		/* INCLUI CIDADES*/
		Cidade c1 = new Cidade(null, "Uberlandia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);
		
		// FAZ O RELACIONAMENTOS DE ESTADO E SUAS CIDADES
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2,c3));
		
		
		// GRAVA ESTADOS NO BANCO
		estadoRepository.save(Arrays.asList(est1,est2));
		
		// GRAVA CIDADES NO BANCO
		cidadeRepository.save(Arrays.asList(c1,c2,c3));
		
		// CRIA CLIENTES
		
		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "36378912377", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("27363323","93838393"));
		
		Endereco e1 = new Endereco(null, "Rua Flores", "300", "apto 303", "Jardim", "38220834", cli1, c1);
		Endereco e2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", cli1, c2);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
		
		
		// GRAVA CLIENTE
		clienteRepository.save(Arrays.asList(cli1));
		
		// GRAVA ENDERECOS
		enderecoRepository.save(Arrays.asList(e1, e2));
	}
}
