package controller;

import dao.DAO;
import dao.DAOFactory;
import model.Usuario;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(
        name = "UserController",
        urlPatterns = {
                "/usuario",
                "/usuario/create"
        }
)
public class UserController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DAO<Usuario> dao;
        Usuario usuario = new Usuario();
        String servletPath = request.getServletPath();

        HttpSession session = request.getSession();

        switch (servletPath) {
            case "/usuario/create": {
                try(DAOFactory daoFactory = DAOFactory.getInstance()) {
                    String username = request.getParameter("usuario");
                    String email = request.getParameter("email");
                    String senha = request.getParameter("senha");
                    String pNome = request.getParameter("nome");
                    String sNome = request.getParameter("sobrenome");
                    String dtNascimentoString = request.getParameter("nascimento");
                    String nomeImagem = request.getParameter("imagem");
                    String cidade = request.getParameter("cidade");
                    String estado = request.getParameter("estado");
                    String pais = request.getParameter("pais");
                    String banda = request.getParameter("banda");
                    String musica = request.getParameter("musica");
                    String genero = request.getParameter("genero");
                    String instrumento = request.getParameter("instrumento");

                    usuario.setUsername(username);
                    usuario.setEmail(email);
                    usuario.setSenha(senha);
                    usuario.setpNome(pNome);
                    usuario.setsNome(sNome);

                    java.util.Date dtNascimento = new SimpleDateFormat("yyyy-mm-dd").parse(dtNascimentoString);
                    usuario.setDtNascimento(new Date(dtNascimento.getTime()));

                    // Pega o caminho absoluto da aplicação
                    String appPath = request.getServletContext().getRealPath("");
                    // Grava o arquivo na pasta img no caminho absoluto
                    String savePath = appPath + File.separator + "img" + File.separator + nomeImagem;
                    File uploadedFile = new File(savePath);
                    usuario.setImagem(nomeImagem);

                    usuario.setCidade(cidade);
                    usuario.setEstado(estado);
                    usuario.setPais(pais);
                    usuario.setBandaFavorita(banda);
                    usuario.setMusicaFavorita(musica);
                    usuario.setGeneroFavorito(genero);
                    usuario.setInstrumentoFavorito(instrumento);

                    dao = daoFactory.getUsuarioDAO();

                    dao.create(usuario);

                    response.sendRedirect(request.getContextPath() + "/usuario");
                } catch (ParseException e) {
                    Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, "Controller", e);

                    session.setAttribute("error", "O formato de data não é válido. Por favor entre data no formato dd/mm/aaaa.");

                    response.sendRedirect(request.getContextPath() + servletPath);
                } catch (ClassNotFoundException | IOException | SQLException ex) {
                    Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, "Controller", ex);

                    session.setAttribute("error", ex.getMessage());

                    response.sendRedirect(request.getContextPath() + servletPath);
                } catch (Exception ex) {
                    Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, "Controller", ex);

                    session.setAttribute("error", "Erro ao gravar arquivo no servidor.");

                    response.sendRedirect(request.getContextPath() + servletPath);
                }
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DAO<Usuario> dao;
        Usuario usuario;
        RequestDispatcher dispatcher;

        switch (request.getServletPath()) {
            case "/usuario": {
                dispatcher = request.getRequestDispatcher("/index.jsp");
                dispatcher.forward(request, response);
            }
            case "/usuario/create": {
                dispatcher = request.getRequestDispatcher("/view/usuario/create.jsp");
                dispatcher.forward(request, response);
                break;
            }
        }
    }
}
