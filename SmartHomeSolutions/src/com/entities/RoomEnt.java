package com.entities;

import java.io.Serializable;

public class RoomEnt implements Serializable  {
	private Integer roomid;
	private String roomname;
	private String roomtype;
	
	public RoomEnt(Integer id,String name,String type)
	{
		this.roomid = id;
		this.roomname = name;
		this.roomtype = type;
	};
	
	public Integer getRoomId(){return this.roomid;};
	public String getRoomName(){return this.roomname;};
	public String getRoomtype(){return this.roomtype;};
	
	@Override
	public String toString() 
	{
	    return "Room: Id: " + this.roomid +
	    		" name: " + this.roomname + 
	    		" type: " + this.roomtype;
	};
}