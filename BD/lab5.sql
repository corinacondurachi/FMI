--1a
select department_name, job_title, avg(salary)
from employees join departments using (department_id) 
join jobs using (job_id)
group by rollup(department_name, job_title);

--1b
--se dubleaza rezultatele deoarece job_id si job_title reprezinta acelasi criteriu 
select department_name, job_title, avg(salary)
from employees join departments using (department_id) join jobs using (job_id)
group by rollup(department_name, department_id, job_title, job_id);

--2a
select department_name, job_title, avg(salary)
from employees 
join departments using (department_id) 
join jobs using (job_id)
group by cube(department_name, job_title);


--2b
--sunt mult mai multe intrucat sunt mai multe criterii

select department_name, job_title, avg(salary), grouping(department_name), grouping(job_title)
from employees 
join departments using (department_id)
join jobs using (job_id)
group by cube(department_name, job_title);


--3
select department_name, job_title, employees.manager_id, max(salary), sum(salary)
from employees 
join departments using (department_id) 
join jobs using (job_id)
group by grouping sets ((department_name, job_title),(job_title, employees.manager_id),());

--4
select max(salary)
from employees
having max(salary) > 15000

--5a
select employee_id, last_name, department_id
from employees a
where salary > (select avg(salary) 
		from employees b 
		where b.department_id = a.department_id)

--5b clauza select nu cred ca da corect

select employee_id, salary, e.department_id, 
(select avg(salary) 
from employees o 
where e.department_id=o.department_id) MedieSal,
(select count(employee_id) 
from employees t 
where t.department_id=e.department_id) NumarAng, 
department_name
from employees e
join departments d 
on (e.department_id=d.department_id);

--5b clauza from 

SELECT employee_id, salary, e.department_id, department_name, sal_med, nr_sal
FROM employees e, departments d, (SELECT department_id, AVG(salary) sal_med,
COUNT(*) nr_sal
FROM employees
GROUP BY department_id) sm
WHERE e.department_id = d.department_id
AND d.department_id = sm.department_id
AND salary > (SELECT AVG(salary)
FROM employees
WHERE department_id = e.department_id); 


--6
select last_name, salary
from employees 
where salary > (select max(avg(salary)) from employees 
					group by department_id);

select last_name, salary
from employees 
where salary > all (select avg(salary) from employees 
					group by department_id);

--7
select last_name, salary
from employees a
where salary = (select min(salary) 
		from employees b 
		where a.department_id = b.department_id);

--v2

select last_name, salary
from employees a 
join 
  (select min(salary) salariu, department_id 
   from employees group by department_id ) b 
on (a.department_id = b.department_id)
where salary =  salariu;

--v3

select last_name, salary
from employees
where (department_id, salary) in (select department_id, min(salary)
				 from employees group by department_id);


--8 atentie
select last_name, e.department_id, department_name
from departments d
join employees e on (e.department_id = d.department_id)
where hire_date = (select min(hire_date) 
		   from employees b 
		   where e.department_id = b.department_id)
order by department_name;

--9
select last_name, department_id
from employees a
where exists (select employee_id 
	      from employees b 
              where a.department_id = b.department_id 
              and b.salary = (select max(salary) 
			      from employees 
			      where department_id = 30));

--10
select last_name, salary from employees
where rownum <= 3
order by salary desc;

--11
select last_name, first_name, employee_id
from employees a
where 2 <= (select count(1) 
	    from employees b where
	    b.manager_id = a.employee_id);

--12
select location_id
from locations e
where location_id in (select location_id
		      from departments 
		      where e.location_id = location_id);

--13
select department_id
from departments d
where not exists (select 1 from employees  where department_id = d.department_id);

--14
-- a)
select employee_id, last_name, hire_date, salary, manager_id
from employees 
where level <= 2
start with last_name = 'De Haan'
connect by prior employee_id = manager_id; 

-- b)
select employee_id, last_name, hire_date, salary, manager_id
from employees 
start with last_name = 'De Haan'
connect by prior employee_id = manager_id; 

--15
select employee_id, last_name, hire_date, manager_id, level
from employees
start with employee_id = 100
connect by prior employee_id = manager_id
order by level; 

--16
select employee_id, manager_id, last_name, level
from employees
where level = 3
  start with employee_id=(select employee_id 
                          from employees 
                          where lower(last_name)='de haan')
connect by prior employee_id=manager_id;

--17 ok sa apara de mai multe ori
select last_name, level, employee_id, manager_id
from employees
connect by employee_id = prior manager_id;

--18
select employee_id, manager_id, last_name, salary, level
from employees
where salary > 5000
start with employee_id in (select employee_id 
			   from employees 
			   where salary= (select max(salary)
					  from employees))
connect by prior employee_id = manager_id;

--19
with deps as (
  select department_name, sum(salary) avg_sal
  from employees 
  join departments using (department_id)
  group by department_name
)
select department_name, avg_sal 
from deps
where avg_sal > (select avg(sum(salary)) 
                 from employees 
                 group by department_id);

--20
with tabel as -- primul tabel selecteaza angajatii directi ai lui king, ordonati dupa vechime
 (select * 
  from employees
  where manager_id = (select employee_id from employees 
                      where last_name='King' and first_name='Steven') order by hire_date )

-- ultima cerere face restul
select employee_id, last_name || ' ' || first_name, job_id, hire_date, manager_id, level
from employees
where to_char(hire_date,'yyyy') != 1970                    
start with employee_id in (select employee_id from tabel)
connect by prior employee_id = manager_id;

--alta var

with subs as (
    select employees.*, level "lvl"
    from employees
    where level >= 2
    start with last_name = 'King'
    connect by prior employee_id = manager_id
    order by hire_date)
    
select employee_id, manager_id, last_name, first_name, job_id, hire_date, level
from subs
where to_char(hire_date, 'yyyy') != '1970'
start with "lvl" = 2
connect by prior employee_id = manager_id;

--21
select * from (
  select * from employees
  order by salary desc)
where rownum <= 10;

--22
select * from
 (select job_id, avg(salary)
  from employees
  group by job_id
  order by 2)
where rownum < 4;

--23
-- 23
select ' departamentul ' || department_name || ' este condus de ' ||nvl(to_char(departments.manager_id),'nimeni') || ' si ' ||
case 
     when count(employee_id) = 0 then 'nu are angajati'
     else 'are numarul de angajati ' || count(employee_id)
end "informatii"
from employees right join departments using (department_id)
group by department_name, departments.manager_id;

--24
select last_name, first_name, nullif(length(last_name), length(first_name)) "lungime nume"
from employees
order by "lungime nume";

--25
-- case
select last_name, hire_date, salary,
case to_char(hire_date, 'yyyy')
when '1989' then salary * 1.20
when '1990' then salary * 1.15
when '1991' then salary * 1.10
else salary
end "Salariu dupa marire"
from employees;

-- decode
select last_name, hire_date, salary, decode(to_char(hire_date, 'yyyy'),
                                    '1989', (1.2) * salary,
                                    '1990', (1.15) * salary,
                                    '1991', (1.1) * salary,
                                    salary) "salariu_marit"
from employees;

--26
select job_id,
(case 
when lower(job_id) LIKE 's%' then sum(salary)
when job_id = (select job_id 
               from employees 
               where salary = (select max(salary) from employees)) then 
				(select avg(salary) from employees)
else min(salary)
end) rez 
from employees
group by job_id;



