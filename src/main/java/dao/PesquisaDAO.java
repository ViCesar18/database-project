package dao;

import model.Pesquisa;

import java.sql.SQLException;
import java.util.List;

public interface PesquisaDAO extends DAO<Pesquisa> {

    List<Pesquisa> pesquisar(Pesquisa pesquisa) throws SQLException;
}
