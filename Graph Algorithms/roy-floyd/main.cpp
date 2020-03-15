#include <iostream>
#include<fstream>
using namespace std;
ifstream f("royfloyd.in");
ofstream g("royfloyd.out");
int d[101][101],p[101][101];

void drum(int i,int j)
{
    if(i!=j)
        drum(i,p[i][j]);
    g<<j<<" ";
}
int main()
{
    int m,n,x,y,i,c,j,k;
    f>>n;
    /*f>>m;
    for(i=1; i<=m; i++)
    {
        f>>x>>y>>c;
        d[x][y]=c;
        p[x][y]=x;
    }*/
    for(i=1; i<=n; i++)
        for(j=1; j<=n; j++)
            f>>d[i][j];
    /*for(i=1; i<=n; i++)
        for(j=1; j<=n; j++)
        {
            if(d[i][j]==0 && i!=j)
                d[i][j]=9999999;
        }*/
    for(k=1; k<=n; k++)
        for(i=1; i<=n; i++)
            for(j=1; j<=n; j++)
                if(d[i][j]>d[i][k]+d[k][j])
                {
                    d[i][j]=d[i][k]+d[k][j];
                    //p[i][j]=p[k][j];
                }
    for(i=1; i<=n; i++)
    {
        for(j=1; j<=n; j++)
            g<<d[i][j]<<" ";
        g<<endl;
    }
    g<<endl;
    /*for(i=1; i<=n; i++)
    {
        for(j=1; j<=n; j++)
            g<<p[i][j]<<" ";
        g<<endl;
    }
    g<<endl;
    drum(2,1);*/


    return 0;
}
