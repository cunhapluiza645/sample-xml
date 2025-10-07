package com.example.benchmark

import androidx.benchmark.macro.FrameTimingMetric
import androidx.benchmark.macro.StartupMode
import androidx.benchmark.macro.junit4.MacrobenchmarkRule
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.By
import androidx.test.uiautomator.Direction
import androidx.test.uiautomator.UiDevice
import org.junit.Rule
import org.junit.Test

class RenderingBenchmark {
    @get:Rule
    val benchmarkRule = MacrobenchmarkRule()

    @Test
    fun renderingSpeed() = benchmarkRule.measureRepeated(
        packageName = "com.example.sample_xml",
        metrics = listOf(FrameTimingMetric()),
        iterations = 100,
        startupMode = StartupMode.COLD
    ) {
        pressHome()
        startActivityAndWait()

        // Access to UiDevice for gestures
        val device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())

        // Scrolling through RecyclerView
        val recycler = device.findObject(By.res("com.example.sample_xml:id/recyclerView"))
        recycler?.setGestureMargin(device.displayWidth / 5)
        recycler?.fling(Direction.DOWN)
        recycler?.fling(Direction.UP)

        // Simulate search
        val searchButton = device.findObject(By.descContains("Suche"))
        searchButton?.click()
        val searchField = device.findObject(By.res("com.example.sample_xml:id/searchInput"))
        searchField?.text = "An"
        device.pressEnter()

        // Wait until the UI is idle
        device.waitForIdle()
    }
}