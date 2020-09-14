package controller;

import dao.DAOFactory;
import dao.UsuarioDAO;
import model.Usuario;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(
        name = "LoginController",
        urlPatterns = {
                "",
                "/login",
                "/logout"
        }
)
public class LoginController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UsuarioDAO dao;
        Usuario usuario = new Usuario();
        HttpSession session = request.getSession();

        switch(request.getServletPath()) {
            case "/login": {
                usuario.setUsername(request.getParameter("usuario"));
                usuario.setSenha(request.getParameter("senha"));

                try(DAOFactory daoFactory = DAOFactory.getInstance()) {
                    dao = daoFactory.getUsuarioDAO();

                    dao.authenticate(usuario);

                    session.setAttribute("usuario", usuario);
                } catch(ClassNotFoundException | IOException | SQLException | SecurityException e) {
                    Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, "Controller", e);
                    session.setAttribute("error", e.getMessage());
                } catch (Exception e) {
                    Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, "Controller", e);
                    session.setAttribute("error", e.getMessage());
                }

                response.sendRedirect(request.getContextPath() + "/feed");
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        RequestDispatcher dispatcher;

        switch(request.getServletPath()) {
            case "": {
                if(session != null && session.getAttribute("usuario") != null) {
                    dispatcher = request.getRequestDispatcher("/view/feed/index.jsp");
                }
                else {
                    dispatcher = request.getRequestDispatcher("/index.jsp");
                }

                dispatcher.forward(request, response);

                break;
            }
            case "/logout": {
                if(session != null) {
                    session.invalidate();
                }

                response.sendRedirect(request.getContextPath() + "/");
            }
        }
    }
}
