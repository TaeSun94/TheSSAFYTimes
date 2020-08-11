/*
제작일: 2020.07.21
유형: table
설명: 회원 정보를 저장하는 table
*/
CREATE TABLE `member` (
	`member_no` INT PRIMARY KEY AUTO_INCREMENT,
	`member_email` VARCHAR(320) UNIQUE KEY NOT NULL,
	`member_id` VARCHAR(20) UNIQUE KEY ,
	`member_pw` VARCHAR(60) NOT NULL,
	`member_first_name` VARCHAR(100),
	`member_last_name` VARCHAR(100),
	`member_phone` VARCHAR(32),
	`member_region` INT,
	`member_track` INT,
	`member_unit` INT,
	`member_intro` VARCHAR(500),
	`member_desc` TEXT,
    `member_article` INT NOT NULL DEFAULT 0,
	`member_follow` INT NOT NULL DEFAULT 0,
	`member_follower` INT NOT NULL DEFAULT 0,
	`member_interested` TEXT ,
	`member_skill` TEXT,
	`member_auth` VARCHAR(20),
	`member_auth_status` BOOL NOT NULL DEFAULT FALSE,
	`member_auth_datetime` DATETIME,
	`member_address` VARCHAR(256),
	`member_datetime` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ,
	FOREIGN KEY (member_region) REFERENCES category(category_no) ON UPDATE CASCADE ON DELETE SET NULL ,
	FOREIGN KEY (member_track) REFERENCES category(category_no) ON UPDATE CASCADE ON DELETE SET NULL ,
	FOREIGN KEY (member_unit) REFERENCES category(category_no) ON UPDATE CASCADE ON DELETE SET NULL
);

/*카테고리 no 대신 이름으로 회원 정보 불러오기*/
SELECT member_no, member_email, member_id, member_pw, member_first_name, member_last_name, member_phone, cr.category_name member_region, ct.category_name member_track, cu.category_name member_unit, member_intro, member_desc, member_article, member_follow, member_follower, member_interested, member_skill, member_auth, member_auth_status, member_auth_datetime, member_address, member_datetime, member_no, member_id, member_pw, member_email, member_first_name, member_last_name, member_phone, cr.category_name, ct.category_name, cu.category_name, member_intro, member_desc, member_article, member_follow, member_follower, member_interested, member_skill, member_auth, member_auth_status, member_auth_datetime, member_address, member_datetime
FROM member
    LEFT OUTER JOIN category cr ON member_region = cr.category_no
    LEFT OUTER JOIN category ct ON member_track = ct.category_no
    LEFT OUTER JOIN category cu ON member_unit = cu.category_no
-- WHERE m.member_id = ?
;


SELECT member_no, member_email, member_id, member_pw, member_first_name, member_last_name, member_phone, (SELECT category_name FROM category WHERE category_no = member.member_region) member_region, (SELECT category_name FROM category WHERE category_no = member.member_track) member_track, (SELECT category_name FROM category WHERE category_no = member.member_unit) member_unit, member_intro, member_desc, member_article, member_follow, member_follower, member_interested, member_skill, member_auth, member_auth_status, member_auth_datetime, member_address, member_datetime
FROM member
-- WHERE m.meber_id = ?
;
SELECT * FROM category;



SELECT * FROM member;

START TRANSACTION ;
ALTER TABLE member MODIFY member_region INT;
ALTER TABLE member MODIFY member_unit INT;
ALTER TABLE member MODIFY member_track INT;
UPDATE member
    SET member_region = NULL,
        member_unit = NULL,
        member_track = NULL
WHERE member_region = 0;
COMMIT ;
ROLLBACK ;


SELECT * FROM  member;

/*============================================================================*/

/*
제작일: 2020.07.22
유형: Table
설명: 방문 기록 테이블
예시: A가 B의 블로그에 아무리 많이 방문 하더라도 하루에 최초 접속 한번만 찍힘
 */
CREATE TABLE IF NOT EXISTS `visit_log` (
	`visit_log_no` INT PRIMARY KEY AUTO_INCREMENT,
	`member_id_from` VARCHAR(20),
	`member_id_to` VARCHAR(20) NOT NULL ,
	`visit_log_date` DATE DEFAULT CURRENT_DATE,
	`visit_log_time` TIME DEFAULT CURRENT_TIME,
	UNIQUE KEY (member_id_from, member_id_to, visit_log_date),
	FOREIGN KEY (`member_id_from`) REFERENCES `member`(`member_id`) ON UPDATE CASCADE ON DELETE SET NULL ,
	FOREIGN KEY (`member_id_to`) REFERENCES `member`(`member_id`) ON UPDATE CASCADE ON DELETE CASCADE
);

