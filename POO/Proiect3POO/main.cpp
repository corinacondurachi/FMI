#include <iostream>
#include<cstring>
#include<fstream>
#include<typeinfo>
using namespace std;
ifstream f("date.in");
class Farmacie
{
protected:
    char *nume_proprietar;
    static int nr_obiecte;
public:
    Farmacie();
    Farmacie(char*);
    Farmacie(const Farmacie&);
    virtual ~Farmacie()=0;
    Farmacie& operator=(Farmacie&);
    virtual void citire(istream& in)
    {
        char *v=new char[100];
        in>>v;
        delete[]nume_proprietar;
        nume_proprietar=new char[strlen(v)+1];
        strcpy(nume_proprietar,v);
        delete[]v;
    }
    virtual void afisare(ostream &out)
    {
        out<<nume_proprietar<<" ";
    }
};

istream &operator>>(istream &in,Farmacie &a)
{
    a.citire(in);
    return in;
}

ostream & operator<<(ostream & out, Farmacie &a)
{
    a.afisare(out);
    return out;
}

Farmacie::Farmacie()
{
    nume_proprietar=NULL;
    nr_obiecte++;
}

Farmacie::Farmacie(char* a)
{
    nume_proprietar=new char[strlen(a)+1];
    strcpy(nume_proprietar,a);
    nr_obiecte++;

}

Farmacie::~Farmacie()
{
    delete[]nume_proprietar;
    nr_obiecte--;
}

Farmacie::Farmacie(const Farmacie &farm)
{
    nume_proprietar=new char[strlen(farm.nume_proprietar)+1];
    strcpy(nume_proprietar,farm.nume_proprietar);
    nr_obiecte++;

}

Farmacie& Farmacie:: operator=(Farmacie &ob)
{
    if(strcmp(this->nume_proprietar,ob.nume_proprietar)==0)
        return *this;
    else
    {
        delete[] this->nume_proprietar;
        nume_proprietar=new char[strlen(ob.nume_proprietar)+1];
        strcpy(nume_proprietar,ob.nume_proprietar);
    }
    return *this;
}


class Farmacie_fizica: public Farmacie
{
protected:
    char *denumire;
    int nr_angajati;
    float *profit_lunar;
public:
    Farmacie_fizica();
    ~Farmacie_fizica();
    Farmacie_fizica(char*,char*,int,float*);
    Farmacie_fizica(const Farmacie_fizica&);
    Farmacie_fizica& operator=(Farmacie_fizica&);
    void citire(istream &in)
    {
        this->Farmacie::citire(in);
        char *v=new char[100];
        in>>v;
        delete[]denumire;
        denumire=new char[strlen(v)+1];
        strcpy(denumire,v);
        delete[]v;
        in>>nr_angajati;
        profit_lunar=new float[12];
        for(int i=0; i<12; i++)
            in>>profit_lunar[i];
    }
    void afisare(ostream &out)
    {
        this->Farmacie::afisare(out);
        out<<denumire<<" ";
        for(int i=0; i<12; i++)
            out<<profit_lunar[i]<<" ";
        out<<endl;
    }
};

Farmacie_fizica::Farmacie_fizica():Farmacie()
{
    denumire=NULL;
    nr_angajati=0;
    profit_lunar=NULL;
}

Farmacie_fizica::~Farmacie_fizica()
{
    delete[]denumire;
    delete[] profit_lunar;
}

Farmacie_fizica::Farmacie_fizica(char *a,char *b,int c,float *d): Farmacie(a)
{
    denumire=new char[strlen(b)+1];
    strcpy(denumire,b);
    nr_angajati=c;
    profit_lunar=new float[12];
    for(int i=0; i<12; i++)
        profit_lunar[i]=d[i];
}

Farmacie_fizica::Farmacie_fizica(const Farmacie_fizica& ob): Farmacie(ob)
{

    denumire=new char[strlen(ob.denumire)+1];
    strcpy(denumire,ob.denumire);
    nr_angajati=ob.nr_angajati;
    for(int i=0; i<12; i++)
        profit_lunar[i]=ob.profit_lunar[i];
}

Farmacie_fizica& Farmacie_fizica:: operator = (Farmacie_fizica &ob)
{
    int ok=1;
    for(int i=0; i<12; i++)
        if(profit_lunar[i]!=ob.profit_lunar[i])
            ok=0;
    if(strcmp(this->nume_proprietar,ob.nume_proprietar)==0 && strcmp(this->denumire,ob.denumire)==0 && this->nr_angajati==ob.nr_angajati && ok==1)
        return *this;
    else
    {
        delete[] this->nume_proprietar;
        nume_proprietar=new char[strlen(ob.nume_proprietar)+1];
        strcpy(nume_proprietar,ob.nume_proprietar);
        delete[] this->denumire;
        denumire=new char[strlen(ob.denumire)+1];
        strcpy(denumire,ob.denumire);
        nr_angajati=ob.nr_angajati;
        for(int i=0; i<12; i++)
            profit_lunar[i]=ob.profit_lunar[i];
    }
    return *this;
}



