using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace examen.Models
{
    public class Poezie
    {
        [Required]
        [Key]
        public int IdPoezie { get; set; }
        
        [Required]
        public string Titlu { get; set; }
        
        [Required]
        public string Autor { get; set; }
        
        [Required]
        public int NrStrofe { get; set; }
        
        [Required]
        public int IdVolum { get; set; }

        [ForeignKey("IdVolum")]
        public Volum Volum { get; set; }
        
    }
}