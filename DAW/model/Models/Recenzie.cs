using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using Microsoft.AspNetCore.Mvc.Rendering;

namespace model.Models
{
    public class Recenzie
    {
        [Key]
        public int IdRecenzie { get; set; }
        
        [Required]
        public string Titlu { get; set; }
        
        [Required]
        public string Comentariu { get; set; }
        
        [Required]
        [Range(1, 5)]
        public int Nota { get; set; }
        
        [Required]
        public DateTime Data { get; set; }
        
        [Required]
        public string NumeUtilizator { get; set; }
        
        [Required]
        public int IDFilm { get; set; }

        [ForeignKey("IDFilm")]
        public Film Film { get; set; }
        
        
        

    }
}