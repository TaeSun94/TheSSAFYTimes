/*
제작일: 2020.07.27
유형: Table
설명: 기사 관리 테이블
*/

CREATE OR REPLACE table article (
    article_no INT PRIMARY KEY AUTO_INCREMENT ,
    member_id VARCHAR(20) ,
    article_title VARCHAR(256) NOT NULL ,
    article_content TEXT NOT NULL ,
    article_type INT ,
    article_datetime DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ,
    FOREIGN KEY (member_id) REFERENCES member(member_id) on UPDATE CASCADE ON DELETE CASCADE ,
    FOREIGN KEY (article_type) REFERENCES category(category_no) ON UPDATE CASCADE ON DELETE SET NULL
);

