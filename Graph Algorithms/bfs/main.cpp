#include <iostream>
#include<fstream>
#include<vector>
using namespace std;
ifstream f("bfs.in");
ofstream g("bfs.out");
int viz[100005],dist[100005],coada[100005];
vector<int> graph[100005];
void BFS(int node)
{
    int i,left,right;
    dist[node]=0;
    viz[node]=1;
    coada[1]=node;
    left=right=1;
    while(left<=right)
    {
        int index=coada[left];
        int lim=graph[index].size();
        for(i=0;i<lim;i++)
        {
            int vecin=graph[index][i];
            if(!viz[vecin])
            {
                dist[vecin]=dist[index]+1;
                viz[vecin]=1;
                coada[++right]=vecin;
            }
        }
            left++;

    }
}
int main()
{
    int n,m,i,a,b,x;
    f>>n>>m;
    f>>x;
    for(i=1;i<=m;i++)
    {
        f>>a>>b;
        graph[a].push_back(b);
        //graph[b].push_back(a);
    }
    BFS(x);
    for(i=1;i<=n;i++)
    {
        if(viz[i]==0)
        dist[i]=-1;
    }
    for(i=1;i<=n;i++)
    g<<dist[i]<<" ";

    return 0;
}
