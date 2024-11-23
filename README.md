# public-data-batch-importer

1. 쿼리문 작성
CREATE TABLE PublicData (
id INT AUTO_INCREMENT PRIMARY KEY, -- ID (자동 증가)
serviceName VARCHAR(255), -- 개방서비스명
serviceId VARCHAR(255), -- 개방서비스아이디
municipalityCode VARCHAR(255), -- 개방자치단체코드
managementNumber VARCHAR(255), -- 관리번호
licenseDate DATE, -- 인허가일자
licenseCancellationDate DATE, -- 인허가취소일자
businessStatusCode VARCHAR(255), -- 영업상태구분코드
businessStatusName VARCHAR(255), -- 영업상태명
detailedBusinessStatusCode VARCHAR(255), -- 상세영업상태코드
detailedBusinessStatusName VARCHAR(255), -- 상세영업상태명
closureDate DATE, -- 폐업일자
suspensionStartDate DATE, -- 휴업시작일자
suspensionEndDate DATE, -- 휴업종료일자
reopeningDate DATE, -- 재개업일자
addressPhone VARCHAR(255), -- 소재지전화
addressArea DECIMAL(10, 2), -- 소재지면적
addressPostcode VARCHAR(10), -- 소재지우편번호
fullAddress VARCHAR(255), -- 소재지전체주소
roadNameFullAddress VARCHAR(255), -- 도로명전체주소
roadNamePostcode VARCHAR(10), -- 도로명우편번호
businessName VARCHAR(255), -- 사업장명
lastModified TIMESTAMP, -- 최종수정시점
dataUpdateType VARCHAR(50), -- 데이터갱신구분
dataUpdateDate DATE, -- 데이터갱신일자
businessTypeName VARCHAR(255), -- 업태구분명
coordinateX DECIMAL(10, 6), -- 좌표정보(x)
coordinateY DECIMAL(10, 6), -- 좌표정보(y)
sanitationBusinessTypeName VARCHAR(255), -- 위생업태명
maleEmployeeCount INT, -- 남성종사자수
femaleEmployeeCount INT, -- 여성종사자수
businessAreaClassification VARCHAR(255), -- 영업장주변구분명
gradeClassification VARCHAR(255), -- 등급구분명
waterFacilityClassification VARCHAR(255), -- 급수시설구분명
totalEmployeeCount INT, -- 총직원수
headquartersEmployeeCount INT, -- 본사직원수
factoryOfficeStaffCount INT, -- 공장사무직직원수
factorySalesStaffCount INT, -- 공장판매직직원수
factoryProductionStaffCount INT, -- 공장생산직직원수
buildingOwnershipClassification VARCHAR(255), -- 건물소유구분명
guaranteeAmount DECIMAL(15, 2), -- 보증액
monthlyRent DECIMAL(15, 2), -- 월세액
multiUseFacilityFlag VARCHAR(10), -- 다중이용업소여부
totalFacilitySize DECIMAL(15, 2), -- 시설총규모
traditionalBusinessDesignationNumber VARCHAR(255), -- 전통업소지정번호
traditionalBusinessMainDish VARCHAR(255), -- 전통업소주된음식
website VARCHAR(255), -- 홈페이지

    -- 테이블에 대한 설명
    COMMENT='공공데이터 서비스 관련 정보 테이블'
);

-- 각 컬럼에 대한 설명 추가
COMMENT ON COLUMN PublicData.serviceName IS '개방서비스명';
COMMENT ON COLUMN PublicData.serviceId IS '개방서비스아이디';
COMMENT ON COLUMN PublicData.municipalityCode IS '개방자치단체코드';
COMMENT ON COLUMN PublicData.managementNumber IS '관리번호';
COMMENT ON COLUMN PublicData.licenseDate IS '인허가일자';
COMMENT ON COLUMN PublicData.licenseCancellationDate IS '인허가취소일자';
COMMENT ON COLUMN PublicData.businessStatusCode IS '영업상태구분코드';
COMMENT ON COLUMN PublicData.businessStatusName IS '영업상태명';
COMMENT ON COLUMN PublicData.detailedBusinessStatusCode IS '상세영업상태코드';
COMMENT ON COLUMN PublicData.detailedBusinessStatusName IS '상세영업상태명';
COMMENT ON COLUMN PublicData.closureDate IS '폐업일자';
COMMENT ON COLUMN PublicData.suspensionStartDate IS '휴업시작일자';
COMMENT ON COLUMN PublicData.suspensionEndDate IS '휴업종료일자';
COMMENT ON COLUMN PublicData.reopeningDate IS '재개업일자';
COMMENT ON COLUMN PublicData.addressPhone IS '소재지전화';
COMMENT ON COLUMN PublicData.addressArea IS '소재지면적';
COMMENT ON COLUMN PublicData.addressPostcode IS '소재지우편번호';
COMMENT ON COLUMN PublicData.fullAddress IS '소재지전체주소';
COMMENT ON COLUMN PublicData.roadNameFullAddress IS '도로명전체주소';
COMMENT ON COLUMN PublicData.roadNamePostcode IS '도로명우편번호';
COMMENT ON COLUMN PublicData.businessName IS '사업장명';
COMMENT ON COLUMN PublicData.lastModified IS '최종수정시점';
COMMENT ON COLUMN PublicData.dataUpdateType IS '데이터갱신구분';
COMMENT ON COLUMN PublicData.dataUpdateDate IS '데이터갱신일자';
COMMENT ON COLUMN PublicData.businessTypeName IS '업태구분명';
COMMENT ON COLUMN PublicData.coordinateX IS '좌표정보(x)';
COMMENT ON COLUMN PublicData.coordinateY IS '좌표정보(y)';
COMMENT ON COLUMN PublicData.sanitationBusinessTypeName IS '위생업태명';
COMMENT ON COLUMN PublicData.maleEmployeeCount IS '남성종사자수';
COMMENT ON COLUMN PublicData.femaleEmployeeCount IS '여성종사자수';
COMMENT ON COLUMN PublicData.businessAreaClassification IS '영업장주변구분명';
COMMENT ON COLUMN PublicData.gradeClassification IS '등급구분명';
COMMENT ON COLUMN PublicData.waterFacilityClassification IS '급수시설구분명';
COMMENT ON COLUMN PublicData.totalEmployeeCount IS '총직원수';
COMMENT ON COLUMN PublicData.headquartersEmployeeCount IS '본사직원수';
COMMENT ON COLUMN PublicData.factoryOfficeStaffCount IS '공장사무직직원수';
COMMENT ON COLUMN PublicData.factorySalesStaffCount IS '공장판매직직원수';
COMMENT ON COLUMN PublicData.factoryProductionStaffCount IS '공장생산직직원수';
COMMENT ON COLUMN PublicData.buildingOwnershipClassification IS '건물소유구분명';
COMMENT ON COLUMN PublicData.guaranteeAmount IS '보증액';
COMMENT ON COLUMN PublicData.monthlyRent IS '월세액';
COMMENT ON COLUMN PublicData.multiUseFacilityFlag IS '다중이용업소여부';
COMMENT ON COLUMN PublicData.totalFacilitySize IS '시설총규모';
COMMENT ON COLUMN PublicData.traditionalBusinessDesignationNumber IS '전통업소지정번호';
COMMENT ON COLUMN PublicData.traditionalBusinessMainDish IS '전통업소주된음식';
COMMENT ON COLUMN PublicData.website IS '홈페이지';
