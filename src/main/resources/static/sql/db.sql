-- DB_BOOKLINK
DROP DATABASE IF EXISTS DB_BOOKLINK;
CREATE DATABASE DB_BOOKLINK;
USE DB_BOOKLINK;

DROP TABLE IF EXISTS TBL_USER;
CREATE TABLE TBL_USER(
	U_NO                INT AUTO_INCREMENT,
	U_ID                VARCHAR(20) UNIQUE,
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
	A_ROLE              VARCHAR(20) DEFAULT 'PRE_ADMIN',  -- 1(PRE_ADMIN), 2(ADMIN), 3(SUPER_ADMIN)
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

DROP TABLE IF EXISTS TBL_JWT;
CREATE TABLE TBL_JWT (
	J_NO 			INT AUTO_INCREMENT,          
    J_REG_DATE 		DATETIME DEFAULT NOW(),      
    J_MOD_DATE 		DATETIME DEFAULT NOW(),      -- 수정 날짜
    PRIMARY KEY (J_NO)
);
SELECT * FROM TBL_JWT;

DROP TABLE IF EXISTS TBL_LIBRARY;
CREATE TABLE TBL_LIBRARY (
	L_NO				INT AUTO_INCREMENT,
    L_CODE				INT NOT NULL,				-- 도서관 코드
    L_NAME				VARCHAR(30) NOT NULL,		-- 도서관 명
    L_ADDRESS			VARCHAR(50) NOT NULL,		-- 도서관 주소
    L_TEL				VARCHAR(20) NOT NULL,		-- 도서관 전화번호
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
alter table TBL_LIBRARY modify L_TEL VARCHAR(60) NOT NULL;
SELECT count(*) FROM TBL_LIBRARY where L_ADDRESS LIKE '%서울%';

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
	A_IP    	VARCHAR(20) UNIQUE,	
	A_COUNT		INT DEFAULT 1,
    A_MOD_DATE	DATETIME DEFAULT NOW(6),
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
        SET MESSAGE_TEXT = '장난치지 말아라~~~';
    END IF;
END $$
DELIMITER ;

