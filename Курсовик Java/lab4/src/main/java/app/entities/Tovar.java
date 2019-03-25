package app.entities;

import java.util.Date;
import java.util.List;

public class Tovar {
    private int ID;
    public int getID()
    {
        return ID;
    }
    private String Name;
    public void setName(String a)
    {
        this.Name=a;
    }
    public String getName()
    {
        return Name;
    }
    private float Weith;
    public float getWeith()
    {
        return Weith;
    }
    private String Izmer;
    public String getIzmer()
    {
        return Izmer;
    }
    private String Country;
    public String getCountry()
    {
        return Country;
    }
    private String Description;
    public String getDescription()
    {
        return Description;
    }
    private float Price;
    public float getPrice()
    {
        return Price;
    }
    private String Kart;
    public String getKart()
    {
        return Kart;
    }
    private int Numbers;
    public void setNumbers(int a)
    {
        this.Numbers=a;
    }
    public int getNumbers()
    {
        return Numbers;
    }
    public Tovar(int ID, String Name, float Weith, String Izmer, String Country, String Description
            , float Price, String Kart){
        this.ID=ID;
        this.Name=Name;
        this.Weith=Weith;
        this.Izmer=Izmer;
        this.Country=Country;
        this.Description=Description;
        this.Price=Price;
        this.Kart=Kart;
    }


    @Override
    public String toString(){
        String lineFormat = "%s %s %s %s %s %s %s %s";
        return (String.format(lineFormat,ID,Name,Weith,Izmer,Country,Description,Price,Kart));
    }
}
