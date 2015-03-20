package com.entities;

import java.io.Serializable;
import java.util.GregorianCalendar;

public class UserEnt implements Serializable  
{
private Integer userid;
private String login;
private String password;
private String phone;
private String username;
private String mail;


public UserEnt(Integer id, String log, String pas, String pho, String nam, String mai)
{
this.userid = id;
this.login = log;
this.password = pas;
this.phone = pho;
this.username = nam;
this.mail = mai;
};

public UserEnt(String log, String nam, String mai, String ph)
{

this.login = log;

this.username = nam;

this.mail = mai;

this.phone = ph;

};

public Integer getUserId(){return this.userid;};
public String getLogin(){return this.login;};	
public String getPassword(){return this.password;};	
public String getPhone(){return this.phone;};	
public String getName(){return this.username;};	
public String getMail(){return this.mail;};	

@Override
public String toString() {
    return "User: Id: " + this.userid +
    		" login: " + this.login + 
    		" password: " + this.password + 
    		//" adress: " + this.adress + 
    		" phone: " + this.phone + 
    		" name: " + this.username + 
    		" mail: " + this.mail;
};

}
