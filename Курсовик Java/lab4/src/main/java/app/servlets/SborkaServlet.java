package app.servlets;

import app.entities.Location;
import app.entities.Tovar;
import app.entities.Zakaz;
import app.model.DAO;
import ejb.classes.AddBookBean;
import ejb.classes.SimpleBean;
import ejb.classes.SingletonBean;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SborkaServlet extends HttpServlet {
    @EJB
    SimpleBean simpleBean;
    @Inject
    AddBookBean addBookBean;
    @EJB
    SingletonBean singletonBean;
    public int parsetoint(String s)
    {
        int foo;
        try {
            foo = Integer.parseInt(s);
        }
        catch (NumberFormatException e)
        {
            foo = 0;
        }
        return foo;
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String infoJava = req.getParameter("INFO");
        //System.out.println(infoJava);
        int info=parsetoint(infoJava);
        if (info==0) {
            req.setAttribute("Selected", addBookBean.getListTovary("принят"));
            req.setAttribute("Select", addBookBean.getListLocation("принят"));
            for (Tovar a:addBookBean.getListTovary("принят"))
            System.out.println(a.toString());
            addBookBean.getListZakaz("принят");
            //for (Zakaz a: addBookBean.getListZak())
                //System.out.println(a.toString());
        }else
        {
            //if  (addBookBean.getListTov()!=null) {
                req.setAttribute("Selected", addBookBean.getListTov());
                req.setAttribute("Select", addBookBean.getListLoc());
           // }
            //else {
            //    for (Zakaz a:addBookBean.getListZak()){
              //      simpleBean.updateStatus(a.getID(),"на сборке");
             //   }
             //   RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/sborka.jsp?INFO=0");
            //    requestDispatcher.forward(req, resp);
           // }
        }

        //System.out.println(addBookBean.getList());
        req.setAttribute("er", addBookBean.getas());
        req.setAttribute("Clients", addBookBean.getClient());
        RequestDispatcher requestDispatcher;
        if (addBookBean.getClient().getRole().equals("грузчик")) {
            requestDispatcher = req.getRequestDispatcher("views/sborka.jsp");
        }
        else requestDispatcher = req.getRequestDispatcher("views/error.jsp");
        requestDispatcher.forward(req, resp);

    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        if (req.getParameter("button1") != null)
        {
            String password = req.getParameter("button1");
            int id=parsetoint(password);
            String pass = req.getParameter("name1");
            int id1=parsetoint(pass);
            String pass1 = req.getParameter("name2");
            int id2=parsetoint(pass1);
            String pass2 = req.getParameter("name3");
            int id3=parsetoint(pass2);
            String pass3 = req.getParameter("name4");
            int id4=parsetoint(pass3);
            String pass4 = req.getParameter("name5");
            int id5=parsetoint(pass4);
            System.out.println("Номер товара "+id);
            System.out.println("Количество "+id1);
            System.out.println("Номер записи для удаления "+id3);
            System.out.println("Номер записи для обновления "+id4);
            System.out.println("Количество для обновления "+id5);
            simpleBean.deleteLocation(id,id3);
            if (id4>id3) {
              if (id5>0)simpleBean.updateSum(id5,id4);
                //Location a = simpleBean.getNextLocation(id, id1);
                //int as=simpleBean.getSumm(a.getID(),id);
                //System.out.println(a.getID());
                //System.out.println("Номер записи " + a.getID());
                //System.out.println(simpleBean.setNumber(a.getID(), id));
                //
            }
            addBookBean.removeelements(id2);
            if  (addBookBean.getListTov().size()==0) {
            System.out.println(5);
            System.out.println(addBookBean.getListTov().size());
                for (Zakaz a : addBookBean.getListZak()) {
                    //System.out.println(a.toString());
                    simpleBean.updateStatus(a.getID(), "на сборке");
                    }
                resp.sendRedirect("/sborka?INFO=0");
            }
                else
               resp.sendRedirect("/sborka?INFO=1");
           // }
           // else resp.sendRedirect("/sborka?INFO=1");
                //System.out.println(simpleBean.setNumber(a.getID(), id));
                //simpleBean.deleteLocation(id,a.getID());

            //System.out.println(as);
            //System.out.println(addBookBean.getListTov().get(id2));
            //            //simpleBean.deleteLocation(id,id2);
            //            //int aq=id1-as;
            //if (aq>0) simpleBean.updateSum(aq,aa);
            //int aa=simpleBean.setNumber(a.getID(),id);

            //addBookBean.removeelements(id2);
             //System.out.println(pass1);

            //req.setAttribute("name", "кек");
            //doGet(req, resp);

        }
        if (req.getParameter("button2") != null)
        {
            req.setAttribute("name", "кек");
            doGet(req, resp);

        }
        if (req.getParameter("button5") != null){
            //req.setAttribute("result", singletonBean.message());

            String pass = req.getParameter("name");
            System.out.println(pass);
            if (req.getParameter("option")!=null)
                simpleBean.sendEmail(pass,addBookBean.getList());
            String pass1 = req.getParameter("number_voler");
            System.out.println(pass1);
            DAO dao=new DAO();
            //dao.addPerson(pass1,pass,addBookBean.getList());
            addBookBean.clear();
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/ok.jsp");
            requestDispatcher.forward(req, resp);

        }


    }
}
