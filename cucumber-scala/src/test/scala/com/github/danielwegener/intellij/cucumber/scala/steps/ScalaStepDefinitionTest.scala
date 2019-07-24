package com.github.danielwegener.intellij.cucumber.scala.steps

import org.junit.Test
import org.junit.Assert._

class ScalaStepDefinitionTest {
  import ScalaStepDefinition._

  @Test
  def testConvertCustomTypeToRegex() = {
    assertEquals("There should be (.*) apple(?:s)?", "There should be {int} apple(s)".convertCustomTypeToRegex)
    assertEquals("I have (.*) cucumber(?:s)? in my (?:belly|stomach)", "I have {int} cucumber(s) in my belly/stomach".convertCustomTypeToRegex)
    assertEquals("(.*) disables connection(?:s)? to (?:Measurements|Blablabla)", "{actor} disables connection(s) to Measurements/Blablabla".convertCustomTypeToRegex)
    // TODO: Make this test succeed
    //assertEquals("There should be (.*) apple(?:\\.\\*)?", "There should be {int} apple(.*)".convertCustomTypeToRegex)
  }
}
