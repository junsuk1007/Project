package poly.service;

import poly.dto.UserDTO;

public interface IUserService {

	int SelectUser(UserDTO uDTO) throws Exception;

	Integer CheckUser(UserDTO uDTO) throws Exception;
}
