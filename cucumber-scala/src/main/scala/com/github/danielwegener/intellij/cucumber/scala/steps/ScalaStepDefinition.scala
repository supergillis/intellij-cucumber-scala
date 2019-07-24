package com.github.danielwegener.intellij.cucumber.scala.steps

import org.jetbrains.plugins.cucumber.steps.AbstractStepDefinition
import org.jetbrains.plugins.scala.lang.psi.api.expr.ScMethodCall
import org.jetbrains.annotations.Nullable
import com.intellij.psi.PsiElement
import org.jetbrains.plugins.scala.lang.psi.api.base.ScLiteral
import java.util
import com.intellij.openapi.diagnostic.Logger
import java.util.Collections

object ScalaStepDefinition {
  val LOG = Logger.getInstance(classOf[ScalaStepDefinition])

  implicit class CucumberString(value: String) {
    /**
      * Replaces all custom type occurrences with regular expressions. It does not actually replace the type
      * parameter with its corresponding regular expression but rather with a catch-all regular expression. That means
      * that {int} or {float} will both be replaced by the same regular expression, i.e. "(.*)".
      *
      * "There should be {int} apple(s)" becomes "There should be (.*) apple(?:s)?"
      * "I have {int} cucumber(s) in my belly/stomach" becomes "I have (.*) cucumber(?:s)? in my (?:belly|stomach)"
      */
    def convertCustomTypeToRegex: String = value
      .replaceAll("\\(([^\\s]*)\\)", "(?:$1)?") // Optional text
      .replaceAll("\\{[^\\s]*\\}", "(.*)") // Parameter type
      .replaceAll("([^\\s]*)/([^\\s]*)", "(?:$1|$2)") // Alternative text
  }
}

class ScalaStepDefinition(scMethod: ScMethodCall) extends AbstractStepDefinition(scMethod) {
  import ScalaStepDefinition._

  override def getVariableNames: util.List[String] = {
    val r = for {
      // WHEN("""regexp""") { (arg0:Int, arg1:String) <-- we want to match these
      arg <- scMethod.args.exprs
      parentheses <- arg.getChildren
    } yield (arg, parentheses)

    LOG.info(r.toString)
    Collections.emptyList()

  }

  @Nullable
  override def getCucumberRegexFromElement(element: PsiElement): String = {

    element match {
      case mc: ScMethodCall =>
        val x = for {
          innerMethodCall <- Some(mc.getEffectiveInvokedExpr).toSeq.collect { case some: ScMethodCall => some }
          literalParameter @ (someOther: ScLiteral) <- innerMethodCall.args.exprs
          if literalParameter.isString
        } yield literalParameter.getValue.toString.convertCustomTypeToRegex
        x.headOption.orNull
      case _ => null
    }

  }
}
