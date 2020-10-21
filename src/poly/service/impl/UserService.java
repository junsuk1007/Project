package poly.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import poly.dto.UserDTO;
import poly.persistance.mapper.IUserMapper;
import poly.service.IUserService;

@Service("UserService")
public class UserService implements IUserService {
	
	@Resource(name = "UserMapper")
	private IUserMapper usermapper;

	@Override
	public int SelectUser(UserDTO uDTO) throws Exception {
		
		int res2 = 0;
		
		res2 = usermapper.SelectUser(uDTO);
		
		res2 = 1;
				
		return res2;		
		
	}

	@Override
	public Integer CheckUser(UserDTO uDTO) throws Exception {
		
		Integer check_user = 0;
		
		check_user = usermapper.CheckUser(uDTO);
		
		if(check_user == null) {
			
			usermapper.SelectUser(uDTO);
			
			check_user = 1;
		}else {
			check_user = 2;
		}
		
		
		return check_user;
	}

}
