package ejb.interfaces;
import app.entities.*;


import javax.ejb.Remote;
import java.util.List;

@Remote
public interface addBook {
    List<Tovar> getBooks(int a, int id);
    List<Tovar> getList();
    List<Tovar> getListTovary(String id);
    List<Location> getListLocation(String id);
    int getnumber();
    List<Integer> getNumbers();
    void setas();
    void clear();
    List<Kategor> getKat();
    List<Tovar> getListTov();
    List<Location> getListLoc();
    void getListZakaz(String id);
    List<Zakaz> getListZak();
    void updateBooks(int a, int id);
    void setClient(AutorizationClient a);
    AutorizationClient getClient();
    Client searchClient(Client a);
    int addClientID(Client a);
    void addTovary(int id);
    int addZakaz(int id);
    List<Postavshik> getPostavshik(int a, int id,int id2);
    void clear2();
    void getBooks2(int a, int id);
    List<Tovar> getBooksList2();
}

