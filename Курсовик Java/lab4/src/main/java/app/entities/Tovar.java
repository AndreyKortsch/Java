package app.entities;

import java.util.Date;
import java.util.List;

public class Tovar {
    private int ID;
    public int getID()
    {
        return ID;
    }
    public void setID(int id)
    {
       this.ID=id;
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
    private int ZakazID;
    public int getZakazID()
    {
        return ZakazID;
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
    private int Numbers2;
    public void setNumbers2(int a)
    {
        this.Numbers2=a;
    }
    public int getNumbers2()
    {
        return Numbers2;
    }
    private List<Postavshik> ListPostavshik;
    public void setListPostavshik(List<Postavshik> a)
    {
        this.ListPostavshik=a;
    }
    private Location location;
    public Location getLocation()
    {
        return location;
    }
    public void setLocation(Location a)
    {
        this.location=a;
    }
    private List<Location> listlocation;
    public List<Location> getListLocation()
    {
        return listlocation;
    }
    public void setListLocation(List<Location> a)
    {
        this.listlocation=a;
    }
    public List<Postavshik> getListPostavshik()
    {
        return ListPostavshik;
    }
    public Tovar(int ID, String Name, int Numbers){
        this.ID=ID;
        this.Name=Name;
        this.Numbers=Numbers;

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
    public Tovar(int ID, int ZakazID, String Name,
            float Price,int Numbers ){
        this.ID=ID;
        this.ZakazID=ZakazID;
        this.Name=Name;
        this.Price=Price;
        this.Numbers=Numbers;
    }
    public Tovar(int ID, int Numbers ){
        this.ID=ID;
        this.Numbers=Numbers;
    }
    public Tovar(int ZakazID, String Name,
                 float Price,int Numbers ){
        this.ZakazID=ZakazID;
        this.Name=Name;
        this.Price=Price;
        this.Numbers=Numbers;
    }
    public Tovar(int ID,String Kart, String Name,
                 float Price,int Numbers ){
        this.ID=ID;
        this.Kart=Kart;
        this.Name=Name;
        this.Price=Price;
        this.Numbers=Numbers;
    }
    @Override
    public String toString(){
        String lineFormat = "%s %s %s %s %s %s %s %s";
        return (String.format(lineFormat,ID,Name,Weith,Izmer,Country,Description,Price,Numbers));
    }
}
