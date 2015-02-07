package com.entities;

import java.io.Serializable;

public class MusicEnt implements Serializable{
	
	private Integer musid;
	private boolean isActiveMus;
	private Integer volume;
	private String namemus;
	private String typemus;
	private String musip;
	private Integer roomid;
	private Integer typeid;
	
	public MusicEnt(Integer mid, boolean mact, Integer vol, String namem, String typem, String mip, Integer rid, Integer tid){
		this.musid = mid;
		this.isActiveMus = mact;
		this.volume = vol;
		this.namemus = namem;
		this.typemus = typem;
		this.musip = mip;
		this.roomid = rid;
		this.typeid = tid;
	}

	public Integer getMusId(){
		return this.musid;
	}

	public boolean getIsActiveMus(){
		return this.isActiveMus;
	}

	public Integer getVolume(){
		return this.volume;
	}

	public String getNameMus(){
		return this.namemus;
	}

	public String getTypeMus(){
		return this.typemus;
	}

	public String getMusIp(){
		return this.musip;	
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
	    return "Music: Id: " + this.musid +
	    		"Name: "+ this.namemus +
	    		" isActiveMus: " + this.isActiveMus + 
	    		" Ip: " + this.musip +
	    		"Volume: "+ this.volume +
	    		"Type: "+this.typemus +
	    		" Room Id: " + this.roomid +
	    		"Type Id: " + this.typeid;
	};
	
}
