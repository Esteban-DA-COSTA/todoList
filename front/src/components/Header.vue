<script setup lang="ts">
import {reactive, ref} from 'vue'
import {models} from "@/shared/todoList-shared.d";
import {taskStore} from "@/store/taskStore.ts";
import TodoTask = models.TodoTask;

const openModal = ref(false)

const newTask: TodoTask = reactive({
  id: -1,
  title: "",
  description: "",
  completed: false,
  dueDate: ""
})

async function createTask() {
  await taskStore.createTask(newTask)
  newTask.title = ""
  newTask.description = ""
  newTask.dueDate = ""
  openModal.value = false
}
</script>

<template>
  <h1 class="text-2xl items-center">My todo App</h1>
  <UModal v-model:open="openModal" title="Create a new task">
    <UButton class="xl fixed top-6 right-6 rounded-full shadow-lg hover:shadow-xl
             transform hover:-translate-y-1 transition-all duration-300"
             icon="material-symbols-light:add-2-rounded"
    />

    <template #content>
      <div class="m-10">
        <UFormField label="Title">
          <UInput v-model="newTask.title"/>
        </UFormField>
        <UFormField label="Description">
          <UTextarea v-model="newTask.description" autoresize/>
        </UFormField>
        <UFormField label="Due date">
          <UInput v-model="newTask.dueDate"/>
        </UFormField>
        <UButton class="float-right" type="submit" @click="createTask">Create task</UButton>
      </div>
    </template>
  </UModal>
</template>

<style scoped>

</style>