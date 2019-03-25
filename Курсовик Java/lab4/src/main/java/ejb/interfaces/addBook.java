package ejb.interfaces;
import app.entities.Kategor;
import app.entities.Tovar;


import javax.ejb.Remote;
import java.util.List;

@Remote
public interface addBook {
    List<Tovar> getBooks(int a, int id);
    List<Tovar> getList();
    int getnumber();
    void incrementnumber(int id);
    void decrementnumber(int id);
    List<Integer> getNumbers();
    void setas();
    void clear();
    List<Kategor> getKat();
}

