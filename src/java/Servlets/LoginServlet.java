package Servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.AccountService;
import models.User;

/**
 *
 * @author xbali
 */

public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
       HttpSession session = request.getSession(); 
       String action = request.getParameter("logout");
       User user = (User)session.getAttribute("user");
       
       if(action != null){
           session.invalidate();
           getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
       }else if (user != null){
           response.sendRedirect("home");
           return;
       }
       getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        if(username == null || password == null || username.equals("") || password.equals("")){
            request.setAttribute("errorMessage", "Please enter your credientials");
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
            
        
    }
        User user = new AccountService().login(username, password);
        
        if(user != null){
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
             response.sendRedirect("home");
            
        }else{
            request.setAttribute("user", user);
            request.setAttribute("errorMessage", "Failed authentication");
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
        }
   } 
}