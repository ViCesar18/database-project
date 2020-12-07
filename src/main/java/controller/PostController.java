package controller;

import dao.DAOFactory;
import dao.FeedDAO;
import dao.PostDAO;
import dao.UsuarioDAO;
import model.Post;
import model.Usuario;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(
        name = "PostController",
        urlPatterns = {
            "/publicar-post"
        }
)

public class PostController extends HttpServlet {
    protected void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PostDAO dao;
        UsuarioDAO usuarioDAO;
        FeedDAO feedDAO;
        Usuario usuario;
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

                    usuarioDAO = daoFactory.getUsuarioDAO();
                    List<Integer> seguidores;

                    seguidores = usuarioDAO.readListSeguidores(usuario.getId());
                    seguidores.add(usuario.getId());

                    feedDAO = daoFactory.getFeedDAO();

                    for (Integer s:seguidores) {
                        feedDAO.insertPostInFeed(s, p.getId());
                    }

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
