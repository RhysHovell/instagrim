/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.dundee.computing.aec.instagrim.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import com.datastax.driver.core.Cluster;
import java.util.HashMap;
import java.util.LinkedList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import uk.ac.dundee.computing.aec.instagrim.lib.CassandraHosts;
import uk.ac.dundee.computing.aec.instagrim.lib.Convertors;
import uk.ac.dundee.computing.aec.instagrim.models.User;
import uk.ac.dundee.computing.aec.instagrim.stores.LoggedIn;
import uk.ac.dundee.computing.aec.instagrim.stores.ProfileBean;


/**
 *
 * @author rhysh
 */
@WebServlet(name = "Search", urlPatterns = {"/Search","/SearchAll"})
public class Search extends HttpServlet {
    
    Cluster cluster = null;
    private HashMap CommandsMap = new HashMap();

    public void init(ServletConfig config){
        cluster = CassandraHosts.getCluster();
    }
    public Search()
    {
        super();
        
        CommandsMap.put("Search",1);
        CommandsMap.put("SearchAll",2);
        
        
        
    }
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Search</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Search at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

//            HttpSession session = request.getSession();
//            LoggedIn lg = (LoggedIn) session.getAttribute("LoggedIn");
//            
//            if (lg == null)
//            {
//                RequestDispatcher rd = request.getRequestDispatcher("error-404.jsp");
//                rd.forward(request,response);
//            }
//            else
//            {
//                String args[] = Convertors.SplitRequestPath(request);
//                
//                int command;
//                try {
//                    command = (Integer) CommandsMap.get(args[1]);
//                    
//                } catch (Exception et) {
//                    return;
//                }
//                switch (command) 
//                 {
//                case 1:
//                    RequestDispatcher rd = request.getRequestDispatcher("search.jsp");
//                    rd.forward(request, response);
//                    break;
//                case 2:
//                    break;
//                }
//            }
            
 }      
            
            
            
 public void searchAll(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
 
  
            
}

 public void searchUser(String user, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
     
            User us = new User();
            us.setCluster(cluster);
            
            java.util.LinkedList<ProfileBean> lsSearchedProfile = us.searchUser(user);
            RequestDispatcher rd = request.getRequestDispatcher("search.jsp");
            request.setAttribute("searchUser", lsSearchedProfile);
            rd.forward(request,response);
 }
    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
 protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            
            User us = new User();
            us.setCluster(cluster);
            String search = request.getParameter("searchUser");
            
            String args[] = Convertors.SplitRequestPath(request);
            int command;
            try {
            command = (Integer) CommandsMap.get(args[1]);
                } catch (Exception et) {
                    error("Bad Operator", response);
                    return;
                }
                switch (command) {
                    case 1:
                        
                        searchUser(search,request,response);
                        break;
                    case 2:
                        searchAll(request,response);
                        break;
                    default:
                        error("Bad Operator", response);
                }
            }
 
    

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>



    private void error(String mess, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out = null;
        out = new PrintWriter(response.getOutputStream());
        out.println("<h1>You have an error in your input</h1>");
        out.println("<h2>" + mess + "</h2>");
        out.close();
    }
}