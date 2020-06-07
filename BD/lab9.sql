-- ex 4
drop table TEMP_SESIUNE_cco;

--5 
Create global temporary table angajati_azi_cco(
  nume VARCHAR2(9)
) on commit preserve rows;

insert into angajati_azi_cco values('Popescu');

commit;

select * from angajati_azi_cco;

--6
insert into angajati_azi_cco values('Marin');
commit;
alter table angajati_azi_cco
modify (nume varchar2(50));

--9
create or replace view VIZ_EMP50_cco
as
select employee_id, last_name, email, salary, hire_date, job_id, department_id from employees
where department_id = 50;

--10a
insert into VIZ_EMP50_cco values(1006, 'Test nume', 'email', 10000, sysdate, 'SA_REP',10);

--10b 
update VIZ_EMP30_cco
set email = 'test - 2', department_id = 20, employee_id = 5, job_id = 'SA_REP', hire_date = sysdate, salary = 2344, first_name = 'acsdv'
where employee_id = 119;
select * from employees;

--10c
insert into VIZ_EMP50_cco values(1009, 'Test nume4', 'email8', 10000, sysdate, 'SA_REP',10);


--11b
-- b)
ROLLBACK;
insert into VIZ_EMP_DEP30_cco (employee_id, last_name, email, salary, hire_date, job_id, department_id)
values (1000, 'Test', 'test', 6000, sysdate, 'PU_CLERK', 30);

--cum pot vedea coloanele actualizabile ale unei vizualizari?
select * from USER_UPDATABLE_COLUMNS where table_name = 'VIZ_EMP_DEP30_CCO'

--13

CREATE OR REPLACE VIEW viz_emp30_cco
AS
    SELECT cod_ang, nume, email, salariu, cod_dep
    FROM angajati_cco
    WHERE cod_dep = 30 WITH CHECK OPTION CONSTRAINT viz_check_dept30_cco;

SELECT * FROM user_constraints
WHERE LOWER(constraint_name) = 'viz_check_dept30_cco';

-- nu merge deoarece nu respecta constrangerea
INSERT INTO viz_emp30_cco
VALUES (300, 'Exemplu', 'email', 15000, 31);

--respecta, deci merge
INSERT INTO viz_emp30_cco
VALUES (300, 'Exemplu', 'email', 15000, 30);

--14
CREATE VIEW viz_emp_s_cco AS
    SELECT *
    FROM angajati_gma
    WHERE (
        SELECT nume
        FROM departamente_gma
        WHERE cod_dep = angajati_gma.cod_dep
    ) LIKE 'S%';

SELECT * FROM viz_emp_s_cco;

--b 
CREATE VIEW viz_emp_s_cco2 AS
    SELECT *
    FROM angajati_gma
    WHERE (
        SELECT nume
        FROM departamente_gma
        WHERE cod_dep = angajati_gma.cod_dep
    ) LIKE 'S%'
    with read only;

SELECT * FROM viz_emp_s_cco;

--15
SELECT * FROM user_views;

--16
SELECT *
FROM angajati_cco
INNER JOIN viz_dept_sum_cco
on (cod_dep = dept_id);

--17 
CREATE VIEW viz_sal_cco AS
    SELECT last_name, department_name, salary, city
    FROM employees
    INNER JOIN departments
    USING (department_id)
    INNER JOIN locations
    USING (location_id);

SELECT * FROM viz_sal_cco;

--19
ALTER VIEW viz_emp_s_cco
ADD PRIMARY KEY (cod_ang) DISABLE NOVALIDATE;

--22
CREATE SEQUENCE seq_emp_gma;

--23
DROP TABLE emp_cco;
CREATE TABLE emp_cco AS (SELECT * FROM employees);

UPDATE emp_cco
SET employee_id = seq_emp_cco.nextval;

SELECT * FROM emp_cco;

-- ex 24 
select * from dept_cco;

-- nextval incrementeaza val secventei - trebuie macar odata executat inainte de a folosi currval pe o secventa
select seq_dept_cco.currval from dual;
insert into dept_cco
values(seq_dept_cco.nextval, 'Test', null, 1700);

-- currval doar returneaza valoare curenta fara a o incrementa
select seq_dept_cco.currval from dual;

--25

