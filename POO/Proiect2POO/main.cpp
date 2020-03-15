#include <iostream>
#include<cstring>
#include<fstream>
using namespace std;
ifstream f("date.in");

class Localitate
{
protected:
    char *denumire;
    int cod;
    long nr_locuitori;
public:
    Localitate();
    Localitate(char*,int,long);
    Localitate(const Localitate&);
    ~Localitate();
    Localitate& operator=(Localitate &);
    friend istream& operator>>(istream &,Localitate&);
    virtual void display()
    {
        cout<<this->denumire<<" "<<this->cod<<" "<<this->nr_locuitori<<endl;
    }
    int get_cod()const;
    int get_nr_locuitori()const;
    char* get_denumire()const;
    void set_cod(int);
    void set_nr_locuitori(int);
    void set_denumire(char*);
};

Localitate::Localitate()
{
    denumire=NULL;
    cod=0;
    nr_locuitori=0;
}
Localitate::Localitate(char *a,int b,long c): cod(b),nr_locuitori(c)
{
    denumire=new char[strlen(a)+1];
    strcpy(denumire,a);
}

Localitate::Localitate(const Localitate &loc)
{
    denumire=new char[strlen(loc.denumire)+1];
    strcpy(denumire,loc.denumire);
    cod=loc.cod;
    nr_locuitori=loc.nr_locuitori;
}

Localitate::~Localitate()
{
    delete[] denumire;
    //cout<<"destructor loc"<<endl;
}

Localitate& Localitate::operator=(Localitate &ob)
{
    if(strcmp(this->denumire,ob.denumire)==0 && this->cod==ob.cod && this->nr_locuitori==ob.nr_locuitori)
        return *this;
    else
    {
        delete[]this->denumire;
        this->denumire=new char[strlen(ob.denumire)+1];
        strcpy(this->denumire,ob.denumire);
        this->cod=ob.cod;
        this->nr_locuitori=ob.nr_locuitori;
    }
    return *this;
}

istream& operator>>(istream & in, Localitate &loc)
{
    char *v=new char[100];
    in>>v;
    delete[]loc.denumire;
    loc.denumire=new char[strlen(v)+1];
    strcpy(loc.denumire,v);
    delete[]v;
    in>>loc.cod>>loc.nr_locuitori;
    return in;
}

int Localitate::get_cod() const
{
    return this->cod;
}

int Localitate::get_nr_locuitori()const
{
    return this->nr_locuitori;
}

char* Localitate::get_denumire()const
{
    return this->denumire;
}

void Localitate:: set_cod(int a)
{
    this->cod=a;
}

void Localitate:: set_nr_locuitori(int a)
{
    this->nr_locuitori=a;
}

void Localitate::set_denumire(char *a)
{
    delete[]this->denumire;
    this->denumire=new char[strlen(a)+1];
    strcpy(this->denumire,a);
}

class Oras:public Localitate
{
protected:
    int nrBlocuri;
public:
    Oras();
    ~Oras();
    Oras(char*,int,long,int);
    Oras(const Oras&);
    Oras& operator=(Oras &);
    friend istream& operator>>(istream &,Oras&);
    void display()
    {
        cout<<this->denumire<<" "<<this->cod<<" "<<this->nr_locuitori<<" "<<this->nrBlocuri<<endl;
    }
};

Oras::Oras():Localitate()
{
    nrBlocuri=0;
}

Oras::Oras(char* a,int b,long c,int nr):Localitate(a, b, c)
{
    nrBlocuri=nr;
}

Oras::Oras(const Oras& ob):Localitate(ob)
{
    nrBlocuri=ob.nrBlocuri;
}

Oras& Oras::operator=(Oras &ob)
{
    if(strcmp(this->denumire,ob.denumire)==0 && this->cod==ob.cod && this->nr_locuitori==ob.nr_locuitori && this->nrBlocuri==ob.nrBlocuri)
        return *this;
    else
    {
        delete[]this->denumire;
        this->denumire=new char[strlen(ob.denumire)+1];
        strcpy(this->denumire,ob.denumire);
        this->cod=ob.cod;
        this->nr_locuitori=ob.nr_locuitori;
        this->nrBlocuri=ob.nrBlocuri;
    }
    return *this;
}

Oras::~Oras()
{
    //cout<<"destructor oras"<<endl;
}

istream& operator>>(istream & in, Oras &oras)
{
    char *v=new char[100];
    in>>v;
    delete[]oras.denumire;
    oras.denumire=new char[strlen(v)+1];
    strcpy(oras.denumire,v);
    delete[]v;
    in>>oras.cod>>oras.nr_locuitori>>oras.nrBlocuri;
    return in;
}

