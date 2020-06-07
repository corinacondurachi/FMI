-- ex 1 varianta 1
select * from employees e
where not exists (
  select 1 from projects p
  where extract (month from start_date) <= 6 and extract (year from start_date) = 2006
  and not exists (
    select 1 from works_on w
    where e.employee_id = w.employee_id and
          w.project_id = p.project_id
  )
);

-- ex 1 varianta 2
select employee_id, count(*) from works_on 
where project_id in (
  select project_id from projects
  where extract (month from start_date) <= 6 and extract (year from start_date) = 2006
)
group by employee_id
having count(employee_id) = (
  select count(*) from projects
  where extract (month from start_date) <= 6 and  extract (year from start_date) = 2006
);

-- ex 1 varianta 3
select employee_id from works_on
minus
select employee_id from (
  select employee_id, project_id from (
    select distinct employee_id from works_on
  ) t1, (
    select project_id from projects
    where extract (month from start_date) <= 6
  ) t2
  minus
  select employee_id, project_id from works_on
);

-- ex 1 varianta 4
select distinct employee_id
from works_on a
where not exists (
  (
    select project_id
    from projects p
    where extract (month from start_date) <= 6 and extract (year from start_date) = 2006
  )
  minus
  (
    select p.project_id
    from projects p, works_on b
    where p.project_id = b.project_id
    and b.employee_id = a.employee_id
  )
);

--2 nu functioneaza si nu imi explic motivul
with employees_with_two_jobs as (
    select employee_id
    from job_history
    group by employee_id
    having COUNT(job_id) >= 2 )    
select w.project_id
from works_on w
where project_id in
            ( select project_id
              from works_on t1
              inner join employees_with_two_jobs e2j
              on (t1.employee_id = e2j.employee_id)
              having COUNT(project_id) =
                                        (select COUNT(1)
                                        from works_on t1
                                        inner join employees_with_two_jobs e2j
                                        on (t1.employee_id = e2j.employee_id)));

--3
with num_jobs as (select employee_id, count(1) as jobs
                  from job_history
                  group by employee_id
            )
select *
from employees
inner join num_jobs
using(employee_id)
where jobs >= 2;

--4
select country_id, count(1) "Nr angajati"
from employees
inner join departments
using (department_id)
inner join locations
using (location_id)
group by country_id;
                
--5
with dupa_termen as ( select project_id
                      from projects
                      where delivery_date > deadline
                    )
select employee_id, first_name, last_name
from employees e
where 2 <= (select count(1)
            from works_on w
            inner join dupa_termen
            using (project_id)
            where e.employee_id = w.employee_id)

--6
select employee_id, first_name, last_name, project_id
from employees 
left join works_on
using (employee_id);

--7
with project_managers as (select distinct project_manager as id_manager
                          from projects
                         )
select first_name, last_name, department_id
from employees
where department_id in (select distinct department_id 
                        from departments
                        inner join employees e 
                        using (department_id)
                        inner join project_managers pm
                        on pm.id_manager = e.employee_id);

--8
with project_managers as (select distinct project_manager as id_manager
                          from projects
                         )
select first_name, last_name, department_id
from employees
where department_id not in (select distinct department_id 
                        from departments
                        inner join employees e 
                        using (department_id)
                        inner join project_managers pm
                        on pm.id_manager = e.employee_id);  

--9
select department_id
from employees
group by department_id
having avg(salary) > &p;

--10
with project_managers as (select distinct project_manager as id_manager, count(1) as no_projects
                          from projects
                          group by project_manager
                         )
select first_name, last_name, salary, no_projects
from employees e
inner join project_managers pm
on pm.id_manager = e.employee_id
where no_projects >= 2;

--11
select distinct employee_id
from works_on w1
where not exists 
        ( select 1
          from projects p
          where project_manager = 102
          and not exists
                ( select 'x'
                  from works_on w2
                  where w2.employee_id = w2.employee_id and 
                        w2.project_id = p.project_id
                )
        );

--12a nu stiu daca e bine, n-am prea inteles ce cere
with employee_200_projects as (
    select project_id, employee_id
    from works_on
    where employee_id = 200
)
select e.employee_id, e.last_name
from employees e
where not exists (
    (
        select project_id
        from works_on
        where employee_id = e.employee_id
    )
    minus
    (
        select project_id
        from employee_200_projects
    )
);


--12b nu stiu daca asta cere sau daca e bine

with employee_200_projects as (
    select project_id, employee_id
    from works_on
    where employee_id = 200
)
select e.employee_id, e.last_name
from employees e
where not exists (
    (
        select project_id
        from employee_200_projects
    )
    minus
    (
        select project_id
        from works_on
        where employee_id = e.employee_id
    )
);



--13
with employee_200_projects as (
    select project_id, employee_id
    from works_on
    where employee_id = 200
)
select e.employee_id, e.last_name
from employees e
where not exists (
    (
        select project_id
        from works_on
        where employee_id = e.employee_id
    )
    minus
    (
        select project_id
        from employee_200_projects
    )
)
and not exists (
    (
        select project_id
        from employee_200_projects
    )
    minus
    (
        select project_id
        from works_on
        where employee_id = e.employee_id
    )
);

--14
select *
from job_grades;

desc job_grades;

select last_name, first_name, salary, grade_level
from employees
inner join job_grades
on ((salary <= highest_sal) and (salary >= lowest_sal));

--15
select employee_id, last_name, salary, department_id
from employees
where employee_id = &cod_angajat;

--16
accept p_jobId PROMPT 'p_jobId= ';
select last_name, department_id, salary 
from employees 
where lower(job_id) = '&p_jobId';

--17
select first_name, department_id, salary
from employees
where hire_date > to_date('&p_date', 'YYYY-MM-DD');

--18
accept data1 prompt 'coloana=';
accept data2 prompt 'tabel=';
select &data1 from &data2
order by 1;

--19
accept data1 prompt 'data inceput = ';
accept data2 prompt 'data final = ';
select last_name || ', ' || job_id Angajati, to_char(hire_date, 'MM/DD/YY')
from employees
where to_date('&data1', 'MM/DD/YY') < hire_date and hire_date < to_date('&data2', 'MM/DD/YY');

--20
accept locatie prompt 'locatie= ';
select e.last_name, e.job_id, e.salary, d.department_name
from employees e
join departments d using(department_id)
join locations l using(location_id)
where lower('&locatie') = lower(l.city);

--21a
accept data1 PROMPT "data 1 = ";
accept data2 PROMPT "data 2 = ";
select (TO_DATE(&data2,  'YYYY-MM-DD') - TO_DATE(&data1,  'YYYY-MM-DD' )) as diff
from dual;

--21b
with num_weeks as (
    select (TO_DATE(&data2,  'YYYY-MM-DD') - TO_DATE(&data1,  'YYYY-MM-DD' ))/7 as num_weeks
    from dual
)
select 5 * num_weeks as working_days
from num_weeks;














