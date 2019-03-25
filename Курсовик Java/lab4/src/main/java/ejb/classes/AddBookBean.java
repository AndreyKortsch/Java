package ejb.classes;


import app.entities.Kategor;
import app.entities.Tovar;
import app.model.DAO;
import ejb.interfaces.addBook;

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.enterprise.context.SessionScoped;

import java.io.Serializable;
import java.util.*;

@Stateful
@SessionScoped
//@StatefulTimeout(value=300, unit= TimeUnit.SECONDS)
@LocalBean
public class AddBookBean implements Serializable, addBook {
    private List<Tovar> s;
    private List<Integer> sl;
    private DAO DAO;
    private int id;
    private boolean as;
@PostConstruct
public void postconstruct()
{
    s=new ArrayList<>();
    DAO=new DAO();
    id=0;
    as=false;
    sl=new ArrayList<>();
    //for (int i=0;i<DAO.listAllPerson().size();i++)
      //  sl.add(0);
}
    @Override
    public List<Tovar> getBooks(int a, int id) {
        //System.out.println(a);
        Tovar as=DAO.showPersonInfo(a);
        as.setNumbers(id);
        //System.out.println(as.toString());
        s.add(as);
        return s;
    }
    @Override
    public List<Kategor> getKat() {
        //System.out.println(a);

        return DAO.listAllKategories();
    }
    @Override
    public void setas(){
    as=true;
    }
    public boolean getas(){
        return as;
    }
    @Override
    public List<Tovar> getList()
    {
        return s;
    }
    @Override
    public List<Integer> getNumbers()
    {
        return sl;
    }
    @Override
    public int getnumber() {
        return id;
    }
    public void removeelement(int id) {
        //sl.set(id,0);
        s.remove(id);
    }
    @Override
    public void incrementnumber(int id)
    {
        int i=sl.get(id);
        i++;
        sl.set(id,i);

    }
    @Override
    public void decrementnumber(int id)
    {
        int i=sl.get(id);
        if (i>0) i--;
        else i=0;
        sl.set(id,i);
    }
    @Override
    public void clear()
    {
        s.clear();

    }

}

