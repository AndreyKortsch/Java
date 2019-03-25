package app.model;
import java.sql.*;
import java.util.Properties;
import java.util.Scanner;
import java.util.*;

import app.entities.Client;
import app.entities.Kategor;
import app.entities.Tovar;

import javax.annotation.Resource;
import javax.sql.*;
import javax.naming.*;


/**
 * Основной класс приложения
 */
public class DAO {
    private static final String PROPERTIES_FILENAME = "application.properties";
    private static final String DB_URL_PROPERTY = "db.url";
    private static final String DB_USERNAME_PROPERTY = "db.username";
    private static final String DB_PASSWORD_PROPERTY = "db.password";

    private Properties settings = new Properties();
    private Scanner scanner = new Scanner(System.in);
    private Connection connection;
    @Resource(name="TestDS")
    DataSource ds;

     /**
     * insert into Person
     */
     public void UpdateZakaz(int id, int sum) {

             String query =
                     "UPDATE  `Sklad`.`Заказ_клиента` SET `итого`=? WHERE `номер заказа`=?;";

             PreparedStatement stmt = null;
             ResultSet rs = null;


             try {
                 stmt = this.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

                 stmt.setInt(1, sum);
                 stmt.setInt(2, id);

                 int rows = stmt.executeUpdate();

                 if (rows > 0) {

                         System.out.println("Заказ №"+id+" успешно обновлен с итоговой суммой = " + sum);

                 } else {
                     System.out.println("Не удалось обновить заказ");
                 }
             } catch (SQLException sqle) {
                 System.out.println("Произошла ошибка при выполнении SQL запроса:");
                 System.out.println(sqle.getMessage());
             }
     }
     public void addTovary(int id,List<Tovar> Tovary) {
         int sum=0;
         for (Tovar a:Tovary) {
             String query =
                     "INSERT INTO `Sklad`.`Заказ_товар` (`номер_заказа`, `номер_товара`, `количество`) VALUES (? , ?, ?);";

             PreparedStatement stmt = null;
             ResultSet rs = null;


             try {
                 stmt = this.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

                 stmt.setInt(1, id);
                 stmt.setInt(2, a.getID());
                 stmt.setInt(3, a.getNumbers());
                 int rows = stmt.executeUpdate();
                 sum+=a.getPrice()*a.getNumbers();
                 if (rows > 0) {
                     rs = stmt.getGeneratedKeys();
                     if (rs.next()) {
                         System.out.println("Товар в заказе №"+id+" успешно добавлен с ID = " + rs.getInt(1));
                     }
                 } else {
                     System.out.println("Не удалось добавить товар.");
                 }
             } catch (SQLException sqle) {
                 System.out.println("Произошла ошибка при выполнении SQL запроса:");
                 System.out.println(sqle.getMessage());
             }
         }
         UpdateZakaz(id,sum);

     }
     public void addZakaz(int id,List<Tovar> Tovary)
     {
         String query =
                 "INSERT INTO `Sklad`.`Заказ_клиента` (`номер клиента`, `дата`, `статус`) VALUES (" +
                         "? , NOW(), ?);";

         PreparedStatement stmt = null;
         ResultSet rs = null;
         try {
             stmt = this.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

             stmt.setInt(1, id);
             stmt.setString(2, "создан");
             int rows = stmt.executeUpdate();

             if (rows > 0) {
                 rs = stmt.getGeneratedKeys();
                 if (rs.next()) {
                     System.out.println("Заказ успешно добавлен с ID = " + rs.getInt(1));
                     addTovary(rs.getInt(1),Tovary);

                 }
             } else {
                 System.out.println("Не удалось добавить пользователя.");
             }
         } catch (SQLException sqle) {
             System.out.println("Произошла ошибка при выполнении SQL запроса:");
             System.out.println(sqle.getMessage());
         }
         }

