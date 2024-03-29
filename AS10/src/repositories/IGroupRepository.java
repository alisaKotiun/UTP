package repositories;

import dtos.GroupDTO;

import java.util.List;

public interface IGroupRepository extends IRepository<GroupDTO> {

    List<GroupDTO> findByName(String name);

    List<GroupDTO> read();

    public String getRecordName(int id);
}
