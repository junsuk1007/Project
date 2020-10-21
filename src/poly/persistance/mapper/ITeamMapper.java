package poly.persistance.mapper;

import config.Mapper;
import poly.dto.TeamDTO;

@Mapper("TeamMapper")
public interface ITeamMapper {

	int SelectedTeam(TeamDTO pDTO) throws Exception;

	String getMVPTeam() throws Exception;

}
