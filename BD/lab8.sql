-- LDD

-- Ex1 a)
create table ANGAJATI_cco (
  cod_ang number(4), 
  nume varchar2(20), 
  prenume varchar2(20), 
  email char(15),
  data_ang date default sysdate, 
  job varchar2(10), 
  cod_sef number(4), 
  salariu number(8, 2), 
  cod_dep number(2)
);

drop table angajati_cco;

-- Ex1 b)
create table ANGAJATI_cco (
  cod_ang number(4) primary key, 
  nume varchar2(20) not null, 
  prenume varchar2(20), 
  email char(15),
  data_ang date default sysdate, 
  job varchar2(10), 
  cod_sef number(4), 
  salariu number(8, 2) not null, 
  cod_dep number(2)
);

drop table angajati_cco;

-- Ex1 c) var 1
create table ANGAJATI_cco (
  cod_ang number(4), 
  nume varchar2(20) not null, 
  prenume varchar2(20), 
  email char(15),
  data_ang date default sysdate, 
  job varchar2(10), 
  cod_sef number(4), 
  salariu number(8, 2) not null, 
  cod_dep number(2),
  primary key (cod_ang)
);


-- Ex1 c) var 2
create table ANGAJATI_cco (
  cod_ang number(4), 
  nume varchar2(20) not null, 
  prenume varchar2(20), 
  email char(15),
  data_ang date default sysdate, 
  job varchar2(10), 
  cod_sef number(4), 
  salariu number(8, 2) not null, 
  cod_dep number(2),
  constraint pk_angajati_ari primary key (cod_ang)
);

drop table angajati_cco;

-- Ex2
desc angajati_cco;
insert into angajati_ari(Cod_ang, Nume, Prenume, Job, Salariu, Cod_dep)
values (100, 'Nume1', 'Prenume1', 'Director', 20000, 10);

insert into angajati_cco
values (101, 'Nume2', 'Prenume2', 'Nume2', '02-02-2004', 'Inginer', 100, 10000, 10);

insert into angajati_cco
values (102, 'Nume3', 'Prenume3', 'Nume3', '05-06-2000', 'Analist', 101, 5000, 20);

insert into angajati_cco
values (103, 'Nume4', 'Prenume4', Null, Null, 'Inginer', 100, 9000, 20);

insert into angajati_cco(Cod_ang, Nume, Prenume, Email, Job, Cod_sef, Salariu, Cod_dep)
values (104, 'Nume5', 'Prenume5', 'Nume5', 'Analist', 101, 3000, 30);

commit;

select * from angajati_cco;

-- Ex3
create table angajati10_cco as select * from angajati_cco where cod_dep = 10;
select * from angajati10_cco; 

desc angajati10_cco;

-- Ex4
alter table angajati_cco
add (comision number (4,2));

select * from angajati_cco;

-- Ex5
-- nu merge pentru ca scadem dimensiunea coloanei
alter table angajati_cco
modify (salariu number(6, 2));


-- Ex6
alter table angajati_cco
modify (salariu default 1000);

-- Ex7
alter table angajati_cco
modify (salariu number(10, 2), comision number(2, 2));

-- Ex8
update angajati_cco
set comision = 0.1 where job like 'A%';

commit;

-- Ex9
alter table angajati_cco
modify (email varchar2(15));

-- Ex10
alter table angajati_cco
add (nr_telefon char(10) default '0700000000');

select * from angajati_cco;

-- Ex11
alter table angajati_cco
drop column nr_telefon;

rollback;

-- Ex12
rename angajati_cco to angajati3_cco;

-- Ex13
select * from tab;
rename angajati3_cco to angajati_cco;

-- Ex14
truncate table angajati10_cco;

select * from angajati10_cco;

-- Ex15
create table DEPARTAMENTE_cco (
  cod_dep number(2), 
  nume varchar2(15) not null, 
  cod_director number(4)
);

desc departamente_cco;

-- Ex16
insert into departamente_cco
values (10, 'Administrativ', 100);
insert into departamente_cco
values (20, 'Proiectare', 101);
insert into departamente_cco
values (30, 'Programare', Null);

select * from departamente_cco;

-- Ex17
alter table departamente_cco
add constraint pk_departamente_cco primary key (cod_dep);

-- Ex18

-- a)
alter table angajati_cco
add constraint fk_angajati_cco_dep_cco foreign key (cod_dep) references departamente_cco (cod_dep);

-- b)
drop table angajati_cco;

create table ANGAJATI_cco (
  cod_ang number(4) primary key, 
  nume varchar2(20) not null, 
  prenume varchar2(20), 
  email char(15) unique,
  data_ang date default sysdate,
  comision number(2, 2),
  job varchar2(10), 
  cod_sef number(4) references angajati_ari(cod_ang), 
  salariu number(8, 2) not null, 
  cod_dep number(2) check (cod_dep > 0),
  unique (nume, prenume),
  check (salariu > nvl(comision, 0) * 100),
  foreign key (cod_dep) references departamente_cco (cod_dep)
);

