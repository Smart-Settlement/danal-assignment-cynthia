CREATE TABLE public_data (
                             id INT AUTO_INCREMENT PRIMARY KEY, -- ID (자동 증가)
                             service_name VARCHAR(255) NULL, -- 개방서비스명
                             service_id VARCHAR(255) NULL, -- 개방서비스아이디
                             municipality_code VARCHAR(255) NULL, -- 개방자치단체코드
                             management_number VARCHAR(255) NULL, -- 관리번호
                             license_date VARCHAR(255) NULL, -- 인허가일자
                             license_cancellation_date VARCHAR(255) NULL, -- 인허가취소일자
                             business_status_code INT NULL, -- 영업상태구분코드
                             business_status_name VARCHAR(255) NULL, -- 영업상태명
                             detailed_business_status_code INT NULL, -- 상세영업상태코드
                             detailed_business_status_name VARCHAR(255) NULL, -- 상세영업상태명
                             closure_date VARCHAR(255) NULL, -- 폐업일자
                             suspension_start_date VARCHAR(255) NULL, -- 휴업시작일자
                             suspension_end_date VARCHAR(255) NULL, -- 휴업종료일자
                             reopening_date VARCHAR(255) NULL, -- 재개업일자
                             address_phone VARCHAR(255) NULL, -- 소재지전화
                             address_area DECIMAL(10, 2) NULL, -- 소재지면적
                             address_postcode VARCHAR(10) NULL, -- 소재지우편번호
                             full_address VARCHAR(255) NULL, -- 소재지전체주소
                             road_name_full_address VARCHAR(255) NULL, -- 도로명전체주소
                             road_name_postcode VARCHAR(10) NULL, -- 도로명우편번호
                             business_name VARCHAR(255) NULL, -- 사업장명
                             last_modified VARCHAR(255) NULL, -- 최종수정시점
                             data_update_type VARCHAR(50) NULL, -- 데이터갱신구분
                             data_update_date VARCHAR(255) NULL, -- 데이터갱신일자
                             business_type_name VARCHAR(255) NULL, -- 업태구분명
                             coordinate_x VARCHAR(255) NULL, -- 좌표정보(x)
                             coordinate_y VARCHAR(255) NULL, -- 좌표정보(y)
                             sanitation_business_type_name VARCHAR(255) NULL, -- 위생업태명
                             male_employee_count INT NULL, -- 남성종사자수
                             female_employee_count INT NULL, -- 여성종사자수
                             business_area_classification VARCHAR(255) NULL, -- 영업장주변구분명
                             grade_classification VARCHAR(255) NULL, -- 등급구분명
                             water_facility_classification VARCHAR(255) NULL, -- 급수시설구분명
                             total_employee_count INT NULL, -- 총직원수
                             headquarters_employee_count INT NULL, -- 본사직원수
                             factory_office_staff_count INT NULL, -- 공장사무직직원수
                             factory_sales_staff_count INT NULL, -- 공장판매직직원수
                             factory_production_staff_count INT NULL, -- 공장생산직직원수
                             building_ownership_classification VARCHAR(255) NULL, -- 건물소유구분명
                             guarantee_amount DECIMAL(15, 2) NULL, -- 보증액
                             monthly_rent DECIMAL(15, 2) NULL, -- 월세액
                             multi_use_facility_flag VARCHAR(10) NULL, -- 다중이용업소여부
                             total_facility_size DECIMAL(15, 2) NULL, -- 시설총규모
                             traditional_business_designation_number VARCHAR(255) NULL, -- 전통업소지정번호
                             traditional_business_main_dish VARCHAR(255) NULL, -- 전통업소주된음식
                             website VARCHAR(255) NULL -- 홈페이지
);



-- https://github.com/spring-projects/spring-batch/tree/main/spring-batch-core/src/main/resources/org/springframework/batch/core/schema-mysql.sql

CREATE TABLE BATCH_JOB_INSTANCE  (
                                     JOB_INSTANCE_ID BIGINT NOT NULL PRIMARY KEY ,
                                     VERSION BIGINT,
                                     JOB_NAME VARCHAR(100) NOT NULL,
                                     JOB_KEY VARCHAR(32) NOT NULL,
                                     CONSTRAINT JOB_INST_UN UNIQUE (JOB_NAME, JOB_KEY)
) ENGINE=InnoDB;

