package controller;

import dao.DAO;
import dao.DAOFactory;
import model.Evento;
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
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(
        name = "EventController",
        urlPatterns = {
                "/evento",
                "/evento/create"
        }
)

public class EventController extends HttpServlet {
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                DAO<Evento> dao;
                Evento evento = new Evento();
                String servletPath = request.getServletPath();

                HttpSession session = request.getSession();

                switch (servletPath){
                        case "/evento/create": {
                                try(DAOFactory daoFactory = DAOFactory.getInstance()) {
                                        evento.setNome(request.getParameter("nome"));

                                        evento.setDescricao(request.getParameter("descricao"));

                                        Date dateInicioEvento = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm").parse(request.getParameter("data_inicio"));
                                        Timestamp timeInicio = new Timestamp(dateInicioEvento.getTime());
                                        evento.setData_inicio(timeInicio);

                                        Date dateTerminoEvento = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm").parse(request.getParameter("data_termino"));
                                        Timestamp timeTermino = new Timestamp(dateTerminoEvento.getTime());
                                        evento.setData_termino(timeTermino);

                                        evento.setnParticipantes(0);

                                        Usuario usuario = (Usuario) session.getAttribute("usuario");

                                        evento.setUsername_id(usuario.getId());

                                        evento.setCategoria(request.getParameter("categoria"));

                                        dao = daoFactory.getEventoDAO();

                                        dao.create(evento);

                                        response.sendRedirect(request.getContextPath() + "/");

                                } catch (ParseException e) {
                                        Logger.getLogger(EventController.class.getName()).log(Level.SEVERE, "Controller", e);

                                        response.sendRedirect(request.getContextPath() + servletPath);
                                } catch (SQLException | IOException | ClassNotFoundException e) {
                                        Logger.getLogger(EventController.class.getName()).log(Level.SEVERE, "Controller", e);

                                        session.setAttribute("error", e.getMessage());

                                        response.sendRedirect(request.getContextPath() + servletPath);
                                } catch (Exception e){
                                        Logger.getLogger(EventController.class.getName()).log(Level.SEVERE, "Controller", e);

                                        session.setAttribute("error", "Erro ao gravar arquivo no servidor.");

                                        response.sendRedirect(request.getContextPath() + servletPath);
                                }

                                break;

                        }
                }
        }

        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                DAO<Evento> dao;
                Evento evento;
                RequestDispatcher dispatcher;

                switch (request.getServletPath()){
                        case "/evento": {
                                dispatcher = request.getRequestDispatcher("/index.jsp");
                                dispatcher.forward(request, response);
                        }
                        case "/evento/create": {
                                dispatcher = request.getRequestDispatcher("/view/evento/create.jsp");
                                dispatcher.forward(request, response);
                        }
                }
        }

}
