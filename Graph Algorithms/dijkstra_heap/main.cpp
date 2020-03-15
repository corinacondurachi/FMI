#include <iostream>
#include<fstream>
#include<queue>
using namespace std;
ifstream f("dijkstra.in");
ofstream g("dijkstra.out");
priority_queue < pair<int, int> > myheap;
pair<int,int> x;
vector<int> graph[50005];
vector<int> graphC[50005];
int viz[50005],dist[50005];
int main()
{
    int n,m,i,a,b,c;
    f>>n>>m;
    for(i=2; i<=n; i++)
    {
        dist[i]=100000000;
        myheap.push(make_pair(-dist[i], i));
    }
    dist[1]=0;
    for(i=1; i<=m; i++)
    {
        f>>a>>b>>c;
        graph[a].push_back(b);
        graphC[a].push_back(c);
    }
    myheap.push(make_pair(0, 1));
    while(!myheap.empty())
    {
        pair<int, int> best = myheap.top();
        myheap.pop();
        int index = best.second;
        if(viz[index])
            continue;
        viz[index]=1;
        int lim=graph[index].size();
        for(int j=0; j<lim; j++)
        {
            int vecin=graph[index][j];
            int cost = graphC[index][j];
            if(dist[index]+cost <dist[vecin])
            {
                dist[vecin]=dist[index]+cost;
                myheap.push(make_pair(-dist[vecin],vecin));
            }
        }
    }
    for(i=2; i<=n; i++)
        if(dist[i]==100000000)
            dist[i]=0;
    for(i=2; i<=n; i++)
        g<<dist[i]<<" ";
    return 0;
}
