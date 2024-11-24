// package com.publicdatabatchimporter.publicdatabatchimporter.processor;
//
// import org.springframework.batch.item.ItemProcessor;
//
// import com.publicdatabatchimporter.publicdatabatchimporter.model.PublicData;
// import com.publicdatabatchimporter.publicdatabatchimporter.utils.FieldNameTranslator;
//
// public class PublicDataProcessor implements ItemProcessor<PublicData, PublicData> {
//
// 	@Override
// 	public PublicData process(PublicData item) throws Exception {
// 		PublicData dto = new PublicData();
//
// 		// 직접 매핑: 변환이 필요 없는 필드
// 		dto.setId(item.getId());
// 		dto.setManagementNumber(item.getManagementNumber());
// 		dto.setLicenseDate(item.getLicenseDate());
// 		dto.setLicenseCancellationDate(item.getLicenseCancellationDate());
// 		dto.setClosureDate(item.getClosureDate());
// 		dto.setSuspensionStartDate(item.getSuspensionStartDate());
// 		dto.setSuspensionEndDate(item.getSuspensionEndDate());
// 		dto.setReopeningDate(item.getReopeningDate());
// 		dto.setAddressPhone(item.getAddressPhone());
// 		dto.setAddressArea(item.getAddressArea());
// 		dto.setAddressPostcode(item.getAddressPostcode());
// 		dto.setLastModified(item.getLastModified());
// 		dto.setDataUpdateDate(item.getDataUpdateDate());
// 		dto.setCoordinateX(item.getCoordinateX());
// 		dto.setCoordinateY(item.getCoordinateY());
// 		dto.setMaleEmployeeCount(item.getMaleEmployeeCount());
// 		dto.setFemaleEmployeeCount(item.getFemaleEmployeeCount());
// 		dto.setTotalEmployeeCount(item.getTotalEmployeeCount());
// 		dto.setHeadquartersEmployeeCount(item.getHeadquartersEmployeeCount());
// 		dto.setFactoryOfficeStaffCount(item.getFactoryOfficeStaffCount());
// 		dto.setFactorySalesStaffCount(item.getFactorySalesStaffCount());
// 		dto.setFactoryProductionStaffCount(item.getFactoryProductionStaffCount());
// 		dto.setGuaranteeAmount(item.getGuaranteeAmount());
// 		dto.setMonthlyRent(item.getMonthlyRent());
// 		dto.setTotalFacilitySize(item.getTotalFacilitySize());
// 		dto.setWebsite(item.getWebsite());
//
// 		// 변환이 필요한 필드
// 		mapTranslatedFields(item, dto);
//
// 		return dto;
// 	}
//
// 	/**
// 	 * 변환이 필요한 필드를 FieldNameTranslator로 매핑합니다.
// 	 */
// 	private void mapTranslatedFields(PublicData item, PublicData dto) {
// 		dto.setServiceName(translate(item.getServiceName()));
// 		dto.setServiceId(translate(item.getServiceId()));
// 		dto.setMunicipalityCode(translate(item.getMunicipalityCode()));
// 		dto.setBusinessStatusCode(translate(item.getBusinessStatusCode()));
// 		dto.setBusinessStatusName(translate(item.getBusinessStatusName()));
// 		dto.setDetailedBusinessStatusCode(translate(item.getDetailedBusinessStatusCode()));
// 		dto.setDetailedBusinessStatusName(translate(item.getDetailedBusinessStatusName()));
// 		dto.setFullAddress(translate(item.getFullAddress()));
// 		dto.setRoadNameFullAddress(translate(item.getRoadNameFullAddress()));
// 		dto.setBusinessName(translate(item.getBusinessName()));
// 		dto.setDataUpdateType(translate(item.getDataUpdateType()));
// 		dto.setBusinessTypeName(translate(item.getBusinessTypeName()));
// 		dto.setSanitationBusinessTypeName(translate(item.getSanitationBusinessTypeName()));
// 		dto.setBusinessAreaClassification(translate(item.getBusinessAreaClassification()));
// 		dto.setGradeClassification(translate(item.getGradeClassification()));
// 		dto.setWaterFacilityClassification(translate(item.getWaterFacilityClassification()));
// 		dto.setBuildingOwnershipClassification(translate(item.getBuildingOwnershipClassification()));
// 		dto.setMultiUseFacilityFlag(translate(item.getMultiUseFacilityFlag()));
// 		dto.setTraditionalBusinessDesignationNumber(translate(item.getTraditionalBusinessDesignationNumber()));
// 		dto.setTraditionalBusinessMainDish(translate(item.getTraditionalBusinessMainDish()));
// 	}
//
// 	/**
// 	 * FieldNameTranslator를 이용하여 값 변환
// 	 */
// 	private String translate(String value) {
// 		return FieldNameTranslator.translate(value);
// 	}
// }
