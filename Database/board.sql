/*
제작일: 2020.07.21
유형: Table
설명: 게시판 그룹 정보를 담고 있는 table
예시: 메뉴바의 A를 선택했을 때 a, b, c의 게시판이 나온다면 a, b, c 게시판의 그룹에 해당하는 A
*/
CREATE OR REPLACE TABLE `board_group` (
    board_group_no INT PRIMARY KEY AUTO_INCREMENT,
    board_group_name VARCHAR(50),
    board_group_order INT UNSIGNED DEFAULT 0
);

/*============================================================================*/

/*
제작일: 2020.07.23
유형: Table
설명: 게시판의 정보를 담고있는 테이블
이 테이블이 각각의 게시판 테이블과 외래키로 엮이지는 않음 | 게시판의 정보를 한눈에 보기 위함
*/
CREATE OR REPLACE TABLE `board` (
    board_no INT PRIMARY KEY AUTO_INCREMENT,
    board_group_no INT,
    board_name VARCHAR(256),    -- 실제 보여줄 이름
    board_table_name VARCHAR(256) UNIQUE KEY NOT NULL ,   -- 테이블 이름
    board_create_datetime DATETIME DEFAULT CURRENT_TIMESTAMP,
    board_desc TEXT,
    FOREIGN KEY (board_group_no) REFERENCES board_group(board_group_no) ON UPDATE CASCADE ON DELETE CASCADE
);

/*============================================================================*/

/*
제작일: 2020.07.23
유형: Table
설명: 프로그래밍 게시판 테이블
*/
CREATE TABLE `program_board` (
    program_board_no INT PRIMARY KEY AUTO_INCREMENT,
    member_id VARCHAR(20) ,
    program_board_title VARCHAR(200) NOT NULL ,
    program_board_datetime DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ,
    program_board_track INT ,
    program_board_content TEXT,
    program_board_hit INT NOT NULL DEFAULT 0,
    program_board_update_datetime DATETIME,
    program_board_like INT NOT NULL DEFAULT 0,
    program_board_dislike INT NOT NULL DEFAULT 0,
    FOREIGN KEY (member_id) REFERENCES member(member_id) ON UPDATE CASCADE ON DELETE SET NULL ,
    FOREIGN KEY (program_board_track) REFERENCES category(category_no) ON UPDATE CASCADE ON DELETE SET NULL
);

ALTER TABLE program_board MODIFY COLUMN program_board_track INT;
ALTER TABLE program_board ADD FOREIGN KEY (program_board_track) REFERENCES category(category_no) ON UPDATE CASCADE ON DELETE SET NULL ;

CREATE TABLE `program_comment` (
    program_comment_no INT PRIMARY KEY AUTO_INCREMENT ,
    program_board_no INT ,
    member_id VARCHAR(20) ,
    program_comment_content TEXT ,
    program_comment_like INT NOT NULL DEFAULT 0 ,
    program_comment_dislike INT NOT NULL DEFAULT 0 ,
    program_comment_del TINYINT(1) NOT NULL DEFAULT 0 ,       -- 삭제 여부
    FOREIGN KEY (program_board_no) REFERENCES program_board(program_board_no) ON DELETE CASCADE ,
    FOREIGN KEY (member_id) REFERENCES member(member_id) ON UPDATE CASCADE ON DELETE SET NULL
);


/*
제작일: 2020.07.23
유형: Table
설명: 프로그래밍 게시판의 게시글 좋아요 테이블 || 해당 글의 좋아요 또는 싫어요 수를 볼 수 있음
*/
CREATE TABLE `program_board_like` (
    program_board_like_no INT PRIMARY KEY AUTO_INCREMENT ,
    program_board_no INT NOT NULL,
    member_id VARCHAR(20),
    program_board_like_check TINYINT(1) NOT NULL ,
    program_board_like_datetime DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ,
    UNIQUE KEY (program_board_no, member_id) ,
    FOREIGN KEY (program_board_no) REFERENCES program_board(program_board_no) ON DELETE CASCADE ,
    FOREIGN KEY (member_id) REFERENCES member(member_id) ON UPDATE CASCADE ON DELETE SET NULL
);

/*사용 예*/

/*============================================================================*/

