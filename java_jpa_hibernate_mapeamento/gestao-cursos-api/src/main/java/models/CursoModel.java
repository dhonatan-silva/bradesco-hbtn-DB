package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import entities.Aluno;
import entities.Curso;

public class CursoModel {

	public void create(Curso curso) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestao-cursos-jpa");
		EntityManager em = emf.createEntityManager();

		try {
			System.out.println("Iniciando a transação");
			em.getTransaction().begin();
			em.persist(curso.getProfessor());

			for (Aluno aluno : curso.getAlunos()) {
				em.persist(aluno);
			}
			em.persist(curso);

			em.getTransaction().commit();
			System.out.println("Curso criado com sucesso!!!");
		} catch (Exception e) {
			em.getTransaction().rollback();
			System.err.println("Erro ao criar um curso!!!" + e.getCause().getMessage());
		} finally {
			em.close();
			emf.close();
			System.out.println("Finalizando a transação");
		}
	}

	public Curso findById(Long id) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestao-cursos-jpa");
		EntityManager em = emf.createEntityManager();
		Curso curso = null;

		try {
			System.out.println("Iniciando a transação");
			curso = em.find(Curso.class, id);
			System.out.println("Curso criado com sucesso!!!");
		} catch (Exception e) {
			em.getTransaction().rollback();
			System.err.println("Erro ao criar um curso!!!" + e.getMessage());
		} finally {
			em.close();
			emf.close();
			System.out.println("Finalizando a transação");
		}
		return curso;
	}

	public List<Curso> findAll() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestao-cursos-jpa");
		EntityManager em = emf.createEntityManager();
		List<Curso> cursos = new ArrayList<>();

		try {
			System.out.println("Iniciando a transação");
			cursos = em.createQuery("SELECT c FROM Curso c", Curso.class).getResultList();
			System.out.println("Cursos listados com sucesso!!!");
		} catch (Exception e) {
			em.getTransaction().rollback();
			System.err.println("Erro ao listar cursos!!!" + e.getMessage());
		} finally {
			em.close();
			emf.close();
			System.out.println("Finalizando a transação");
		}

		return cursos;
	}

	public void update(Curso curso) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestao-cursos-jpa");
		EntityManager em = emf.createEntityManager();

		try {
			System.out.println("Iniciando a transação");
			em.getTransaction().begin();

			Curso crs = em.find(Curso.class, curso.getId());

			if (crs != null) {
				crs.setNome(curso.getNome());
			} else {
				System.out.println("Curso não encontrado!");
			}

			em.getTransaction().commit();
			System.out.println("Curso atualizado com sucesso!!!");
		} catch (Exception e) {
			em.getTransaction().rollback();
			System.err.println("Erro ao atualizar aluno!!!" + e.getMessage());
		} finally {
			em.close();
			emf.close();
			System.out.println("Finalizando a transação");
		}
	}

	public void delete(Curso curso) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestao-cursos-jpa");
		EntityManager em = emf.createEntityManager();

		try {
			System.out.println("Iniciando a transação");
			em.getTransaction().begin();

			Curso crs = em.find(Curso.class, curso.getId());

			if (crs != null) {
				em.remove(crs);
			} else {
				System.out.println("Curso não encontrado!");
			}

			em.getTransaction().commit();
			System.out.println("Curso removido com sucesso!!!");
		} catch (Exception e) {
			em.getTransaction().rollback();
			System.err.println("Erro ao atualizar curso!!!" + e.getMessage());
		} finally {
			em.close();
			emf.close();
			System.out.println("Finalizando a transação");
		}
	}

}
