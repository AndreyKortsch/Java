package ejb.classes;


import app.entities.*;
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
    private List<Tovar> s3;
    private List<Integer> sl;
    private List<Postavshik> s2;
    private List<Kategor> s4;
    private DAO DAO;
    private int id;
    private int id3;
    private int id4;
    private int id5;
    private int id6;
    private int id7;
    private int id8;
    private int id9;
    private boolean as;
    private List<Tovar> ListTovary;
    private List<Location> ListLocation;
    private List<Zakaz> ListZakaz;
    private AutorizationClient Client;
    private List<Tovar> ListTovarys;
    private List<Location> ListLocation2;
@PostConstruct
public void postconstruct()
{
    s=new ArrayList<>();
    DAO=new DAO();
    id=0;
    as=false;
    sl=new ArrayList<>();
   Client=new AutorizationClient(null,null,"неавторизованный пользователь");
    s2=new ArrayList<>();
    ListTovarys=new ArrayList<>();
    ListLocation2=new ArrayList<>();
    id3=0;
    id4=0;
    id5=0;
    id6=0;
    id7=0;
    id8=0;
    id9=0;
    s3=new ArrayList<>();
    s4=new ArrayList<>();
}
    public void setlistKategor(int a, int id) {
        Kategor q;
        boolean flag=false;
        int k=0;
        for (Kategor aa:s4){
            if(aa.getTovar().getID()==a) {
                flag=true;
                q=s4.get(k);
                q.setID(id);

            }
            k++;
        }
        if (!flag) s4.add(new Kategor(id,a));

    }
    public List<Kategor> getlistKategor(){return s4;}
    @Override
    public List<Tovar> getListTov()
    {
        return ListTovary;
    }

    public int getId4() {
        return id4;
    }
    public void setId4(int id4){
    this.id4=id4;
    }

    @Override
    public List<Location> getListLoc()
    {
        return ListLocation;
    }
    @Override
    public List<Tovar> getBooks(int a, int id) {
        Tovar as=DAO.showPersonInfo(a);
        as.setNumbers(id);
        Tovar q;
        int aw;
        boolean flag=false;
        int k=0;
        for (Tovar aa:s){
            if(aa.getID()==as.getID()) {
                flag=true;
                q=s.get(k);
                aw=q.getNumbers();
                q.setNumbers(aw+id);

            }
            k++;
        }
        if (!flag) s.add(as);
        return s;
    }
    @Override
    public void getBooks2(int a, int id) {
        Tovar as=DAO.showPersonInfo(a);
        as.setNumbers(id);
        Tovar q;
        int aw;
        boolean flag=false;
        int k=0;
        for (Tovar aa:s3){
            if(aa.getID()==as.getID()) {
                flag=true;
                q=s3.get(k);
                aw=q.getNumbers();
                q.setNumbers(aw+id);

            }
            k++;
        }
        if (!flag) s3.add(as);

    }
    @Override
    public List<Tovar> getBooksList2() {
    return s3;
    }
    public void BooksList2remove(int id) {
        s3.remove(id);
    }
    public void BooksList2clear() {
        s3.clear();
    }
    @Override
    public List<Postavshik> getPostavshik(int a, int id,int id2) {
        Postavshik as=DAO.showPostavshikInfo(id);
        Postavshik q;
        Tovar s;
        int aw;
        boolean flag=false;
        int k=0;
        for (Postavshik aa:s2){
            if(aa.getID()==as.getID()) {
                flag=true;
                q=s2.get(k);
                s=DAO.showPersonInfo(a);
                boolean flag2=false;
                int re=0;
                Tovar qa;
                for (Tovar asa:aa.getListTovar()){
                    if(asa.getID()==s.getID()) {
                        flag2=true;
                        qa=aa.getListTovar().get(re);
                        aw=qa.getNumbers();
                        qa.setNumbers(aw+id2);

                    }
                    re++;
                }
                if (!flag2) {
                    s.setNumbers(id2);
                    q.addListTovar(s);
                };

            }
            k++;
        }
        if (!flag)
        {s2.add(as);
        q=s2.get(s2.size()-1);
        s=DAO.showPersonInfo(id);
        s.setNumbers(id2);
        q.addListTovar(s);
        }
        return s2;
    }
    @Override
    public void updateBooks(int a, int id) {
        //System.out.println(a);
        Tovar q;
        q=s.get(id);
        q.setNumbers(a);



    }
    @Override
    public List<Kategor> getKat() {
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
    public void setClient(AutorizationClient a){
        Client=a;
    }
    @Override
    public AutorizationClient getClient(){
        return Client;
    }
    @Override
    public List<Tovar> getList()
    {
        return s;
    }
    public void setid3(int id3){
        this.id3=id3;
    }
    public int getid3(){
        return id3;
    }
    public void setid5(int id5){
        this.id5=id5;
    }
    public int getid5(){
        return id5;
    }
    public void setid6(int id6){
        this.id6=id6;
    }
    public int getid6(){
        return id6;
    }
    public void setid7(int id7){
        this.id7=id7;
    }
    public int getid7(){
        return id7;
    }
    public void setid8(int id8){
        this.id8=id8;
    }
    public int getid8(){
        return id8;
    }
    public void setid9(int id9){
        this.id9=id9;
    }
    public int getid9(){
        return id9;
    }
    public List<Tovar> getTovarys()
    {

        return ListTovarys;
    }
    public void addListLoc(Location a)
    {
            System.out.println("товараы"+a.getTovarID());

            int k=0;
            boolean match = false;
            for (Location firstItem : ListLocation2) {
                System.out.println(firstItem.getTovarID());
                if (firstItem.getTovarID()==a.getTovarID()) {
                    Location as=ListLocation2.get(k);
                    ListLocation2.set(k,a);
                    System.out.println("ok");
                    match = true;
                }
                System.out.println(firstItem.getID()+"      "+firstItem.getTovarID());
                k++;
            }
            if (!match) {
                ListLocation2.add(a);
            }

    }
    public List<Location> getListLoc22()
    {

        return ListLocation2;
    }
    public void getListLoc2(int id)
    {

        ListLocation2.remove(id);
    }
    public void getListq()
    {

        ListLocation2.clear();
    }
    public List<Location> getListLoc3(List<Location> a)
    {
        if (a.size()!=0) {
            List<Location> thirdList = new ArrayList<>();

            for (Location secondItem : a) {
                boolean match = false;
                for (Location firstItem : ListLocation2) {
                    if ((secondItem.getRjad().equals(firstItem.getRjad())) &&
                            (secondItem.getStojka() == firstItem.getStojka()) &&
                            (secondItem.getJarus() == firstItem.getJarus())) {
                        match = true;
                    }
                }
                if (!match) {
                    thirdList.add(secondItem);
                }
            }
            return thirdList;
        }
        else return a;
    }

    public void addTovarys(Tovar a)
    {
        ListTovarys.add(a);

    }
    public void Tovarysremove()
    {
        ListTovarys.clear();

    }
    public List<Postavshik> getPostavshik()
    {
        return s2;
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
        s.remove(id);
    }
    public void removeelements(int id) {
        ListTovary.remove(id);
    }
    public void removeelementw(int id) {
        s2.remove(id);
    }
    public void removetovaris(int id) {
        ListTovarys.remove(id);
    }

    @Override
    public void clear()
    {
        s.clear();

    }
    @Override
    public void clear2()
    {
        s2.clear();

    }
    @Override
    public Client searchClient(Client a){
        DAO dao=new DAO();
        return dao.searchUser(a);
    }
    @Override
    public int addClientID(Client a){
        DAO dao=new DAO();
        return dao.addPerson(a);
    }
    @Override
    public int addZakaz(int id){
        DAO dao=new DAO();
       return dao.addZakaz(id);
    }
    @Override
    public void addTovary(int id){
        DAO dao=new DAO();
        dao.addTovary(id,getList());
    }

    @Override
    public void getListZakaz(String id)
    {
        DAO DAO = new DAO();
        ListZakaz=DAO.listAllZakaz(id);
    }
    @Override
    public List<Zakaz> getListZak()
    {
        return ListZakaz;
    }
    @Override
    public List<Tovar> getListTovary(String id)
    {
        DAO DAO = new DAO();
        ListTovary=DAO.listAllTovary(id);
        return DAO.listAllTovary(id);
    }
    @Override
    public List<Location> getListLocation(String id)
    {
        DAO DAO = new DAO();
        ListLocation=DAO.listAllLocation(id);
        return DAO.listAllLocation(id);
    }
}

