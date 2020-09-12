package dao;

import model.Evento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PgEventoDAO implements EventoDAO {
    private final Connection connection;

    private static final String CREATE_QUERY =
            "INSERT INTO rede_musical.evento" +
                    "(nome, descricao, data, numero_participantes, categoria)" +
                    "VALUES(?, ?, ?, ?, ?, ?);";

    private static final String READ_QUERY =
            "SELECT nome, descricao, data, numero_participantes, categoria" +
                    "FROM rede_musical.evento" +
                    "WHERE id = ?;";

    private static final String UPDATE_EVENTO_DATA_QUERY =
            "UPDATE rede_musical.evento" +
                    "SET nome = ?, descricao = ?, data = ?, numero_participantes = ?, categoria = ?" +
                    "WHERE id = ?;";

    private static final String DELETE_QUERY =
            "DELETE rede_musical.evento" +
                    "WHERE id = ?;";

    private static final String ALL_QUERY =
            "SELECT id, nome" +
                    "FROM rede_musical.evento" +
                    "ORDER BY id;";

    @Override
    public void create(Evento evento) throws SQLException {
        try (PreparedStatement statement = this.connection.prepareStatement(CREATE_QUERY)) {
            statement.setString(1, evento.getNome());
            statement.setString(2, evento.getDescricao());
            statement.setTimestamp(3, evento.getData());
            statement.setInt(4, evento.getnParticipantes());
            statement.setString(5, evento.getCategoria());

            statement.executeUpdate();
        } catch (SQLException e){
            Logger.getLogger(PgEventoDAO.class.getName()).log(Level.SEVERE,"DAO", e);

            if (e.getMessage().contains("uq_evento_id")) {
                throw new SQLException("Erro ao inserir evento: id já existente");
            }
            else if(e.getMessage().contains("not-null")) {
                throw new SQLException("Erro ao inserir evento: pelo menos um campo está em branco.");
            }
            else {
                throw new SQLException("Erro ao inserir evento.");
            }
        }
    }

    @Override
    public Evento read(Integer id) throws SQLException {
        Evento evento = new Evento();

        try (PreparedStatement statement = connection.prepareStatement(READ_QUERY)){
            statement.setInt(1, id);

            try (ResultSet result = statement.executeQuery()){
                if (result.next()){
                    evento.setId(id);
                    evento.setNome(result.getString("nome"));
                    evento.setDescricao(result.getString("descricao"));
                    evento.setData(result.getTimestamp("data"));
                    evento.setnParticipantes(result.getInt("numero_participantes"));
                    evento.setCategoria(result.getString("categoria"));
                }
                else {
                    throw new SQLException("Erro ao vizualizar: evento não encontrado");
                }
            }
        } catch(SQLException e){
            Logger.getLogger(PgEventoDAO.class.getName()).log(Level.SEVERE, "DAO", e);

            if (e.getMessage().equals("Erro ao vizualizar: evento não encontrado")) {
                throw e;
            }
            else{
                throw new SQLException("Erro ao vizualizar.");
            }
        }

        return evento;
    }

    @Override
    public void update(Evento evento) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_EVENTO_DATA_QUERY)) {
            statement.setString(1, evento.getNome());
            statement.setString(2, evento.getDescricao());
            statement.setTimestamp(3, evento.getData());
            statement.setInt(4, evento.getnParticipantes());
            statement.setString(5, evento.getCategoria());

            if (statement.executeUpdate() < 1){
                throw new SQLException("Erro ao editar: evento não encontrado.");
            }
        } catch (SQLException e){
            Logger.getLogger(PgEventoDAO.class.getName()).log(Level.SEVERE, "DAO", e);

            if (e.getMessage().equals("Erro ao editar: evento não encontrado.")){
                throw e;
            }
            else if (e.getMessage().contains("uq_evento_id")){
                throw new SQLException("Erro ao editar: evento já existe.");
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
                throw new SQLException("Erro ao deletar: evento não encontrado.");
            }
        } catch (SQLException e){
            Logger.getLogger(PgEventoDAO.class.getName()).log(Level.SEVERE, "DAO", e);

            if (e.getMessage().equals("Erro ao deletar: evento não encontrado")){
                throw e;
            }
            else{
                throw new SQLException("Erro ao deletar");
            }
        }
    }

    @Override
    public List<Evento> all() throws SQLException {
        List<Evento> eventos = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(ALL_QUERY);
             ResultSet result = statement.executeQuery()){
            while (result.next()){
                Evento evento = new Evento();

                evento.setId(result.getInt("id"));
                evento.setNome(result.getString("nome"));

                eventos.add (evento);
            }
        } catch (SQLException e){
            Logger.getLogger(PgEventoDAO.class.getName()).log(Level.SEVERE, "DAO", e);

            throw new SQLException("Erro ao listar eventos");
        }

        return eventos;

    }

    public PgEventoDAO(Connection connection) {
        this.connection = connection;
    }
}
