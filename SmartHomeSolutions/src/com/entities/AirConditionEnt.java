package com.entities;

import java.io.Serializable;

public class AirConditionEnt implements Serializable{
 
	private Integer condid;
	private boolean isActiveCond;
	private Integer condtemp;
	private Integer power;
	private String namecond;
	private String condip;
	private Integer roomid;
	private Integer typeid;
	
	public AirConditionEnt(Integer cid, boolean cact, Integer ctemp, Integer cpow, String ncond, String cip, Integer rid, Integer tid){
		this.condid = cid;
		this.isActiveCond = cact;
		this.condtemp = ctemp;
		this.power = cpow;
		this.namecond = ncond;
		this.condip = cip;
		this.roomid = rid;
		this.typeid = tid;
	}

	public Integer getCondId(){
		return this.condid;
	}

	public boolean getIsActiveCond(){
		return this.isActiveCond;
	}

	public Integer getCondTemp(){
		return this.condtemp;
	}

	public String getNameCond(){
		return this.namecond;
	}

	public Integer getCondPower(){
		return this.power;
	}

	public String getCondIp(){
		return this.condip;	
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
	    return "AirCond: Id: " + this.condid +
	    		"Name: "+ this.namecond +
	    		" isActiveCond: " + this.isActiveCond + 
	    		" Ip: " + this.condip +
	    		"Temperature: "+ this.condtemp +
	    		"Power: "+this.power +
	    		" Room Id: " + this.roomid +
	    		"Type Id: " + this.typeid;
	};
}
