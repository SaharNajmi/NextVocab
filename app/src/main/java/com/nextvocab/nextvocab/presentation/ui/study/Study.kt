package com.nextvocab.nextvocab.presentation.ui.study

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.nextvocab.nextvocab.domain.model.Word
import com.nextvocab.nextvocab.presentation.ui.WordHeader
import com.nextvocab.nextvocab.presentation.ui.theme.BackColor
import java.util.concurrent.TimeUnit

@Composable
fun Study(
    viewModel: StudyViewModel,
    onBackClick: () -> Unit
) {
    val context = LocalContext.current
    val wordsToReview by viewModel.getTodayReviewWords()
        .collectAsStateWithLifecycle(initialValue = emptyList())

    var currentCardIndex by remember { mutableIntStateOf(0) }
    var isAnswerVisible by remember { mutableStateOf(false) }
    val currentCardToReview = wordsToReview.getOrNull(currentCardIndex)

    if (currentCardIndex > wordsToReview.size) {
        LaunchedEffect(Unit) {
            Toast.makeText(context, "Review Complete!", Toast.LENGTH_LONG)
                .show()
            onBackClick()
        }
    }
    currentCardToReview?.let { currentCard ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(BackColor)
                .padding(start = 12.dp, end = 12.dp, top = 24.dp),
        ) {
            WordHeader(name = currentCard.name, partOfSpeak = currentCard.partOfSpeak,
                onCancelClick = {
                    onBackClick()
                })

            Divider(modifier = Modifier.padding(vertical = 8.dp))

            if (isAnswerVisible)
                Text(
                    text = formatCardContent(currentCard), style = TextStyle(color = Color.White),
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .padding(start = 8.dp, end = 8.dp)
                ) else {
                Spacer(
                    modifier = Modifier
                        .weight(1f)
                )
            }

            if (isAnswerVisible) {
                Row {
                    Button(modifier = Modifier.padding(4.dp),
                        onClick = {
                            currentCardIndex += 1
                            val updated = updateCardState(currentCard, CardFeedback.AGAIN)
                            viewModel.updateWord(updated)

                            isAnswerVisible = false

                        }) {
                        Text(CardFeedback.AGAIN.name)
                    }
                    Button(modifier = Modifier.padding(4.dp),
                        onClick = {
                            currentCardIndex += 1
                            val updated = updateCardState(currentCard, CardFeedback.HARD)
                            viewModel.updateWord(updated)

                            isAnswerVisible = false
                        }) {
                        Text(CardFeedback.HARD.name)
                    }
                    Button(modifier = Modifier.padding(4.dp),
                        onClick = {
                            currentCardIndex += 1
                            val updated = updateCardState(currentCard, CardFeedback.GOOD)
                            viewModel.updateWord(updated)

                            isAnswerVisible = false

                        }) {
                        Text(CardFeedback.GOOD.name)
                    }
                    Button(modifier = Modifier.padding(4.dp),
                        onClick = {
                            currentCardIndex += 1
                            val updated = updateCardState(currentCard, CardFeedback.EASY)
                            viewModel.updateWord(updated)
                            isAnswerVisible = false

                        }) {
                        Text(CardFeedback.EASY.name)
                    }
                }


            } else {
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp), shape = RoundedCornerShape(12.dp),
                    onClick = {
                        isAnswerVisible = true
                    }
                ) {
                    Text("Show Answer")
                }
            }
        }
    }

}


enum class CardFeedback {
    AGAIN,
    HARD,
    GOOD,
    EASY
}

fun formatCardContent(word: Word): String {
    val formattedMeaning = word.meaning.map { it.meaning }.joinToString("\n") { "* $it" }
    val formattedExample = word.examples.map { it.example }.mapIndexed { index, item ->
        "${index + 1}. $item"
    }.joinToString("\n")
    return "$formattedMeaning\n\n$formattedExample"
}


fun updateCardState(
    card: Word,
    feedback: CardFeedback
): Word {
    val newReviewInterval = when (feedback) {
        CardFeedback.AGAIN -> 1
        CardFeedback.HARD -> maxOf(1, card.reviewInterval / 2)
        CardFeedback.GOOD -> card.reviewInterval * 2
        CardFeedback.EASY -> card.reviewInterval * 3
    }
    val newReviewDate =
        System.currentTimeMillis() + TimeUnit.DAYS.toMillis(newReviewInterval.toLong())

    return card.copy(
        feedback = feedback,
        reviewDate = newReviewDate,
        reviewInterval = newReviewInterval
    )
}
