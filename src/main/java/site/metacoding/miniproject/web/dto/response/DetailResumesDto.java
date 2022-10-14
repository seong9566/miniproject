package site.metacoding.miniproject.web.dto.response;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DetailResumesDto {
	private String personalName;
	private String personalEmail;
	private String personalPhoneNumber;
	private String resumesTitle;
	private String resumesPicture;
	private Boolean oneYearLess;
	private Boolean twoYearOver;
	private Boolean threeYearOver;
	private Boolean fiveYearOver;
	private String portfolidSource;
	private String portfolioFile;
	private String resumesIntroduce;	
	
	// 수정 시 resumesId 얻기
	private Integer resumesId;
}
