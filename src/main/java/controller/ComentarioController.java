package controller;

import dao.*;
import model.Banda;
import model.Comentario;
import model.Post;
import model.Usuario;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(
        name = "ComentarioController",
        urlPatterns = {
                "/publicar-comentario",
        }
)

public class ComentarioController extends HttpServlet {
    protected void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ComentarioDAO dao;
        UsuarioDAO usuarioDAO;
        FeedDAO feedDAO;
        Usuario usuario;

        String servletPath = request.getServletPath();
        HttpSession session = request.getSession();
        Post post = new Post();

        switch (servletPath) {
            case "/publicar-comentario":{
                try(DAOFactory daoFactory = DAOFactory.getInstance()) {
                    usuario = (Usuario) session.getAttribute("usuario");
                    int idPost = Integer.parseInt(request.getParameter("idPost"));

                    Comentario c = new Comentario();
                    c.setPostId(idPost);
                    c.setTextoComentario(request.getParameter("textoComentario"));
                    c.setUsuarioId(usuario.getId());

                    dao = daoFactory.getComentarioDAO();

                    dao.create(c);

                    response.sendRedirect(request.getContextPath() + "/feed");
                }catch (SQLException | ClassNotFoundException e) {
                    Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, "Controller", e);

                    session.setAttribute("error", e.getMessage());

                    response.sendRedirect(request.getContextPath() + "/feed");
                } catch (Exception e) {
                    Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, "Controller", e);

                    session.setAttribute("error", e.getMessage());

                    response.sendRedirect(request.getContextPath() + "/feed");
                }

                break;
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String servletPath = request.getServletPath();
        PostDAO dao;
        HttpSession session = request.getSession();
        RequestDispatcher dispatcher;

        switch (servletPath) {
        }
    }
}
