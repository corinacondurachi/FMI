--Lab2

--1
SELECT FIRST_NAME ||' '|| LAST_NAME ||' '|| 'CASTIGA' || ' '||  SALARY|| ' LUNAR DAR DORESTE'|| ' '|| 3*SALARY AS "salariu ideal"
from EMPLOYEES;

--2
SELECT INITCAP(FIRST_NAME) || ' '|| UPPER(LAST_NAME) ||' '|| LENGTH(LAST_NAME)
FROM EMPLOYEES
WHERE LAST_NAME LIKE ('J%') OR LAST_NAME  LIKE ('M%') OR LAST_NAME LIKE ('__a%');

--3
SELECT FIRST_NAME , EMPLOYEE_ID, DEPARTMENT_ID
FROM EMPLOYEES
WHERE trim(lower(FIRST_NAME)) = 'steven';

--4
SELECT LAST_NAME , EMPLOYEE_ID, LENGTH(LAST_NAME),INSTR(lower(LAST_NAME),'a')
FROM EMPLOYEES
WHERE lower(Last_NAME) = '%e';

--5
SELECT LAST_NAME 
FROM EMPLOYEES
where trunc(trunc(SYSDATE-HIRE_DATE)/7)=trunc(SYSDATE-HIRE_DATE)/7;

--6
SELECT LAST_NAME, EMPLOYEE_ID,SALARY, TO_CHAR(SALARY*1.15,'99999.99'), trunc(MOD(SALARY*1.15,1000)/100)
FROM EMPLOYEES;

--7
SELECT LAST_NAME AS "Nume", HIRE_DATE AS "Data angajarii"
FROM EMPLOYEES
where COMMISSION_PCT is not null;

--8
SELECT TO_CHAR(SYSDATE+30,'MONTH DD YYYY HH24:MI:SS') FROM DUAL;

--9
SELECT TRUNC(TO_DATE('31-12-2020 23:59', 'dd-MM-YYYY HH24:MI')-SYSDATE) FROM DUAL;

-10
SELECT SYSDATE+0.5 FROM DUAL;
SELECT TO_CHAR(SYSDATE+1/24/60*5, 'HH24:MI') FROM DUAL;

--11
SELECT LAST_NAME, HIRE_DATE, NEXT_DAY(ADD_MONTHS(HIRE_DATE,6),'Monday')
FROM EMPLOYEES;

--12
SELECT LAST_NAME, TRUNC(MONTHS_BETWEEN(SYSDATE, HIRE_DATE)) AS "Luni lucrate"
FROM EMPLOYEES
ORDER BY 2; 

--13
SELECT LAST_NAME, HIRE_DATE, TO_CHAR(HIRE_DATE,'day') as "ZI"
FROM EMPLOYEES
order by TO_CHAR(HIRE_DATE,'d');


Facute acasa

--14
SELECT first_name, NVL(TO_CHAR(commission_pct), 'Fara comision' ) as Comision
FROM employees;

--15
SELECT LAST_NAME, SALARY, COMMISSION_PCT
FROM EMPLOYEES
WHERE SALARY+ NVL(commission_pct, 0) * salary /12>10000;

--16
SELECT first_name, job_id, salary, 
DECODE(job_id,
  'IT_PROG', salary+salary*20/100,
  'SA_REP', salary+salary/25/100,
  'SA_MAN', salary+salary*35/100, salary)  as "Salariu renegociat"
FROM EMPLOYEES;



--17
SELECT e.first_name, e.employee_id, e.department_id
FROM employees e
INNER JOIN departments d ON e.department_id=d.department_id;

--18
SELECT distinct j.job_title
FROM jobs j
join employees e on (j.job_id = e.job_id)
WHERE department_id=30;

--19
SELECT employees.first_name, departments.department_name, departments.location_id
FROM employees
inner JOIN departments ON 
employees.department_id=departments.department_id
WHERE NVL(employees.commission_pct,0) > 0;

--20
SELECT employees.first_name, departments.department_name
FROM employees
LEFT JOIN departments ON 
employees.department_id=departments.department_id
WHERE employees.first_name LIKE ('%a%') or employees.first_name LIKE ('A%') ;

--21
SELECT employees.first_name, employees.job_id, departments.department_id, departments.department_name
FROM employees
LEFT JOIN departments ON 
employees.department_id=departments.department_id
LEFT JOIN locations ON departments.location_id=locations.location_id
WHERE locations.city='Oxford' ;

--22
SELECT t1.employee_id as "Ang#", t1.first_name as "Angajat", t1.manager_id as "Mgr#", t2.first_name as "Manager"
FROM employees t1
INNER JOIN employees t2 ON 
t2.employee_id=t1.manager_id;

--23
SELECT t1.employee_id as "Ang#", t1.first_name as "Angajat", t1.manager_id as "Mgr#", t2.first_name as "Manager"
FROM employees t1
LEFT JOIN employees t2 ON 
t2.employee_id=t1.manager_id;

--24
SELECT t1.employee_id as "ID", t1.first_name as "Nume", t2.first_name As "Coleg" 
FROM employees t1
JOIN employees t2 ON 
t1.department_id=t2.department_id;

--25
SELECT t1.job_id as "job_ID", t1.job_title as "Job Title", t2.salary As "Salariu", t3.department_name 
FROM jobs t1
JOIN employees t2 ON 
t1.job_id=t2.job_id
JOIN departments t3 ON 
t2.department_id=t3.department_id;

--26
SELECT first_name || ' ' || last_name name, hire_date
FROM employees
where hire_date > (
SELECT hire_date
from employees
where last_name='Gates');

--27
SELECT t1.first_name || ' ' || t1.last_name name Angajat, t1.hire_date Data_ang,
       t2.first_name || ' ' || t2.last_name name Manager, t2.hire_date Data_mng
From employees t1
inner join employees t2
ON t1.manager_id = t2.employee_id
where t1.hire_date < t2.hire_date;








