package ejb.classes;

import app.entities.*;
import app.model.DAO;
import ejb.interfaces.SimpleBeanRemote;

import java.util.ArrayList;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import ejb.classes.AddBookBean;
import javax.activation.DataSource;
import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.naming.InitialContext;
import java.sql.Connection;
import java.util.List;
@Resource(name="TestDS", type=javax.sql.DataSource.class)
@Stateless
@LocalBean
public class SimpleBean implements SimpleBeanRemote {
    public void setSessionContext(SessionContext sessionContext) {
        DataSource dataSource = (DataSource)
                sessionContext.lookup("clients");
    }

    private Connection connection;

    public void someOtherMethod() throws Exception {
        InitialContext initialContext = new InitialContext();
        DataSource dataSource = (DataSource)
                initialContext.lookup("java:comp/env/jdbc/clients");
    }
    public List<Tovar> searchTovar(String s) {
        DAO model1 = new DAO();
        List<Tovar> names = model1.listAllTovaro();
        List<Tovar> ae=new ArrayList<>();
        for (Tovar a:names){
            if (a.getName().toUpperCase().contains(s.toUpperCase())) ae.add(a);
        }
        return ae;
    }
    @Override
    public List<Tovar> myList2() {
        DAO model1 = new DAO();
        List<Tovar> names = model1.listAllTovaro();
        return names;
    }
    public int summ(Tovar a) {
        DAO model1 = new DAO();
        return model1.listTovarSum(a);
    }
    public int summ2(Tovar a) {
        DAO model1 = new DAO();
        return model1.listTovarSum2(a);
    }
    @Override
    public List<Tovar> myList3(int id) {
        DAO model1 = new DAO();
        List<Tovar> names = model1.listAllTovarov(id);
        return names;
    }

    @Override
    public List<Tovar> myList(int id) {
        DAO model1 = new DAO();
        List<Tovar> names = model1.listAllPerson(id);
        return names;
    }

    @Override
    public List<Client> getClientList(String id) {
        DAO DAO = new DAO();
        return DAO.listAllClient(id);
    }
    public void addTovarKat(int a, int b) {
        DAO DAO = new DAO();
        DAO.addTovarKat(a,b);
    }
    @Override
    public List<Zakaz> getClientZakaz(String id) {
        DAO DAO = new DAO();
        return DAO.listAllZakaz(id);
    }

    @Override
    public List<Postavshik> getListPostavshik(int id) {
        DAO DAO = new DAO();
        return DAO.listAllPostavchik(id);
    }

    @Override
    public List<Zakaz> getClientZakazs() {
        DAO DAO = new DAO();
        return DAO.listAllZakazs();
    }
    public int addZakazPostavshik(int id) {
        DAO DAO = new DAO();
        return DAO.addZakazPostavshik(id);
    }
    public void addZakazTovar(int id,Tovar a) {
        DAO DAO = new DAO();
        DAO.addZakazTovar(id,a);
    }
    @Override
    public int setNumber(int id, int sum) {
        DAO DAO = new DAO();
        return DAO.setNumber(id, sum);
    }
    public List<Zakaz> setList(int id) {
        DAO DAO = new DAO();
        return DAO.listAllZakazs(id);
    }
    public List<Tovar> setList2(int id) {
        DAO DAO = new DAO();
        return DAO.listAllTovars(id);
    }
    @Override
    public List<Tovar> getListTovar(String id) {
        DAO DAO = new DAO();
        return DAO.listAllTovar(id);
    }

    @Override
    public List<Tovar> getListTovars() {
        DAO DAO = new DAO();
        return DAO.listAllTovars();
    }

    @Override
    public List<Integer> getListTovars(int id) {
        DAO DAO = new DAO();
        return DAO.listAllTovarIndex(id);
    }

    @Override
    public AutorizationClient getClient(AutorizationClient a) {
        DAO DAO = new DAO();
        return DAO.searchUsers(a);
    }

