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


SELECT * FROM team_apply;