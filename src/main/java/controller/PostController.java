package controller;

import dao.DAOFactory;
import dao.FeedDAO;
import dao.PostDAO;
import dao.UsuarioDAO;
import model.Post;
import model.Usuario;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;

@WebServlet(
        name = "PostController",
        urlPatterns = {
            "/publicar-post"
        }
)

public class PostController extends HttpServlet {
    private static String SAVE_DIR = "assets/img/post";
    private static final int MAX_FILE_SIZE = 1024 * 1024 * 4;
    protected void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PostDAO dao;
        UsuarioDAO usuarioDAO;
        FeedDAO feedDAO;
        Usuario usuario;
        String servletPath = request.getServletPath();
        HttpSession session = request.getSession();

        switch (servletPath) {
            case "/publicar-post":{
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
                try(DAOFactory daoFactory = DAOFactory.getInstance()){
                    List<FileItem> items = upload.parseRequest(request);
                    Iterator<FileItem> iterator = items.iterator();
                    Post p = new Post();
                    while(iterator.hasNext()) {
                        FileItem item = iterator.next();

                        // Processa os campos regulares do formulário
                        if(item.isFormField()) {
                            String fieldName = item.getFieldName();
                            String fieldValue = item.getString();

                            switch(fieldName) {
                                case "textoPost":
                                    p.setTextoPost(fieldValue);
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

                                p.setImagem(fileName);
                            }
                        }
                    }

                    usuario = (Usuario) session.getAttribute("usuario");
                    p.setUsuario(usuario);
                    p.setUsuarioId(usuario.getId());


                    dao = daoFactory.getPostDAO();

                    dao.create(p);

                    usuarioDAO = daoFactory.getUsuarioDAO();
                    List<Integer> seguidores;

                    seguidores = usuarioDAO.readListSeguidores(usuario.getId());
                    seguidores.add(usuario.getId());

                    feedDAO = daoFactory.getFeedDAO();

                    for (Integer s:seguidores) {
                        feedDAO.insertPostInFeed(s, p.getId());
                    }

                    response.sendRedirect(request.getContextPath() + "/feed");
                } catch(Exception e){
                    System.out.println(e);
                }
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String servletPath = request.getServletPath();
        switch (servletPath) {

        }

    }
}
