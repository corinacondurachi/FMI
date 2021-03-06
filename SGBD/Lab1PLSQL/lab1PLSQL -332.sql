<< principal >> 
DECLARE
    v_client_id       NUMBER(4) := 1600;
    v_client_nume     VARCHAR2(50) := 'N1';
    v_nou_client_id   NUMBER(3) := 500;
BEGIN
    << secundar >> 
    DECLARE
        v_client_id         NUMBER(4) := 0;
        v_client_nume       VARCHAR2(50) := 'N2';
        v_nou_client_id     NUMBER(3) := 300;
        v_nou_client_nume   VARCHAR2(50) := 'N3';
    BEGIN
        v_client_id := v_nou_client_id;
        principal.v_client_nume := v_client_nume
                                   || ' '
                                   || v_nou_client_nume; 
       --pozitia 1 
        DBMS_OUTPUT.PUT_LINE(v_client_id);
      DBMS_OUTPUT.PUT_LINE(v_client_nume);
      DBMS_OUTPUT.PUT_LINE(v_nou_client_nume);
    END;
    v_client_id := ( v_client_id * 12 ) / 10; 
    --pozitia 2 
    DBMS_OUTPUT.PUT_LINE(v_client_id);
    DBMS_OUTPUT.PUT_LINE(v_client_nume);
END;

/

Determina?i:
- valoarea variabilei v_client_id la pozi?ia 1;--300
- valoarea variabilei v_client_nume la pozi?ia 1; --N2
- valoarea variabilei v_nou_client_id la pozi?ia 1; --300
- valoarea variabilei v_nou_client_nume la pozi?ia 1; --n3
- valoarea variabilei v_id_client la pozi?ia 2; --1600*12/10
- valoarea variabilei v_client_nume la pozi?ia 2. --N2N3


--3
VARIABLE g_mesaj VARCHAR2(50)
BEGIN
  :g_mesaj := 'Invat PL/SQL';
END;
/
PRINT g_mesaj


BEGIN
  DBMS_OUTPUT.PUT_LINE('Invat PL/SQL');
END;
/

SELECT department_name
FROM employees e,
  departments d
WHERE e.department_id=d.department_id
GROUP BY department_name
HAVING COUNT(*) =
                  (SELECT MAX(COUNT(*))
                  FROM employees 
                  GROUP BY department_id
  );
  
DECLARE
  v_dep departments.department_name%TYPE;
BEGIN
  SELECT department_name
  INTO   v_dep
  FROM   employees e, departments d
  WHERE  e.department_id=d.department_id 
  GROUP BY department_name
  HAVING COUNT(*) = (SELECT MAX(COUNT(*))
                     FROM   employees
                     GROUP BY department_id);
  DBMS_OUTPUT.PUT_LINE('Departamentul '|| v_dep);
  end;
  /
  
  
SELECT department_name
FROM employees e,
  departments d
WHERE e.department_id=d.department_id
GROUP BY department_name
HAVING COUNT(*) =
  (SELECT min(COUNT(*)) FROM employees GROUP BY department_id
  );
  
  DECLARE
  v_dep departments.department_name%TYPE;
BEGIN
  SELECT department_name
  INTO   v_dep
  FROM   employees e, departments d
  WHERE  e.department_id=d.department_id 
  GROUP BY department_name
  HAVING COUNT(*) < (SELECT min(COUNT(*))  --=
                     FROM   employees
                     GROUP BY department_id);
  DBMS_OUTPUT.PUT_LINE('Departamentul '|| v_dep);
  Exception
     when Too_many_rows then
         DBMS_OUTPUT.PUT_LINE('Mai multe linii');
    when no_data_found then
        DBMS_OUTPUT.PUT_LINE('nicio linie');
  end;
  /
  --5
VARIABLE rezultat VARCHAR2(35)
BEGIN
  SELECT department_name
  INTO   :rezultat
  FROM   employees e, departments d
  WHERE  e.department_id=d.department_id 
  GROUP BY department_name
  HAVING COUNT(*) = (SELECT MAX(COUNT(*))
                     FROM   employees
                     GROUP BY department_id);
  DBMS_OUTPUT.PUT_LINE('Departamentul '|| :rezultat);
