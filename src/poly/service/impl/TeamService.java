package poly.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import poly.dto.TeamDTO;
import poly.persistance.mapper.ITeamMapper;
import poly.service.ITeamService;

@Service("TeamService")
public class TeamService implements ITeamService {
	
	@Resource(name ="TeamMapper")
	private ITeamMapper teammapper;

	@Override
	public int SelectedTeam(TeamDTO pDTO) throws Exception {
		
		int res = 0;
		
		if (pDTO == null) {
			pDTO = new TeamDTO();
		}
		
		res = teammapper.SelectedTeam(pDTO);
				
		res = 1;
		
		
		return res;
	}

	@Override
	public String getMVPTeam() throws Exception {
		
		String MVPTeam = teammapper.getMVPTeam();
		
		return MVPTeam;
	}

}
