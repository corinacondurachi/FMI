--27
with tabel as ( select cod_agentie, pret, destinatie
                from excursie
                inner join agentie
                on id_agentie = cod_agentie
                inner join achizitioneaza
                on cod_excursie = id_excursie
              )
select cod_agentie, destinatie, sum(pret)
from tabel
group by grouping sets((cod_agentie, destinatie), (cod_agentie), (destinatie), ());

--28
with tabel as ( select cod_agentie, to_char(data_achizitie, 'yyyy') as year, pret
                from excursie
                inner join agentie
                on id_agentie = cod_agentie
                inner join achizitioneaza
                on cod_excursie = id_excursie
              )
select cod_agentie, year, sum(pret)
from tabel
group by grouping sets((cod_agentie, year), ());

--29
with tabel as ( select cod_agentie, excursie.denumire
                from excursie
                left outer join agentie
                on id_agentie = cod_agentie
                left outer join achizitioneaza
                on cod_excursie = id_excursie
                left outer join turist
                on cod_turist = id_turist
              ),
destinatii as ( select excursie.denumire
                from excursie
                left outer join agentie
                on id_agentie = cod_agentie
                left outer join achizitioneaza
                on cod_excursie = id_excursie
                inner join turist
                on cod_turist = id_turist
                where to_char(data_nastere,'yyyy') = 84
               )
select denumire
from tabel
where cod_agentie is null and denumire not in (select * from destinatii);


--30 
create table turist_cco as select * from turist;
alter table turist_cco
add primary key (id_turist);

--pentru a vedea constrangerile click pe tabel sau select * from user_constraints

create table achizitioneaza_cco as select * from achizitioneaza;
alter table achizitioneaza_cco
add foreign key (cod_turist) references turist_cco (id_turist)
on delete cascade;

alter table achizitioneaza_cco
add foreign key (cod_excursie) references excursie_cco (id_excursie)
on delete cascade;

create table excursie_cco as select * from excursie;
alter table excursie_cco
add primary key (id_excursie);
alter table excursie_cco
add foreign key (cod_agentie) references agentie_cco (id_agentie)
on delete cascade;

create table agentie_cco as select * from agentie;
alter table agentie_cco
add primary key (id_agentie);

--31
commit;
select * from excursie_cco;

update achizitioneaza_cco
set discount = (select max(discount) from achizitioneaza_cco)
where ( select pret
        from excursie_cco
        where id_excursie = cod_excursie
) > (select avg(pret) from excursie_cco);

rollback;

--32
delete from excursie_cco e
where e.pret < (select avg(pret)
                from excursie_cco
                where cod_agentie = e.cod_agentie
);
rollback;

--33 
select * from excursie_cco;
alter table excursie_cco
drop constraint fk_ex_ag_cco;

insert into excursie_cco
values (110, 'Test', 2000, 'Spania', 5, 20, 15);

update excursie_cco
set cod_agentie = null
where cod_agentie not in (
    select distinct cod_agentie
    from agentie_gma
);

rollback;

--34
create view v_excursie_cco as
    select *
    from excursie_cco
    where cod_agentie = 10
    with check option;

select * from v_excursie_cco;

--clauza violata
insert into v_excursie_cco
VALUES (110, 'Test', 2000, 'Spania', 5, 20, 15);

--merge
insert into v_excursie_cco
VALUES (110, 'Test', 2000, 'Spania', 5, 10, 15);

--a adaugat si aici
select * from excursie_cco;

commit;

--35
truncate table achizitioneaza_cco;
savepoint a;

--36
insert into achizitioneaza_cco
select * from achizitioneaza
where to_char(data_achizitie, 'yyyy') = 2010;

update achizitioneaza_cco
set data_start = data_start + 31, 
    data_end = data_end + 31;

--37
update achizitioneaza_cco
set discount = 1.1 * discount
where (
        select cod_agentie
        from excursie_cco
        where cod_agentie = id_excursie
) = 10;

--38
delete from achizitioneaza_cco
where (select data_nastere
       from turist_cco
       where id_turist = cod_turist
       )
is null;

--39 nu stiu cu merge
-- e ok si asa?
truncate table achizitioneaza_cco;
insert into achizitioneaza_cco
select * from achizitioneaza;

--39
update excursie_cco
set pret = 0.9 * pret
where id_excursie in 
                    (
                    select cod_excursie
                    from(
                        select cod_excursie, count(1) as nr_achizitii
                        from achizitioneaza_cco
                        group by cod_excursie
                    ) achizitii
                    where nr_achizitii > 1 
                    );

--40
alter table turist_cco
modify (nume not null);

alter table turist_cco
add unique (nume, prenume);

--41
alter table achizitioneaza_cco
add check (data_start < data_end);

alter table achizitioneaza_cco
modify(data_achizitie default sysdate);

--42 nu prea am inteles cerinta si nici ce face asta
/*
sintaxa respectiva blocheaza liniile returnate pentru update - adica celelalte sesiuni care vor incerca sa faca update pe acele randuri vor esua, 
mai putin cea care le blocheaza. Dupa ce se face commit liniile se deblocheaza si ceilalti utilizatori le pot face update.
*/

select *
from achizitioneaza_cco
where data_start > sysdate
for update of data_achizitie;

update achizitioneaza_cco
set data_achizitie = default
where data_start > sysdate;

commit;

--43
with excursii_stanciu as
        ( select distinct cod_excursie
        from excursie_cco
        inner join achizitioneaza_cco
        on cod_excursie = id_excursie
        inner join turist_cco
        on cod_turist  = id_turist
        where nume = 'Stanciu'
        )
select t.nume, t.prenume
from turist t 
where not exists (
        (
        select cod_excursie
        from excursii_stanciu
        )
        minus
        (
        select cod_excursie
        from achizitioneaza_cco
        where cod_turist = t.id_turist
        )

);

--44
with excursii_stanciu as
        ( select distinct cod_excursie
        from excursie_cco
        inner join achizitioneaza_cco
        on cod_excursie = id_excursie
        inner join turist_cco
        on cod_turist  = id_turist
        where nume = 'Stanciu'
        )
select t.nume, t.prenume
from turist t 
where not exists (
        select cod_excursie
        from achizitioneaza_cco
        where cod_turist = t.id_turist and 
            cod_excursie not in (
                    select cod_excursie 
                    from excursii_stanciu
                    )
    
);

--45
with excursii_stanciu as
        ( select distinct cod_excursie
        from excursie_cco
        inner join achizitioneaza_cco
        on cod_excursie = id_excursie
        inner join turist_cco
        on cod_turist  = id_turist
        where nume = 'Stanciu'
        )
select t.nume, t.prenume
from turist t 
where not exists (
        (
        select cod_excursie
        from excursii_stanciu
        )
        minus
        (
        select cod_excursie
        from achizitioneaza_cco
        where cod_turist = t.id_turist
        )
) and not exists(
        (
        select cod_excursie
        from achizitioneaza_cco
        where cod_turist = t.id_turist
        )
        minus
        (
        select cod_excursie
        from excursii_stanciu
        )
);

--46 
insert into turist_cco
values (&id_turist, '&nume_turist', null, sysdate);

--47
update turist_cco
set prenume = 'Alex'
where id_turist = 7;

select nume, prenume
from turist_cco
where id_turist = 7;