END;
/
PRINT rezultat


--6. Modifica?i exerci?iul anterior astfel încât s? 
--ob?ine?i ?i num?rul de angaja?i din departamentul respectiv.

SELECT department_name, count(*)
FROM employees e,
  departments d
WHERE e.department_id=d.department_id
GROUP BY department_name
HAVING COUNT(*) =
  (SELECT MAX(COUNT(*)) FROM employees GROUP BY department_id
  );
  
  
  
 DECLARE
  v_dep departments.department_name%TYPE;
   v_nr_angajati number(4);
BEGIN
  SELECT department_name, count(*)
  INTO   v_dep, v_nr_angajati
  FROM   employees e, departments d
  WHERE  e.department_id=d.department_id 
  GROUP BY department_name
  HAVING COUNT(*) = (SELECT max(COUNT(*))  --=
                     FROM   employees
                     GROUP BY department_id);
  DBMS_OUTPUT.PUT_LINE('Departamentul '|| v_dep|| ' si are '
                                   ||v_nr_angajati|| ' angajati');
  Exception
     when Too_many_rows then
         DBMS_OUTPUT.PUT_LINE('Mai multe linii');
    when no_data_found then
        DBMS_OUTPUT.PUT_LINE('nicio linie');
  end;
  /
  
  SET VERIFY OFF
DECLARE
   v_cod           employees.employee_id%TYPE:=&p_cod;
   v_bonus         NUMBER(8);
   v_salariu_anual NUMBER(8);
BEGIN
   SELECT salary*12 
   INTO   v_salariu_anual
   FROM   employees 
   WHERE  employee_id = v_cod;
   IF v_salariu_anual>=200001 
      THEN v_bonus:=20000;
   ELSIF v_salariu_anual >= 100001 
      THEN v_bonus:=10000;
      ELSE v_bonus:=5000;
   END IF;
   DBMS_OUTPUT.PUT_LINE('Bonusul este ' || v_bonus);
   exception
        when no_data_found then
        DBMS_OUTPUT.PUT_LINE('nicio linie');
END;
/
SET VERIFY ON

DECLARE
   v_cod           employees.employee_id%TYPE:=&p_cod;
   v_bonus         NUMBER(8);
   v_salariu_anual NUMBER(8);
BEGIN
   SELECT salary*12 INTO v_salariu_anual
   FROM   employees 
   WHERE  employee_id = v_cod;
   CASE WHEN v_salariu_anual>=200001 
             THEN v_bonus:=20000;
        WHEN v_salariu_anual >= 100001 
             THEN v_bonus:=10000;
        ELSE v_bonus:=5000;
   END CASE;
   DBMS_OUTPUT.PUT_LINE('Bonusul este ' || v_bonus);
exception
  when no_data_found then  
      DBMS_OUTPUT.PUT_LINE('nicio linie!');
END;
/

create table emp_mng as select * from employees;
select * from emp_mng;

DEFINE p_cod_sal= 1800
DEFINE p_cod_dept = 80 
DEFINE p_procent =20
DECLARE
  v_cod_sal emp_mng.employee_id%TYPE  := &p_cod_sal;
  v_cod_dept emp_mng.department_id%TYPE:= &p_cod_dept;
  v_procent NUMBER(8)                  :=&p_procent;
BEGIN
  UPDATE emp_mng
  SET department_id = v_cod_dept,
    salary =salary + (salary* v_procent/100)
  WHERE employee_id = v_cod_sal;
  IF SQL%ROWCOUNT =0 THEN
    DBMS_OUTPUT.PUT_LINE('Nu exista un angajat cu acest cod');
  ELSE
    DBMS_OUTPUT.PUT_LINE('Actualizare realizata');
  END IF;
END;
/ 
ROLLBACK;

create table zile_mng
(id number,
data date,
nume_zi varchar2(30));

