using System;
using Microsoft.EntityFrameworkCore;
using model.Models;

namespace model.Data
{
    public class AppDbContext : DbContext
    {
        public AppDbContext(DbContextOptions<AppDbContext> options)
            : base(options)
        {
        }

        public  DbSet<Film> Films { get; set; }
        public  DbSet<Recenzie> Recenzii { get; set; }

        public void Seed()
        {
            if (Database.EnsureCreated())
            {
                Films.Add(new Film
                {
                    IDFilm = 1,
                    Denumire = "Titanic"
                });

                Films.Add(new Film
                {
                    IDFilm = 2,
                    Denumire = "Vikings"
                });

                Recenzii.Add(new Recenzie
                    {
                        Comentariu = "Foarte bun",
                        Data = DateTime.Now,
                        IDFilm = 1,
                        Nota = 4,
                        NumeUtilizator = "Masd",
                        Titlu = "recenzie"

                    }

                );
                
                Recenzii.Add(new Recenzie
                    {
                        Comentariu = "Foarte prost",
                        Data = DateTime.Now,
                        IDFilm = 2,
                        Nota = 1,
                        NumeUtilizator = "Masdakiii",
                        Titlu = "recenzie proasta"

                    }

                );

                SaveChanges();
            }
        }
            }
        }
    
