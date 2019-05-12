
package app.servlets;

        import app.entities.Location;
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

public class DvischenieServlet extends HttpServlet {
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
        req.setAttribute("Post", simpleBean.getListPost());
        req.setAttribute("Am",addBookBean.getid8());
        req.setAttribute("names5",simpleBean.setList(addBookBean.getid8()));
        System.out.println("номер в списке заказов "+addBookBean.getid9());
        req.setAttribute("Am2",addBookBean.getid9());
        if ((addBookBean.getid9()==0)&&(!(simpleBean.setList(addBookBean.getid8()).isEmpty())))
        req.setAttribute("names6",simpleBean.setList2(simpleBean.setList(addBookBean.getid8()).get(0).getID()));
        String infoJava = req.getParameter("INFO.java");
        int info=parsetoint(infoJava);
        if (infoJava==null) info=1;

        List<Tovar> sd=simpleBean.myList2();
        int a;
        int aw=0;
        if (info==1) {

            req.setAttribute("Animals", addBookBean.getListLoc3(simpleBean.ListLocation2()));
            req.setAttribute("Selected", addBookBean.getTovarys());


        }
        else{

            List<Tovar> asa=addBookBean.getBooksList2();
            for (Tovar as:asa) {
                as.setListLocation(simpleBean.getNextLocationList(as.getID()));
                System.out.println("Итог"+as.getNumbers2());
                if (sd.get(addBookBean.getId4()).getID()==as.getID()) aw=as.getNumbers2();
            }
            req.setAttribute("Selected2", asa);

        }
        req.setAttribute("Tovar", sd);
        req.setAttribute("Info", info);
        System.out.println("Сумма "+sd.get(addBookBean.getId4()).getNumbers2());
        req.setAttribute("T", sd.get(addBookBean.getId4()).getNumbers()-simpleBean.summ(sd.get(addBookBean.getId4()))-aw);
        req.setAttribute("Ta",addBookBean.getId4());
        req.setAttribute("Clients", addBookBean.getClient());
        RequestDispatcher requestDispatcher;
        if (addBookBean.getClient().getRole().equals("завсклада")) {
            requestDispatcher = req.getRequestDispatcher("views/dvischenie.jsp");
        }
        else requestDispatcher = req.getRequestDispatcher("views/error.jsp");
        requestDispatcher.forward(req, resp);

    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        if (req.getParameter("button6") != null) {
            String password = req.getParameter("number");
            String pass = req.getParameter("myselect");
            int id=parsetoint(pass);
            String pass2 = req.getParameter("myselect3");
            int id2=parsetoint(pass2);
            System.out.println(id);
            int id1=parsetoint(password);
            Tovar as=new Tovar(id,simpleBean.getTovar(id).getName(),id1);
            as.setLocation(addBookBean.getListLoc3(simpleBean.ListLocation2()).get(id2));
            addBookBean.addListLoc(addBookBean.getListLoc3(simpleBean.ListLocation2()).get(id2));
            addBookBean.addTovarys(as);
        }
        if (req.getParameter("myselect2") != null){
            String pass = req.getParameter("myselect2");
            int id=parsetoint(pass);
            System.out.println("згачение "+id);
            addBookBean.setid8(id);
            addBookBean.setid9(0);


        }
        if (req.getParameter("myselect89") != null){
            String pass = req.getParameter("myselect89");
            int id=parsetoint(pass);
            String pass1 = req.getParameter("name1");
            int id1=parsetoint(pass1);
            String pass2 = req.getParameter("name2");
            int id2=parsetoint(pass2);
            System.out.println("товар "+id1+"количество"+id2+"расположение "+addBookBean.getListLoc3(simpleBean.ListLocation2()).get(id));
            Location a=addBookBean.getListLoc3(simpleBean.ListLocation2()).get(id);
            System.out.println();
            a.setTovarID(id1);
            a.setNumbers(id2);
            addBookBean.addListLoc(a);
            System.out.println("товар "+addBookBean.getListLoc22().get(0).getTovarID());
            req.setAttribute("loc",addBookBean.getListLoc22());
            req.setAttribute("names6",simpleBean.setList2(simpleBean.setList(addBookBean.getid8()).get(addBookBean.getid9()).getID()));


        }
        if (req.getParameter("myselect6") != null){
            String pass = req.getParameter("myselect6");
            int id=parsetoint(pass);
            System.out.println("номер заказа "+id);
            addBookBean.setid9(id);
            req.setAttribute("names6",simpleBean.setList2(simpleBean.setList(addBookBean.getid8()).get(addBookBean.getid9()).getID()));
            for (Tovar a:simpleBean.setList2(simpleBean.setList(addBookBean.getid8()).get(addBookBean.getid9()).getID()))
            System.out.println("товар"+a.toString());
        }
        if (req.getParameter("button10") != null){
            String pa = req.getParameter("button10");
             int id=parsetoint(pa);
            addBookBean.removetovaris(id);
            addBookBean.getListLoc2(id);
        }
        if (req.getParameter("button14") != null){
            String pa = req.getParameter("button14");
            int id=parsetoint(pa);
            addBookBean.BooksList2remove(id);
        }
        if (req.getParameter("button12") != null){
            String pa = req.getParameter("button12");
            int id=parsetoint(pa);
            if (simpleBean.setList2(simpleBean.setList(addBookBean.getid8()).get(addBookBean.getid9()).getID()).size()!=
                    addBookBean.getListLoc22().size()) req.setAttribute("naem","кек");
            else {
                for (Location a : addBookBean.getListLoc22()) {
                    simpleBean.getListTovar4(a);
                    System.out.println("товарsds " + a.getTovarID() + "количество" + a.getNumbers());
                    simpleBean.getListTovar2(simpleBean.addTovars2(a.getTovarID())+a.getNumbers(),a.getTovarID());
                }
                simpleBean.deletestr(simpleBean.setList(addBookBean.getid8()).get(addBookBean.getid9()).getID());
                simpleBean.deletezak(simpleBean.setList(addBookBean.getid8()).get(addBookBean.getid9()).getID());
                addBookBean.getListq();
                addBookBean.setid8(id);
                addBookBean.setid9(0);
            }
        }
        List<Tovar> sd=simpleBean.myList2();
        if (req.getParameter("button16") != null){
            String password = req.getParameter("number4");
            String pass = req.getParameter("myselect4");
            int id=parsetoint(pass);
            int id2=parsetoint(password);
            //System.out.println("Номер="+id);
            //System.out.println("Номер2="+sd.get(id).getID());
            int a =sd.get(id).getNumbers2();
            List<Tovar> asa=addBookBean.getBooksList2();
            int b=0;
            addBookBean.getBooks2(sd.get(id).getID(),id2);
            for (Tovar as:asa) {
                if (as.getID()==sd.get(id).getID())
                {
                    b=as.getNumbers2();
                    as.setNumbers2(id2+b);
                }
            }

        }
        if (req.getParameter("myselect4") != null){
           String pass = req.getParameter("myselect4");
            int id=parsetoint(pass);
            addBookBean.setId4(id);
            req.setAttribute("Ta",id);
            req.setAttribute("T", sd.get(id).getNumbers()-simpleBean.summ(sd.get(id))-sd.get(id).getNumbers2());
        }
        if (req.getParameter("button22") != null){
            String pa = req.getParameter("button22");
            int id=parsetoint(pa);
             for (Tovar as:addBookBean.getBooksList2())
             {
                 Location a =simpleBean.getList2LocationList(as);
                 System.out.println(a.getID()+" "+ a.getTovarID()+" "+a.getNumbers());
                 simpleBean.deleteLocation(a.getTovarID(),a.getID()-1);
                 simpleBean.updateSum(a.getNumbers(),a.getID());
                 simpleBean.getListTovar2(simpleBean.addTovars2(a.getTovarID())-as.getNumbers(),a.getTovarID());
             }
             addBookBean.BooksList2clear();

        }

        doGet(req, resp);
    }
    }
