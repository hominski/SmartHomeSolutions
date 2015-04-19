package com.entities;

import java.io.Serializable;
import java.util.*;


public class Mode implements Serializable{

private int modeId;
private int userId;
private String modeName;
private GregorianCalendar timeOfBeg;
private GregorianCalendar timeOfEnd;
private boolean active;
private boolean activeCam;
private boolean activeLamp;
private int brightness;
private String colour;
private boolean activeAC;
private int tempAC;
private boolean activeMus;
private int volume;
private String typeMus;

public Mode(int id, int ui,String name, GregorianCalendar timeb, GregorianCalendar timee, 
		boolean act, boolean actcam, boolean actlamp, 
		int bright, String col, boolean actac, int temp,boolean actmus, int vol, String type)
{
	this.modeId = id;
	this.userId = ui;
	this.modeName = name;
	this.timeOfBeg = timeb;
	this.timeOfEnd = timee;
	this.active = act;
	this.activeCam = actcam;
	this.activeLamp = actlamp;
	this.brightness = bright;
	this.colour = col;
	this.activeAC = actac;
	this.tempAC = temp;
	this.activeMus = actmus;
	this.volume = vol;
	this.typeMus = type;
}


public int getModeId()
{	
return this.modeId;
}

public String getModeName()
{
return this.modeName;	
}

public int getTimeOfBeg()
{
return timeOfBeg.get(Calendar.MINUTE);
}

public Date getTimeOfEnd()
{
Date d = timeOfEnd.getTime();
return d;	
}

public boolean isModeActive()
{
return this.active;	
}

public boolean isActiveLamp()
{
return this.activeLamp;	
}

public boolean isActiveCond()
{
return this.activeAC;
}

public String getColour()
{
return this.colour;
}

public int getBrightness()
{
return this.brightness;	
}

public int getTemperature()
{
return this.tempAC;	
}
}