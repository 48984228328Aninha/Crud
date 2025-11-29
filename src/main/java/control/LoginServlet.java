package control;

import dao.UsuarioDAO;
import jdbc.ConnectionFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import model.UserModel;

@WebServlet("/api/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        UsuarioDAO dao = new UsuarioDAO();
        UserModel usuario = dao.login(username, password);

        PrintWriter out = resp.getWriter();

        if (usuario != null) {
            out.print("{ \"status\": \"ok\", \"username\": \"" + usuario.getUsername() + "\", \"roleId\": " + usuario.getUserid()+ " }");
        } else {
            out.print("{ \"status\": \"error\", \"message\": \"Credenciais inválidas\" }");
        }
    }
}

