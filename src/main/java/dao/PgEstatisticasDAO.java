package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Estatisticas;

public class PgEstatisticasDAO implements EstatisticasDAO {
    private final Connection connection;

    private static final String POST_LIKES_QUERY =
        "SELECT p.id, COUNT(p.id) AS numero_likes " +
        "FROM rede_musical.post p " +
        "JOIN rede_musical.usuario_da_like_em_post udlep ON p.id = udlep.post_id " +
        "GROUP BY p.id " +
        "HAVING COUNT(p.id) >= ( " +
        "SELECT MAX(maior_numero_likes.numero_likes) " +
        "FROM ( " +
        "SELECT p.id AS id, COUNT(p.id) AS numero_likes " +
        "FROM rede_musical.post p  " +
        "JOIN rede_musical.usuario_da_like_em_post udlep ON p.id = udlep.post_id " +
        "GROUP BY p.id " +
        ") maior_numero_likes " +
        ")" +
        "LIMIT 1;";

    private static final String POST_COMENTARIOS_QUERY =
        "SELECT p.id, COUNT(p.id) AS numero_comentarios " +
        "FROM rede_musical.post p " +
        "JOIN rede_musical.comentario c ON p.id = c.post_id " +
        "GROUP BY p.id " +
        "HAVING COUNT(p.id) >= ( " +
        "SELECT MAX(maior_numero_comentarios.numero_comentarios) " +
        "FROM ( " +
        "SELECT p.id AS id, COUNT(p.id) AS numero_comentarios " +
        "FROM rede_musical.post p  " +
        "JOIN rede_musical.comentario c ON p.id = c.post_id " +
        "GROUP BY p.id " +
        ") maior_numero_comentarios " +
        ") " +
        "LIMIT 1;";

    private static final String POST_COMPARTILHAMENTOS_QUERY =
        "SELECT p.id, COUNT(p.id) AS numero_compartilhamentos " +
        "FROM rede_musical.post p " +
        "JOIN rede_musical.usuario_compartilha_post ucp ON p.id = ucp.post_id " +
        "GROUP BY p.id " +
        "HAVING COUNT(p.id) >= ( " +
        "SELECT MAX(maior_numero_compartilhamentos.numero_compartilhamentos) " +
        "FROM ( " +
        "SELECT p.id AS id, COUNT(p.id) AS numero_compartilhamentos " +
        "FROM rede_musical.post p  " +
        "JOIN rede_musical.usuario_compartilha_post ucp ON p.id = ucp.post_id " +
        "GROUP BY p.id " +
        ") maior_numero_compartilhamentos " +
        ") " +
        "LIMIT 1;";

    private static final String TOTAL_INTERACOES_QUERY =
        "SELECT numero_likes," +
            "numero_comentarios,"  +
            "numero_compartilhamentos,"  +
            "numero_likes + numero_comentarios + numero_compartilhamentos AS numero_interacoes " +
        "FROM ( " +
        "SELECT COUNT(*) numero_likes " +
        "FROM rede_musical.usuario_da_like_em_post " +
        ") likes, " +
        "( " +
        "SELECT COUNT(*) numero_comentarios " +
        "FROM rede_musical.comentario " +
        ") comentarios, " +
        "( " +
        "SELECT COUNT(*) numero_compartilhamentos " +
        "FROM rede_musical.usuario_compartilha_post " +
        ") compartilhamentos;";

    private static final String INSTRUMENTOS_MAIS_TOCADOS_QUERY =
        "SELECT ui.instrumento, COUNT(*) AS frequencia " +
        "FROM rede_musical.usuario_instrumentos ui " +
        "JOIN rede_musical.usuario u ON ui.usuario_id = u.id " +
        "WHERE u.sexo = ? " +
        "GROUP BY u.sexo, ui.instrumento " +
        "ORDER BY frequencia DESC;";

    private static final String GENERO_PREFERIDO_QUERY =
        "SELECT genero_favorito, COUNT(*) AS frequencia " +
        "FROM rede_musical.usuario u " +
        "WHERE sexo = ? " +
        "GROUP BY sexo, genero_favorito " +
        "ORDER BY frequencia DESC;";

    public PgEstatisticasDAO(Connection connection) { this.connection = connection; }

    @Override
    public Estatisticas buscarPostMaiorNumeroLikes() throws SQLException {
        Estatisticas estatisticas = new Estatisticas();

        try(PreparedStatement statement = connection.prepareStatement(POST_LIKES_QUERY)) {
            try(ResultSet result = statement.executeQuery()) {
                if(result.next()) {
                    estatisticas.setId(result.getInt("id"));
                    estatisticas.setNumeroInteracoes(result.getInt("numero_likes"));
                }
            }
        } catch (SQLException e) {
            Logger.getLogger(PgUsuarioDAO.class.getName()).log(Level.SEVERE, "DAO", e);
            throw new SQLException("Erro ao vizualisar post com mais likes.");
        }

        return estatisticas;
    }

    @Override
    public Estatisticas buscarPostMaiorNumeroComentarios() throws SQLException {
        Estatisticas estatisticas = new Estatisticas();

        try(PreparedStatement statement = connection.prepareStatement(POST_COMENTARIOS_QUERY)) {
            try(ResultSet result = statement.executeQuery()) {
                if(result.next()) {
                    estatisticas.setId(result.getInt("id"));
                    estatisticas.setNumeroInteracoes(result.getInt("numero_comentarios"));
                }
            }
        } catch (SQLException e) {
            Logger.getLogger(PgUsuarioDAO.class.getName()).log(Level.SEVERE, "DAO", e);
            throw new SQLException("Erro ao vizualisar post com mais comentários.");
        }

        return estatisticas;
    }

