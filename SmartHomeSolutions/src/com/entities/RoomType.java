package com.entities;

public class RoomType {
private int type_id;
private String room_type;

public RoomType(int id, String t){
this.type_id = id;
this.room_type = t;
}

public String getRoomTypes()
{return room_type;}

public int getIdType(){
return type_id;
}

}
