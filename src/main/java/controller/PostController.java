package controller;

import dao.DAOFactory;
import dao.FeedDAO;
import dao.PostDAO;
import model.Post;
import model.Usuario;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(
        name = "PostController",
        urlPatterns = {
            "/publicar-post"
        }
)

public class PostController extends HttpServlet {
    protected void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PostDAO dao;
        Usuario usuario = new Usuario();
        String servletPath = request.getServletPath();
        HttpSession session = request.getSession();

        switch (servletPath) {
            case "/publicar-post":{
                try(DAOFactory daoFactory = DAOFactory.getInstance()){
                    usuario = (Usuario) session.getAttribute("usuario");
                    dao = daoFactory.getPostDAO();

                    Post p = new Post();
                    p.setTextoPost(request.getParameter("textoPost"));
                    p.setUsuario(usuario);
                    p.setUsuarioId(usuario.getId());

                    dao.create(p);

                    response.sendRedirect(request.getContextPath() + "/feed");
                } catch(Exception e){
                    System.out.println(e);
                }
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String servletPath = request.getServletPath();
        switch (servletPath) {

        }

    }
}
