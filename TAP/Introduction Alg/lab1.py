#include <iostream>
#include <fstream>
#include <vector>
#include <algorithm>
using namespace std;

ifstream f("date.in");
int n, a, b, c,k;
vector<int> v;

void print (vector<int>& w)
{
    for (vector<int>::iterator it = w.begin(); it < w.end(); it++)
    {
        cout << *it << " ";
    }
    cout << endl;
}


int main()
{
    f >> n;
    f>>k;

    for(int i =  0; i < n; i++)
    {
        f >> a;
        v.push_back(a);
    }

    v.resize(n);

    sort(v.begin(),v.end());

    print(v);

    for (vector<int>::iterator x = v.begin(); x < v.end(); x++)
    {
        vector<int>::iterator y = x + 1;
        vector<int>::iterator z =v.end()-1;
        if(*x==*(x+1))
            continue;
        while(y<z)
        {
            if(*z+*y==k-(*x))
            {
                cout << "DA" << " " << *x << " " << *y << " " << *z << endl;
                y++;
                z--;
            }
            if(*z+*y<k-(*x))
            {
                y++;
                while(*(y+1)==*y && *(z-1)==*z)
                    y++;
            }
            else
            {
                z--;
                while(*(z-1)==*z && *(y+1)==*y)
                    z--;
            }

        }

    }

    return 0;
}

