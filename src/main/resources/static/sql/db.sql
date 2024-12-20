-- DB_BOOKLINK
DROP DATABASE IF EXISTS DB_BOOKLINK;
CREATE DATABASE DB_BOOKLINK;
USE DB_BOOKLINK;

DROP TABLE IF EXISTS TBL_USER;
CREATE TABLE TBL_USER(
	U_NO                INT AUTO_INCREMENT,
	U_ID                VARCHAR(50) UNIQUE,
	U_PW                VARCHAR(100) NOT NULL,
	U_PHONE             VARCHAR(20) NOT NULL,
	U_SEX               VARCHAR(2) NOT NULL,			-- M(남) W(여)
	U_AGE               VARCHAR(10) NOT NULL,       	-- 연령대
	U_ZIPCODE			VARCHAR(10) NOT NULL,       	-- 우편번호
	U_POST_ADDRESS      VARCHAR(100) NOT NULL,      	-- 우편번호 주소
	U_DETAIL_ADDRESS    VARCHAR(50),                	-- 상세 주소
	U_SNS_ID            VARCHAR(30),                	-- 구글, 네이버 ID
	U_ACTIVE            TINYINT DEFAULT 1,          	-- 0(삭제된 계정), 1(활동 계정), 2(휴면 계정)
	U_LAST_LOGINED		DATETIME DEFAULT NOW(),			-- 마지막으로 로그인 한 시간(휴면 계정 구분)
	U_REG_DATE          DATETIME DEFAULT NOW(),
	U_MOD_DATE          DATETIME DEFAULT NOW(),
	PRIMARY KEY(U_NO)
);
SELECT * FROM TBL_USER;

DROP TABLE IF EXISTS TBL_ADMIN;
CREATE TABLE TBL_ADMIN(
	A_NO                INT AUTO_INCREMENT,
	A_ID                VARCHAR(20) UNIQUE,
	A_PW                VARCHAR(100) NOT NULL,
	A_MAIL              VARCHAR(20) NOT NULL,
	A_PHONE             VARCHAR(20) NOT NULL,
	A_ROLE              VARCHAR(20) DEFAULT 'ADMIN',  -- 1(PRE_ADMIN), 2(ADMIN), 3(SUPER_ADMIN)
	A_REG_DATE          DATETIME DEFAULT NOW(),
	A_MOD_DATE          DATETIME DEFAULT NOW(),
	PRIMARY KEY(A_NO)
);
SELECT * FROM TBL_ADMIN;

DROP TABLE IF EXISTS TBL_WISHLIST;
CREATE TABLE TBL_WISHLIST(
	W_NO 			INT AUTO_INCREMENT,
	W_U_ID 			VARCHAR(50) NOT NULL,        -- 사용자 ID (TBL_USER의 U_ID와 연관)
	W_ISBN13 		VARCHAR(20) NOT NULL,        -- 책 고유번호 (ISBN13 NO)
    W_AUTHORS 		VARCHAR(50) NOT NULL,		 -- 저자
    W_NAME 			VARCHAR(50) NOT NULL,		 -- 도서명
	W_PUBLISHER 	VARCHAR(30) NOT NULL,		 -- 출판사
    W_BOOKIMAGEURL 	TEXT NOT NULL,		 		 -- 이미지 URL
    W_B_READ		TINYINT DEFAULT 0,			 -- 0 : 읽지않음, 1 : 읽음 
	W_REG_DATE 		DATETIME DEFAULT NOW(),
	W_MOD_DATE 		DATETIME DEFAULT NOW(),
   PRIMARY KEY(W_NO)
);
SELECT * FROM TBL_WISHLIST;
CREATE INDEX IDX_WISHLIST ON TBL_WISHLIST(W_ISBN13);

DROP TABLE IF EXISTS TBL_EVENT;
CREATE TABLE TBL_EVENT (
	E_NO 			INT AUTO_INCREMENT,          -- 이벤트 ID
    E_TITLE 		VARCHAR(100) NOT NULL,       -- 이벤트 제목
    E_IMAGE 		VARCHAR(255) NOT NULL,       -- 이벤트 이미지
    E_URL 			VARCHAR(255),                -- 이벤트 URL
    E_DESC 			TEXT,                        -- 이벤트 설명 (선택적)
    E_ACTIVE 		TINYINT DEFAULT 1,           -- 0(DELETE), 1(ACTIVE), 2(STOP), 3(자체광고 활성화), 4(자체광고 비활성화)
    E_START_DATE 	DATETIME,                    -- 이벤트 시작 날짜
    E_END_DATE 		DATETIME,                    -- 이벤트 종료 날짜
    E_REG_DATE 		DATETIME DEFAULT NOW(),      -- 등록 날짜
    E_MOD_DATE 		DATETIME DEFAULT NOW(),      -- 수정 날짜
    PRIMARY KEY (E_NO)
);
SELECT * FROM TBL_EVENT;

