package controller;

import dao.BandaDAO;
import dao.ComentarioDAO;
import dao.DAOFactory;
import dao.PostDAO;
import model.Banda;
import model.Post;
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
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(
        name = "BandController",
        urlPatterns = {
                "/banda",
                "/banda/create",
                "/banda/all",
                "/banda/perfil",
                "/banda/perfil/delete",
                "/banda/perfil/update",
                "/banda/perfil/update-foto",
                "/seguir-banda",
                "/parar-seguir-banda",
                "/participar-banda",
                "/parar-participar-banda"
        }
)

public class BandController extends HttpServlet {
    private static final int MAX_FILE_SIZE = 1024 * 1024 * 4;
    private static String SAVE_DIR = "assets/img/banda";

    protected void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BandaDAO dao;
        Banda banda = new Banda ();
        String servletPath = request.getServletPath();

        HttpSession session = request.getSession();

        switch (servletPath){
            case "/banda/create": {
                DiskFileItemFactory factory = new DiskFileItemFactory();

                factory.setSizeThreshold(MAX_FILE_SIZE);

                factory.setRepository(new File("/temp"));

                ServletFileUpload upload = new ServletFileUpload(factory);

                upload.setSizeMax(MAX_FILE_SIZE);

                try (DAOFactory daoFactory = DAOFactory.getInstance()){
                    List<FileItem> items = upload.parseRequest(request);

                    Iterator<FileItem> iterator = items.iterator();
                    while (iterator.hasNext()){
                        FileItem item = iterator.next();

                        if (item.isFormField()){
                            String fieldName = item.getFieldName();
                            String fieldValue = item.getString();

                            switch (fieldName){
                                case "sigla":
                                    banda.setSigla(fieldValue);
                                    break;

                                case "nome":
                                    banda.setNome(fieldValue);

                                case "genero":
                                    banda.setGenero(fieldValue);
                            }
                        }
                        else{
                            String fieldName = item.getFieldName();
                            String fileName = item.getName();

                            if (fieldName.equals("imagem") && !fileName.isBlank()){
                                String appPath = request.getServletContext().getRealPath("");

                                String savePath = appPath + File.separator + SAVE_DIR + File.separator + fileName;
                                File uploadedFile = new File (savePath);
                                item.write(uploadedFile);

                                banda.setImagem(fileName);
                            }
                        }
                    }

                    dao = daoFactory.getBandaDAO();

                    Usuario usuario = (Usuario) session.getAttribute("usuario");

                    banda.setUsername_id(usuario.getId());

                    dao.create(banda);
                    
                    response.sendRedirect(request.getContextPath() + "/");
                } catch (ParseException e) {
                    Logger.getLogger(BandController.class.getName()).log(Level.SEVERE, "Controller", e);

                    response.sendRedirect(request.getContextPath() + servletPath);
                } catch (FileUploadException e) {
                    Logger.getLogger(BandController.class.getName()).log(Level.SEVERE, "Controller", e);

                    session.setAttribute("error", "Erro ao fazer upload do arquivo.");

                    response.sendRedirect(request.getContextPath() + servletPath);
                } catch (SQLException | IOException | ClassNotFoundException e) {
                    Logger.getLogger(BandController.class.getName()).log(Level.SEVERE, "Controller", e);

                    session.setAttribute("error", e.getMessage());

                    response.sendRedirect(request.getContextPath() + servletPath);
                } catch (Exception e){
                    Logger.getLogger(BandController.class.getName()).log(Level.SEVERE, "Controller", e);

                    session.setAttribute("error", "Erro ao gravar arquivo no servidor.");

                    response.sendRedirect(request.getContextPath() + servletPath);
                }

                break;

            }
            case "/banda/perfil/update": {
                try(DAOFactory daoFactory = DAOFactory.getInstance()) {
                    int idBanda = Integer.parseInt(request.getParameter("id"));
                    dao = daoFactory.getBandaDAO();
                    banda.setId(idBanda);
                    banda.setNome(request.getParameter("nome"));
                    banda.setSigla(request.getParameter("sigla"));
                    banda.setGenero(request.getParameter("genero"));

                    dao.update(banda);

                    response.sendRedirect(request.getContextPath() + "/banda/perfil?id=" + banda.getId());
                } catch (Exception e) {
                    Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, "Controller", e);

                    session.setAttribute("error", "Erro ao gravar arquivo no servidor.");

                    response.sendRedirect(request.getContextPath() + "/banda/perfil/update");
                }

                break;
            }
            case "/banda/perfil/update-foto": {
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
                                    banda.setId(Integer.parseInt(fieldValue));
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

                                banda.setImagem(fileName);
                            }
                        }
                    }

                    dao = daoFactory.getBandaDAO();
                    dao.update(banda);

                    response.sendRedirect(request.getContextPath() + "/banda/perfil?id=" + banda.getId());
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
            case "/seguir-banda": {
                Integer idUsuario = Integer.parseInt(request.getReader().readLine());
                Integer idBanda = Integer.parseInt(request.getParameter("idBanda"));

                try(DAOFactory daoFactory = DAOFactory.getInstance()) {

                    dao = daoFactory.getBandaDAO();

                    dao.insertUsuarioSegueBanda(idUsuario, idBanda);
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
            case "/parar-seguir-banda": {
                Integer idUsuario = Integer.parseInt(request.getReader().readLine());
                Integer idBanda = Integer.parseInt(request.getParameter("idBanda"));

                try(DAOFactory daoFactory = DAOFactory.getInstance()) {

                    dao = daoFactory.getBandaDAO();

                    dao.deleteUsuarioSegueBanda(idUsuario, idBanda);
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
            case "/participar-banda":{
                try(DAOFactory daoFactory = DAOFactory.getInstance()){
                    dao = daoFactory.getBandaDAO();
                    Usuario usuarioLogado = (Usuario) session.getAttribute("usuario");

                    int idBanda = Integer.parseInt(request.getParameter("id"));
                    int idUsuario = usuarioLogado.getId();
                    String instrumento = request.getParameter("instrumento");

                    Banda b = dao.read(idBanda);
                    dao.insertUsuarioParticipaBanda(idUsuario, idBanda, instrumento);

                    response.sendRedirect(request.getContextPath() + "/banda/perfil?id=" + b.getId());
                }catch (Exception e) {
                    Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, "Controller", e);

                    session.setAttribute("error", "Erro ao participar de banda.");

                    response.sendRedirect(request.getContextPath() + "/banda/perfil?id=" + banda.getId());
                }

                break;
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       BandaDAO dao;
       PostDAO postDAO;
       ComentarioDAO comentarioDAO;
       Banda banda;
       RequestDispatcher dispatcher;
       HttpSession session = request.getSession();
       String servletPath = request.getServletPath();

       switch (request.getServletPath()){
           case "/banda": {
               dispatcher = request.getRequestDispatcher("/index.jsp");
               dispatcher.forward(request, response);
           }
           case "/banda/create": {
               dispatcher = request.getRequestDispatcher("/view/banda/create.jsp");
               dispatcher.forward(request, response);
           }
           case "/banda/perfil": {
               if(session.getAttribute("usuario") != null) {
                   Integer idUsuarioLogado = ((Usuario) session.getAttribute("usuario")).getId();

                   try(DAOFactory daoFactory = DAOFactory.getInstance()) {
                       dao = daoFactory.getBandaDAO();
                       int idBanda = Integer.parseInt(request.getParameter("id"));

                       Banda b = dao.read(idBanda);
                       Boolean segue = dao.readUsuarioSegueBanda(idUsuarioLogado, idBanda);
                       Boolean participa = dao.readUsuarioParticipaBanda(idUsuarioLogado, idBanda);
                       Integer seguidores = dao.readNumeroSeguidores(idBanda);
                       Integer participantes = dao.readNumeroParticipantes(idBanda);

                       request.setAttribute("banda", b);
                       request.setAttribute("segue", segue);
                       request.setAttribute("participa", participa);
                       request.setAttribute("seguidores", seguidores);
                       request.setAttribute("participantes", participantes);

                       List<Post> posts = dao.allPostsBanda(idBanda);

                       postDAO = daoFactory.getPostDAO();
                       comentarioDAO = daoFactory.getComentarioDAO();
                       for (Post p:posts) {
                           p.setnCurtidas(postDAO.numberOfLikes(p.getId()));
                           p.setnComentarios(comentarioDAO.numberOfComents(p.getId()));
                           p.setnCompartilhamentos(postDAO.numberOfCompartilhamentos(p.getId()));
                           p.setCurtiu(postDAO.verificarLikePost(idUsuarioLogado, p.getId()));
                           p.setComentarios(comentarioDAO.allComentsPost(p.getId()));
                           p.setCompartilhou(postDAO.verificarCompartilhamentoPost(idUsuarioLogado, p.getId()));
                       }

                       request.setAttribute("posts", posts);

                       dispatcher = request.getRequestDispatcher("/view/banda/perfil.jsp");
                       dispatcher.forward(request, response);
                   } catch (Exception e){
                       System.out.println(e);
                   }
               } else {
                   response.sendRedirect(request.getContextPath() + "/");
               }

               break;
           }
           case "/banda/all": {
               try (DAOFactory daoFactory = DAOFactory.getInstance()) {
                   dao = daoFactory.getBandaDAO();

                   List<Banda> bandas = dao.all();

                   request.setAttribute("bandas", bandas);

                   dispatcher = request.getRequestDispatcher("/view/banda/all.jsp");
                   dispatcher.forward(request, response);
               } catch (Exception e) {
                   System.out.println(e);
               }

               break;
           }
           case "/banda/perfil/delete": {
               try(DAOFactory daoFactory = DAOFactory.getInstance()) {
                   dao = daoFactory.getBandaDAO();
                   int idBanda = Integer.parseInt(request.getParameter("id"));

                   dao.delete(idBanda);

                   response.sendRedirect(request.getContextPath());
               } catch (SQLException | ClassNotFoundException e) {
                   Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, "Controller", e);

                   session.setAttribute("error", e.getMessage());

                   response.sendRedirect(request.getContextPath() + "/banda/perfil");
               } catch (Exception e) {
                   Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, "Controller", e);

                   session.setAttribute("error", e.getMessage());

                   response.sendRedirect(request.getContextPath() + "/banda/perfil");
               }
               break;
           }
           case "/banda/perfil/update": {
               try(DAOFactory daoFactory = DAOFactory.getInstance()) {
                   dao = daoFactory.getBandaDAO();
                   int idBanda = Integer.parseInt(request.getParameter("id"));
                   Banda b;

                   b = dao.read(idBanda);

                   request.setAttribute("banda", b);

                   dispatcher = request.getRequestDispatcher("/view/banda/update-perfil.jsp");
                   dispatcher.forward(request, response);
               } catch (SQLException | ClassNotFoundException e) {
                   Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, "Controller", e);

                   session.setAttribute("error", e.getMessage());

                   response.sendRedirect(request.getContextPath() + "/banda/perfil");
               } catch (Exception e) {
                   Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, "Controller", e);

                   session.setAttribute("error", e.getMessage());

                   response.sendRedirect(request.getContextPath() + "/banda/perfil");
               }

               break;
           }
           case "/banda/perfil/update-foto": {
               Usuario usuarioLogin = (Usuario) session.getAttribute("usuario");

               try(DAOFactory daoFactory = DAOFactory.getInstance()) {
                   dao = daoFactory.getBandaDAO();
                   int idBanda = Integer.parseInt(request.getParameter("id"));
                   Banda b;

                   b = dao.read(idBanda);

                   request.setAttribute("banda", b);

                   dispatcher = request.getRequestDispatcher("/view/banda/update-perfil-imagem.jsp");
                   dispatcher.forward(request, response);
               } catch (SQLException | ClassNotFoundException e) {
                   Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, "Controller", e);

                   session.setAttribute("error", e.getMessage());

                   response.sendRedirect(request.getContextPath() + "/banda/perfil");
               } catch (Exception e) {
                   Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, "Controller", e);

                   session.setAttribute("error", e.getMessage());

                   response.sendRedirect(request.getContextPath() + "/banda/perfil");
               }

               break;
           }
           case "/participar-banda":{
               try (DAOFactory daoFactory = DAOFactory.getInstance()){
                   dao = daoFactory.getBandaDAO();
                   int idBanda = Integer.parseInt(request.getParameter("id"));
                   Banda b;

                   b = dao.read(idBanda);

                   request.setAttribute("banda", b);
                   dispatcher = request.getRequestDispatcher("/view/banda/escolher-instrumento.jsp");
                   dispatcher.forward(request, response);
               } catch (Exception e) {
                   Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, "Controller", e);

                   session.setAttribute("error", "Erro ao participar de banda.");

                   response.sendRedirect(request.getContextPath() + "/banda/perfil");
               }
               break;
           }

           case "/parar-participar-banda":{
               try(DAOFactory daoFactory = DAOFactory.getInstance()){
                   dao = daoFactory.getBandaDAO();
                   Usuario usuarioLogado = (Usuario) session.getAttribute("usuario");

                   int idBanda = Integer.parseInt(request.getParameter("id"));
                   int idUsuario = usuarioLogado.getId();

                   Banda b = dao.read(idBanda);
                   dao.deleteUsuarioParticipaBanda(idUsuario, idBanda);

                   response.sendRedirect(request.getContextPath() + "/banda/perfil?id=" + b.getId());
               }catch (Exception e) {
                   Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, "Controller", e);

                   session.setAttribute("error", "Erro ao deixar de participar da banda.");

                   response.sendRedirect(request.getContextPath() + "/banda/perfil");
               }

               break;
           }





           /*case "/banda/perfil/update": {
               try(DAOFactory daoFactory = DAOFactory.getInstance()) {
                   dao = daoFactory.getBandaDAO();
                   int idBanda = Integer.parseInt(request.getParameter("id"));
                   Banda b;

                   b = dao.read(idBanda);

                   request.setAttribute("banda", b);

                   dispatcher = request.getRequestDispatcher("/view/banda/update-perfil.jsp");
                   dispatcher.forward(request, response);
               } catch (SQLException | ClassNotFoundException e) {
                   Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, "Controller", e);

                   session.setAttribute("error", e.getMessage());

                   response.sendRedirect(request.getContextPath() + "/banda/perfil");
               } catch (Exception e) {
                   Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, "Controller", e);

                   session.setAttribute("error", e.getMessage());

                   response.sendRedirect(request.getContextPath() + "/banda/perfil");
               }

               break;
           }*/
       }
    }




}
