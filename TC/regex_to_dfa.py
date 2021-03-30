from copy import deepcopy
from PySimpleAutomata import automata_IO, DFA
import graphviz
import os


class Expr_regNode:

    @staticmethod
    def reduce_paranteze(expr_reg):
        # elimina parantezele aflate pe prima si pe ultima pozitie
        while expr_reg[0] == '(' and expr_reg[-1] == ')':
            expr_reg = expr_reg[1:-1]
        return expr_reg

    def __init__(self, expr_reg):
        self.fct_nullable = None
        self.first_pos = []
        self.last_pos = []
        self.obiect = None
        self.position = None
        self.deltaescendenti = []

        #Check if it e leaf
        if len(expr_reg) == 1 and expr_reg in alfabet:
            #Leaf
            self.obiect = expr_reg
            #Lambda checking 
            self.fct_nullable = False
            return
        
        #It e an internal node
        #Finding the leftmost operators in all three
        iteratie = -1
        op_sau = -1
        concatenare = -1
        i = 0

        #Getting the rest of terms    
        while i < len(expr_reg):
            if expr_reg[i] == '(':
                #Composed block
                paranteze = 1
                #Skipping the entire term
                i+=1
                while paranteze != 0 and i < len(expr_reg):
                    if expr_reg[i] == '(':
                        paranteze += 1
                    if expr_reg[i] == ')':
                        paranteze -= 1
                    i+=1
            else:
                #Going to the next char
                i+=1
            
            #Found a concatenare in previous iteration
            #And also it was the last element check if breaking
            if i == len(expr_reg):
                break

            #Testing if concat
            
            if expr_reg[i] in alfabet or expr_reg[i] == '(' :
                if concatenare == -1:
                    concatenare = i
                continue
            #Testing for iteratie
            if expr_reg[i] == '*':
                if iteratie == -1:
                    iteratie = i
                continue
            #Testing for or operator
            if expr_reg[i] == '|':
                if op_sau == -1:
                    op_sau = i
        
        #Setting the current operation by priority
        if op_sau != -1:
            #Found an or operation
            self.obiect = '|'
            self.deltaescendenti.append(Expr_regNode(self.reduce_paranteze(expr_reg[:op_sau])))
            self.deltaescendenti.append(Expr_regNode(self.reduce_paranteze(expr_reg[(op_sau+1):])))
        elif concatenare != -1:
            #Found a concatenare
            self.obiect = '.'
            self.deltaescendenti.append(Expr_regNode(self.reduce_paranteze(expr_reg[:concatenare])))
            self.deltaescendenti.append(Expr_regNode(self.reduce_paranteze(expr_reg[concatenare:])))
        elif iteratie != -1:
            #Found a iteratie
            self.obiect = '*'
            self.deltaescendenti.append(Expr_regNode(self.reduce_paranteze(expr_reg[:iteratie])))

    def functii(self, pos, follow_pos):
        if self.obiect in alfabet:
            #e a leaf
            self.first_pos = [pos]
            self.last_pos = [pos]
            self.position = pos
            #Add the position in the follow_pos let
            follow_pos.append([self.obiect,[]])
            return pos+1
        #e an internal node
        for descendent in self.deltaescendenti:
            pos = descendent.functii(pos, follow_pos)
        #Calculate current functii

        if self.obiect == '.':
            #e concatenare
            #First_pos
            if self.deltaescendenti[0].fct_nullable:
                self.first_pos = sorted(list(set(self.deltaescendenti[0].first_pos + self.deltaescendenti[1].first_pos)))
            else:
                self.first_pos = deepcopy(self.deltaescendenti[0].first_pos)
            #Last_pos
            if self.deltaescendenti[1].fct_nullable:
                self.last_pos = sorted(list(set(self.deltaescendenti[0].last_pos + self.deltaescendenti[1].last_pos)))
            else:
                self.last_pos = deepcopy(self.deltaescendenti[1].last_pos)
            #Fct_nullable
            self.fct_nullable = self.deltaescendenti[0].fct_nullable and self.deltaescendenti[1].fct_nullable
            #Follow_pos
            for i in self.deltaescendenti[0].last_pos:
                for j in self.deltaescendenti[1].first_pos:
                    if j not in follow_pos[i][1]:
                        follow_pos[i][1] = sorted(follow_pos[i][1] + [j])

        elif self.obiect == '|':
            #e or operator
            #First_pos
            self.first_pos = sorted(list(set(self.deltaescendenti[0].first_pos + self.deltaescendenti[1].first_pos)))
            #Last_pos
            self.last_pos = sorted(list(set(self.deltaescendenti[0].last_pos + self.deltaescendenti[1].last_pos)))
            #Fct_nullable
            self.fct_nullable = self.deltaescendenti[0].fct_nullable or self.deltaescendenti[1].fct_nullable

        elif self.obiect == '*':
            #e iteratie
            #First_pos
            self.first_pos = deepcopy(self.deltaescendenti[0].first_pos)
            #Last_pos
            self.last_pos = deepcopy(self.deltaescendenti[0].last_pos)
            #Fct_nullable
            self.fct_nullable = True
            #Follow_pos
            for i in self.deltaescendenti[0].last_pos:
                for k in self.deltaescendenti[0].first_pos:
                    if k not in follow_pos[i][1]:
                        follow_pos[i][1] = sorted(follow_pos[i][1] + [k])

        return pos

    def write_level(self, level):
        print(str(level) + ' ' + self.obiect, self.first_pos, self.last_pos, self.fct_nullable, '' if self.position == None else self.position)
        for descendent in self.deltaescendenti:
            descendent.write_level(level+1)

