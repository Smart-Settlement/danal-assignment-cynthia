package com.publicdatabatchimporter.publicdatabatchimporter.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PUBLIC) // 기본 생성자 공개
@Entity
@Table(name = "public_data")
public class PublicData {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "service_name")
	private String serviceName; // 개방서비스명

	@Column(name = "service_id")
	private String serviceId; // 개방서비스아이디

	@Column(name = "municipality_code")
	private String municipalityCode; // 개방자치단체코드

	@Column(name = "management_number")
	private String managementNumber; // 관리번호

	@Column(name = "license_date")
	private String licenseDate; // 인허가일자

	@Column(name = "license_cancellation_date")
	private String licenseCancellationDate; // 인허가취소일자

	@Column(name = "business_status_code")
	private String businessStatusCode; // 영업상태구분코드

	@Column(name = "business_status_name")
	private String businessStatusName; // 영업상태명

	@Column(name = "detailed_business_status_code")
	private String detailedBusinessStatusCode; // 상세영업상태코드

	@Column(name = "detailed_business_status_name")
	private String detailedBusinessStatusName; // 상세영업상태명

	@Column(name = "closure_date")
	private String closureDate; // 폐업일자

	@Column(name = "suspension_start_date")
	private String suspensionStartDate; // 휴업시작일자

	@Column(name = "suspension_end_date")
	private String suspensionEndDate; // 휴업종료일자

	@Column(name = "reopening_date")
	private String reopeningDate; // 재개업일자

	@Column(name = "address_phone")
	private String addressPhone; // 소재지전화

	@Column(name = "address_area")
	private BigDecimal addressArea; // 소재지면적

	@Column(name = "address_postcode")
	private String addressPostcode; // 소재지우편번호

	@Column(name = "full_address")
	private String fullAddress; // 소재지전체주소

	@Column(name = "road_name_full_address")
	private String roadNameFullAddress; // 도로명전체주소

	@Column(name = "road_name_postcode")
	private String roadNamePostcode; // 도로명우편번호

	@Column(name = "business_name")
	private String businessName; // 사업장명

	@Column(name = "last_modified")
	private String lastModified; // 최종수정시점

	@Column(name = "data_update_type")
	private String dataUpdateType; // 데이터갱신구분

	@Column(name = "data_update_date")
	private String dataUpdateDate; // 데이터갱신일자

	@Column(name = "business_type_name")
	private String businessTypeName; // 업태구분명

	@Column(name = "coordinate_x")
	private String coordinateX; // 좌표정보(x)

	@Column(name = "coordinate_y")
	private String coordinateY; // 좌표정보(y)

	@Column(name = "sanitation_business_type_name")
	private String sanitationBusinessTypeName; // 위생업태명

	@Column(name = "male_employee_count")
	private Integer maleEmployeeCount; // 남성종사자수

	@Column(name = "female_employee_count")
	private Integer femaleEmployeeCount; // 여성종사자수

	@Column(name = "business_area_classification")
	private String businessAreaClassification; // 영업장주변구분명

	@Column(name = "grade_classification")
	private String gradeClassification; // 등급구분명

	@Column(name = "water_facility_classification")
	private String waterFacilityClassification; // 급수시설구분명

	@Column(name = "total_employee_count")
	private Integer totalEmployeeCount; // 총직원수

	@Column(name = "headquarters_employee_count")
	private Integer headquartersEmployeeCount; // 본사직원수

	@Column(name = "factory_office_staff_count")
	private Integer factoryOfficeStaffCount; // 공장사무직직원수

	@Column(name = "factory_sales_staff_count")
	private Integer factorySalesStaffCount; // 공장판매직직원수

	@Column(name = "factory_production_staff_count")
	private Integer factoryProductionStaffCount; // 공장생산직직원수

	@Column(name = "building_ownership_classification")
	private String buildingOwnershipClassification; // 건물소유구분명

	@Column(name = "guarantee_amount")
	private BigDecimal guaranteeAmount; // 보증액

	@Column(name = "monthly_rent")
	private BigDecimal monthlyRent; // 월세액

	@Column(name = "multi_use_facility_flag")
	private String multiUseFacilityFlag; // 다중이용업소여부

	@Column(name = "total_facility_size")
	private BigDecimal totalFacilitySize; // 시설총규모

	@Column(name = "traditional_business_designation_number")
	private String traditionalBusinessDesignationNumber; // 전통업소지정번호

	@Column(name = "traditional_business_main_dish")
	private String traditionalBusinessMainDish; // 전통업소주된음식

	@Column(name = "website")
	private String website; // 홈페이지
}
