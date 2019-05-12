package ejb.interfaces;
import app.entities.*;

import javax.ejb.Local;
import java.util.List;

@Local
public interface SimpleBeanRemote {
    List<Tovar> myList(int id);
    String getMessage();
    List<Tovar> getList();
    void sendEmail(String a,List<Tovar> as);
    Tovar getTovar(int id);
    void updateStatus(int id, String sum);
    List<Client> getClientList(String id);
    List<Zakaz> getClientZakaz(String id);
    List<Tovar> getListTovar(String id);
    Location getNextLocation(int id, int sum);
    int getSumm(int id, int sum);
    void deleteLocation(int id,int sum);
    void updateSum(int id, int sum);
    int setNumber(int id,int sum);
    List<Location> ListLocation();
    List<Location> LocationAll();
    void updateLocationZakaz(int id, Location sum);
    void getListTovar(int id);
    AutorizationClient getClient(AutorizationClient a);
    List<Zakaz> getClientZakazs();
    List<Tovar> getListTovars();
    List<Tovar> myList2();
    List<Postavshik> getListPostavshik(int id);
    List<Tovar> myList3(int id);
    List<Postavshik> getListPost();
    List<Location> LocationAll2();
    List<Location> ListLocation2();
    List<Integer> getListTovars(int id);
    List<Integer> ListNumbersTovars(List<Tovar> a,List<Integer> b);
    void addTovars(int id, List<Integer> sum);
    int addTovars2(int sum);
}