    @Override
    public Estatisticas buscarPostMaiorNumeroCompartilhamentos() throws SQLException {
        Estatisticas estatisticas = new Estatisticas();

        try(PreparedStatement statement = connection.prepareStatement(POST_COMPARTILHAMENTOS_QUERY)) {
            try(ResultSet result = statement.executeQuery()) {
                if(result.next()) {
                    estatisticas.setId(result.getInt("id"));
                    estatisticas.setNumeroInteracoes(result.getInt("numero_compartilhamentos"));
                }
            }
        } catch (SQLException e) {
            Logger.getLogger(PgUsuarioDAO.class.getName()).log(Level.SEVERE, "DAO", e);
            throw new SQLException("Erro ao vizualisar post com mais compartilhamentos.");
        }

        return estatisticas;
    }

    @Override
    public Estatisticas buscarNumeroInteracoes() throws SQLException {
        Estatisticas estatisticas = new Estatisticas();

        try(PreparedStatement statement = connection.prepareStatement(TOTAL_INTERACOES_QUERY)) {
            try(ResultSet result = statement.executeQuery()) {
                if(result.next()) {
                    estatisticas.setNumeroLikes(result.getInt("numero_likes"));
                    estatisticas.setNumeroComentarios(result.getInt("numero_comentarios"));
                    estatisticas.setNumeroCompartilhamentos(result.getInt("numero_compartilhamentos"));
                    estatisticas.setNumeroInteracoes(result.getInt("numero_interacoes"));
                }
            }
        } catch (SQLException e) {
            Logger.getLogger(PgUsuarioDAO.class.getName()).log(Level.SEVERE, "DAO", e);
            throw new SQLException("Erro ao vizualisar Número de Interações.");
        }

        return estatisticas;
    }

    @Override
    public List<Estatisticas> buscarInstrumentosMaisTocadosMulheres() throws SQLException {
        List<Estatisticas> estatisticasList = new ArrayList<>();

        try(PreparedStatement statement = connection.prepareStatement(INSTRUMENTOS_MAIS_TOCADOS_QUERY)) {
            statement.setString(1, "F");

            try(ResultSet result = statement.executeQuery()) {
                while(result.next()) {
                    Estatisticas estatisticas = new Estatisticas();

                    estatisticas.setInstrumento(result.getString("instrumento"));
                    estatisticas.setFrequencia(result.getInt("frequencia"));

                    estatisticasList.add(estatisticas);
                }
            }
        } catch (SQLException e) {
            Logger.getLogger(PgUsuarioDAO.class.getName()).log(Level.SEVERE, "DAO", e);
            throw new SQLException("Erro ao vizualisar instrumento mais tocado por mulheres.");
        }

        return estatisticasList;
    }

    @Override
    public List<Estatisticas> buscarInstrumentosMaisTocadosHomens() throws SQLException {
        List<Estatisticas> estatisticasList = new ArrayList<>();

        try(PreparedStatement statement = connection.prepareStatement(INSTRUMENTOS_MAIS_TOCADOS_QUERY)) {
            statement.setString(1, "M");

            try(ResultSet result = statement.executeQuery()) {
                while(result.next()) {
                    Estatisticas estatisticas = new Estatisticas();

                    estatisticas.setInstrumento(result.getString("instrumento"));
                    estatisticas.setFrequencia(result.getInt("frequencia"));

                    estatisticasList.add(estatisticas);
                }
            }
        } catch (SQLException e) {
            Logger.getLogger(PgUsuarioDAO.class.getName()).log(Level.SEVERE, "DAO", e);
            throw new SQLException("Erro ao vizualisar instrumento mais tocado por homens.");
        }

        return estatisticasList;
    }

    @Override
    public List<Estatisticas> buscarGeneroPreferidoMulheres() throws SQLException {
        List<Estatisticas> estatisticasList = new ArrayList<>();

        try(PreparedStatement statement = connection.prepareStatement(GENERO_PREFERIDO_QUERY)) {
            statement.setString(1, "F");

            try(ResultSet result = statement.executeQuery()) {
                while(result.next()) {
                    Estatisticas estatisticas = new Estatisticas();

                    estatisticas.setGeneroFavorito(result.getString("genero_favorito"));
                    estatisticas.setFrequencia(result.getInt("frequencia"));

                    estatisticasList.add(estatisticas);
                }
            }
        } catch (SQLException e) {
            Logger.getLogger(PgUsuarioDAO.class.getName()).log(Level.SEVERE, "DAO", e);
            throw new SQLException("Erro ao vizualisar gênero preferido das mulheres.");
        }

        return estatisticasList;
    }

    @Override
    public List<Estatisticas> buscarGeneroPreferidoHomens() throws SQLException {
        List<Estatisticas> estatisticasList = new ArrayList<>();

        try(PreparedStatement statement = connection.prepareStatement(GENERO_PREFERIDO_QUERY)) {
            statement.setString(1, "M");

            try(ResultSet result = statement.executeQuery()) {
                while(result.next()) {
                    Estatisticas estatisticas = new Estatisticas();

                    estatisticas.setGeneroFavorito(result.getString("genero_favorito"));
                    estatisticas.setFrequencia(result.getInt("frequencia"));

                    estatisticasList.add(estatisticas);
                }
            }
        } catch (SQLException e) {
            Logger.getLogger(PgUsuarioDAO.class.getName()).log(Level.SEVERE, "DAO", e);
            throw new SQLException("Erro ao vizualisar gênero preferido dos homens.");
        }

        return estatisticasList;
    }
}
