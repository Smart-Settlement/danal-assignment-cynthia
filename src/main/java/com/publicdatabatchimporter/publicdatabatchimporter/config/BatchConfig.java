package com.publicdatabatchimporter.publicdatabatchimporter.config;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ExecutionContext;
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
import com.publicdatabatchimporter.publicdatabatchimporter.repository.PublicDataRepository;
import com.publicdatabatchimporter.publicdatabatchimporter.utils.FieldNameTranslator;

@Configuration
@EnableBatchProcessing  // Spring Batch 기능 활성화
public class BatchConfig {

	private final JobRepository jobRepository;  // Batch Job의 메타데이터를 저장하는 JobRepository
	private final PlatformTransactionManager transactionManager;  // 트랜잭션 관리자를 위한 PlatformTransactionManager

	// 생성자를 통해 JobRepository와 PlatformTransactionManager를 주입받습니다.
	@Autowired
	public BatchConfig(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
		this.jobRepository = jobRepository;
		this.transactionManager = transactionManager;
	}

	// CSV 파일을 읽어오는 Reader 설정
	@Bean
	public FlatFileItemReader<PublicData> reader() {
		FlatFileItemReader<PublicData> reader = new FlatFileItemReader<>();
		reader.setResource(new ClassPathResource("fulldata_07_24_04_P.csv")); // CSV 파일 경로 설정
		reader.setLinesToSkip(1); // 첫 번째 라인(헤더)을 건너뜀

		// BufferedReader를 UTF-8로 설정
		reader.setBufferedReaderFactory((resource, encoding) ->
			new BufferedReader(new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8))
		);

		// CSV 데이터를 DTO 객체로 매핑
		reader.setLineMapper(new DefaultLineMapper<>() {{
			setLineTokenizer(new DelimitedLineTokenizer() {{
				setNames(FieldNameTranslator.translateHeader()); // CSV 헤더를 DTO 필드에 매핑
				setDelimiter(","); // 데이터 구분자 설정
				setStrict(false); // 데이터 누락 허용
			}});
			setFieldSetMapper(new BeanWrapperFieldSetMapper<>() {{
				setTargetType(PublicData.class); // DTO 클래스 설정
			}});
		}});

		// Reader에서 데이터 읽기 테스트 (디버깅용)
		try {
			reader.open(new ExecutionContext());
			PublicData data = reader.read();
			if (data != null) {
				System.out.println("Reader output: " + data);
			} else {
				System.out.println("No data found.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reader;
	}

	// 데이터를 처리(가공)하는 Processor 설정
	@Bean
	public ItemProcessor<PublicData, PublicData> processor() {
		return item -> {
			if (item == null) {
				System.out.println("Processor received null item");
				return null;
			}
			System.out.println("Processor input: " + item); // 디버깅용 로그
			return item; // 그대로 반환
		};
	}

	// 데이터를 DB에 저장하는 Writer 설정
	@Bean
	public ItemWriter<PublicData> writer(PublicDataRepository publicDataRepository) {
		return items -> {
			try {
				if (items == null) {
					System.out.println("Writer received null items.");
					return;
				}
				if (items.isEmpty()) {
					System.out.println("Writer received no items.");
					return;
				}
				System.out.println("Writer received " + items.size() + " items.");
				for (PublicData item : items) {
					System.out.println("Item to save: " + item);
					if (item.getServiceName() == null) {
						System.out.println("Invalid item: ServiceName is null for item " + item);
					}
				}

				// 데이터를 데이터베이스에 저장
				System.out.println("Attempting to save items...");
				publicDataRepository.saveAll(items); // JPA를 통해 데이터 저장
				System.out.println("Data saved successfully: " + items.size() + " items.");
			} catch (Exception e) {
				System.err.println("Exception in Writer: " + e.getMessage());
				e.printStackTrace(); // 예외 출력
				throw e; // 예외를 다시 던져 Spring Batch에 알림
			}
		};
	}

	// Step 정의: Job의 단계를 설정 (CSV 파일 읽기, 처리, 저장)
	@Bean
	public Step step1(PublicDataRepository publicDataRepository) {
		return new StepBuilder("step1", jobRepository)
			.<PublicData, PublicData>chunk(1) // Chunk 단위 처리 (1개씩 처리)
			.reader(reader()) // Reader 설정
			// .processor(processor()) // Processor 설정
			.writer(writer(publicDataRepository)) // Writer 설정
			.listener(new StepExecutionListener() { // Step 실행 이벤트 리스너 추가
				@Override
				public void beforeStep(StepExecution stepExecution) {
					System.out.println("Step 시작");
				}

				@Override
				public ExitStatus afterStep(StepExecution stepExecution) {
					System.out.println("Step 종료. 처리된 아이템 수: " + stepExecution.getWriteCount());
					System.out.println("Step 종료 상태: " + stepExecution.getExitStatus());
					return stepExecution.getExitStatus();
				}
			})
			.transactionManager(transactionManager) // 트랜잭션 관리
			.build();
	}

	// Job 정의: Step을 포함하여 Batch 작업을 설정
	@Bean
	public Job importJob(PublicDataRepository publicDataRepository) {
		return new JobBuilder("importJob", jobRepository)
			.incrementer(new RunIdIncrementer()) // 매 실행마다 고유 ID 부여
			.start(step1(publicDataRepository)) // Step 추가
			.build();
	}
}
