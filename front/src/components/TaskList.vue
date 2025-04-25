<script setup lang="ts">
import {taskStore} from "@/store/taskStore";
</script>

<template>
  <UContainer class="p-8 min-h-screen">
    <Task v-for="task in taskStore.tasks" :key="task.id" :task="task" class="m-8" @deleteTask="taskStore.removeTask"/>
  </UContainer>
</template>

<style scoped>
</style>