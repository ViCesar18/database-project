package dao;

import model.Post;

import java.sql.Connection;

public class PgDAOFactory extends DAOFactory{

    public PgDAOFactory(Connection connection) {
            this.connection = connection;
    }

    @Override
    public UsuarioDAO getUsuarioDAO() {
        return new PgUsuarioDAO(this.connection);
    }
    public BandaDAO getBandaDAO(){ return new PgBandaDAO(this.connection);}

    public EventoDAO getEventoDAO(){ return new PgEventoDAO(this.connection);}

    public PesquisaDAO getPesquisaDAO(){ return new PgPesquisaDAO(this.connection);}

    public FeedDAO getFeedDAO() { return new PgFeedDAO(this.connection); }

    public PostDAO getPostDAO() { return new PgPostDAO(this.connection);}
}
