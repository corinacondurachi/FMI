import time
import copy


#vector directii
directii = [[-1, 0], [-1, 1], [0, 1], [1, 1], [1, 0], [1, -1], [0, -1], [-1, -1]]

def fill(matrice, pozitie, dirs, jucator):
    # inlocuiesc piesele jucatorului opus cu piesele jucatorului curent (cele din interior)
    # primeste pozitia unuia dintre X-uri si directiile de unde a ajuns
    # merg inpoi pana cat timp gasesc piese ale jucatorului opus si le schimb
    # ma opresc cand dau de piesa jucatorului curent
    for d in dirs:
        i, j = pozitie[0], pozitie[1]
        matrice[i][j] = jucator
        i -= d[0]
        j -= d[1]  # mergem inapoi

        while matrice[i][j] != jucator:
            matrice[i][j] = jucator
            i -= d[0]
            j -= d[1]

class Joc:
    """
    Clasa care defineste jocul. Se va schimba de la un joc la altul.
    """
    NR_COLOANE = 8
    NR_LINII = 8
    SIMBOLURI_JUC = ['A', 'N']  
    JMIN = None  # 'A'
    JMAX = None  # 'N'
    GOL = '.'
    def __init__(self, tabla=None):
        if tabla is not None:
            self.matr = tabla 
        else:
            self.matr = [[Joc.GOL for j in range(Joc.NR_COLOANE)] for i in range(Joc.NR_LINII)]
            self.matr[3][3] = self.matr[4][4] = self.SIMBOLURI_JUC[1]
            self.matr[4][3] = self.matr[3][4] = self.SIMBOLURI_JUC[0]


    def final(self, jucator):
        # returnam simbolul jucatorului castigator daca nu mai exista mutari sau tabla este plina
        # sau returnam 'remiza'
        # sau 'False' daca nu s-a terminat jocul
        rez = False

        pos = self.genereaza_posibilitati(jucator)
        if len(pos) == 0:
            scor_jmin = self.nr_piese(self.JMIN)  # trebuie calculate ambele pentru ca jocul se poate termina
            scor_jmax = self.nr_piese(self.JMAX)  # si inainte sa se umple tabla
            if scor_jmax > scor_jmin:
                return self.JMAX
            elif scor_jmax == scor_jmin:
                return 'remiza'
            else:
                return self.JMIN
            
        return False
    
    def genereaza_posibilitati(self, jucator):
        # returneaza un dictionar. cheile sunt pozitiile in care se pot pune piese
        # valorile sunt pozitiile din care a plecat ca sa se ajunga la ele
        jucator_opus = self.JMAX if jucator == self.JMIN else self.JMIN
        pos = {}

        for i in range(len(self.matr)):
            for j in range(len(self.matr[i])):
                if self.matr[i][j] == jucator:
                    for directie in directii:
                        i_nou, j_nou = i + directie[0], j + directie[1]
                        if i_nou not in range(self.NR_LINII) or j_nou not in range(self.NR_COLOANE) or self.matr[i_nou][j_nou] != jucator_opus:
                            continue  # trebuie sa treaca peste cel putin o piesa de cealalta culoare

                        gasit = True
                        while self.matr[i_nou][j_nou] == jucator_opus:
                            i_nou += directie[0]
                            j_nou += directie[1]
                            if i_nou not in range(self.NR_LINII) or j_nou not in range(self.NR_COLOANE) or self.matr[i_nou][j_nou] == jucator: #verific sa fie deja ocupata
                                gasit = False
                                break

                        if gasit:
                            if (i_nou, j_nou) not in pos.keys(): #verific daca nu aveam deja pozitia
                                pos[(i_nou, j_nou)] = [directie]
                            else:
                                pos[(i_nou, j_nou)].append(directie)

        return pos


    def mutari(self, jucator):
        l_mutari=[]

        posibilitati = self.genereaza_posibilitati(jucator)
        for pozitie, dirs in posibilitati.items():
            matr_noua = copy.deepcopy(self.matr)
            fill(matr_noua, pozitie, dirs, jucator)
            l_mutari.append(Joc(matr_noua))


        return l_mutari


    def nr_piese(self, jucator):
        nr = 0
        for line in self.matr:
            nr += line.count(jucator)
        return nr

    def fct_euristica(self):
        return self.nr_piese(Joc.JMAX) - self.nr_piese(Joc.JMIN)



    def estimeaza_scor(self, adancime, jucator):
        t_final = self.final(jucator)
        if t_final == Joc.JMAX :
            return (999 + adancime)
        elif t_final == Joc.JMIN:
            return (-999-adancime)
        elif t_final == 'remiza':
            return 0
        else:
            return self.fct_euristica()


    def __str__(self):
        sir = '  '
        for nr_col in range(self.NR_COLOANE):
            sir += str(nr_col) + ' '
        sir += '\n'

        for lin in range(self.NR_LINII):
            sir += (str(lin) + ' ' + " ".join([str(x) for x in self.matr[lin]]) + "\n")
        return sir


