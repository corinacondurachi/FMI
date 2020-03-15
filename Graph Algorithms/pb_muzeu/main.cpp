#include <iostream>
#include<fstream>
#include<queue>
using namespace std;
ifstream f("muzeu.in");
ofstream g("muzeu.out");
queue<pair<int,int>> coada;
int d[255][255];
int main()
{
    int n,i,j;
    char x;
    f>>n;
    for(i=1; i<=n; i++)
        for(j=1; j<=n; j++)
        {
            f>>x;
            if(x == '.')
                d[i][j]=100000;
            if(x=='P')
            {
                d[i][j]=0;
                coada.push(make_pair(i,j));
            }
            if(x=='#')
                d[i][j]=-2;
        }
    while(!coada.empty())
    {
        pair<int,int> index=coada.front();
        coada.pop();
        int x=index.first;
        int y=index.second;
        //sus (x-1,y)
        if(x>1 && d[x-1][y]>0 && d[x-1][y]>d[x][y]+1)
        {
            d[x-1][y]=d[x][y]+1;
            coada.push(make_pair(x-1,y));
        }
        if(x<n && d[x+1][y]>0 && d[x+1][y]>d[x][y]+1)
        {
            d[x+1][y]=d[x][y]+1;
            coada.push(make_pair(x+1,y));
        }
        if(y>1 && d[x][y-1]>0 && d[x][y-1]>d[x][y]+1)
        {
            d[x][y-1]=d[x][y]+1;
            coada.push(make_pair(x,y-1));
        }
        if(y<n && d[x][y+1]>0 && d[x][y+1]>d[x][y]+1)
        {
            d[x][y+1]=d[x][y]+1;
            coada.push(make_pair(x,y+1));
        }
    }
    for(i=1; i<=n; i++)
    {
        for(j=1; j<=n; j++)
        {if(d[i][j]==100000)
            d[i][j]=-1;
            g<<d[i][j]<<" ";}
        g<<endl;
    }

    return 0;
}
