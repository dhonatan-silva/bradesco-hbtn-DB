package demo;

import java.time.LocalDate;
import java.util.List;

import entities.Pessoa;
import entities.Produto;
import models.PessoaModel;
import models.ProdutoModel;

public class AdministrativoApp {
	public static void main(String[] args) {
		ProdutoModel produtoModel = new ProdutoModel();

		Produto p1 = new Produto();
		p1.setNome("TV");
		p1.setPreco(300.0);
		p1.setQuantidade(100);
		p1.setStatus(true);

		// 1) Criando um produto
		produtoModel.create(p1);

		// 2) Buscando todos os produtos na base de dados
		List<Produto> produtos = produtoModel.findAll();
		System.out.println("Qtde de produtos encontrados : " + produtos.size());
		
		// 3) Buscando produto por id
		Produto byId = produtoModel.findById(0);
		System.out.println("Produto encontrado: " + byId.toString());
		
		// 4) Atualizar produto
		p1.setPreco(500.0);
		p1.setId(1);
		produtoModel.update(p1);
		
		// 5) Remover produto
		produtoModel.delete(p1);
		
		PessoaModel pessoaModel = new PessoaModel();
		
		Pessoa p2 = new Pessoa();
		p2.setNome("Jhon Doe");;
		p2.setEmail("jhon_doe@mail.com");
		p2.setIdade(30);
		p2.setCpf("99999999999");
		p2.setDataNascimento(LocalDate.of(2000, 10, 10));
		
		// 1) Criar pessoa
		pessoaModel.create(p2);
		
		// 2) Buscar todas pessoas
		List<Pessoa> pessoas = pessoaModel.findAll();
		System.out.println("Qtde de produtos encontrados : " + pessoas.size());
		
		// 3) Buscar pessoa por id
		p2.setId(1);
		Pessoa byId2 = pessoaModel.findById(p2);
		System.out.println("Pessoa encontrada: " + byId2.toString());
		
		// 4) Atualizar pessoa
		p2.setNome("Mary Doe");
		p2.setId(1);
		pessoaModel.update(p2);
		
		// 5) Remover Pessoa
		p2.setId(1);
		pessoaModel.delete(p2);


	}
}
