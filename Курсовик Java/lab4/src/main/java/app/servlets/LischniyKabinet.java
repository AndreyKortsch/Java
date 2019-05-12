package app.servlets;

import javax.inject.Inject;
import javax.servlet.http.HttpServlet;
import app.model.DAO;
import ejb.classes.SingletonBean;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.Integer;
import ejb.classes.AddBookBean;
import ejb.classes.SimpleBean;

public class LischniyKabinet extends HttpServlet {
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
        String infoJava = req.getParameter("INFO.java");
        if(infoJava!=null){

            if (infoJava.equals("new")) infoJava="создан";
            if (infoJava.equals("wait")) infoJava="закончен";
            if (infoJava.equals("end")) infoJava="отменен";
        }
        if(infoJava==null)infoJava="создан";
        System.out.println(infoJava);

        req.setAttribute("Clients", addBookBean.getClient());

        if (infoJava.equals("создан")){
            req.setAttribute("Zakazs", simpleBean.getClientZakazs());
            req.setAttribute("Selected", simpleBean.getListTovars());
        }
        else {
                req.setAttribute("Zakazs", simpleBean.getClientZakaz(infoJava));
                req.setAttribute("Selected", simpleBean.getListTovar(infoJava));
        }
        req.setAttribute("Kat",infoJava);
        //req.setAttribute("answer", simpleBean.getMessage());
        RequestDispatcher requestDispatcher;
        System.out.println("клиент"+addBookBean.getClient().getRole());
        if (addBookBean.getClient().getRole().equals("авторизированный пользователь")) {
            requestDispatcher = req.getRequestDispatcher("views/lischniykabinet.jsp");
        }
        else requestDispatcher = req.getRequestDispatcher("views/error.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("charset=UTF-8");
        req.setCharacterEncoding("UTF-8");
        DAO dao=new DAO();
        if (req.getParameter("button1") != null) {
            String password = req.getParameter("button1");
            int id = parsetoint(password);
            System.out.println(id);
            simpleBean.updateStatus(id, "принят");
        }
        //System.out.println(pass);
        if (req.getParameter("button2") != null) {
            String password = req.getParameter("button2");
            int id = parsetoint(password);
            simpleBean.updateStatus(id, "ожидает");
        }
        if (req.getParameter("button3") != null) {
            String password = req.getParameter("button3");
            int id = parsetoint(password);
            simpleBean.updateStatus(id, "отменен");
        }

        doGet(req, resp);
    }



}