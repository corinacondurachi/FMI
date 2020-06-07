--1
select last_name, first_name, to_char(hire_date, 'MONTH YYYY')
from employees
where department_id = (select department_id 
                      from employees
                      where lower(last_name) = 'gates')
and (last_name like '%a%' or last_name like 'A%' or last_name like '%a')
and last_name <> 'Gates';


--varianta2 
select e.last_name, e.first_name, to_char(e.hire_date, 'MONTH YYYY')
from employees e
join employees e2 on (e.department_id = e2.department_id and
                      lower(e2.last_name) ='gates'
                      and (e.last_name like '%a%' or e.last_name like 'A%' 
                      or e.last_name like '%a')
                      and e.last_name <> 'Gates');

--2 var 1
select e.employee_id, e.last_name, d.department_id, d.department_name
from employees e
join departments d on (e.department_id = d.department_id) --pentru a afisa numele dep
where (select count(*)
       from employees s
       where lower(s.last_name) like '%t%' and s.department_id = e.department_id and s.employee_id != e.employee_id) > 0  
order by 2;

--2 var 2 
select distinct e.employee_id, e.last_name, d.department_id, d.department_name
from employees e
join departments d on (e.department_id = d.department_id)
join employees s on (e.department_id = s.department_id and lower(s.last_name) like '%t%' and s.employee_id != e.employee_id)
order by 2;


--3
select e1.last_name, e1.salary, job_title, city, country_name
from employees e1
join jobs using (job_id)
join departments using (department_id)
join locations using (location_id)
join countries using (country_id)
join employees e2 on (e1.manager_id = e2.employee_id and e2.last_name = 'King');

--4
select department_name, department_id, last_name, job_id
from departments
join employees using (department_id)
where department_name like '%ti%'
order by department_name, last_name;

--5
select d.department_id, d.department_name, l.city  
from departments d
join locations l on d.location_id = l.location_id and l.city = 'Oxford'
join employees e on e.department_id = d.department_id;

--6  var1
select distinct e1.employee_id, e1.last_name, e1.salary, (j.min_salary + j.max_salary) / 2 "avg"
from employees e1
join jobs j on (j.job_id = e1.job_id and e1.salary > (j.min_salary + j.max_salary) / 2)
where (select count(*)
       from employees s
       where lower(s.last_name) like '%t%' and s.department_id = e1.department_id) > 0  
order by 3;

--6  var2
select distinct e1.employee_id, e1.last_name, e1.department_id, e1.salary, (j.min_salary + j.max_salary) / 2 "avg"
from employees e1
join jobs j on (j.job_id = e1.job_id and e1.salary > (j.min_salary + j.max_salary) / 2)
join employees e3 on (e1.department_id = e3.department_id and lower(e3.last_name) like '%t%')
order by 3;

--7 v1
select distinct e1.last_name, department_name
from employees e1
left outer join departments using (department_id);

--7 v2
select distinct last_name, department_name
from departments 
right outer join employees using (department_id);


--8 v1
select  last_name, department_name
from employees
right outer join departments using (department_id);

--8 v2
select  last_name, department_name
from departments
left outer join employees using (department_id);

--9 v1
select  last_name, department_name
from employees
full outer join departments using (department_id);

--9 v2 SQL99
select last_name, department_name
from employees
left outer join departments using(department_id)
union all  
select last_name, department_name
from employees 
right outer join departments using(department_id)
where last_name is null -- le aduag pe cele care lipsesc din left outer join

--10
select  department_id 
from departments
where department_name like '%re%'
union
select department_id
from employees
where job_id = 'SA_REP';  --sunt ordonate dupa dep_id

--11
select  department_id 
from departments
where department_name like '%re%'
union all
select department_id
from employees
where job_id = 'SA_REP'; --avem duplicate iar rezultatele nu sunt ordonate?

--12
select  department_id 
from departments
minus
select  department_id 
from employees;

--13
select  department_id 
from departments
where department_name like '%re%'
intersect
select  department_id 
from employees
where job_id = 'HR_REP';

--14
select  last_name, job_id, employee_id 
from employees
where salary > 3000
union 
select  last_name, job_id, employee_id 
from employees
natural join jobs 
where salary = (min_salary + max_salary) /2;

--15
select  last_name, hire_date
from employees
where hire_date > (select hire_date 
                   from employees
                   where last_name='Gates');

--16
select  last_name, salary
from employees
where department_id = (select department_id  --se poate folosi si = si in deoarece se returneaza o singura valoare
                   from employees
                   where last_name='Gates')
and last_name != 'Gates';

--17
select  last_name, salary
from employees
where manager_id = (select employee_id
                   from employees
                   where manager_id is null);



--18
select  last_name, department_id, salary
from employees e1
where (department_id, salary) in (select department_id, salary 
                                  from employees e2
                                  where commission_pct is not null and
                                  e1.last_name != e2.last_name);

--19
SELECT employee_id, last_name, salary
FROM employees e
WHERE salary > (SELECT (min_salary + max_salary)/2 FROM jobs WHERE job_id = e.job_id)
and department_id in (select department_id from employees where lower(last_name) like '%t%')

--20
select last_name
from employees
where salary > all (select salary 
                    from employees 
                    where job_id like '%CLERK%') 
-- cu any trebuie ca salariul sa fie mai mare decat unul dintre ei
order by salary desc;

--21
select last_name, department_name, salary
from employees e1
join departments using (department_id)
where commission_pct is null
and (select count(*) from employees e2 where e2.manager_id = e1.manager_id and
                e2.commission_pct is not null) > 0;
 
--alta var
select last_name, department_name, salary
from employees e
join departments using (department_id)
where commission_pct is null
and e.manager_id in (select manager_id from employees where commission_pct is not null);  

   
--22
select last_name, department_name, salary, job_title
from employees e1
join departments using (department_id)
join jobs using (job_id)
where (salary,commission_pct) in (select salary, commission_pct
                  from employees  e
                  join departments d  on e.department_id = d.department_id
                  join locations l on d.location_id = l.location_id
                  where l.city='Oxford' and  e1.employee_id != e.employee_id);

--23
select last_name, department_id, salary, job_id
from employees e
where department_id in ( select department_id 
                        from departments
                        join locations using (location_id)
                        where city = 'Toronto' );