class Stare:
    """
    Clasa folosita de algoritmii minimax si alpha-beta
    Are ca proprietate tabla de joc
    Functioneaza cu conditia ca in cadrul clasei Joc sa fie definiti JMIN si JMAX (cei doi jucatori posibili)
    De asemenea cere ca in clasa Joc sa fie definita si o metoda numita mutari() care ofera lista cu
    configuratiile posibile in urma mutarii unui jucator
    """

    ADANCIME_MAX = None

    def __init__(self, tabla_joc, j_curent, adancime, parinte=None, scor=None):
        self.tabla_joc = tabla_joc
        self.j_curent = j_curent

        #adancimea in arborele de stari
        self.adancime=adancime

        #scorul starii (daca e finala) sau al celei mai bune stari-fiice (pentru jucatorul curent)
        self.scor=scor

        #lista de mutari posibile din starea curenta
        self.mutari_posibile=[]

        #cea mai buna mutare din lista de mutari posibile pentru jucatorul curent
        self.stare_aleasa=None

    def jucator_opus(self):
        if self.j_curent==Joc.JMIN:
            return Joc.JMAX
        else:
            return Joc.JMIN

    def mutari(self):
        l_mutari=self.tabla_joc.mutari(self.j_curent)
        juc_opus=self.jucator_opus()
        l_stari_mutari=[Stare(mutare, juc_opus, self.adancime-1, parinte=self) for mutare in l_mutari]

        return l_stari_mutari


    def __str__(self):
        sir= str(self.tabla_joc) + "(Juc curent: "+self.j_curent+")\n"
        return sir



""" Algoritmul MinMax """

def min_max(stare):

    if stare.adancime==0 or stare.tabla_joc.final(stare.j_curent) :
        stare.scor=stare.tabla_joc.estimeaza_scor(stare.adancime, stare.j_curent)
        return stare

    #calculez toate mutarile posibile din starea curenta
    stare.mutari_posibile=stare.mutari()

    #aplic algoritmul minimax pe toate mutarile posibile (calculand astfel subarborii lor)
    mutari_scor=[min_max(mutare) for mutare in stare.mutari_posibile]

    if stare.j_curent==Joc.JMAX :
        #daca jucatorul e JMAX aleg starea-fiica cu scorul maxim
        stare.stare_aleasa = max(mutari_scor, key=lambda x: x.scor)
    else:
        #daca jucatorul e JMIN aleg starea-fiica cu scorul minim
        stare.stare_aleasa = min(mutari_scor, key=lambda x: x.scor)

    stare.scor=stare.stare_aleasa.scor
    return stare



def alpha_beta(alpha, beta, stare):
    if stare.adancime==0 or stare.tabla_joc.final() :
        stare.scor = stare.tabla_joc.estimeaza_scor(stare.adancime)
        return stare

    if alpha >= beta:
        return stare #este intr-un interval invalid deci nu o mai procesez

    stare.mutari_posibile = stare.mutari()

    if stare.j_curent == Joc.JMAX :
        scor_curent = float('-inf')

        for mutare in stare.mutari_posibile:
            #calculeaza scorul
            stare_noua = alpha_beta(alpha, beta, mutare)

            if (scor_curent < stare_noua.scor):
                stare.stare_aleasa = stare_noua
                scor_curent = stare_noua.scor
            if(alpha < stare_noua.scor):
                alpha = stare_noua.scor
                if alpha >= beta:
                    break

    elif stare.j_curent == Joc.JMIN :
        scor_curent = float('inf')

        for mutare in stare.mutari_posibile:
            stare_noua = alpha_beta(alpha, beta, mutare)

            if (scor_curent > stare_noua.scor):
                stare.stare_aleasa = stare_noua
                scor_curent = stare_noua.scor

            if(beta > stare_noua.scor):
                beta = stare_noua.scor
                if alpha >= beta:
                    break

    stare.scor = stare.stare_aleasa.scor

    return stare