SELECT * FROM visit_log;
SELECT * FROM member;
/*사용 예*/
-- 회원 정보와 함께 조회
SELECT m.*, IFNULL(v.today, 0) today, IFNULL(g.total, 0) total
FROM
    member m
    LEFT JOIN (
        SELECT member_id_to, count(member_id_to) today
        FROM visit_log
        WHERE visit_log_date = CURRENT_DATE
        GROUP BY member_id_to) v
    ON m.member_id = v.member_id_to
    LEFT JOIN (
        SELECT member_id_to, count(member_id_to) total
        FROM visit_log
        GROUP BY member_id_to) g
    ON m.member_id = g.member_id_to
-- WHERE m.member_id = 'ssafy'  -- 회원 조회
;

-- 방문자 수만 조회
SELECT member_id_to, count(member_id_to) today
FROM visit_log
WHERE visit_log_date = CURRENT_DATE
GROUP BY member_id_to;

SELECT member_id_to, count(member_id_to) total
FROM visit_log
GROUP BY member_id_to;
/*============================================================================*/

/*
제작일: 2020.07.23
유형: Table
설명: 팔로워 테이블 나를 누가 팔로우하고 내가 누구를 팔로우하는지 알 수 있다.
예시: A는 B를 단지 한번만 팔로우 할 수 있다.
 */
CREATE TABLE `follow` (
    `follow_no` INT PRIMARY KEY AUTO_INCREMENT,
    `member_id_from` VARCHAR(20) NOT NULL ,
    `member_id_to` VARCHAR(20) NOT NULL ,
    `follow_datetime` DATETIME DEFAULT CURRENT_TIMESTAMP,
    UNIQUE KEY (member_id_from, member_id_to),
    FOREIGN KEY (`member_id_from`) REFERENCES `member`(member_id) ON UPDATE CASCADE ON DELETE CASCADE ,
    FOREIGN KEY (`member_id_to`) REFERENCES `member`(`member_id`) ON UPDATE CASCADE ON DELETE CASCADE
);


/*사용 예*/

-- 회원 정보와 함께 팔로우, 팔로워 구하기
SELECT m.*, IFNULL(f.follow, 0) follow, IFNULL(g.follower, 0) follower
FROM
    member m
    INNER JOIN
    (SELECT member_id_from, count(member_id_from) follow FROM follow GROUP BY member_id_from) f ON m.member_id = f.member_id_from
    INNER JOIN
    (SELECT member_id_to, count(member_id_to) follower FROM follow GROUP BY member_id_to) g ON m.member_id = g.member_id_to
;

-- 내가 팔로우한 회원 정보
SELECT m.*
FROM  member m
    INNER JOIN (SELECT member_id_to FROM follow WHERE member_id_from = ?) f ON f.member_id_to = m.member_id;

-- 나를 팔로우한 회원 정보
SELECT m.*
FROM  member m
    INNER JOIN (SELECT member_id_from FROM follow WHERE member_id_to = ?) f ON f.member_id_from = m.member_id;

/*
제작일: 2020.08.03
유형: Trigger
설명: 팔로우 시, 팔로우한 사람과 팔로우 된 사람의 팔로워 / 팔로우 수를 증가시킨다.
 */
DELIMITER //
CREATE OR REPLACE TRIGGER TRG_FOLLOW_INCREMENT
AFTER INSERT ON follow
FOR EACH ROW
    BEGIN
        UPDATE member m
            SET m.member_follow = (SELECT COUNT(f.member_id_to) FROM follow f WHERE f.member_id_from = NEW.member_id_from)
        WHERE m.member_id = NEW.member_id_from;

        UPDATE member m
            SET m.member_follower = (SELECT COUNT(f.member_id_from) FROM follow f WHERE f.member_id_to = NEW.member_id_to)
        WHERE m.member_id = NEW.member_id_to;
    END //
DELIMITER ;


/*
제작일: 2020.08.03
유형: Trigger
설명: 팔로우 해제 시, 팔로우한 사람과 팔로우 된 사람의 팔로워 / 팔로우 수를 감소시킨다.
 */
DELIMITER //
CREATE OR REPLACE TRIGGER TRG_FOLLOW_DECREMENT
AFTER DELETE ON follow
FOR EACH ROW
    BEGIN
        UPDATE member m
            SET m.member_follow = (SELECT COUNT(f.member_id_to) FROM follow f WHERE f.member_id_from = OLD.member_id_from)
        WHERE m.member_id = OLD.member_id_from;

        UPDATE member m
            SET m.member_follower = (SELECT COUNT(f.member_id_from) FROM follow f WHERE f.member_id_to = OLD.member_id_to)
        WHERE m.member_id = OLD.member_id_to;
    END //
DELIMITER ;



/*============================================================================*/

/*
제작일: 2020.08.11
유형: Table
설명: 인증 관련 테이블(임시)
 */
CREATE TABLE auth (
    id VARCHAR(20),
    password VARCHAR(20),
    authory VARCHAR(100),
    enabled BOOL,
    name VARCHAR(50)
)