DECLARE
  contor NUMBER(6) := 1;
  v_data DATE;
  maxim  NUMBER(2) := LAST_DAY(SYSDATE)-SYSDATE;
BEGIN
  LOOP
    v_data := sysdate+contor;
    INSERT INTO zile_mng VALUES
      (contor,v_data,TO_CHAR(v_data,'Day')
      );
    contor := contor + 1;
    EXIT WHEN contor > maxim;
  END LOOP;
END;
/

select  LAST_DAY(SYSDATE)-SYSDATE
from dual;

select sysdate+1
from dual;

select * from zile_mng;

--11. Rezolva?i cerin?a anterioar? folosind instruc?iunea WHILE.
DECLARE
  contor NUMBER(6) := 1;
  v_data DATE;
  maxim  NUMBER(2) := LAST_DAY(SYSDATE)-SYSDATE;BEGIN
  WHILE contor <= maxim 
  LOOP
    v_data := sysdate+contor;
    INSERT INTO zile_mng VALUES
      (contor,v_data,TO_CHAR(v_data,'Day')
      );
    contor := contor + 1;
  END LOOP;
END;
/

delete from zile_mng;


--12. Rezolva?i cerin?a anterioar? folosind instruc?iunea FOR.

DECLARE
  v_data DATE;
  maxim  NUMBER(2) := LAST_DAY(SYSDATE)-SYSDATE;
BEGIN
  FOR contor IN 1..maxim  LOOP
    v_data := sysdate+contor;
    INSERT INTO zile_mng VALUES
      (contor,v_data,TO_CHAR(v_data,'Day'));
  END LOOP;
END;
/
select * from zile_mng;

DECLARE
  i POSITIVE                :=1;
  max_loop CONSTANT POSITIVE:=10;
BEGIN
  LOOP
    i  :=i+1;
    IF i>max_loop THEN
      DBMS_OUTPUT.PUT_LINE('in loop i=' || i);
      GOTO urmator;
    END IF;
  END LOOP;
  <<urmator>>
  i:=1;
  DBMS_OUTPUT.PUT_LINE('dupa loop i=' || i);
END;
/

DECLARE
  i POSITIVE                :=1;
  max_loop CONSTANT POSITIVE:=10;
BEGIN
  i:=1;
  LOOP
    i:=i+1;
    DBMS_OUTPUT.PUT_LINE('in loop i=' || i);
    EXIT WHEN i>max_loop;
  END LOOP;
  i:=1;
  DBMS_OUTPUT.PUT_LINE('dupa loop i=' || i);
END;
/
DECLARE
  numar  NUMBER(3)    :=100;
  mesaj1 VARCHAR2(255):='text 1';
  mesaj2 VARCHAR2(255):='text 2';
BEGIN
              DECLARE
                numar  NUMBER(3)    :=1;
                mesaj1 VARCHAR2(255):='text 2';
                mesaj2 VARCHAR2(255):='text 3';
            BEGIN
              numar :=numar+1;
              mesaj2:=mesaj2||' adaugat in sub-bloc';
              DBMS_OUTPUT.PUT_LINE(numar);
            END;
numar :=numar+1;
mesaj1:=mesaj1||' adaugat un blocul principal';
mesaj2:=mesaj2||' adaugat in blocul principal';
END;
/

--Exercitii

--1
a) Valoarea variabilei numar în subbloc este:
b) Valoarea variabilei mesaj1 în subbloc este:
c) Valoarea variabilei mesaj2 în subbloc este:
d) Valoarea variabilei numar în bloc este:
e) Valoarea variabilei mesaj1 în bloc este:
f) Valoarea variabilei mesaj2 în bloc este:

-- a. 2
 -- b. text 2
 -- c. text 3 adaugat in sub-bloc
 -- d. 101
 -- e. text 1 adaugat un blocul principal
 -- f. text 2 adaugat in blocul principal
 
 DECLARE
