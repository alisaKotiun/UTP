package repositories;

import dtos.DTOBase;
import exc.UnimplementedException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class Repository<T extends DTOBase> implements IRepository<T>{

    private Connection connection;

    public Repository(Connection c) throws SQLException {
        connection = c;
        connection.setAutoCommit(false);
    }

    @Override
    public Connection getConnection() {
        return connection;
    }

    @Override
    public void addOrUpdate(T dto) {
        if(exists(dto)){
            update(dto);
        } else{
            add(dto);
        }
    }

    @Override
    public abstract void delete(T dto);

    @Override
    public abstract T findById(int id);



    @Override
    public void beginTransaction() {
        try {
            Connection c = connection;
            c.setAutoCommit(false);
        } catch (SQLException e) {
            throw new UnimplementedException(e);
        }
    }

    @Override
    public void commitTransaction() {
        try {
            Connection c = connection;
            c.commit();
        } catch (SQLException e) {
            throw new UnimplementedException(e);
        }
    }

    @Override
    public void rollbackTransaction() {
        try {
            Connection c = connection;
            c.rollback();
        } catch (SQLException e) {
            throw new UnimplementedException(e);
        }
    }

    @Override
    public int getCount() {
        try {
            Connection c = connection;
            String count = "SELECT COUNT(1) FROM " + getTableName();
            PreparedStatement st = c.prepareStatement(count);
            ResultSet re = st.executeQuery();
            if(re.next()){
                return  re.getInt(1);
            } else{
                throw new UnimplementedException(new Exception("error"));
            }
        } catch (SQLException e) {
            throw new UnimplementedException(e);
        }
    }

    abstract String getTableName();

    int sequenceNextVal(){
        return getCount();
    }


    @Override
    public boolean exists(T dto) {
        if(dto.hasExistingId()) {
            T t = findById(dto.getId());
            return t != null;
        }
        return false;
    }
}
