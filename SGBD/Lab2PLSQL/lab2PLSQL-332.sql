DECLARE
 x NUMBER(1) := 5;
 y x%TYPE := NULL;
BEGIN
 IF x <> y THEN
 DBMS_OUTPUT.PUT_LINE ('valoare <> null este = true');
 ELSE
 DBMS_OUTPUT.PUT_LINE ('valoare <> null este != true');
 END IF;

 x := NULL;
 IF x = y THEN
 DBMS_OUTPUT.PUT_LINE ('null = null este = true');
 ELSE
 DBMS_OUTPUT.PUT_LINE ('null = null este != true');
 END IF;
END;
/

--2
--a
DECLARE
 TYPE emp_record IS RECORD
 (cod employees.employee_id%TYPE,
 salariu employees.salary%TYPE,
 job employees.job_id%TYPE);
 v_ang emp_record;
BEGIN
 v_ang.cod:=700;
 v_ang.salariu:= 9000;
 v_ang.job:='SA_MAN';
 DBMS_OUTPUT.PUT_LINE ('Angajatul cu codul '|| v_ang.cod ||
 ' si jobul ' || v_ang.job || ' are salariul ' || v_ang.salariu);
END;
/
--b
DECLARE
 TYPE emp_record IS RECORD
 (cod employees.employee_id%TYPE,
 salariu employees.salary%TYPE,
 job employees.job_id%TYPE);
 v_ang emp_record;
BEGIN
SELECT employee_id, salary, job_id
INTO v_ang
FROM employees
WHERE employee_id = 101;
DBMS_OUTPUT.PUT_LINE ('Angajatul cu codul '|| v_ang.cod ||
 ' si jobul ' || v_ang.job || ' are salariul ' || v_ang.salariu);
END;
/

DECLARE
 TYPE emp_record IS RECORD
 (cod employees.employee_id%TYPE,
 salariu employees.salary%TYPE,
 job employees.job_id%TYPE);
 v_ang emp_record;
BEGIN
SELECT employee_id, salary, job_id
 INTO v_ang.cod, v_ang.salariu, v_ang.job
 FROM employees
 WHERE employee_id = 101;
DBMS_OUTPUT.PUT_LINE ('Angajatul cu codul '|| v_ang.cod ||
 ' si jobul ' || v_ang.job || ' are salariul ' || v_ang.salariu);
END;
/

create table emp_mng2 as select * from employees;

DECLARE
 TYPE emp_record IS RECORD
 (cod employees.employee_id%TYPE,
 salariu employees.salary%TYPE,
 job employees.job_id%TYPE);
 v_ang emp_record;
BEGIN
DELETE FROM emp_mng2
WHERE employee_id=100
RETURNING employee_id, salary, job_id INTO v_ang;
DBMS_OUTPUT.PUT_LINE ('Angajatul cu codul '|| v_ang.cod ||
 ' si jobul ' || v_ang.job || ' are salariul ' || v_ang.salariu);
END;
/

select * from emp_mng2  where employee_id =100;
ROLLBACK;

--3

DECLARE
v_ang1 employees%ROWTYPE;
v_ang2 employees%ROWTYPE;
BEGIN
-- sterg angajat 100 si mentin in variabila linia stearsa
 DELETE FROM emp_mng2
 WHERE employee_id = 100
 RETURNING employee_id, first_name, last_name, email, phone_number,
 hire_date, job_id, salary, commission_pct, manager_id,
 department_id
 INTO v_ang1;
-- inserez in tabel linia stearsa
 INSERT INTO emp_mng2
 VALUES v_ang1;
-- sterg angajat 101
 DELETE FROM emp_mng2
 WHERE employee_id = 101;
 -- obtin datele din tabelul employees
 SELECT *
 INTO v_ang2
 FROM employees
 WHERE employee_id = 101;
-- inserez o linie oarecare in emp_mng2
 INSERT INTO emp_mng2
 VALUES(1000,'FN','LN','E',null,sysdate, 'AD_VP',1000, null,100,90);
-- modific linia adaugata anterior cu valorile variabilei v_ang2
 UPDATE emp_mng2
 SET ROW = v_ang2
 WHERE employee_id = 1000;
END;
/
rollback;
select * from emp_mng2  where employee_id in (100, 101, 1000);



DECLARE
 TYPE tablou_indexat IS TABLE OF NUMBER INDEX BY PLS_INTEGER;
 t tablou_indexat;
BEGIN
-- punctul a
 FOR i IN 1..10 LOOP
 t(i):=i;
 END LOOP;
 DBMS_OUTPUT.PUT('Tabloul are ' || t.COUNT ||' elemente: ');
 FOR i IN t.FIRST..t.LAST LOOP
 DBMS_OUTPUT.PUT(t(i) || ' ');
 END LOOP;
 DBMS_OUTPUT.NEW_LINE;
-- punctul b
 FOR i IN 1..10 LOOP
       IF i mod 2 = 1 THEN t(i):=null;
       END IF;
 END LOOP;
 DBMS_OUTPUT.PUT('Tabloul are ' || t.COUNT ||' elemente: ');
 FOR i IN t.FIRST..t.LAST LOOP
 DBMS_OUTPUT.PUT(nvl(t(i), 0) || ' ');
 END LOOP;
 DBMS_OUTPUT.NEW_LINE;
 
 FOR i IN t.FIRST..t.LAST LOOP
 DBMS_OUTPUT.PUT(t(i) || ' ');
 END LOOP;
 DBMS_OUTPUT.NEW_LINE;
-- punctul c
 t.DELETE(t.first);
 t.DELETE(5,7);
 t.DELETE(t.last);
 DBMS_OUTPUT.PUT_LINE('Primul element are indicele ' || t.first ||
 ' si valoarea ' || nvl(t(t.first),0));
