package cards.tests

import org.junit.runner.RunWith
import org.junit.runners.Suite
import org.junit.runners.Suite.SuiteClasses

[RunWith(javaClass<Suite>())]
[SuiteClasses(javaClass<CardTest>(), javaClass<HandTest>(), javaClass<RoundStateTest>(), javaClass<NormalGameStateTest>())]
class AllTests {
}
