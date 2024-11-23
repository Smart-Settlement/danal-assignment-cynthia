package com.publicdatabatchimporter.publicdatabatchimporter.utils;

import java.util.LinkedHashMap;
import java.util.Map;

public class FieldNameTranslator {

	// 한글 -> 영어 매핑
	private static final Map<String, String> koreanToEnglishMap = new LinkedHashMap<>();

	static {
		koreanToEnglishMap.put("번호", "id");
		koreanToEnglishMap.put("개방서비스명", "serviceName");
		koreanToEnglishMap.put("개방서비스아이디", "serviceId");
		koreanToEnglishMap.put("개방자치단체코드", "municipalityCode");
		koreanToEnglishMap.put("관리번호", "managementNumber");
		koreanToEnglishMap.put("인허가일자", "licenseDate");
		koreanToEnglishMap.put("인허가취소일자", "licenseCancellationDate");
		koreanToEnglishMap.put("영업상태구분코드", "businessStatusCode");
		koreanToEnglishMap.put("영업상태명", "businessStatusName");
		koreanToEnglishMap.put("상세영업상태코드", "detailedBusinessStatusCode");
		koreanToEnglishMap.put("상세영업상태명", "detailedBusinessStatusName");
		koreanToEnglishMap.put("폐업일자", "closureDate");
		koreanToEnglishMap.put("휴업시작일자", "suspensionStartDate");
		koreanToEnglishMap.put("휴업종료일자", "suspensionEndDate");
		koreanToEnglishMap.put("재개업일자", "reopeningDate");
		koreanToEnglishMap.put("소재지전화", "addressPhone");
		koreanToEnglishMap.put("소재지면적", "addressArea");
		koreanToEnglishMap.put("소재지우편번호", "addressPostcode");
		koreanToEnglishMap.put("소재지전체주소", "fullAddress");
		koreanToEnglishMap.put("도로명전체주소", "roadNameFullAddress");
		koreanToEnglishMap.put("도로명우편번호", "roadNamePostcode");
		koreanToEnglishMap.put("사업장명", "businessName");
		koreanToEnglishMap.put("최종수정시점", "lastModified");
		koreanToEnglishMap.put("데이터갱신구분", "dataUpdateType");
		koreanToEnglishMap.put("데이터갱신일자", "dataUpdateDate");
		koreanToEnglishMap.put("업태구분명", "businessTypeName");
		koreanToEnglishMap.put("좌표정보(x)", "coordinateX");
		koreanToEnglishMap.put("좌표정보(y)", "coordinateY");
		koreanToEnglishMap.put("위생업태명", "sanitationBusinessTypeName");
		koreanToEnglishMap.put("남성종사자수", "maleEmployeeCount");
		koreanToEnglishMap.put("여성종사자수", "femaleEmployeeCount");
		koreanToEnglishMap.put("영업장주변구분명", "businessAreaClassification");
		koreanToEnglishMap.put("등급구분명", "gradeClassification");
		koreanToEnglishMap.put("급수시설구분명", "waterFacilityClassification");
		koreanToEnglishMap.put("총직원수", "totalEmployeeCount");
		koreanToEnglishMap.put("본사직원수", "headquartersEmployeeCount");
		koreanToEnglishMap.put("공장사무직직원수", "factoryOfficeStaffCount");
		koreanToEnglishMap.put("공장판매직직원수", "factorySalesStaffCount");
		koreanToEnglishMap.put("공장생산직직원수", "factoryProductionStaffCount");
		koreanToEnglishMap.put("건물소유구분명", "buildingOwnershipClassification");
		koreanToEnglishMap.put("보증액", "guaranteeAmount");
		koreanToEnglishMap.put("월세액", "monthlyRent");
		koreanToEnglishMap.put("다중이용업소여부", "multiUseFacilityFlag");
		koreanToEnglishMap.put("시설총규모", "totalFacilitySize");
		koreanToEnglishMap.put("전통업소지정번호", "traditionalBusinessDesignationNumber");
		koreanToEnglishMap.put("전통업소주된음식", "traditionalBusinessMainDish");
		koreanToEnglishMap.put("홈페이지", "website");
	}

	/**
	 * CSV 헤더를 PublicData 필드명 배열로 변환
	 *
	 * @return 변환된 필드명 배열
	 */
	public static String[] translateHeader() {
		return koreanToEnglishMap.values().toArray(new String[0]);
	}

	/**
	 * 한글 값을 영어로 변환
	 *
	 * @param koreanValue 변환할 한글 값
	 * @return 변환된 영어 값. 매핑되지 않은 경우 원래 값을 반환.
	 */
	public static String translate(String koreanValue) {
		return koreanToEnglishMap.getOrDefault(koreanValue, koreanValue);
	}
}
