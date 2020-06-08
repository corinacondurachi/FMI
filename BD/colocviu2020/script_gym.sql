drop table participa_la;
drop table clasa;
drop table instructor;
drop table client;
drop table disciplina;


create table instructor(
  id_instructor number(6) primary key,
  nume varchar2(20) not null,
  prenume varchar2(20) not null,
  data_nasterii date not null
);

create table client(
 id_client number(6) primary key,
 nume varchar2(20) not null,
 prenume varchar2(20) not null
);

create table disciplina(
  id_disciplina number(6) primary key,
  denumire varchar2(20)
);

create table clasa(
  id_clasa number(6) primary key,
  cod_disciplina number(6) references disciplina(id_disciplina),
  cod_instructor number(6) references instructor(id_instructor),
  data_desfasurarii date
);

create table participa_la(
  cod_client number(6) references client(id_client),
  cod_clasa number(6) references clasa(id_clasa),
  primary key (cod_client, cod_clasa)
);

-- instructor
insert into instructor values (1, 'Antonescu', 'Marin', to_date('05-01-1995', 'DD-MM-YYYY'));
insert into instructor values (2, 'Iliescu', 'Andreea', to_date('01-10-1995', 'DD-MM-YYYY'));
insert into instructor values (3, 'Tache', 'Ionel', to_date('19-07-1992', 'DD-MM-YYYY'));
insert into instructor values (4, 'Dumitrascu', 'Simona', to_date('12-05-1997', 'DD-MM-YYYY'));
insert into instructor values (5, 'Antochi', 'Razvan', to_date('26-09-1995', 'DD-MM-YYYY'));

-- client
insert into client values (1, 'Ivascu', 'Marina');
insert into client values (2, 'Margarint', 'Andrei');
insert into client values (3, 'Vasilache', 'Ionut');
insert into client values (4, 'Sima', 'Alexandra');
insert into client values (5, 'Ionescu', 'Cristina');
insert into client values (6, 'Ionascu', 'Stefania');
insert into client values (7, 'Dogeanu', 'Ioana');
insert into client values (8, 'Teofil', 'Vlad');
insert into client values (9, 'Moncea', 'Stefan');
insert into client values (10, 'Margineanu', 'George');

-- disciplina
insert into disciplina values (1, 'Aerobic');
insert into disciplina values (2, 'Pilates');
insert into disciplina values (3, 'Zumba');
insert into disciplina values (4, 'TRX');
insert into disciplina values (5, 'Circuit Training');
insert into disciplina values (6, 'Yoga');

-- clasa
insert into clasa values (1, 1, 1, to_date('01-03-2020 19:00', 'DD-MM-YYYY HH24:MI')); -- Aerobic, Antonescu
insert into clasa values (2, 2, 2, to_date('26-05-2020 10:00', 'DD-MM-YYYY HH24:MI')); -- Pilates, Iliescu
insert into clasa values (3, 2, 5, to_date('01-03-2020 20:00', 'DD-MM-YYYY HH24:MI')); -- Pilates, Antochi
insert into clasa values (4, 3, 1, to_date('15-03-2020 19:00', 'DD-MM-YYYY HH24:MI')); -- Zumba, Antonescu
insert into clasa values (5, 4, 4, to_date('15-03-2020 18:00', 'DD-MM-YYYY HH24:MI')); -- TRX, Dumitrascu
insert into clasa values (6, 3, 1, to_date('20-05-2020 19:00', 'DD-MM-YYYY HH24:MI')); -- Zumba, Antonescu
insert into clasa values (7, 2, 4, to_date('20-05-2020 19:00', 'DD-MM-YYYY HH24:MI')); -- Pilates, Dumitrascu
insert into clasa values (8, 1, 5, to_date('10-04-2020 15:00', 'DD-MM-YYYY HH24:MI')); -- Aerobic, Antochi
insert into clasa values (9, 6, 4, to_date('20-11-2019 20:00', 'DD-MM-YYYY HH24:MI')); -- Yoga, Dumitrascu
insert into clasa values (10, 5, 4, to_date('05-02-2020 18:00', 'DD-MM-YYYY HH24:MI')); -- Circuit Training, Dumitrascu
insert into clasa values (11, 1, 1, to_date('18-03-2020 19:00', 'DD-MM-YYYY HH24:MI')); -- Aerobic, Antonescu
insert into clasa values (12, 2, 2, to_date('15-03-2020 21:00', 'DD-MM-YYYY HH24:MI')); -- Pilates, Iliescu
insert into clasa values (13, 1, 3, to_date('12-03-2020 20:00', 'DD-MM-YYYY HH24:MI')); -- Aerobic, Tache

-- participa_la
insert into participa_la values (1, 1);
insert into participa_la values (2, 1);
insert into participa_la values (10, 1);
insert into participa_la values (5, 1);
insert into participa_la values (7, 1);
insert into participa_la values (4, 1);
insert into participa_la values (1, 2);
insert into participa_la values (6, 2);
insert into participa_la values (7, 2);
insert into participa_la values (8, 3);
insert into participa_la values (8, 4);
insert into participa_la values (1, 5);
insert into participa_la values (2, 5);
insert into participa_la values (2, 7);
insert into participa_la values (10, 5);
insert into participa_la values (1, 9);
insert into participa_la values (7, 9);
insert into participa_la values (10, 9);
insert into participa_la values (1, 11);
insert into participa_la values (2, 11);
insert into participa_la values (3, 11);
insert into participa_la values (5, 13);
insert into participa_la values (7, 13);
insert into participa_la values (8, 13);
insert into participa_la values (9, 13);
insert into participa_la values (10, 13);
insert into participa_la values (1, 8);
insert into participa_la values (5, 8);
insert into participa_la values (7, 8);
insert into participa_la values (9, 8);

commit;
