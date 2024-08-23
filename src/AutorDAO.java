import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AutorDAO {

    private Connection conexao;

    public AutorDAO() {
        this.conexao = ConexaoBD.getInstancia().getConexao();
    }

    public void inserir(Autor autor) throws SQLException {
        String sql = "INSERT INTO Autor (ID_Autor, Nome, Nacionalidade) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, autor.getIdAutor());
            stmt.setString(2, autor.getNome());
            stmt.setString(3, autor.getNacionalidade());
            stmt.executeUpdate();
        }
    }

    public void atualizar(Autor autor) throws SQLException {
        String sql = "UPDATE Autor SET Nome = ?, Nacionalidade = ? WHERE ID_Autor = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, autor.getNome());
            stmt.setString(2, autor.getNacionalidade());
            stmt.setInt(3, autor.getIdAutor());
            stmt.executeUpdate();
        }
    }

    public void excluir(int idAutor) throws SQLException {
        String sql = "DELETE FROM Autor WHERE ID_Autor = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, idAutor);
            stmt.executeUpdate();
        }
    }

    public Autor buscarPorId(int idAutor) throws SQLException {
        String sql = "SELECT * FROM Autor WHERE ID_Autor = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, idAutor);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Autor(
                        rs.getInt("ID_Autor"),
                        rs.getString("Nome"),
                        rs.getString("Nacionalidade")
                );
            }
            return null;
        }
    }

    public List<Autor> listar() throws SQLException {
        List<Autor> autores = new ArrayList<>();
        String sql = "SELECT * FROM Autor";
        try (Statement stmt = conexao.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                autores.add(new Autor(
                        rs.getInt("ID_Autor"),
                        rs.getString("Nome"),
                        rs.getString("Nacionalidade")
                ));
            }
        }
        return autores;
    }
}
