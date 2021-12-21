DROP TABLE EMP_SENIORITY;
DROP TABLE EMPLOYEE;
DROP TABLE DEPARTMENT;
CREATE TABLE EMPLOYEE (ID INTEGER GENERATED ALWAYS AS IDENTITY NOT NULL, NAME VARCHAR(255), SALARY BIGINT, PRIMARY KEY (ID));
CREATE TABLE DEPARTMENT (ID INTEGER GENERATED ALWAYS AS IDENTITY NOT NULL, NAME VARCHAR(255), PRIMARY KEY (ID));
CREATE TABLE EMP_SENIORITY (DEPARTMENT_ID INTEGER NOT NULL, EMP_ID INTEGER NOT NULL, SENIORITY INTEGER, PRIMARY KEY (DEPARTMENT_ID, EMP_ID));
ALTER TABLE EMP_SENIORITY ADD CONSTRAINT DEPTREF FOREIGN KEY (DEPARTMENT_ID) REFERENCES DEPARTMENT (ID);
ALTER TABLE EMP_SENIORITY ADD CONSTRAINT EMPREF FOREIGN KEY (EMP_ID) REFERENCES EMPLOYEE (ID);