DROP TABLE IF EXISTS TBL_LIBRARY;
CREATE TABLE TBL_LIBRARY (
	L_NO				INT AUTO_INCREMENT,
    L_CODE				VARCHAR(10) NOT NULL,		-- 도서관 코드
    L_NAME				VARCHAR(30) NOT NULL,		-- 도서관 명
    L_ADDRESS			VARCHAR(50) NOT NULL,		-- 도서관 주소
    L_TEL				VARCHAR(60) NOT NULL,		-- 도서관 전화번호
    L_LATITUDE			DOUBLE NOT NULL,			-- 도서관 위도
    L_LONGITUDE			DOUBLE NOT NULL,			-- 도서관 경도
    L_HOMEPAGE			TEXT NOT NULL,				-- 도서관 홈페이지 URL
    L_CLOSED			TEXT NOT NULL,				-- 도서관 휴관일
    L_OPERATION_TIME	TEXT NOT NULL,				-- 도서관 운영시간
    L_REG_DATE 			DATETIME DEFAULT NOW(),     -- 등록 날짜
    L_MOD_DATE 			DATETIME DEFAULT NOW(),     -- 수정 날짜
    PRIMARY KEY (L_NO)
);
SELECT * FROM TBL_LIBRARY;
CREATE INDEX IDX_LIBRARY ON TBL_LIBRARY(L_NAME);

DROP TABLE IF EXISTS TBL_MY_LIBRARY;
CREATE TABLE TBL_MY_LIBRARY(
	ML_NO 			INT AUTO_INCREMENT,
	ML_U_ID 		VARCHAR(50) NOT NULL,			-- 사용자의 U_ID
	ML_L_CODE 		INT NOT NULL,                	-- 도서관의 L_CODE
	ML_REG_DATE 	DATETIME DEFAULT NOW(),
	ML_MOD_DATE 	DATETIME DEFAULT NOW(),
   PRIMARY KEY(ML_NO)
);
SELECT * FROM TBL_MY_LIBRARY;

DROP TABLE IF EXISTS TBL_API;
CREATE TABLE TBL_API (
	A_NO		INT AUTO_INCREMENT,
	A_IP    	VARCHAR(20) NOT NULL,	
	A_COUNT		INT DEFAULT 1,
    A_MOD_DATE	DATETIME(6) DEFAULT NOW(6),
    PRIMARY KEY(A_NO)
);
SELECT * FROM TBL_API;


DROP TRIGGER IF EXISTS API_DOUBLE_CHECK;
DELIMITER $$
CREATE TRIGGER API_DOUBLE_CHECK
BEFORE UPDATE ON TBL_API 
FOR EACH ROW
BEGIN
    IF TIMEDIFF(NOW(6), OLD.A_MOD_DATE) < '00:00:01.000000' THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = '비정상적인 호출입니다.';
    END IF;
END $$
DELIMITER ;

show triggers;
-- 매일 자정 이벤트 스케줄링
DELIMITER $$
CREATE EVENT IF NOT EXISTS deactivate_expired_events
ON SCHEDULE EVERY 1 DAY 
DO
BEGIN
    -- 종료일이 지나면 자동으로 비활성화 처리
    UPDATE TBL_EVENT
    SET E_ACTIVE = CASE
        WHEN E_ACTIVE = 1 THEN 2  -- 홈 광고: 1 -> 2
        WHEN E_ACTIVE = 3 THEN 4  -- 자체 광고: 3 -> 4
        ELSE E_ACTIVE
    END
    WHERE E_END_DATE < NOW() AND E_ACTIVE IN (1, 3);  	-- 종료일이 지나고 홈 광고(1) 또는 자체 광고(3)만 업데이트
END $$
DELIMITER ;

SHOW VARIABLES LIKE 'event_scheduler';
SHOW EVENTS;											-- 이벤트 스케줄링 확인
DROP EVENT IF EXISTS deactivate_expired_events;			-- 이벤트 스케줄링 비활성화

select * from tbl_user;
LOAD DATA LOCAL INFILE 'C:/Users/Manic-063/Desktop/tbl_user.csv'
INTO TABLE tbl_user
FIELDS TERMINATED BY ',' 
LINES TERMINATED BY '\r\n'
IGNORE 1 ROWS
(U_ID, U_PW, U_PHONE, U_SEX, U_AGE, U_ZIPCODE, U_POST_ADDRESS, U_DETAIL_ADDRESS, U_SNS_ID, U_ACTIVE, U_LAST_LOGINED, U_REG_DATE, U_MOD_DATE);

select * from TBL_MY_LIBRARY;
LOAD DATA LOCAL INFILE 'C:/Users/Manic-063/Desktop/tbl_my_library.csv'
INTO TABLE TBL_MY_LIBRARY
FIELDS TERMINATED BY ',' 
LINES TERMINATED BY '\r\n'
IGNORE 1 ROWS
(ML_U_ID, ML_L_CODE, ML_REG_DATE, ML_MOD_DATE);

select * from TBL_WISHLIST;
LOAD DATA LOCAL INFILE 'C:/Users/Manic-063/Desktop/wish.csv'
INTO TABLE TBL_WISHLIST
FIELDS TERMINATED BY ',' 
LINES TERMINATED BY '\r\n'
IGNORE 1 ROWS
(W_U_ID, W_ISBN13, W_AUTHORS, W_NAME, W_PUBLISHER, W_BOOKIMAGEURL, W_B_READ, W_REG_DATE, W_MOD_DATE);


CREATE TABLE TBL_LOGS (
    L_NO INT AUTO_INCREMENT PRIMARY KEY,
    TIME TIMESTAMP,
    LEVEL VARCHAR(10),
    LOGGER VARCHAR(255),
    MESSAGE TEXT
);
select * from TBL_LOGS;
