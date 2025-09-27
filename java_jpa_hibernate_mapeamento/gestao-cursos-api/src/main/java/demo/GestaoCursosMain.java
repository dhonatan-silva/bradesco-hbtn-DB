package demo;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import entities.Aluno;
import entities.Curso;
import entities.Endereco;
import entities.MaterialCurso;
import entities.Professor;
import entities.Telefone;
import models.AlunoModel;
import models.CursoModel;

public class GestaoCursosMain {
	public static void main(String[] args) {

		Aluno aluno = new Aluno();
		aluno.setNomeCompleto("Jhon Doe");
		aluno.setMatricula("12345");
		aluno.setNascimento(new Date(971136000000L));
		aluno.setEmail("jhon.doe@mail.com");

		Endereco endereco1 = new Endereco();
		endereco1.setLogradouro("Rua");
		endereco1.setEnderco("Rua Alberto José da Mota");
		endereco1.setNumero("514");
		endereco1.setBairro("Jardim São Luiz (Valparizo)");
		endereco1.setCidade("Barueri");
		endereco1.setEstado("SP");
		endereco1.setCep(06413730);
		endereco1.setAluno(aluno);

		Endereco endereco2 = new Endereco();
		endereco2.setLogradouro("Rua");
		endereco2.setEnderco("Rua Comendador Lino Frescht");
		endereco2.setNumero("580");
		endereco2.setBairro("Boracéia");
		endereco2.setCidade("Bertioga");
		endereco2.setEstado("SP");
		endereco2.setCep(11271255);
		endereco2.setAluno(aluno);

		aluno.getEnderecos().addAll(Arrays.asList(endereco1, endereco2));

		Telefone telefone = new Telefone();
		telefone.setDDD("13");
		telefone.setNumero("9949-6036");
		telefone.setAluno(aluno);
		aluno.getTelefones().addAll(Arrays.asList(telefone));

		MaterialCurso materialCurso = new MaterialCurso();
		materialCurso.setUrl("https://www.marketplace.com.br/livros-ti/abra_sua_cabeca_java");

		Curso curso1 = new Curso();
		curso1.setNome("Java Orientado a Objetos");
		curso1.setSigla("JOO");
		curso1.getAlunos().addAll(Arrays.asList(aluno));
		curso1.setMaterialCurso(materialCurso);
		materialCurso.setCurso(curso1);

		Professor professor = new Professor();
		professor.setNomeCompleto("Paulo José");
		professor.setMatricula("123789");
		professor.setEmail("paulo.jose@umc.com");
		professor.getCursos().addAll(Arrays.asList(curso1));
		curso1.setProfessor(professor);

		AlunoModel alunoModel = new AlunoModel();

		// 1) - Create Aluno
		alunoModel.create(aluno);
		System.out.println("# - Aluno criado com sucesso");

		// 2) - Find By Id - Aluno
		Aluno byId = alunoModel.findById(1L);
		System.out.println("# - Aluno buscado: " + byId.toString());

		// 3) - Finda All Aluno
		List<Aluno> all = alunoModel.findAll();
		System.out.println("# - Total de alunos cadastrados: " + all.size());

		// 4) - Update Aluno
		aluno.setNomeCompleto("Mary Doe");
		aluno.setId(1L);
		alunoModel.update(aluno);
		System.out.println("# - Aluno atualizado com sucesso");

		// 5) - Delete Aluno
		aluno.setId(1L);
		alunoModel.delete(aluno);
		System.out.println("# - Aluno excluído com sucesso");

		CursoModel cursoModel = new CursoModel();

		// 1) - Create Curso
		cursoModel.create(curso1);
		System.out.println("# - Cursos gravados com sucesso ");

		// 2 - Find By Id Curso
		Curso byId2 = cursoModel.findById(1L);
		System.out.println("# - Curso buscado com sucesso: " + byId2.toString());

		// 3) - Find All Curso
		List<Curso> allCurso = cursoModel.findAll();
		System.out.println("# - Total de cursos cadastrados: " + allCurso.size());

		// 4) - Update - Curso
		curso1.setId(1L);
		curso1.setNome("Técnico em Análise de Sistemas");
		curso1.setSigla("TADS");
		cursoModel.update(curso1);
		System.out.println("# - Curso atualizado com sucesso");

		// 5) - Delete Curso
		curso1.setId(1L);
		cursoModel.delete(curso1);
		System.out.println("# - Curso excluído com sucesso");
	}

}
