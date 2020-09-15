package dao;

import model.Banda;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PgBandaDAO implements BandaDAO {
    private final Connection connection;

    private static final String CREATE_QUERY =
            "INSERT INTO rede_musical.banda" +
            "(sigla, nome, genero_musical, imagem, usuario_id)" +
            "VALUES(?, ?, ?, ?, ?);";

    private static final String READ_QUERY =
            "SELECT sigla, nome, genero_musical, imagem, usuario_id" +
            " FROM rede_musical.banda" +
            " WHERE id = ?;";

    private static final String UPDATE_BANDA_DATA_QUERY =
            "UPDATE rede_musical.banda" +
            " SET sigla = ?, nome = ?, genero_musical = ?, imagem = ?, usuario_id = ?" +
            " WHERE id = ?;";

    private static final String DELETE_QUERY =
            "DELETE FROM rede_musical.banda" +
            " WHERE id = ?;";

    private static final String ALL_QUERY =
            "SELECT id, nome, sigla, genero_musical, usuario_id" +
            " FROM rede_musical.banda" +
            " ORDER BY id;";

    @Override
    public void create(Banda banda) throws SQLException {
        try (PreparedStatement statement = this.connection.prepareStatement(CREATE_QUERY)) {
            statement.setString(1, banda.getSigla());
            statement.setString(2, banda.getNome());
            statement.setString(3, banda.getGenero());
            statement.setString(4, banda.getImagem());
            statement.setInt(5, banda.getUsername_id());

            statement.executeUpdate();
        } catch (SQLException e){
            Logger.getLogger(PgBandaDAO.class.getName()).log(Level.SEVERE,"DAO", e);

            if (e.getMessage().contains("uq_banda_sigla")) {
                throw new SQLException("Erro ao inserir banda: sigla já existente");
            }
            else if(e.getMessage().contains("not-null")) {
                throw new SQLException("Erro ao inserir banda: pelo menos um campo está em branco.");
            }
            else {
                throw new SQLException("Erro ao inserir banda.");
            }
        }
    }

    @Override
    public Banda read(Integer id) throws SQLException {
        Banda banda = new Banda();

        try (PreparedStatement statement = connection.prepareStatement(READ_QUERY)){
            statement.setInt(1, id);

            try (ResultSet result = statement.executeQuery()){
                if (result.next()){
                    banda.setId(id);
                    banda.setSigla(result.getString("sigla"));
                    banda.setNome(result.getString("nome"));
                    banda.setGenero(result.getString("genero_musical"));
                    banda.setImagem(result.getString("imagem"));
                    banda.setUsername_id(result.getInt("username_id"));
                }
                else {
                    throw new SQLException("Erro ao vizualizar: banda não encontrada");
                }
            }
        } catch(SQLException e){
            Logger.getLogger(PgBandaDAO.class.getName()).log(Level.SEVERE, "DAO", e);

            if (e.getMessage().equals("Erro ao vizualizar: banda não encontrada")) {
                throw e;
            }
            else{
                throw new SQLException("Erro ao vizualizar.");
            }
        }

        return banda;
    }

    @Override
    public void update(Banda banda) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_BANDA_DATA_QUERY)) {
            statement.setString(1, banda.getSigla());
            statement.setString(2, banda.getNome());
            statement.setString(3, banda.getGenero());
            statement.setString(4, banda.getImagem());
            statement.setInt(5, banda.getUsername_id());

            if (statement.executeUpdate() < 1){
                throw new SQLException("Erro ao editar: banda não encontrada.");
            }
        } catch (SQLException e){
            Logger.getLogger(PgBandaDAO.class.getName()).log(Level.SEVERE, "DAO", e);

            if (e.getMessage().equals("Erro ao editar: banda não encontrada.")){
                throw e;
            }
            else if (e.getMessage().contains("uq_banda_sigla")){
                throw new SQLException("Erro ao editar: banda já existe.");
            }
            else if(e.getMessage().contains("not-null")) {
                throw new SQLException("Erro ao editar: pelo menos um campo está em branco.");
            }
            else {
                throw new SQLException("Erro ao editar.");
            }
        }
    }

    @Override
    public void delete(Integer id) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(DELETE_QUERY)){
            statement.setInt(1, id);

            if (statement.executeUpdate() < 1){
                throw new SQLException("Erro ao deletar: banda não encontrada.");
            }
        } catch (SQLException e){
            Logger.getLogger(PgBandaDAO.class.getName()).log(Level.SEVERE, "DAO", e);

            if (e.getMessage().equals("Erro ao deletar: banda não encontrada")){
                throw e;
            }
            else{
                throw new SQLException("Erro ao deletar");
            }
        }
    }

    @Override
    public List<Banda> all() throws SQLException {
        List<Banda> bandas = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(ALL_QUERY);
             ResultSet result = statement.executeQuery()){
            while (result.next()){
                Banda banda = new Banda();

                banda.setId(result.getInt("id"));
                banda.setSigla(result.getString("sigla"));
                banda.setNome(result.getString("nome"));
                banda.setGenero(result.getString("genero_musical"));
                banda.setUsername_id(result.getInt("usuario_id"));

                bandas.add (banda);
            }
        } catch (SQLException e){
            Logger.getLogger(PgBandaDAO.class.getName()).log(Level.SEVERE, "DAO", e);

            throw new SQLException("Erro ao listar bandas");
        }

        return bandas;

    }

    public PgBandaDAO(Connection connection) {
        this.connection = connection;
    }
}