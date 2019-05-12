package app.servlets;

import app.entities.Kategor;
import app.entities.Tovar;
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
import java.util.ArrayList;
import java.util.List;

public class EditServlet extends HttpServlet {
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
        String infoJava = req.getParameter("INFO.java");
        int info=parsetoint(infoJava);
        if (infoJava==null) info=1;
        System.out.println("итого"+infoJava);
        req.setAttribute("Am",addBookBean.getid5());
        req.setAttribute("Am2",addBookBean.getid6());
        req.setAttribute("Am3",addBookBean.getid7());
        req.setAttribute("Am5",simpleBean.myList2());
        List<Kategor> listB = new ArrayList<Kategor>(addBookBean.getKat());
        listB.remove(addBookBean.getid6());
        req.setAttribute("An",listB);
        req.setAttribute("Tovars4",simpleBean.ListNumbersTovars2(simpleBean.myList2(), simpleBean.myList(addBookBean.getKat().get(addBookBean.getid6()).getID())));
        req.setAttribute("Tovars",simpleBean.myList(addBookBean.getKat().get(addBookBean.getid6()).getID()));
        req.setAttribute("Info", info);
        req.setAttribute("Info2", addBookBean.getKat());
        req.setAttribute("Clients", addBookBean.getClient());
        RequestDispatcher requestDispatcher;
        if (addBookBean.getClient().getRole().equals("завсклада")) {
            requestDispatcher = req.getRequestDispatcher("views/edit.jsp");
        }
        else requestDispatcher = req.getRequestDispatcher("views/error.jsp");
        requestDispatcher.forward(req, resp);

    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");
        if (req.getParameter("myselect2") != null){
            String pass = req.getParameter("myselect2");
            int id=parsetoint(pass);
            System.out.println(id);
            addBookBean.setid5(id);

        }
        if (req.getParameter("myselect6") != null){
            String pass = req.getParameter("myselect6");
            int id=parsetoint(pass);
            System.out.println(id);
            addBookBean.setid6(id);
            addBookBean.setid7(id);


        }
        if (req.getParameter("button16") != null){
            List<Tovar>a= simpleBean.myList(addBookBean.getKat().get(addBookBean.getid6()).getID());
            List<Tovar>b=simpleBean.ListNumbersTovars2(simpleBean.myList2(), simpleBean.myList(addBookBean.getKat().get(addBookBean.getid6()).getID()));
            int q=0;
            String pass2 = req.getParameter("name2");
            System.out.println("значение "+pass2);
            simpleBean.UpdateKat(pass2,addBookBean.getKat().get(addBookBean.getid6()).getID());
            for (Tovar as:a) {
                String pass = req.getParameter(Integer.toString(q));
                int id=parsetoint(pass);
                System.out.println(as.getID()+"    "+addBookBean.getKat().get(id).getID());
                Boolean s=simpleBean.UpdateKat2(as.getID(),addBookBean.getKat().get(addBookBean.getid6()).getID(),addBookBean.getKat().get(id).getID());
                System.out.println("ошибка "+s);
                if (!s)simpleBean.DeleteKat(as.getID(),addBookBean.getKat().get(addBookBean.getid6()).getID());
                q++;
            }
            for (Tovar as:b) {
                if (req.getParameter(Integer.toString(q))!=null) {
                    String pass = req.getParameter(Integer.toString(q));
                    int id = parsetoint(pass);
                    System.out.println("номер товара "+as.getID());
                    simpleBean.addTovarKat(as.getID(),addBookBean.getKat().get(addBookBean.getid6()).getID());

                }
                q++;
            }
            addBookBean.setid7(addBookBean.getid6());

        }
        if (req.getParameter("button34") != null){
            int q=0;
            String pass3 = req.getParameter("name4");
            int id3=simpleBean.InsertKat(pass3);
            for (Tovar as:simpleBean.myList2()) {
                if (req.getParameter(Integer.toString(q))!=null) {
                    String pass = req.getParameter(Integer.toString(q));
                    int id = parsetoint(pass);
                    System.out.println("номер товара "+as.getID());
                    simpleBean.addTovarKat(as.getID(),id3);

                }
                q++;
            }
        }
        if (req.getParameter("button35") != null){
            int q=0;
            List<Kategor> listB = new ArrayList<Kategor>(addBookBean.getKat());
            listB.remove(addBookBean.getid6());
            for (Tovar as:simpleBean.myList(addBookBean.getKat().get(addBookBean.getid6()).getID())) {
                if (req.getParameter(Integer.toString(q))!=null) {
                    String pass = req.getParameter(Integer.toString(q));
                    int id = parsetoint(pass);
                    Boolean s=simpleBean.UpdateKat2(as.getID(),addBookBean.getKat().get(addBookBean.getid6()).getID(),listB.get(id).getID());
                    System.out.println("ошибка "+s);
                    if (!s)simpleBean.DeleteKat(as.getID(),addBookBean.getKat().get(addBookBean.getid6()).getID());
                    System.out.println("номер товара "+as.getID());


                }

                q++;
            }
            simpleBean.DeleteKat(addBookBean.getKat().get(addBookBean.getid6()).getID());
            addBookBean.setid6(0);
        }
        doGet(req, resp);
    }
}