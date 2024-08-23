import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LivroDAO {

    private Connection conexao;

    public LivroDAO() {
        this.conexao = ConexaoBD.getInstancia().getConexao();
    }

    public void inserir(Livro livro) throws SQLException {
        String sql = "INSERT INTO Livro (ID_Livro, Titulo, Ano_Publicacao, ID_Autor) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, livro.getIdLivro());
            stmt.setString(2, livro.getTitulo());
            stmt.setInt(3, livro.getAnoPublicacao());
            stmt.setInt(4, livro.getIdAutor());
            stmt.executeUpdate();
        }
    }

    public void atualizar(Livro livro) throws SQLException {
        String sql = "UPDATE Livro SET Titulo = ?, Ano_Publicacao = ?, ID_Autor = ? WHERE ID_Livro = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, livro.getTitulo());
            stmt.setInt(2, livro.getAnoPublicacao());
            stmt.setInt(3, livro.getIdAutor());
            stmt.setInt(4, livro.getIdLivro());
            stmt.executeUpdate();
        }
    }

    public void excluir(int idLivro) throws SQLException {
        String sql = "DELETE FROM Livro WHERE ID_Livro = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, idLivro);
            stmt.executeUpdate();
        }
    }

    public Livro buscarPorId(int idLivro) throws SQLException {
        String sql = "SELECT * FROM Livro WHERE ID_Livro = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, idLivro);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Livro(
                        rs.getInt("ID_Livro"),
                        rs.getString("Titulo"),
                        rs.getInt("Ano_Publicacao"),
                        rs.getInt("ID_Autor")
                );
            }
            return null;
        }
    }

    public List<Livro> listar() throws SQLException {
        List<Livro> livros = new ArrayList<>();
        String sql = "SELECT * FROM Livro";
        try (Statement stmt = conexao.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                livros.add(new Livro(
                        rs.getInt("ID_Livro"),
                        rs.getString("Titulo"),
                        rs.getInt("Ano_Publicacao"),
                        rs.getInt("ID_Autor")
                ));
            }
        }
        return livros;
    }

    public List<Livro> listarLivrosPorAutor(int idAutor) throws SQLException {
        List<Livro> livros = new ArrayList<>();
        String sql = "SELECT * FROM Livro WHERE ID_Autor = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, idAutor);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                livros.add(new Livro(
                        rs.getInt("ID_Livro"),
                        rs.getString("Titulo"),
                        rs.getInt("Ano_Publicacao"),
                        rs.getInt("ID_Autor")
                ));
            }
        }
        return livros;
    }
}
