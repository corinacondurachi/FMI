#include <iostream>
#include <stdlib.h>
#include <fstream>

using namespace std;
ifstream f("date.in");
class polinom;
class pereche
{
    double coef;
    int exp;
    pereche* next;
public:
    pereche();
    pereche(const pereche&);
    friend class polinom;
    ~pereche();
    friend istream& operator>>(istream &, pereche*);
    friend ostream& operator<<(ostream &, pereche*);
    friend istream& operator>>(istream &,polinom*);
    friend ostream& operator<<(ostream &, polinom*);

};
pereche::pereche()
{
    coef = 0;
    exp=0;
    next = NULL;
}
pereche::pereche(const pereche &n)
{
    coef = n.coef;
    exp=n.exp;
    next = NULL;
}
pereche::~pereche()
{
    next=NULL;
}
class polinom
{
    pereche *prim, *ultim;
public:
    polinom();
    ~polinom();
    void add_sf(pereche*);
    friend istream& operator>>(istream &,polinom*);
    friend ostream& operator<<(ostream &, polinom*);
    void ordonare();
    void schimba_semn_coef();
    int valoare(int x);
    void sterge_zerouri();
    polinom* operator+(polinom*);
    polinom* operator-(polinom*);
    polinom* operator*(polinom*);
    polinom* operator/(polinom*);
    polinom* operator%(polinom*);
    int operator[](int);
};
polinom::polinom()
{
    prim=ultim=NULL;
}

polinom::~polinom()
{
    pereche *p=this->prim;
    pereche *q=p;
    while(p)
    {
        p=p->next;
        delete q;
        q=p;
    }
}

void polinom::add_sf(pereche *p)
{
    if(prim==NULL)
    {
        prim=p;
        ultim=p;
    }
    else
    {
        ultim->next=p;
        ultim=p;
    }
    return;
}
istream& operator>>(istream & in, pereche *p)
{
    in>>p->coef>>p->exp;
    return in;
}

ostream& operator<<(ostream & out, pereche *p)
{
    out<<p->coef<<" "<<p->exp<<" ";
    return out;
}

istream& operator>>(istream & in, polinom*l)
{
    int x,i;
    in>>x;
    if(x==0)
        cout<<"nu exista grade";
    for(i=1; i<=x; i++)
    {
        pereche *n=new pereche;
        in>>n;
        if(n->coef)
            l->add_sf(n);
    }
    return in;
}

ostream& operator<<(ostream &out, polinom *l)
{
    pereche * p = l->prim;
    while (p!=NULL)
    {
        out<<p;
        p = p -> next;
    }
    out<<endl;
    return out;
}

void polinom::ordonare()
{
    pereche *a=this->prim;
    pereche *i,*j;
    int aux;
    for(i=a; i->next!=NULL; i=i->next)
        for(j=i->next; j!=NULL; j=j->next)
        {
            if(i->exp<j->exp)
            {
                aux=i->coef;
                i->coef=j->coef;
                j->coef=aux;
                aux=i->exp;
                i->exp=j->exp;
                j->exp=aux;
            }
        }
}

int polinom::valoare(int x)
{
    pereche *p=this->prim;
    int s=0,b,k;
    while(p)
    {
        b=p->exp;
        k=1;
        while(b)
        {
            k=k*x;
            b--;
        }
        s=s+k*p->coef;
        p=p->next;
    }
    return s;
}

void polinom::schimba_semn_coef()
{
    pereche *p=this->prim;
    while(p)
    {
        p->coef=-1*p->coef;
        p=p->next;
    }
}

void polinom::sterge_zerouri()
{
    pereche *p=this->prim;
    while(p->coef==0)
    {
        pereche *aux=p;
        p=p->next;
        prim=p;
        delete aux;
    }
    while (p->next!=NULL)
    {
        if(p->next->coef==0)
        {
            pereche *aux=p->next;
            p->next=p->next->next;
            delete aux;
        }
        else
            p=p->next;
    }
}

polinom *polinom::operator+(polinom *L2)
{
    polinom *suma=new polinom;
    pereche *p1=this->prim;
    pereche *p2=L2->prim;
    if(p1->coef==0 && p2->coef==0)
        cout<<"nu putem aduna doua polinoame nule";
    while(p1 && p2)
    {
        if(p1->exp>p2->exp)

        {
            pereche *r=new pereche(*p1);
            suma->add_sf(r);
            p1=p1->next;
        }
        else if(p1->exp<p2->exp)
        {
            pereche *r=new pereche(*p2);
            suma->add_sf(r);
            p2=p2->next;
        }
        else if (p1->exp==p2->exp)
        {
            pereche *r=new pereche();
            r->exp=p1->exp;
            r->coef=p1->coef+p2->coef;
            suma->add_sf(r);
            p1=p1->next;
            p2=p2->next;
        }
    }
    while(p1)
    {
        pereche *r=new pereche(*p1);
        suma->add_sf(r);
        p1=p1->next;
    }
    while(p2)
    {
        pereche *r=new pereche(*p2);
        suma->add_sf(r);
        p2=p2->next;
    }
    suma->sterge_zerouri();
    return suma;
}
polinom *polinom::operator-(polinom *L2)
{
    polinom *dif=new polinom;
    pereche *p1=this->prim;
    L2->schimba_semn_coef();
    while(p1)
    {
        pereche *r=new pereche(*p1);
        dif->add_sf(r);
        p1=p1->next;
    }
    dif=*dif+L2;
    L2->schimba_semn_coef();
    dif->sterge_zerouri();
    return dif;
}

