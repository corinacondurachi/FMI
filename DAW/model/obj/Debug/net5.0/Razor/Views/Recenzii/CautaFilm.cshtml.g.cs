#pragma checksum "/Users/corinacondurachi/Desktop/model/Views/Recenzii/CautaFilm.cshtml" "{ff1816ec-aa5e-4d10-87f7-6f4963833460}" "d35999f36bdc3641daa1c76442260743057aa467"
// <auto-generated/>
#pragma warning disable 1591
[assembly: global::Microsoft.AspNetCore.Razor.Hosting.RazorCompiledItemAttribute(typeof(AspNetCore.Views_Recenzii_CautaFilm), @"mvc.1.0.view", @"/Views/Recenzii/CautaFilm.cshtml")]
namespace AspNetCore
{
    #line hidden
    using System;
    using System.Collections.Generic;
    using System.Linq;
    using System.Threading.Tasks;
    using Microsoft.AspNetCore.Mvc;
    using Microsoft.AspNetCore.Mvc.Rendering;
    using Microsoft.AspNetCore.Mvc.ViewFeatures;
#nullable restore
#line 1 "/Users/corinacondurachi/Desktop/model/Views/_ViewImports.cshtml"
using model;

#line default
#line hidden
#nullable disable
#nullable restore
#line 2 "/Users/corinacondurachi/Desktop/model/Views/_ViewImports.cshtml"
using model.Models;

#line default
#line hidden
#nullable disable
    [global::Microsoft.AspNetCore.Razor.Hosting.RazorSourceChecksumAttribute(@"SHA1", @"d35999f36bdc3641daa1c76442260743057aa467", @"/Views/Recenzii/CautaFilm.cshtml")]
    [global::Microsoft.AspNetCore.Razor.Hosting.RazorSourceChecksumAttribute(@"SHA1", @"526f74ce82e2a2c3d0ae5900cc5f729eec53e1ea", @"/Views/_ViewImports.cshtml")]
    public class Views_Recenzii_CautaFilm : global::Microsoft.AspNetCore.Mvc.Razor.RazorPage<model.Models.Recenzie>
    {
        #pragma warning disable 1998
        public async override global::System.Threading.Tasks.Task ExecuteAsync()
        {
            WriteLiteral("\n\n");
#nullable restore
#line 4 "/Users/corinacondurachi/Desktop/model/Views/Recenzii/CautaFilm.cshtml"
  
    ViewBag.Title = "Index";

#line default
#line hidden
#nullable disable
            WriteLiteral("\n<h2>Lista recenzii</h2>\n\n\n<br />\n\n");
#nullable restore
#line 13 "/Users/corinacondurachi/Desktop/model/Views/Recenzii/CautaFilm.cshtml"
  
    var recenzii = ViewData["recenzii"] as List<Recenzie>;
    List<Recenzie> SortedList = recenzii.OrderBy(o=>o.Nota).ToList();
    
    foreach (var recenzie in SortedList)
    {

#line default
#line hidden
#nullable disable
            WriteLiteral("        <div class=\"col-md-4 col-lg-4 col-xs-6 col-sm-4\">\n            \n            ");
#nullable restore
#line 21 "/Users/corinacondurachi/Desktop/model/Views/Recenzii/CautaFilm.cshtml"
       Write(Html.Label("Titlu", "Titlu:"));

#line default
#line hidden
#nullable disable
            WriteLiteral("\n            <p>");
#nullable restore
#line 22 "/Users/corinacondurachi/Desktop/model/Views/Recenzii/CautaFilm.cshtml"
          Write(recenzie.Titlu);

#line default
#line hidden
#nullable disable
            WriteLiteral("</p>\n\n            <br />\n\n            ");
#nullable restore
#line 26 "/Users/corinacondurachi/Desktop/model/Views/Recenzii/CautaFilm.cshtml"
       Write(Html.Label("Film", "Film:"));

#line default
#line hidden
#nullable disable
            WriteLiteral("\n            <p>");
#nullable restore
#line 27 "/Users/corinacondurachi/Desktop/model/Views/Recenzii/CautaFilm.cshtml"
          Write(recenzie.IDFilm);

#line default
#line hidden
#nullable disable
            WriteLiteral("</p>\n\n            <br />\n            \n            ");
#nullable restore
#line 31 "/Users/corinacondurachi/Desktop/model/Views/Recenzii/CautaFilm.cshtml"
       Write(Html.Label("Comentariu", "Comentariu:"));

#line default
#line hidden
#nullable disable
            WriteLiteral("\n            <p>");
#nullable restore
#line 32 "/Users/corinacondurachi/Desktop/model/Views/Recenzii/CautaFilm.cshtml"
          Write(recenzie.Comentariu);

#line default
#line hidden
#nullable disable
            WriteLiteral("</p>\n            \n            <br />\n            \n            ");
#nullable restore
#line 36 "/Users/corinacondurachi/Desktop/model/Views/Recenzii/CautaFilm.cshtml"
       Write(Html.Label("Nota", "Nota:"));

#line default
#line hidden
#nullable disable
            WriteLiteral("\n            <p>");
#nullable restore
#line 37 "/Users/corinacondurachi/Desktop/model/Views/Recenzii/CautaFilm.cshtml"
          Write(recenzie.Nota);

#line default
#line hidden
#nullable disable
            WriteLiteral("</p>\n            \n            <br />\n            \n            ");
#nullable restore
#line 41 "/Users/corinacondurachi/Desktop/model/Views/Recenzii/CautaFilm.cshtml"
       Write(Html.Label("Data", "Data:"));

#line default
#line hidden
#nullable disable
            WriteLiteral("\n            <p>");
#nullable restore
#line 42 "/Users/corinacondurachi/Desktop/model/Views/Recenzii/CautaFilm.cshtml"
          Write(recenzie.Data);

#line default
#line hidden
#nullable disable
            WriteLiteral("</p>\n                        \n            <br />\n            \n            ");
#nullable restore
#line 46 "/Users/corinacondurachi/Desktop/model/Views/Recenzii/CautaFilm.cshtml"
       Write(Html.Label("Utilizator", "Utilizator:"));

#line default
#line hidden
#nullable disable
            WriteLiteral("\n            <p>");
#nullable restore
#line 47 "/Users/corinacondurachi/Desktop/model/Views/Recenzii/CautaFilm.cshtml"
          Write(recenzie.NumeUtilizator);

#line default
#line hidden
#nullable disable
            WriteLiteral("</p>\n                        \n            <br />\n            \n            \n\n            <a");
            BeginWriteAttribute("href", " href=\"", 1136, "\"", 1175, 2);
            WriteAttributeValue("", 1143, "/books/edit/", 1143, 12, true);
#nullable restore
#line 53 "/Users/corinacondurachi/Desktop/model/Views/Recenzii/CautaFilm.cshtml"
WriteAttributeValue("", 1155, recenzie.IdRecenzie, 1155, 20, false);

#line default
#line hidden
#nullable disable
            EndWriteAttribute();
            WriteLiteral(" class=\"btn btn-warning\">Editare</a>\n\n            <br />\n            <br/>\n\n            <a");
            BeginWriteAttribute("href", " href=\"", 1266, "\"", 1307, 2);
            WriteAttributeValue("", 1273, "/books/delete/", 1273, 14, true);
#nullable restore
#line 58 "/Users/corinacondurachi/Desktop/model/Views/Recenzii/CautaFilm.cshtml"
WriteAttributeValue("", 1287, recenzie.IdRecenzie, 1287, 20, false);

#line default
#line hidden
#nullable disable
            EndWriteAttribute();
            WriteLiteral(" class=\"btn btn-danger\">Sterge</a>\n\n        </div>\n");
#nullable restore
#line 61 "/Users/corinacondurachi/Desktop/model/Views/Recenzii/CautaFilm.cshtml"




    }

#line default
#line hidden
#nullable disable
        }
        #pragma warning restore 1998
        [global::Microsoft.AspNetCore.Mvc.Razor.Internal.RazorInjectAttribute]
        public global::Microsoft.AspNetCore.Mvc.ViewFeatures.IModelExpressionProvider ModelExpressionProvider { get; private set; }
        [global::Microsoft.AspNetCore.Mvc.Razor.Internal.RazorInjectAttribute]
        public global::Microsoft.AspNetCore.Mvc.IUrlHelper Url { get; private set; }
        [global::Microsoft.AspNetCore.Mvc.Razor.Internal.RazorInjectAttribute]
        public global::Microsoft.AspNetCore.Mvc.IViewComponentHelper Component { get; private set; }
        [global::Microsoft.AspNetCore.Mvc.Razor.Internal.RazorInjectAttribute]
        public global::Microsoft.AspNetCore.Mvc.Rendering.IJsonHelper Json { get; private set; }
        [global::Microsoft.AspNetCore.Mvc.Razor.Internal.RazorInjectAttribute]
        public global::Microsoft.AspNetCore.Mvc.Rendering.IHtmlHelper<model.Models.Recenzie> Html { get; private set; }
    }
}
#pragma warning restore 1591
