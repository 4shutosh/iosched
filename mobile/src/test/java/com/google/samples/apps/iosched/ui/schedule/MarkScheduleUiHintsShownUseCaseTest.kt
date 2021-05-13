/*
 * Copyright 2018 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.samples.apps.iosched.ui.schedule

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.samples.apps.iosched.shared.domain.prefs.MarkScheduleUiHintsShownUseCase
import com.google.samples.apps.iosched.test.data.MainCoroutineRule
import com.google.samples.apps.iosched.test.data.runBlockingTest
import com.google.samples.apps.iosched.test.util.fakes.FakePreferenceStorage
import kotlinx.coroutines.test.TestCoroutineDispatcher
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

/**
 * Unit tests for the [ScheduleUiHintsDialogViewModel].
 */
class MarkScheduleUiHintsShownUseCaseTest {

    // Executes tasks in the Architecture Components in the same thread
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    // Overrides Dispatchers.Main used in Coroutines
    @get:Rule
    var coroutineRule = MainCoroutineRule()

    @Test
    fun dismissed_navigateUiHintsShowAction() = coroutineRule.runBlockingTest {
        val prefs = FakePreferenceStorage()
        val useCase = MarkScheduleUiHintsShownUseCase(prefs, TestCoroutineDispatcher())

        useCase.invoke(Unit)
        val hintsShown = prefs.scheduleUiHintsShown
        assertTrue(hintsShown)
    }
}
