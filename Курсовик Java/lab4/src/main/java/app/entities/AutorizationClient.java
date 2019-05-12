package app.entities;

public class AutorizationClient extends Client{
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
    private String Role;
    public String getRole()
    {
        return Role;
    }
    public AutorizationClient(int ID, String Telefon, String Email,String Login, String Password,String Role){
        super(ID,Telefon,Email);
        this.Login=Login;
        this.Password=Password;
        this.Role=Role;

    }
    public AutorizationClient(String Login, String Password,String Role){
        this.Login=Login;
        this.Password=Password;
        this.Role=Role;

    }
    public AutorizationClient(String Login, String Password){

        this.Login=Login;
        this.Password=Password;


    }
}
