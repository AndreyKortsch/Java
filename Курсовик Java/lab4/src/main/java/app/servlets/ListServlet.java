package app.servlets;

import app.entities.Tovar;
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
import java.util.List;

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
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        req.setAttribute("Anim", addBookBean.getList());
        req.setAttribute("id", addBookBean.getnumber());
        req.setAttribute("Kat", addBookBean.getKat());
        req.setAttribute("Clients", addBookBean.getClient());
        List<Tovar> asa;
        String infoJava = req.getParameter("INFO.java");
        System.out.println(infoJava);
        int info=parsetoint(infoJava);
        if (infoJava==null) info=1;
        String na="";
        if (req.getParameter("mytext")!=null)
        {
            na=new String(req.getParameter("mytext").getBytes("ISO-8859-1"),"UTF8");
            System.out.println("строка "+na);
            if (na.length()!=0)
                asa=simpleBean.searchTovar(na);
            else asa=simpleBean.myList(1);
        }
        else asa=simpleBean.myList(info);
        if (req.getParameter("button38")!=null)
        {
            String qa=req.getParameter("button38");
            int id=parsetoint(qa);
            if (3*(id+1)>asa.size()){
                req.setAttribute("номер",3*id);
                req.setAttribute("номер2",asa.size());
            }
            else{
                req.setAttribute("номер",3*id);
                req.setAttribute("номер2",3*(id+1));
            }
         }
        else {
            if (req.getParameter("mytext")!=null)
            {
                if (na.length()!=0) {
                    req.setAttribute("номер", 0);
                    req.setAttribute("номер2", asa.size());
                }
            }else{
                if (3>asa.size()){
                    req.setAttribute("номер",0);
                    req.setAttribute("номер2",asa.size());
                }
                else{
            req.setAttribute("номер",0);
            req.setAttribute("номер2",3);}
            }
        }
        req.setAttribute("text", na);
        for (Tovar as:asa) {
            as.setNumbers(simpleBean.summ2(as));
            as.setNumbers2(simpleBean.summ(as));
            System.out.println(as.getNumbers()+""+as.getNumbers2());
        }
            req.setAttribute("Animals",asa);
        req.setAttribute("list",addBookBean.getNumbers());
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/list.jsp");
        requestDispatcher.forward(req, resp);

    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        if (req.getParameter("button1") != null) {
            String password = req.getParameter("button1");
            String pass = req.getParameter("name");
            int id=parsetoint(pass);
            String pass1 = req.getParameter("mynumber");
            int id1=parsetoint(pass1);
            //System.out.println(pass);
            //System.out.println(pass1);
            req.setAttribute("name", "кек");
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
            //addBookBean.incrementnumber(i);
            //req.setAttribute("id", addBookBean.getNumbers());
        }
        if (req.getParameter("button4") != null){
            String pa = req.getParameter("button4");
            //System.out.println(pa);
            int id=parsetoint(pa);
            //System.out.println(id);
            addBookBean.removeelement(id);
            //req.setAttribute("id", addBookBean.getNumbers());
        }
        if (req.getParameter("button2") != null){
            String pa = req.getParameter("button2");
            String pas = req.getParameter("name1");
            //System.out.println(pa);
            int id=parsetoint(pa);
            int i=parsetoint(pas);
            //addBookBean.decrementnumber(i);
            //req.setAttribute("id", addBookBean.decrementnumber(id));
        }
        doGet(req, resp);
        }
}
