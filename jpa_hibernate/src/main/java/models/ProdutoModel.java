package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import entities.Produto;

public class ProdutoModel {

	public void create(Produto p) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("admin-jpa");
		EntityManager em = emf.createEntityManager();

		try {
			System.out.println("Iniciando a transação");
			em.getTransaction().begin();
			em.persist(p);
			em.getTransaction().commit();
			System.out.println("Produto criado com sucesso!!!");
		} catch (Exception e) {
			em.getTransaction().getRollbackOnly();
			System.err.println("Erro ao criar o produto!!!" + e.getMessage());
		} finally {
			em.close();
			emf.close();
			System.out.println("Finalizando a transação");
		}
	}

	public Produto findById(int id) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("admin-jpa");
		EntityManager em = emf.createEntityManager();
		Produto produto = null;

		try {
			System.out.println("Iniciando a transação");
			produto = em.find(Produto.class, id);
			System.out.println("Produto criado com sucesso!!!");
		} catch (Exception e) {
			em.getTransaction().rollback();
			System.err.println("Erro ao criar o produto!!!" + e.getMessage());
		} finally {
			em.close();
			emf.close();
			System.out.println("Finalizando a transação");
		}

		return produto;
	}

	public List<Produto> findAll() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("admin-jpa");
		EntityManager em = emf.createEntityManager();
		List<Produto> produtos = new ArrayList<Produto>();

		try {
			System.out.println("Iniciando a transação");
			produtos = em.createQuery("SELECT p FROM Produto p", Produto.class).getResultList();
			System.out.println("Produtos listados com sucesso!!!");
		} catch (Exception e) {
			em.getTransaction().rollback();
			System.err.println("Erro ao listar produtos !!!" + e.getMessage());
		} finally {
			em.close();
			emf.close();
			System.out.println("Finalizando a transação");
		}

		return produtos;
	}

	public void update(Produto p) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("admin-jpa");
		EntityManager em = emf.createEntityManager();

		try {
			System.out.println("Iniciando a transação");
			em.getTransaction().begin();

			Produto produto = em.find(Produto.class, p.getId());

			if (produto != null) {
				produto.setPreco(p.getPreco());
			} else {
				System.out.println("Produto não encontrado!");
			}

			em.getTransaction().commit();
			System.out.println("Produto atualizado com sucesso!!!");
		} catch (Exception e) {
			em.getTransaction().rollback();
			System.err.println("Erro ao atualizar produto!!!" + e.getMessage());
		} finally {
			em.close();
			emf.close();
			System.out.println("Finalizando a transação");
		}
	}

	public void delete(Produto p) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("admin-jpa");
		EntityManager em = emf.createEntityManager();

		try {
			System.out.println("Iniciando a transação");
			em.getTransaction().begin();

			Produto produto = em.find(Produto.class, p.getId());

			if (produto != null) {
				em.remove(produto);
			} else {
				System.out.println("Produto não encontrado!");
			}

			em.getTransaction().commit();
			System.out.println("Produto removido com sucesso!!!");
		} catch (Exception e) {
			em.getTransaction().rollback();
			System.err.println("Erro ao atualizar produto!!!" + e.getMessage());
		} finally {
			em.close();
			emf.close();
			System.out.println("Finalizando a transação");
		}
	}
}
