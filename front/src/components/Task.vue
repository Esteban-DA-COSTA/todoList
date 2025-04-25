<script setup lang="ts">
import {models} from "@/shared/todoList-shared.d";
import {reactive} from "vue";
import {taskStore} from "@/store/taskStore.ts";
import TodoTask = models.TodoTask;

const {task} = defineProps<{ task: TodoTask }>();

const emit = defineEmits<{
  deleteTask: [id: number],
}>()

const toast = useToast();
const error: Partial<Toast> = reactive({
  title: "Something went wrong",
  description: "",
  color: "error",
});
</script>

<template>
  <UCard :class="{ 'completed': task.completed}" class="task" variant="outline"
         @click="taskStore.completeTask(task.id, !task.completed)">
    <template #header>
      <div class="flex flex-row">
        <h1>{{ task.id }}. {{ task.title }}</h1>
        <UButton class="absolute right-5" color="error" icon="material-symbols-light:close-rounded" variant="soft"
                 @click.stop="taskStore.removeTask(task.id)"/>
      </div>
    </template>
    {{ task.description }}
    <template #footer>
      {{ task.dueDate }}
    </template>
  </UCard>
</template>

<style scoped>
.task {
  background-color: #ffffff;
  border: none;
  border-radius: 12px;
  padding: 1.5rem;
  margin: 1rem;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.05);
  transition: all 0.3s ease;
  position: relative;
  overflow: hidden;
}

.task::before {
  content: '';
  position: absolute;
  top: 0;
  left: 0;
  width: 10px;
  height: 100%;
  background-color: yellow;
}

.task:hover {
  transform: translateY(-3px);
  box-shadow: 0 6px 12px rgba(0, 0, 0, 0.1);
  cursor: pointer;
}

h1 {
  margin: 0 0 0.75rem 0;
  font-size: 1.4rem;
  color: #2c3e50;
  font-weight: 600;
}

p {
  margin: 0;
  color: #666;
  font-size: 1rem;
  line-height: 1.5;
}

.completed {
  background-color: #f8faf9;
  opacity: 0.9;
}

.completed::before {
  background-color: #10b981;
}

.completed h1 {
  color: #4b5563;
  text-decoration: line-through;
  text-decoration-thickness: 2px;
  text-decoration-color: #10b981;
}

.completed p {
  color: #9ca3af;
}

.completed:hover {
  transform: translateY(-2px);
  box-shadow: 0 4px 8px rgba(16, 185, 129, 0.1);
}

</style>
