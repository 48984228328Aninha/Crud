/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.UUID;
import model.RoleModel;


/**
 *
 * @author humbe
 */
public class UserModel {
    private String userid;
    private String username;
    private String password;

    
    public UserModel(){}
    
    public UserModel(String username, String password, long roleId) {
    this.userid = UUID.randomUUID().toString(); // gera o ID automático
    this.username = username;
    this.password = password;
   
    }


    public String getUserid() {
        return userid;
    }
    
    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    
}
