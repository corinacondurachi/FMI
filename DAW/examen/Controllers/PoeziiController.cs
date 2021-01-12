using System;
using System.Collections.Generic;
using System.Linq;
using examen.Data;
using examen.Models;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.Rendering;
using Microsoft.EntityFrameworkCore;

namespace examen.Controllers
{
    public class PoeziiController: Controller
    {
        
        private AppDbContext context;

        public PoeziiController(AppDbContext appDbContext)
        {
            context = appDbContext;
        }
        
        // GET: /poezii/index
        [HttpGet]
        public ActionResult Index()
        {
            ViewData["poezii"] = context.Poezii.Include(x => x.Volum).ToList();

            return View();
        }
        
        
        // GET: /poezii/create
        [HttpGet]
        public ActionResult Create()
        {
            var volume = context.Volume.Select(x => new
            {
                IDVolum = x.IdVolum,
                Denumire = x.Denumire
            }).ToList();

            ViewBag.Volume = new SelectList(volume, "IDVolum", "Denumire");

            return View();
        }

        // POST: /poezie/create
        [HttpPost]
        public ActionResult Create(Poezie poezie)
        {
            if (ModelState.IsValid)
            {
                context.Poezii.Add(poezie);

                context.SaveChanges();

                return RedirectToAction("Index", "Poezii");
               
            }

            var volume = context.Volume.Select(x => new
            {
                IDVolum = x.IdVolum,
                Denumire = x.Denumire
            }).ToList();

            ViewBag.Volume = new SelectList(volume, "IDVolum", "Denumire", poezie.IdVolum);
            return View(poezie);
        }
        
        
        // GET: /poezii/edit/{id}
        [HttpGet]
        public ActionResult Edit(int id)
        {
            var poezie = context.Poezii.Find(id);
            
            var volume = context.Volume.Select(x => new
            {
                IDVolum = x.IdVolum,
                Denumire = x.Denumire
            }).ToList();

            ViewBag.Volume = new SelectList(volume, "IDVolum", "Denumire");

            if (poezie == null)
            {
                return NotFound();
            }

            return View(poezie);
        }
        
        // POST: /books/edit
        [HttpPost]
        public ActionResult Edit(Poezie poezie)
        {
            try
            {
                if (ModelState.IsValid)
                {
                    var oldPoezie = context.Poezii.Find(poezie.IdPoezie);

                    if (oldPoezie == null)
                    {
                        return NotFound();
                    }
                    
                    var volume = context.Volume.Select(x => new
                    {
                        IDVolum = x.IdVolum,
                        Denumire = x.Denumire
                    }).ToList();

                    ViewBag.Volume = new SelectList(volume, "IDVolum", "Denumire", poezie.IdVolum);

                    oldPoezie.Titlu = poezie.Titlu;
                    oldPoezie.NrStrofe = poezie.NrStrofe;
                    oldPoezie.Autor = poezie.Autor;
                    oldPoezie.IdVolum = poezie.IdVolum;
                    
                    TryUpdateModelAsync(oldPoezie);
                    
                    context.SaveChanges();

                    return RedirectToAction("Index", "Poezii");
                }
            }
            catch (Exception e)
            {
                return Json(new {error_message = e.Message});
            }

            return View(poezie);
        }
        
        
        // GET: /books/delete/{id}
        [HttpGet]
        public ActionResult Delete(int id)
        {
            var book = context.Poezii.Find(id);

            if (book == null)
            {
                return NotFound();
            }

            context.Poezii.Remove(book);

            context.SaveChanges();

            return RedirectToAction("Index", "Poezii");
        }
        
        //ruta speciala care contine acel substring in titlul poeziei
        // https://localhost:5001/poezii/search/str
        [HttpGet("poezii/search/{str}")]
        public ActionResult Search(string str)
        {
            ViewData["poezii"] = context.Poezii.Include(x => x.Volum).Where(x => x.Titlu.ToLower().Contains(str)).ToList();
            return View();
        }
        
        //ruta speciala care contine acel substring in titlul volumului
        // https://localhost:5001/poezii/searchVolum/str
        [HttpGet("poezii/searchVolum/{str}")]
        public ActionResult SearchVolum(string str)
        {
            ViewData["poezii"] = context.Poezii.Include(x => x.Volum).Where(x => x.Volum.Denumire.ToLower().Contains(str)).ToList();
            return View();
        }
        
        [HttpGet]
        public ActionResult Cautare()
        {

            return View();
        }
        
        
        // GET: poezii/cauta/?titluPoezie=ramai&titluVolum=Eminescu
        [HttpGet]
        public ActionResult CautarePoezie(string titluPoezie, string titluVolum)
        {
            if (titluPoezie != null && titluVolum != null)
            {
                ViewData["poezii"] = context.Poezii.Include(x => x.Volum).Where(x => x.Titlu.ToLower().Contains(titluPoezie) && x.Volum.Denumire.ToLower().Contains(titluVolum)).ToList();

            }
            
            return View();
        }
        
    }
}