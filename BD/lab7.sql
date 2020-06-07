-- 1
CREATE TABLE EMP_cco AS SELECT * FROM employees;

CREATE TABLE DEPT_cco AS SELECT * FROM departments;

select * from emp_cco;

-- 4
ALTER TABLE emp_cco
ADD CONSTRAINT pk_emp_cco PRIMARY KEY(employee_id);
ALTER TABLE dept_cco
ADD CONSTRAINT pk_dept_cco PRIMARY KEY(department_id);
ALTER TABLE emp_cco
ADD CONSTRAINT fk_emp_dept_cco
FOREIGN KEY(department_id) REFERENCES dept_cco(department_id);


-- 5
-- a) Merge? - Nu pentru ca nu sunt toate valorile coloanelor specificate
INSERT INTO DEPT_cco
VALUES (300, 'Programare');
desc dept_ari;

-- b) Merge? - Merge dar avem valori null la coloanele ce nu apar in lista coloanelor
INSERT INTO DEPT_cco (department_id, department_name)
VALUES (300, 'Programare');
rollback;

select * from dept_cco where department_id = 300;

-- c) Merge? - Nu - conversia nu se poate face din varchar2 in number
INSERT INTO DEPT_cco (department_name, department_id)
VALUES (300, 'Programare');

-- d) Merge? - Da
INSERT INTO DEPT_cco (department_id, department_name, location_id)
VALUES (300, 'Programare', null);
rollback;

-- e) Merge? - Nu, pt ca lipseste valoarea pt PK
-- Identity insert sql server -> autogenerare valori PK
INSERT INTO DEPT_cco (department_name, location_id)
VALUES ('Programare', null);

--6
insert into emp_cco(employee_id, first_name, last_name, email, hire_date, job_id, department_id)
values (207, 'Andrei', 'Riclea', 'aricle@bla.com', to_date('01-05-2020', 'DD-MM-YYYY'), 'IT_PROG', 100);

rollback;

--7
insert into emp_cco(employee_id, first_name, last_name, email, hire_date, job_id, department_id)
values (208, 'Andrei', 'Riclea', 'aricle@bla.com', sysdate, 'IT_PROG', 100)

rollback;

--8
insert into emp_cco(employee_id, first_name, last_name, email, hire_date, job_id, department_id)
values ((select max(employee_id) + 1 from emp_cco), 'Andrei', 'Riclea', 'aricle@bla.com', sysdate, 'IT_PROG', 100);

select * from emp_cco where employee_id = 207;
rollback;

--9
create table emp1_cco as select * from employees;

truncate table emp1_cco;

select * from emp1_cco;

insert into emp1_cco
 (select * from employees where commission_pct > 0.25); 
 
 select * from emp1_cco;

 rollback;

 --10
insert into emp_cco
values (0, user, user, 'TOTAL', 'TOTAL', sysdate, 'IT_PROG', (select sum(salary) from employees), (select avg(commission_pct) from employees), null, null);

select * from emp_cco
rollback;

--11
accept p_id prompt 'Id = ';
accept p_nume prompt 'Nume = ';
accept p_prenume prompt 'Prenume = ';
accept p_salariu prompt 'Salariu = ';
insert into emp_cco(employee_id, last_name, first_name, email, salary, job_id, hire_date)
values (&p_id, '&p_nume', '&p_prenume', concat(substr('&p_prenume', 0, 1), substr('&p_nume', 0, 7)), &p_salariu, 'IT_PROG', sysdate);

select * from emp_cco;

rollback;

--12
create table emp2_cco as select * from employees;
truncate table emp2_cco;

create table emp3_cco as select * from employees;
truncate table emp3_cco;

insert into emp1_cco
 (select * from employees where salary < 5000); 
 
 select * from emp1_cco;
 
 insert into emp2_cco
 (select * from employees where salary >= 5000 and salary < 10000); 
 
 select * from emp2_cco;
 
 insert into emp3_cco
 (select * from employees where salary > 10000); 
 
 select * from emp3_cco;
 
truncate table emp1_cco;
truncate table emp2_cco;
truncate table emp3_cco;

--13
create table emp0_cco as select * from employees;
truncate table emp0_cco;

create table emp1_cco as select * from employees;
truncate table emp1_cco;

