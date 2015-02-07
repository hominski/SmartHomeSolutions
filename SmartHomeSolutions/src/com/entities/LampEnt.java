package com.entities;

import java.io.Serializable;

public class LampEnt implements Serializable{
	
	private Integer lampid;
	private boolean isActiveLamp;
	private Integer brigthness;
	private String colour;
	private String namelamp;
	private Integer roomid;
	private Integer typeid;
	private String lampip;

	public LampEnt(Integer lid, boolean lact, Integer bri, String col, String nlp, String lip, Integer rid, Integer tid){
		this.lampid = lid;
		this.isActiveLamp = lact;
		this.brigthness = bri;
		this.colour = col;
		this.namelamp = nlp;
		this.lampip = lip;
		this.roomid = rid;
		this.typeid = tid;
	}

	public Integer getLampId(){
		return this.lampid;
	}

	public boolean getIsActiveLamp(){
		return this.isActiveLamp;
	}

	public Integer getBrightness(){
		return this.brigthness;
	}

	public String getColour(){
		return this.colour;
	}

	public String getNameLamp(){
		return this.namelamp;
	}

	public String getLampIp(){
		return this.lampip;	
	}

	public Integer getRoomId(){
		return this.roomid;
	}

	public Integer getTypeId(){
		return this.typeid;
	}
	
	@Override
	public String toString() 
	{
	    return "Lamp: Id: " + this.lampid +
	    		"Name: "+ this.namelamp +
	    		" isActiveLamp: " + this.isActiveLamp + 
	    		" Ip: " + this.lampip +
	    		"Brightness: "+ this.brigthness +
	    		"Colour: "+this.colour +
	    		" Room Id: " + this.roomid +
	    		"Type Id: " + this.typeid;
	};
}

