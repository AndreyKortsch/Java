package app.entities;

public class Client {
    private int ID;
    public int getID()
    {
        return ID;
    }



    private String Telefon;
    public String getTelefon()
    {
        return Telefon;
    }


    private String Email;
    public String getEmail()
    {
        return Email;
    }

    public  Client(){}
    public Client(int ID,String Telefon, String Email){
        this.ID=ID;
        this.Telefon=Telefon;
        this.Email=Email;

    }
    public Client(String Telefon, String Email){
        this.ID=ID;
        this.Telefon=Telefon;
        this.Email=Email;

    }
}
