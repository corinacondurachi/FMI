import sys
import numpy as np
import pdb


def select_random_path(E):
    # pentru linia 0 alegem primul pixel in mod aleator
    line = 0
    col = np.random.randint(low=0, high=E.shape[1], size=1)[0]
    path = [(line, col)]
    for i in range(E.shape[0]):
        # alege urmatorul pixel pe baza vecinilor
        line = i
        # coloana depinde de coloana pixelului anterior
        if path[-1][1] == 0:  # pixelul este localizat la marginea din stanga
            opt = np.random.randint(low=0, high=2, size=1)[0]
        elif path[-1][1] == E.shape[1] - 1:  # pixelul este la marginea din dreapta
            opt = np.random.randint(low=-1, high=1, size=1)[0]
        else:
            opt = np.random.randint(low=-1, high=2, size=1)[0]
        col = path[-1][1] + opt
        path.append((line, col))

    return path


def select_greedy_path(E):
    # pentru linia 0 alegem indicele pixelului avand valoarea minima
    line = 0
    # gasim mai intai valoarea minima de pe prima linie
    minimum = np.min(E[line])
    # determinam indexul acelei valori
    col = np.where(E[0] == minimum)[0][0]
    path = [(line, col)]
    for i in range(1, E.shape[0]):
        # alege urmatorul pixel pe baza vecinilor
        line = i
        # coloana depinde de coloana pixelului anterior
        if path[-1][1] == 0:  # pixelul este localizat la marginea din stanga
            col = np.where(E[line] == min(E[line, col: col + 2]))[0][0]
        elif path[-1][1] == E.shape[1] - 1:  # pixelul este la marginea din dreapta
            col = np.where(E[line] == min(E[line, col-1: col + 1]))[0][0]
        else:
            col = np.where(E[line] == min(E[line, col-1: col + 2]))[0][0]

        path.append((line, col))

    return path


# pentru a elimina un obiect folosesc coordonatele x, y pentru a marca ca drumul ales trebuie sa treaca prin acel punct si sa aiba o lungime de 'h' pixeli. Daca parametrii sunt None, inseamna ca se va apela normal, fara a trece printr-o portiune prestabilita
def select_dynamic_programming_path(E, x=None, y= None, h=None):
    
    line = 0
    # matricea costurilor
    M = np.zeros((E.shape[0], E.shape[1]))
    # copiez prima linie
    M[0] = E[0] 

    # calculez matricea de cost minim
    for i in range(1, E.shape[0]):
        for j in range(E.shape[1]):
            
            if j-1 < 0:  # pixelul este localizat la marginea din stanga
                M[i,j] = E[i,j] + min(M[i-1, j], M[i-1, j + 1])
                
            elif j+1 == E.shape[1]:  # pixelul este la marginea din dreapta
                    M[i,j] = E[i,j] + min(M[i-1, j-1], M[i-1, j])          
            else:
                M[i,j] = E[i,j] + min(M[i-1, j-1], M[i-1, j], M[i-1,j+1])
                
    # cazul in care nu trebuie sa trec printr-o portiune anume (cand nu elimin un obiect)
    if x == None:            
        # iau cea mai mica valoare de pe ultima linie si o adaug in path
        line = M.shape[0]-1
        col = np.where(M[line] == np.min(M[line]))[0][0]

        path = [(line, col)]

        # parcurg matricea de jos in sus si gasesc drumul de cost minim
        for i in range(M.shape[0]-1 , 0, -1):
            line = i
            if M[line-1,col] + E[line,col] == M [line,col]:
                col = col
            elif M[line-1,col-1] + E[line,col] == M [line,col]:
                col = col-1
            else:
                col = col+1
                    
            path.append((line-1, col))
        # inversez ca sa aflu drumul de sus in jos    
        path.reverse()

    # cazul in care trebuie sa elimin obiectul
    else:
        line = x
        col = y

        # adaug punctul din stanga sus
        path = [(line, col)]

        # adaug in path toate punctele din prima linie a chenarului pentru a ma asigura ca trec pe acolo
        for i in range (1,h):
            path.append((x+i,y))

        # parcurg matricea de jos in sus (din punctul x pana sus) si gasesc drumul de cost minim 
        for i in range(x , 0, -1):
            line = i
            if M[line-1,col] + E[line,col] == M [line,col]:
                col = col
            elif M[line-1,col-1] + E[line,col] == M [line,col]:
                col = col-1
            else:
                col = col+1
                    
            path.append((line-1, col))

        # inversez ca sa aflu drumul de sus in jos (de sus pana la x)   
        path.reverse()
        
        # ma pozitionez pe coloana y si caut drumul de la x+h (de la 0 la x am adaugat si de la x la h este portiunea prin care trebuie neaparat sa trec, care e deja adaugata) in jos
        col = y
        for i in range(x+h, E.shape[0]):
            line = i
            if M[line - 1 ,col] + E[line,col] == M [line,col]:
                col = col
            elif M[line-1,col-1] + E[line,col] == M [line,col]:
                col = col-1
            else:
                col = col+1
                
            path.append((line, col))


    return path


def select_path(E, method):
    if method == 'aleator':
        return select_random_path(E)
    elif method == 'greedy':
        return select_greedy_path(E)
    elif method == 'programareDinamica':
        return select_dynamic_programming_path(E)
    else:
        print('The selected method %s is invalid.' % method)
        sys.exit(-1)