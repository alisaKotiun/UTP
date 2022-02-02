package test;

import dtos.DTOBase;
import dtos.GroupDTO;
import dtos.UserDTO;
import exc.UnimplementedException;
import org.junit.After;
import org.junit.Before;
import repositories.IRepository;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;

public abstract class RepositoryTestBase<TDTO extends DTOBase, TRepository extends IRepository<TDTO>, CTRepository extends TRepository> {

    private static String URL = "jdbc:postgresql://localhost:5432/utp-10";
    private static String USERNAME = "postgres";
    private static String PASSWORD = "ilove2002";

    private static String tableGU = "users_groups";
    private static String columnIdGU = "user_group_id";
    private static String columnU = "user_id";
    private static String columnG = "group_id";

    private static Connection connection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

    private TRepository repository;
    private Constructor<CTRepository> constructor;

    public RepositoryTestBase(Class<CTRepository> c) throws NoSuchMethodException, SQLException, IllegalAccessException, InvocationTargetException, InstantiationException {
        constructor = c.getConstructor(Connection.class);
        constructor.setAccessible(true);
        repository = constructor.newInstance(connection());
    }

    public TRepository repository() {
        return repository;
    }

    @Before
    public void before() {
        TRepository rep = repository();
        if (rep != null) {
            rep.beginTransaction();
        }
    }

    @After
    public void after() {
        TRepository rep = repository();
        if (rep != null) {
            rep.rollbackTransaction();
            //rep.commitTransaction();
        }
    }

    public void Create(GroupDTO g, UserDTO u) throws Exception {
        int count = count();
        int i1 = g.getId();
        int i2 = u.getId();
        String st = "INSERT INTO " + tableGU + " (" + columnIdGU + ", " + columnU + ", " + columnG
                + ") VALUES ("+ count + ", " + i2 + ", " + i1 + ")";

        Connection connection = connection();
        PreparedStatement pSt = connection.prepareStatement(st);
        pSt.executeUpdate();
        u.addGroup(g);
        g.addUser(u);
    }

    int count(){
        try {
            String st = "SELECT COUNT(1) FROM " + tableGU;
            Connection connection = connection();
            PreparedStatement pSt = connection.prepareStatement(st);
            ResultSet re = pSt.executeQuery();
            if(re.next()) {
                return re.getInt(1);
            }
            return 0;
        } catch (SQLException e) {
            throw new UnimplementedException(e);
        }
    }
}
