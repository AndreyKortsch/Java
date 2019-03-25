package app.servlets;

import ejb.classes.AddBookBean;
import ejb.classes.SimpleBean;
import ejb.classes.SingletonBean;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class OkServlet extends HttpServlet {
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
        req.setAttribute("result", singletonBean.message());
        addBookBean.clear();
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/ok.jsp");
        requestDispatcher.forward(req, resp);

    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        if (req.getParameter("button5") != null){
            System.out.println("ewr");
            //RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/ok.jsp");
            //requestDispatcher.forward(req, resp);
            ServletContext context = getServletContext().getContext("app.servlets.OkServlet");
            RequestDispatcher rd = context.getRequestDispatcher("/OkServlet");
            rd.forward(req, resp);
        }


        doGet(req, resp);
    }
}


