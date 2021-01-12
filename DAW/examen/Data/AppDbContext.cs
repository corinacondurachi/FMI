using System;
using Microsoft.EntityFrameworkCore;
using examen.Models;

namespace examen.Data
{
    public class AppDbContext : DbContext
    {
        public AppDbContext(DbContextOptions<AppDbContext> options)
            : base(options)
        {
        }
        
        public  DbSet<Poezie> Poezii { get; set; }
        public  DbSet<Volum> Volume { get; set; }
       
        public void Seed()
        {
            if (Database.EnsureCreated())
            {
                
                Volume.Add(new Volum
                {
                    Denumire = "Poezii Eminescu"
                });
                
                Volume.Add(new Volum
                {
                    Denumire = "Poezii Blaga"
                });
                
                Volume.Add(new Volum
                {
                    Denumire = "Poezii Arghezi"
                });
                
                SaveChanges();

                Poezii.Add(new Poezie
                    {
                        Titlu = "Luceafarul",
                        NrStrofe = 98,
                        Autor = "Eminescu",
                        IdVolum = 1
                    });
                
                Poezii.Add(new Poezie
                {
                    Titlu = "Flori de mucigai",
                    NrStrofe = 2,
                    Autor = "Arghezi",
                    IdVolum = 3
                });
                
                Poezii.Add(new Poezie
                {
                    Titlu = "Revedere",
                    NrStrofe = 7,
                    Autor = "Eminescu",
                    IdVolum = 1
                });
                
                Poezii.Add(new Poezie
                {
                    Titlu = "O ramai",
                    NrStrofe = 2,
                    Autor = "Eminescu",
                    IdVolum = 1
                });
                
                Poezii.Add(new Poezie
                {
                    Titlu = "Eu nu strivesc corola",
                    NrStrofe = 2,
                    Autor = "Blaga",
                    IdVolum = 2
                });
                
                Poezii.Add(new Poezie
                {
                    Titlu = "Testament",
                    NrStrofe = 2,
                    Autor = "Arghezi",
                    IdVolum = 3
                });
                
                SaveChanges();
            }
        }
    }
}
