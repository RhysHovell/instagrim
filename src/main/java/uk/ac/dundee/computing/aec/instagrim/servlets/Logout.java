
package uk.ac.dundee.computing.aec.instagrim.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Rhys
 */
@WebServlet(name = "Logout", urlPatterns = {"/Logout"})
public class Logout extends HttpServlet {
@Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)  
                                throws ServletException, IOException {
            response.setContentType("text/html"); 
            System.out.println("You were successfully logged out!");
            PrintWriter out=response.getWriter();  
            
            request.getRequestDispatcher("index.jsp").include(request, response);  
            
            HttpSession session=request.getSession();  
            session.invalidate();  
            
            
            
            response.sendRedirect("index.jsp");
            
          
            
            out.close();
    }  
}  
