#include <iostream>
#include<fstream>
#include<queue>
#include<algorithm>
using namespace std;
ifstream f("apm.in");
ofstream g("apm.out");
priority_queue <pair<int, pair<int, int>> > myheap;
pair<int,pair<int,int>> muchii[200005];
vector  <pair<int,int>> apm;
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
    int n,m,i,a,b,c,s=0,k=0;
    f>>n>>m;
    for(i=1; i<=n; i++)
    {
        tata[i]=i;
        grad[i]=0;
    }
    for(i=0; i<m; i++)
    {
        f>>a>>b>>c;
        muchii[i]=make_pair(c,make_pair(a,b));
    }
    sort(muchii,muchii+m);
    for(i=0;i<m;i++)
        {
            int x=muchii[i].second.first;
            int y=muchii[i].second.second;
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
                s=s+muchii[i].first;
                k++;
                apm.push_back(make_pair(x,y));
            }
    }
    g<<s<<"\n";
    g<<k<<"\n";
    for(i=0;i<k;i++)
        g<<apm[i].first<<" "<<apm[i].second<<"\n";
    return 0;
}
