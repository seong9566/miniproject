package site.metacoding.miniproject.domain.resumes;

import java.util.List;

import site.metacoding.miniproject.web.dto.response.CompanyMainDto;

public interface ResumesDao {
	public void insert(Resumes resumes);
	public List<CompanyMainDto> findAll();
	public Resumes findById(Integer id);
	public void update(Resumes resumes);
	public void deleteById(Integer id);
	
	// 검색 결과에 따른 이력서 목록 보기
	public List<CompanyMainDto> findResumesBoard();
}
