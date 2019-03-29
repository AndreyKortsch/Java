package ejb.interfaces;
import app.entities.Tovar;

import javax.ejb.Local;
import java.util.List;

@Local
public interface SimpleBeanRemote {
    List<Tovar> myList(int id);
    String getMessage();
    List<Tovar> getList();
    void sendEmail(String a,List<Tovar> as);
    Tovar getTovar(int id);
}

