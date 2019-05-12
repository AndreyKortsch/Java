package app.entities;


import java.util.List;

public class Kategor {
    private int ID;
    public int getID()
    {
        return ID;
    }
    public void setID(int ID)
    {
        this.ID=ID;
    }
    private int TovarID;
    public int getTovarID()
    {
        return TovarID;
    }
    public void setTovarID(int ID)
    {
        this.TovarID=ID;
    }
    private String Name;
    public String getName()
    {
        return Name;
    }
    public Kategor(int ID,String Name){
        this.Name=Name;
        this.ID=ID;

    }
    private Tovar Tovars;
    public Tovar getTovar()
    {
        return Tovars;
    }
   // public List<Animal> AnimalList;
public Kategor (int id, int a)
{
    ID=id;
    TovarID=a;
}
    @Override
    public String toString(){
        String lineFormat = "%s";
        return (String.format(Name));
    }
}
