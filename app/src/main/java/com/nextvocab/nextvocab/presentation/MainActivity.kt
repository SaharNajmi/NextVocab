package com.nextvocab.nextvocab.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.navigation.compose.rememberNavController
import com.nextvocab.nextvocab.presentation.navigation.NavGraph
import com.nextvocab.nextvocab.presentation.sharedviewmodel.SharedViewModel
import com.nextvocab.nextvocab.presentation.ui.detail.FlashCardDetailViewModel
import com.nextvocab.nextvocab.presentation.ui.example.ExampleViewModel
import com.nextvocab.nextvocab.presentation.ui.home.HomeViewModel
import com.nextvocab.nextvocab.presentation.ui.study.StudyViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val exampleViewModel: ExampleViewModel by viewModels()
    private val flashCardDetailViewModel: FlashCardDetailViewModel by viewModels()
    private val sharedViewModel: SharedViewModel by viewModels()
    private val studyViewModel: StudyViewModel by viewModels()
    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavGraph(
                navController = navController,
                sharedViewModel = sharedViewModel,
                detailViewModel = flashCardDetailViewModel,
                exampleViewModel = exampleViewModel,
                studyViewModel = studyViewModel,
                homeViewModel = homeViewModel
            )
        }
    }
}
