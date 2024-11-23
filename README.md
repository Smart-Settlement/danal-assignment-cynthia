# public-data-batch-importer

- 이 프로젝트는 Spring Batch를 사용하여 CSV 파일에서 데이터를 읽고 데이터베이스에 저장하는 배치 애플리케이션입니다. 
- Spring Batch의 청크 단위 처리 모델을 사용하며, JPA를 통해 데이터 저장을 관리합니다.

## 📂 프로젝트 구조
```bash
src/
├── main/
│   ├── java/
│   │   ├── com/publicdatabatchimporter/publicdatabatchimporter/
│   │   │   ├── config/                # 배치 구성 파일
│   │   │   │   └── BatchConfig.java  # Job, Step, Reader, Writer, Processor 설정
│   │   │   ├── model/                # 공공 데이터 모델 클래스
│   │   │   ├── repository/           # 데이터베이스 작업을 위한 JPA 리포지토리
│   │   │   ├── utils/                # CSV 헤더 변환 유틸리티 클래스
│   │   │   └── writer/               # 배치 Job 실행 클래스
│   ├── resources/
│   │   ├── application.properties    # 애플리케이션 설정
│   │   ├── schema.sql                # 데이터베이스 테이블 정의 스크립트
│   │   └── fulldata_07_24_04_P.csv   # 처리할 CSV 파일
```

## ✨ 주요 기능

- 청크 단위 처리: 한 번에 청크 크기만큼 데이터를 처리 (기본 설정: 1개씩 처리).
- 유연한 Step 구성:
  - CSV 파일에서 데이터를 읽기
  - 데이터를 가공 및 처리 (필요 시)
  - 가공된 데이터를 데이터베이스에 저장
- 데이터베이스 통합:
  - JPA 및 Spring Data를 사용하여 데이터 저장.
- 에러 핸들링:
  - Writer 단계에서 데이터 삽입 시 발생하는 에러를 캡처하고 로깅.
- 실행 수명 주기 관리:
  - Step 시작 및 완료 시 로그를 출력.

## 🛠️ 사전 준비

- JDK 17
- Spring Framework 6.x
- Spring Batch 5.x
- mysql 8.x
- gradle 8.x

## ⚙️ 설정 방법
1. 애플리케이션 설정
   application.properties 파일에 데이터베이스 연결 정보를 설정하세요:

```properties
# 데이터베이스 설정
spring.datasource.url=jdbc:mysql://localhost:3306/your_database
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update

# Hibernate SQL 로깅 (선택 사항)
spring.jpa.show-sql=true

# 배치 테이블 접두어 설정 (선택 사항)
spring.batch.jdbc.table-prefix=BATCH_
```

2. CSV 파일
   fulldata_07_24_04_P.csv 파일을 resources/ 디렉토리에 배치하세요. 파일은 다음과 같은 구조를 가져야 합니다:

```mathematica
헤더1, 헤더2, ..., 헤더N
값1, 값2, ..., 값N
```

3. 데이터베이스 테이블
   데이터를 저장할 테이블을 생성하려면 schema.sql 스크립트를 실행하세요:

```sql
CREATE TABLE public_data (
id INT AUTO_INCREMENT PRIMARY KEY,
service_name VARCHAR(255) NULL,
service_id VARCHAR(255) NULL,
municipality_code VARCHAR(255) NULL,
management_number VARCHAR(255) NULL,
license_date VARCHAR(255) NULL,
coordinate_x DECIMAL(10, 6) NULL,
coordinate_y DECIMAL(10, 6) NULL
-- 생략
);
```

## 🚀 실행 방법

1. 애플리케이션 빌드
   Gradle으로 프로젝트를 빌드합니다:
```
bash
./gradlew clean build
```

2. 애플리케이션 실행
   애플리케이션을 실행하려면 다음 명령어를 사용하세요:

```bash
java -jar build/libs/public-data-batch-importer.jar
```

## 📝 코드 설명

BatchConfig.java
배치 작업을 구성하는 주요 설정 클래스입니다.

1. Reader
   CSV 파일에서 데이터를 읽고 DTO로 매핑

```ava
@Bean
public FlatFileItemReader<PublicData> reader() { ... }
```

2. Writer
   가공된 데이터를 데이터베이스에 저장

```java
@Bean
public ItemWriter<PublicData> writer(PublicDataRepository publicDataRepository) { ... }
```

3. Step
   단일 배치 처리 단계를 정의합니다.

```java
@Bean
public Step step1(PublicDataRepository publicDataRepository) { ... }
```

4. Job
   Step을 조합하여 배치 작업(Job)을 정의합니다.

```java
@Bean
public Job importJob(PublicDataRepository publicDataRepository) { ... }
```

## 🐞 디버깅 및 로깅

1. 로그 레벨 설정: 디버깅을 위해 application.properties 파일에 다음을 추가:

```properties
logging.level.org.springframework.batch=DEBUG
logging.level.com.publicdatabatchimporter=DEBUG
```

2. Job 수명 주기 로그:

- Step 시작 및 종료 시 로그가 출력됩니다.
- Writer는 처리된 항목 수를 로깅합니다.

## 🛠️ 커스터마이징

- 청크 크기 변경: Step 정의에서 처리할 데이터의 크기를 변경할 수 있습니다.
- 에러 핸들링: Writer에서 특정 예외에 대한 처리를 확장할 수 있습니다.

## 🛡️ 에러 핸들링

- CSV 파일의 잘못된 행: 누락되거나 유효하지 않은 필드는 처리 중 로깅됩니다.
- 데이터베이스 에러: Writer에서 데이터 삽입 시 발생하는 에러를 캡처하고 로그를 남깁니다.

## 📖 참고 자료

- Spring Batch 공식 문서
- Spring Data JPA 공식 문서

