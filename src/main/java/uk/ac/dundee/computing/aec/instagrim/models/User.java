
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
import com.datastax.driver.core.Statement;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedList;
import uk.ac.dundee.computing.aec.instagrim.lib.AeSimpleSHA1;
import uk.ac.dundee.computing.aec.instagrim.stores.*;

/**
 *
 * @author Administrator
 */
public class User {

    Cluster cluster;

    public User() {

    }

    public boolean RegisterUser(String username, String Password, String firstname, String lastname, String email) {
        AeSimpleSHA1 sha1handler = new AeSimpleSHA1();
        String EncodedPassword = null;
        try {
            EncodedPassword = sha1handler.SHA1(Password);
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException et) {
            System.out.println("Can't check your password");
            return false;
        }
        Session session = cluster.connect("instagrim");
        PreparedStatement ps = session.prepare("insert into userprofiles (login, password, first_name, last_name, email) Values(?,?,?,?,?)");

        BoundStatement boundStatement = new BoundStatement(ps);
        session.execute(boundStatement.bind(username, EncodedPassword, firstname, lastname, email));
       
        PreparedStatement ps2 = session.prepare("insert into profilepic(user) Values(?)");
        BoundStatement bs2 = new BoundStatement(ps2);
        session.execute(bs2.bind(username));
        
        //We are assuming this always works. Also a transaction would be good here !

        session.close();

        return true;
    }
    public java.util.LinkedList<ProfileBean> searchAll(){
        Session session = cluster.connect("instagrim");
        LinkedList<ProfileBean> profileBeanList = new LinkedList();
        
        
        String cqlQuery = "select * from userprofiles";
        PreparedStatement ps = session.prepare(cqlQuery);
        ResultSet rs;
        BoundStatement bs = new BoundStatement(ps);
        rs = session.execute(bs.bind());
        if(rs.isExhausted()){
            System.out.println("Profile not found");
        }
        else
        {
            for (Row row : rs){
            ProfileBean profile = new ProfileBean();
            profile.setLogin(row.getString("login"));
            profileBeanList.add(profile);    
        }
            
        }
        return profileBeanList;
    }
    java.util.LinkedList<ProfileBean> searchUser(String username){
        java.util.LinkedList<ProfileBean> lsProfile = new LinkedList<>();
        
        Session session = cluster.connect("instagrim");
        PreparedStatement ps = session.prepare("select * from userprofiles");
        ResultSet rs = null;
        BoundStatement bs = new BoundStatement(ps);
        rs = session.execute(bs.bind());
        
        if(rs.isExhausted()){
            System.out.println("No users Found");
            return null;            
        }
        else{
            for (Row row : rs){
                ProfileBean profile = new ProfileBean();
                profile.setLogin(row.getString("login"));
                lsProfile.add(profile);
            }
        }
        return lsProfile;
    }
    public ProfileBean getUserInfo(ProfileBean profile, String username) {

        Session session = cluster.connect("instagrim");

        String cqlQuery = "select * from userprofiles where login=?";
        PreparedStatement ps = session.prepare(cqlQuery);
        ResultSet rs = null;
        BoundStatement bs = new BoundStatement(ps);
        rs = session.execute(bs.bind(username));
        
        if(rs.isExhausted()){
            System.out.println("Profile not found");
        }
        else
        {
            for (Row row : rs){
            profile.setLogin(row.getString("login"));
            profile.setFirstName(row.getString("first_name"));
            String lastname = row.getString("last_name");
            String email = row.getString("email");
            profile.setLastName(lastname);
            profile.setEmail(email); 
        }
      
        }
        return profile;
        
    }
 
    public boolean updateUserDetails(String username, String firstname, String lastname, String email){
        Session session = cluster.connect("instagrim");
       /*String cqlQuery = ("update userprofiles set first_name=?,last_name=?,email=? where login =?");
        
        PreparedStatement ps = session.prepare(cqlQuery);
        BoundStatement bs = new BoundStatement(ps);
        session.execute(bs.bind(firstname,lastname,email,username));
        
        */
        Statement query = QueryBuilder.update("instagrim","userprofiles")
                                .with(QueryBuilder.set("first_name", firstname))
                                .and(QueryBuilder.set("last_name", lastname))
                                .and(QueryBuilder.set("email", email))
                 .where(QueryBuilder.eq("login", username));
            
        try
        {
            
        session.execute(query);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
            
        
        return true;
    }
    public void deleteUser(String username){
        Session session = cluster.connect("instagrim");
        PreparedStatement deleteUser = session.prepare("delete * from userprofiles where login=?");
        session.execute(deleteUser.bind(username));
        
        
        
        
    }
    public boolean IsValidUser(String username, String Password) {
        AeSimpleSHA1 sha1handler = new AeSimpleSHA1();
        String EncodedPassword = null;
        try {
            EncodedPassword = sha1handler.SHA1(Password);
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException et) {
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
                if (StoredPass.compareTo(EncodedPassword) == 0) {
                    return true;
                }
            }
        }

        return false;
    }
    public boolean userValid(String username){
        
        String cqlQuery = "select login from userprofiles where login =?";
        Session session = cluster.connect("instagrim");
        PreparedStatement ps = session.prepare(cqlQuery);
        ResultSet rs = null;
        BoundStatement boundStatement = new BoundStatement(ps);
        rs = session.execute(boundStatement.bind(username));
         if (rs.isExhausted()) {
            System.out.println("No Profile Found");
            return false;
        } else {
        
        return true;
        }
    }
    public void setCluster(Cluster cluster) {
        this.cluster = cluster;
    }

}
