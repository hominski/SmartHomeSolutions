package com.entities;

import java.io.Serializable;

public class RoomEnt implements Serializable  {
	private int roomid;
	private int userid;
	private String roomname;
	private String roomtype;
	
	public RoomEnt(int id, int UI, String name,String type)
	{
		this.roomid = id;
		this.userid = UI;
		this.roomname = name;
		this.roomtype = type;
	};
	
	public RoomEnt(int ui, String name,String type)
	{
	    this.userid = ui; 
		this.roomname = name;
		this.roomtype = type;
	};
	
	public int getUserId(){return this.userid;};
	public int getRoomId(){return this.roomid;};
	public String getRoomName(){return this.roomname;};
	public void setRoomName(String name){this.roomname = name;};
	public String getRoomType(){return this.roomtype;};
	public void setRoomType(String type){this.roomtype = type;};
	
	@Override
	public String toString() 
	{
	    return "Room: Id: " + this.roomid +
	    		" name: " + this.roomname + 
	    		" type: " + this.roomtype;
	};
}