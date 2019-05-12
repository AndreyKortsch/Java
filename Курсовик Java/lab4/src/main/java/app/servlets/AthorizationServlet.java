
package app.servlets;

        import app.entities.AutorizationClient;
        import app.entities.Client;
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
        import java.lang.Integer;
        import java.util.Date;
        import java.text.SimpleDateFormat;
        import java.text.DateFormat;

public class AthorizationServlet extends HttpServlet {
    @EJB
    SingletonBean singletonBean;
    @EJB
    SimpleBean simpleBean;
    @Inject
    AddBookBean addBookBean;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/athorization.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("charset=UTF-8");
        req.setCharacterEncoding("UTF-8");
        if (req.getParameter("button5") != null){
            String pass = req.getParameter("name");
            System.out.println(pass);
            String pass1 = req.getParameter("number_voler");
            System.out.println(pass1);
            AutorizationClient a=simpleBean.getClient(new AutorizationClient(pass1,pass));
            if (a==null)
            { req.setAttribute("name", "кек");
            doGet(req, resp);
            }
            else {
                resp.sendRedirect("/list");
                addBookBean.setClient(a);
            }

        }



    }
}