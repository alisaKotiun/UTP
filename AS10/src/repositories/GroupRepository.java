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

public class GroupRepository extends Repository<GroupDTO> implements IGroupRepository{
    private static String tableG = "groups";
    private static String columnIdG = "group_id";
    private static String columnNameG = "group_name";
    private static String columnDescG = "group_description";

    public GroupRepository(Connection connection) throws SQLException {
        super(connection);
    }

    @Override
    public List<GroupDTO> findByName(String name) {
        try {
            List<GroupDTO> list = new ArrayList<>();
            Connection connection = getConnection();
            String st;
            if(name.contains("%")) {
                st = "SELECT * FROM " + tableG + " WHERE " + columnNameG + " LIKE ?";
            }
            else{
                st = "SELECT * FROM " + tableG + " WHERE " + columnNameG + " = ?";
            }
            PreparedStatement pSt = connection.prepareStatement(st);
            pSt.setString(1, name);
            ResultSet resultSet = pSt.executeQuery();
            while (resultSet.next()){
                int gId = resultSet.getInt(1);
                String gName = resultSet.getString(2);
                String gDesc = resultSet.getString(3);
                list.add(new GroupDTO(gId, gName, gDesc));
            }
            return list;
        } catch (SQLException e) {
            throw new UnimplementedException(e);
        }
    }

    @Override
    public List<GroupDTO> read() {
        try {
            List<GroupDTO> list = new ArrayList<>();
            Connection connection = getConnection();
            String st = "SELECT * FROM " + tableG;
            PreparedStatement pSt = connection.prepareStatement(st);
            ResultSet resultSet = pSt.executeQuery();
            while (resultSet.next()){
                int gId = resultSet.getInt(1);
                String gName = resultSet.getString(2);
                String gDesc = resultSet.getString(3);
                list.add(new GroupDTO(gId, gName, gDesc));
            }
            return list;
        } catch (SQLException e) {
            throw new UnimplementedException(e);
        }
    }

    @Override
    public void add(GroupDTO dto) {
        try {
        Connection connection = getConnection();
        int i = sequenceNextVal();
        String st = "INSERT INTO " + tableG + " (" + columnIdG+ ", " + columnNameG + ", " + columnDescG
                + ") VALUES (" + i + ", ?, ?)";
        dto.setId(i);
            PreparedStatement psT = connection.prepareStatement(st);
            psT.setString(1, dto.getName());
            psT.setString(2,  dto.getDescription());
            psT.executeUpdate();
        } catch (SQLException e) {
            throw new UnimplementedException(e);
        }
    }

    @Override
    public void update(GroupDTO dto) {
        try {
            Connection connection = getConnection();
            String st = "UPDATE " + tableG + " SET " + columnNameG + " = ?, "
                    + columnDescG + " = ? WHERE " + columnIdG + " = ?";
            PreparedStatement psT = connection.prepareStatement(st);
            psT.setString(1, dto.getName());
            psT.setString(2, dto.getDescription());
            psT.setInt(3, dto.getId());
            psT.executeUpdate();
        } catch (SQLException e) {
            throw new UnimplementedException(e);
        }
    }
    @Override
    public void delete(GroupDTO dto) {
        try {
            Connection connection = getConnection();
            String st = "DELETE FROM " + tableG + " WHERE " + columnIdG + " = ?";
            PreparedStatement psT = connection.prepareStatement(st);
            psT.setInt(1, dto.getId());
            psT.executeUpdate();
        } catch (SQLException e) {
            throw new UnimplementedException(e);
        }
    }

    @Override
    public GroupDTO findById(int id) {
        try {
            Connection connection = getConnection();
            GroupDTO group = null;
            String st = "SELECT * FROM " + tableG + " WHERE " + columnIdG + " = ?";
            PreparedStatement pSt = connection.prepareStatement(st);
            pSt.setInt(1, id);
            ResultSet resultSet = pSt.executeQuery();
            while (resultSet.next()){
                int gId = resultSet.getInt(1);
                String gName = resultSet.getString(2);
                String gDesc = resultSet.getString(3);
                group = new GroupDTO(gId, gName, gDesc);
            }
            return group;
        } catch (SQLException e) {
            throw new UnimplementedException(e);
        }
    }

    @Override
    String getTableName() {
        return tableG;
    }

    @Override
    public String getRecordName(int id) {
            GroupDTO g = findById(id);
            if (g != null) return g.getName();
            return null;
    }
}
