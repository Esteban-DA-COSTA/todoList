<script setup lang="ts">
import {taskStore} from "@/store/taskStore";

// const tasks = taskStore.tasks


// const res = await fetch("http://127.0.0.1:8080/tasks");
// const data = await res.json();
// console.log(tasks);
// tasks.value = data;
//
// function removeFromList(id: number) {
//   tasks.value = tasks.value!.filter(task => task.id !== id)
// }

</script>

<template>
  <UContainer class="p-8 min-h-screen bg-gray-50">
    <Task v-for="task in taskStore.tasks" :key="task.id" :task="task" class="m-8" @deleteTask="taskStore.removeTask"/>
  </UContainer>
</template>

<style scoped>
</style>