#pragma checksum "/Users/corinacondurachi/Desktop/examen/Views/Poezii/Edit.cshtml" "{ff1816ec-aa5e-4d10-87f7-6f4963833460}" "93bc5d9bcc09a9dfa54c006995fb8f1ae967d80f"
// <auto-generated/>
#pragma warning disable 1591
[assembly: global::Microsoft.AspNetCore.Razor.Hosting.RazorCompiledItemAttribute(typeof(AspNetCore.Views_Poezii_Edit), @"mvc.1.0.view", @"/Views/Poezii/Edit.cshtml")]
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
#line 1 "/Users/corinacondurachi/Desktop/examen/Views/_ViewImports.cshtml"
using examen;

#line default
#line hidden
#nullable disable
#nullable restore
#line 2 "/Users/corinacondurachi/Desktop/examen/Views/_ViewImports.cshtml"
using examen.Models;

#line default
#line hidden
#nullable disable
    [global::Microsoft.AspNetCore.Razor.Hosting.RazorSourceChecksumAttribute(@"SHA1", @"93bc5d9bcc09a9dfa54c006995fb8f1ae967d80f", @"/Views/Poezii/Edit.cshtml")]
    [global::Microsoft.AspNetCore.Razor.Hosting.RazorSourceChecksumAttribute(@"SHA1", @"949bda0d636078f8f7d3cb30ac02e646b188ed8e", @"/Views/_ViewImports.cshtml")]
    public class Views_Poezii_Edit : global::Microsoft.AspNetCore.Mvc.Razor.RazorPage<examen.Models.Poezie>
    {
        #pragma warning disable 1998
        public async override global::System.Threading.Tasks.Task ExecuteAsync()
        {
            WriteLiteral("\n<h2>Editare</h2>\n\n<br />\n\n");
#nullable restore
#line 7 "/Users/corinacondurachi/Desktop/examen/Views/Poezii/Edit.cshtml"
 using (Html.BeginForm())
{

#line default
#line hidden
#nullable disable
            WriteLiteral("    <div class=\"form-horizontal\">\n        \n        ");
#nullable restore
#line 11 "/Users/corinacondurachi/Desktop/examen/Views/Poezii/Edit.cshtml"
   Write(Html.HiddenFor(x => x.IdPoezie));

#line default
#line hidden
#nullable disable
            WriteLiteral(";\n\n        <div class=\"form-group\">\n            ");
#nullable restore
#line 14 "/Users/corinacondurachi/Desktop/examen/Views/Poezii/Edit.cshtml"
       Write(Html.LabelFor(x => x.Titlu, new {@class = "control-label col-md-3"}));

#line default
#line hidden
#nullable disable
            WriteLiteral("\n            <div class=\"col-md-9\">\n                ");
#nullable restore
#line 16 "/Users/corinacondurachi/Desktop/examen/Views/Poezii/Edit.cshtml"
           Write(Html.TextBoxFor(x => x.Titlu, new {@class = "form-control"}));

#line default
#line hidden
#nullable disable
            WriteLiteral("\n                ");
#nullable restore
#line 17 "/Users/corinacondurachi/Desktop/examen/Views/Poezii/Edit.cshtml"
           Write(Html.ValidationMessageFor(x => x.Titlu, "", new {@class = "text-danger"}));

#line default
#line hidden
#nullable disable
            WriteLiteral("\n            </div>\n        </div>\n        \n         <div class=\"form-group\">\n                ");
#nullable restore
#line 22 "/Users/corinacondurachi/Desktop/examen/Views/Poezii/Edit.cshtml"
           Write(Html.LabelFor(x => x.Volum, new { @class = "control-label col-md-3" }));

#line default
#line hidden
#nullable disable
            WriteLiteral("\n                <div class=\"col-md-9\">\n                    ");
#nullable restore
#line 24 "/Users/corinacondurachi/Desktop/examen/Views/Poezii/Edit.cshtml"
               Write(Html.DropDownList("IDVolum", (SelectList)ViewBag.Volume));

#line default
#line hidden
#nullable disable
            WriteLiteral("\n                    ");
#nullable restore
#line 25 "/Users/corinacondurachi/Desktop/examen/Views/Poezii/Edit.cshtml"
               Write(Html.ValidationMessageFor(x => x.IdVolum, "", new {@class = "text-danger"}));

#line default
#line hidden
#nullable disable
            WriteLiteral("\n                </div>\n            </div>\n\n        <div class=\"form-group\">\n            ");
#nullable restore
#line 30 "/Users/corinacondurachi/Desktop/examen/Views/Poezii/Edit.cshtml"
       Write(Html.LabelFor(x => x.NrStrofe, new {@class = "control-label col-md-3"}));

#line default
#line hidden
#nullable disable
            WriteLiteral("\n            <div class=\"col-md-9\">\n                ");
#nullable restore
#line 32 "/Users/corinacondurachi/Desktop/examen/Views/Poezii/Edit.cshtml"
           Write(Html.TextBoxFor(x => x.NrStrofe, new {@class = "form-control"}));

#line default
#line hidden
#nullable disable
            WriteLiteral("\n                ");
#nullable restore
#line 33 "/Users/corinacondurachi/Desktop/examen/Views/Poezii/Edit.cshtml"
           Write(Html.ValidationMessageFor(x => x.NrStrofe, "", new {@class = "text-danger"}));

#line default
#line hidden
#nullable disable
            WriteLiteral("\n            </div>\n        </div>\n\n        <div class=\"form-group\">\n            ");
#nullable restore
#line 38 "/Users/corinacondurachi/Desktop/examen/Views/Poezii/Edit.cshtml"
       Write(Html.LabelFor(x => x.Autor, new {@class = "control-label col-md-3"}));

#line default
#line hidden
#nullable disable
            WriteLiteral("\n            <div class=\"col-md-9\">\n                ");
#nullable restore
#line 40 "/Users/corinacondurachi/Desktop/examen/Views/Poezii/Edit.cshtml"
           Write(Html.TextBoxFor(x => x.Autor, new {@class = "form-control"}));

#line default
#line hidden
#nullable disable
            WriteLiteral("\n                ");
#nullable restore
#line 41 "/Users/corinacondurachi/Desktop/examen/Views/Poezii/Edit.cshtml"
           Write(Html.ValidationMessageFor(x => x.Autor, "", new {@class = "text-danger"}));

#line default
#line hidden
#nullable disable
            WriteLiteral(@"
            </div>
        </div>

        
        

        <div class=""form-group"">
            <div class=""col-md-10 col-md-offset-2"">
                <input type=""submit"" value=""Edit"" class=""btn btn-primary""/>
            </div>
        </div>

    </div>
");
#nullable restore
#line 55 "/Users/corinacondurachi/Desktop/examen/Views/Poezii/Edit.cshtml"
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
        public global::Microsoft.AspNetCore.Mvc.Rendering.IHtmlHelper<examen.Models.Poezie> Html { get; private set; }
    }
}
#pragma warning restore 1591