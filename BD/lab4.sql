-- 1
-- a) functiile grup ignora valorile null (cu exceptia lui COUNT(*))
-- b) in having putem pune functii precum sum(coloana). e folosit ca sa grupam sortarile

-- 2
select round(max(salary)) "Maxim", round(min(salary)) "Minim", round(sum(salary)) "Suma", round(avg(salary)) "Media"   
from employees;

-- 3
select job_id, round(max(salary)) "Maxim", round(min(salary)) "Minim", round(sum(salary)) "Suma", round(avg(salary)) "Media"   
from employees
group by job_id;

-- 4
select job_id, count(*) "Nr angajati"
from employees
group by job_id;

-- 5
select count(distinct manager_id)
from employees;

-- 6
select max(salary) - min(salary) "Diferenta salarii"
from employees;

-- 7
select department_name, city, count(*) "Nr angajati", avg(salary)
from departments d
join locations l on(l.location_id = d.location_id)
join employees e on(e.department_id = d.department_id)
group by d.department_name, l.city;

-- 8
select employee_id, last_name
from employees
where salary > (select avg(salary) from employees)
order by salary desc;

-- 9 atentie!! 
select manager_id, min(salary) from employees
where manager_id is not null
group by manager_id
having min(salary) > 5000  -- ca sa se vada efectul
order by 2 desc;

-- 10
select department_id, department_name, max(salary)
from departments d
join employees e using(department_id)
group by department_id, department_name
having max(salary) > 8000;

-- 11
select avg(min(salary))
from employees
group by department_id;

-- 12
select department_id, department_name, sum(salary)
from employees
join departments using(department_id)
group by department_id, department_name;

-- 13
select max(avg(salary))
from employees
group by department_id;

-- 14
select job_id, job_title, avg(salary)
from employees
join jobs using(job_id)
group by job_id, job_title
having avg(salary) = (select min(avg(salary)) from employees group by job_id);

-- 15
select avg(salary)
from employees		
having avg(salary) > 2500;

-- 16
select department_id, job_id, avg(salary)
from employees
join departments using(department_id)
join jobs using(job_id)
group by department_id, job_id
order by 1;

-- 17
select department_name, avg(salary)
from employees
join departments using(department_id)
group by department_name
having avg(salary) = (select max(avg(salary)) from employees group by department_id);

--18

--a
select department_id, department_name, count(*)
from departments
join employees using (department_id)
group by department_id, department_name
having count(*) < 4;

--b
select department_id, department_name, count(*)
from departments
join employees using (department_id)
group by department_id, department_name
having count(*) =  ( select max(count(*)) from employees 
                                        group by department_id );


--19
select last_name
from employees
where hire_date = (select hire_date
                  from employees
                  group by hire_date
                  having count(*) = (select max(count(*))
                     		     from employees
                  		     group by hire_date)
                  );
                  
--20 atentie
select count(*) 
from departments
where department_id in (select department_id 
                        from departments
                        join employees using (department_id)
                        group by department_id
                        having count(*) >= 15);


--21
select department_id, sum(salary), count(*) 
from departments
join employees using (department_id)
group by department_id
having department_id != 30 and count(*) > 10 
order by sum(salary);

--22
select *
from
(select department_id, department_name, count(*) "Nume angajati", avg(salary) "Salariu mediu"
from departments
join employees using (department_id)
group by department_id, department_name)

left outer join (select last_name, job_id, department_id
    FROM employees)
using (department_id);


--23
select department_id, city, job_id, sum(salary)
from departments
inner join employees using (department_id)
inner join locations using (location_id)
where department_id > 80
group by department_id, city, job_id;

--where e folosit pentru filtrarea randurilor inainte de grupare - aici afecreaza randurile care sunt folosite in cadrul sumei
-- having e folosit pentru filtrarea randurilor dupa grupare

--24
select last_name
from employees
where employee_id in (select employee_id 
                      from employees
                      inner join job_history using (employee_id)
                      group by employee_id
                      having count(*) >= 2); 
                      
--25
select avg(nvl(commission_pct,0))
from employees;

--27
select job_id,
  (select sum(salary) from employees where job_id = e.job_id and department_id = 30) Dept30,
  (select sum(salary) from employees where job_id = e.job_id and department_id = 50) Dept50,
  (select sum(salary) from employees where job_id = e.job_id and department_id = 80) Dept80,
  (select sum(salary) from employees where job_id = e.job_id) total
from employees e;

--28
select 
  (select count(*) from employees e where TO_CHAR(e.hire_date, 'yyyy') = '1997') Hired1997,
  (select count(*) from employees e where TO_CHAR(e.hire_date, 'yyyy') = '1998') Hired1998,
  (select count(*) from employees e where TO_CHAR(e.hire_date, 'yyyy') = '1999') Hired1999,
  (select count(*) from employees e where TO_CHAR(e.hire_date, 'yyyy') = '2000') Hired2000,
  (select count(*) from employees ) total
from dual;

--29
select d.department_id, department_name,
  (   select count(*)
      from employees e
      where d.department_id = department_id )
      as "Nr angajati",
   (  select avg(salary)
      from employees e
      where d.department_id = department_id )
      as  "Salariu mediu",
    last_name,
    salary,
    job_id     
from departments d
left join employees e  on (e.department_id = d.department_id);

--30

select department_id,  department_name, sum_salaries
from (
    select department_id, sum(salary) sum_salaries
    from employees
    group by department_id
)
Left outer join departments using (department_id);

--31 
select last_name, salary, department_id, avg_salary
from (
    select department_id, avg(salary) as avg_salary
    from employees
    group by department_id
)
inner join employees using (department_id);

--alta varianta
select  last_name, salary, b.department_id, medie, numar
from employees a
right join
(select department_id, avg(salary) medie, count(employee_id) numar
 from employees right join departments using (department_id) group by department_id) b
on (a.department_id=b.department_id);


--32
select last_name, salary, department_id, avg_salary, nr_angajati
from (
    select department_id, avg(salary) as avg_salary, count(*) as nr_angajati
    from employees
    group by department_id
)
inner join employees using (department_id);

--33 subquery where
select department_name, a.department_id, last_name, salary
from departments a left join employees b on (a.department_id=b.department_id)
where salary = (select min(salary) from employees c where c.department_id = b.department_id)
order by 2;


--33 var 2 subquery from
select department_name, last_name, salary
from (
        select department_name,department_id, min(salary) as min_salary
        from departments
        join employees using (department_id)
        group by department_name,department_id)
inner join employees using (department_id)
where salary = min_salary;


 --34
 select department_id, department_name, no_employees, average_salary, last_name, salary, job_id
 from (
        select department_id,
        count(*) as no_employees,
        avg(salary) as average_salary
        from employees
        group by department_id
 )
join employees using (department_id)
 full outer join departments using (department_id);