    public void addPerson(String a,String b, List<Tovar> Tovary) {

        establishConnection();
        if (searchUser(a,b)==null) {
            String query =
                    "INSERT INTO `Sklad`.`Клиент` (`логин`, `пароль`, `почта`, `телефон`) VALUES (" +
                            "NULL, NULL, ?, ?);";

            PreparedStatement stmt = null;
            ResultSet rs = null;

            try {
                stmt = this.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

                stmt.setString(2, a);
                stmt.setString(1, b);


                int rows = stmt.executeUpdate();

                if (rows > 0) {
                    rs = stmt.getGeneratedKeys();
                    if (rs.next()) {
                        addZakaz(rs.getInt(1),Tovary);
                        System.out.println("Клиент успешно добавлен с ID = " + rs.getInt(1));
                    }
                } else {
                    System.out.println("Не удалось добавить пользователя.");
                }
            } catch (SQLException sqle) {
                System.out.println("Произошла ошибка при выполнении SQL запроса:");
                System.out.println(sqle.getMessage());
            }
            finally {
                try {
                    if (rs != null && !rs.isClosed()) {
                        rs.close();
                    }
                } catch (Exception e) {
                    System.out.println("Произошла ошибка 1");
                    // do nothing
                }
                try {
                    if (stmt != null && !stmt.isClosed()) {
                        stmt.close();
                    }
                } catch (Exception e) {
                    // do nothing
                    System.out.println("Произошла ошибка 2");
                }
            }
        }
        else {
            System.out.println(searchUser(a,b).getID());
            addZakaz(searchUser(a,b).getID(),Tovary);
            //closeConnection();
        }
    }
public Client searchUser(String a,String b ) {
    establishConnection();

    String query = "select * from клиент " +
            "WHERE телефон= ? " +
            "and почта = ?";
    List<Tovar> AnimalList = new ArrayList<>();
    PreparedStatement stmt = null;
    ResultSet rs = null;
    Client as =null;
    int aa=0;
    try {

        stmt = this.connection.prepareStatement(query);
        stmt.setString(1, a);
        stmt.setString(2, b);
        rs = stmt.executeQuery();
        if (rs.next()) {
            as = new Client(
                    rs.getInt("номер_клиента"),
                    rs.getString("телефон"),
                    rs.getString("почта")
            );


        }

    }
    catch (SQLException sqle) {
        System.out.println("Произошла ошибка при выполнении SQL запроса:");
        System.out.println(sqle.getMessage());
    }
    return as;
}
    /**
     * select <brief> from Person
     */
    public List<Tovar> listAllPerson(int id) {

        establishConnection();
        String query = "select * from товар_категория,товар " +
                "WHERE товар_категория.номер_товара=товар.номер_товара " +
                "and товар_категория.номер_категории = ?";
        List<Tovar> AnimalList = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = this.connection.prepareStatement(query);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            while(rs.next()) {
                  AnimalList.add(new Tovar(
                                rs.getInt("номер_товара"),
                                rs.getString("название"),
                                rs.getFloat("вес"),
                                rs.getString("единица_измерения"),
                                rs.getString("страна_производитель"),
                                rs.getString("описание"),
                                rs.getFloat("цена"),
                                rs.getString("картинка")
                                ));

            }
        } catch (SQLException sqle) {
            System.out.println("Произошла ошибка при выполнении SQL запроса:");
            System.out.println(sqle.getMessage());
        } finally {
            try {
                if (rs != null && !rs.isClosed()) {
                    rs.close();
                }

            } catch (Exception e) {
                // do nothing
            }
            try {
                if (stmt != null && !stmt.isClosed()) {
                    stmt.close();
                }
                closeConnection();
            } catch (Exception e) {
                // do nothing
            }
        }

        // Вернуться в меню
        return AnimalList;
    }

    public List<Kategor> listAllKategories() {

        establishConnection();
        String query = "select * from Категории";
        List<Kategor> Kategoris = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = this.connection.prepareStatement(query);
            rs = stmt.executeQuery();
            while(rs.next()) {

                Kategoris.add(new Kategor(rs.getInt("номер"),
                        rs.getString("название")));

            }
        } catch (SQLException sqle) {
            System.out.println("Произошла ошибка при выполнении SQL запроса:");
            System.out.println(sqle.getMessage());
        } finally {
            try {
                if (rs != null && !rs.isClosed()) {
                    rs.close();
                }

            } catch (Exception e) {
                // do nothing
            }
            try {
                if (stmt != null && !stmt.isClosed()) {
                    stmt.close();
                }
                closeConnection();
            } catch (Exception e) {
                // do nothing
            }
        }

        // Вернуться в меню
        return Kategoris;
    }

    /**
     * select <full> from Person where id = <id>
     */
    public Tovar showPersonInfo(int aNumber) {

        establishConnection();
        String query = "select * from Товар where номер_товара = ? limit 1";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Tovar a=null;

        try {
            stmt = this.connection.prepareStatement(query);
            stmt.setInt(1, aNumber);
            rs = stmt.executeQuery();

            if (rs.next()) {


                 a = new Tovar(
                        rs.getInt("номер_товара"),
                        rs.getString("название"),
                        rs.getFloat("вес"),
                        rs.getString("единица_измерения"),
                        rs.getString("страна_производитель"),
                        rs.getString("описание"),
                        rs.getFloat("цена"),
                        rs.getString("картинка")

                );

                //System.out.println(String.format("%-19s %s", "1. Номер:", accountNumber));
                //System.out.println(String.format("%-19s %s", "2. Имя:", lastName));
               // System.out.println(String.format("%-19s %s", "3. Номер Вольера:", firstName));
               // System.out.println(String.format("%-19s %s", "4. Вид:", VidsID));
            } else {
                System.out.println("Пользователь с таким номером не найден.");
            }
        } catch (SQLException sqle) {
            System.out.println("Произошла ошибка при выполнении SQL запроса:");
            System.out.println(sqle.getMessage());
        } finally {
            try {
                if (rs != null && !rs.isClosed()) {
                    rs.close();
                }
            } catch (Exception e) {
                // do nothing
            }
            try {
                if (stmt != null && !stmt.isClosed()) {
                    stmt.close();
                }
            } catch (Exception e) {
                // do nothing
            }

        }


        return a;
    }


    /**
     * Установить соединение с БД
     */
    public void establishConnection() {


        try {
            InitialContext ctx = new InitialContext();
            ds = (DataSource) ctx.lookup("java:comp/env/jdbc/clients");
            this.connection = ds.getConnection();

        } catch (NamingException e){
            System.out.println("Произошла ошибка");
            e.printStackTrace();
        }
        catch (SQLException sqle) {
            System.out.println("Произошла ошибка при установке соединения с БД: ");
            System.out.println(sqle.getMessage());
            System.exit(1);
        }
    }

    /**
     * Закрытие соединения с БД
     */
    public void closeConnection() {
        try {
            if (this.connection != null) {
                this.connection.close();
            }
        } catch (SQLException sqle) {
            System.out.println("Произошла ошибка при закрытии соединения:");
            System.out.println(sqle.getMessage());
        }
    }

    /**
     * Загрузка параметров подключения к БД
     */

    }




