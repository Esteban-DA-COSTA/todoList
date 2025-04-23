<script setup lang="ts">
import {reactive} from "vue";
import {models} from "@/shared/todoList-shared.d";
import TodoTask = models.TodoTask;

const task: TodoTask = reactive({id: 1, title: "test", completed: false});
</script>

<template>
  <UContainer>
    My todo is {{task.title}}
  </UContainer>
</template>

<style scoped>

</style>
