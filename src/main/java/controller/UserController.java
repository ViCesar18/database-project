package controller;

import dao.DAO;
import dao.DAOFactory;
import dao.UsuarioDAO;
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
                "/usuario/create",
                "/usuario/perfil",
                "/usuario/perfil/update",
                "/usuario/perfil/update-musical",
                "/usuario/perfil/update-foto",
                "/usuario/perfil/update-senha",
                "/usuario/perfil/delete",
                "/usuario/all",
                "/usuario/instrumentos"
        }
)
public class UserController extends HttpServlet {
    public static String USER_LOGADO;
    private static final int MAX_FILE_SIZE = 1024 * 1024 * 4;

    /**
     * Pasta para salvar os arquivos que foram 'upados'. Os arquivos vão ser
     * salvos na pasta de build do servidor. Ao limpar o projeto (clean),
     * pode-se perder estes arquivos. Façam backup antes de limpar.
     */
    private static String SAVE_DIR = "assets/img/usuario";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        UsuarioDAO dao;
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

                    response.sendRedirect(request.getContextPath() + "/");
                } catch (FileUploadException e) {
                    Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, "Controller", e);

                    session.setAttribute("error", "Erro ao fazer upload do arquivo.");

                    response.sendRedirect(request.getContextPath() + "/");
                } catch (SQLException | IOException | ClassNotFoundException e) {
                    Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, "Controller", e);

                    session.setAttribute("error", e.getMessage());

                    response.sendRedirect(request.getContextPath() + "/");
                } catch (Exception e) {
                    Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, "Controller", e);

                    session.setAttribute("error", "Erro ao gravar arquivo no servidor.");

                    response.sendRedirect(request.getContextPath() + "/");
                }

                break;
            }
            case "/usuario/perfil/update": {
                try(DAOFactory daoFactory = DAOFactory.getInstance()) {
                    usuario.setId(Integer.parseInt(request.getParameter("id")));
                    usuario.setUsername(request.getParameter("username"));
                    usuario.setEmail(request.getParameter("email"));
                    usuario.setpNome(request.getParameter("nome"));
                    usuario.setsNome(request.getParameter("sobrenome"));

                    java.util.Date dtNascimento = new SimpleDateFormat("yyyy-mm-dd").parse(request.getParameter("nascimento"));
                    usuario.setDtNascimento(new Date(dtNascimento.getTime()));

                    usuario.setCidade(request.getParameter("cidade"));
                    usuario.setEstado(request.getParameter("estado"));
                    usuario.setPais(request.getParameter("pais"));

                    dao = daoFactory.getUsuarioDAO();

                    dao.update(usuario);

                    response.sendRedirect(request.getContextPath() + "/usuario/perfil");
                } catch (ParseException e) {
                    Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, "Controller", e);

                    session.setAttribute("error", "O formato de data não é válido. Por favor entre data no formato dd/mm/aaaa.");

                    response.sendRedirect(request.getContextPath() + "/usuario/perfil/update");
                } catch (SQLException | IOException | ClassNotFoundException e) {
                    Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, "Controller", e);

                    session.setAttribute("error", e.getMessage());

                    response.sendRedirect(request.getContextPath() + "/usuario/perfil/update");
                } catch (Exception e) {
                    Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, "Controller", e);

                    session.setAttribute("error", "Erro ao gravar arquivo no servidor.");

                    response.sendRedirect(request.getContextPath() + "/usuario/perfil/update");
                }

                break;
            }
            case "/usuario/perfil/update-musical": {
                try(DAOFactory daoFactory = DAOFactory.getInstance()) {
                    usuario.setId(Integer.parseInt(request.getParameter("id")));
                    usuario.setBandaFavorita(request.getParameter("banda"));
                    usuario.setMusicaFavorita(request.getParameter("musica"));
                    usuario.setGeneroFavorito(request.getParameter("genero"));
                    usuario.setInstrumentoFavorito(request.getParameter("instrumento"));

                    dao = daoFactory.getUsuarioDAO();

                    dao.update(usuario);

                    response.sendRedirect(request.getContextPath() + "/usuario/perfil");
                } catch (ParseException e) {
                    Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, "Controller", e);

                    session.setAttribute("error", "O formato de data não é válido. Por favor entre data no formato dd/mm/aaaa.");

                    response.sendRedirect(request.getContextPath() + "/usuario/perfil/update-musical");
                } catch (SQLException | IOException | ClassNotFoundException e) {
                    Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, "Controller", e);

                    session.setAttribute("error", e.getMessage());

                    response.sendRedirect(request.getContextPath() + "/usuario/perfil/update-musical");
                } catch (Exception e) {
                    Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, "Controller", e);

                    session.setAttribute("error", "Erro ao gravar arquivo no servidor.");

                    response.sendRedirect(request.getContextPath() + "/usuario/perfil/update-musical");
                }

                break;
            }
            case "/usuario/perfil/update-foto": {
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

                            switch (fieldName) {
                                case "id":
                                    usuario.setId(Integer.parseInt(fieldValue));
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

                    dao.update(usuario);

                    response.sendRedirect(request.getContextPath() + "/usuario/perfil");
                } catch (FileUploadException e) {
                    Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, "Controller", e);

                    session.setAttribute("error", "Erro ao fazer upload do arquivo.");

                    response.sendRedirect(request.getContextPath() + "/");
                } catch (SQLException | IOException | ClassNotFoundException e) {
                    Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, "Controller", e);

                    session.setAttribute("error", e.getMessage());

                    response.sendRedirect(request.getContextPath() + "/");
                } catch (Exception e) {
                    Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, "Controller", e);

                    session.setAttribute("error", "Erro ao gravar arquivo no servidor.");

                    response.sendRedirect(request.getContextPath() + "/");
                }

                break;
            }
            case "/usuario/perfil/update-senha": {
                try(DAOFactory daoFactory = DAOFactory.getInstance()) {
                    usuario.setId(Integer.parseInt(request.getParameter("id")));
                    usuario.setUsername(request.getParameter("senha"));    // Gambiarra para atualizar a senha (senha antiga)
                    usuario.setSenha(request.getParameter("novaSenha"));   // Nova senha

                    dao = daoFactory.getUsuarioDAO();

                    dao.update(usuario);

                    response.sendRedirect(request.getContextPath() + "/usuario/perfil");
                } catch (ParseException e) {
                    Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, "Controller", e);

                    session.setAttribute("error", "O formato de data não é válido. Por favor entre data no formato dd/mm/aaaa.");

                    response.sendRedirect(request.getContextPath() + "/usuario/perfil/update-senha");
                } catch (SQLException | IOException | ClassNotFoundException e) {
                    Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, "Controller", e);

                    session.setAttribute("error", e.getMessage());

                    response.sendRedirect(request.getContextPath() + "/usuario/perfil/update-senha");
                } catch (Exception e) {
                    Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, "Controller", e);

                    session.setAttribute("error", "Erro ao gravar arquivo no servidor.");

                    response.sendRedirect(request.getContextPath() + "/usuario/perfil/update-senha");
                }

                break;
            }
            case "/usuario/instrumentos": {
                try(DAOFactory daoFactory = DAOFactory.getInstance()) {
                    usuario = (Usuario) session.getAttribute("usuario");

                    dao = daoFactory.getUsuarioDAO();

                    dao.insertInstrumento(usuario.getId(), request.getParameter("instrumentoQueToca"));

                    response.sendRedirect(request.getContextPath() + "/usuario/perfil");
                } catch (SQLException | ClassNotFoundException e) {
                    Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, "Controller", e);

                    session.setAttribute("error", e.getMessage());

                    response.sendRedirect(request.getContextPath() + "/usuario/perfil");
                } catch (Exception e) {
                    Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, "Controller", e);

                    session.setAttribute("error", e.getMessage());

                    response.sendRedirect(request.getContextPath() + "/usuario/perfil");
                }

                break;
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UsuarioDAO dao;
        Usuario usuario;
        RequestDispatcher dispatcher;
        HttpSession session = request.getSession();
        String servletPath = request.getServletPath();

        switch (request.getServletPath()) {
            case "/usuario": {
                dispatcher = request.getRequestDispatcher("/index.jsp");
                dispatcher.forward(request, response);
                break;
            }
            case "/usuario/create": {
                dispatcher = request.getRequestDispatcher("/view/usuario/create.jsp");
                dispatcher.forward(request, response);
                break;
            }
            case "/usuario/perfil": {
                if(session.getAttribute("usuario") != null) {
                    Usuario usuarioLogin = (Usuario) session.getAttribute("usuario");

                    try(DAOFactory daoFactory = DAOFactory.getInstance()) {
                        dao = daoFactory.getUsuarioDAO();

                        usuario = dao.read(usuarioLogin.getId());

                        List<String> instrumentos = dao.readInstrumentos(usuarioLogin.getId());

                        request.setAttribute("usuario", usuario);
                        request.setAttribute("instrumentos", instrumentos);

                        dispatcher = request.getRequestDispatcher("/view/usuario/perfil.jsp");
                        dispatcher.forward(request, response);
                    } catch (SQLException | ClassNotFoundException e) {
                        Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, "Controller", e);

                        session.setAttribute("error", e.getMessage());

                        response.sendRedirect(request.getContextPath() + "/");
                    } catch (Exception e) {
                        Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, "Controller", e);

                        session.setAttribute("error", e.getMessage());

                        response.sendRedirect(request.getContextPath() + "/");
                    }
                }
                else {
                    response.sendRedirect( request.getContextPath() + "/");
                }
                break;
            }
            case "/usuario/perfil/update": {
                if(session.getAttribute("usuario") != null) {
                    Usuario usuarioLogin = (Usuario) session.getAttribute("usuario");

                    try(DAOFactory daoFactory = DAOFactory.getInstance()) {
                        dao = daoFactory.getUsuarioDAO();

                        usuario = dao.read(usuarioLogin.getId());

                        request.setAttribute("usuario", usuario);

                        dispatcher = request.getRequestDispatcher("/view/usuario/update-perfil.jsp");
                        dispatcher.forward(request, response);
                    } catch (SQLException | ClassNotFoundException e) {
                        Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, "Controller", e);

                        session.setAttribute("error", e.getMessage());

                        response.sendRedirect(request.getContextPath() + "/usuario/perfil");
                    } catch (Exception e) {
                        Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, "Controller", e);

                        session.setAttribute("error", e.getMessage());

                        response.sendRedirect(request.getContextPath() + "/usuario/perfil");
                    }
                }
                else {
                    response.sendRedirect(request.getContextPath() + "/");
                }

                break;
            }
            case "/usuario/perfil/update-musical": {
                if(session.getAttribute("usuario") != null) {
                    Usuario usuarioLogin = (Usuario) session.getAttribute("usuario");

                    try(DAOFactory daoFactory = DAOFactory.getInstance()) {
                        dao = daoFactory.getUsuarioDAO();

                        usuario = dao.read(usuarioLogin.getId());

                        request.setAttribute("usuario", usuario);

                        dispatcher = request.getRequestDispatcher("/view/usuario/update-perfil-musical.jsp");
                        dispatcher.forward(request, response);
                    } catch (SQLException | ClassNotFoundException e) {
                        Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, "Controller", e);

                        session.setAttribute("error", e.getMessage());

                        response.sendRedirect(request.getContextPath() + "/usuario/perfil");
                    } catch (Exception e) {
                        Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, "Controller", e);

                        session.setAttribute("error", e.getMessage());

                        response.sendRedirect(request.getContextPath() + "/usuario/perfil");
                    }
                }
                else {
                    response.sendRedirect(request.getContextPath() + "/");
                }

                break;
            }
            case "/usuario/perfil/update-foto": {
                if(session.getAttribute("usuario") != null) {
                    Usuario usuarioLogin = (Usuario) session.getAttribute("usuario");

                    try(DAOFactory daoFactory = DAOFactory.getInstance()) {
                        dao = daoFactory.getUsuarioDAO();

                        usuario = dao.read(usuarioLogin.getId());

                        request.setAttribute("usuario", usuario);

                        dispatcher = request.getRequestDispatcher("/view/usuario/update-perfil-foto.jsp");
                        dispatcher.forward(request, response);
                    } catch (SQLException | ClassNotFoundException e) {
                        Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, "Controller", e);

                        session.setAttribute("error", e.getMessage());

                        response.sendRedirect(request.getContextPath() + "/usuario/perfil");
                    } catch (Exception e) {
                        Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, "Controller", e);

                        session.setAttribute("error", e.getMessage());

                        response.sendRedirect(request.getContextPath() + "/usuario/perfil");
                    }
                }
                else {
                    response.sendRedirect(request.getContextPath() + "/");
                }

                break;
            }
            case "/usuario/perfil/update-senha": {
                if(session.getAttribute("usuario") != null) {
                    dispatcher = request.getRequestDispatcher("/view/usuario/update-perfil-senha.jsp");
                    dispatcher.forward(request, response);
                }
                else {
                    response.sendRedirect(request.getContextPath() + "/");
                }
                break;
            }
            case "/usuario/perfil/delete": {
                try(DAOFactory daoFactory = DAOFactory.getInstance()) {
                    usuario = (Usuario) session.getAttribute("usuario");

                    dao = daoFactory.getUsuarioDAO();

                    dao.delete(usuario.getId());

                    response.sendRedirect(request.getContextPath() + "/logout");
                } catch (SQLException | ClassNotFoundException e) {
                    Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, "Controller", e);

                    session.setAttribute("error", e.getMessage());

                    response.sendRedirect(request.getContextPath() + "/usuario/perfil");
                } catch (Exception e) {
                    Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, "Controller", e);

                    session.setAttribute("error", e.getMessage());

                    response.sendRedirect(request.getContextPath() + "/usuario/perfil");
                }

                break;
            }
            case "/usuario/all": {
                try(DAOFactory daoFactory = DAOFactory.getInstance()) {
                    dao = daoFactory.getUsuarioDAO();

                    List<Usuario> usuarios = dao.all();

                    request.setAttribute("usuarios", usuarios);

                    dispatcher = request.getRequestDispatcher("/view/usuario/all.jsp");
                    dispatcher.forward(request, response);
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
}
