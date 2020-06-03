package poly.persistance.mapper;

import config.Mapper;
import poly.dto.AdminDTO;

@Mapper("AdminMapper")
public interface IAdminMapper {

	int getAdminExists(AdminDTO pDTO) throws Exception;

}
