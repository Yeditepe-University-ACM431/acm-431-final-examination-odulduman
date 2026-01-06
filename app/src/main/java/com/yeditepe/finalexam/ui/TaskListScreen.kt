package com.yeditepe.finalexam.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.yeditepe.finalexam.model.Task
import com.yeditepe.finalexam.viewmodel.TaskViewModel

@Composable
fun TaskListScreen(navController: NavController, viewModel: TaskViewModel = viewModel()) {

    val tasks = viewModel.tasks

    Column {
        LazyColumn {
            items(tasks) { task ->
                TaskRow(task = task, navController = navController, onTaskCheckedChange = {
                     viewModel.toggleTask(task.id)
                })
            }
        }
    }
}

@Composable
fun TaskRow(task: Task, navController: NavController, onTaskCheckedChange: (Boolean) -> Unit) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = task.title,
            modifier = Modifier
                .weight(1f)
                .clickable {
                    navController.navigate("taskDetail/${task.title}")
                }
        )
        Spacer(modifier = Modifier.width(8.dp))
        Checkbox(
            checked = task.isCompleted,
            onCheckedChange = onTaskCheckedChange
        )
    }
}
