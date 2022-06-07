package co.com.jorge.apiservlet.webapp.form;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/registro")
public class FormServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String pais = request.getParameter("pais");
        String[] lenguajes = request.getParameterValues("lenguajes");
        String[] roles = request.getParameterValues("roles");

        String idioma = request.getParameter("idioma");
        boolean habilitar = request.getParameter("habilitar") != null && request.getParameter("habilitar").equals("on");
        String secreto = request.getParameter("secreto");

        Map<String, String> errores = new HashMap<>();

        if (username == null){
            errores.put("username", "El username es requerido!");
        }

        if (password == null){
            errores.put("password", "El password no puede ser vacio!");
        }

        if (email == null){
            errores.put("email", "El email es requerido y debe tener un formato de correo!");
        }
        if (pais == null){
            errores.put("pais", "El pais es requerido!");
        }
        if (lenguajes == null){
            errores.put("lenguajes", "Debe seleccionar al menos un tema!");
        }
        if (roles == null){
            errores.put("roles", "Debe selecciona al menos un rol!");
        }
        if (idioma == null){
            errores.put("idioma", "Debe selecciona un idioma!");
        }

        if (errores.isEmpty()){
            try (PrintWriter out = response.getWriter()) {
                out.println("<!DOCTYPE html>");
                out.println("   <html>");
                out.println("       <head>");
                out.println("           <meta charset=\"UTF-8\"/>");
                out.println("           <title>Resultado form</title>");
                out.println("               <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor\" crossorigin=\"anonymous\">\n");
                out.println("       </head>");
                out.println("       <body class='container'>");
                out.println("           <h1>Resultado form!</h1><section class='d-flex align-items-center'>");
                out.println("           <ul class=\"list-group list-group-flush\">");
                out.println("               <li class=\"list-group-item\">Username: " + username + "</li>");
                out.println("               <li class=\"list-group-item\">Password: " + password + "</li>");
                out.println("               <li class=\"list-group-item\">Email: " + email + "</li>");
                out.println("               <li class=\"list-group-item\">Pais: " + pais + "</li>");
                out.println("               <li class=\"list-group-item\">Lenguajes: <ul class=\"list-group list-group-flush\">");
                Arrays.asList(lenguajes).forEach(lenguaje -> {
                    out.println("                               <li class=\"list-group-item\">" + lenguaje + "</li>");
                });
                out.println("                </ul></li>");

                out.println("               <li class=\"list-group-item\">Roles: <ul class=\"list-group list-group-flush\">");
                Arrays.asList(roles).forEach(rol -> {
                    out.println("                               <li class=\"list-group-item\">" + rol + "</li>");
                });
                out.println("                </ul></li>");
                out.println("               <li class=\"list-group-item\">Idioma: " + idioma + "</li>");
                out.println("               <li class=\"list-group-item\">Habilitado: " + habilitar + "</li>");
                out.println("               <li class=\"list-group-item\">Secreto: " + secreto + "</li>");
                out.println("           </ul>");
                out.println("               <script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js\" integrity=\"sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2\" crossorigin=\"anonymous\"></script>\n");
                out.println("       </section></body>");
                out.println("   </html>");

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }else {
            request.setAttribute("errores", errores);
            getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
        }

    }
}
