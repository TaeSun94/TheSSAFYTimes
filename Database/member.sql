/*
제작일: 2020.07.21
유형: table
설명: 회원 정보를 저장하는 table
*/
CREATE TABLE `member` (
	`member_no` INT PRIMARY KEY AUTO_INCREMENT,
	`member_id` VARCHAR(20) UNIQUE KEY NOT NULL,
	`member_pw` VARCHAR(60) NOT NULL,
	`member_email` VARCHAR(320) UNIQUE KEY NOT NULL,
	`member_first_name` VARCHAR(100),
	`member_last_name` VARCHAR(100),
	`member_phone` VARCHAR(32),
	`member_region` INT DEFAULT 0,
	`member_track` INT DEFAULT 0,
	`member_unit` INT DEFAULT 0,
	`member_intro` VARCHAR(500),
	`member_desc` TEXT,
	`member_auth` VARCHAR(20),
	`member_auth_status` BOOL NOT NULL DEFAULT FALSE,
	`member_auth_datetime` DATETIME,
	`member_address` VARCHAR(256),
	`member_datetime` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ,
	FOREIGN KEY (member_region) REFERENCES category(category_no) ON UPDATE CASCADE ON DELETE SET NULL ,
	FOREIGN KEY (member_track) REFERENCES category(category_no) ON UPDATE CASCADE ON DELETE SET NULL ,
	FOREIGN KEY (member_unit) REFERENCES category(category_no) ON UPDATE CASCADE ON DELETE SET NULL

);

ALTER TABLE member ADD `member_class` INT DEFAULT 0;
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

/*============================================================================*/