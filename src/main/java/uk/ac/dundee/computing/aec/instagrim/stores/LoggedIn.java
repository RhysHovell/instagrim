/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package uk.ac.dundee.computing.aec.instagrim.stores;

import java.util.UUID;

/**
 *
 * @author Administrator
 */
public class LoggedIn {
    boolean loggedin=false;
    String Username=null;
    private Pic profilepic=null;
    public void LoggedIn(){
        
    }
    
    public void setUsername(String name){
        this.Username=name;
    }
    public String getUsername(){
        return Username;
    }
    public void setLoggedin(){
        loggedin=true;
    }
    public void setLoggedout(){
        loggedin=false;
    }
    
    public void setLoginState(boolean logedin){
        this.loggedin=logedin;
    }
    public boolean getLoggedin(){
        return loggedin;
    }
    public void setProfilePic(Pic profilepic){
        this.profilepic = profilepic;
    }
    public Pic getProfilePic(){
        return profilepic;
    }
    

}
