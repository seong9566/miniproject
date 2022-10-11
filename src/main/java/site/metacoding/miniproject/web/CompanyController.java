package site.metacoding.miniproject.web;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.RequiredArgsConstructor;
import site.metacoding.miniproject.service.company.CompanyService;
import site.metacoding.miniproject.web.dto.request.JobPostingInsertDto;
import site.metacoding.miniproject.web.dto.request.JobPostingUpdateDto;
import site.metacoding.miniproject.web.dto.response.CompanyInfoDto;
import site.metacoding.miniproject.web.dto.response.CompanyJobPostingDto;
import site.metacoding.miniproject.web.dto.response.ResponseDto;

@Controller
@RequiredArgsConstructor
public class CompanyController {
	private final HttpSession session;
	private final CompanyService companyService;

	@GetMapping("/")
	public String mainForm() {
		return "/company/main";
	}
	
	//회사의 구인 공고 리스트 보기 
	@GetMapping("/company/jobpostingboardList")
	public String findAlljobPostingBoard(Model model) {
	List<CompanyJobPostingDto> jobPostingBoardPS =  companyService.findAllJobpostingBoard();
	model.addAttribute("jobPostingBoardList", jobPostingBoardPS);
	
	System.out.println("======================");
	for(int i = 0 ; i < jobPostingBoardPS.size() ;i++) {
		System.out.println(jobPostingBoardPS);
	}
	System.out.println("======================");
	
	return "company/companyJobPostingBoardList";
	}
	
	@GetMapping("/company/jobpostingboard/{jobPostingBoardId}")
	public CompanyJobPostingDto findJobPostingOne(@PathVariable Integer jobPostingBoardId){
		CompanyJobPostingDto jobPostingOne = companyService.findJobPostingBoardOne(jobPostingBoardId);
		return jobPostingOne;
		
	}
	
	
	@GetMapping("/company/jobpostingboard/insert")
	public String insertJobPostingBoardForm(Model model) {
		//1. 회사 정보 가져오기
		
		return "company/companyJobPostingBoardInsert";
	}
	@PostMapping("/jobpostingboard/insert")
	public @ResponseBody ResponseDto<?> insertJobPostingBoard(@RequestBody JobPostingInsertDto jobPostingInsertDto){
		companyService.insertJobPostingBoard(jobPostingInsertDto);
		return new ResponseDto<>(1,"등록 성공",null);
				
	}
	@PostMapping("/jobpostingboard/{jobPostingBoardId}/update")
	public @ResponseBody ResponseDto<?> updateJobPostingBoard(@RequestBody JobPostingUpdateDto jobPostingUpdateDto,@PathVariable Integer jobPostingBoardId){
		companyService.updateJobPostingBoard(jobPostingUpdateDto, jobPostingBoardId);
		return new ResponseDto<>(1,"수정성공",null);
	}
	@DeleteMapping("/jobpostingboard/{jobPostingBoardId}/delete")
	public @ResponseBody ResponseDto<?> deleteByJobPostingBoard(@PathVariable Integer jobPostingBoardId){
		companyService.deleteByJobPostingBoard(jobPostingBoardId);
		return new ResponseDto<>(1,"삭제 성공",null);
	}

	//채용공고 수정하기 
	@GetMapping("/company/jobpostingboard/{jobPostingBoardId}/updateForm")
	public String updateForm(@PathVariable Integer jobPostingBoardId) {
		return "company/companyJobPostingBoardUpdate";
	}
	
	//회사 정보 보기 
	@GetMapping("/company/{companyId}/inform")
	public String inform(Model model,@PathVariable Integer companyId) {
		CompanyInfoDto companyPS =  companyService.findCompanyInfo(companyId);
		model.addAttribute("companyInfo", companyPS);
		System.out.println(companyPS.getLoginId());
		System.out.println(companyPS.getCompanyAddress());
		System.out.println(companyPS.getCompanyEmail());
		System.out.println(companyPS.getCompanyName());
		System.out.println(companyPS.getCompanyPhoneNumber());
		System.out.println(companyPS.getCompanyPicture());
		return "company/inform";
	}
	//회사정보 수정
	@GetMapping("/company/inform/update")
	public String informUpdate() {
		return "company/update";
	}
	
}