def afis_daca_final(stare_curenta):

    final = stare_curenta.tabla_joc.final()
    if(final):
        if (final=="remiza"):
            print("Remiza!")
        else:
            print("A castigat "+final)

        return True

    return False



def main():
    #initializare algoritm
    raspuns_valid=False
    while not raspuns_valid:
        tip_algoritm=input("Algorimul folosit? (raspundeti cu 1 sau 2)\n 1.Minimax\n 2.Alpha-beta\n ")
        if tip_algoritm in ['1','2']:
            raspuns_valid=True
        else:
            print("Nu ati ales o varianta corecta.")

    # initializare ADANCIME_MAX
    raspuns_valid = False
    while not raspuns_valid:
        n = input("Adancime maxima a arborelui: ")
        if n.isdigit():
            Stare.ADANCIME_MAX = int(n)
            raspuns_valid = True
        else:
            print("Trebuie sa introduceti un numar natural nenul.")


    # initializare jucatori
    [s1, s2] = Joc.SIMBOLURI_JUC.copy()  # lista de simboluri posibile
    raspuns_valid = False
    while not raspuns_valid:
        Joc.JMIN = str(input("Doriti sa jucati cu {} sau cu {}? ".format(s1, s2))).upper()
        if (Joc.JMIN in Joc.SIMBOLURI_JUC):
            raspuns_valid = True
        else:
            print("Raspunsul trebuie sa fie {} sau {}.".format(s1, s2))
    Joc.JMAX = s1 if Joc.JMIN == s2 else s2

    #initializare tabla
    tabla_curenta = Joc()
    print("Tabla initiala")
    print(str(tabla_curenta))

    #creare stare initiala
    stare_curenta = Stare(tabla_curenta, Joc.SIMBOLURI_JUC[0], Stare.ADANCIME_MAX)

    linie = -1
    coloana = -1
    while True :
        if (stare_curenta.j_curent == Joc.JMIN):
            #muta jucatorul
            raspuns_valid=False
            while not raspuns_valid:
                try:
                    pos = stare_curenta.tabla_joc.genereaza_posibilitati(Joc.JMIN)
                    print("Pozitii posibile: ")
                    for p in pos.keys():
                        print(p, end=' ')
                    print()

                    linie = int(input("linie = "))
                    coloana = int(input("coloana = "))


                    if (linie, coloana) in pos.keys():
                        raspuns_valid = True
                    else:
                        print("Pozitie invalida. Pozitii posibile: ")
                        for p in pos.keys():
                            print(p, end=' ')
                        print()

                        print("Coloana invalida (trebuie sa fie un numar intre 0 si {}).".format(Joc.NR_COLOANE - 1))

                except ValueError:
                    print("Coloana trebuie sa fie un numar intreg.")

            #dupa iesirea din while sigur am valida coloana
            #deci pot plasa simbolul pe "tabla de joc"
            
            # datele sunt corecte
            dirs = pos[(linie, coloana)]
            fill(stare_curenta.tabla_joc.matr, (linie, coloana), dirs, Joc.JMIN)
            
            #afisarea starii jocului in urma mutarii utilizatorului
            print("\nTabla dupa mutarea jucatorului")
            print(str(stare_curenta))

            #S-a realizat o mutare. Schimb jucatorul cu cel opus
            stare_curenta.j_curent = stare_curenta.jucator_opus()

        #--------------------------------
        else: #jucatorul e JMAX (calculatorul)
        #Mutare calculator

            #preiau timpul in milisecunde de dinainte de mutare
            t_inainte=int(round(time.time() * 1000))
            if tip_algoritm=='1':
                stare_actualizata = min_max(stare_curenta)
            else: #tip_algoritm==2
                stare_actualizata = alpha_beta(-5000, 5000, stare_curenta)
            stare_curenta.tabla_joc = stare_actualizata.stare_aleasa.tabla_joc
            print("Tabla dupa mutarea calculatorului")
            print(str(stare_curenta))

            #preiau timpul in milisecunde de dupa mutare
            t_dupa=int(round(time.time() * 1000))
            print("Calculatorul a \"gandit\" timp de "+str(t_dupa-t_inainte)+" milisecunde.")

            #S-a realizat o mutare. Schimb jucatorul cu cel opus
            stare_curenta.j_curent = stare_curenta.jucator_opus()


if __name__ == "__main__" :
        main()