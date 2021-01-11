using System;
using System.Linq;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.Rendering;
using Microsoft.EntityFrameworkCore;
using model.Data;
using model.Models;

namespace model.Controllers
{
    public class RecenziiController : Controller
    {
        
        private AppDbContext context;

        public RecenziiController(AppDbContext appDbContext)
        {
            context = appDbContext;
        }

        // GET: /recenzii/index
        [HttpGet]
        public ActionResult Index()
        {
            ViewData["recenzii"] = context.Recenzii.Include(x => x.Film).ToList();

            return View();
        }
        
        [HttpGet]
        public ActionResult Cautare()
        {

            return View();
        }
        
        
        // GET: /recenzii/create
        [HttpGet]
        public ActionResult Create()
        {
            var films = context.Films.Select(x => new
            {
                IDFilm = x.IDFilm,
                Denumire = x.Denumire
            }).ToList();

            ViewBag.Films = new SelectList(films, "IDFilm", "Denumire");

            return View();
        }

        // POST: /recenzie/create
        [HttpPost]
        public ActionResult Create(Recenzie recenzie)
        {
            if (ModelState.IsValid)
            {
                context.Recenzii.Add(recenzie);

                context.SaveChanges();

                return RedirectToAction("Index", "Recenzii");
                //return Redirect("Index");
                //return View("Index");
            }

            var filme = context.Films.Select(x => new
            {
                IDFilm = x.IDFilm,
                Denumire = x.Denumire
            }).ToList();

            ViewBag.Films = new SelectList(filme, "IDFilm", "Denumire", recenzie.IDFilm);

            return View(recenzie);
        }
        
        
        // GET: /books/edit/{id}
        [HttpGet]
        public ActionResult Edit(int id)
        {
            var recenzie = context.Recenzii.Find(id);

            if (recenzie == null)
            {
                return NotFound();
            }

            return View(recenzie);
        }

        // POST: /books/edit
        [HttpPost]
        public ActionResult Edit(Recenzie recenzie)
        {
            try
            {
                if (ModelState.IsValid)
                {
                    var oldRecenzie = context.Recenzii.Find(recenzie.IdRecenzie);

                    if (oldRecenzie == null)
                    {
                        return NotFound();
                    }

                    oldRecenzie.Titlu = recenzie.Titlu;
                    oldRecenzie.Comentariu = recenzie.Comentariu;
                    oldRecenzie.Nota = recenzie.Nota;
                    oldRecenzie.Data = recenzie.Data;
                    oldRecenzie.IDFilm = recenzie.IDFilm;
                    oldRecenzie.NumeUtilizator = recenzie.NumeUtilizator;
                    oldRecenzie.IdRecenzie = recenzie.IdRecenzie;

                    TryUpdateModelAsync(oldRecenzie);
                    
                    context.SaveChanges();

                    return RedirectToAction("Index", "Recenzii");
                }
            }
            catch (Exception e)
            {
                return Json(new {error_message = e.Message});
            }

            return View(recenzie);
        }

        // GET: /books/delete/{id}
        [HttpGet]
        public ActionResult Delete(int id)
        {
            var book = context.Recenzii.Find(id);

            if (book == null)
            {
                return NotFound();
            }

            context.Recenzii.Remove(book);

            context.SaveChanges();

            return RedirectToAction("Index", "Recenzii");
        }
        
        // GET: /recenzii/index
        [HttpGet]
        public ActionResult Media()
        {
            ViewData["recenzii"] = context.Recenzii.Include(x => x.Film).ToList();
            ViewData["films"] = context.Films.ToList();

            return View();
        }
        
        // GET: Recenzii/cautafilm/?numefilm=vikings&nota=3
        [HttpGet]
        public ActionResult CautaFilm(string? numeFilm, int? Nota)
        {
            if (numeFilm != null)
            {
                var id = context.Films.Where(x => x.Denumire.ToLower() == numeFilm.ToLower()).ToList()[0].IDFilm;
                ViewData["recenzii"] = context.Recenzii.Include(x => x.Film).Where(x => x.IDFilm == id).ToList();
            }

            if (Nota != null)
            {
                
                ViewData["recenzii"] = context.Recenzii.Include(x => x.Film).Where(x => x.Nota == Nota).ToList();

            }

            if (Nota != null && numeFilm != null)
            {
                var id = context.Films.Where(x => x.Denumire.ToLower() == numeFilm.ToLower()).ToList()[0].IDFilm;
                ViewData["recenzii"] = context.Recenzii.Include(x => x.Film).Where(x => x.IDFilm == id && x.Nota == Nota).ToList();
                
            }
            
            return View();
        }
    }

        
    }
