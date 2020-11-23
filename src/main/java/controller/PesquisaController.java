package controller;

import dao.DAO;
import dao.DAOFactory;
import dao.PesquisaDAO;
import model.Pesquisa;
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
import java.text.ParseException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "PesquisaController",
            urlPatterns = {
                    "/pesquisa"
            }
)
public class PesquisaController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PesquisaDAO dao;
        RequestDispatcher dispatcher;
        HttpSession session = request.getSession();

        switch(request.getServletPath()) {
            case "/pesquisa": {
                if(session.getAttribute("usuario") != null) {
                    String filtroUsuario = request.getParameter("filtroUsuario");
                    String filtroBanda = request.getParameter("filtroBanda");
                    String filtroEvento = request.getParameter("filtroEvento");

                    if(filtroUsuario == null && filtroBanda == null && filtroEvento == null) {
                        request.setAttribute("erroPesquisa", true);

                        dispatcher = request.getRequestDispatcher("/view/feed/index.jsp");
                        dispatcher.forward(request, response);
                    }

                    try (DAOFactory daoFactory = DAOFactory.getInstance()) {
                        Pesquisa pesquisa = new Pesquisa();

                        pesquisa.setPesquisa(request.getParameter("pesquisa"));
                        if (filtroUsuario != null) {
                            pesquisa.setFiltroUsuario(true);
                        } else {
                            pesquisa.setFiltroUsuario(false);
                        }
                        if (filtroBanda != null) {
                            pesquisa.setFiltroBanda(true);
                        } else {
                            pesquisa.setFiltroBanda(false);
                        }
                        if (filtroEvento != null) {
                            pesquisa.setFiltroEvento(true);
                        } else {
                            pesquisa.setFiltroEvento(false);
                        }

                        dao = daoFactory.getPesquisaDAO();

                        List<Pesquisa> pesquisas = dao.pesquisar(pesquisa);

                        request.setAttribute("pesquisas", pesquisas);
                        request.setAttribute("pesquisa", pesquisa);

                        dispatcher = request.getRequestDispatcher("/view/feed/pesquisa.jsp");
                        dispatcher.forward(request, response);
                    } catch (ParseException e) {
                        Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, "Controller", e);

                        session.setAttribute("error", "O formato de data não é válido. Por favor entre data no formato dd/mm/aaaa.");

                        response.sendRedirect(request.getContextPath() + "/feed");
                    } catch (SQLException | IOException | ClassNotFoundException e) {
                        Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, "Controller", e);

                        session.setAttribute("error", e.getMessage());

                        response.sendRedirect(request.getContextPath() + "/feed");
                    } catch (Exception e) {
                        Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, "Controller", e);

                        session.setAttribute("error", "Erro ao gravar arquivo no servidor.");

                        response.sendRedirect(request.getContextPath() + "/feed");
                    }
                }
                else {
                    response.sendRedirect(request.getContextPath() + "/");
                }
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}