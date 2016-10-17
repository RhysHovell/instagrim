
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.dundee.computing.aec.instagrim.servlets;

import com.datastax.driver.core.Cluster;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.BoundStatement;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import com.datastax.driver.core.Session;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import uk.ac.dundee.computing.aec.instagrim.lib.CassandraHosts;
import uk.ac.dundee.computing.aec.instagrim.stores.ProfileBean;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import java.io.InputStream;
import java.util.HashMap;
import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import uk.ac.dundee.computing.aec.instagrim.lib.Convertors;
import uk.ac.dundee.computing.aec.instagrim.models.PicModel;
import uk.ac.dundee.computing.aec.instagrim.models.User;
import uk.ac.dundee.computing.aec.instagrim.stores.LoggedIn;
import uk.ac.dundee.computing.aec.instagrim.stores.Pic;

/**
 *
 * @author Rhys
 */
@WebServlet(name = "Profile", urlPatterns = {"/Profile", "/setProfilePicture"})
@MultipartConfig
public class Profile extends HttpServlet {
    
    Cluster cluster=null;
    private HashMap CommandsMap = new HashMap();
    
    public Profile(){
        
    super();
    CommandsMap.put("ProfilePicture",1);
    CommandsMap.put("UpdateProfilePicture",2);
    
    
    }
    @Override
    public void init(ServletConfig config) throws ServletException {
        // TODO Auto-generated method stub
        cluster = CassandraHosts.getCluster();
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
        request.getRequestDispatcher("userprofile.jsp").include(request, response);  
        try (PrintWriter out = response.getWriter()) {
         
        
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
       // processRequest(request, response);
        
        HttpSession session=request.getSession();
        LoggedIn lg = (LoggedIn)session.getAttribute("LoggedIn");
        
        User user = new User();
        user.setCluster(cluster);
        ProfileBean profile = new ProfileBean();
        
        profile = user.getUserInfo(profile, lg.getUsername());
        request.setAttribute("Profile", profile);
        
        
       
        RequestDispatcher rd = request.getRequestDispatcher("userprofile.jsp");
        
        rd.forward(request, response);
        
        
        
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
             String args[] = Convertors.SplitRequestPath(request);
             
            HttpSession session = request.getSession();
            LoggedIn lg = (LoggedIn) session.getAttribute("LoggedIn");
            String user = lg.getUsername();
            if (args[1].equals ("setProfilePicture"));
            {
                setProfilePic(request, response, user);
                response.sendRedirect("Profile");
            }
            if (args[1].equals("DeleteProfile"));
            {
                
            }
        
        
        
        
    }
    
    public void setProfilePic(HttpServletRequest request, HttpServletResponse response, String user) throws ServletException, IOException{
        for (Part part : request.getParts()) {
            System.out.println("Part Name " + part.getName());

            String type = part.getContentType();
            String filename = part.getSubmittedFileName();
            
            InputStream is = request.getPart(part.getName()).getInputStream();
            int i = is.available();
            HttpSession session=request.getSession();
            LoggedIn lg= (LoggedIn)session.getAttribute("LoggedIn");
            String username="majed";
            if (lg.getLoggedin()){
                username=lg.getUsername();
            }
            if (i > 0) {
                byte[] b = new byte[i + 1];
                is.read(b);
                System.out.println("Length : " + b.length);
                PicModel tm = new PicModel();
                tm.setCluster(cluster);
                tm.updateProfilePic(b, type, filename, username);
                Pic profilepic = tm.getProfilePic(username);
                lg.setProfilePic(profilepic);
                is.close();
            }

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

}
