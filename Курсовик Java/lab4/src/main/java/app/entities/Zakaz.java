package app.entities;

import java.util.Date;

public class Zakaz {
    private int ID;
    public int getID()
    {
        return ID;
    }
    private int ClientID;
    public int getClientID()
    {
        return ClientID;
    }
    private Date Date;
    public Date getDate()
    {
        return Date;
    }
    private double Itog;
    public double getItog()
    {
        return Itog;
    }
    private String Status;
    public String getStatus()
    {
        return Status;
    }
    private String Rjad;
    public String getRjad()
    {
        return Rjad;
    }
    private int Stojka;
    public int getStojka()
    {
        return Stojka;
    }
    private int Jarus;
    public int getJarus()
    {
        return Jarus;
    }
    public Zakaz(int ID,int ClientID, Date Date, Double Itog){
        this.ID=ID;
        this.ClientID=ClientID;
        this.Date=Date;
        this.Itog=Itog;
    }
    public Zakaz(int ID,int ClientID, Date Date){
        this.ID=ID;
        this.ClientID=ClientID;
        this.Date=Date;

    }
    public Zakaz(int ID,int ClientID, Date Date, Double Itog,String Rjad, int Stojka,int Jarus,String Status){
        this.ID=ID;
        this.ClientID=ClientID;
        this.Date=Date;
        this.Itog=Itog;
        this.Rjad=Rjad;
        this.Stojka=Stojka;
        this.Jarus=Jarus;
        this.Status=Status;

    }
    @Override
    public String toString(){
        String lineFormat = "%s %s %s %s %s %s %s %s";
        return (String.format(lineFormat,ID,ClientID,Date,Itog,Status,Rjad,Stojka,Jarus));
        }
}