-- Ex19
desc angajati_cco;
insert into angajati_cco(Cod_ang, Nume, Prenume, Job, Salariu, Cod_dep)
values (100, 'Nume1', 'Prenume1', 'Director', 20000, 10);

insert into angajati_cco
values (101, 'Nume2', 'Prenume2', 'Nume2', '02-02-2004', 0.1, 'Inginer', 100, 10000, 10);

insert into angajati_cco
values (102, 'Nume3', 'Prenume3', 'Nume3', '05-06-2000', null, 'Analist', 101, 5000, 20);

insert into angajati_cco
values (103, 'Nume4', 'Prenume4', Null, Null, null, 'Inginer', 100, 9000, 20);

insert into angajati_cco(Cod_ang, Nume, Prenume, Email, Job, Cod_sef, Salariu, Cod_dep)
values (104, 'Nume5', 'Prenume5', 'Nume5', 'Analist', 101, 3000, 30);

commit;

-- Ex21
-- nu functioneaza
drop table departamente_cco;

-- Ex24
-- nu functioneaza pentru ca avem randuri care incalca constrangerile
alter table angajati_cco
modify (email not null);

-- Ex25
-- nu functioneaza pt ca dep 50 nu exista in tabelul referentiat (departamente_cco)
insert into angajati_cco(Cod_ang, Nume, Prenume, Email, Job, Cod_sef, Salariu, Cod_dep)
values (105, 'Nume6', 'Prenume6', 'Nume6', 'Analist', 101, 3000, 50);

-- Ex26
insert into departamente_cco
values (60, 'Analiza', null);
commit;

-- Ex27
-- fara a specifica clauza ON CASCADE la nivel de FK constraint nu se poate realiza 
-- delete pe un row parinte care e referentiat de randuri din tabelul copil
delete from departamente_cco where cod_dep = 20;

-- Ex28
delete from departamente_cco where cod_dep = 60;
rollback;

--29
-- nu merge deoarece nu exista cheia primara
insert into angajati_cco(Cod_ang, Nume, Prenume, Email, Job, Cod_sef, Salariu, Cod_dep)
values (107, 'Nume7', 'Prenume7', 'Nume7', 'Analist', 114, 3000, 50);

--30
--am adaugat
insert into angajati_cco(Cod_ang, Nume, Prenume, Email, Job, Cod_sef, Salariu, Cod_dep)
values (114, 'Nume6', 'Prenume6', 'Nume6', 'Analist', 104, 3000, 10);

commit;

--nu imi da voie sa adaug si nu inteleg de ce
insert into angajati_cco(Cod_ang, Nume, Prenume, Email, Job, Cod_sef, Salariu, Cod_dep)
values (117, 'Nume7', 'Prenume7', 'Nume7', 'Inginer', 114, 2000, 10);

select * from angajati_cco;


--31
SELECT table_name, constraint_name, column_name
FROM user_cons_columns
WHERE LOWER(table_name) IN ('angajati_cco');

alter table angajati_cco
drop constraint SYS_C00344169;

alter table angajati_cco
add constraint fk_angajati_dep_cco foreign key(cod_dep) references departamente_cco(cod_dep) on delete cascade;

-- Ex32
delete from departamente_cco
where cod_dep = 20;

select * from departamente_cco;

rollback;

--33
-- la fk am vzt ca se mai pune cv si nu am inteles ce 
alter table angajati_cco
add constraint fk_angajati_cco_dep_cco foreign key (cod_dep) references departamente_cco (cod_dep);

alter table angajati_cco
add constraint fk_departamente_cco foreign key (cod_director) references angajati_cco (cod_angajat);

--34
-- dupa ce se sterge in departamente_cco ramane valoarea 102
update departamente_cco
set cod_director = 102
where cod_dep = 30;

delete from angajati_cco where cod_ang = 102;

select * from departamente_cco;
select * from angajati_cco;

rollback;

--e posibila suprimarea ang 101? cred ca da, dar nu stiu cum 

--35
alter table angajati_cco
add constraint constraint_nume
  check (salariu < 30000);

--36
-- nu merge check constraint violated
update angajati_cco
set salariu = 35000
where cod_ang = 101;

--37
-- adauga valoarea, insa nu merge setata din nou constrangerea intrucat nu poate fi validata
alter table angajati_cco
drop constraint constraint_nume;

update angajati_cco
set salariu = 35000
where cod_ang = 101;

alter table angajati_cco
add constraint constraint_nume2
  check (salariu < 30000);









