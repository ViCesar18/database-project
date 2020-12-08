package dao;

import model.Banda;
import model.Post;
import model.Usuario;

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
            " SET sigla = ?, nome = ?, genero_musical = ?" +
            " WHERE id = ?;";

    private static final String UPDATE_BANDA_IMAGEM =
            "UPDATE rede_musical.banda" +
            " SET imagem = ?" +
            " WHERE id = ?;";

    private static final String DELETE_QUERY =
            "DELETE FROM rede_musical.banda" +
            " WHERE id = ?;";

    private static final String ALL_QUERY =
            "SELECT id, nome, sigla, genero_musical, usuario_id" +
            " FROM rede_musical.banda" +
            " ORDER BY id;";

    private static final String INSERT_USUARIO_SEGUE_BANDA =
            "INSERT INTO rede_musical.usuario_segue_banda " +
            "(usuario_id, banda_id) " +
            "VALUES(?, ?);";

    private static final String DELETE_USUARIO_SEGUE_BANDA =
            "DELETE FROM rede_musical.usuario_segue_banda " +
            "WHERE usuario_id = ? AND banda_id = ?;";

    private static final String READ_USUARIO_SEGUE_BANDA =
            "SELECT COUNT(*) > 0 AS segue " +
            "FROM rede_musical.usuario_segue_banda " +
            "WHERE usuario_id = ? AND banda_id = ?;";

    private static final String NUMERO_DE_SEGUIDORES =
            "SELECT COUNT(*) AS seguidores " +
            "FROM rede_musical.usuario_segue_banda " +
            "WHERE banda_id = ?;";

    private static String INSERT_USUARIO_PARTICIPA_BANDA =
            "INSERT INTO rede_musical.usuario_participa_de_banda" +
            "(usuario_id, banda_id, instrumento)" +
            "VALUES(?,?,?);";

    private static final String DELETE_USUARIO_PARTICIPA_BANDA =
            "DELETE FROM rede_musical.usuario_participa_de_banda " +
            "WHERE usuario_id = ? AND banda_id = ?;";

    private static final String READ_USUARIO_PARTICIPA_BANDA =
            "SELECT COUNT(*) > 0 AS participa " +
            "FROM rede_musical.usuario_participa_de_banda " +
            "WHERE usuario_id = ? AND banda_id = ?;";

    private static final String NUMERO_DE_PARTICIPANTES =
            "SELECT COUNT(*) AS participantes " +
            "FROM rede_musical.usuario_participa_de_banda " +
            "WHERE banda_id = ?;";

    private static final String GET_ID_BANDA =
            "SELECT id " +
            "FROM rede_musical.banda " +
            "WHERE nome = ? AND SIGLA = ?;";

    private static final String ALL_POSTS_OF_BAND =
        "SELECT pb.banda_id, p.*, u.pnome, u.snome, u.imagem AS imagem_usuario , b.nome, b.sigla, b.imagem AS imagem_banda FROM rede_musical.post_banda pb " +
        "JOIN rede_musical.post p ON p.id = pb.post_id " +
        "JOIN rede_musical.usuario u ON p.usuario_id = u.id " +
        "JOIN rede_musical.banda b ON b.id = pb.banda_id " +
        "WHERE pb.banda_id = ?" +
        "ORDER BY dt_publicacao DESC;";

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
                    banda.setUsername_id(result.getInt("usuario_id"));
                }
                else {
                    throw new SQLException("Erro ao visualizar: banda não encontrada");
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
        String query;
        if(banda.getImagem() != null && !banda.getImagem().isBlank()) {
            query = UPDATE_BANDA_IMAGEM;
        }
        else{
            query = UPDATE_BANDA_DATA_QUERY;
        }

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            if(banda.getImagem() != null && !banda.getImagem().isBlank()) {
                statement.setString(1, banda.getImagem());
                statement.setInt(2, banda.getId());
            }
            else{
                statement.setString(1, banda.getSigla());
                statement.setString(2, banda.getNome());
                statement.setString(3, banda.getGenero());
                statement.setInt(4, banda.getId());
            }


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

    @Override
    public void insertUsuarioSegueBanda(Integer usuarioId, Integer bandaId) throws SQLException {
        try(PreparedStatement statement = connection.prepareStatement(INSERT_USUARIO_SEGUE_BANDA)) {
            statement.setInt(1, usuarioId);
            statement.setInt(2, bandaId);

            statement.executeUpdate();
        } catch(SQLException e) {
            Logger.getLogger(PgUsuarioDAO.class.getName()).log(Level.SEVERE, "DAO", e);

            throw new SQLException("Erro ao seguir banda.");
        }
    }

    @Override
    public void deleteUsuarioSegueBanda(Integer usuarioId, Integer bandaId) throws SQLException {
        try(PreparedStatement statement = connection.prepareStatement(DELETE_USUARIO_SEGUE_BANDA)) {
            statement.setInt(1, usuarioId);
            statement.setInt(2, bandaId);

            statement.executeUpdate();
        } catch(SQLException e) {
            Logger.getLogger(PgUsuarioDAO.class.getName()).log(Level.SEVERE, "DAO", e);

            throw new SQLException("Erro ao deixar de seguir banda.");
        }
    }

    @Override
    public Boolean readUsuarioSegueBanda(Integer usuarioId, Integer bandaId) throws SQLException {
        Boolean segue = false;

        try(PreparedStatement statement = connection.prepareStatement(READ_USUARIO_SEGUE_BANDA)) {
            statement.setInt(1, usuarioId);
            statement.setInt(2, bandaId);

            try(ResultSet result = statement.executeQuery()) {
                if(result.next()) {
                    String segueStr = result.getString("segue");

                    segue = segueStr.equals("t") ? true : false;
                }
            }
        } catch(SQLException e) {
            Logger.getLogger(PgUsuarioDAO.class.getName()).log(Level.SEVERE, "DAO", e);

            throw new SQLException("Erro verificar se usuario segue banda.");
        }

        return segue;
    }

    @Override
    public Integer readNumeroSeguidores(Integer id) throws SQLException {
        Integer seguidores = null;

        try(PreparedStatement statement = connection.prepareStatement(NUMERO_DE_SEGUIDORES)) {
            statement.setInt(1, id);

            try(ResultSet result = statement.executeQuery()) {
                if(result.next()) {
                    seguidores = Integer.parseInt(result.getString("seguidores"));
                }
            }
        } catch(SQLException e) {
            Logger.getLogger(PgUsuarioDAO.class.getName()).log(Level.SEVERE, "DAO", e);

            throw new SQLException("Erro verificar numero de seguidores da banda.");
        }

        return seguidores;
    }

    public void insertUsuarioParticipaBanda(Integer idUsuario, Integer idBanda, String instrumento) throws SQLException{
        try (PreparedStatement statement = connection.prepareStatement(INSERT_USUARIO_PARTICIPA_BANDA)){
            statement.setInt(1, idUsuario);
            statement.setInt(2, idBanda);
            statement.setString(3, instrumento);

            statement.executeUpdate();
        } catch (SQLException e){
            Logger.getLogger(PgUsuarioDAO.class.getName()).log(Level.SEVERE, "DAO", e);

            throw new SQLException("Erro ao participar de banda");
        }
    }

    public void deleteUsuarioParticipaBanda(Integer idUsuario, Integer idBanda) throws SQLException{
        try (PreparedStatement statement = connection.prepareStatement(DELETE_USUARIO_PARTICIPA_BANDA)){
            statement.setInt(1, idUsuario);
            statement.setInt(2, idBanda);

            statement.executeUpdate();
        } catch (SQLException e){
            Logger.getLogger(PgUsuarioDAO.class.getName()).log(Level.SEVERE, "DAO", e);

            throw new SQLException("Erro ao deixa de participar em banda");        }
    }

    public Boolean readUsuarioParticipaBanda(Integer idUsuario, Integer idBanda) throws SQLException{
        Boolean participa = false;

        try (PreparedStatement statement = connection.prepareStatement(READ_USUARIO_PARTICIPA_BANDA)){
            statement.setInt(1, idUsuario);
            statement.setInt(2, idBanda);

            try (ResultSet result = statement.executeQuery()){
                if (result.next()) {
                    String participaStr = result.getString("participa");

                    participa = participaStr.equals("t") ? true : false;
                }
            } catch (SQLException e){
                Logger.getLogger(PgUsuarioDAO.class.getName()).log(Level.SEVERE, "DAO", e);

                throw new SQLException("Erro ao verificar a participação do usuario em uma banda.");
            }

            return participa;
        }
    }

    public Integer readNumeroParticipantes(Integer id) throws SQLException {
        Integer participantes = null;

        try(PreparedStatement statement = connection.prepareStatement(NUMERO_DE_PARTICIPANTES)) {
            statement.setInt(1, id);

            try(ResultSet result = statement.executeQuery()) {
                if(result.next()) {
                    participantes = Integer.parseInt(result.getString("participantes"));
                }
            }
        } catch(SQLException e) {
            Logger.getLogger(PgUsuarioDAO.class.getName()).log(Level.SEVERE, "DAO", e);

            throw new SQLException("Erro verificar numero de participantes da banda.");
        }

        return participantes;
    }

    @Override
    public Integer getBandaId(String bandaNome, String bandaSigla) throws SQLException {
        Integer id;

        try (PreparedStatement statement = connection.prepareStatement(GET_ID_BANDA)){
            statement.setString(1, bandaNome);
            statement.setString(2, bandaSigla);

            try (ResultSet result = statement.executeQuery()){
                if (result.next()){
                    id = result.getInt("id");
                }
                else {
                    throw new SQLException("Erro ao buscar ID: banda não encontrada");
                }
            }
        } catch(SQLException e){
            Logger.getLogger(PgBandaDAO.class.getName()).log(Level.SEVERE, "DAO", e);

            if (e.getMessage().equals("Erro ao buscar ID: banda não encontrada")) {
                throw e;
            }
            else{
                throw new SQLException("Erro ao buscar ID.");
            }
        }

        return id;
    }

    @Override
    public List<Post> allPostsBanda(Integer bandaId) throws SQLException {
        List<Post> posts = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(ALL_POSTS_OF_BAND)) {
            statement.setInt(1, bandaId);
            try(ResultSet result = statement.executeQuery()){
                while(result.next()) {
                    Post post = new Post();
                    Usuario usuario = new Usuario();
                    Banda banda = new Banda();

                    post.setId(result.getInt("id"));
                    post.setTextoPost(result.getString("texto_post"));
                    post.setImagem(result.getString("imagem"));
                    post.setDtPublicacao(result.getTimestamp("dt_publicacao"));
                    post.setUsuarioId(result.getInt("usuario_id"));
                    usuario.setId(result.getInt("usuario_id"));
                    usuario.setpNome(result.getString("pnome"));
                    usuario.setsNome(result.getString("snome"));
                    usuario.setImagem(result.getString("imagem_usuario"));
                    post.setUsuario(usuario);
                    banda.setId(bandaId);
                    banda.setNome(result.getString("nome"));
                    banda.setSigla(result.getString("sigla"));
                    banda.setImagem(result.getString("imagem_banda"));
                    post.setBanda(banda);

                    posts.add(post);
                }
            }catch (SQLException e) {
                throw new SQLException("Erro listar posts de banda.");
            }
        } catch(SQLException e) {
            Logger.getLogger(PgUsuarioDAO.class.getName()).log(Level.SEVERE, "DAO", e);

            throw new SQLException("Erro ao listar posts de banda.");
        }

        return posts;
    }
}