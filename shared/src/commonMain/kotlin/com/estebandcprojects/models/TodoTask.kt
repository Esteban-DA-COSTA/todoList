package com.estebandcprojects.models

import kotlinx.serialization.Serializable
import kotlin.js.ExperimentalJsExport
import kotlin.js.JsExport

@OptIn(ExperimentalJsExport::class)
@JsExport
@Serializable
data class TodoTask(val id: Int, var title: String, var completed: Boolean = false)
