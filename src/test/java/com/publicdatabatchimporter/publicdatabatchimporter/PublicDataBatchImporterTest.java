package com.publicdatabatchimporter.publicdatabatchimporter;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.publicdatabatchimporter.publicdatabatchimporter.config.BatchConfig;
import com.publicdatabatchimporter.publicdatabatchimporter.dto.PublicDataDTO;
import com.publicdatabatchimporter.publicdatabatchimporter.model.PublicData;
import com.publicdatabatchimporter.publicdatabatchimporter.writer.PublicDataItemWriter;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@Import(BatchConfig.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PublicDataBatchImporterTest {

	@Autowired
	private JobLauncher jobLauncher;

	@Autowired
	private Job importJob;

	@Autowired
	private JdbcTemplate jdbcTemplate; // 데이터베이스 검증용

	@Autowired
	private PublicDataItemWriter writer;

	@Test
	void testBatchJobRunsSuccessfully() throws Exception {
		// Batch Job 실행
		JobExecution jobExecution = jobLauncher.run(importJob, new JobParameters());

		// 디버깅을 위한 로그 추가
		System.out.println("Job Execution Status: " + jobExecution.getStatus());
		System.out.println("Job Exit Status: " + jobExecution.getExitStatus());
		System.out.println("Job Failure Exceptions: " + jobExecution.getAllFailureExceptions());

		// Batch Job 상태 확인
		assertThat(jobExecution.getStatus().isUnsuccessful()).isFalse(); // 실패했는지 확인
		assertThat(jobExecution.getExitStatus().getExitCode()).isEqualTo("COMPLETED"); // 정상 종료 확인
	}


	@Test
	void testCsvFileReader() {
		// 데이터베이스에 저장된 내용을 확인
		List<PublicDataDTO> data = jdbcTemplate.query(
			"SELECT * FROM publicdata",
			(rs, rowNum) -> {
				PublicDataDTO publicData = new PublicDataDTO();
				publicData.setServiceName(rs.getString("serviceName"));
				publicData.setServiceId(rs.getString("serviceId"));
				publicData.setMunicipalityCode(rs.getString("municipalityCode"));
				publicData.setManagementNumber(rs.getString("managementNumber"));
				publicData.setLicenseDate(rs.getDate("licenseDate"));
				return publicData;
			}
		);

		// 검증
		assertThat(data).isNotEmpty(); // 데이터가 읽혔는지 확인
		assertThat(data.get(0).getServiceName()).isNotNull();
	}

	// @Test
	// void testItemProcessor() throws Exception {
	// 	// 예제 PublicData 생성
	// 	PublicDataDTO input = new PublicDataDTO();
	// 	input.setServiceName("Test Service");
	// 	input.setBusinessStatusName("Active");
	//
	// 	// Processor 호출
	// 	PublicDataDTO processed = writer.process(input);
	//
	// 	// 데이터 검증
	// 	assertThat(processed).isNotNull();
	// 	assertThat(processed.getServiceName()).isEqualTo("Test Service");
	// 	assertThat(processed.getBusinessStatusName()).isEqualTo("Active");
	// }

	@Test
	void testItemWriter() throws Exception {
		// 예제 데이터 생성
		PublicDataDTO item = new PublicDataDTO();
		item.setServiceName("Test Service");
		item.setServiceId("TEST001");
		item.setMunicipalityCode("12345");

		// 데이터 저장
		// writer.write(List.of(item));

		// 데이터베이스 확인
		List<PublicDataDTO> data = jdbcTemplate.query(
			"SELECT * FROM publicdata WHERE serviceId = ?",
			new Object[] { "TEST001" },
			(rs, rowNum) -> {
				PublicDataDTO publicData = new PublicDataDTO();
				publicData.setServiceName(rs.getString("serviceName"));
				publicData.setServiceId(rs.getString("serviceId"));
				publicData.setMunicipalityCode(rs.getString("municipalityCode"));
				return publicData;
			}
		);

		// 검증
		assertThat(data).hasSize(1);
		assertThat(data.get(0).getServiceName()).isEqualTo("Test Service");
		assertThat(data.get(0).getServiceId()).isEqualTo("TEST001");
	}
}
