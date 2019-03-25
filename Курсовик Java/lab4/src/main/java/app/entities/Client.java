package app.entities;

public class Client {
    private int ID;
    public int getID()
    {
        return ID;
    }

    private String Login;
    public String getLogin()
    {
        return Login;
    }
    private String Password;
    public String getPassword()
    {
        return Password;
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
    public Client(int ID,String Telefon, String Password){
        this.ID=ID;
        this.Telefon=Telefon;
        this.Password=Password;

    }
}
