package poly.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import poly.dto.AdminDTO;
import poly.persistance.mapper.IAdminMapper;
import poly.service.IAdminService;

@Service("AdminService")
public class AdminService implements IAdminService {
	
	@Resource(name = "AdminMapper")
	private IAdminMapper adminmapper;

	@Override
	public int AdminCheck(AdminDTO pDTO) throws Exception {
		
		int res = 0;
		
		if (pDTO == null) {
			pDTO = new AdminDTO();
		}
		
		int success = adminmapper.getAdminExists(pDTO);
		
		if( success > 0) {
			res = 1;
		}else {
			res = 0;
		}
		
		
		
		return res;
	}
	
	

}