polinom *polinom::operator*(polinom *L2)
{
    polinom *inmult=new polinom;
    pereche *p1=this->prim;
    pereche *p2=L2->prim;
    if(p1==NULL || p2==NULL)
    {
        cout<<"0";
        return NULL;
    }
    while(p1)
    {
        polinom *intermediar=new polinom;
        pereche *p2=L2->prim;
        while(p2)
        {
            pereche *r=new pereche();
            r->coef=p1->coef*p2->coef;
            r->exp=p1->exp+p2->exp;
            intermediar->add_sf(r);
            p2=p2->next;
        }
        inmult=*intermediar+inmult;
        delete intermediar;
        p1=p1->next;
    }
    return inmult;
}

polinom *polinom::operator/(polinom *L2)
{
    polinom *cat=new polinom, *rest=new polinom,*impartitor=new polinom;
    pereche *p1=this->prim;
    pereche *p2=L2->prim;
    if(p2==NULL)
    {
        cout<<"Nu se poate imparti la 0";
        return NULL;
    }
    if(p1->exp<p2->exp)
    {
        cout<<"Nu se poate imparti la un polinom de grad mai mare";
        return NULL;
    }
    if(p1==NULL)
    {
        cout<<"0";
        return NULL;
    }
    while(p1)
    {
        pereche *a=new pereche(*p1);
        rest->add_sf(a);
        p1=p1->next;
    }
    while(p2)
    {
        pereche *b=new pereche(*p2);
        impartitor->add_sf(b);
        p2=p2->next;
    }
    p1=rest->prim;
    p2=L2->prim;
    while(p1->exp>=p2->exp)
    {
        pereche *r=new pereche();
        polinom *aux=new polinom();
        r->coef=p1->coef/p2->coef;
        r->exp=p1->exp-p2->exp;
        cat->add_sf(r);
        aux->add_sf(r);
        aux=*impartitor*aux;
        aux->schimba_semn_coef();
        rest=*rest+aux;
        rest->sterge_zerouri();
        p1=rest->prim;
        delete aux;
    }
    return cat;
}

polinom *polinom::operator%(polinom *L2)
{
    polinom *rest=new polinom,*deimpartit=new polinom,*impartitor=new polinom,*cat=new polinom;
    pereche *p1=this->prim;
    pereche *p2=L2->prim;
    if(p2->exp==0 || p2==NULL)
    {
        cout<<"Nu se poate imparti la 0";
        return NULL;
    }
    if(p1->exp<p2->exp)
    {
        cout<<"Nu se poate imparti la un polinom de grad mai mare";
        return NULL;
    }
    if(p1==NULL)
    {
        cout<<"0";
        return NULL;
    }
    while(p1)
    {
        pereche *a=new pereche(*p1);
        deimpartit->add_sf(a);
        p1=p1->next;
    }
    while(p2)
    {
        pereche *b=new pereche(*p2);
        impartitor->add_sf(b);
        p2=p2->next;
    }
    cat=*deimpartit/impartitor;
    rest=*deimpartit-(*cat*impartitor);
    rest->sterge_zerouri();
    return rest;
}

int polinom::operator[](int x)
{
    pereche *p=this->prim;
    while(x>0 && p->next!=NULL)
    {
        p=p->next;
        x--;
    }
    if(x!=0)
        cout<<"termeni insuficienti";
    return p->exp;
}

int main()
{
    polinom *L1=new polinom;
    polinom *L2=new polinom,*S=new polinom,*D=new polinom,*P=new polinom,*Cat=new polinom,*Rest=new polinom;
    f>>L1;
    cout<<L1;
    L1->ordonare();
    cout<<L1;
    f>>L2;
    cout<<L2;
    L2->ordonare();
    cout<<L2;
    int a=L1->valoare(5);
    cout<<a<<endl;
    S=*L1+L2;
    cout<<S;
    D=*L1-L2;
    cout<<D;
    P=*L1*L2;
    cout<<P;
    Cat=*L1/L2;
    cout<<Cat;
    Rest=*L1%L2;
    cout<<Rest;
    cout<<(*L1)[200000];
    return 0;
}
