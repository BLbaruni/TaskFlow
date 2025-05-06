package com.billy.taskflow.ui.screens.tasklist

data class TodoTask(
    val description: String,
    val isCompleted: Boolean,
    val isNew: Boolean = false
)

fun main() {
    val websiteTodoList = listOf(
        TodoTask(description = "Styleguide creation", isCompleted = false),
        TodoTask(description = "Send wireframes", isCompleted = false),
        TodoTask(description = "Readability About page", isCompleted = false),
        TodoTask(description = "Check color contrast", isCompleted = false),
        TodoTask(description = "New task", isCompleted = false, isNew = true)
    )

    println("Website todo")
    println("------------------")

    websiteTodoList.forEach { task ->
        val checkbox = if (task.isCompleted) "[✓]" else "[ ]"
        val newIndicator = if (task.isNew) "+ " else ""
        println("$checkbox $newIndicator${task.description}")
    }
}