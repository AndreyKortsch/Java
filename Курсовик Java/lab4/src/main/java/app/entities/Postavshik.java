package app.entities;

import java.util.ArrayList;
import java.util.List;

public class Postavshik {
    private int ID;
    public int getID()
    {
        return ID;
    }
    private String Organizationname;
    public String getOrganizationname()
    {
        return Organizationname;
    }
    private String Email;
    public String getEmail()
    {
        return Email;
    }
    private String Adress;
    public String getAdress()
    {
        return Adress;
    }
    private int TovarID;
    public int getTovarID()
    {
        return TovarID;
    }
    public Postavshik(int ID,String OrganizationName, String Email, String Adress){
        this.ID=ID;
        this.Organizationname=OrganizationName;
        this.Email=Email;
        this.Adress=Adress;

    }
    private List<Tovar> ListTovar=new ArrayList<>();
    public void setListTovar(List<Tovar> a)
    {
        this.ListTovar=a;
    }
    public void addListTovar(Tovar a)
    {
        ListTovar.add(a);
    }
    public List<Tovar> getListTovar()
    {
        return ListTovar;
     }
    @Override
    public String toString(){
        String lineFormat = "%s %s %s";
        return (String.format(lineFormat,Organizationname,Email,Adress));
    }
}
