package com.publicdatabatchimporter.publicdatabatchimporter.model;

import java.math.BigDecimal;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@ToString
public class PublicData {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String serviceName;  // 개방서비스명
	private String serviceId;  // 개방서비스아이디
	private String municipalityCode;  // 개방자치단체코드
	private String managementNumber;  // 관리번호
	private Date licenseDate;  // 인허가일자
	private Date licenseCancellationDate;  // 인허가취소일자
	private String businessStatusCode;  // 영업상태구분코드
	private String businessStatusName;  // 영업상태명
	private String detailedBusinessStatusCode;  // 상세영업상태코드
	private String detailedBusinessStatusName;  // 상세영업상태명
	private Date closureDate;  // 폐업일자
	private Date suspensionStartDate;  // 휴업시작일자
	private Date suspensionEndDate;  // 휴업종료일자
	private Date reopeningDate;  // 재개업일자
	private String addressPhone;  // 소재지전화
	private BigDecimal addressArea;  // 소재지면적
	private String addressPostcode;  // 소재지우편번호
	private String fullAddress;  // 소재지전체주소
	private String roadNameFullAddress;  // 도로명전체주소
	private String roadNamePostcode;  // 도로명우편번호
	private String businessName;  // 사업장명
	private Date lastModified;  // 최종수정시점
	private String dataUpdateType;  // 데이터갱신구분
	private Date dataUpdateDate;  // 데이터갱신일자
	private String businessTypeName;  // 업태구분명
	private BigDecimal coordinateX;  // 좌표정보(x)
	private BigDecimal coordinateY;  // 좌표정보(y)
	private String sanitationBusinessTypeName;  // 위생업태명
	private int maleEmployeeCount;  // 남성종사자수
	private int femaleEmployeeCount;  // 여성종사자수
	private String businessAreaClassification;  // 영업장주변구분명
	private String gradeClassification;  // 등급구분명
	private String waterFacilityClassification;  // 급수시설구분명
	private int totalEmployeeCount;  // 총직원수
	private int headquartersEmployeeCount;  // 본사직원수
	private int factoryOfficeStaffCount;  // 공장사무직직원수
	private int factorySalesStaffCount;  // 공장판매직직원수
	private int factoryProductionStaffCount;  // 공장생산직직원수
	private String buildingOwnershipClassification;  // 건물소유구분명
	private BigDecimal guaranteeAmount;  // 보증액
	private BigDecimal monthlyRent;  // 월세액
	private String multiUseFacilityFlag;  // 다중이용업소여부
	private BigDecimal totalFacilitySize;  // 시설총규모
	private String traditionalBusinessDesignationNumber;  // 전통업소지정번호
	private String traditionalBusinessMainDish;  // 전통업소주된음식
	private String website;  // 홈페이지

}
