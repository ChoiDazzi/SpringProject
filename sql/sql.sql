--SCALA SUBQUERY
SELECT A.SEQ,
       A.BOOK_ID,
       (SELECT B.NAME FROM BOOK_INFO B WHERE B.BOOK_ID =  A.BOOK_ID) NAME,
       A.FILENAME
FROM (
         SELECT * FROM ATTACH --INLINE VIEW
         WHERE SEQ >= 3
     ) A;

--ROWNUM: 행 번호
WITH T AS(
    SELECT ROW_NUMBER() over (ORDER BY SEQ DESC) RNUM,
            SEQ, BOOK_ID, FILENAME
    FROM ATTACH
)
SELECT * FROM T;

--집계함수: SUM, COUNT, MAX, MIN, AVG
SELECT COUNT(*) FROM BOOK_INFO;

WITH U AS (
    SELECT ROW_NUMBER() OVER (ORDER BY T.BOOK_ID DESC ) RNUM
         , T.*
    FROM(
            SELECT A.BOOK_ID, A.NAME, A.UNIT_PRICE, A.AUTHOR,
                   SUBSTR(A.DESCRIPTION,1,7) || '..<p>' DESCRIPTION,
                   A.PUBLISHER, A.CATEGORY, A.UNITS_IN_STOCK,
                   A.TOTAL_PAGES, A.RELEASE_DATE, A.CONDITION,
                   B.SEQ, B.FILENAME
            FROM BOOK_INFO A LEFT OUTER JOIN ATTACH B
                                             ON(A.BOOK_ID = B.BOOK_ID)
            WHERE 1 = 1
              AND (
                        CATEGORY        LIKE '%' || '홍길동' ||'%' OR
                        NAME            LIKE '%' || '홍길동' ||'%' OR
                        DESCRIPTION     LIKE '%' || '홍길동' ||'%' OR
                        AUTHOR          LIKE '%' || '홍길동' ||'%' OR
                        PUBLISHER       LIKE '%' || '홍길동' ||'%'
                )
            ORDER BY A.BOOK_ID DESC
        ) T
)
SELECT U.*
FROM U
WHERE U.RNUM BETWEEN 1 AND 10;

--1:N
SELECT B.BOOK_ID, B.NAME, B.UNIT_PRICE, B.AUTHOR, B.DESCRIPTION,
       B.PUBLISHER, B.CATEGORY, B.UNITS_IN_STOCK, B.TOTAL_PAGES, B.RELEASE_DATE, B.CONDITION,
       A.SEQ, A.FILENAME
FROM BOOK_INFO B , ATTACH A
WHERE B.BOOK_ID = A.BOOK_ID(+) AND B.BOOK_ID = 'ISBN1452';

--ANSI 표준
SELECT B.BOOK_ID, B.NAME, B.UNIT_PRICE, B.AUTHOR, B.DESCRIPTION,
       B.PUBLISHER, B.CATEGORY, B.UNITS_IN_STOCK, B.TOTAL_PAGES, B.RELEASE_DATE, B.CONDITION,
       A.SEQ, A.FILENAME
FROM BOOK_INFO B LEFT OUTER JOIN ATTACH A ON(B.BOOK_ID = A.BOOK_ID)
WHERE B.BOOK_ID = 'ISBN1452';

select * from ATTACH;

SELECT A.SEQ,
       A.BOOK_ID,
       A.FILENAME
FROM ATTACH A
WHERE A.SEQ = (
    SELECT MIN(B.SEQ)
    FROM ATTACH B
    WHERE B.BOOK_ID = A.BOOK_ID
);

-- 상관관계 서브쿼리
UPDATE ATTACH A
SET A.FILENAME = '가져온 정보'
WHERE A.SEQ = (
    SELECT MIN(B.SEQ)
    FROM ATTACH B
    WHERE B.BOOK_ID = A.BOOK_ID
)
  AND A.BOOK_ID = '아이디';

ALTER TABLE attach
    ADD CONSTRAINT FK_ATTACH FOREIGN KEY (BOOK_ID)
        REFERENCES BOOK_INFO(BOOK_ID)
        ON DELETE CASCADE;

MERGE INTO ATTACH A
    USING DUAL ON(A.BOOK_ID = 'ISBN1455')
    WHEN MATCHED THEN   --조건에 해당하는 데이터가 있음
        UPDATE
            SET    A.FILENAME = '개똥이'
    WHERE  A.SEQ = (
        SELECT MIN(B.SEQ)
        FROM ATTACH B
        WHERE B.BOOK_ID = A.BOOK_ID
    )
WHEN NOT MATCHED THEN   --조건에 해당하는 데이터가 없음
    INSERT (SEQ, BOOK_ID, FILENAME)
    VALUES((SELECT NVL(MAX(SEQ),0) + 1 FROM ATTACH),'ISBN1455','개똥이');

CREATE TABLE ITEM(
                     ITEM_ID NUMBER,
                     ITEM_NAME VARCHAR2(60),
                     PRICE NUMBER,
                     DESCRIPTION CLOB, --CHARACTER LARGE OBJECT(4GB)
                     PICTURE_URL VARCHAR2(600),
                     CONSTRAINT PK_ITEM PRIMARY KEY (ITEM_ID)
);

CREATE TABLE ITEM2 (
                       ITEM_ID     NUMBER,
                       ITEM_NAME   VARCHAR2(60),
                       PRICE       NUMBER,
                       DESCRIPTION CLOB,
                       PICTURE_URL VARCHAR2(600),
                       PICTURE_URL2 VARCHAR2(600),
                       CONSTRAINT PK_ITEM2 PRIMARY KEY (ITEM_ID)
);

