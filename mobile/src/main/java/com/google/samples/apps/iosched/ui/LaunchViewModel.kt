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

package com.google.samples.apps.iosched.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.samples.apps.iosched.shared.domain.prefs.OnboardingCompletedUseCase
import com.google.samples.apps.iosched.shared.result.Result.Loading
import com.google.samples.apps.iosched.shared.result.data
import com.google.samples.apps.iosched.ui.LaunchNavigatonAction.NavigateToMainActivityAction
import com.google.samples.apps.iosched.ui.LaunchNavigatonAction.NavigateToOnboardingAction
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted.Companion.Eagerly
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

/**
 * Logic for determining which screen to send users to on app launch.
 */
@HiltViewModel
class LaunchViewModel @Inject constructor(
    onboardingCompletedUseCase: OnboardingCompletedUseCase
) : ViewModel() {
    val launchDestination = onboardingCompletedUseCase(Unit).map { result ->
        if (result.data == false) {
            NavigateToOnboardingAction
        } else {
            NavigateToMainActivityAction
        }
    }.stateIn(viewModelScope, Eagerly, Loading)
}

sealed class LaunchNavigatonAction {
    object NavigateToOnboardingAction : LaunchNavigatonAction()
    object NavigateToMainActivityAction : LaunchNavigatonAction()
}
