package entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Curso {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nome;
	private String sigla;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "professor_id")
	private Professor professor;

	@ManyToMany
	@JoinTable(name = "curso_aluno", joinColumns = @JoinColumn(name = "curso_id"), inverseJoinColumns = @JoinColumn(name = "aluno_id"))
	private Set<Aluno> alunos = new HashSet<>();;

	@OneToOne(mappedBy = "curso", cascade = CascadeType.ALL, orphanRemoval = true)
	private MaterialCurso materialCurso;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	public Set<Aluno> getAlunos() {
		return alunos;
	}

	public void setAlunos(Set<Aluno> alunos) {
		this.alunos = alunos;
	}

	public MaterialCurso getMaterialCurso() {
		return materialCurso;
	}

	public void setMaterialCurso(MaterialCurso materialCurso) {
		this.materialCurso = materialCurso;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Curso [id=");
		builder.append(id);
		builder.append(", nome=");
		builder.append(nome);
		builder.append(", sigla=");
		builder.append(sigla);
		builder.append(", professor=");
		builder.append(professor);
		builder.append(", materialCurso=");
		builder.append(materialCurso);
		builder.append("]");
		return builder.toString();
	}

}
