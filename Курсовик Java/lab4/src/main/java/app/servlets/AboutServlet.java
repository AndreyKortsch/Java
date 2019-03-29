package app.servlets;

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

public class AboutServlet extends HttpServlet {
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
        String infoJava = req.getParameter("INFO1.java");
        System.out.println(infoJava);
        int info=parsetoint(infoJava);
        req.setAttribute("Tovar",simpleBean.getTovar(info));
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/about.jsp");
        requestDispatcher.forward(req, resp);

    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{



    }
}
