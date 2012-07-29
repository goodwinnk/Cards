package test

import junit.framework.Test
import junit.framework.TestSuite
import test.simple.SimpleTests

public fun suite(): Test {
    val suite = TestSuite()
    suite.addTestSuite(javaClass<SimpleTests>())
    return suite
}