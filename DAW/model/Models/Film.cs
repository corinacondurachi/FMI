using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;

namespace model.Models
{
    public class Film
    {
        [Required]
        [Key]
        public int IDFilm { get; set; }
        
        [Required]
        public string Denumire { get; set; }
        
        public virtual ICollection<Recenzie> Recenzii { get; set; }
    }
}