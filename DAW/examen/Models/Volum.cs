using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;

namespace examen.Models
{
    public class Volum
    {
        
        [Required]
        [Key]
        public int IdVolum { get; set; }
        
        [Required]
        public string Denumire { get; set; }
        
        public virtual ICollection<Poezie> Poezii { get; set; }
    }
}