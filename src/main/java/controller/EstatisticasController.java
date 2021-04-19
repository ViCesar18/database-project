package controller;

import dao.ComentarioDAO;
import dao.DAOFactory;
import dao.EstatisticasDAO;
import dao.PostDAO;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import model.Estatisticas;
import model.Post;
import model.Usuario;

@WebServlet(
    name = "EstatisticaController",
    urlPatterns = {
        "/estatisticas"
    }
)
public class EstatisticasController extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        RequestDispatcher dispatcher;

        HttpSession session = request.getSession();

        switch (request.getServletPath()) {
            case "/estatisticas": {
                try (DAOFactory daoFactory = DAOFactory.getInstance()) {
                    Usuario u = (Usuario) session.getAttribute("usuario");

                    EstatisticasDAO estatisticasDAO = daoFactory.getEstatisticasDAO();
                    PostDAO postDAO = daoFactory.getPostDAO();
                    ComentarioDAO comentarioDAO = daoFactory.getComentarioDAO();

                    // Post com mais likes
                    Post postLikes = postDAO
                        .read(estatisticasDAO.buscarPostMaiorNumeroLikes().getId());

                    postLikes.setnCurtidas(postDAO.numberOfLikes(postLikes.getId()));
                    postLikes.setnComentarios(comentarioDAO.numberOfComents(postLikes.getId()));
                    postLikes.setnCompartilhamentos(
                        postDAO.numberOfCompartilhamentos(postLikes.getId()));
                    postLikes.setCurtiu(postDAO.verificarLikePost(u.getId(), postLikes.getId()));
                    postLikes.setComentarios(comentarioDAO.allComentsPost(postLikes.getId()));
                    postLikes.setCompartilhou(postDAO
                        .verificarCompartilhamentoPost(postLikes.getId(), postLikes.getId()));

                    session.setAttribute("postLikes", postLikes);

                    // Post com mais comentários
                    Post postComentarios = postDAO
                        .read(estatisticasDAO.buscarPostMaiorNumeroComentarios()
                            .getId());

                    postComentarios.setnCurtidas(postDAO.numberOfLikes(postComentarios.getId()));
                    postComentarios
                        .setnComentarios(comentarioDAO.numberOfComents(postComentarios.getId()));
                    postComentarios.setnCompartilhamentos(
                        postDAO.numberOfCompartilhamentos(postComentarios.getId()));
                    postComentarios
                        .setCurtiu(postDAO.verificarLikePost(u.getId(), postComentarios.getId()));
                    postComentarios
                        .setComentarios(comentarioDAO.allComentsPost(postComentarios.getId()));
                    postComentarios.setCompartilhou(postDAO
                        .verificarCompartilhamentoPost(postComentarios.getId(),
                            postComentarios.getId()));

                    session.setAttribute("postComentarios", postComentarios);

                    // Post com mais compartilhamentos
                    Post postCompartilhamentos = postDAO
                        .read(estatisticasDAO.buscarPostMaiorNumeroCompartilhamentos().getId());

                    postCompartilhamentos
                        .setnCurtidas(postDAO.numberOfLikes(postCompartilhamentos.getId()));
                    postCompartilhamentos
                        .setnComentarios(
                            comentarioDAO.numberOfComents(postCompartilhamentos.getId()));
                    postCompartilhamentos.setnCompartilhamentos(
                        postDAO.numberOfCompartilhamentos(postCompartilhamentos.getId()));
                    postCompartilhamentos
                        .setCurtiu(
                            postDAO.verificarLikePost(u.getId(), postCompartilhamentos.getId()));
                    postCompartilhamentos
                        .setComentarios(
                            comentarioDAO.allComentsPost(postCompartilhamentos.getId()));
                    postCompartilhamentos.setCompartilhou(postDAO
                        .verificarCompartilhamentoPost(postCompartilhamentos.getId(),
                            postCompartilhamentos.getId()));

                    session.setAttribute("postCompartilhamentos", postCompartilhamentos);

                    // Número de Interações (Total)
                    Estatisticas interacoesTotal = estatisticasDAO.buscarNumeroInteracoes();

                    session.setAttribute("numeroLikes", interacoesTotal.getNumeroLikes());
                    session.setAttribute("numeroComentarios", interacoesTotal.getNumeroComentarios());
                    session.setAttribute("numeroCompartilhamentos", interacoesTotal.getNumeroCompartilhamentos());
                    session.setAttribute("numeroInteracoes", interacoesTotal.getNumeroInteracoes());

                    // Instrumentos mais tocados (Mulheres)
                    List<Estatisticas> instrumentosMulheres = estatisticasDAO.buscarInstrumentosMaisTocadosMulheres();

                    session.setAttribute("instrumentosMulheres", instrumentosMulheres);

                    // Instrumentos mais tocados (Mulheres)
                    List<Estatisticas> instrumentosHomens = estatisticasDAO.buscarInstrumentosMaisTocadosHomens();

                    session.setAttribute("instrumentosHomens", instrumentosHomens);

                    // Gênero favorito (mulheres)
                    List<Estatisticas> generosFavoritosMulheres = estatisticasDAO.buscarGeneroPreferidoMulheres();

                    session.setAttribute("generosFavoritosMulheres", generosFavoritosMulheres);

                    // Gênero favorito (homens)
                    List<Estatisticas> generosPreferidosHomens = estatisticasDAO.buscarGeneroPreferidoHomens();

                    session.setAttribute("generosPreferidosHomens", generosPreferidosHomens);
                } catch (Exception e) {
                    System.out.println(e);
                }

                dispatcher = request.getRequestDispatcher("/view/estatisticas/index.jsp");
                dispatcher.forward(request, response);
                break;
            }
        }
    }
}