class Capitala:public Oras
{
protected:
    char *numePrefect;
public:
    Capitala();
    ~Capitala();
    Capitala(char*,int,long,int,char*);
    Capitala(const Capitala&);
    Capitala& operator=(Capitala &);
    friend istream& operator>>(istream &,Capitala&);
    void display()
    {
        cout<<this->denumire<<" "<<this->cod<<" "<<this->nr_locuitori<<" "<<this->nrBlocuri<<" "<<this->numePrefect<<endl;
    }
};

Capitala::Capitala():Oras()
{
    numePrefect=NULL;
}

Capitala::~Capitala()
{
    delete[] numePrefect;
    //cout<<"destructor capitala"<<endl;
}

Capitala::Capitala(char* a,int b,long c,int d,char* e):Oras(a,b,c,d)
{
    numePrefect=new char[strlen(e)+1];
    strcpy(numePrefect,e);
}

Capitala::Capitala(const Capitala &ob):Oras(ob)
{
    numePrefect=new char[strlen(ob.numePrefect)];
    strcpy(numePrefect,ob.numePrefect);
}

Capitala& Capitala::operator=(Capitala &ob)
{
    if(strcmp(this->denumire,ob.denumire)==0 && this->cod==ob.cod && this->nr_locuitori==ob.nr_locuitori &&
            this->nrBlocuri==ob.nrBlocuri && strcmp(this->numePrefect,ob.numePrefect)==0)
        return *this;
    else
    {
        delete[]this->denumire;
        this->denumire=new char[strlen(ob.denumire)+1];
        strcpy(this->denumire,ob.denumire);
        this->cod=ob.cod;
        this->nr_locuitori=ob.nr_locuitori;
        this->nrBlocuri=ob.nrBlocuri;
        delete[]this->numePrefect;
        this->numePrefect=new char[strlen(ob.numePrefect)+1];
        strcpy(this->numePrefect,ob.numePrefect);
    }
    return *this;
}

istream &operator>>(istream & in,Capitala &cap)
{
    char *v=new char[100];
    in>>v;
    delete[] cap.denumire;
    cap.denumire=new char[strlen(v)+1];
    strcpy(cap.denumire,v);
    delete[]v;
    in>>cap.cod>>cap.nr_locuitori>>cap.nrBlocuri;
    char *w=new char[100];
    in>>w;
    delete[] cap.numePrefect;
    cap.numePrefect=new char[strlen(v)+1];
    strcpy(cap.numePrefect,v);
    delete[]w;
}

class Judet
{
    Localitate *p;
    int nrLoc;
public:
    Judet();
    ~Judet();
    Judet(const Judet&);
    Judet& operator=(Judet &);
    friend istream& operator>>(istream &,Judet&);
    void display()
    {
        cout<<p->get_denumire()<<" "<<p->get_cod()<<" "<<p->get_nr_locuitori()<<" "<<this->nrLoc<<endl;
    }
};

Judet::Judet()
{
    p=new Localitate(" ",0,0);
    nrLoc=0;
}

Judet::~Judet()
{
    delete p;
    //cout<<"destructor jud"<<endl;
}

Judet::Judet(const Judet &jud)
{
    p=new Localitate(*jud.p);
    nrLoc=jud.nrLoc;
}

Judet& Judet::operator=(Judet &jud)
{
    delete p;
    p=new Localitate(*jud.p);
    nrLoc=jud.nrLoc;
    return *this;
}

istream& operator>>(istream & in, Judet &jud)
{
    delete jud.p;
    jud.p=new Localitate;
    in>>*jud.p;
    in>>jud.nrLoc;
    return in;
}

int main()
{
    int n,i;
    f>>n;
    Localitate *l=new Localitate[n+1];
    for(i=1;i<=n;i++)
    {
        f>>l[i];
        l[i].display();
    }
    l[2]=l[1];
    l[2].display();
    delete[] l;

    f>>n;
    Oras *o=new Oras[n+1];
    for(i=1;i<=n;i++)
    {
        f>>o[i];
        o[i].display();
    }
    o[2]=o[1];
    o[2].display();
    delete[] o;

    f>>n;
    Capitala *c=new Capitala[n+1];
    for(i=1;i<=n;i++)
    {
        f>>c[i];
        c[i].display();
    }
    c[2]=c[1];
    c[2].display();
    delete[] c;

    f>>n;
    Judet *j=new Judet[n+1];
    for(i=1;i<=n;i++)
    {
        f>>j[i];
        j[i].display();
    }
    j[2]=j[1];
    j[2].display();
    delete[] j;

    return 0;
}