class Farmacie_online: public Farmacie
{
protected:
    char *adresa_web;
    int nr_vizionari;
    float discount;
public:
    Farmacie_online();
    ~Farmacie_online();
    Farmacie_online(char*,char*,int,float);
    Farmacie_online(const Farmacie_online&);
    Farmacie_online& operator=(Farmacie_online&);
    int get_nr_vizitatori();
    void citire(istream &in)
    {
        this->Farmacie::citire(in);
        char *v=new char[100];
        in>>v;
        delete[]adresa_web;
        adresa_web=new char[strlen(v)+1];
        strcpy(adresa_web,v);
        delete[]v;
        in>>nr_vizionari;
        in>>discount;
    }
    void afisare(ostream &out)
    {
        this->Farmacie::afisare(out);
        out<<adresa_web<<" "<<nr_vizionari<<" "<<discount<<endl;
    }
};

Farmacie_online::Farmacie_online()
{
    adresa_web=NULL;
    nr_vizionari=0;
    discount=0;
}

Farmacie_online::~Farmacie_online()
{
    delete[]adresa_web;
}

Farmacie_online::Farmacie_online(char* a, char* b,int c,float d):Farmacie(a)
{
    adresa_web=new char[strlen(b)+1];
    strcpy(adresa_web,b);
    nr_vizionari=c;
    discount=d;
}

Farmacie_online::Farmacie_online(const Farmacie_online &ob):Farmacie(ob)
{
    adresa_web=new char[strlen(ob.adresa_web)+1];
    strcpy(adresa_web,ob.adresa_web);
    nr_vizionari=ob.nr_vizionari;
    discount=ob.discount;
}

Farmacie_online& Farmacie_online:: operator =(Farmacie_online &ob)
{
    if(strcmp(ob.nume_proprietar,this->nume_proprietar)==0 && strcmp(ob.adresa_web,this->adresa_web)==0 && ob.nr_vizionari==this->nr_vizionari && ob.discount==this->discount)
        return *this;
    else
    {
        delete [] nume_proprietar;
        nume_proprietar=new char[strlen(ob.nume_proprietar)+1];
        strcpy(nume_proprietar,ob.nume_proprietar);
        delete[] adresa_web;
        adresa_web=new char[strlen(ob.adresa_web)+1];
        strcpy(adresa_web,ob.adresa_web);
        nr_vizionari=ob.nr_vizionari;
        discount=ob.discount;
    }
    return *this;
}

int Farmacie_online::get_nr_vizitatori()
{
    return nr_vizionari;
}

template<class T> class GestionareFarmacii
{
protected:
    int index;
    T id;
    Farmacie **farm;
public:

    void operator+=(Farmacie*);
    GestionareFarmacii(int);
    ~GestionareFarmacii();
};

template<class T>GestionareFarmacii<T>::GestionareFarmacii(int n)
{
    farm=new Farmacie*[n];
    index=0;
}

template< class T>GestionareFarmacii<T>::~GestionareFarmacii()
{
    for(int i=1; i<=index; i++)
        delete farm[i];
    delete[] farm;
}

template< class T> void GestionareFarmacii<T>::operator +=(Farmacie *ob)
{
    index++;
    farm[index]=ob;
    cout<<*farm[index]<<" ";
}

template<> class GestionareFarmacii<unsigned>
{
protected:
    int index;
    unsigned id;
    Farmacie **farm;
public:
    GestionareFarmacii(int);
    void operator+=(Farmacie *);
    void afisare_vizitatori();
    ~GestionareFarmacii();

};

GestionareFarmacii<unsigned>::GestionareFarmacii(int n)
{
    farm=new Farmacie*[n];
    index=0;
}

GestionareFarmacii<unsigned>::~GestionareFarmacii()
{
     //for(int i=1; i<=index; i++)
        //delete farm[i];
    delete[] farm;
}

void GestionareFarmacii<unsigned>::operator +=(Farmacie *ob)
{
    if(typeid(*ob)==typeid(Farmacie_online))
    {
        index++;
        farm[index]=ob;
    }
}

void GestionareFarmacii<unsigned>::afisare_vizitatori()
{
        cout<<endl<<"Numarul de vizitatori ale farmaciilor online este:"<<endl;
        for(int i=1; i<=index; i++)
        {
            Farmacie_online *fo=dynamic_cast<Farmacie_online*>(farm[i]);
            if(fo!=NULL)
            cout<<fo->get_nr_vizitatori()<<" ";
        }
        cout<<endl;
}

int Farmacie::nr_obiecte=0;
int main()
{
    int x,n,i;
    f>>n;
    Farmacie *far;
    GestionareFarmacii<int> ob(n);
    GestionareFarmacii<unsigned> ob2(n);
    for(i=0; i<n; i++)
    {
        f>>x;
        try
        {
            if(x!=1 && x!=2)
                throw x;
            if(x==1)
                far=new Farmacie_fizica;
            if(x==2)
                far=new Farmacie_online;
            f>>*far;
            ob+=far;
            ob2+=far;
        }
        catch(int x)
        {
            cout<<"optiune gresita \n";
        }
    }
    ob2.afisare_vizitatori();
return 0;
}
