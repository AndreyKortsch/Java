package app.servlets;
import app.entities.Postavshik;
import app.entities.Tovar;
import ejb.classes.AddBookBean;
import ejb.classes.SimpleBean;
import ejb.classes.SingletonBean;

import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ZakazPostavshikServlet extends HttpServlet {
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
        resp.setContentType("charset=UTF-8");
        req.setCharacterEncoding("UTF-8");
        req.setAttribute("Anim", addBookBean.getPostavshik());
        req.setAttribute("id", addBookBean.getnumber());
        req.setAttribute("Kat", addBookBean.getKat());
        List<Tovar> a;
        if (req.getParameter("button3") != null) {
            String password = req.getParameter("mynumbers");
            int id=parsetoint(password);
            a=simpleBean.myList3(id);
        }
        else a=simpleBean.myList2();
        for (Tovar as:a) {
            as.setListPostavshik(simpleBean.getListPostavshik(as.getID()));
            as.setNumbers2(simpleBean.summ(as));
        }
        req.setAttribute("Animals",a);
        req.setAttribute("Clients", addBookBean.getClient());
        RequestDispatcher requestDispatcher;
        if (addBookBean.getClient().getRole().equals("кладовщик")) {
            requestDispatcher = req.getRequestDispatcher("views/zakazpostavshik.jsp");
        }
        else requestDispatcher = req.getRequestDispatcher("views/error.jsp");
        requestDispatcher.forward(req, resp);

    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        resp.setContentType("charset=UTF-8");
        req.setCharacterEncoding("UTF-8");
        if (req.getParameter("button1") != null) {
            String password = req.getParameter("myselect");
            String pass = req.getParameter("button1");
            int id=parsetoint(pass);
            int id1=parsetoint(password);
            req.setAttribute("name", "кек");
            String pass1 = req.getParameter("mynumber");
            int id2=parsetoint(pass1);
                req.setAttribute("Anim", addBookBean.getPostavshik(id,id1,id2));
            doGet(req, resp);
        }

        if (req.getParameter("button4") != null){
            String pa = req.getParameter("button4");
            int id=parsetoint(pa);
            addBookBean.removeelementw(id);
            doGet(req, resp);
        }
        if (req.getParameter("button5") != null){
            for (Postavshik a:addBookBean.getPostavshik()){
                int id=simpleBean.addZakazPostavshik(a.getID());
                for (Tovar s:a.getListTovar()){
                    simpleBean.addZakazTovar(id,s);
                }
            simpleBean.sendEmail(a.getEmail(),a.getListTovar());}
            addBookBean.clear2();
            req.setAttribute("Clients", addBookBean.getClient());
            req.setAttribute("result", singletonBean.message(1));
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/ok.jsp");
            requestDispatcher.forward(req, resp);
        }

    }
}