/*
제작일: 2020.07.23
유형: Table
설명: 게시판의 정보를 담고있는 테이블
*/
CREATE TABLE `notice` (
    notice_no INT PRIMARY KEY AUTO_INCREMENT,
    member_id VARCHAR(20) ,
    notice_title VARCHAR(200) NOT NULL ,
    notice_content TEXT,
    notice_datetime DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ,
    FOREIGN KEY (member_id) REFERENCES member(member_id) ON UPDATE CASCADE ON DELETE SET NULL
);


/*============================================================================*/

CREATE TABLE `free_board` (
    free_board_no INT PRIMARY KEY AUTO_INCREMENT,
    member_id VARCHAR(20) ,
    free_board_title VARCHAR(200) NOT NULL ,
    free_board_content TEXT,
    free_board_hit INT NOT NULL DEFAULT 0,
    free_board_update_datetime DATETIME,
    free_board_datetime DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ,
    free_board_like INT NOT NULL DEFAULT 0,
    free_board_dislike INT NOT NULL DEFAULT 0,
    FOREIGN KEY (member_id) REFERENCES member(member_id) ON UPDATE CASCADE ON DELETE SET NULL
);

CREATE TABLE `free_comment` (
    free_comment_no INT PRIMARY KEY AUTO_INCREMENT ,
    free_board_no INT ,
    member_id VARCHAR(20) ,
    free_comment_content TEXT ,
    free_comment_like INT NOT NULL DEFAULT 0 ,
    free_comment_dislike INT NOT NULL DEFAULT 0 ,
    free_comment_del TINYINT(1) NOT NULL DEFAULT 0 ,       -- 삭제 여부
    FOREIGN KEY (free_board_no) REFERENCES free_board(free_board_no) ON DELETE CASCADE ,
    FOREIGN KEY (member_id) REFERENCES member(member_id) ON UPDATE CASCADE ON DELETE SET NULL
);

CREATE TABLE `free_board_like` (
    free_board_like_no INT PRIMARY KEY AUTO_INCREMENT ,
    free_board_no INT NOT NULL,
    member_id VARCHAR(20),
    free_board_like_check TINYINT(1) NOT NULL ,
    free_board_like_datetime DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ,
    UNIQUE KEY (free_board_no, member_id) ,
    FOREIGN KEY (free_board_no) REFERENCES free_board(free_board_no) ON DELETE CASCADE ,
    FOREIGN KEY (member_id) REFERENCES member(member_id) ON UPDATE CASCADE ON DELETE SET NULL
);
ALTER TABLE free_board_like MODIFY COLUMN free_board_like_check BOOL;

/*============================================================================*/

/*
제작일: 2020.07.27
유형: Trigger
설명: 각 게시판의 like count를 자동으로 증가/감소 시킴
*/
DELIMITER //
CREATE OR REPLACE TRIGGER TRG_FREE_BOARD_LIKE_INSERT
AFTER INSERT ON free_board_like
FOR EACH ROW
    BEGIN
        IF NEW.free_board_like_check THEN
            UPDATE free_board
            SET free_board_like = free_board_like + 1
            WHERE free_board_no = NEW.free_board_no;
        ELSE
            UPDATE free_board
            SET free_board_dislike = free_board_dislike + 1
            WHERE free_board_no = NEW.free_board_no;
        END IF;
    END //
DELIMITER ;

DELIMITER //
CREATE OR REPLACE TRIGGER TRG_FREE_BOARD_LIKE_UPDATE
AFTER UPDATE ON free_board_like
FOR EACH ROW
    BEGIN
        DECLARE update_like_diff INT;
        SET update_like_diff = NEW.free_board_like_check - OLD.free_board_like_check;

        UPDATE free_board
        SET free_board_like = free_board_like + update_like_diff,
            free_board_dislike = free_board_dislike - update_like_diff
        WHERE free_board_no = NEW.free_board_no;
    END //
DELIMITER ;

DELIMITER //
CREATE OR REPLACE TRIGGER TRG_FREE_BOARD_LIKE_DELETE
AFTER DELETE ON free_board_like
FOR EACH ROW
    BEGIN
        IF OLD.free_board_like_check THEN
            UPDATE free_board
            SET free_board_like = free_board_like - 1
            WHERE free_board_no = OLD.free_board_no;
        ELSE
            UPDATE free_board
            SET free_board_dislike = free_board_dislike - 1
            WHERE free_board_no = OLD.free_board_no;
        END IF;
    END //