numar number(3):=100;
mesaj1 varchar2(255):='text 1';
mesaj2 varchar2(255):='text 2';
BEGIN
DECLARE
numar number(3):=1;
mesaj1 varchar2(255):='text 2';
mesaj2 varchar2(255):='text 3';
BEGIN
numar:=numar+1;
mesaj2:=mesaj2||' adaugat in sub-bloc';
DBMS_OUTPUT.PUT_LINE('Numar Subbloc ' || numar);
DBMS_OUTPUT.PUT_LINE('Mesaj1 Subbloc ' || mesaj1);
DBMS_OUTPUT.PUT_LINE('Mesaj2 Subbloc ' || mesaj2);
END;
numar:=numar+1;
mesaj1:=mesaj1||' adaugat un blocul principal';
mesaj2:=mesaj2||' adaugat in blocul principal';
DBMS_OUTPUT.PUT_LINE('Numar Bloc ' || numar);
DBMS_OUTPUT.PUT_LINE('Mesaj1 Bloc ' || mesaj1);
DBMS_OUTPUT.PUT_LINE('Mesaj2 Bloc ' || mesaj2);
END;
/

-- 2a
SELECT luna_octombrie.book_date, NVL(imprumuturi_octombrie.num, 0) AS num
FROM (
    -- Generam toate imprumuturile facute in octombrie
    SELECT TO_DATE(book_date) AS book_date, COUNT(1) AS num
    FROM rental
    GROUP BY book_date
) imprumuturi_octombrie
RIGHT JOIN (
    -- Genereaza toate datele din luna octombrie
    SELECT TO_DATE(last_day('01-OCT-2020') - ROWNUM + 1) AS book_date
    FROM DUAL CONNECT BY ROWNUM < 31
) luna_octombrie
ON imprumuturi_octombrie.book_date = luna_octombrie.book_date
ORDER BY book_date;


-- 2b
DROP TABLE octombrie_cco;
CREATE TABLE octombrie_cco (
    id NUMBER PRIMARY KEY,
    data DATE NOT NULL UNIQUE
);
COMMIT;

DECLARE
    ziua DATE := '01-OCT-2020';
BEGIN
    FOR i IN 1..31 LOOP
        INSERT INTO octombrie_cco VALUES (i,ziua);
        ziua := ziua + 1;
    END LOOP;
END;
/

--select * from octombrie_cco;

SELECT data, NVL(num, 0) as number_bookings
FROM octombrie_cco
LEFT JOIN (
    SELECT TO_DATE(book_date) AS data, COUNT(1) AS num
    FROM rental
    GROUP BY book_date
)
USING (data)
ORDER BY data;


SELECT * FROM member;

3. Defini?i un bloc anonim în care s? se determine num?rul 
de filme (titluri) împrumutate de un membru al c?rui nume
este introdus de la tastatur?. Trata?i urm?toarele dou? situa?ii: 
nu exist? nici un membru cu nume dat; exist? mai mul?i membrii 
cu acela?i nume.



DECLARE
   numele member.last_name%type := '&input';
   rental_count NUMBER(2);
BEGIN

  SELECT COUNT(DISTINCT title_id)
  INTO rental_count
  FROM rental r, member m
  WHERE r.member_id = m.member_id
  and lower(m.last_name)= lower(numele);

  DBMS_OUTPUT.PUT_LINE(rental_count || ' filme imprumutate');

  EXCEPTION
    WHEN NO_DATA_FOUND THEN DBMS_OUTPUT.PUT_LINE('no data found');
    WHEN TOO_MANY_ROWS THEN DBMS_OUTPUT.PUT_LINE('too many rows');
 
END;
/

--ngao
--Velasquez
--Catchpole
4 filme imprumutate

4 filme imprumutate

0 filme imprumutate


DECLARE
 i POSITIVE:=1;
 max_loop CONSTANT POSITIVE:=10;
BEGIN
LOOP
 i:=i+1;
 IF i>max_loop THEN
 DBMS_OUTPUT.PUT_LINE('in loop i=' || i);
 GOTO urmator;
 END IF;
END LOOP;
<<urmator>>
i:=1;
DBMS_OUTPUT.PUT_LINE('dupa loop i=' || i);
END;
/


