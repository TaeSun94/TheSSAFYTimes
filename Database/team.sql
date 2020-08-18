
/*
제작일: 2020.08.05
유형: Table
설명: 팀매칭을 위한 테이블
 */
CREATE TABLE team_board (
    team_board_no INT PRIMARY KEY AUTO_INCREMENT ,
    member_id VARCHAR(20) , -- 제안자
    team_board_category INT ,         -- 프로젝트 분야
    team_board_title VARCHAR(200) ,     -- 프로젝트 제목
    team_board_content  TEXT ,      -- 프로젝트 이름
    team_board_start_datetime DATETIME ,
    team_board_end_datetime DATETIME ,
    team_board_total_count INT ,
    team_board_front_remain_count INT ,
    team_board_back_remain_count INT ,
    team_board_datetime DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ,
    FOREIGN KEY (member_id) REFERENCES member(member_id) ON UPDATE CASCADE ON DELETE CASCADE
);

/*
제작일: 2020.08.05
유형: Table
설명: 팀 지원 상태 테이블
 */
CREATE TABLE team_apply (
    team_apply_no INT PRIMARY KEY AUTO_INCREMENT ,
    team_board_no INT NOT NULL ,
    member_id VARCHAR(20) ,
    team_apply_content TEXT ,
    team_apply_position VARCHAR(20) ,       -- 지원 포지션 Front/Back
    team_apply_status BOOL NOT NULL DEFAULT FALSE ,
    team_apply_datetime DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ,
    UNIQUE KEY (team_board_no, member_id) ,
    FOREIGN KEY (team_board_no) REFERENCES team_board(team_board_no) ON UPDATE CASCADE ON DELETE CASCADE ,
    FOREIGN KEY (member_id) REFERENCES member(member_id) ON UPDATE CASCADE ON DELETE CASCADE
);


/*
제작일: 2020.08.11
유형: Trigger
설명: 팀매칭 성공 시 필요 지원자 수를 빼는 트리거
 */
DELIMITER //
CREATE OR REPLACE TRIGGER TRG_TEAM_REQUIRE_DECREASE
AFTER UPDATE ON team_apply
FOR EACH ROW
BEGIN
    IF OLD.team_apply_status = FALSE && NEW.team_apply_status = TRUE THEN
        IF NEW.team_apply_position = 'F' THEN
            UPDATE team_board
                SET team_board_front_remain_count = team_board_front_remain_count - 1
            WHERE team_board_no = NEW.team_board_no;
        ELSEIF NEW.team_apply_position = 'B' THEN
            UPDATE team_board
                SET team_board_back_remain_count = team_board_back_remain_count - 1
            WHERE team_board_no = NEW.team_board_no;
        END IF ;
    END IF ;

END //
DELIMITER ;

SELECT * FROM team_apply;
SELECT * FROM team_board;


SELECT tb.team_board_no, tb.team_board_title, ta.team_apply_position, ta.team_apply_status
FROM team_board tb
    INNER JOIN team_apply ta ON tb.team_board_no = ta.team_board_no
WHERE ta.member_id = ?;

SELECT *
FROM ?
-- LIMIT a, b       -- b: 한 페이지에 보여질 게시글 수      a: 시작 행 번호
-- a = b * (페이지번호 - 1)
;

SELECT * FROM team_board;

DELETE FROM team_board WHERE team_board_no = 15;