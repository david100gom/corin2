/**
    파일명: TeamService.java
    설   명: 
    작성일: 2018. 6. 6.
    작성자: 김 진 원
*/

package site.corin2.project.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import site.corin2.project.dao.MsgDAO;
import site.corin2.project.dao.TeamDAO;
import site.corin2.project.dto.ProjectDTO;
import site.corin2.project.dto.TeamDTO;
import site.corin2.user.dto.UserDTO;

@Service
public class TeamService {
	
	@Autowired
	private SqlSession sqlSession;
	
	//해당 프로젝트에 모든 멤버 조회
	public List<TeamDTO> allTeamMemberSelect(int projectNum){
		TeamDAO teamDAO = sqlSession.getMapper(TeamDAO.class);
		List<TeamDTO> teamMembers = null;
		try {
			teamMembers = (ArrayList<TeamDTO>)teamDAO.allTeamMemberSelect(projectNum);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return teamMembers;
	}
	
	//해당 프로젝트의 모든 멤버의 유저DTO 조회
	public List<UserDTO> allTeamMemberProfileSelect(int projectNum){
		TeamDAO teamDAO = sqlSession.getMapper(TeamDAO.class);
		List<UserDTO> teamMemberProfiles = null;
		try {
			teamMemberProfiles = (ArrayList<UserDTO>)teamDAO.allTeamMemberProfileSelect(projectNum);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return teamMemberProfiles;
	}
	
	//송신아이디가 받은 모든 초대 메시지의 projectDTO를 조회한다.
	public List<ProjectDTO> allInviteMsgSelect(String receptionId){
		MsgDAO msgDAO = sqlSession.getMapper(MsgDAO.class);
		List<ProjectDTO> inviteMsgs = null;
		try {
			inviteMsgs = (ArrayList<ProjectDTO>)msgDAO.allInviteMsgSelect(receptionId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return inviteMsgs;
	}
}
