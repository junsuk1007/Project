package poly.service;

import poly.dto.TeamDTO;

public interface ITeamService {

	int SelectedTeam(TeamDTO pDTO) throws Exception;

	String getMVPTeam() throws Exception;
	
	 

}
