package app.servlets;

import ejb.classes.AddBookBean;
import ejb.classes.SimpleBean;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ListServlet extends HttpServlet {
    @EJB
    SimpleBean simpleBean;
    @Inject
    AddBookBean addBookBean;
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
        //DAO model1 = new DAO();
        //List<Animal> names = model1.listAllPerson();

        req.setAttribute("Anim", addBookBean.getList());
        req.setAttribute("id", addBookBean.getnumber());
        req.setAttribute("Kat", addBookBean.getKat());
        String infoJava = req.getParameter("INFO.java");
        System.out.println(infoJava);
        int info=parsetoint(infoJava);
        if (infoJava==null) info=1;
            req.setAttribute("Animals",simpleBean.myList(info));
        req.setAttribute("list",addBookBean.getNumbers());
        //req.setAttribute("answer", simpleBean.getMessage());
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/list.jsp");
        requestDispatcher.forward(req, resp);

    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        if (req.getParameter("button1") != null) {
            String password = req.getParameter("button1");
            String pass = req.getParameter("name");
            int id=parsetoint(pass);
            String pass1 = req.getParameter("mynumber");
            int id1=parsetoint(pass1);
            //System.out.println(pass);
            System.out.println(pass1);

            //Animal a=(Animal) req.getAttribute("s");
            if (id>=0)
            req.setAttribute("Anim", addBookBean.getBooks(parsetoint(password),id1));
            else
            req.setAttribute("Anim", addBookBean.getList());
        }
        if (req.getParameter("button3") != null){
            String pa = req.getParameter("button3");
            String pas = req.getParameter("name1");
            //System.out.println(pa);
            int id=parsetoint(pa);
            int i=parsetoint(pas);
            addBookBean.incrementnumber(i);
            //req.setAttribute("id", addBookBean.getNumbers());
        }
        if (req.getParameter("button4") != null){
            String pa = req.getParameter("button4");
            //System.out.println(pa);
            int id=parsetoint(pa);
            System.out.println(id);
            addBookBean.removeelement(id);
            //req.setAttribute("id", addBookBean.getNumbers());
        }
        if (req.getParameter("button2") != null){
            String pa = req.getParameter("button2");
            String pas = req.getParameter("name1");
            //System.out.println(pa);
            int id=parsetoint(pa);
            int i=parsetoint(pas);
            addBookBean.decrementnumber(i);
            //req.setAttribute("id", addBookBean.decrementnumber(id));
        }
        doGet(req, resp);
        }
}