create table emp2_cco as select * from employees;
truncate table emp2_cco;

create table emp3_cco as select * from employees;
truncate table emp3_cco;

insert into emp0_cco
 (select * from employees where department_id = 80); 
 
select * from emp0_cco;

insert into emp1_cco
 (select * from employees where salary < 5000 and department_id != 80); 
 
 select * from emp1_cco;
 
 insert into emp2_cco
 (select * from employees where salary >= 5000 and salary < 10000 and department_id != 80 ); 
 
 select * from emp2_cco;
 
 insert into emp3_cco
 (select * from employees where salary > 10000 and department_id != 80); 
 
 select * from emp3_cco;

--14
update emp_cco
set salary = salary * 1.05;

select * from emp_cco;

rollback;

--15
update emp_cco
set job_id = 'SA_REP'
where department_id = 80 and commission_pct is not null;

select * from emp_cco;

rollback;

--16
update dept_cco
set manager_id = (select employee_id from emp_ari where last_name = 'Grant' and first_name = 'Douglas')
where department_id = 20;
update emp_cco
set salary = salary + 1000
where employee_id = (select employee_id from emp_ari where last_name = 'Grant' and first_name = 'Douglas');

select * from dept_cco;

rollback;

--17
update emp_cco e1
set (salary, commission_pct) = (select salary, commission_pct from emp_cco where employee_id = e1.manager_id)
where salary = (select MIN(salary) from emp_cco);

select * from emp_cco where salary = (select MIN(salary) from emp_cco);

rollback;

--18
update emp_cco e1
set email = substr(last_name, 0, 1) || nvl(first_name, '.')
where salary = (select max(salary) from emp_cco where department_id = e1.department_id);

select * from emp_cco e1
where salary = (select max(salary) from emp_ari where department_id = e1.department_id);

rollback;

--19
update emp_cco
set salary =  (select avg(salary) from emp_cco)
where (department_id, hire_date) in (select  department_id, min(hire_date) 
                                    from emp_cco
                                    group by department_id);

--20
update angajati_cco
set (job, cod_dep) = (select job, cod_dep from angajati_cco where cod_ang = 205)
where cod_ang = 114;

--21
accept dep_id prompt 'Id = ';

select * from dept_cco
where department_id = &dep_id;

accept dep_nume prompt 'Nume = ';
accept manager_id prompt 'Manager_id = ';
accept loc_id prompt 'Location_id = ';

update dept_cco
set department_name = '&dep_nume', manager_id = &manager_id, location_id = &loc_id
where department_id = &dep_id;

select * from dept_cco
where department_id = &dep_id;


--22
-- nu merge pentru ca avem valori in emp_pnu (child values) ce referentiaza valori din dept_pnu (parent values)
delete from dept_cco;
rollback;

--23
delete from emp_cco
where commission_pct is null;

rollback;

--24
delete from dept_cco
where department_id not in (select department_id from employees where department_id is not null);

rollback;

--25
accept emp_id prompt 'Id = ';

select * from emp_cco
where employee_id = &emp_id;

delete from emp_cco
where employee_id = &emp_id;

--26
accept p_id prompt 'Id = ';
accept p_nume prompt 'Nume = ';
accept p_prenume prompt 'Prenume = ';
accept p_salariu prompt 'Salariu = ';
insert into emp_cco(employee_id, last_name, first_name, email, salary, job_id, hire_date)
values (&p_id, '&p_nume', '&p_prenume', concat(substr('&p_prenume', 0, 1), substr('&p_nume', 0, 7)), &p_salariu, 'IT_PROG', sysdate);

select * from emp_cco;

--27
savepoint salvat1;

--28
delete from emp_cco;
select * from emp_cco;

--29
ROLLBACK;

--30

SELECT * FROM emp_cco;
COMMIT;

--31
delete from emp_cco where commission_pct is null;

MERGE into emp_cco e1
using (select * from employees) e2
on (e1.employee_id = e2.employee_id)
when matched then
  update set e1.salary = e2.salary, e1.last_name = e2.last_name
when not matched then
  insert (employee_id, job_id, last_name, first_name, hire_date, email)
  values (e2.employee_id, e2.job_id, e2.last_name, e2.first_name, e2.hire_date, e2.email);
  
select * from emp_cco where commission_pct is not null;













