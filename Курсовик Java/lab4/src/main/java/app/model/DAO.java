package app.model;
import java.sql.*;
import java.util.Properties;
import java.util.Scanner;
import java.util.*;

import app.entities.*;

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
     public void UpdateStatusZakaz(int id, String sum) {
         establishConnection();
         String query =
                 "UPDATE  `Sklad`.`Заказ_клиента` SET `статус`=?,`ряд`=NULL, " +
                         "`стойка`=NULL,`ярус`=NULL WHERE `номер заказа`=?";

         PreparedStatement stmt = null;
         ResultSet rs = null;


         try {
             stmt = this.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

             stmt.setString(1, sum);
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
     public void UpdateZakaz(int id, int sum) {
            establishConnection();
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
    public void UpdateKategor(String id, int sum) {
        establishConnection();
        String query =
                "UPDATE  `Sklad`.`категории` SET `название`=? WHERE `номер`=?;";

        PreparedStatement stmt = null;
        ResultSet rs = null;


        try {
            stmt = this.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            stmt.setString(1, id);
            stmt.setInt(2, sum);

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
    public boolean UpdateKategor3(int a,int id, int sum) {
        establishConnection();
        String query =
                "UPDATE  `Sklad`.`товар_категория` SET `номер_категории`=? WHERE `номер_категории`=?" +
                        "  AND `номер_товара`=?;";

        PreparedStatement stmt = null;
        ResultSet rs = null;


        try {
            stmt = this.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            stmt.setInt(1, sum);
            stmt.setInt(2, id);
            stmt.setInt(3, a);

            int rows = stmt.executeUpdate();

            if (rows > 0) {

                System.out.println("Заказ №"+id+" успешно обновлен с итоговой суммой = " + sum);

            } else {
                System.out.println("Не удалось обновить заказ");
            }
        } catch (SQLException sqle) {
            System.out.println("Произошла ошибка при выполнении SQL запроса:");
            System.out.println(sqle.getMessage());
            return false;
        }
        return true;
    }
    public int setNumber(int id,int sum) {
        establishConnection();
        String query =
                "SELECT `номер записи` from" +
                        "`Sklad`.`Расположение товара` where `номер записи`>? and `номер_товара`=? LIMIT 1;";

        PreparedStatement stmt = null;
        ResultSet rs = null;
        int a=0;
        try {
            stmt = this.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            stmt.setInt(1, id);
            stmt.setInt(2, sum);
            rs = stmt.executeQuery();

            if (rs.next()) {
                a=rs.getInt(1);
                System.out.println("Заказ №"+a+" успешно обновлен с итоговой суммой = ");

            } else {
                System.out.println("Не удалось обновить заказ");
            }
        } catch (SQLException sqle) {
            System.out.println("Произошла ошибка при выполнении SQL запроса:");
            System.out.println(sqle.getMessage());
        }
        return a;
    }
    public void UpdateSum(int id, int sum) {
establishConnection();
        String query =
                "UPDATE  `Sklad`.`Расположение товара` " +
                        "SET `количество`=? WHERE `номер записи`=?;";

        PreparedStatement stmt = null;
        ResultSet rs = null;


        try {
            stmt = this.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            stmt.setInt(1, id);
            stmt.setInt(2, sum);
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
    public void DeleteCat(int id, int sum) {
        establishConnection();
        String query =
                "DELETE from `Sklad`.`товар_категория` WHERE `номер_товара`=? and `номер_категории`=?";

        PreparedStatement stmt = null;
        ResultSet rs = null;


        try {
            stmt = this.connection.prepareStatement(query);

            stmt.setInt(1, id);
            stmt.setInt(2, sum);

            int rows = stmt.executeUpdate();

            if (rows > 0) {

                System.out.println("Товар №"+id+" успешно удален ");

            } else {
                System.out.println("Не удалось обновить заказ");
            }
        } catch (SQLException sqle) {
            System.out.println("Произошла ошибка при выполнении SQL запроса:");
            System.out.println(sqle.getMessage());
        }
    }
    public void DeleteStr(int id) {
        establishConnection();
        String query =
                "DELETE from `Sklad`.`строка_заказа` WHERE `номер_заказа`=?";

        PreparedStatement stmt = null;
        ResultSet rs = null;


        try {
            stmt = this.connection.prepareStatement(query);

            stmt.setInt(1, id);

            int rows = stmt.executeUpdate();

            if (rows > 0) {

                System.out.println("Товар №"+id+" успешно удален ");

            } else {
                System.out.println("Не удалось обновить заказ");
            }
        } catch (SQLException sqle) {
            System.out.println("Произошла ошибка при выполнении SQL запроса:");
            System.out.println(sqle.getMessage());
        }
    }
    public void DeleteZak(int id) {
        establishConnection();
        String query =
                "DELETE from `Sklad`.`заказ_поставщику` WHERE `номер_заказа`=?";

        PreparedStatement stmt = null;
        ResultSet rs = null;


        try {
            stmt = this.connection.prepareStatement(query);

            stmt.setInt(1, id);

            int rows = stmt.executeUpdate();

            if (rows > 0) {

                System.out.println("Товар №"+id+" успешно удален ");

            } else {
                System.out.println("Не удалось обновить заказ");
            }
        } catch (SQLException sqle) {
            System.out.println("Произошла ошибка при выполнении SQL запроса:");
            System.out.println(sqle.getMessage());
        }
    }
    public void DeleteCat2(int id) {
        establishConnection();
        String query =
                "DELETE from `Sklad`.`категории` WHERE `номер`=?";

        PreparedStatement stmt = null;
        ResultSet rs = null;


        try {
            stmt = this.connection.prepareStatement(query);

            stmt.setInt(1, id);

            int rows = stmt.executeUpdate();

            if (rows > 0) {

                System.out.println("Товар №"+id+" успешно удален ");

            } else {
                System.out.println("Не удалось обновить заказ");
            }
        } catch (SQLException sqle) {
            System.out.println("Произошла ошибка при выполнении SQL запроса:");
            System.out.println(sqle.getMessage());
        }
    }
    public void DeleteLocation(int id, int sum) {
establishConnection();
        String query =
                "DELETE from `Sklad`.`Расположение товара` WHERE `номер_товара`=? and `номер записи`<=?";

        PreparedStatement stmt = null;
        ResultSet rs = null;


        try {
            stmt = this.connection.prepareStatement(query);

            stmt.setInt(1, id);
            stmt.setInt(2, sum);

            int rows = stmt.executeUpdate();

            if (rows > 0) {

                System.out.println("Товар №"+id+" успешно удален ");

            } else {
                System.out.println("Не удалось обновить заказ");
            }
        } catch (SQLException sqle) {
            System.out.println("Произошла ошибка при выполнении SQL запроса:");
            System.out.println(sqle.getMessage());
        }
    }
     public void addTovary(int id,List<Tovar> Tovary) {
         establishConnection();
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
    public void addPostavchikTovar(int id,List <Integer> ad) {
        establishConnection();
        int sum=0;
        for (Integer a:ad) {
            String query =
                    "INSERT INTO `Sklad`.`Поставшик_товар` (`номер_товара`, `номер_поставщика`) VALUES (? , ?);";

            PreparedStatement stmt = null;
            ResultSet rs = null;


            try {
                stmt = this.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

                stmt.setInt(1, a);
                stmt.setInt(2, id);
                int rows = stmt.executeUpdate();
                if (rows > 0) {
                    rs = stmt.getGeneratedKeys();
                    if (rs.next()) {
                        System.out.println("Товар в заказе №"+a+" успешно добавлен к поставщику = " + a);
                    }
                } else {
                    System.out.println("Не удалось добавить товар.");
                }
            } catch (SQLException sqle) {
                System.out.println("Произошла ошибка при выполнении SQL запроса:");
                System.out.println(sqle.getMessage());
            }
        }


    }
    public void addTovar(int a,int id) {
        establishConnection();
            String query =
                    "INSERT INTO `Sklad`.`регистр_остатков_товаров` (`номер_товара`, `количество`) VALUES (? , ?);";

            PreparedStatement stmt = null;
            ResultSet rs = null;


            try {
                stmt = this.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

                stmt.setInt(1, id);
                stmt.setInt(2, a);
                int rows = stmt.executeUpdate();
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
    public void addTovar3(Tovar a) {
        establishConnection();
        String query =
                "INSERT INTO `Sklad`.`Расположение товара` (`номер_товара`,`ряд`,`стойка`,`ярус`,`количество`) VALUES (? , ?,?,?,?);";

        PreparedStatement stmt = null;
        ResultSet rs = null;


        try {
            stmt = this.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            stmt.setInt(1, a.getID());
            stmt.setString(2, a.getLocation().getRjad());
            stmt.setInt(3, a.getLocation().getStojka());
            stmt.setInt(4, a.getLocation().getJarus());
            stmt.setInt(5, a.getNumbers());
            int rows = stmt.executeUpdate();
            if (rows > 0) {
                rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    System.out.println("Товар №"+a.getID()+" успешно добавлен с ID = " + rs.getInt(1));
                }
            } else {
                System.out.println("Не удалось добавить товар.");
            }
        } catch (SQLException sqle) {
            System.out.println("Произошла ошибка при выполнении SQL запроса:");
            System.out.println(sqle.getMessage());
        }
    }
    public void addTovar4(Location a) {
        establishConnection();
        String query =
                "INSERT INTO `Sklad`.`Расположение товара` (`номер_товара`,`ряд`,`стойка`,`ярус`,`количество`) VALUES (? , ?,?,?,?);";

        PreparedStatement stmt = null;
        ResultSet rs = null;


        try {
            stmt = this.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, a.getTovarID());
            stmt.setString(2, a.getRjad());
            stmt.setInt(3, a.getStojka());
            stmt.setInt(4, a.getJarus());
            stmt.setInt(5, a.getNumbers());
            int rows = stmt.executeUpdate();
            if (rows > 0) {
                rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    System.out.println("Товар №"+a.getID()+" успешно добавлен с ID = " + rs.getInt(1));
                }
            } else {
                System.out.println("Не удалось добавить товар.");
            }
        } catch (SQLException sqle) {
            System.out.println("Произошла ошибка при выполнении SQL запроса:");
            System.out.println(sqle.getMessage());
        }
    }

     public int addZakaz(int id)
     {
         establishConnection();
         String query =
                 "INSERT INTO `Sklad`.`Заказ_клиента` (`номер клиента`, `дата`, `статус`) VALUES (" +
                         "? , NOW(), ?);";

         PreparedStatement stmt = null;
         ResultSet rs = null;
         int s=0;
         try {
             stmt = this.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
             stmt.setInt(1, id);
             stmt.setString(2, "создан");
             int rows = stmt.executeUpdate();
             if (rows > 0) {
                 rs = stmt.getGeneratedKeys();
                 if (rs.next()) {
                    System.out.println("Заказ успешно добавлен с ID = " + rs.getInt(1));
                    s=rs.getInt(1);
                 }
             } else {
                 System.out.println("Не удалось добавить пользователя.");
             }
         } catch (SQLException sqle) {
             System.out.println("Произошла ошибка при выполнении SQL запроса:");
             System.out.println(sqle.getMessage());
         }
        System.out.println(s);
         System.out.println(s);
         return s;
         }
    public int addZakazPostavshik(int id)
    {
        establishConnection();
        String query =
                "INSERT INTO `Sklad`.`Заказ_поставщику` (`номер_поставщика`, `дата`) VALUES (" +
                        "? , NOW());";

        PreparedStatement stmt = null;
        ResultSet rs = null;
        int s=0;
        try {
            stmt = this.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, id);
           int rows = stmt.executeUpdate();
            if (rows > 0) {
                rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    System.out.println("Заказ успешно добавлен с ID = " + rs.getInt(1));
                    s=rs.getInt(1);
                }
            } else {
                System.out.println("Не удалось добавить пользователя.");
            }
        } catch (SQLException sqle) {
            System.out.println("Произошла ошибка при выполнении SQL запроса:");
            System.out.println(sqle.getMessage());
        }
        System.out.println(s);
        System.out.println(s);
        return s;
    }
    public void addZakazTovar(int id,Tovar a)
    {
        establishConnection();
        String query =
                "INSERT INTO `Sklad`.`Строка_заказа` (`номер_товара`, `номер_заказа`,`количество`) VALUES (?,?,?);";

        PreparedStatement stmt = null;
        ResultSet rs = null;
        int s=0;
        try {
            stmt = this.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, a.getID());
            stmt.setInt(2, id);
            stmt.setInt(3, a.getNumbers());
            int rows = stmt.executeUpdate();
            if (rows > 0) {
                rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    System.out.println("Заказ успешно добавлен с ID = " + rs.getInt(1));
                    s=rs.getInt(1);
                }
            } else {
                System.out.println("Не удалось добавить пользователя.");
            }
        } catch (SQLException sqle) {
            System.out.println("Произошла ошибка при выполнении SQL запроса:");
            System.out.println(sqle.getMessage());
        }


    }
    public int addPerson(Client a) {

        establishConnection();

            String query =
                    "INSERT INTO `Sklad`.`Клиент` (`логин`, `пароль`, `почта`, `телефон`,`номер_роли`) VALUES (" +
                            "NULL, NULL, ?, ?,1);";

            PreparedStatement stmt = null;
            ResultSet rs = null;
            int s=0;
            try {
                stmt = this.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

                stmt.setString(2, a.getTelefon());
                stmt.setString(1, a.getEmail());


                int rows = stmt.executeUpdate();

                if (rows > 0) {
                    rs = stmt.getGeneratedKeys();
                    if (rs.next()) {
                        s=rs.getInt(1);
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
       return s;
    }
    public int addTovarKat(int a,int b) {

        establishConnection();

        String query =
                "INSERT INTO `Sklad`.`товар_категория` (`номер_товара`, `номер_категории`) VALUES (" +
                        "?, ?);";

        PreparedStatement stmt = null;
        ResultSet rs = null;
        int s=0;
        try {
            stmt = this.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            stmt.setInt(2, b);
            stmt.setInt(1, a);


            int rows = stmt.executeUpdate();

            if (rows > 0) {
                rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    s=rs.getInt(1);
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
        return s;
    }
    public int InsertKat(String b) {

        establishConnection();

        String query =
                "INSERT INTO `Sklad`.`категории` (`название`) VALUES (" +
                        "?);";

        PreparedStatement stmt = null;
        ResultSet rs = null;
        int s=0;
        try {
            stmt = this.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);


            stmt.setString(1, b);


            int rows = stmt.executeUpdate();

            if (rows > 0) {
                rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    s=rs.getInt(1);
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
        return s;

    }
    public int selectSum(int id,int Num) {

        establishConnection();
        String query = "select sum(количество) as sum " +
                "from `Расположение товара` " +
                "WHERE номер_товара=? AND `номер записи`<=?";
        int a=0;
        Location AnimalList = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = this.connection.prepareStatement(query);
            stmt.setInt(1, id);
            stmt.setInt(2, Num);
            rs = stmt.executeQuery();
            if (rs.next()) {
                a=rs.getInt(1);


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
        return  a;
    }
    public AutorizationClient searchUsers(AutorizationClient a) {
        establishConnection();

        String query = "select * from клиент,роли " +
                "WHERE логин= ? " +
                "and пароль = ? and клиент.номер_роли=роли.номер_роли";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        AutorizationClient as =null;
        int aa=0;
        try {

            stmt = this.connection.prepareStatement(query);
            stmt.setString(1, a.getLogin());
            stmt.setString(2, a.getPassword());
            rs = stmt.executeQuery();
            if (rs.next()) {
                as = new AutorizationClient(
                        rs.getInt("номер_клиента"),
                        rs.getString("телефон"),
                        rs.getString("почта"),
                        rs.getString("логин"),
                        rs.getString("пароль"),
                        rs.getString("название")
                );


            }

        }
        catch (SQLException sqle) {
            System.out.println("Произошла ошибка при выполнении SQL запроса:");
            System.out.println(sqle.getMessage());
        }
        return as;
    }
public Client searchUser(Client a) {
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
        stmt.setString(1, a.getTelefon());
        stmt.setString(2, a.getEmail());
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
    public Location getNextLocation(int id,int Num) {

        establishConnection();
        String query = "select `номер записи`,номер_товара,sum(количество) as sum " +
                "from `Расположение товара` " +
                "WHERE номер_товара=? GROUP by `номер записи`,номер_товара HAVING sum<=? ORDER BY `номер записи` DESC limit 1";
       Location AnimalList = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = this.connection.prepareStatement(query);
            stmt.setInt(1, id);
            stmt.setInt(2, Num);
            rs = stmt.executeQuery();
            if (rs.next()) {
                AnimalList=new Location(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getInt(3)

                );

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
        return  AnimalList;
    }
    public List<Location> getNextLocation2(int id) {

        establishConnection();
        String query = "select `номер записи`,ряд,стойка, ярус,количество " +
                "from `Расположение товара` " +
                "WHERE номер_товара=? ";
        List<Location> AnimalList = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = this.connection.prepareStatement(query);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            while (rs.next()) {
                AnimalList.add(new Location(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getInt(4),
                        rs.getInt(5))
                );

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
        return  AnimalList;
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
    public List<Client> listAllClient(String s) {

        establishConnection();
        String query = "select * from клиент,заказ_клиента " +
                "WHERE клиент.номер_клиента=заказ_клиента.`номер клиента` " +
                "and заказ_клиента.статус = ? GROUP By клиент.номер_клиента";
        List<Client> AnimalList = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = this.connection.prepareStatement(query);
            stmt.setString(1, s);
            rs = stmt.executeQuery();
            while(rs.next()) {
                AnimalList.add(new Client(
                        rs.getInt("номер_клиента"),
                        rs.getString("телефон"),
                        rs.getString("почта")

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
    public List<Zakaz> listAllZakazs() {

        establishConnection();
        String query = "select * from заказ_клиента " +
                "WHERE заказ_клиента.статус <>'закончен' AND заказ_клиента.статус <>'отменен'";
        List<Zakaz> AnimalList = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = this.connection.prepareStatement(query);
            rs = stmt.executeQuery();
            while(rs.next()) {
                AnimalList.add(new Zakaz(
                        rs.getInt("номер заказа"),
                        rs.getInt("номер клиента"),
                        rs.getDate("дата"),
                        rs.getDouble("итого"),
                        rs.getString("ряд"),
                        rs.getInt("стойка"),
                        rs.getInt("ярус"),
                        rs.getString("статус")
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
    public List<Zakaz> listAllZakazs(int id) {

        establishConnection();
        String query = "select * from заказ_поставщику " +
                "WHERE номер_поставщика=?";
        List<Zakaz> AnimalList = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = this.connection.prepareStatement(query);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            while(rs.next()) {
                AnimalList.add(new Zakaz(
                        rs.getInt("номер_заказа"),
                        rs.getInt("номер_поставщика"),
                        rs.getDate("дата")

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
    public List<Tovar> listAllTovars(int id) {

        establishConnection();
        String query = "select товар.номер_товара,товар.название, строка_заказа.количество from строка_заказа,товар " +
                "WHERE строка_заказа.номер_товара=товар.номер_товара AND Строка_заказа.номер_заказа=?";
        List<Tovar> AnimalList = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = this.connection.prepareStatement(query);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            while(rs.next()) {
                AnimalList.add(new Tovar(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3)

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
    public List<Zakaz> listAllZakaz(String s) {

        establishConnection();
        String query = "select * from заказ_клиента " +
                "WHERE заказ_клиента.статус = ?";
        List<Zakaz> AnimalList = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = this.connection.prepareStatement(query);
            stmt.setString(1, s);
            rs = stmt.executeQuery();
            while(rs.next()) {
                AnimalList.add(new Zakaz(
                        rs.getInt("номер заказа"),
                        rs.getInt("номер клиента"),
                        rs.getDate("дата"),
                        rs.getDouble("итого"),
                        rs.getString("ряд"),
                        rs.getInt("стойка"),
                        rs.getInt("ярус"),
                        rs.getString("статус")
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
    public List<Postavshik> listAllPostavchik(int s) {

        establishConnection();
        String query = "select `номер поставщика`,наименование_организации,адрес,почта from поставщики";
        List<Postavshik> AnimalList = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = this.connection.prepareStatement(query);
            rs = stmt.executeQuery();
            while(rs.next()) {
                AnimalList.add(new Postavshik(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4)
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
    public List<Integer> listAllTovarIndex(int s) {

        establishConnection();
        String query = "select номер_поставщика,номер_товара from поставшик_товар " +
                "WHERE номер_поставщика=?";
        List<Integer> AnimalList = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = this.connection.prepareStatement(query);
            stmt.setInt(1, s);
            rs = stmt.executeQuery();
            while(rs.next()) {
                AnimalList.add(
                        rs.getInt(2)
                );

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
    public List<Tovar> listAllTovar(String s) {

        establishConnection();
        String query = "select Заказ_товар.номер_заказа,sum(заказ_товар.количество) as sum,Товар.название,Товар.цена " +
                "from заказ_клиента,Заказ_товар,товар " +
                "WHERE заказ_клиента.статус = ? and заказ_клиента.`номер заказа`=" +
                "заказ_товар.номер_заказа " +
                "AND заказ_товар.номер_товара=товар.номер_товара GROUP BY Заказ_товар.номер_заказа," +
                "Заказ_товар.номер_товара";
        List<Tovar> AnimalList = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = this.connection.prepareStatement(query);
            stmt.setString(1, s);
            rs = stmt.executeQuery();
            while(rs.next()) {
                AnimalList.add(new Tovar(
                        rs.getInt(1),
                        rs.getString(3),
                        rs.getFloat(4),
                        rs.getInt(2)

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
    public List<Tovar> listAllTovaro() {

        establishConnection();
        String query = "select товар.номер_товара,товар.картинка,`Расположение товара`.номер_товара,sum(`Расположение товара`.количество) as sum,Товар.название,Товар.цена " +
                "from товар Left JOIN `Расположение товара` " +
                "ON товар.номер_товара=`Расположение товара`.номер_товара  GROUP BY `Расположение товара`.номер_товара";
        List<Tovar> AnimalList = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = this.connection.prepareStatement(query);
            rs = stmt.executeQuery();
            while(rs.next()) {
                AnimalList.add(new Tovar(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(5),
                        rs.getFloat(6),
                        rs.getInt(4)

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
    public int listTovarSum(Tovar a) {

        establishConnection();
        String query = "select sum(количество) as sum " +
                "from заказ_товар,заказ_клиента where заказ_клиента.`номер заказа` =заказ_товар.номер_заказа AND " +
                "заказ_товар.номер_товара=? AND" +
                "(статус='создан' OR статус='ожидает' OR статус ='принят')";
        int AnimalList = 0;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = this.connection.prepareStatement(query);
            stmt.setInt(1,a.getID());
            rs = stmt.executeQuery();
            if(rs.next()) {
                AnimalList=rs.getInt(1
                );

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
    public int listTovarSum2(Tovar a) {

        establishConnection();
        String query = "select sum(количество) as sum " +
                "from `Расположение товара` where номер_товара=? ";
        int AnimalList = 0;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = this.connection.prepareStatement(query);
            stmt.setInt(1,a.getID());
            rs = stmt.executeQuery();
            if(rs.next()) {
                AnimalList=rs.getInt(1
                );

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
    public List<Tovar> listAllTovarov(int s) {

        establishConnection();
        String query = "select товар.номер_товара,товар.картинка,`Расположение товара`.номер_товара,sum(`Расположение товара`.количество) as sum,Товар.название,Товар.цена " +
                "from товар Left JOIN `Расположение товара` " +
                "ON товар.номер_товара=`Расположение товара`.номер_товара  GROUP BY `Расположение товара`.номер_товара HAVING sum<=?";
        List<Tovar> AnimalList = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = this.connection.prepareStatement(query);
            stmt.setInt(1, s);
            rs = stmt.executeQuery();
            while(rs.next()) {
                AnimalList.add(new Tovar(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(5),
                        rs.getFloat(6),
                        rs.getInt(4)

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
    public List<Tovar> listAllTovary(String s) {

        establishConnection();
        String query = "select товар.номер_товара,Заказ_товар.номер_заказа,sum(заказ_товар.количество) as sum,Товар.название,Товар.цена " +
                "from заказ_клиента,Заказ_товар,товар " +
                "WHERE заказ_клиента.статус = ? and заказ_клиента.`номер заказа`=" +
                "заказ_товар.номер_заказа " +
                "AND заказ_товар.номер_товара=товар.номер_товара GROUP BY " +
                "Заказ_товар.номер_товара";
        List<Tovar> AnimalList = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = this.connection.prepareStatement(query);
            stmt.setString(1, s);
            rs = stmt.executeQuery();
            while(rs.next()) {
                AnimalList.add(new Tovar(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getString(4),
                        rs.getFloat(5),
                        rs.getInt(3)

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
    public List<Tovar> listAllTovars() {

        establishConnection();
        String query = "select Заказ_товар.номер_заказа,sum(заказ_товар.количество) as sum,Товар.название,Товар.цена " +
                "from заказ_клиента,Заказ_товар,товар " +
                "WHERE заказ_клиента.статус <>'закончен' and заказ_клиента.статус <>'отменен' and заказ_клиента.`номер заказа`= " +
                "заказ_товар.номер_заказа " +
                "AND заказ_товар.номер_товара=товар.номер_товара GROUP BY Заказ_товар.номер_заказа," +
                "Заказ_товар.номер_товара";
        List<Tovar> AnimalList = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = this.connection.prepareStatement(query);
            rs = stmt.executeQuery();
            while(rs.next()) {
                AnimalList.add(new Tovar(
                        rs.getInt(1),
                        rs.getString(3),
                        rs.getFloat(4),
                        rs.getInt(2)

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
    public List<Tovar> listAllTovar(int s) {

        establishConnection();
        String query = "select Заказ_товар.номер_товара,sum(заказ_товар.количество) as sum " +
                "from Заказ_товар " +
                "WHERE заказ_товар.номер_заказа = ? GROUP BY " +
                "Заказ_товар.номер_товара,Заказ_товар.номер_заказа";
        List<Tovar> AnimalList = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = this.connection.prepareStatement(query);
            stmt.setInt(1, s);
            rs = stmt.executeQuery();
            while(rs.next()) {
                AnimalList.add(new Tovar(
                        rs.getInt(1),
                        rs.getInt(2)


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
    public int listAllTovarw(int s) {

        establishConnection();
        String query = "select количество " +
                "from регистр_остатков_товаров " +
                "WHERE номер_товара = ? ORDER BY номер_записи DESC " +
                "LIMIT 1";
        int a=0;
        List<Tovar> AnimalList = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = this.connection.prepareStatement(query);
            stmt.setInt(1, s);
            rs = stmt.executeQuery();
            if (rs.next()) {
                a=rs.getInt(1);

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
        return a;
    }
    public List<Location> listAllLocation(String s) {

        establishConnection();
        String query = "select `Расположение товара`.`номер записи`," +
                "`Расположение товара`.номер_товара," +
                "`Расположение товара`.ряд,`Расположение товара`.стойка,`Расположение товара`.ярус," +
                "`Расположение товара`.количество " +
                "from заказ_клиента,Заказ_товар,товар,`Расположение товара` " +
                "WHERE заказ_клиента.статус = ? and заказ_клиента.`номер заказа`=" +
                "заказ_товар.номер_заказа " +
                "AND заказ_товар.номер_товара=товар.номер_товара " +
                "AND товар.номер_товара=`Расположение товара`.номер_товара GROUP BY " +
                "Заказ_товар.номер_товара,`Расположение товара`.`номер записи`";
        List<Location> AnimalList = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = this.connection.prepareStatement(query);
            stmt.setString(1, s);
            rs = stmt.executeQuery();
            while(rs.next()) {
                AnimalList.add(new Location(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getInt(5),
                        rs.getInt(6)
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
    public List<Location> listAllLocation2() {

        establishConnection();
        String query = "select `Расположение товара`.ряд,`Расположение товара`.стойка,`Расположение товара`.ярус " +
                "from `Расположение товара`";
        List<Location> AnimalList = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = this.connection.prepareStatement(query);
            rs = stmt.executeQuery();
            while(rs.next()) {
                AnimalList.add(new Location(
                        rs.getString(1),
                        rs.getInt(2),
                        rs.getInt(3)
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
    public List<Postavshik> showPostavshikInfo2() {

        establishConnection();
        String query = "select * from Поставщики";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Postavshik> a=new ArrayList<>();

        try {
            stmt = this.connection.prepareStatement(query);
            rs = stmt.executeQuery();

            while (rs.next()) {


                a.add(new Postavshik(
                        rs.getInt(4),
                        rs.getString(1),
                        rs.getString(3),
                        rs.getString(2))
               );
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
    public Postavshik showPostavshikInfo(int aNumber) {

        establishConnection();
        String query = "select * from Поставщики where `номер поставщика` = ? limit 1";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Postavshik a=null;

        try {
            stmt = this.connection.prepareStatement(query);
            stmt.setInt(1, aNumber);
            rs = stmt.executeQuery();

            if (rs.next()) {


                a = new Postavshik(
                        rs.getInt(4),
                        rs.getString(1),
                        rs.getString(3),
                        rs.getString(2)
                );
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
    public List<Location> LocationZakaz() {

        establishConnection();
        String query = "select ряд,стойка,ярус from Заказ_клиента";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Location> a=new ArrayList<>();;

        try {
            stmt = this.connection.prepareStatement(query);
            rs = stmt.executeQuery();
            while (rs.next()) {


                a.add( new Location(
                        rs.getString(1),
                        rs.getInt(2),
                        rs.getInt(3))


                );

                //System.out.println(String.format("%-19s %s", "1. Номер:", accountNumber));
                //System.out.println(String.format("%-19s %s", "2. Имя:", lastName));
                // System.out.println(String.format("%-19s %s", "3. Номер Вольера:", firstName));
                // System.out.println(String.format("%-19s %s", "4. Вид:", VidsID));
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
    public void UpdateLocationZakaz(int id, Location sum) {
        establishConnection();
        String query =
                "UPDATE  `Sklad`.`Заказ_клиента` SET `ряд`=?,`стойка`=?,`ярус`=? WHERE `номер заказа`=?";

        PreparedStatement stmt = null;
        ResultSet rs = null;


        try {
            stmt = this.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            stmt.setString(1, sum.getRjad());
            stmt.setInt(2, sum.getStojka());
            stmt.setInt(3, sum.getJarus());
            stmt.setInt(4,id);

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




