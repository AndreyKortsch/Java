package app.entities;

public class Location {
    private int ID;
    public int getID()
    {
        return ID;
    }
    public void setID(int id)
    {
        ID=id;
    }
    private int TovarID;
    public int getTovarID()
    {
        return TovarID;
    }
    public void setTovarID(int id)
    {
        TovarID=id;
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
    private int Numbers;
    public int getNumbers()
    {
        return Numbers;
    }
    public void setNumbers(int id)
    {
        Numbers=id;
    }
public Location (int ID,int TovarID,String Rjad, int Staojka, int Jarus, int Numbers){
        this.ID=ID;
        this.TovarID=TovarID;
        this.Rjad=Rjad;
        this.Stojka=Staojka;
        this.Jarus=Jarus;
        this.Numbers=Numbers;
}
    public Location (int ID,String Rjad, int Staojka, int Jarus, int Numbers){
        this.ID=ID;
        this.Rjad=Rjad;
        this.Stojka=Staojka;
        this.Jarus=Jarus;
        this.Numbers=Numbers;
    }
    public Location (String Rjad, int Stojka, int Jarus){
        this.Rjad=Rjad;
        this.Stojka=Stojka;
        this.Jarus=Jarus;
        }
    public Location (int ID,int TovarID, int Numbers) {
        this.ID = ID;
        this.TovarID = TovarID;
        this.Numbers = Numbers;

    }
    @Override
    public String toString(){
        String lineFormat = "%s %s %s";
        return (String.format(lineFormat,Rjad,Stojka,Jarus));}

}