CREATE TABLE ITEM3 (
                       ITEM_ID     NUMBER,
                       ITEM_NAME   VARCHAR2(60),
                       PRICE       NUMBER,
                       DESCRIPTION CLOB,
                       CONSTRAINT PK_ITEM3 PRIMARY KEY (ITEM_ID)
);

CREATE TABLE ITEM_ATTACH (
                             FULLNAME VARCHAR2(600) NOT NULL ,
                             ITEM_ID NUMBER,
                             REGDATE DATE,
                             CONSTRAINT PK_ITEM_ATTACH PRIMARY KEY (FULLNAME),
                             CONSTRAINT FK_ITEM_ATTACH FOREIGN KEY (ITEM_ID)
                                 REFERENCES ITEM3(ITEM_ID)
);

SELECT COLUMN_NAME
     , DATA_TYPE
     , CASE WHEN DATA_TYPE='NUMBER' THEN 'private int ' || FN_GETCAMEL(COLUMN_NAME) || ';'
            WHEN DATA_TYPE IN('VARCHAR2','CHAR') THEN 'private String ' || FN_GETCAMEL(COLUMN_NAME) || ';'
            WHEN DATA_TYPE='DATE' THEN 'private Date ' || FN_GETCAMEL(COLUMN_NAME) || ';'
            ELSE 'private String ' || FN_GETCAMEL(COLUMN_NAME) || ';'
    END AS CAMEL_CASE
     , '<result property="'||FN_GETCAMEL(COLUMN_NAME)||'" column="'||COLUMN_NAME||'"/>' RESULTMAP
FROM ALL_TAB_COLUMNS
WHERE TABLE_NAME = 'ITEM2';
--------------
select * from book_info;
select * from attach;

SELECT
        SUBSTR(NVL(MAX(BOOK_ID),'ISBN1234'),1,4)
        || TRIM((SUBSTR(NVL(MAX(BOOK_ID),'ISBN1234'),5) + 1))
FROM BOOK_INFO;

SELECT NVL(MAX(BOOK_ID),'ISBN1234') from book_info;

SELECT A.BOOK_ID, A.NAME, A.UNIT_PRICE, A.AUTHOR, A.DESCRIPTION,
       A.PUBLISHER, A.CATEGORY, A.UNITS_IN_STOCK,
       A.TOTAL_PAGES, A.RELEASE_DATE, A.CONDITION,
       B.SEQ, B.BOOK_ID, B.FILENAME
FROM BOOK_INFO A, ATTACH B
WHERE A.BOOK_ID = B.BOOK_ID(+);

--표준어 ANSI 표준
SELECT A.BOOK_ID, A.NAME, A.UNIT_PRICE, A.AUTHOR, A.DESCRIPTION,
       A.PUBLISHER, A.CATEGORY, A.UNITS_IN_STOCK,
       A.TOTAL_PAGES, A.RELEASE_DATE, A.CONDITION,
       B.SEQ, B.BOOK_ID, B.FILENAME
FROM BOOK_INFO A LEFT OUTER JOIN ATTACH B
                                 ON(A.BOOK_ID = B.BOOK_ID);

--구글 카멜변환(https://heavenly-appear.tistory.com/270)
SELECT COLUMN_NAME
     , DATA_TYPE
     , CASE WHEN DATA_TYPE='NUMBER' THEN 'private int ' || FN_GETCAMEL(COLUMN_NAME) || ';'
            WHEN DATA_TYPE IN('VARCHAR2','CHAR') THEN 'private String ' || FN_GETCAMEL(COLUMN_NAME) || ';'
            WHEN DATA_TYPE='DATE' THEN 'private Date ' || FN_GETCAMEL(COLUMN_NAME) || ';'
            ELSE 'private String ' || FN_GETCAMEL(COLUMN_NAME) || ';'
    END AS CAMEL_CASE
     , '<result property="'||FN_GETCAMEL(COLUMN_NAME)||'" column="'||COLUMN_NAME||'"/>' RESULTMAP
FROM ALL_TAB_COLUMNS
WHERE TABLE_NAME = 'BOOK_INFO';

select * from BOOK_INFO;

--데이터 다중 생성
DECLARE
BEGIN
FOR I IN 1301..1457 LOOP
            INSERT INTO BOOK_INFO (BOOK_ID, NAME, UNIT_PRICE, AUTHOR, DESCRIPTION, PUBLISHER, CATEGORY,TOTAL_PAGES)
            VALUES ('ISBN'||I,'제목'||I,12000,'최서연','내용'||I,'민음사','소설',1000);
END LOOP;
COMMIT;
END;
/

--FROM 절에 사용된 서브쿼리? INLINE VIEW
WITH U AS (
    SELECT ROW_NUMBER() OVER (ORDER BY T.BOOK_ID DESC ) RNUM
         , T.*
    FROM(
            SELECT A.BOOK_ID, A.NAME, A.UNIT_PRICE, A.AUTHOR,
                   SUBSTR(A.DESCRIPTION,1,7) || '..<p>' DESCRIPTION,
                   A.PUBLISHER, A.CATEGORY, A.UNITS_IN_STOCK,
                   A.TOTAL_PAGES, A.RELEASE_DATE, A.CONDITION,
                   B.SEQ, B.FILENAME
            FROM BOOK_INFO A LEFT OUTER JOIN ATTACH B
                                             ON(A.BOOK_ID = B.BOOK_ID)
            ORDER BY A.BOOK_ID DESC
        ) T
)
SELECT U.*
FROM U
WHERE U.RNUM BETWEEN (3 * 10) - (10 - 1) AND (3 * 10);
/



