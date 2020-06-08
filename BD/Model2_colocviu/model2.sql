--1
select nume, prenume, nvl(count(1), 0)
from medic
left outer join programari using (id_medic)
where to_char(data_prog, 'yyyy') = 2018
group by id_medic, nume, prenume;

--2
select denumire, nume, prenume
from departamente d
inner join medic using (id_departament)
where salariu = (select max(salariu)
                from medic m
                where m.id_department = d.id_departement
                group by id_departament
                );

--3
select nume, prenume, d.denumire
from pacient
inner join programare using (id_pacient)
inner join medic using (id_medic)
inner join departament d using (id_departament)
where to_char(data_nasterii, 'mm') = to_char(data_prog, 'mm') and to_char(data_nasterii, 'dd') = to_char(data_prog, 'dd') and 
(lower(denumire) = 'cardiologie' or lower(denumire) = 'dermatologie');


--4
select distinct nume, prenume
from pacient
inner join programare using (id_pacient)
where data_programare > (select data programare 
                         from programare
                         inner join pacienti using (id_pacient)
                         where nume = 'Popescu'
                        );
                        
--5
insert into departamant (id_departament, denumire, salariu_min, salariu_man)
values (300, 'Chirirgie', 10000, 1000000);

alter table departament
add primary key (id_departamant);

alter table medic
add foreign key (id_departamant) references departament (id_departamant)
on delete cascade;
