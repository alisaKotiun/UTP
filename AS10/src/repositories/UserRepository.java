package repositories;

import dtos.GroupDTO;
import dtos.UserDTO;
import exc.UnimplementedException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserRepository extends Repository<UserDTO> implements IUserRepository{

    private static String tableU = "users";
    private static String columnIdU = "user_id";
    private static String columnLoginU = "user_login";
    private static String columnPasswordU = "user_password";


    public UserRepository(Connection c) throws SQLException {
        super(c);
    }

    @Override
    public List<UserDTO> findByName(String username) {
        try {
            List<UserDTO> list = new ArrayList<>();
            Connection connection = getConnection();
            String st;
            if(username.contains("%")) {
                st = "SELECT * FROM " + tableU + " WHERE " + columnLoginU + " LIKE ?";
            }
            else{
                st = "SELECT * FROM " + tableU + " WHERE " + columnLoginU + " = ?";
            }
            PreparedStatement pSt = connection.prepareStatement(st);
            pSt.setString(1, username);
            ResultSet resultSet = pSt.executeQuery();
            while (resultSet.next()){
                int uId = resultSet.getInt(1);
                String uLogin = resultSet.getString(2);
                String uPassword = resultSet.getString(3);
                list.add(new UserDTO(uId, uLogin, uPassword));
            }
            return list;
        } catch (SQLException e) {
            throw new UnimplementedException(e);
        }
    }

    @Override
    public List<UserDTO> read() {
        try {
            List<UserDTO> list = new ArrayList<>();
            Connection connection = getConnection();
            String st = "SELECT * FROM " + tableU;
            PreparedStatement pSt = connection.prepareStatement(st);
            ResultSet resultSet = pSt.executeQuery();
            while (resultSet.next()){
                int uId = resultSet.getInt(1);
                String uLogin = resultSet.getString(2);
                String uPassword = resultSet.getString(3);
                list.add(new UserDTO(uId, uLogin, uPassword));
            }
            return list;
        } catch (SQLException e) {
            throw new UnimplementedException(e);
        }
    }

    @Override
    String getTableName() {
        return tableU;
    }

    @Override
    public void add(UserDTO dto) {
        try {
            Connection connection = getConnection();
            String st = "INSERT INTO " + tableU + " (" + columnIdU+ ", " + columnLoginU + ", " + columnPasswordU
                    + ") VALUES (" + sequenceNextVal() + ", ?, ?)";
            PreparedStatement psT = connection.prepareStatement(st);
            psT.setString(1, dto.getLogin());
            psT.setString(2, dto.getPassword());
            psT.executeUpdate();
        } catch (SQLException e) {
            throw new UnimplementedException(e);
        }
    }

    @Override
    public void update(UserDTO dto) {
        try {
            Connection connection = getConnection();
            String st = "UPDATE " + tableU + " SET " + columnLoginU + " = ?, "
                    + columnPasswordU + " = ? WHERE " + columnIdU + " = ?";
            PreparedStatement psT = connection.prepareStatement(st);
            psT.setString(1, dto.getLogin());
            psT.setString(2, dto.getPassword());
            psT.setInt(3, dto.getId());
            psT.executeUpdate();
        } catch (SQLException e) {
            throw new UnimplementedException(e);
        }
    }

    @Override
    public void delete(UserDTO dto) {
        try{
            Connection connection = getConnection();
            String st = "DELETE FROM " + tableU + " WHERE " + columnIdU + " = ?";
            PreparedStatement psT = connection.prepareStatement(st);
            psT.setInt(1, dto.getId());
            psT.executeUpdate();
        } catch (SQLException e) {
            throw new UnimplementedException(e);
        }
    }

    @Override
    public UserDTO findById(int id) {
        try {
            Connection connection = getConnection();
            String st = "SELECT * FROM " + tableU + " WHERE " + columnIdU + " = ?";
            PreparedStatement pSt = connection.prepareStatement(st);
            pSt.setInt(1, id);
            ResultSet resultSet = pSt.executeQuery();
            while (resultSet.next()){
                int uId = resultSet.getInt(1);
                String uLogin = resultSet.getString(2);
                String uPassword = resultSet.getString(3);
                return new UserDTO(uId, uLogin, uPassword);
            }
            return null;
        } catch (SQLException e) {
            throw new UnimplementedException(e);
        }
    }

    @Override
    public String getRecordName(int id) {
        UserDTO u = findById(id);
        if(u != null) return u.getLogin();
        return null;
    }
}
