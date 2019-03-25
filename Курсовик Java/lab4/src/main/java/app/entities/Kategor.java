package app.entities;


import java.util.List;

public class Kategor {
    private int ID;
    public int getID()
    {
        return ID;
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
   // public List<Animal> AnimalList;

    @Override
    public String toString(){
        String lineFormat = "%s";
        return (String.format(Name));
    }
}
