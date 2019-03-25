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

}
