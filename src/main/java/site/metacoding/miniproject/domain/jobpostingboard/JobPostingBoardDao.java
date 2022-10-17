package site.metacoding.miniproject.domain.jobpostingboard;

import java.util.List;

import site.metacoding.miniproject.web.dto.response.JobPostingBoardDetailDto;
import site.metacoding.miniproject.web.dto.response.JobPostingBoardListDto;

public interface JobPostingBoardDao {
	public void insert(JobPostingBoard jobPostingBoard);
	public List<JobPostingBoard> findAll();
	public JobPostingBoardDetailDto findById(Integer jobPostingBoardId);
	public void update(JobPostingBoard jobPostingBoard);
	public void deleteById(Integer jobPostingBoardId);
	
	//채용공고 목록 보기
	public List<JobPostingBoardListDto> jobPostingBoardList(Integer companyId);
	//채용공고 자세히 보기 
}
