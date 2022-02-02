package test;

import dtos.GroupDTO;
import org.junit.Assert;
import org.junit.Test;
import repositories.GroupRepository;
import repositories.IGroupRepository;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

public class GroupRepositoryTest extends RepositoryTestBase<GroupDTO, IGroupRepository, GroupRepository> {

    public GroupRepositoryTest() throws NoSuchMethodException, SQLException, IllegalAccessException, InvocationTargetException, InstantiationException {
        super(GroupRepository.class);
    }

    @Test
    public void add() {
        IGroupRepository repository = repository();
        int num = repository.getCount();
        GroupDTO g = new GroupDTO(num, "gr" + num, "desc" + num);
        repository.add(g);
        int newNum = repository.getCount();
        Assert.assertEquals(num+1, newNum);
    }

    @Test
    public void update() {
        IGroupRepository repository = repository();
        int num = repository.getCount();
        GroupDTO g = new GroupDTO(num, "gr" + num, "desc" + num);
        repository.add(g);
        String newName = "grNEW";
        g.setName(newName);
        repository.update(g);
        String actName = repository.getRecordName(num);
        Assert.assertEquals(newName, actName);

    }

    @Test
    public void addOrUpdate() {
        IGroupRepository repository = repository();
        int num = repository.getCount();
        GroupDTO g = new GroupDTO(num, "gr" + num, "desc" + num);
        repository.addOrUpdate(g);
        int acNum = repository.getCount();
        Assert.assertEquals(num + 1, acNum);
        g.setName("new");
        repository.addOrUpdate(g);
        acNum = repository.getCount();
        Assert.assertEquals(num + 1, acNum);
    }

    @Test
    public void delete() {
        IGroupRepository repository = repository();
        int num = repository.getCount();
        GroupDTO g = new GroupDTO(num, "gr" + num, "desc" + num);
        repository.add(g);
        int newNum = repository.getCount();
        Assert.assertEquals(num+1, newNum);
        repository.delete(g);
        newNum = repository.getCount();
        Assert.assertEquals(num, newNum);
    }

    @Test
    public void findById() {
        IGroupRepository repository = repository();
        int num = repository.getCount();
        GroupDTO g = new GroupDTO(num, "gr" + num, "desc" + num);
        repository.add(g);
        GroupDTO gFound = repository.findById(g.getId());
        Assert.assertEquals(g.getId(), gFound.getId());
        Assert.assertEquals(g.getName(), gFound.getName());
        Assert.assertEquals(g.getDescription(), gFound.getDescription());
    }

    @Test
    public void findByName() {
        IGroupRepository repository = repository();
        int num = repository.getCount();
        GroupDTO g = new GroupDTO(num, "gr" + num, "desc" + num);
        repository.add(g);
        String name = g.getName();
        List<GroupDTO> listFound = repository.findByName(name);
        Assert.assertEquals(1, listFound.size());
        GroupDTO g1 = new GroupDTO(0, name, "desc" + num);
        repository.add(g1);
        listFound = repository.findByName(name);
        Assert.assertEquals(2, listFound.size());
    }

    @Test
    public void read() {
        IGroupRepository repository = repository();
        int num = repository.getCount();
        GroupDTO g = new GroupDTO(num, "gr" + num, "desc" + num);
        GroupDTO g1 = new GroupDTO(num+1, "gr" + (num+1), "desc" + (num+1));
        repository.add(g);
        repository.add(g1);
        List<GroupDTO> listFound = repository.read();
        Assert.assertTrue(listFound.size() > 0);
    }
}
