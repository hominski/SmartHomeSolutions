package com.entities;

import java.io.Serializable;

public class UserEnt implements Serializable  
{
private Integer userid;
private String login;
private String password;
private String adress;
private String phone;
private String username;
private String mail;

public UserEnt(Integer id, String log, String pas, String adr, String pho, String nam, String mai)
{
this.userid = id;
this.login = log;
this.password = pas;
this.adress = adr;
this.phone = pho;
this.username = nam;
this.mail = mai;
};

public Integer getUserId(){return this.userid;};
public String getLogin(){return this.login;};	
public String getPassword(){return this.password;};	
public String getAdress(){return this.adress;};	
public String getPhone(){return this.phone;};	
public String getName(){return this.username;};	
public String getMail(){return this.mail;};	

@Override
public String toString() {
    return "User: Id: " + this.userid +
    		" login: " + this.login + 
    		" password: " + this.password + 
    		" adress: " + this.adress + 
    		" phone: " + this.phone + 
    		" name: " + this.username + 
    		" mail: " + this.mail;
};

}
