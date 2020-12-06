package controller;

import dao.DAO;
import dao.DAOFactory;
import dao.EventoDAO;
import model.Banda;
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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(
        name = "EventController",
        urlPatterns = {
                "/evento",
                "/evento/create",
                "/evento/all",
                "/evento/perfil",
                "/evento/perfil/delete",
                "/evento/perfil/update",
                "/comparecer-evento",
                "/parar-comparecer-evento"
        }
)

public class EventController extends HttpServlet {
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                EventoDAO dao;
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

                                        evento.setNome_local(request.getParameter("nome_local"));

                                        evento.setRua(request.getParameter("rua"));

                                        evento.setNumero(request.getParameter("numero"));

                                        evento.setBairro(request.getParameter("bairro"));

                                        evento.setCep(request.getParameter("cep"));

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
                        case "/evento/perfil/update": {
                                try(DAOFactory daoFactory = DAOFactory.getInstance()){
                                        int idEvento = Integer.parseInt(request.getParameter("id"));
                                        dao = daoFactory.getEventoDAO();
                                        evento.setId(idEvento);
                                        evento.setNome(request.getParameter("nome"));
                                        evento.setDescricao(request.getParameter("descricao"));
                                        evento.setNome_local(request.getParameter("nome_local"));
                                        evento.setRua(request.getParameter("rua"));
                                        evento.setNumero(request.getParameter("numero"));
                                        evento.setBairro(request.getParameter("bairro"));
                                        evento.setCep(request.getParameter("cep"));
                                        Date dateInicioEvento = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm").parse(request.getParameter("data_inicio"));
                                        Timestamp timeInicio = new Timestamp(dateInicioEvento.getTime());
                                        evento.setData_inicio(timeInicio);
                                        Date dateTerminoEvento = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm").parse(request.getParameter("data_termino"));
                                        Timestamp timeTermino = new Timestamp(dateTerminoEvento.getTime());
                                        evento.setData_termino(timeTermino);
                                        evento.setCategoria(request.getParameter("categoria"));

                                        dao.update(evento);

                                        response.sendRedirect(request.getContextPath() + "/evento/perfil?id=" + evento.getId());

                                } catch(Exception e){
                                        Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, "Controller", e);
                                        session.setAttribute("error", "Erro ao gravar arquivo no servidor.");
                                        response.sendRedirect(request.getContextPath() + "/evento/perfil/update");
                                }
                                break;
                        }
                        case "/comparecer-evento":{
                                Integer idUsuario = Integer.parseInt(request.getReader().readLine());
                                Integer idEvento = Integer.parseInt(request.getParameter("idEvento"));

                                try(DAOFactory daoFactory = DAOFactory.getInstance()){
                                        dao = daoFactory.getEventoDAO();

                                        dao.insertUsuarioCompareceEvento(idUsuario, idEvento);
                                } catch (SQLException | ClassNotFoundException e) {
                                        Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, "Controller", e);

                                        session.setAttribute("error", e.getMessage());

                                        response.sendRedirect(request.getContextPath() + "/");
                                } catch (Exception e) {
                                        Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, "Controller", e);

                                        session.setAttribute("error", e.getMessage());

                                        response.sendRedirect(request.getContextPath() + "/");
                                }
                                break;
                        }
                        case "/parar-comparecer-evento":{
                                Integer idUsuario = Integer.parseInt(request.getReader().readLine());
                                Integer idEvento = Integer.parseInt(request.getParameter("idEvento"));

                                try(DAOFactory daoFactory = DAOFactory.getInstance()) {

                                        dao = daoFactory.getEventoDAO();

                                        dao.deleteUsuarioCompareceEvento(idUsuario, idEvento);
                                } catch (SQLException | ClassNotFoundException e) {
                                        Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, "Controller", e);

                                        session.setAttribute("error", e.getMessage());

                                        response.sendRedirect(request.getContextPath() + "/");
                                } catch (Exception e) {
                                        Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, "Controller", e);

                                        session.setAttribute("error", e.getMessage());

                                        response.sendRedirect(request.getContextPath() + "/");
                                }
                                break;
                        }
                }
        }

        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                EventoDAO dao;
                Evento evento;
                RequestDispatcher dispatcher;
                HttpSession session = request.getSession();

                switch (request.getServletPath()){
                        case "/evento": {
                                dispatcher = request.getRequestDispatcher("/index.jsp");
                                dispatcher.forward(request, response);
                        }
                        case "/evento/create": {
                                dispatcher = request.getRequestDispatcher("/view/evento/create.jsp");
                                dispatcher.forward(request, response);
                        }
                        case "/evento/perfil": {
                                try(DAOFactory daoFactory = DAOFactory.getInstance()) {
                                        Integer idUsuarioLogado = ((Usuario) session.getAttribute("usuario")).getId();
                                        dao = daoFactory.getEventoDAO();
                                        int idEvento = Integer.parseInt(request.getParameter("id"));
                                        Evento e = dao.read(idEvento);
                                        Boolean comparece = dao.readUsuarioCompareceEvento(idUsuarioLogado, idEvento);
                                        Integer participantes = dao.readNumeroParticipantes(idEvento);
                                        request.setAttribute("comparece", comparece);
                                        request.setAttribute("participantes", participantes);

                                        request.setAttribute("evento", e);

                                        dispatcher = request.getRequestDispatcher("/view/evento/perfil.jsp");
                                        dispatcher.forward(request, response);
                                } catch (Exception e){
                                        System.out.println(e);
                                }

                                break;
                        }
                        case "/evento/all": {
                                try(DAOFactory daoFactory = DAOFactory.getInstance()) {
                                        dao = daoFactory.getEventoDAO();

                                        List<Evento> eventos = dao.all();

                                        request.setAttribute("eventos", eventos);

                                        dispatcher = request.getRequestDispatcher("/view/evento/all.jsp");
                                        dispatcher.forward(request, response);
                                } catch (Exception e){
                                        System.out.println(e);
                                }

                                break;
                        }
                        case "/evento/perfil/delete": {
                                try(DAOFactory daoFactory = DAOFactory.getInstance()) {
                                        dao = daoFactory.getEventoDAO();
                                        int idEvento = Integer.parseInt(request.getParameter("id"));

                                        dao.delete(idEvento);

                                        response.sendRedirect(request.getContextPath());
                                } catch (SQLException | ClassNotFoundException e) {
                                        Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, "Controller", e);

                                        session.setAttribute("error", e.getMessage());

                                        response.sendRedirect(request.getContextPath() + "/evento/perfil");
                                } catch (Exception e) {
                                        Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, "Controller", e);

                                        session.setAttribute("error", e.getMessage());

                                        response.sendRedirect(request.getContextPath() + "/evento/perfil");
                                }
                                break;
                        }
                        case "/evento/perfil/update": {
                                try(DAOFactory daoFactory = DAOFactory.getInstance()){
                                        dao = daoFactory.getEventoDAO();
                                        int idEvento = Integer.parseInt(request.getParameter("id"));
                                        Evento e;

                                        e = dao.read(idEvento);



                                        request.setAttribute("evento", e);

                                        dispatcher = request.getRequestDispatcher("/view/evento/update-perfil.jsp");
                                        dispatcher.forward(request, response);
                                }catch (SQLException | ClassNotFoundException e) {
                                        Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, "Controller", e);

                                        session.setAttribute("error", e.getMessage());

                                        response.sendRedirect(request.getContextPath() + "/evento/perfil");
                                } catch (Exception e) {
                                        Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, "Controller", e);

                                        session.setAttribute("error", e.getMessage());

                                        response.sendRedirect(request.getContextPath() + "/evento/perfil");
                                }
                        }
                        break;
                }
        }

}