    @Override
    public void getListTovar(int id) {
        DAO DAO = new DAO();
        for (Tovar a : DAO.listAllTovar(id)) {
            System.out.println(DAO.listAllTovarw(a.getID()) + " " + a.getNumbers());
            DAO.addTovar((DAO.listAllTovarw(a.getID()) - a.getNumbers()), a.getID());
        }

    }
    public void getListTovar2(int a,int id) {
        DAO DAO = new DAO();
        DAO.addTovar(a, id);
    }
    public void getListTovar3(Tovar id) {
        DAO DAO = new DAO();
        DAO.addTovar3(id);
    }
    public void getListTovar4(Location id) {
        DAO DAO = new DAO();
        DAO.addTovar4(id);
    }
    public void deletestr(int id) {
        DAO DAO = new DAO();
        DAO.DeleteStr(id);
    }
    public void deletezak(int id) {
        DAO DAO = new DAO();
        DAO.DeleteZak(id);
    }
    @Override
    public int getSumm(int id, int sum) {
        //System.out.println(a);
        DAO DAO = new DAO();
        return DAO.selectSum(id, sum);
    }
    @Override
    public void addTovars(int id, List<Integer> sum) {
       DAO DAO = new DAO();
       DAO.addPostavchikTovar(id, sum);
    }
    @Override
    public int addTovars2(int sum) {
        DAO DAO = new DAO();
       return DAO.listAllTovarw(sum);
    }
    @Override
    public void updateStatus(int id, String sum) {
        //System.out.println(a);
        DAO DAO = new DAO();
        DAO.UpdateStatusZakaz(id, sum);
    }

    @Override
    public void updateLocationZakaz(int id, Location sum) {
        //System.out.println(a);
        DAO DAO = new DAO();
        DAO.UpdateLocationZakaz(id, sum);
    }

