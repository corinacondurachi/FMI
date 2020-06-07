--1
select *
from (
    select denumire
    from excursie 
    inner join achizitioneaza 
    on id_excursie = cod_excursie
    order by data_achizitie
)
where rownum = 1;

--2
select cod_excursie, count(1)
from achizitioneaza
group by cod_excursie;

--3
with tabel as (select ag.denumire, ag.oras,  count(1) as nr_excursii, avg(pret) as pret_mediu
from agentie ag
inner join excursie ex
on ag.id_agentie = ex.cod_agentie
group by ag.oras, ag.denumire )

select *
from tabel;

--4a
with nr_excursii as ( select count(1) as nr_excursii, t.nume, t.prenume 
from turist t
inner join achizitioneaza ac
on ac.cod_turist = t.id_turist
group by t.nume, t.prenume )
select nume, prenume
from nr_excursii
where nr_excursii >=2; 

--4b
with nr_excursii as ( select count(1) as nr_excursii, t.nume, t.prenume 
from turist t
inner join achizitioneaza ac
on ac.cod_turist = t.id_turist
group by t.nume, t.prenume )
select count(1)
from nr_excursii
where nr_excursii >=2; 

--5
select *
from turist t
inner join achizitioneaza ac
on ac.cod_turist = t.id_turist
inner join excursie ex
on ex.id_excursie = ac.cod_excursie
where ex.destinatie != 'Paris';

WITH turisti_paris AS (
    SELECT *
    FROM turist t
    INNER JOIN achizitioneaza a
    ON t.id_turist = a.cod_turist
    INNER JOIN excursie e
    ON a.cod_excursie = e.id_excursie
    WHERE e.destinatie LIKE 'Paris')
SELECT *
FROM turist
WHERE (nume, prenume) NOT IN (
    SELECT nume, prenume
    FROM turisti_paris);


--6
select id_turist, nume, prenume
from turist t1
where 2 <= (
            select count( distinct destinatie)
            from turist t2
            inner join achizitioneaza ac
            on ac.cod_turist = t2.id_turist
            inner join excursie ex
            on ex.id_excursie = ac.cod_excursie
            where t1.id_turist = t2.id_turist);
            
--7
select denumire,
    NVL(
        (select sum(pret - pret * NVL(discount, 0)) 
        from achizitioneaza
        inner join excursie
        on cod_excursie = id_excursie
        where cod_agentie = id_agentie),
    0) as profit 
from agentie;

--8
select denumire, oras 
from agentie
where 3 <= (select count(1)
            from excursie
            where pret < 2000 and id_agentie = cod_agentie
);

--9
select *
from excursie
where id_excursie not in (select cod_excursie
                          from achizitioneaza
                        );

--10
select id_excursie, e.denumire, pret, destinatie, nvl( a.denumire, 'agentie necunoscuta') as nume_angentie  , oras
from excursie e
left join agentie a
on cod_agentie = id_agentie;

--11
select denumire, pret, cod_agentie
from excursie
where pret > (select pret
              from excursie
              where denumire = 'Orasul luminilor' and cod_agentie = 10  
      
      );


--12
select *
from turist
inner join achizitioneaza
on id_turist = cod_turist
where (data_end - data_start) >= 10;

--13
select id_excursie, denumire, pret
from excursie
inner join achizitioneaza 
on id_excursie = cod_excursie
inner join turist
on cod_turist = id_turist
where (SYSDATE - data_nastere)/365.25 <= 35;
                        
--14
select distinct id_turist, nume, prenume
from turist
-- daca fac direct cu egal nu ii ia pe cei care n au achizitionat nimic 
where id_turist not in (
          select distinct id_turist
          from turist
          inner join achizitioneaza 
          on cod_turist = id_turist
          inner join excursie 
          on cod_excursie = id_excursie
          inner join agentie a
          on id_agentie = cod_agentie
          where a.oras like 'Bucuresti');
  