SELECT seq_emp_cco.currval FROM dual;
SELECT seq_dept_cco.currval FROM dual;


--26

DROP SEQUENCE seq_dept_cco;

--28
CREATE UNIQUE INDEX idx_emp_id_cco
    ON emp_gma (employee_id);

ALTER TABLE emp_cco
ADD PRIMARY KEY (employee_id)
ADD UNIQUE (last_name, first_name, hire_date);

--29
CREATE INDEX idx_dept_id_cco
    ON emp_cco (department_id);

--30
CREATE INDEX idx_dept_name_cco
    ON dept_cco (UPPER(department_name));

CREATE INDEX idx_emp_name_cco
    ON emp_cco (LOWER(last_name));

--31

SELECT index_name, column_name, column_position, uniqueness
FROM user_indexes
INNER JOIN user_ind_columns
USING (index_name)
WHERE LOWER(user_indexes.table_name) IN ('emp_cco', 'dept_cco');


--32
DROP INDEX idx_emp_last_name_cco;

--35
CREATE TABLE ang_1_cco
CLUSTER angajati_cco(employee_id)
AS SELECT * FROM employees WHERE salary < 5000;

CREATE TABLE ang_2_cco
CLUSTER angajati_cco(employee_id)
AS SELECT * FROM employees WHERE 5000 <= salary AND salary < 10000;

CREATE TABLE ang_3_cco
CLUSTER angajati_cco(employee_id)
AS SELECT * FROM employees WHERE salary >= 10000;

--36
SELECT * FROM user_clusters;

--37
SELECT cluster_name
FROM user_tables
WHERE LOWER(table_name) = 'ang_3_cco';

--39 nu mai face
SELECT * FROM user_tables
WHERE LOWER(table_name) = 'ang_3_cco';

--40
DROP TABLE ang_2_cco;

SELECT * FROM user_tables
WHERE LOWER(cluster_name) = 'angajati_cco';

--42 NU MERGE!!
CREATE PUBLIC SYNONYM emp_public_cco FOR emp_cco;

--43
CREATE SYNONYM v30_cco FOR viz_emp30_cco;

--44
CREATE SYNONYM dept_syn_cco FOR dept_cco;

SELECT * FROM dept_syn_cco;

RENAME dept_cco TO dept_redenumit_cco;

RENAME dept_redenumit_cco TO dept_cco;

--45
SELECT * FROM user_synonyms
WHERE LOWER(synonym_name) LIKE '%cco';

DROP SYNONYM v30_cco;
DROP SYNONYM dept_syn_cco;

--46
CREATE MATERIALIZED VIEW job_dep_sal_cco
BUILD IMMEDIATE
REFRESH COMPLETE
ENABLE QUERY REWRITE
AS SELECT d.department_name, j.job_title, SUM(salary) AS suma_salarii
FROM employees e
INNER JOIN departments d
ON e.department_id = d.department_id
INNER JOIN jobs j
ON e.job_id = j.job_id
GROUP BY d.department_name, j.job_title;

--47 
CREATE TABLE job_dep_cco (
    job VARCHAR2 (10),
    dep NUMBER (4),
    suma_salarii NUMBER(9, 2)
);

CREATE MATERIALIZED VIEW job_dep_cco
ON PREBUILT TABLE WITH REDUCED PRECISION
ENABLE QUERY REWRITE AS (
    SELECT j.job_title as job, d.department_id as dep, SUM(salary) AS suma_salarii
    FROM employees e
    INNER JOIN departments d
    ON e.department_id = d.department_id
    INNER JOIN jobs j
    ON e.job_id = j.job_id
    GROUP BY d.department_id, j.job_title
);

--48
CREATE MATERIALIZED VIEW LOG ON dept_cco;

CREATE MATERIALIZED VIEW dep_vm_cco
REFRESH FAST START WITH SYSDATE NEXT SYSDATE + 1/288
WITH PRIMARY KEY
AS SELECT * FROM dept_cco;

--49 eroare 
ALTER MATERIALIZED VIEW job_dep_sal_cco
REFRESH FAST NEXT SYSDATE + 7 DISABLE QUERY REWRITE;

--50
DROP MATERIALIZED VIEW dep_vm_cco;
DROP MATERIALIZED VIEW job_dep_sal_cco;
