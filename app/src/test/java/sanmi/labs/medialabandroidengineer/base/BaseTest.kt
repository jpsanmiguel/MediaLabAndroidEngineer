package sanmi.labs.medialabandroidengineer.base

import org.junit.After
import org.junit.Before
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import sanmi.labs.medialabandroidengineer.di.appModule

abstract class BaseTest: KoinTest {

    @Before
    open fun setUp() {
        startKoin { modules(appModule) }
    }

    @After
    open fun tearDown() {
        stopKoin()
    }
}