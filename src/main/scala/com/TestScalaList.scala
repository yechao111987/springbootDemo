
package com

import org.junit.{Assert, Test}

class TestScalaList {
  def main(args: Array[String]): Unit = {

    println("hello,Main")
  }

  def count(a: Int, b: Int): Int = {
    return a + b
  }
}

@Test
class DemoTest extends Assert {
  @Test
  def test1: Unit = {
    var a: Int = 5
    var b: Int = 6
    var c: Int = 12
    val test1 = new TestScalaList
    var c1: Int = test1.count(a, b)
    assert(c1 == c, "测试不通过")
  }


}

