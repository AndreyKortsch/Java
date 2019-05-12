package app.servlets;

import app.entities.Tovar;
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
import java.util.List;

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
        String infoJava2 = req.getParameter("d");
        System.out.println(infoJava2);
        int info=parsetoint(infoJava);
        int info2=parsetoint(infoJava2);
        Tovar as=simpleBean.getTovar(info);
            as.setNumbers(simpleBean.summ2(as));
            as.setNumbers2(simpleBean.summ(as));
            System.out.println(as.getNumbers()+" "+as.getNumbers2());

        req.setAttribute("Tovar",as);
        req.setAttribute("qw",info2);
        req.setAttribute("Clients", addBookBean.getClient());
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/about.jsp");
        requestDispatcher.forward(req, resp);

    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        if (req.getParameter("button1") != null) {
            String password = req.getParameter("button1");
            String pass1 = req.getParameter("mynumber");
            int id1=parsetoint(pass1);
            System.out.println(pass1);
            req.setAttribute("Anim", addBookBean.getBooks(parsetoint(password),id1));
            resp.sendRedirect("/list");
        }


    }
}
