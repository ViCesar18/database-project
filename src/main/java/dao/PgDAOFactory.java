package dao;

import java.sql.Connection;

public class PgDAOFactory extends DAOFactory{

    public PgDAOFactory(Connection connection) {
            this.connection = connection;
    }

    @Override
    public UsuarioDAO getUsuarioDAO() {
        return new PgUsuarioDAO(this.connection);
    }
}
