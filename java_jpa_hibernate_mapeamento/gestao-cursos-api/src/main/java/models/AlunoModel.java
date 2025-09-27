package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import entities.Aluno;

public class AlunoModel {
	
	public void create(Aluno aluno) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestao-cursos-jpa");
		EntityManager em = emf.createEntityManager();

		try {
			System.out.println("Iniciando a transação");
			em.getTransaction().begin();
			em.persist(aluno);
			em.getTransaction().commit();
			System.out.println("Aluno criado com sucesso!!!");
		} catch (Exception e) {
			em.getTransaction().rollback();
			System.err.println("Erro ao criar um aluno!!!" + e.getMessage());
		} finally {
			em.close();
			emf.close();
			System.out.println("Finalizando a transação");
		}
	}

	public Aluno findById(Long id) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestao-cursos-jpa");
		EntityManager em = emf.createEntityManager();
		Aluno aluno = null;

		try {
			System.out.println("Iniciando a transação");
			aluno = em.find(Aluno.class, id);
			System.out.println("Aluno criado com sucesso!!!");
		} catch (Exception e) {
			em.getTransaction().rollback();
			System.err.println("Erro ao criar um aluno!!!" + e.getMessage());
		} finally {
			em.close();
			emf.close();
			System.out.println("Finalizando a transação");
		}
		return aluno;
	}

	public List<Aluno> findAll() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestao-cursos-jpa");
		EntityManager em = emf.createEntityManager();
		List<Aluno> alunos = new ArrayList<>();

		try {
			System.out.println("Iniciando a transação");
			alunos = em.createQuery("SELECT a FROM Aluno a", Aluno.class).getResultList();
			System.out.println("Alunos listados com sucesso!!!");
		} catch (Exception e) {
			em.getTransaction().rollback();
			System.err.println("Erro ao listar alunos!!!" + e.getMessage());
		} finally {
			em.close();
			emf.close();
			System.out.println("Finalizando a transação");
		}

		return alunos;
	}

	public void update(Aluno aluno) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestao-cursos-jpa");
		EntityManager em = emf.createEntityManager();

		try {
			System.out.println("Iniciando a transação");
			em.getTransaction().begin();

			Aluno aln = em.find(Aluno.class, aluno.getId());

			if (aln != null) {
				aln.setNomeCompleto(aluno.getNomeCompleto());
			} else {
				System.out.println("Aluno não encontrado!");
			}

			em.getTransaction().commit();
			System.out.println("aluno atualizado com sucesso!!!");
		} catch (Exception e) {
			em.getTransaction().rollback();
			System.err.println("Erro ao atualizar aluno!!!" + e.getMessage());
		} finally {
			em.close();
			emf.close();
			System.out.println("Finalizando a transação");
		}
	}

	public void delete(Aluno aluno) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestao-cursos-jpa");
		EntityManager em = emf.createEntityManager();

		try {
			System.out.println("Iniciando a transação");
			em.getTransaction().begin();

			Aluno aln = em.find(Aluno.class, aluno.getId());

			if (aln != null) {
				em.remove(aln);
			} else {
				System.out.println("Aluno não encontrado!");
			}

			em.getTransaction().commit();
			System.out.println("Aluno removido com sucesso!!!");
		} catch (Exception e) {
			em.getTransaction().rollback();
			System.err.println("Erro ao atualizar aluno!!!" + e.getMessage());
		} finally {
			em.close();
			emf.close();
			System.out.println("Finalizando a transação");
		}
	}
}
