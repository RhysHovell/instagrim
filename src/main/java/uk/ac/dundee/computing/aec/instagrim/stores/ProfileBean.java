/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.dundee.computing.aec.instagrim.stores;

import com.datastax.driver.core.utils.Bytes;
import java.nio.ByteBuffer;
/**
 *
 * @author Rhys
 */
public class ProfileBean {
    private String login = null;
    private String first_name = null;
    private String last_name = null;
    private String Email = null;
    private ByteBuffer pImage = null;
    private int length;
    private String type;
    private java.util.UUID UUID = null;
  
    public void ProfileBean(){
    
    }
    public void setLogin(String login){
        this.login = login;
    }
    public String getLogin(){
        return login;
    }
    public void setFirstname(String firstName){
        this.first_name = firstName;
    }
    public String getFirstname(){
        return first_name;
    }
    public void setLastname(String lastName){
        this.last_name = lastName;
    }
    public String getLastname(){
        return last_name;
    }
    public void setEmail(String Email){
        this.Email = Email;
    }
    public String getEmail(){
        return Email;
    }
    public void setUUID(java.util.UUID UUID){
        this.UUID = UUID;
    }
    public String getUUID(){
        return UUID.toString();
    }
    public void setProfilePic(ByteBuffer pImage, int length, String type)
    {
        this.pImage = pImage;
        this.length = length;
        this.type = type;
    }
    public ByteBuffer getBuffer(){
        return pImage;
    }
    public int getLength(){
        return length;
    }
    public String getType(){
        return type;
    }
    public byte[] getBytes(){
        byte image[] = Bytes.getArray(pImage);
        return image;
    }

    
    
}
