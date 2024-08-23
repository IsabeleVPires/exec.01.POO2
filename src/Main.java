import java.sql.SQLException;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        try {
            // Criar instâncias dos DAOs
            AutorDAO autorDAO = new AutorDAO();
            LivroDAO livroDAO = new LivroDAO();

            // Inserir autores
            Autor autor1 = new Autor(1, "J.K. Rowling", "Britânica");
            Autor autor2 = new Autor(2, "George R. R. Martin", "Americano");
            autorDAO.inserir(autor1);
            autorDAO.inserir(autor2);

            // Inserir livros
            Livro livro1 = new Livro(1, "Harry Potter e a Pedra Filosofal", 1997, 1);
            Livro livro2 = new Livro(2, "Harry Potter e a Câmara Secreta", 1998, 1);
            Livro livro3 = new Livro(3, "A Guerra dos Tronos", 1996, 2);
            livroDAO.inserir(livro1);
            livroDAO.inserir(livro2);
            livroDAO.inserir(livro3);

            // Listar todos os autores
            System.out.println("Lista de autores:");
            List<Autor> autores = autorDAO.listar();
            for (Autor autor : autores) {
                System.out.println("ID: " + autor.getIdAutor() + ", Nome: " + autor.getNome() + ", Nacionalidade: " + autor.getNacionalidade());
            }

            // Listar todos os livros
            System.out.println("\nLista de livros:");
            List<Livro> livros = livroDAO.listar();
            for (Livro livro : livros) {
                System.out.println("ID: " + livro.getIdLivro() + ", Título: " + livro.getTitulo() + ", Ano: " + livro.getAnoPublicacao() + ", ID Autor: " + livro.getIdAutor());
            }

            // Listar livros de um autor específico
            System.out.println("\nLivros do autor J.K. Rowling:");
            List<Livro> livrosAutor1 = livroDAO.listarLivrosPorAutor(1);
            for (Livro livro : livrosAutor1) {
                System.out.println("ID: " + livro.getIdLivro() + ", Título: " + livro.getTitulo() + ", Ano: " + livro.getAnoPublicacao());
            }

            // Atualizar um autor
            Autor autorAtualizado = new Autor(1, "Joanne Rowling", "Britânica");
            autorDAO.atualizar(autorAtualizado);

            // Atualizar um livro
            Livro livroAtualizado = new Livro(1, "Harry Potter e a Pedra Filosofal", 1997, 1);
            livroDAO.atualizar(livroAtualizado);

            // Excluir um livro
            livroDAO.excluir(2);

            // Excluir um autor
            autorDAO.excluir(2);

            // Verificar resultados após exclusão
            System.out.println("\nLista de livros após exclusão:");
            livros = livroDAO.listar();
            for (Livro livro : livros) {
                System.out.println("ID: " + livro.getIdLivro() + ", Título: " + livro.getTitulo() + ", Ano: " + livro.getAnoPublicacao() + ", ID Autor: " + livro.getIdAutor());
            }

            System.out.println("\nLista de autores após exclusão:");
            autores = autorDAO.listar();
            for (Autor autor : autores) {
                System.out.println("ID: " + autor.getIdAutor() + ", Nome: " + autor.getNome() + ", Nacionalidade: " + autor.getNacionalidade());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