DBMS_OUTPUT.PUT_LINE('Ultimul element are indicele ' || t.last ||
 ' si valoarea ' || nvl(t(t.last),0));
 DBMS_OUTPUT.PUT('Tabloul are ' || t.COUNT ||' elemente: ');
 FOR i IN t.FIRST..t.LAST LOOP
 IF t.EXISTS(i) THEN
 DBMS_OUTPUT.PUT(nvl(t(i), 0)|| ' ');
 END IF;
 END LOOP;
 DBMS_OUTPUT.NEW_LINE;
-- punctul d
 t.delete;
 DBMS_OUTPUT.PUT_LINE('Tabloul are ' || t.COUNT ||' elemente.');
END;
/
--5
DECLARE
 TYPE tablou_indexat IS TABLE OF emp_mng2%ROWTYPE
 INDEX BY BINARY_INTEGER;
 t tablou_indexat;
BEGIN
-- stergere din tabel si salvare in tablou
 DELETE FROM emp_mng2
 WHERE ROWNUM<= 2
 RETURNING employee_id, first_name, last_name, email, phone_number,
 hire_date, job_id, salary, commission_pct, manager_id,
 department_id
 BULK COLLECT INTO t;
--afisare elemente tablou
 DBMS_OUTPUT.PUT_LINE (t(1).employee_id ||' ' || t(1).last_name);
 DBMS_OUTPUT.PUT_LINE (t(2).employee_id ||' ' || t(2).last_name);
--inserare cele 2 linii in tabel
 INSERT INTO emp_mng2 VALUES t(1);
 INSERT INTO emp_mng2 VALUES t(2);
 END;
/
select * from emp_mng2;

--6
DECLARE
 TYPE tablou_imbricat IS TABLE OF NUMBER;
 t tablou_imbricat := tablou_imbricat();
BEGIN
-- punctul a
 FOR i IN 1..10 LOOP
 t.extend;
 t(i):=i;
 END LOOP;
 DBMS_OUTPUT.PUT('Tabloul are ' || t.COUNT ||' elemente: ');

 FOR i IN t.FIRST..t.LAST LOOP
 DBMS_OUTPUT.PUT(t(i) || ' ');
 END LOOP;
 DBMS_OUTPUT.NEW_LINE;
 -- punctul b
 FOR i IN 1..10 LOOP
 IF i mod 2 = 1 THEN t(i):=null;
 END IF;
 END LOOP;
 DBMS_OUTPUT.PUT('Tabloul are ' || t.COUNT ||' elemente: ');
 FOR i IN t.FIRST..t.LAST LOOP
 DBMS_OUTPUT.PUT(nvl(t(i), 0) || ' ');
 END LOOP;
 DBMS_OUTPUT.NEW_LINE;
-- punctul c
 t.DELETE(t.first);
 t.DELETE(5,7);
 t.DELETE(t.last);
 DBMS_OUTPUT.PUT_LINE('Primul element are indicele ' || t.first ||
 ' si valoarea ' || nvl(t(t.first),0));
 DBMS_OUTPUT.PUT_LINE('Ultimul element are indicele ' || t.last ||
 ' si valoarea ' || nvl(t(t.last),0));
 DBMS_OUTPUT.PUT('Tabloul are ' || t.COUNT ||' elemente: ');
 FOR i IN t.FIRST..t.LAST LOOP
 IF t.EXISTS(i) THEN
 DBMS_OUTPUT.PUT(nvl(t(i), 0)|| ' ');
 END IF;
 END LOOP;
 DBMS_OUTPUT.NEW_LINE;
-- punctul d
 t.delete;
 DBMS_OUTPUT.PUT_LINE('Tabloul are ' || t.COUNT ||' elemente.');
END;
/

7. Declara?i un tip tablou imbricat de caractere ?i o variabil? de acest tip. Ini?ializa?i variabila cu
urm?toarele valori: m, i, n, i, m. Afi?a?i con?inutul tabloului, de la primul la ultimul element ?i
invers. ?terge?i elementele 2 ?i 4 ?i apoi afi?a?i con?inutul tabloului.
DECLARE
TYPE tablou_imbricat IS TABLE OF CHAR(1);
t tablou_imbricat := tablou_imbricat('m', 'i', 'n', 'i', 'm');
 i INTEGER;
BEGIN
i := t.FIRST;
WHILE i <= t.LAST LOOP
 DBMS_OUTPUT.PUT(t(i));
 i := t.NEXT(i);
END LOOP;
DBMS_OUTPUT.NEW_LINE;
 i := t.LAST;
WHILE i >= t.FIRST LOOP
 DBMS_OUTPUT.PUT(t(i));
 i := t.PRIOR(i);
  END LOOP;
DBMS_OUTPUT.NEW_LINE;
 t.delete(2);
 t.delete(4);
      i := t.FIRST;
      WHILE i <= t.LAST LOOP
       DBMS_OUTPUT.PUT(t(i));
       i := t.NEXT(i);
      END LOOP;
      DBMS_OUTPUT.NEW_LINE;
DBMS_OUTPUT.PUT_LINE('TEST');
       FOR i IN t.FIRST..t.LAST LOOP
       IF t.EXISTS(i) THEN
       DBMS_OUTPUT.PUT(nvl(t(i), 0));
       END IF;
       end loop;
       DBMS_OUTPUT.NEW_LINE;
 i := t.LAST;
WHILE i >= t.FIRST LOOP
 DBMS_OUTPUT.PUT(t(i));
 i := t.PRIOR(i);
END LOOP;
DBMS_OUTPUT.NEW_LINE;
END;
/