--15
select distinct id_turist, nume, prenume
from turist
where id_turist in (
                    select id_turist
                    from turist
                    inner join achizitioneaza 
                    on cod_turist = id_turist
                    inner join excursie e
                    on cod_excursie = id_excursie
                    inner join agentie ag
                    on cod_agentie = id_agentie
                    where e.denumire like '%1 Mai%' and ag.oras = 'Bucuresti');

--16
select id_turist, nume, prenume, e.denumire
from turist
inner join achizitioneaza 
on cod_turist = id_turist
inner join excursie e
on cod_excursie = id_excursie
inner join agentie ag
on cod_agentie = id_agentie
where ag.denumire = 'Smart Tour';

--17
select *
from(
  select id_excursie, nr_locuri, count(1) as locuri_ocupate
  from achizitioneaza
  inner join excursie
  on cod_excursie = id_excursie
  where data_start = '14-AUG-11'
  group by id_excursie, nr_locuri) tabel
where tabel.locuri_ocupate = tabel.nr_locuri

--18
select id_turist, MAX(data_achizitie)
from (select id_turist, cod_excursie, data_achizitie
      from turist
      inner join achizitioneaza
      on id_turist = cod_turist
      inner join excursie
      on cod_excursie = id_excursie
      order by data_achizitie desc) tabel
group by id_turist;

--19
with tabel as (select *
              from excursie
              order by pret desc
              )
select *
from tabel
where rownum <= 5;

--20
select nume, prenume
from turist
inner join achizitioneaza
on id_turist = cod_turist
where to_char(data_start, 'MON') = to_char(data_nastere, 'MON');

--21
with nr_pers as (
          select cod_excursie, count(1) as nr
          from achizitioneaza
          group by cod_excursie
),
doua as (
        select *
        from nr_pers
        where nr = 2    
)
select distinct nume, prenume
from turist
inner join achizitioneaza
on id_turist = cod_turist
inner join doua
using (cod_excursie)
inner join excursie
on id_excursie = cod_excursie
inner join agentie
on id_agentie = cod_agentie
where oras like 'Constanta';


-22
with clasificare as (
          select id_excursie, durata,
          case 
          when durata <= 5 then 'mica'
          when durata <= 19 then 'medie'
          else 'lunga'
          end
          as categorie
          from excursie
)
select *
from clasificare
order by durata;

--23
with tabel as ( select id_excursie, cod_agentie, oras
                from excursie
                inner join agentie
                on id_agentie = cod_agentie
              )
select 
  (select count(*) from tabel  where oras = 'Constanta') as  "Nr ex Constanta",
  (select count(*) from tabel  where oras = 'Bucuresti')as  "Nr ex Bucuresti",
  (select count(*) from tabel ) as  "Nr excurii"
from dual;

--24 nu erau de 24 de ani
select id_excursie, denumire
from excursie
inner join achizitioneaza
on cod_excursie = id_excursie
inner join turist
on id_turist = cod_turist
where round((SYSDATE - data_nastere) / 365.25) = 35;

--25
with tabel as ( select cod_agentie, pret, destinatie
                from excursie
                inner join agentie
                on id_agentie = cod_agentie
                inner join achizitioneaza
                on cod_excursie = id_excursie
              )
select cod_agentie, destinatie, sum(pret), grouping(cod_agentie), grouping(destinatie)
from tabel
group by rollup(cod_agentie, destinatie);

--26
with preturi_medii as ( select id_agentie, oras, avg(pret) as pret_mediu
                        from agentie
                        inner join excursie
                        on id_agentie = cod_agentie
                        group by id_agentie, oras            
)

select t1.id_agentie, t1.oras, t2.id_agentie, t2.pret_mediu
from preturi_medii t1
inner join preturi_medii t2
on t1.oras = t2.oras and t1.id_agentie != t2.id_agentie;







