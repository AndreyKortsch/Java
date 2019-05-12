package app.servlets;
import app.entities.Client;
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

public class ZakazServlet extends HttpServlet {
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
        req.setAttribute("Selected", addBookBean.getList());
        System.out.println(addBookBean.getList());
        req.setAttribute("er", addBookBean.getas());
        req.setAttribute("Clients", addBookBean.getClient());
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/zakaz.jsp");
        requestDispatcher.forward(req, resp);

    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        if (req.getParameter("button2") != null)
        {   if (addBookBean.getClient().getRole().equals("неавторизованный пользователь"))
        {
            req.setAttribute("name", "кек");
            doGet(req, resp);

        }
        else{
            System.out.println(addBookBean.getClient().getID());
            int a=addBookBean.addZakaz(addBookBean.getClient().getID());
            System.out.println(a);
            addBookBean.addTovary(a);
            addBookBean.clear();
            req.setAttribute("result", singletonBean.message(a));
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/ok.jsp");
            requestDispatcher.forward(req, resp);}

        }

        if (req.getParameter("button6") != null){
            String pa = req.getParameter("button6");
            //System.out.println(pa);
            int id=parsetoint(pa);
            System.out.println(id);
            addBookBean.removeelement(id);
            if (addBookBean.getList().size()==0)resp.sendRedirect("/list");
            else
                doGet(req, resp);
            //req.setAttribute("id", addBookBean.getNumbers());
        }
        if (req.getParameter("button10") != null){
            int i=0;
            for (Tovar a:addBookBean.getList())
            {
                String pas = req.getParameter(Integer.toString(i));
                System.out.println(String.valueOf(i));
                int id=parsetoint(pas);
                System.out.println(i);
                addBookBean.updateBooks(id,i);
                i++;
            }
            //System.out.println(pa);
            doGet(req, resp);
            //req.setAttribute("id", addBookBean.getNumbers());
        }
        if (req.getParameter("button5") != null){


            String pass = req.getParameter("name");
            System.out.println(pass);

            String pass1 = req.getParameter("number_voler");
            System.out.println(pass1);
            System.out.println(pass1.matches("^((8|\\+7)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{7,10}$"));
            if (pass1.matches("^((8|\\+7)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{7,10}$")){

                if (req.getParameter("option")!=null)
                    simpleBean.sendEmail(pass,addBookBean.getList());

                //Client my=addBookBean.searchClient(new Client(pass1,pass));
                int a=0;
                //System.out.println(my.getEmail()+" "+my.getTelefon());
                if (addBookBean.searchClient(new Client(pass1,pass))==null) {
                    a=addBookBean.addZakaz(addBookBean.addClientID(new Client(pass1,pass)));
                    addBookBean.addTovary(a);
                }
                else {
                    a=addBookBean.addZakaz(addBookBean.searchClient(new Client(pass1,pass)).getID());
                    addBookBean.addTovary(a);
                }
                addBookBean.clear();
                req.setAttribute("result", singletonBean.message(a));
                req.setAttribute("Clients", addBookBean.getClient());
                RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/ok.jsp");
                requestDispatcher.forward(req, resp);}
            else
            {
                req.setAttribute("name", "кек");
                req.setAttribute("name1", "кек");
                doGet(req,resp);
            }

        }


    }
}
