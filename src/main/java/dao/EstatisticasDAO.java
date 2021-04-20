package dao;

import java.sql.SQLException;
import java.util.List;
import model.Estatisticas;

public interface EstatisticasDAO {
    Estatisticas buscarPostMaiorNumeroLikes() throws SQLException;

    Estatisticas buscarPostMaiorNumeroComentarios() throws SQLException;

    Estatisticas buscarPostMaiorNumeroCompartilhamentos() throws SQLException;

    Estatisticas buscarNumeroInteracoes() throws SQLException;

    List<Estatisticas> buscarInstrumentosMaisTocadosMulheres() throws SQLException;

    List<Estatisticas> buscarInstrumentosMaisTocadosHomens() throws SQLException;

    List<Estatisticas> buscarGeneroPreferidoMulheres() throws SQLException;

    List<Estatisticas> buscarGeneroPreferidoHomens() throws SQLException;

    public Estatisticas buscarFrequenciaGeracoes() throws  SQLException;
}
