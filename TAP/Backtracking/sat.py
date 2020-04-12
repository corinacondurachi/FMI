def back(k,E):
    if not E:
        global ok
        ok=1
        return True
    global n
    if k==n:
        return False
    x[k]=0
    for c in E:
        cuv=str(' '+'x'+str(k))
        n_cuv=str('!'+'x'+str(k))
        if cuv in c or n_cuv in c:
            if n_cuv in c:
                E.remove(c)
                back(k+1,E)
                E.append(c)
            else:
                c.remove(cuv)
                if c:
                    back(k+1,E)
                c.append(cuv)
    x[k]=1
    for c in E:
        cuv=str(' '+'x'+str(k))
        n_cuv=str('!'+'x'+str(k))
        if cuv in c or n_cuv in c:
            if cuv in c:
                E.remove(c)
                back(k+1,E)
                E.append(c)
            else:
                    c.remove(n_cuv)
                    if c:
                        back(k+1,E)
                    c.append(n_cuv)

# Satisfiabile
# "(x2V!x3)^(x1Vx2Vx3)^(x1V!x2)"
E = [[' x2', '!x3'], [' x1', ' x2', ' x3'], [' x1', '!x2']]

# "x1Vx2"
# E = [[' x1', ' x2', ' x3']]


#    Nesatisfiabile
# E = [[' x1', ' x2'], ['!x1', ' x2'], [' x1', '!x2'], ['!x1', '!x2']]
# E = [[' x1'], ['!x1']]
n = 4 #nr de clauze+1
x = [0] * n

# Testare
ok = 0
back(1, E)
if ok == 1:
    print("Satisfiabila")
else:
    print("Nesatisfiabila")       