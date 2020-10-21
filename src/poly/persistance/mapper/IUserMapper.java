package poly.persistance.mapper;

import config.Mapper;
import poly.dto.UserDTO;

@Mapper("UserMapper")
public interface IUserMapper {

	int SelectUser(UserDTO uDTO) throws Exception;

	Integer CheckUser(UserDTO uDTO) throws Exception;

}
