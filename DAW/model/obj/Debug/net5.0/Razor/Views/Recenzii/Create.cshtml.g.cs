#pragma checksum "/Users/corinacondurachi/Desktop/model/Views/Recenzii/Create.cshtml" "{ff1816ec-aa5e-4d10-87f7-6f4963833460}" "ef69e97e8e6ff8c077e9dc978b7b8abf06b03f58"
// <auto-generated/>
#pragma warning disable 1591
[assembly: global::Microsoft.AspNetCore.Razor.Hosting.RazorCompiledItemAttribute(typeof(AspNetCore.Views_Recenzii_Create), @"mvc.1.0.view", @"/Views/Recenzii/Create.cshtml")]
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
    [global::Microsoft.AspNetCore.Razor.Hosting.RazorSourceChecksumAttribute(@"SHA1", @"ef69e97e8e6ff8c077e9dc978b7b8abf06b03f58", @"/Views/Recenzii/Create.cshtml")]
    [global::Microsoft.AspNetCore.Razor.Hosting.RazorSourceChecksumAttribute(@"SHA1", @"526f74ce82e2a2c3d0ae5900cc5f729eec53e1ea", @"/Views/_ViewImports.cshtml")]
    public class Views_Recenzii_Create : global::Microsoft.AspNetCore.Mvc.Razor.RazorPage<model.Models.Recenzie>
    {
        #pragma warning disable 1998
        public async override global::System.Threading.Tasks.Task ExecuteAsync()
        {
            WriteLiteral("\n\n");
#nullable restore
#line 4 "/Users/corinacondurachi/Desktop/model/Views/Recenzii/Create.cshtml"
  
    ViewBag.Title = "Create";

#line default
#line hidden
#nullable disable
            WriteLiteral("\n<h2>Add new book</h2>\n\n<br />\n\n");
#nullable restore
#line 12 "/Users/corinacondurachi/Desktop/model/Views/Recenzii/Create.cshtml"
 using (Html.BeginForm())
{

#line default
#line hidden
#nullable disable
            WriteLiteral("<div class=\"form-horizontal\">\n\n    <div class=\"form-group\">\n        ");
#nullable restore
#line 17 "/Users/corinacondurachi/Desktop/model/Views/Recenzii/Create.cshtml"
   Write(Html.LabelFor(x => x.Titlu, new { @class = "control-label col-md-3" }));

#line default
#line hidden
#nullable disable
            WriteLiteral("\n        <div class=\"col-md-9\">\n            ");
#nullable restore
#line 19 "/Users/corinacondurachi/Desktop/model/Views/Recenzii/Create.cshtml"
       Write(Html.TextBoxFor(x => x.Titlu, new { @class = "form-control" }));

#line default
#line hidden
#nullable disable
            WriteLiteral("\n            ");
#nullable restore
#line 20 "/Users/corinacondurachi/Desktop/model/Views/Recenzii/Create.cshtml"
       Write(Html.ValidationMessageFor(x => x.Titlu, "", new { @class = "text-danger" }));

#line default
#line hidden
#nullable disable
            WriteLiteral("\n        </div>\n    </div>\n\n    <div class=\"form-group\">\n        ");
#nullable restore
#line 25 "/Users/corinacondurachi/Desktop/model/Views/Recenzii/Create.cshtml"
   Write(Html.LabelFor(x => x.Film, new { @class = "control-label col-md-3" }));

#line default
#line hidden
#nullable disable
            WriteLiteral("\n        <div class=\"col-md-9\">\n");
            WriteLiteral("            ");
#nullable restore
#line 28 "/Users/corinacondurachi/Desktop/model/Views/Recenzii/Create.cshtml"
       Write(Html.DropDownList("IDFilm", (SelectList)ViewBag.Films));

#line default
#line hidden
#nullable disable
            WriteLiteral("\n            ");
#nullable restore
#line 29 "/Users/corinacondurachi/Desktop/model/Views/Recenzii/Create.cshtml"
       Write(Html.ValidationMessageFor(x => x.IDFilm, "", new {@class = "text-danger"}));

#line default
#line hidden
#nullable disable
            WriteLiteral("\n        </div>\n    </div>\n\n    <div class=\"form-group\">\n        ");
#nullable restore
#line 34 "/Users/corinacondurachi/Desktop/model/Views/Recenzii/Create.cshtml"
   Write(Html.LabelFor(x => x.Comentariu, new { @class = "control-label col-md-3" }));

#line default
#line hidden
#nullable disable
            WriteLiteral("\n        <div class=\"col-md-9\">\n            ");
#nullable restore
#line 36 "/Users/corinacondurachi/Desktop/model/Views/Recenzii/Create.cshtml"
       Write(Html.TextBoxFor(x => x.Comentariu, new { @class = "form-control" }));

#line default
#line hidden
#nullable disable
            WriteLiteral("\n            ");
#nullable restore
#line 37 "/Users/corinacondurachi/Desktop/model/Views/Recenzii/Create.cshtml"
       Write(Html.ValidationMessageFor(x => x.Comentariu, "", new { @class = "text-danger" }));

#line default
#line hidden
#nullable disable
            WriteLiteral("\n        </div>\n    </div>\n\n    <div class=\"form-group\">\n        ");
#nullable restore
#line 42 "/Users/corinacondurachi/Desktop/model/Views/Recenzii/Create.cshtml"
   Write(Html.LabelFor(x => x.Nota, new { @class = "control-label col-md-3" }));

#line default
#line hidden
#nullable disable
            WriteLiteral("\n        <div class=\"col-md-9\">\n            ");
#nullable restore
#line 44 "/Users/corinacondurachi/Desktop/model/Views/Recenzii/Create.cshtml"
       Write(Html.TextBoxFor(x => x.Nota, new { @class = "form-control" }));

#line default
#line hidden
#nullable disable
            WriteLiteral("\n            ");
#nullable restore
#line 45 "/Users/corinacondurachi/Desktop/model/Views/Recenzii/Create.cshtml"
       Write(Html.ValidationMessageFor(x => x.Nota, "", new { @class = "text-danger" }));

#line default
#line hidden
#nullable disable
            WriteLiteral("\n        </div>\n    </div>\n\n    <div class=\"form-group\">\n        ");
#nullable restore
#line 50 "/Users/corinacondurachi/Desktop/model/Views/Recenzii/Create.cshtml"
   Write(Html.LabelFor(x => x.Data, new { @class = "control-label col-md-3" }));

#line default
#line hidden
#nullable disable
            WriteLiteral("\n        <div class=\"col-md-9\">\n            ");
#nullable restore
#line 52 "/Users/corinacondurachi/Desktop/model/Views/Recenzii/Create.cshtml"
       Write(Html.TextAreaFor(x => x.Data, new { @class = "form-control" }));

#line default
#line hidden
#nullable disable
            WriteLiteral("\n            ");
#nullable restore
#line 53 "/Users/corinacondurachi/Desktop/model/Views/Recenzii/Create.cshtml"
       Write(Html.ValidationMessageFor(x => x.Data, "", new { @class = "text-danger" }));

#line default
#line hidden
#nullable disable
            WriteLiteral("\n        </div>\n    </div>\n    \n    <div class=\"form-group\">\n            ");
#nullable restore
#line 58 "/Users/corinacondurachi/Desktop/model/Views/Recenzii/Create.cshtml"
       Write(Html.LabelFor(x => x.NumeUtilizator, new { @class = "control-label col-md-3" }));

#line default
#line hidden
#nullable disable
            WriteLiteral("\n            <div class=\"col-md-9\">\n                ");
#nullable restore
#line 60 "/Users/corinacondurachi/Desktop/model/Views/Recenzii/Create.cshtml"
           Write(Html.TextAreaFor(x => x.NumeUtilizator, new { @class = "form-control" }));

#line default
#line hidden
#nullable disable
            WriteLiteral("\n                ");
#nullable restore
#line 61 "/Users/corinacondurachi/Desktop/model/Views/Recenzii/Create.cshtml"
           Write(Html.ValidationMessageFor(x => x.NumeUtilizator, "", new { @class = "text-danger" }));

#line default
#line hidden
#nullable disable
            WriteLiteral("\n            </div>\n        </div>\n\n    <div class=\"form-group\">\n        <div class=\"col-md-10 col-md-offset-2\">\n            <input type=\"submit\" value=\"Cauta\" class=\"btn btn-primary\" />\n        </div>\n    </div>\n\n</div>\n");
#nullable restore
#line 72 "/Users/corinacondurachi/Desktop/model/Views/Recenzii/Create.cshtml"
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
