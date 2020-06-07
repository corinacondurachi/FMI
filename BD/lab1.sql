--3
desc EMPLOYEES;

--4
select * from EMPLOYEES;
select employee_id as "id ang" from EMPLOYEES;

--5
select employee_id as cod, first_name as prenume, last_name as nume, job_id as "cod job" , hire_DATE as "data angajare" from EMPLOYEES;

--6
SELECT job_id FROM employees;
SELECT DISTINCT job_id FROM employees;

--7
SELECT last_name|| ', ' || job_id "Angajat si titlu" 
FROM employees;

--8
SELECT last_name|| ', ' || job_id || ', ' || salary "Informatii complete"
FROM employees;

--9
SELECT last_name, salary
FROM employees
WHERE salary > 2850;

--10
SELECT last_name, department_id
FROM employees
WHERE employee_id =104;

--11
SELECT last_name, salary
FROM employees
WHERE salary NOT BETWEEN 1500 AND 2850;

--12
SELECT last_name, job_id, hire_date
FROM employees
WHERE hire_date BETWEEN '20-FEB-1987' and '1-MAY-1989'
ORDER BY hire_date;

--13
SELECT last_name, department_id 
FROM employees
where department_id IN (10, 30)
ORDER BY last_name;

--14
SELECT last_name as "Angajat", salary as "Salariu lunar"
FROM employees
where department_id IN (10, 30) and salary>3500

--15
select SYSDATE 
FROM dual;

--16 v1
SELECT first_name, last_name, hire_date
FROM employees
WHERE hire_date LIKE ('%87%');

--16 v2
SELECT first_name, last_name, hire_date
FROM employees
WHERE TO_CHAR(hire_date, 'YYYY')='1987';

--17
SELECT last_name, job_id
FROM employees
WHERE manager_id is null;

--18
select last_name, salary, commission_pct
FROM employees
WHERE commission_pct is not null
order by salary desc, commission_pct desc;

--19
select last_name, salary, commission_pct
FROM employees
order by salary desc, commission_pct desc;

--20
select first_name
FROM employees
where first_name like ('__a%');

--21
select first_name
FROM employees
where (first_name like ('%l%l%') and (department_id=30 or manager_id=101));

--22
select first_name,job_id,salary
FROM employees
where (job_id like ('%CLERK%') or job_id like ('%REP%')) and salary not in (1000,2000,3000);

--23
select first_name,salary,commission_pct
FROM employees
where salary>5*commission_pct*salary
