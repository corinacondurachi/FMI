#include <iostream>
#include<fstream>
#include<queue>
using namespace std;
ifstream f("apm.in");
ofstream g("apm.out");
priority_queue <pair<int, pair<int, int>> > myheap;
vector<int> graph[200005];
vector<int> graphC[200005];
vector  <pair<int,int>> apm;
int viz[200005];
int main()
{
    int n,m,i,a,b,c,s=0,k=0;
    f>>n>>m;
    for(i=1; i<=m; i++)
    {
        f>>a>>b>>c;
        graph[a].push_back(b);
        graph[b].push_back(a);
        graphC[a].push_back(c);
        graphC[b].push_back(c);
    }
    int lim=graph[1].size();
    viz[1]=1;
    for(int j=0; j<lim; j++)
    {
        int vecin=graph[1][j];
        int cost = graphC[1][j];
        myheap.push(make_pair(-cost,make_pair(1,vecin)));
    }
    while(!myheap.empty())
    {
        pair<int,pair<int, int>> best = myheap.top();
        myheap.pop();
        int vecin=best.second.second;
        if(viz[vecin])
            continue;
        viz[vecin]=1;
        s=s-best.first;
        k++;
        apm.push_back(best.second);
        int lim=graph[vecin].size();
        for(int j=0; j<lim; j++)
        {
            int vecin2=graph[vecin][j];
            int cost = graphC[vecin][j];
            myheap.push(make_pair(-cost,make_pair(vecin,vecin2)));
        }
    }
    g<<s<<endl;
    g<<k<<endl;
    for(i=0;i<k;i++)
        g<<apm[i].first<<" "<<apm[i].second<<endl;
    return 0;
}
