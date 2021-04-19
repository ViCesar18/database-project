package controller;

import dao.*;
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

@WebServlet(
        name = "EstatisticaController",
        urlPatterns = {
                "/estatisticas"
        }
)
public class EstatisticasController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher;
        HttpSession session = request.getSession();
        switch (request.getServletPath()) {
            case "/estatisticas": {
                dispatcher = request.getRequestDispatcher("/view/estatisticas/index.jsp");
                dispatcher.forward(request, response);
                break;
            }
        }
    }
}

