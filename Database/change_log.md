# 0727

* [수정] board 테이블 컬럼 `board__name` -> `board_name`
* [수정] member 테이블 컬럼 `region`, `class`, `track`, `unit` 기존 문자열 입력 -> `category` 테이블 외래 키 참조(int 형)
* [추가] article 테이블
* [추가] cartegory_manage , cartegory 테이블




# 0728

* [수정] category 테이블 컬럼 `category_order` 기본값 0, null일 시 화면에 표시하지 않음
* [수정] `category`가 정해지지 않거나 없을 때 `NULL`이 아닌 `0`이 들어감 | `category`, `category_manage` 테이블에  PK가 `0`인 튜플 삽입





# 0729

* [추가] 카테고리 등록 프로시저





# 0731

* [삭제] member 테이블 컬럼 `member_class`
* [수정] er-diagram 수정





# 0803

* [수정] member의 카테고리 제약조건
* [추가] 작성 기사 수 자동 증가 트리거





# 0804

* [수정] 프로그램, 자유 게시판 좋아요 컬럼명 수정 (likeCount -> like)
* [추가] 싫어요, 좋아요 자동 증가 트리거 | 싫어요 컬럼 (dislike)