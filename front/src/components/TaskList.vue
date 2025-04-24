<script setup lang="ts">
import {models} from "@/shared/todoList-shared.d";
import {ref} from "vue";
import TodoTask = models.TodoTask;

const tasks = ref<TodoTask[] | null>(null);

const res = await fetch("http://127.0.0.1:8080/tasks");
const data = await res.json();
console.log(tasks);
tasks.value = data;

</script>

<template>
  <UContainer class="p-8 min-h-screen bg-gray-50">
      <Task class="m-8" v-for="task in tasks" :key="task.id" :task="task"/>
  </UContainer>
</template>

<style scoped>
</style>