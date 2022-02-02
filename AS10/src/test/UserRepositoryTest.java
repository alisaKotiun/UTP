package test;

import dtos.UserDTO;
import org.junit.Assert;
import org.junit.Test;
import repositories.IUserRepository;
import repositories.UserRepository;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

public final class UserRepositoryTest extends RepositoryTestBase<UserDTO, IUserRepository, UserRepository> {

    public UserRepositoryTest() throws NoSuchMethodException, SQLException, IllegalAccessException, InvocationTargetException, InstantiationException {
        super(UserRepository.class);
    }

    @Test
    public void add() {
        IUserRepository repository = repository();
        int num = repository.getCount();
        UserDTO u = new UserDTO(num, "u" + num, "password" + num);
        repository.add(u);
        int newNum = repository.getCount();
        Assert.assertEquals(num + 1, newNum);
    }

    @Test
    public void update() {
        IUserRepository repository = repository();
        int num = repository.getCount();
        UserDTO u = new UserDTO(num, "u" + num, "password" + num);
        repository.add(u);
        String newLogin = "uNEW";
        u.setLogin(newLogin);
        repository.update(u);
        String actLogin = repository.getRecordName(num);
        Assert.assertEquals(newLogin, actLogin);
    }

    @Test
    public void addOrUpdate() {
        IUserRepository repository = repository();
        int num = repository.getCount();
        UserDTO u = new UserDTO(num, "u" + num, "password" + num);
        repository.addOrUpdate(u);
        int newNum = repository.getCount();
        Assert.assertEquals(num + 1, newNum);
        u.setLogin("uNEW");
        repository.addOrUpdate(u);
        newNum = repository.getCount();
        Assert.assertEquals(num + 1, newNum);
    }

    @Test
    public void delete() {
        IUserRepository repository = repository();
        int num = repository.getCount();
        UserDTO u = new UserDTO(num, "u" + num, "password" + num);
        repository.add(u);
        int newNum = repository.getCount();
        Assert.assertEquals(num + 1, newNum);
        repository.delete(u);
        newNum = repository.getCount();
        Assert.assertEquals(num, newNum);
    }

    @Test
    public void findById() {
        IUserRepository repository = repository();
        int num = repository.getCount();
        UserDTO u = new UserDTO(num, "u" + num, "password" + num);
        repository.add(u);
        UserDTO uFound = repository.findById(u.getId());
        Assert.assertEquals(u.getId(), uFound.getId());
        Assert.assertEquals(u.getLogin(), uFound.getLogin());
        Assert.assertEquals(u.getPassword(), uFound.getPassword());
    }

    @Test
    public void findByName() {
        IUserRepository repository = repository();
        int num = repository.getCount();
        UserDTO u = new UserDTO(num, "u" + num, "password" + num);
        repository.add(u);
        String login = u.getLogin();
        List<UserDTO> listFound = repository.findByName(login);
        Assert.assertEquals(1, listFound.size());
        UserDTO u1 = new UserDTO(num, login, "password" + num+1);
        repository.add(u1);
        listFound = repository.findByName(login);
        Assert.assertEquals(2, listFound.size());
    }

    @Test
    public void read() {
        IUserRepository repository = repository();
        int num = repository.getCount();
        UserDTO u = new UserDTO(num, "u" + num, "password" + num);
        UserDTO u1 = new UserDTO(num+1, "u" + (num+1), "password" + (num+1));
        repository.add(u);
        repository.add(u1);
        List<UserDTO> listFound = repository.read();
        Assert.assertTrue(listFound.size() > 0);
    }

}
