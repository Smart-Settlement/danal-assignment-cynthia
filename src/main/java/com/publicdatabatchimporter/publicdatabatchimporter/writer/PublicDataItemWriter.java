package com.publicdatabatchimporter.publicdatabatchimporter.writer;

import java.time.temporal.TemporalAccessor;

import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.publicdatabatchimporter.publicdatabatchimporter.dto.PublicDataDTO;
import com.publicdatabatchimporter.publicdatabatchimporter.model.PublicData;

@Component
public class PublicDataItemWriter implements ItemWriter<PublicData> {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public void write(Chunk<? extends PublicData> chunk) throws Exception {
		for (PublicData item : chunk) {
			// 데이터베이스에 삽입하는 SQL 쿼리문
			String sql = "INSERT INTO PublicData (serviceName, serviceId, municipalityCode, managementNumber, " +
				"licenseDate, licenseCancellationDate, businessStatusCode, businessStatusName, " +
				"detailedBusinessStatusCode, detailedBusinessStatusName, closureDate, suspensionStartDate, " +
				"suspensionEndDate, reopeningDate, addressPhone, addressArea, addressPostcode, fullAddress, " +
				"roadNameFullAddress, roadNamePostcode, businessName, lastModified, dataUpdateType, " +
				"dataUpdateDate, businessTypeName, coordinateX, coordinateY, sanitationBusinessTypeName, " +
				"maleEmployeeCount, femaleEmployeeCount, businessAreaClassification, gradeClassification, " +
				"waterFacilityClassification, totalEmployeeCount, headquartersEmployeeCount, factoryOfficeStaffCount, " +
				"factorySalesStaffCount, factoryProductionStaffCount, buildingOwnershipClassification, guaranteeAmount, " +
				"monthlyRent, multiUseFacilityFlag, totalFacilitySize, traditionalBusinessDesignationNumber, " +
				"traditionalBusinessMainDish, website) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

			// JdbcTemplate을 사용하여 DB에 삽입
			jdbcTemplate.update(sql,
				item.getServiceName(), item.getServiceId(), item.getMunicipalityCode(), item.getManagementNumber(),
				item.getLicenseDate(), item.getLicenseCancellationDate(), item.getBusinessStatusCode(), item.getBusinessStatusName(),
				item.getDetailedBusinessStatusCode(), item.getDetailedBusinessStatusName(), item.getClosureDate(),
				item.getSuspensionStartDate(), item.getSuspensionEndDate(), item.getReopeningDate(), item.getAddressPhone(),
				item.getAddressArea(), item.getAddressPostcode(), item.getFullAddress(), item.getRoadNameFullAddress(),
				item.getRoadNamePostcode(), item.getBusinessName(), item.getLastModified(), item.getDataUpdateType(),
				item.getDataUpdateDate(), item.getBusinessTypeName(), item.getCoordinateX(), item.getCoordinateY(),
				item.getSanitationBusinessTypeName(), item.getMaleEmployeeCount(), item.getFemaleEmployeeCount(),
				item.getBusinessAreaClassification(), item.getGradeClassification(), item.getWaterFacilityClassification(),
				item.getTotalEmployeeCount(), item.getHeadquartersEmployeeCount(), item.getFactoryOfficeStaffCount(),
				item.getFactorySalesStaffCount(), item.getFactoryProductionStaffCount(), item.getBuildingOwnershipClassification(),
				item.getGuaranteeAmount(), item.getMonthlyRent(), item.getMultiUseFacilityFlag(), item.getTotalFacilitySize(),
				item.getTraditionalBusinessDesignationNumber(), item.getTraditionalBusinessMainDish(), item.getWebsite()
			);
		}
	}
}
