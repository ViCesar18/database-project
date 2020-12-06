package dao;

import model.Pesquisa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PgPesquisaDAO implements PesquisaDAO{
    private final Connection connection;

    private static final String SEARCH_USER_QUERY =
            "SELECT id, username, CONCAT(pnome, ' ', snome) AS nome " +
            "FROM rede_musical.usuario " +
            "WHERE LOWER(username) LIKE LOWER(?) " +
            "OR LOWER(pnome) LIKE LOWER(?) " +
            "OR LOWER(snome) LIKE LOWER(?);";

    private static final String SEARCH_BAND_QUERY =
            "SELECT id, sigla, nome, usuario_id " +
            "FROM rede_musical.banda " +
            "WHERE LOWER(sigla) LIKE LOWER(?) " +
            "OR LOWER(nome) LIKE LOWER(?);";

    private static final String SEARCH_EVENT_QUERY =
            "SELECT id, nome " +
            "FROM rede_musical.evento " +
            "WHERE LOWER(nome) LIKE LOWER(?);";

    public PgPesquisaDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Pesquisa> pesquisar(Pesquisa pesquisa) throws SQLException {
        List<Pesquisa> pesquisas = new ArrayList<>();

        if(pesquisa.getFiltroUsuario()) {
            try (PreparedStatement statement = connection.prepareStatement(SEARCH_USER_QUERY)) {
                statement.setString(1, pesquisa.getPesquisa() + "%");
                statement.setString(2, pesquisa.getPesquisa() + "%");
                statement.setString(3, "%" + pesquisa.getPesquisa() + "%");

                try(ResultSet result = statement.executeQuery()) {
                    while(result.next()) {
                        Pesquisa pesquisaUsuario = new Pesquisa();

                        pesquisaUsuario.setIdUsuario(result.getInt("id"));
                        pesquisaUsuario.setUsernameUsuario(result.getString("username"));
                        pesquisaUsuario.setNomeUsuario(result.getString("nome"));

                        pesquisas.add(pesquisaUsuario);
                    }
                }
            } catch(SQLException e) {
                Logger.getLogger(PgPesquisaDAO.class.getName()).log(Level.SEVERE, "DAO", e);

                throw new SQLException("Erro ao listar pesquisas (usuario).");
            }
        }

        if(pesquisa.getFiltroBanda()) {
            try (PreparedStatement statement = connection.prepareStatement(SEARCH_BAND_QUERY)) {
                statement.setString(1, pesquisa.getPesquisa() + "%");
                statement.setString(2, pesquisa.getPesquisa() + "%");

                try(ResultSet result = statement.executeQuery()) {
                    while(result.next()) {
                        Pesquisa pesquisaBanda = new Pesquisa();

                        pesquisaBanda.setIdBanda(result.getInt("id"));
                        pesquisaBanda.setSiglaBanda(result.getString("sigla"));
                        pesquisaBanda.setNomeBanda(result.getString("nome"));
                        pesquisaBanda.setIdCriadorBanda(result.getInt("usuario_id"));

                        pesquisas.add(pesquisaBanda);
                    }
                }
            } catch(SQLException e) {
                Logger.getLogger(PgPesquisaDAO.class.getName()).log(Level.SEVERE, "DAO", e);

                throw new SQLException("Erro ao listar pesquisas (banda).");
            }
        }

        if(pesquisa.getFiltroEvento()) {
            try (PreparedStatement statement = connection.prepareStatement(SEARCH_EVENT_QUERY)) {
                statement.setString(1, pesquisa.getPesquisa() + "%");

                try(ResultSet result = statement.executeQuery()) {
                    while(result.next()) {
                        Pesquisa pesquisaEvento = new Pesquisa();

                        pesquisaEvento.setIdEvento(result.getInt("id"));
                        pesquisaEvento.setNomeEvento(result.getString("nome"));

                        pesquisas.add(pesquisaEvento);
                    }
                }
            } catch(SQLException e) {
                Logger.getLogger(PgPesquisaDAO.class.getName()).log(Level.SEVERE, "DAO", e);

                throw new SQLException("Erro ao listar pesquisas (evento).");
            }
        }

        return pesquisas;
    }

    @Override
    public void create(Pesquisa pesquisa) throws SQLException {

    }

    @Override
    public Pesquisa read(Integer id) throws SQLException {
        return null;
    }

    @Override
    public void update(Pesquisa pesquisa) throws SQLException {

    }

    @Override
    public void delete(Integer id) throws SQLException {

    }

    @Override
    public List<Pesquisa> all() throws SQLException {
        return null;
    }
}
