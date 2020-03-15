#include <iostream>
#include<fstream>
using namespace std;
ifstream f("bellmanford.in");
ofstream g("bellmanford.out");
pair<int,pair<int,int>> muchii[250005];
int dist[50005];
int main()
{
    int m,n,i,a,b,c,ok;
    f>>n>>m;
    for(i=1; i<=m; i++)
    {
        f>>a>>b>>c;
        muchii[i]=make_pair(c,make_pair(a,b));
    }
    dist[1]=0;
    for(i=2; i<=n; i++)
        dist[i]=10000000;
    for(i=1; i<=n; i++)
    {
        ok=0;
        for(int j=1; j<=m; j++)
        {
            int c=muchii[j].first;
            int a=muchii[j].second.first;
            int b=muchii[j].second.second;
            if(dist[a]+c<dist[b])
            {
                dist[b]=dist[a]+c;
                ok=1;
            }
        }
        if(ok==0)
            break;
    }
    ok=0;
    if(i==n+1)
    {
        for(int j=1; j<=m; j++)
        {
            int c=muchii[j].first;
            int a=muchii[j].second.first;
            int b=muchii[j].second.second;
            if(dist[a]+c<dist[b])
            {
                dist[b]=dist[a]+c;
                ok=1;
            }
        }
    }
    if(ok==1)
        g<<"Ciclu negativ!";
    else if(ok==0)
    {
        for(i=2; i<=n; i++)
            g<<dist[i]<<" ";
    }
    return 0;
}
