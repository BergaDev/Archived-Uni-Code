use csit115;
#SELECT version(), sysdate(), system_user() FROM DUAL;

CREATE TABLE FIRST_NAME(name VARCHAR(20) NOT NULL,
CONSTRAINT FN_PK PRIMARY KEY(name));
INSERT INTO FIRST_NAME VALUES ('Matthew');
SELECT * FROM FIRST_NAME;
DROP TABLE FIRST_NAME;

