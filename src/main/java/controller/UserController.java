package controller;

import dao.DAO;
import dao.DAOFactory;
import model.Usuario;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
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
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.Iterator;
import java.util.List;
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
    private static final int MAX_FILE_SIZE = 1024 * 1024 * 4;

    /**
     * Pasta para salvar os arquivos que foram 'upados'. Os arquivos vão ser
     * salvos na pasta de build do servidor. Ao limpar o projeto (clean),
     * pode-se perder estes arquivos. Façam backup antes de limpar.
     */
    private static String SAVE_DIR = "assets/img";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DAO<Usuario> dao;
        Usuario usuario = new Usuario();
        String servletPath = request.getServletPath();

        HttpSession session = request.getSession();

        switch (servletPath) {
            case "/usuario/create": {
                // Se fosse um forms simples
                // String username = request.getParameter("usuario");

                // Como existe upload de arquivos (imagem), deve-se usar enctype="multipart/form-data"

                // Cria a factory para itens de arquivos disk-based
                DiskFileItemFactory factory = new DiskFileItemFactory();

                //  as restrições da factory
                factory.setSizeThreshold(MAX_FILE_SIZE);

                // Seta o diretório usado para armazenar arquivos temporários que são maiores que o tamanho máximo configurado
                factory.setRepository(new File("/temp"));

                // Cria um novo manipulador de upload de arquivos
                ServletFileUpload upload = new ServletFileUpload(factory);

                // Seta a restrição geral do tamanho da requisição
                upload.setSizeMax(MAX_FILE_SIZE);

                try(DAOFactory daoFactory = DAOFactory.getInstance()) {
                    // Análise de requisição
                    List<FileItem> items = upload.parseRequest(request);

                    // Processa os items upados
                    Iterator<FileItem> iterator = items.iterator();
                    while(iterator.hasNext()) {
                        FileItem item = iterator.next();

                        // Processa os campos regulares do formulário
                        if(item.isFormField()) {
                            String fieldName = item.getFieldName();
                            String fieldValue = item.getString();

                            switch(fieldName) {
                                case "usuario":
                                    usuario.setUsername(fieldValue);
                                    break;

                                case "email":
                                    usuario.setEmail(fieldValue);
                                    break;

                                case "senha":
                                    usuario.setSenha(fieldValue);
                                    break;

                                case "nome":
                                    usuario.setpNome(fieldValue);
                                    break;

                                case "sobrenome":
                                    usuario.setsNome(fieldValue);
                                    break;

                                case "nascimento":
                                    java.util.Date dtNascimento = new SimpleDateFormat("yyyy-mm-dd").parse(fieldValue);
                                    usuario.setDtNascimento(new Date(dtNascimento.getTime()));
                                    break;

                                case "cidade":
                                    usuario.setCidade(fieldValue);
                                    break;

                                case "estado":
                                    usuario.setEstado(fieldValue);
                                    break;

                                case "pais":
                                    usuario.setPais(fieldValue);
                                    break;

                                case "banda":
                                    usuario.setBandaFavorita(fieldValue);
                                    break;

                                case "musica":
                                    usuario.setMusicaFavorita(fieldValue);
                                    break;

                                case "genero":
                                    usuario.setGeneroFavorito(fieldValue);
                                    break;

                                case "instrumento":
                                    usuario.setInstrumentoFavorito(fieldValue);
                                    break;
                            }
                        }
                        else {
                            // Processa os arquivos upados
                            String fieldName = item.getFieldName();
                            String fileName = item.getName();

                            if(fieldName.equals("imagem") && !fileName.isBlank()) {
                                // Pega o caminho absoluto da aplicação
                                String appPath = request.getServletContext().getRealPath("");

                                // Grava o arquivo upado na pasta img no caminho absoluto
                                String savePath = appPath + File.separator + SAVE_DIR + File.separator + fileName;
                                File uploadedFile = new File(savePath);
                                item.write(uploadedFile);

                                usuario.setImagem(fileName);
                            }
                        }
                    }

                    dao = daoFactory.getUsuarioDAO();

                    dao.create(usuario);

                    response.sendRedirect(request.getContextPath() + "/");
                } catch (ParseException e) {
                    Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, "Controller", e);

                    session.setAttribute("error", "O formato de data não é válido. Por favor entre data no formato dd/mm/aaaa.");

                    response.sendRedirect(request.getContextPath() + servletPath);
                } catch (FileUploadException e) {
                    Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, "Controller", e);

                    session.setAttribute("error", "Erro ao fazer upload do arquivo.");

                    response.sendRedirect(request.getContextPath() + servletPath);
                }catch (SQLException | IOException | ClassNotFoundException e) {
                    Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, "Controller", e);

                    session.setAttribute("error", e.getMessage());

                    response.sendRedirect(request.getContextPath() + servletPath);
                } catch (Exception e) {
                    Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, "Controller", e);

                    session.setAttribute("error", "Erro ao gravar arquivo no servidor.");

                    response.sendRedirect(request.getContextPath() + servletPath);
                }

                break;
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
