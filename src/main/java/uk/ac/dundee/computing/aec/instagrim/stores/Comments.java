/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.dundee.computing.aec.instagrim.stores;
import java.util.*;

/**
 *
 * @author rhysh
 */

public class Comments {
    private UUID commentID = null;
    private UUID picID = null;
    private String user = null;
    private String comment = null;
    private Date date = null;
    
    public String getcommentID(){
        return commentID.toString();
    }
    public void setCommentID(UUID commentID){
        this.commentID = commentID;
    }
    public String getUser(){
        return user;
    }
    public void setUser(String user){
        this.user = user;
    }
    public String getComment(){
        return comment;
    }
    public void setComment(String comment){
        this.comment = comment;
    }
    public Date getDate(){
        return date;
    }
    public void setDate(Date date){
        this.date = date;
    }
    public UUID getPicID(){
        return picID;
    }
    public void setPicID(UUID picID){
        this.picID = picID;
    }
    
    
    
    
}