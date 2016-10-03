/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package uk.ac.dundee.computing.aec.instagrim.models;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import uk.ac.dundee.computing.aec.instagrim.lib.AeSimpleSHA1;
import uk.ac.dundee.computing.aec.instagrim.stores.*;


/**
 *
 * @author Administrator
 */
public class User {
    Cluster cluster;
    public User(){
        
    }

    public boolean RegisterUser(String username, String Password, String firstname){
        AeSimpleSHA1 sha1handler= new AeSimpleSHA1();
        String EncodedPassword=null;
        try {
            EncodedPassword= sha1handler.SHA1(Password);
        }catch (UnsupportedEncodingException | NoSuchAlgorithmException et){
            System.out.println("Can't check your password");
            return false;
        }
        Session session = cluster.connect("instagrim");
        PreparedStatement ps = session.prepare("insert into userprofiles (login,password,first_name) Values(?,?,?)");
       
        BoundStatement boundStatement = new BoundStatement(ps);
        session.execute(boundStatement.bind(username,EncodedPassword,firstname));
        //We are assuming this always works. Also a transaction would be good here !
        
        session.close();
     
        return true;
    }
    
    public ProfileBean getUserInfo(ProfileBean profile, String username){
       
        Session session = cluster.connect("instagrim");
        
        String cqlQuery = "SELECT * FROM userprofiles WHERE username = ?"; 
        PreparedStatement ps = session.prepare(cqlQuery);
        BoundStatement bs = new BoundStatement(ps);
        ResultSet rs = null;
        rs = session.execute(bs.bind(username));
        if (!rs.isExhausted()){
            System.out.println("User not found");
            return null;
            
        } else {
            for(Row row : rs)
            {
                String login = row.getString("login");
                String firstname = row.getString("first_name");
//                String lastname = row.getString("lastname");
//                String email = row.getString("email");
                profile.setLogin(login);
                profile.setFirstname(firstname);
//                profile.setLastname(lastname);
//                profile.setEmail(email);

                
                
            }
        }
        return profile;
    }
    
    public boolean IsValidUser(String username, String Password){
        AeSimpleSHA1 sha1handler=  new AeSimpleSHA1();
        String EncodedPassword=null;
        try {
            EncodedPassword= sha1handler.SHA1(Password);
        }catch (UnsupportedEncodingException | NoSuchAlgorithmException et){
            System.out.println("Can't check your password");
            return false;
        }
        Session session = cluster.connect("instagrim");
        PreparedStatement ps = session.prepare("select password from userprofiles where login =?");
        ResultSet rs = null;
        BoundStatement boundStatement = new BoundStatement(ps);
        rs = session.execute(boundStatement.bind(username));
        if (rs.isExhausted()) {
            System.out.println("No Images returned");
            return false;
        } else {
            for (Row row : rs) {
               
                String StoredPass = row.getString("password");
                if (StoredPass.compareTo(EncodedPassword) == 0)
                    return true;
            }
        }
   
    
    return false;  
    }
       public void setCluster(Cluster cluster) {
        this.cluster = cluster;
    }

    
}
