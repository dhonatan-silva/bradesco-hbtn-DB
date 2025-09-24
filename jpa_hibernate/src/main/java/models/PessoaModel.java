package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import entities.Pessoa;

public class PessoaModel {

	public void create(Pessoa p) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("admin-jpa");
		EntityManager em = emf.createEntityManager();

		try {
			System.out.println("Iniciando a transação");
			em.getTransaction().begin();
			em.persist(p);
			em.getTransaction().commit();
			System.out.println("Pessoa criada com sucesso !!!");
		} catch (Exception e) {
			em.getTransaction().getRollbackOnly();
			System.err.println("Erro ao criar o pessoa !!!" + e.getMessage());
		} finally {
			em.close();
			emf.close();
			System.out.println("Finalizando a transação");
		}

	}

	public Pessoa findById(Pessoa p) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("admin-jpa");
		EntityManager em = emf.createEntityManager();
		Pessoa pessoa = null;

		try {
			System.out.println("Iniciando a transação");
			pessoa = em.find(Pessoa.class, p.getId());
			System.out.println("Pessoa criado com sucesso !!!");
		} catch (Exception e) {
			em.getTransaction().rollback();
			System.err.println("Erro ao pessoa o produto !!!" + e.getMessage());
		} finally {
			em.close();
			emf.close();
			System.out.println("Finalizando a transação");
		}

		return pessoa;
	}

	public List<Pessoa> findAll() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("admin-jpa");
		EntityManager em = emf.createEntityManager();
		List<Pessoa> pessoas = new ArrayList<Pessoa>();

		try {
			System.out.println("Iniciando a transação");
			pessoas = em.createQuery("SELECT p FROM Pessoa p", Pessoa.class).getResultList();
			System.out.println("Pessoas listadas com sucesso!!!");
		} catch (Exception e) {
			em.getTransaction().rollback();
			System.err.println("Erro ao listar pessoa!!!" + e.getMessage());
		} finally {
			em.close();
			emf.close();
			System.out.println("Finalizando a transação");
		}

		return pessoas;
	}

	public void update(Pessoa p) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("admin-jpa");
		EntityManager em = emf.createEntityManager();

		try {
			System.out.println("Iniciando a transação");
			em.getTransaction().begin();

			Pessoa pessoa = em.find(Pessoa.class, p.getId());

			if (pessoa != null) {
				pessoa.setNome(p.getNome());
			} else {
				System.out.println("Pessoa não encontrada!");
			}

			em.getTransaction().commit();
			System.out.println("Pessoa atualizada com sucesso!!!");
		} catch (Exception e) {
			em.getTransaction().rollback();
			System.err.println("Erro ao atualizar pessoa!!!" + e.getMessage());
		} finally {
			em.close();
			emf.close();
			System.out.println("Finalizando a transação");
		}
	}

	public void delete(Pessoa p) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("admin-jpa");
		EntityManager em = emf.createEntityManager();

		try {
			System.out.println("Iniciando a transação");
			em.getTransaction().begin();

			Pessoa pessoa = em.find(Pessoa.class, p.getId());

			if (pessoa != null) {
				em.remove(pessoa);
			} else {
				System.out.println("Pessoa não encontrado!");
			}

			em.getTransaction().commit();
			System.out.println("Pessoa removido com sucesso!!!");
		} catch (Exception e) {
			em.getTransaction().rollback();
			System.err.println("Erro ao atualizar pessoa!!!" + e.getMessage());
		} finally {
			em.close();
			emf.close();
			System.out.println("Finalizando a transação");
		}
	}
}
