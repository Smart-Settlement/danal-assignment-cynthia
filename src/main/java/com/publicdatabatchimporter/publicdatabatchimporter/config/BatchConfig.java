package com.publicdatabatchimporter.publicdatabatchimporter.config;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.item.Chunk;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.core.io.ClassPathResource;

import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;

import com.publicdatabatchimporter.publicdatabatchimporter.model.PublicData;

@Configuration
@EnableBatchProcessing  // Spring Batch 기능을 활성화하는 어노테이션
public class BatchConfig {

	@Autowired
	private DataSource dataSource;  // 데이터베이스 연결을 위한 DataSource 주입

	private final JobRepository jobRepository;  // Batch Job의 메타데이터를 저장하는 JobRepository
	private final PlatformTransactionManager transactionManager;  // 트랜잭션 관리자를 위한 PlatformTransactionManager

	// 생성자 주입을 통해 JobRepository와 PlatformTransactionManager를 주입받습니다.
	@Autowired
	public BatchConfig(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
		this.jobRepository = jobRepository;
		this.transactionManager = transactionManager;
	}

	// CSV 파일을 읽어오는 FlatFileItemReader 설정
	@Bean
	public FlatFileItemReader<PublicData> reader() {
		FlatFileItemReader<PublicData> reader = new FlatFileItemReader<>();
		reader.setResource(new ClassPathResource("fulldata_07_24_04_P_일반음식점.csv"));  // CSV 파일 경로 설정
		// 파일을 읽을 때 각 라인의 구분을 설정합니다. 여기서는 콤마로 구분된 CSV 파일입니다.
		reader.setLineMapper(new DefaultLineMapper<PublicData>() {{
			setLineTokenizer(new DelimitedLineTokenizer() {{
				setNames(
					"id", "serviceName", "serviceId", "municipalityCode", "managementNumber",
					"licenseDate", "licenseCancellationDate", "businessStatusCode", "businessStatusName",
					"detailedBusinessStatusCode", "detailedBusinessStatusName", "closureDate",
					"suspensionStartDate", "suspensionEndDate", "reopeningDate", "addressPhone",
					"addressArea", "addressPostcode", "fullAddress", "roadNameFullAddress",
					"roadNamePostcode", "businessName", "lastModified", "dataUpdateType",
					"dataUpdateDate", "businessTypeName", "coordinateX", "coordinateY",
					"sanitationBusinessTypeName", "maleEmployeeCount", "femaleEmployeeCount",
					"businessAreaClassification", "gradeClassification", "waterFacilityClassification",
					"totalEmployeeCount", "headquartersEmployeeCount", "factoryOfficeStaffCount",
					"factorySalesStaffCount", "factoryProductionStaffCount", "buildingOwnershipClassification",
					"guaranteeAmount", "monthlyRent", "multiUseFacilityFlag", "totalFacilitySize",
					"traditionalBusinessDesignationNumber", "traditionalBusinessMainDish", "website"
				);
			}});

			setFieldSetMapper(new BeanWrapperFieldSetMapper<PublicData>() {{
				setTargetType(PublicData.class);  // 읽은 데이터를 PublicData 객체로 매핑합니다.
			}});
		}});

		return reader;
	}

	// 데이터 처리(가공)하는 Processor 설정
	@Bean
	public ItemProcessor<PublicData, PublicData> processor() {
		return new ItemProcessor<PublicData, PublicData>() {
			@Override
			public PublicData process(PublicData item) throws Exception {
				// 여기서 데이터 가공 로직을 넣습니다. (예: 날짜 포맷 변경, 불필요한 데이터 제거 등)
				return item;  // 현재는 그대로 반환
			}
		};
	}

	// 데이터를 DB에 저장하는 Writer 설정
	@Bean
	public ItemWriter<PublicData> writer() {
		return new ItemWriter<PublicData>() {
			@Override
			public void write(Chunk<? extends PublicData> chunk) throws Exception {
				// Chunk 단위로 DB에 데이터를 저장하는 로직을 구현해야 합니다.
			}

			public void write(List<? extends PublicData> items) throws Exception {
				// DB에 저장할 데이터 항목들을 처리합니다.
				// 예시: DB에 저장하는 로직을 구현
			}
		};
	}

	// Step 정의: Job의 각 단계를 설정합니다. 여기서는 CSV 파일을 읽고 처리하는 Step입니다.
	@Bean
	public Step step1() {
		// StepBuilder를 JobRepository와 TransactionManager로 생성
		StepBuilder stepBuilder = new StepBuilder("step1", jobRepository);
		return stepBuilder
			.<PublicData, PublicData>chunk(10)  // 10개 단위로 데이터를 처리하도록 설정
			.reader(reader())  // Reader 설정
			.processor(processor())  // Processor 설정
			.writer(writer())  // Writer 설정
			.transactionManager(transactionManager)  // 트랜잭션 관리 설정
			.build();  // Step 빌드 완료
	}

	// Job 정의: Step들을 조합하여 Job을 정의합니다.
	@Bean
	public Job importJob() {
		JobBuilder jobBuilder = new JobBuilder("importJob", jobRepository);
		return jobBuilder
			.incrementer(new RunIdIncrementer())  // Job의 인크리먼터 설정 (매번 실행 시 고유한 실행 ID를 부여)
			.start(step1())  // Job의 첫 번째 Step 설정
			.build();  // Job 빌드 완료
	}
}
