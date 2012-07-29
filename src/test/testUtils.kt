package test

import junit.framework.TestCase

public abstract class UsefulTestCase: TestCase() {
    protected fun getTestName(): String {
        val name = getName()
        if (name == null) {
            return ""
        }
        return name.trim("test").decapitalize()
    }
}
