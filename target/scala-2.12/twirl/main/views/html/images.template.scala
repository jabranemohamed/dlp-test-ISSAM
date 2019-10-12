
package views.html

import _root_.play.twirl.api.TwirlFeatureImports._
import _root_.play.twirl.api.TwirlHelperImports._
import _root_.play.twirl.api.Html
import _root_.play.twirl.api.JavaScript
import _root_.play.twirl.api.Txt
import _root_.play.twirl.api.Xml
import models._
import controllers._
import play.api.i18n._
import views.html._
import play.api.templates.PlayMagic._
import java.lang._
import java.util._
import scala.collection.JavaConverters._
import play.core.j.PlayMagicForJava._
import play.mvc._
import play.api.data.Field
import play.mvc.Http.Context.Implicit._
import play.data._
import play.core.j.PlayFormsMagicForJava._

object images extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template1[List[ImageDto],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(images : List[ImageDto]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*1.27*/("""

"""),format.raw/*3.1*/("""<div class="main" style="margin:0 auto ; width:1080px">
"""),_display_(/*4.2*/for(image <- images) yield /*4.22*/ {_display_(Seq[Any](format.raw/*4.24*/(""" """),format.raw/*4.25*/("""<img src=" """),_display_(/*4.37*/image/*4.42*/.getDownload_url()),format.raw/*4.60*/("""" width="250" height="250">
  """)))}),format.raw/*5.4*/("""
"""),format.raw/*6.1*/("""</div>
 """))
      }
    }
  }

  def render(images:List[ImageDto]): play.twirl.api.HtmlFormat.Appendable = apply(images)

  def f:((List[ImageDto]) => play.twirl.api.HtmlFormat.Appendable) = (images) => apply(images)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  DATE: Sat Oct 12 19:01:45 CEST 2019
                  SOURCE: /Users/jabranemohamed/Desktop/dlp-test4/app/views/images.scala.html
                  HASH: fc636e43c266fb6eab437eb38b160d4f371e7927
                  MATRIX: 957->1|1077->26|1107->30|1190->88|1225->108|1264->110|1292->111|1330->123|1343->128|1381->146|1442->178|1470->180
                  LINES: 28->1|33->1|35->3|36->4|36->4|36->4|36->4|36->4|36->4|36->4|37->5|38->6
                  -- GENERATED --
              */
          