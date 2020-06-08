
--1. Sa se afiseze instructorii nascuti in anul 1995 ce au tinut clase de Pilates la care au participat clienti

select nume, prenume, data_nasterii 
from instructor
inner join clasa c on (cod_instructor = id_instructor)
inner join disciplina d on (cod_disciplina = id_disciplina)
inner join participa_la p on (c.id_clasa = p.cod_clasa)
where to_char(data_nasterii,'yyyy') = 1995 and d.denumire = 'Pilates' and ( select count(1)
                                      from participa_la
                                      where cod_clasa = d.id_disciplina) != 0;


--2. Sa se afiseze denumirea disciplinei la care au participat cei mai multi clienti la o clasa

with tabel as(
select d.denumire as denumire, count(1) as clienti
from disciplina d
inner join clasa on (cod_disciplina = id_disciplina)
inner join participa_la p on (id_clasa = cod_clasa)
inner join client on (cod_client = id_client)
GROUP BY id_disciplina, denumire)
select denumire from tabel
where clienti = (select max(clienti) from tabel);


--3. Sa se afiseze numele clientilor care au participat in luna martie a anului 2020 la minim 2 clase de Aerobic

with tabel as (
  select c.nume, data_desfasurarii, cod_disciplina
  from client c 
    inner join participa_la p on (id_client = cod_client)
    inner join clasa cl on (p.cod_clasa = cl.id_clasa)
    inner join disciplina on (cod_disciplina = id_disciplina)
    where to_char(data_desfasurarii, 'yyyy') = 2020 and to_char(data_desfasurarii, 'mm') = 3 and cod_disciplina = (select id_disciplina
                                                                                                                   from disciplina
                                                                                                                   where lower(denumire) = 'aerobic'
                                                                                                                    )
    )
select nume
from tabel 
group by (nume)
having count(1) > 1;


--4. Sa se stearga participarile la clasele ce au fost tinute de 'Marin Antonescu'. Anulati modificarile facute.

delete from participa_la 
where cod_clasa in  (select id_clasa
                    from clasa where cod_instructor = (select id_instructor from instructor where lower(nume) = 'antonescu' and lower(prenume) = 'marin'));
rollback;

--5. Sa se adauge in tabelul clasa coloana nr_participanti cu o constrangere check > 0 --1

alter table clasa
add(nr_participanti NUMBER);

alter table clasa
add constraint
nr_participanti CHECK (nr_participanti > 0);

select * from clasa;
