/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.dundee.computing.aec.instagrim.stores;
import java.util.UUID;

/**
 *
 * @author Rhys
 */
public class ProfileBean {
    private String login = null;
    private String firstname = null;
    private String lastname = null;
    private String Email = null;
    private UUID profilepic=null;

  
    public void ProfileBean(){
    
    }
    public void setLogin(String login){
        this.login = login;
    }
    public String getLogin(){
        return login;
    }
    public void setFirstName(String firstname){
        this.firstname = firstname;
    }
    public String getFirstName(){
        return firstname;
    }
    public void setLastName(String lastname){
        this.lastname = lastname;
    }
    public String getLastName(){
        return lastname;
    }
    public void setEmail(String Email){
        this.Email = Email;
    }
    public String getEmail(){
        return Email;
    }
    public void setProfilePic(UUID profilepic){
        this.profilepic = profilepic;
    }
    public UUID getProfilePic(){
        return profilepic;
    }
    
}
