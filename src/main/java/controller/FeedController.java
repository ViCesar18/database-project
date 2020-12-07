package controller;

import dao.*;
import model.Banda;
import model.Comentario;
import model.Post;
import model.Usuario;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(
        name = "FeedController",
        urlPatterns = {
                "/feed",
        }
)
public class FeedController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        FeedDAO dao;
        Usuario usuario = new Usuario();
        String servletPath = request.getServletPath();

        switch (servletPath) {

        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        RequestDispatcher dispatcher;
        FeedDAO dao;
        ComentarioDAO comentarioDAO;
        PostDAO postDAO;

        switch(request.getServletPath()) {
            case "/feed": {
                if(session.getAttribute("usuario") != null) {
                    try (DAOFactory daoFactory = DAOFactory.getInstance()) {
                        dao = daoFactory.getFeedDAO();
                        comentarioDAO = daoFactory.getComentarioDAO();
                        Usuario u = (Usuario) session.getAttribute("usuario");

                        List<Post> posts = dao.allPostsFeed(u.getId());

                        postDAO = daoFactory.getPostDAO();
                        for (Post p:posts) {
                            p.setnCurtidas(postDAO.numberOfLikes(p.getId()));
                            p.setnComentarios(comentarioDAO.numberOfComents(p.getId()));
                            p.setnCompartilhamentos(postDAO.numberOfCompartilhamentos(p.getId()));
                            p.setCurtiu(postDAO.verificarLikePost(u.getId(), p.getId()));
                            p.setComentarios(comentarioDAO.allComentsPost(p.getId()));
                            p.setCompartilhou(postDAO.verificarCompartilhamentoPost(u.getId(), p.getId()));
                        }

                        request.setAttribute("posts", posts);
                        request.setAttribute("usuarioLogado", u);

                        dispatcher = request.getRequestDispatcher("/view/feed/index.jsp");
                        dispatcher.forward(request, response);
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                }
                else {
                    response.sendRedirect(request.getContextPath() + "/");
                }
            }
        }
    }
}
