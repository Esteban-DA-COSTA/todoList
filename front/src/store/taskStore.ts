import {reactive} from "vue";
import {models} from "@/shared/todoList-shared.d";
import TodoTask = models.TodoTask;

interface TaskStore {
    tasks: TodoTask[]
    getTasks: () => void
}

export const taskStore = reactive({
    tasks: [],
    getTasks: async () => {
        fetch("http://127.0.0.1:8080/tasks")
            .then(response => response.json())
            .then(data => {
                console.log(data)
                taskStore.tasks = data
            })
    }
})