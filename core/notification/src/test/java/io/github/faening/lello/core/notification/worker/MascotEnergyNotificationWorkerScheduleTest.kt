package io.github.faening.lello.core.notification.worker

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.work.WorkInfo
import androidx.work.WorkManager
import androidx.work.testing.WorkManagerTestInitHelper
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test

class MascotEnergyNotificationWorkerScheduleTest {

    private lateinit var context: Context
    private lateinit var workManager: WorkManager

    @Before
    fun setUp() {
        context = ApplicationProvider.getApplicationContext()
        WorkManagerTestInitHelper.initializeTestWorkManager(context)
        workManager = WorkManager.getInstance(context)
    }

    @Test
    fun testWorkerIsScheduled() {
        // Act
        MascotEnergyNotificationWorker.schedule(context)

        // Assert
        val workInfos = workManager.getWorkInfosByTag("mascot_energy_notification").get()
        assertEquals(1, workInfos.size)
        assertEquals(WorkInfo.State.ENQUEUED, workInfos[0].state)
    }

    @Test
    fun testWorkerIsCanceled() {
        // Arrange
        MascotEnergyNotificationWorker.schedule(context)

        // Act
        MascotEnergyNotificationWorker.cancel(context)

        // Assert
        val workInfos = workManager.getWorkInfosByTag("mascot_energy_notification").get()
        assertEquals(0, workInfos.size)
    }
}