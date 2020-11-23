package controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(
        name = "FeedController",
        urlPatterns = {
                "/feed"
        }
)
public class FeedController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        RequestDispatcher dispatcher;

        switch(request.getServletPath()) {
            case "/feed": {
                if(session.getAttribute("usuario") != null) {
                    dispatcher = request.getRequestDispatcher("/view/feed/index.jsp");
                    dispatcher.forward(request, response);
                }
                else {
                    response.sendRedirect(request.getContextPath() + "/");
                }
            }
        }
    }
}