class Expr_regArbore:

    def __init__(self, expr_reg):
        self.radacina = Expr_regNode(expr_reg)
        self.follow_pos = []
        self.functii()
    
    def write(self):
        self.radacina.write_level(0)

    def functii(self):
        pozitii = self.radacina.functii(0, self.follow_pos)   
    
    def toAutomat(self):

        def contine_diez(q):
            for k in q:
                if self.follow_pos[k][0] == '#':
                    return True
            return False

        M = [] #Starile marcate
        Q = [] 
        V = alfabet - {'#', ''} 
        d = [] 
        F = [] 
        st_init = self.radacina.first_pos

        Q.append(st_init)
        if contine_diez(st_init):
            F.append(Q.index(st_init))
        
        while len(Q) - len(M) > 0:
            #There exets one unmarked
            #We take one of those
            q = [x for x in Q if x not in M][0]
            #Generating the delta dictionary for the new state
            d.append({})
            #We mark it
            M.append(q)
            #For each litera in the automata's alfabet
            for a in V:
                # Compute destination state ( d(q,a) = U )
                U = []
                #Compute U
                #foreach position in state
                for x in q:
                    #if x has label a
                    if self.follow_pos[x][0] == a:
                        #We add the position to U's composition
                        U = U + self.follow_pos[x][1]
                U = sorted(list(set(U)))
                #Checking if the e a valid state
                if len(U) == 0:
                    #No pozitii, skipping, it won't produce any new states ( also won't be final )
                    continue
                if U not in Q:
                    Q.append(U)
                    if contine_diez(U):
                        F.append(Q.index(U))
                #d(q,a) = U
                d[Q.index(q)][a] = Q.index(U)
        
        return Automat(Q,V,d,Q.index(st_init),F)

        
class Automat:

    def __init__(self,Q,V,d,st_init,final):
        self.Q = Q
        self.alfabet = V
        self.delta = d
        self.st_init = st_init
        self.final = final

    def run(self, mesaj):
        
        #Running the automata
        stare = self.st_init
        for i in mesaj:
            #Check if transition exets
            if stare >= len(self.delta):
                print('Mesajul nu este acceptat')
                exit(0)
            if i not in self.delta[stare].keys():
                print('Mesajul nu este acceptat')
                exit(0)
            #Execute transition
            stare = self.delta[stare][i]
        
        if stare in self.final:
            print('Acceptat')
        else:
            print('Mesaj neacceptat')

    def dfa(self):
        automat = {}
        automat['states'] = []
        automat ['transitions'] = {}
        automat ['initial_state'] = '0'
        automat['accepting_states'] = []
        for x in range(len(self.Q)):
            automat['states'].append(x)
            if x in self.final:
                automat['accepting_states'].append(x)
            for k in self.delta[x]:
                automat['transitions'][(str(x),k)] = self.delta[x][k]    
        return automat

#Preprocessing Functii
def preprocess(expr_regulata):
    expr_regulata = clean_iteratie(expr_regulata)
    expr_regulata = expr_regulata.replace(' ','')
    expr_regulata = '(' + expr_regulata + ')' + '#'
    while '()' in expr_regulata:
        expr_regulata = expr_regulata.replace('()','')
    return expr_regulata

def clean_iteratie(expr_reg):
    for k in range(0, len(expr_reg) - 1):
        while k < len(expr_reg) - 1 and expr_reg[k + 1] == expr_reg[k] and expr_reg[k] == '*':
            expr_reg = expr_reg[:k] + expr_reg[k + 1:]
    return expr_reg

def gen_alfabet(expr_regulata):
    return set(expr_regulata) - set('()|*')


#Settings
alfabet = None

#Main
expr_reg = '(aa|b)*ab(bb|a)*'

#Preprocess expr_reg and generate the alfabet    
p_expr_reg = preprocess(expr_reg)
alfabet = gen_alfabet(p_expr_reg)
#add optional literas that don't appear in the expression
extra = ''
alfabet = alfabet.union(set(extra))

#Construct
arbore = Expr_regArbore(p_expr_reg)
automat = arbore.toAutomat()

#Test
mesaj = 'baaab'
print('The e the expr_reg : ' + expr_reg)
print('The e the alfabet : ' + ''.join(sorted(alfabet)))
print('The e the automata : \n')
automat_ex = automat.dfa()
print(automat_ex)
print('\nTesting for : "'+mesaj+'" : ')
automat.run(mesaj)

# Generate DFA
automata_IO.dfa_to_dot(automat_ex, 'automat')
