package sample;
import java.sql.*;

public class DatabaseHandler {
    Connection dbConnection = null;

    public Connection getDbConnection() throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mysql://localhost:3306/laba2?serverTimezone=Europe/Moscow&useSSL=false";
        Class.forName("com.mysql.cj.jdbc.Driver");
        dbConnection = DriverManager.getConnection(connectionString, "root", "12345");
        return dbConnection;
    }


    public void add(String name, String surname, String street, String number) {
        String insert = "INSERT INTO adres (name, surname, street, number)VALUES(?,?,?,?)";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);

            prSt.setString(1, name);
            prSt.setString(2, surname);
            prSt.setString(3, street);
            prSt.setString(4, number);

            prSt.executeUpdate();
            getDbConnection().close();
        } catch (SQLException |
                ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public ResultSet getAdres() {
        ResultSet resultSet = null;
        String select = "SELECT * FROM adres";

        try {
            Statement statement = getDbConnection().createStatement();
            resultSet = statement.executeQuery(select);
            getDbConnection().close();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public void delete(int id) throws SQLException, ClassNotFoundException {
        String delete = "DELETE FROM adres WHERE id =?";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(delete);

            prSt.setInt(1, id);

            prSt.executeUpdate();
            getDbConnection().close();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void update( String name, String surname, String street, String number,int id){
        String update = "UPDATE adres SET name = ?, surname = ?, street = ?, number = ? WHERE id =?";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(update);

            prSt.setString(1, name);
            prSt.setString(2, surname);
            prSt.setString(3, street);
            prSt.setString(4, number);
            prSt.setInt(5, id);


            prSt.executeUpdate();
            getDbConnection().close();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

/*
    public ResultSet getProduct(String table, int i){
        ResultSet resultSet = null;
        String select;
        if(i == 0)
            select = "SELECT * FROM "+ table;
        else
            select = "SELECT * FROM soldout WHERE month(date) = " + i;
        try {
            Statement statement = getDbConnection().createStatement();
            resultSet = statement.executeQuery(select);
            getDbConnection().close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public void deleteProduct(Products product) throws SQLException, ClassNotFoundException {
        String delete = "DELETE FROM products WHERE code =?";
        String update = "UPDATE products SET amount = ? WHERE code =?";

        if(product.getAmount()>1){
            PreparedStatement prSt = getDbConnection().prepareStatement(update);

            prSt.setInt(1, product.getAmount()-1);
            prSt.setString(2, product.getCode());

            prSt.executeUpdate();
        }else {
            try {
                PreparedStatement prSt = getDbConnection().prepareStatement(delete);

                prSt.setString(1, product.getCode());

                prSt.executeUpdate();
                getDbConnection().close();

            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

    }

    public void insertNewProduct(Products product){
        int i = addNewProdCheck(product.getCode());
        System.out.println(i);
        if(i>=1){
            String update = "UPDATE products SET amount = amount + ? WHERE code = ?";
            try {
                PreparedStatement prSt = getDbConnection().prepareStatement(update);
                prSt.setInt(1, product.getAmount());
                prSt.setString(2, product.getCode());
                prSt.executeUpdate();
                getDbConnection().close();
            } catch (SQLException |
                    ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        else {
            String insert = "INSERT INTO products (code, name, price, size, description, amount)VALUES(?,?,?,?,?,?)";
            try {
                PreparedStatement prSt = getDbConnection().prepareStatement(insert);
                prSt.setString(1, product.getCode());
                prSt.setString(2, product.getName());
                prSt.setInt(3, product.getPrice());
                prSt.setString(4, product.getSize());
                prSt.setString(5, product.getDeskr());
                prSt.setInt(6, product.getAmount());
                prSt.executeUpdate();
                getDbConnection().close();
            } catch (SQLException |
                    ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public void deleteUser(Users user) throws SQLException, ClassNotFoundException {
        String delete = "DELETE FROM users WHERE username =?";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(delete);

            prSt.setString(1, user.getUsername());

            prSt.executeUpdate();
            getDbConnection().close();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }


 */

