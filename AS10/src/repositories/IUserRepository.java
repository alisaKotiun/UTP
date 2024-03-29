package repositories;

import dtos.UserDTO;

import java.util.List;

public interface IUserRepository extends IRepository<UserDTO> {

    List<UserDTO> findByName(String username);

    List<UserDTO> read();

    public String getRecordName(int id);
}
