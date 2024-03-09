package repository;

import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {

    public static List<User> getAllUsers(){
        String sqlCommand = "select * from users\n" +
                "order by user_id asc;";
        List<User> userList = new ArrayList<>();
        PropertiesLoader.loadPropertiesFile();
        try(Connection con = DriverManager.getConnection(
                PropertiesLoader.properties.getProperty("database_url"),
                PropertiesLoader.properties.getProperty("database_username"),
                PropertiesLoader.properties.getProperty("database_password")
        );
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlCommand);
        ){
            while(resultSet.next()) {
                userList.add(new User(
                        resultSet.getInt("user_id"),
                        resultSet.getString("user_uuid"),
                        resultSet.getString("user_name"),
                        resultSet.getString("user_email"),
                        resultSet.getString("user_password"),
                        resultSet.getBoolean("is_deleted"),
                        resultSet.getBoolean("is_verified")
                ));
            }

        }catch (SQLException sqlError){
            System.out.println(sqlError.getMessage());
        }
        return userList;
    }

    public static void insertUser(User user) {
        String sqlCommand = "INSERT INTO users (user_uuid, user_name, user_email, user_password, is_deleted, is_verified) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        PropertiesLoader.loadPropertiesFile();
        try (Connection con = DriverManager.getConnection(
                PropertiesLoader.properties.getProperty("database_url"),
                PropertiesLoader.properties.getProperty("database_username"),
                PropertiesLoader.properties.getProperty("database_password"));
             PreparedStatement preparedStatement = con.prepareStatement(sqlCommand, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, user.getUserUUID());
            preparedStatement.setString(2, user.getUserName());
            preparedStatement.setString(3, user.getUserEmail());
            preparedStatement.setString(4, user.getUserPassword());
            preparedStatement.setBoolean(5, user.getIsDeleted());
            preparedStatement.setBoolean(6, user.getIsVerified());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        user.setUserID(generatedKeys.getInt(1));
                    } else {
                        throw new SQLException("Failed to retrieve auto-generated key.");
                    }
                }
            }
        } catch (SQLException sqlError) {
            System.out.println(sqlError.getMessage());
        }
    }

    public static boolean deleteUser(Integer userId) {
        String sqlCommand = "DELETE FROM users WHERE user_id = ?";
        PropertiesLoader.loadPropertiesFile();
        try (Connection con = DriverManager.getConnection(
                PropertiesLoader.properties.getProperty("database_url"),
                PropertiesLoader.properties.getProperty("database_username"),
                PropertiesLoader.properties.getProperty("database_password"));
             PreparedStatement preparedStatement = con.prepareStatement(sqlCommand)) {

            preparedStatement.setInt(1, userId);

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException sqlError) {
            System.out.println(sqlError.getMessage());
            return false;
        }
    }

    public static boolean updateUser(User user) {
        String sqlCommand = "UPDATE users SET user_name = ?, user_email = ?, user_password = ?, is_deleted = ?, is_verified = ? WHERE user_id = ?";
        PropertiesLoader.loadPropertiesFile();
        try(Connection con = DriverManager.getConnection(
                PropertiesLoader.properties.getProperty("database_url"),
                PropertiesLoader.properties.getProperty("database_username"),
                PropertiesLoader.properties.getProperty("database_password"));
            PreparedStatement preparedStatement = con.prepareStatement(sqlCommand)) {

            preparedStatement.setString(1, user.getUserName());
            preparedStatement.setString(2, user.getUserEmail());
            preparedStatement.setString(3, user.getUserPassword());
            preparedStatement.setBoolean(4, user.getIsDeleted());
            preparedStatement.setBoolean(5, user.getIsVerified());
            preparedStatement.setInt(6, user.getUserID());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException sqlError) {
            System.out.println(sqlError.getMessage());
            return false;
        }
    }


    public static User searchUserByID(Integer id){
        String sql = "SELECT *FROM users WHERE user_id = ?";
        PropertiesLoader.loadPropertiesFile();
        try(Connection connection = DriverManager.getConnection(
                        PropertiesLoader.properties.getProperty("database_url"),
                        PropertiesLoader.properties.getProperty("database_username"),
                        PropertiesLoader.properties.getProperty("database_password")
                );
                Statement statement = connection.createStatement();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
        )
        {
            preparedStatement.setInt(1,id);
            try(ResultSet resultSet = preparedStatement.executeQuery();){
                while (resultSet.next()){
                    return new User(
                            resultSet.getInt("user_id"),
                            resultSet.getString("user_uuid"),
                            resultSet.getString("user_name"),
                            resultSet.getString("user_email"),
                            resultSet.getString("user_password"),
                            resultSet.getBoolean("is_deleted"),
                            resultSet.getBoolean("is_verified")
                    );
                }
            }

        }catch (Exception exception){
            System.out.println(exception.getMessage());
        }
        return new User();
    }
}
