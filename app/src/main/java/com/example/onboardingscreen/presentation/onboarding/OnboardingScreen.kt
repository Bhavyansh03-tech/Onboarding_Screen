package com.example.onboardingscreen.presentation.onboarding

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.onboardingscreen.util.Dimens.MediumPadding2
import com.example.onboardingscreen.util.Dimens.PageIndicatorWidth
import com.example.onboardingscreen.common.BackTextButton
import com.example.onboardingscreen.common.NextButton
import com.example.onboardingscreen.common.PageIndicator
import com.example.onboardingscreen.presentation.onboarding.components.OnBoardingPage
import com.example.onboardingscreen.presentation.onboarding.viewModel.OnBoardingEvents
import kotlinx.coroutines.launch

@Composable
fun OnBoardingScreen(
    event: (OnBoardingEvents) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        // Creating a PagerState :->
        val pagerState = rememberPagerState(initialPage = 0){
            pages.size
        }

        // Creating button state for different states :->
        val buttonState = remember {
            derivedStateOf {
                when(pagerState.currentPage){
                    0 -> listOf("", "Next")
                    1 -> listOf("Back", "Next")
                    2 -> listOf("Back", "Get started")
                    else -> listOf("", "")
                }
            }
        }

        // Setup Onboarding Pages or Calling Pager :->
        HorizontalPager(state = pagerState) { index ->
            OnBoardingPage(page = pages[index])
        }

        // Creating Page Indicator :->
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = MediumPadding2)
                .navigationBarsPadding(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            PageIndicator(
                modifier = Modifier.width(PageIndicatorWidth),
                pageSize = pages.size,
                selectedPage = pagerState.currentPage
            )

            // Creating Button :->
            Row(verticalAlignment = Alignment.CenterVertically) {

                val scope = rememberCoroutineScope()

                if (buttonState.value[0].isNotEmpty()) {
                    // Back Button :->
                    BackTextButton(
                        text = buttonState.value[0],
                        onClick = {
                            scope.launch {
                                pagerState.animateScrollToPage(page = pagerState.currentPage - 1)
                            }
                        }
                    )
                }

                NextButton(
                    text = buttonState.value[1],
                    onClick = {
                        scope.launch {
                            if (pagerState.currentPage == 2) {
                                event(OnBoardingEvents.SaveAppEntry)
                            } else {
                                pagerState.animateScrollToPage(page = pagerState.currentPage + 1)
                            }
                        }
                    }
                )
            }
        }

        Spacer(modifier = Modifier.weight(0.3f))

    }
}