DECLARE
 i POSITIVE:=1;
 max_loop CONSTANT POSITIVE:=10;
BEGIN
 i:=1;
 LOOP
 i:=i+1;
 DBMS_OUTPUT.PUT_LINE('in loop i=' || i);
 EXIT WHEN i>max_loop;
 END LOOP;
 i:=1;
 DBMS_OUTPUT.PUT_LINE('dupa loop i=' || i);
END;
/

--4. Modifica?i problema anterioar? astfel încât s? afi?a?i ?i urm?torul text:
--- Categoria 1 (a împrumutat mai mult de 75% din titlurile existente)
--- Categoria 2 (a împrumutat mai mult de 50% din titlurile existente)
--- Categoria 3 (a împrumutat mai mult de 25% din titlurile existente)
--- Categoria 4 (altfel)



--4

DECLARE
  numele member.last_name%type := '&input';
  rental_id number;
  rental_count NUMBER(2);
  total_movies NUMBER;
  percent_rented NUMBER;
BEGIN

  SELECT COUNT(DISTINCT title_id)
  INTO rental_count
  FROM rental r, member m
  WHERE r.member_id = m.member_id
  and lower(m.last_name)= lower(numele);
  
  -- nr de filme in total
  select count(1) into total_movies
  from title;
  
  DBMS_OUTPUT.PUT_LINE(rental_count || ' filme imprumutate');
  
  percent_rented := rental_count / total_movies;
  IF percent_rented > 0.75 THEN
    DBMS_OUTPUT.PUT_LINE('Categoria 1');
  ELSIF percent_rented > 0.5 THEN
    DBMS_OUTPUT.PUT_LINE('Categoria 2');
  ELSIF percent_rented > 0.25 THEN
    DBMS_OUTPUT.PUT_LINE('Categoria 3');
  ELSE
    DBMS_OUTPUT.PUT_LINE('Categoria 4');
  END IF;
     
  EXCEPTION
    WHEN NO_DATA_FOUND THEN DBMS_OUTPUT.PUT_LINE('no data found');
    WHEN TOO_MANY_ROWS THEN DBMS_OUTPUT.PUT_LINE('too many rows');
 
END;
/

--5
--Crea?i tabelul member_*** (o copie a tabelului member). Ad?uga?i în acest tabel coloana
--discount, care va reprezenta procentul de reducere aplicat pentru membrii, în func?ie de categoria
--din care fac parte ace?tia:
--- 10% pentru membrii din Categoria 1
--- 5% pentru membrii din Categoria 1
--- 3% pentru membrii din Categoria 1
--- nimic
--Actualiza?i coloana discount pentru un membru al c?rui cod este dat de la tastatur?. Afi?a?i un
--mesaj din care s? reias? dac? actualizarea s-a produs sau nu.


create table member_mng2 as select * from member;
alter table member_mng2 add discount number default 0;
select * from member_mng2;

DECLARE
  numele member.last_name%type := '&input';
  rental_id number;
   rental_count NUMBER(2);
   total number(3);
   procent number(3);
BEGIN
  SELECT member_id
  INTO rental_id
  FROM  member m
  WHERE lower(m.last_name)= lower(numele);

  SELECT COUNT(DISTINCT title_id)
  INTO rental_count
  FROM rental r, member m
  WHERE r.member_id = m.member_id
  and lower(m.last_name)= lower(numele);

  DBMS_OUTPUT.PUT_LINE(rental_count || ' filme imprumutate');
  
  Select count(*)
  into total
  from title;
  
  procent := rental_count/total;
  if procent >=0.75
      then DBMS_OUTPUT.PUT_LINE('cat 1');
      update member_mng2 set discount=10 where member_id = rental_id;
      elsif procent>=0.5
          then DBMS_OUTPUT.PUT_LINE('cat 2');
          update member_mng2 set discount=5 where member_id = rental_id;
          elsif procent>=0.25
            then DBMS_OUTPUT.PUT_LINE('cat 3');
            update member_mng2 set discount=3 where member_id = rental_id;
            else DBMS_OUTPUT.PUT_LINE('cat 4');
  end if;
  EXCEPTION
    WHEN NO_DATA_FOUND THEN DBMS_OUTPUT.PUT_LINE('no data found');
    WHEN TOO_MANY_ROWS THEN DBMS_OUTPUT.PUT_LINE('too many rows');
 
