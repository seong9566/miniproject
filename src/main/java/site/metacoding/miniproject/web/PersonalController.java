package site.metacoding.miniproject.web;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.RequiredArgsConstructor;
import site.metacoding.miniproject.domain.resumes.Resumes;
import site.metacoding.miniproject.service.personal.PersonalService;
import site.metacoding.miniproject.web.dto.request.InsertResumesDto;
import site.metacoding.miniproject.web.dto.request.PersonalUpdateDto;
import site.metacoding.miniproject.web.dto.request.UpdateResumesDto;
import site.metacoding.miniproject.web.dto.response.CompanyMainDto;
import site.metacoding.miniproject.web.dto.response.DetailResumesDto;
import site.metacoding.miniproject.web.dto.response.PersonalAddressDto;
import site.metacoding.miniproject.web.dto.response.PersonalFormDto;
import site.metacoding.miniproject.web.dto.response.PersonalInfoDto;
import site.metacoding.miniproject.web.dto.response.ResponseDto;
import site.metacoding.miniproject.web.dto.response.ResumesPagingDto;
import site.metacoding.miniproject.web.dto.response.SignedDto;

@RequiredArgsConstructor
@Controller
public class PersonalController {
	
	private final HttpSession session;
	private final PersonalService personalService;

	// 이력서 작성 하기
	@GetMapping("/personal/resumesForm")
	public String resumesForm(Model model) {				
		SignedDto<?> principal = (SignedDto<?>)session.getAttribute("principal");		
		PersonalInfoDto personalInfoPS = personalService.personalInfoById(principal.getPersonalId());			
		model.addAttribute("personalInfoPS", personalInfoPS);
		return "personal/resumesForm";
	}

	@PostMapping("/personal/resumes")
	public @ResponseBody ResponseDto<?> insertResumes(@RequestBody InsertResumesDto insertResumesDto) {
		SignedDto<?> principal = (SignedDto<?>)session.getAttribute("principal");		
		personalService.insertResumes(principal.getPersonalId(), insertResumesDto);
		return new ResponseDto<>(1, "이력서 등록 성공", null);
	}
	
	
	
	// 내가 작성한 이력서 목록 보기
	@GetMapping("/personal/myresumesList")
	public String myresumesList(Model model) {
		SignedDto<?> principal = (SignedDto<?>)session.getAttribute("principal");		
		List<Resumes> resumesList = personalService.myresumesAll(principal.getPersonalId());
		model.addAttribute("resumesList", resumesList);
		return "personal/myresumesList";
	}
	
	// 이력서 상세 보기
	@GetMapping("/personal/resumes/{resumesId}")
	public String resumesById(@PathVariable Integer resumesId, Model model) {			
		DetailResumesDto detailResumesDtoPS = personalService.resumesById(resumesId);
		model.addAttribute("detailResumesDtoPS", detailResumesDtoPS);
		return "personal/resumesDetail";
	}
	
	
	//"/personal/resumes/update"+ resumesId
	// 이력서 수정
	@GetMapping("/personal/resumes/update/{resumesId}")
	public String updateForm(@PathVariable Integer resumesId, Model model) {
		DetailResumesDto detailResumesDtoPS = personalService.resumesById(resumesId);
		model.addAttribute("detailResumesDtoPS", detailResumesDtoPS);
		return "personal/resumesUpdateForm";
	}
	
	@PutMapping("/personal/resumes/update/{resumesId}")
	public @ResponseBody ResponseDto<?> updateResumes(@PathVariable Integer resumesId, @RequestBody UpdateResumesDto updateResumesDto) {
		personalService.updateResumes(resumesId, updateResumesDto);			
		return new ResponseDto<>(1, "이력서 수정 성공", null);
	}
	
	// 이력서 삭제 하기
	@DeleteMapping("/personal/resumes/delete/{resumesId}")
	public @ResponseBody ResponseDto<?> deleteResumes(@PathVariable Integer resumesId){
		personalService.deleteResumes(resumesId);
		return new ResponseDto<>(1, "이력서 삭제 성공", null);
	}
	
	// 전체 이력서 목록 보기
	@GetMapping("/companymain")
	public String resumesList(Model model, Integer page, String keyword) {
		if(page==null) page=0;
		int startNum = page*5;
		
		System.out.println("===============");
		System.out.println("keyword : "+keyword);
		System.out.println("startNum : "+startNum );
		System.out.println("===============");
		
		if(keyword == null || keyword.isEmpty()) { 
			List<CompanyMainDto> resumesList = personalService.resumesAll(startNum);
			ResumesPagingDto paging = personalService.resumesPaging(page, null);

			paging.makeBlockInfo(keyword);

			model.addAttribute("resumesList", resumesList);	
			model.addAttribute("paging",paging);
			
		} else {
			List<CompanyMainDto> resumesList = personalService.findSearch(startNum, keyword);
			ResumesPagingDto paging = personalService.resumesPaging(page, keyword);
			paging.makeBlockInfo(keyword);
			model.addAttribute("resumesList", resumesList);
			model.addAttribute("paging",paging);
		}
		
		return "company/main";
	}
	
	// 내정보 보기
		@GetMapping("/personal/info")
		public String form(Model model) {
			SignedDto<?> principal = (SignedDto<?>) session.getAttribute("principal");
			PersonalFormDto personalformPS = personalService.personalformById(principal.getPersonalId());
			PersonalAddressDto personalAddressPS = personalService.personalAddress(principal.getPersonalId());
			model.addAttribute("personalAddress", personalAddressPS);
			model.addAttribute("personalform", personalformPS);
			return "personal/info";
		}

		@GetMapping("/personal/update")
		public String update(Model model) {
			SignedDto<?> principal = (SignedDto<?>) session.getAttribute("principal");
			PersonalUpdateDto personalUpdateFormPS = personalService.personalUpdateById(principal.getPersonalId());
			PersonalAddressDto personalAddressPS = personalService.personalAddress(principal.getPersonalId());
			model.addAttribute("personalAddress", personalAddressPS);
			model.addAttribute("personalUpdateForm", personalUpdateFormPS);
			return "personal/update";
		}
		
		@PutMapping("/personal/update")
		public @ResponseBody ResponseDto<?> personalUpdate(@RequestBody PersonalUpdateDto personalUpdateDto){
			SignedDto<?> principal =  (SignedDto)session.getAttribute("principal");
			personalService.updatePersonal(principal.getUsersId(), principal.getPersonalId(), personalUpdateDto);
			return new ResponseDto<>(1, "수정 성공", null);
		}
}