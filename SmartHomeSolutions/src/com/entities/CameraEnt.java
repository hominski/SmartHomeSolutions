package com.entities;

import java.io.Serializable;

public class CameraEnt implements Serializable{
	
	private Integer camid;
	private boolean isActiveCam;
	private Integer camip;
	private Integer roomid;
	private Integer typeid;
	
	public CameraEnt(Integer cid, boolean act, Integer cip, Integer rid, Integer tid){
		this.camid = cid;
		this.isActiveCam = act;
		this.camip = cip;
		this.roomid = rid;
		this.typeid = tid;
	}
	
   public Integer getCamId(){
   return this.camid;
   }
   
   public boolean getIsActiveCam(){
   return this.isActiveCam;   
   }
   
   public Integer getCamIp(){
   return this.camip;
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
	    return "Camera: Id: " + this.camid +
	    		" isActiveCam: " + this.isActiveCam + 
	    		" Ip: " + this.camip +
	    		" Room Id: " + this.roomid +
	    		"Type Id: " + this.typeid;
	};
}
