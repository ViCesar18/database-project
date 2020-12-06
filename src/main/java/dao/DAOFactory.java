package dao;

import jdbc.ConnectionFactory;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public abstract class DAOFactory implements AutoCloseable{
    protected Connection connection;

    public static DAOFactory getInstance() throws ClassNotFoundException, IOException, SQLException {
        Connection connection = ConnectionFactory.getInstance().getConnection();
        DAOFactory factory;

        if(ConnectionFactory.getDbServer().equals("postgresql")) {
            factory = new PgDAOFactory(connection);
        }
        else {
            throw new RuntimeException("Servidor de banco de dados não suportado.");
        }

        return factory;
    }

    public void closeConnection() throws SQLException {
        try {
            this.connection.close();
        } catch(SQLException e) {
            System.err.println(e.getMessage());

            throw new SQLException("Erro ao fechar conexão ao banco de dados.");
        }
    }

    public abstract UsuarioDAO getUsuarioDAO();

    public abstract BandaDAO getBandaDAO();

    public abstract EventoDAO getEventoDAO();

    public abstract PesquisaDAO getPesquisaDAO();

    public abstract FeedDAO getFeedDAO();

    @Override
    public void close() throws Exception {
        this.closeConnection();
    }
}