END;
/


DECLARE
  numele member.last_name%type := '&input';
  rental_id number;
   rental_count NUMBER(2);
   total number(3);
   procent number(3);
BEGIN
--  SELECT member_id
--  INTO rental_id
--  FROM  member m
--  WHERE lower(m.last_name)= lower(numele);

  SELECT COUNT(DISTINCT title_id)
  INTO rental_count
  FROM rental r, member m
  WHERE r.member_id = m.member_id
  and lower(m.last_name)= lower(numele);

  DBMS_OUTPUT.PUT_LINE(rental_count || ' filme imprumutate');
  
  Select count(*)
  into total
  from title;
  
  procent := rental_count/total;
  if procent >=0.75
      then DBMS_OUTPUT.PUT_LINE('cat 1');
      update member_mng2 set discount=10 where member_id = rental_id;
      elsif procent>=0.5
          then DBMS_OUTPUT.PUT_LINE('cat 2');
          update member_mng2 set discount=5 where member_id = rental_id;
          elsif procent>=0.25
            then DBMS_OUTPUT.PUT_LINE('cat 3');
            update member_mng2 set discount=3 where member_id = rental_id;
            else DBMS_OUTPUT.PUT_LINE('cat 4');
            update member_mng2 set discount=null where member_id = rental_id;
  end if;
  if SQL%rowcount>0
    then DBMS_OUTPUT.PUT_LINE(' Discount adaugat pt membrul ' || Initcap(numele));
    else DBMS_OUTPUT.PUT_LINE(' Nu s a facut update');
  end if;
  
--  EXCEPTION
--    WHEN NO_DATA_FOUND THEN DBMS_OUTPUT.PUT_LINE('no data found');
--    WHEN TOO_MANY_ROWS THEN DBMS_OUTPUT.PUT_LINE('too many rows');
 
END;
/


--5 var tema


-- 5
DROP TABLE member_cco;
CREATE TABLE member_cco AS (
    SELECT * FROM member
);

ALTER TABLE member_cco
ADD discount NUMBER DEFAULT NULL;

UNDEFINE cod_membru;

DECLARE
  searched_member NUMBER;
  rental_count NUMBER(2);
  total_movies NUMBER;
  percent_rented NUMBER;
  original_discount NUMBER;
  new_discount NUMBER;

BEGIN
   searched_member := &cod_membru;

    SELECT COUNT(*) INTO rental_count
    FROM title
    INNER JOIN rental
    USING (title_id)
    WHERE member_id = searched_member;

    SELECT COUNT(*) INTO total_movies
    FROM title;


    SELECT discount INTO original_discount
    FROM member_cco
    WHERE member_id = searched_member;
    
    percent_rented := rental_count / total_movies;
    IF percent_rented >= 0.75 THEN
        new_discount := 0.10;
    ELSIF percent_rented >= 0.5 THEN
        new_discount := 0.05;
    ELSIF percent_rented >= 0.25 THEN
        new_discount := 0.03;
    ELSE
        new_discount := NULL;
    END IF;
    
    IF NVL(original_discount, 0) != NVL(new_discount, 0) THEN
        UPDATE member_cco
        SET discount = new_discount
        WHERE member_id = searched_member;

        DBMS_OUTPUT.PUT_LINE('Updated discount to be ' || new_discount);
    ELSE
        DBMS_OUTPUT.PUT_LINE('Discount is unchanged');
    END IF;
    
    EXCEPTION
    WHEN NO_DATA_FOUND THEN DBMS_OUTPUT.PUT_LINE('no data found');
    WHEN TOO_MANY_ROWS THEN DBMS_OUTPUT.PUT_LINE('too many rows');

END;

/
