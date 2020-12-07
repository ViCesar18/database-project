package controller;

import dao.DAOFactory;
import dao.FeedDAO;
import dao.PostDAO;
import dao.UsuarioDAO;
import model.Banda;
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

        switch(request.getServletPath()) {
            case "/feed": {
                if(session.getAttribute("usuario") != null) {
                    try (DAOFactory daoFactory = DAOFactory.getInstance()) {
                        dao = daoFactory.getFeedDAO();
                        Usuario u = (Usuario) session.getAttribute("usuario");

                        List<Post> posts = dao.allPostsFeed(u.getId());

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