    @Override
    public List<Location> ListLocation() {
        DAO DAO = new DAO();
        List<Location> listOne = DAO.LocationZakaz();
        List<Location> listTwo = LocationAll();
        for (Location firstItem : listOne) {
            System.out.println(firstItem.toString());
        }
        if (listOne.size() != 0) {


            List<Location> thirdList = new ArrayList<>();

            for (Location secondItem : listTwo) {
                boolean match = false;
                for (Location firstItem : listOne) {
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
        } else return listTwo;
    }

    @Override
    public List<Integer> ListNumbersTovars(List<Tovar> a, List<Integer> b) {

        if (b.size() != 0) {
            List<Integer> thirdList = new ArrayList<>();
            for (Tovar secondItem : a) {
                boolean match = false;
                for (int firstItem : b) {
                    if (firstItem == secondItem.getID()) {
                        match = true;
                    }
                }
                if (!match) {
                    thirdList.add(secondItem.getID());
                }
            }
            return thirdList;
        } else {

            List<Integer> as = new ArrayList<>();
            for (Tovar firstItem : a) {

                    as.add(firstItem.getID());
                }
            return as;
            }
        }
    public List<Tovar> ListNumbersTovars2(List<Tovar> a, List<Tovar> b) {

        if (b.size() != 0) {
            List<Tovar> thirdList = new ArrayList<>();
            for (Tovar secondItem : a) {
                boolean match = false;
                for (Tovar firstItem : b) {
                    if (firstItem.getID() == secondItem.getID()) {
                        match = true;
                    }
                }
                if (!match) {
                    thirdList.add(secondItem);
                }
            }
            return thirdList;
        } else return a;

    }



    @Override
    public void updateSum(int id, int sum) {
        //System.out.println(a);
        DAO DAO=new DAO();
        DAO.UpdateSum(id,sum);
    }
    @Override
    public List<Location> LocationAll() {
        //System.out.println(a);
        List<Location> a=new ArrayList<>();
        int k=1;
        String A=new String();
        A = "A";
        for (int ka=1;ka<=2;ka++) {

            for (int i = 1; i <= 16; i++)

                for (int j = 1; j <= 5; j++)
                    a.add(new Location(A, i, j));

            A= "B";
        }
        return a;
    }
    @Override
    public List<Location> LocationAll2() {
        List<Location> a=new ArrayList<>();
       for (char sa='A';sa<='P';sa++) {

            for (int i = 1; i <= 16; i++)

                for (int j = 1; j <= 5; j++)
                    a.add(new Location(String.valueOf(sa), i, j));


        }
        return a;
    }
    @Override
    public List<Location> ListLocation2() {
        //System.out.println(a);
        DAO DAO=new DAO();
        List<Location> listOne=DAO.listAllLocation2();
        List<Location> listTwo = LocationAll2();

        if (listOne.size()!=0) {
            List<Location> thirdList = new ArrayList<>();

            for (Location secondItem : listTwo) {
                boolean match = false;
                for (Location firstItem : listOne) {
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
        else return listTwo;
    }
    public List<Location> getNextLocationList(int id) {
        //System.out.println(a);
        DAO DAO=new DAO();
        return DAO.getNextLocation2(id);
    }
    public Location getList2LocationList(Tovar d) {
        int i=0;
        int s=0;
        int k=0;
        int ost=0;
        do{

            s+=d.getListLocation().get(i).getNumbers();
            k=d.getListLocation().get(i).getID();
            i++;
            System.out.println(s+" "+d.getNumbers());
        }
            while (s<d.getNumbers());
        return new Location(k,d.getID(),s-d.getNumbers());
    }
    @Override
    public Location getNextLocation(int id, int sum) {
        //System.out.println(a);
        DAO DAO=new DAO();
        return DAO.getNextLocation(id,sum);
    }
    @Override
    public Tovar getTovar(int id) {
        DAO model1 = new DAO();
        return model1.showPersonInfo(id);
    }
    public void UpdateKat(String id,int sum) {
        DAO model1 = new DAO();
        model1.UpdateKategor(id,sum);
    }
    public boolean UpdateKat2(int k, int id,int sum) {
        DAO model1 = new DAO();
        return model1.UpdateKategor3(k,id,sum);
    }
    public void DeleteKat(int k, int id) {
        DAO model1 = new DAO();
        model1.DeleteCat(k,id);
    }
    public int InsertKat(String id) {
        DAO model1 = new DAO();
        return model1.InsertKat(id);
    }
    public void DeleteKat(int id) {
        DAO model1 = new DAO();
        model1.DeleteCat2(id);
    }
   @Override
    public void deleteLocation(int id,int sum) {
        DAO a=new DAO();
        a.DeleteLocation(id,sum);
    }
    @Override
        public String getMessage() {
        return "This message was generated by simple ejb";
    }
    @Override
    public List<Tovar> getList() {
        AddBookBean a=new AddBookBean();
        return a.getList();
    }
    @Override
    public List<Postavshik> getListPost() {
        DAO a=new DAO();
        return a.showPostavshikInfo2();
    }
    @Override
    public void sendEmail(String a,List<Tovar> as)
    {
        String to = a;         // sender email
        String from = "qwerty12345678901234567890123@yandex.ru";       // receiver email
        String host = "smtp.yandex.ru";            // mail server host

        Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.host", "smtp.yandex.ru");
        properties.setProperty("mail.smtp.port", "465");
        properties.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.setProperty("mail.smtp.socketFactory.port", "465");
        properties.setProperty("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.debug", "true");
        properties.put("mail.smtp.socketFactory.fallback", "false");
        Session session = Session.getDefaultInstance(properties,
                new javax.mail.Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("qwerty12345678901234567890123", "12581258");
                    }
                });



        try {
            MimeMessage message = new MimeMessage(session); // email message

            message.setFrom(new InternetAddress(from)); // setting header fields

            message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));

            //message.setSubject("Test Mail from Java Program"); // subject line
            message.setSubject("Ваш заказ");
            // actual mail body
            String s=new String();
            s="Ваш заказ успешно оформлен"+"\n";
            for (Tovar b:as){
                s+=b.toString()+"\n";
            }
            message.setText(s);
            //Transport transport = session.getTransport("smtp");
            //transport.connect(host, 465, "qwerty12345678901234567890123", "12581258");
            //Transport.send(message, message.getAllRecipients());
            //transport.close();
            // Send message
            Transport.send(message);
            System.out.println("Email Sent successfully....");
        } catch (MessagingException mex){ mex.printStackTrace(); }

    }

    }
