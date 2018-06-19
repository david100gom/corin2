/* 
    파일명: FileUploadService.java
    설명: 
    작성일: 2018. 6. 16.
    작성자: 전나영
*/
package site.corin2.board.service;

import java.util.LinkedList;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.View;

import site.corin2.board.dao.UploadDAO;
import site.corin2.board.dto.BoardDTO;
import site.corin2.board.dto.FileMeta;
import site.corin2.board.dto.UploadDTO;
@Service
public class UploadService {

	@Autowired
	private SqlSession sqlSession;
	
	public LinkedList<UploadDTO> uploadSelect(int projectNum) {
		UploadDAO dao = sqlSession.getMapper(UploadDAO.class);
		return dao.uploadSelect(projectNum);
	
		
	}

	public void uploadInsert(UploadDTO uploadDTO) {
		UploadDAO dao = sqlSession.getMapper(UploadDAO.class);
		dao.uploadInsert(uploadDTO);
	}

	public void boardInsert(BoardDTO boardDTO) {
		UploadDAO dao = sqlSession.getMapper(UploadDAO.class);
		dao.boardInsert(boardDTO);
		
	}

}
