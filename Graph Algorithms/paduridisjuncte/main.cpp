#include <iostream>
#include<fstream>
#include<vector>
using namespace std;
ifstream f("disjoint.in");
ofstream g("disjoint.out");
int tata[100007],grad[100007];
int find_father(int nod)
{
    if(tata[nod]==nod)
        return nod;
    tata[nod]=find_father(tata[nod]);
    return tata[nod];
}
int main()
{
    int m,n,x,y,cod,i;
    f>>n>>m;
    for(i=1; i<=n; i++)
    {
        tata[i]=i;
        grad[i]=0;
    }
    for(i=0; i<m; i++)
    {
        f>>cod>>x>>y;
        if(cod==2)
        {
            if(find_father(x)==find_father(y))
                g<<"DA"<<"\n";
            else
                g<<"NU"<<"\n";
        }
        else
            if(find_father(x)!=find_father(y))
            {
                int A=find_father(x);
                int B=find_father(y);
                if(grad[A]<grad[B])
                {
                    tata[A]=B;
                    grad[B]+=grad[A];
                }
                else
                {
                    tata[B]=A;
                    grad[A]+=grad[B];
                }
            }
    }

    return 0;
}