CREATE TABLE BATCH_JOB_EXECUTION  (
                                      JOB_EXECUTION_ID BIGINT NOT NULL PRIMARY KEY ,
                                      VERSION BIGINT,
                                      JOB_INSTANCE_ID BIGINT NOT NULL,
                                      CREATE_TIME DATETIME NOT NULL,
                                      START_TIME DATETIME NULL,
                                      END_TIME DATETIME  NULL,
                                      STATUS VARCHAR(10),
                                      EXIT_CODE VARCHAR(2500),
                                      EXIT_MESSAGE VARCHAR(2500),
                                      LAST_UPDATED DATETIME,
                                      JOB_CONFIGURATION_LOCATION VARCHAR(2500) NULL,
                                      CONSTRAINT JOB_INST_EXEC_FK FOREIGN KEY (JOB_INSTANCE_ID)
                                          REFERENCES BATCH_JOB_INSTANCE(JOB_INSTANCE_ID)
) ENGINE=InnoDB;

CREATE TABLE BATCH_JOB_EXECUTION_PARAMS (
                                            JOB_EXECUTION_ID BIGINT NOT NULL,
                                            PARAMETER_NAME VARCHAR(100) NOT NULL,
                                            PARAMETER_TYPE VARCHAR(100) NOT NULL,
                                            PARAMETER_VALUE VARCHAR(2500),
                                            IDENTIFYING CHAR(1) NOT NULL,
                                            CONSTRAINT JOB_EXEC_PARAMS_FK FOREIGN KEY (JOB_EXECUTION_ID)
                                                REFERENCES BATCH_JOB_EXECUTION(JOB_EXECUTION_ID)
) ENGINE=InnoDB;

CREATE TABLE BATCH_STEP_EXECUTION  (
                                       STEP_EXECUTION_ID BIGINT NOT NULL PRIMARY KEY ,
                                       VERSION BIGINT NOT NULL,
                                       STEP_NAME VARCHAR(100) NOT NULL,
                                       JOB_EXECUTION_ID BIGINT NOT NULL,
                                       START_TIME DATETIME NULL,
                                       END_TIME DATETIME NULL,
                                       STATUS VARCHAR(10),
                                       COMMIT_COUNT BIGINT,
                                       READ_COUNT BIGINT,
                                       FILTER_COUNT BIGINT,
                                       WRITE_COUNT BIGINT,
                                       READ_SKIP_COUNT BIGINT,
                                       WRITE_SKIP_COUNT BIGINT,
                                       PROCESS_SKIP_COUNT BIGINT,
                                       ROLLBACK_COUNT BIGINT,
                                       EXIT_CODE VARCHAR(2500),
                                       EXIT_MESSAGE VARCHAR(2500),
                                       LAST_UPDATED DATETIME,
                                       CREATE_TIME DATETIME,
                                       CONSTRAINT JOB_EXEC_STEP_FK FOREIGN KEY (JOB_EXECUTION_ID)
                                           REFERENCES BATCH_JOB_EXECUTION(JOB_EXECUTION_ID)
) ENGINE=InnoDB;

CREATE TABLE BATCH_STEP_EXECUTION_CONTEXT  (
                                               STEP_EXECUTION_ID BIGINT NOT NULL PRIMARY KEY,
                                               SHORT_CONTEXT TEXT NOT NULL,
                                               SERIALIZED_CONTEXT TEXT DEFAULT NULL,
                                               CONSTRAINT STEP_EXEC_CTX_FK FOREIGN KEY (STEP_EXECUTION_ID)
                                                   REFERENCES BATCH_STEP_EXECUTION(STEP_EXECUTION_ID)
) ENGINE=InnoDB;

CREATE TABLE BATCH_JOB_EXECUTION_CONTEXT  (
                                              JOB_EXECUTION_ID BIGINT NOT NULL PRIMARY KEY,
                                              SHORT_CONTEXT TEXT NOT NULL,
                                              SERIALIZED_CONTEXT TEXT DEFAULT NULL,
                                              CONSTRAINT JOB_EXEC_CTX_FK FOREIGN KEY (JOB_EXECUTION_ID)
                                                  REFERENCES BATCH_JOB_EXECUTION(JOB_EXECUTION_ID)
) ENGINE=InnoDB;

CREATE TABLE BATCH_STEP_EXECUTION_SEQ  (
    ID BIGINT NOT NULL
) ENGINE=InnoDB;

CREATE TABLE BATCH_JOB_EXECUTION_SEQ  (
    ID BIGINT NOT NULL
) ENGINE=InnoDB;

INSERT INTO BATCH_STEP_EXECUTION_SEQ VALUES(0);
INSERT INTO BATCH_JOB_EXECUTION_SEQ VALUES(0);

CREATE TABLE BATCH_JOB_SEQ (
    ID BIGINT NOT NULL
);

-- 초기값 삽입 (0부터 시작)
INSERT INTO BATCH_JOB_SEQ VALUES (0);