DELIMITER ;

DELIMITER //
CREATE OR REPLACE TRIGGER TRG_PROGRAM_BOARD_LIKE_INSERT
AFTER INSERT ON program_board_like
FOR EACH ROW
    BEGIN
        IF NEW.program_board_like_check THEN
            UPDATE program_board
            SET program_board_like = program_board_like + 1
            WHERE program_board_no = NEW.program_board_no;
        ELSE
            UPDATE program_board
            SET program_board_dislike = program_board_dislike + 1
            WHERE program_board_no = NEW.program_board_no;
        END IF;
    END //
DELIMITER ;

DELIMITER //
CREATE OR REPLACE TRIGGER TRG_PROGRAM_BOARD_LIKE_UPDATE
AFTER UPDATE ON program_board_like
FOR EACH ROW
    BEGIN
        DECLARE update_like_diff INT;
        SET update_like_diff = NEW.program_board_like_check - OLD.program_board_like_check;

        UPDATE program_board
        SET program_board_like = program_board_like + update_like_diff,
            program_board_dislike = program_board_dislike - update_like_diff
        WHERE program_board_no = NEW.program_board_no;
    END //
DELIMITER ;

DELIMITER //
CREATE OR REPLACE TRIGGER TRG_PROGRAM_BOARD_LIKE_DELETE
AFTER DELETE ON program_board_like
FOR EACH ROW
    BEGIN
        IF OLD.program_board_like_check THEN
            UPDATE program_board
            SET program_board_like = program_board_like - 1
            WHERE program_board_no = OLD.program_board_no;
        ELSE
            UPDATE program_board
            SET program_board_dislike = program_board_dislike - 1
            WHERE program_board_no = OLD.program_board_no;
        END IF;
    END //
DELIMITER ;

/*사용 예*/
-- 게시글 생성
INSERT INTO free_board(member_id, free_board_title, free_board_content)
VALUES ('123', '트리거 테스트', '8조화이팅ㅇ너ㅏㄹㅇ너리ㅏ어리ㅏㄴㅁ러니;럼ㅇ니렁ㅁ니러이ㅣㅏ리ㅏ렁니ㅏ렁ㄴ미ㅏㅓㅣㅏㄹㄹ이런ㅁ이ㅏ런ㅁ이ㅏ러이리ㅏㄹ');

-- 좋아요 입력(중복 시 수정)
INSERT INTO free_board_like(free_board_no, member_id, free_board_like_check)
VALUES ('3', 'zzz', 1)
ON DUPLICATE KEY UPDATE free_board_like_check = 0;

-- 좋아요 삭제
DELETE FROM free_board_like WHERE free_board_no = 3;

/*============================================================================*/

/*
제작일: 2020.08.10
유형: Table
설명: 조회수를 증가시키기 위한 테이블
*/

CREATE OR REPLACE TABLE board_hit (
    board_hit_no INT PRIMARY KEY AUTO_INCREMENT ,
    board_name VARCHAR(200) NOT NULL ,  -- 게시판 이름 ex) free_board, program_board
    board_no INT NOT NULL ,     -- 게시글 번호
    board_hit_ip VARCHAR(15) ,  -- 아이피 번호
    board_hit_datetime DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ,
    UNIQUE KEY (board_name, board_no, board_hit_ip)
);

/*
제작일: 2020.08.10
유형: Trigger
설명: 게시판 별 자동 조회수 증가 트리거
*/
DELIMITER //
CREATE OR REPLACE TRIGGER TRG_BOARD_HIT_INCREMENT
AFTER INSERT ON board_hit
FOR EACH ROW
    BEGIN
        IF NEW.board_name = 'free_board' THEN
            UPDATE free_board
                SET free_board_hit = free_board_hit + 1
            WHERE free_board_no = NEW.board_no;
        ELSEIF NEW.board_name = 'program_board' THEN
            UPDATE program_board
                SET program_board_hit = program_board_hit + 1
            WHERE program_board_no = NEW.board_no;
        -- ELSEIF  THEN     -- Board 추가가        END IF;
        END IF;
    END //
DELIMITER ;

SELECT * FROM free_board;
/*사용 예*/
INSERT INTO board_hit(board_name, board_no, board_hit_ip)
VALUES ('free_board', 31, '213.119.232.109');