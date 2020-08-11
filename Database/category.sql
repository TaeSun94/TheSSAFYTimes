/*
제작일: 2020.07.27
유형: Table
설명: 카테고리를 관리하는 테이블
*/
CREATE OR REPLACE TABLE category_manage (
    category_manage_no INT PRIMARY KEY AUTO_INCREMENT ,
    category_manage_name VARCHAR(200) NOT NULL , -- 지역, 반, 트랙, 기수, 기사 종류 와 같이 실제 화면에 표시할 이름
    category_manage_description VARCHAR(200) ,       -- 간단 설명
    category_datetime DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    UNIQUE KEY (category_manage_name)
);

/*
제작일: 2020.07.27
유형: Table
설명: 카테고리 테이블
예시: name 값이 state 인 value는 서울특별시, 대전광역시, 경상북도, 광주광역시 등이 있고
이를 부모로 하는 key값이 sigungu인 value 종로구, 유성구, 구미시, 광산구
*/
CREATE OR REPLACE TABLE category (
    category_no INT PRIMARY KEY AUTO_INCREMENT ,
    category_manage_no INT NOT NULL ,     -- 카테고리 관리 번호
    category_name VARCHAR(200) NOT NULL , -- 1학기, 2학기 | 1반, 2반, 3반, 4반 | Python, Java, Embedded | 사회, 과학 과 같이 화면에 보여줄 이름
    category_no_parent INT , -- 부모 카테고리 (부모가 없을 시 NULL) (예: 광주광역시를 선택하면 동구, 서구, 남구, 북구, 광산구가 나옴)
    category_order INT DEFAULT 0 ,     -- 정렬 순서(order by 사용 | 꼭 이것으로 정렬해야되는 건 아님), NULL일 시 화면에 표시되지 않음
    FOREIGN KEY (category_manage_no) REFERENCES category_manage(category_manage_no) ON UPDATE CASCADE ON DELETE CASCADE ,
    FOREIGN KEY (category_no_parent) REFERENCES category(category_no) ON UPDATE CASCADE ON DELETE CASCADE
);
-- 인덱스 삭제(유니크 삭제 시 사용)
-- DROP INDEX category_manage_no ON category;


/*사용 예*/
SELECT *
FROM category
WHERE category_manage_no = 5    -- 예) m, 시군구 카테고리의 모든 값이 나옴
    AND category_order IS NOT NULL  -- NULL은 더이상 쓰이지 않는 카테고리
-- AND category_no_parent = 2  -- 예) category_no가 1인 gwangju가 부모인 모든 카테고리 값이 나옴
ORDER BY category_order     -- 문자열 형식의 숫자를 정렬할 때 유리
;

SELECT c.category_no, cm.category_manage_name, c.category_name, (SELECT category_name FROM category WHERE category_no = c.category_no_parent) category_name_parent, c.category_order
FROM category c
    INNER JOIN category_manage cm ON c.category_manage_no = cm.category_manage_no;

/*사용 예*/
SELECT c.category_no, c.category_name
FROM category c
    INNER JOIN category_manage cm ON c.category_manage_no = cm.category_manage_no
WHERE cm.category_manage_name = ?       -- 카테고리 메니저의 이름이 들어가야 됨(번호로 검색하려면 cm.category_manage_no 로 수정)
AND c.category_no_parent <=> ?       -- 부모 카테고리의 번호가 들어가야 됨 (카테고리를 선택했을 때 하위 카테고리를 요청할 때)
AND c.category_order IS NOT NULL    -- order가 null이면 더이상 사용하지 않는 카테고리
ORDER BY c.category_order;          -- 순서 정렬

/*============================================================================*/

/*
제작일: 2020.07.28
유형: Procedure
설명: 카테고리 간편 등록 프로시저 카테고리 매니저 이름, 카테고리 이름, 카테고리 부모 번호, 순서를 받아 카테고리 매니저 이름이 존재하지 않으면
자동으로 생성해준다.
*/
DELIMITER //
CREATE OR REPLACE PROCEDURE PROC_INSERT_CATEGORY (IN _category_manage_name VARCHAR(200), IN _category_name VARCHAR(200), IN _category_no_parent INT, _category_order INT)
BEGIN
    DECLARE cmn_no INT;
    SELECT category_manage_no into cmn_no FROM category_manage WHERE category_manage_name = _category_manage_name;
    IF cmn_no IS NULL THEN
        INSERT INTO category_manage(category_manage_name) VALUE (_category_manage_name);
        SELECT category_manage_no INTO cmn_no FROM category_manage WHERE category_manage_name = _category_manage_name;
    END IF ;
    INSERT INTO category(category_manage_no, category_name, category_no_parent, category_order) VALUE (cmn_no, _category_name, _category_no_parent, _category_order);
END //
DELIMITER ;

/*사용 예*/
CALL PROC_INSERT_CATEGORY('AllTrack', '기타', NULL, 100);

SELECT * FROM category_manage;
SELECT * FROM category;