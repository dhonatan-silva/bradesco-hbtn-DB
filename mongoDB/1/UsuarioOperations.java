import java.util.Arrays;

import org.bson.Document;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;

public class UsuarioOperations {

	private static MongoCollection<Document> collection;

	public static void main(String[] args) {
		MongoDBConnection connection = new MongoDBConnection();

		if (connection != null) {
			collection = connection.getDatabase().getCollection("usuarios");

			// Inserir usuários
			insertMany();

			// Consulte os registros.
			findAll();

			// Altere a idade de Bob para 32 anos.
			update("Bob", 32);

			// Consulte os registros.
			findAll();

			// Apague o registro Charlie.
			delete("Charlie");

			// Consulte os registros.
			findAll();
		}

	}

	public static void insertMany() {
		Usuario usuario1 = new Usuario("Alice", 25);
		Usuario usuario2 = new Usuario("Bob", 30);
		Usuario usuario3 = new Usuario("Charlie", 35);
		collection.insertMany(Arrays.asList(usuario1.toDocument(), usuario2.toDocument(), usuario3.toDocument()));
		System.out.println("Usuários inseridos com sucesso!");
	}

	public static void update(String nome, int idade) {
		collection.updateOne(Filters.eq("nome", nome), Updates.set("idade", idade));
		System.out.println("Usuário: " + nome + " atualizado com sucesso!");
	}

	public static void findAll() {
		FindIterable<Document> usuarios = collection.find();
		System.out.println("--------------------------------------------------");
		System.out.println("Total de usuários: " + collection.countDocuments());
		System.out.println("--------------------------------------------------");
		for (Document document : usuarios) {
			System.out.println("Nome: " + Usuario.fromDocument(document).getNome());
			System.out.println("Idade: " + Usuario.fromDocument(document).getIdade());
			System.out.println("--------------------------------------------------");
		}
	}

	public static void delete(String nome) {
		collection.deleteOne(Filters.eq("nome", nome));
		System.out.println("Usuário: " + nome + " deletado com sucesso!");
	}

}
