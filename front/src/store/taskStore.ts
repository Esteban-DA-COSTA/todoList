import {reactive} from "vue";
import {models} from "@/shared/todoList-shared.d";
import TodoTask = models.TodoTask;

interface TaskStore {
    tasks: TodoTask[]
    getTasks: () => Promise<void>
    removeTask: (id: number) => Promise<void>
    completeTask: (id: number, completed: boolean) => Promise<void>
    createTask: (task: TodoTask) => Promise<void>
}

const errorToast: Partial<Toast> = {
    title: "Something went wrong",
    description: "",
    color: "error",
}

const toaster = useToast();

export const taskStore = reactive<TaskStore>({
    tasks: [],
    getTasks: async () => {
        const response = await fetch("http://127.0.0.1:8080/tasks")
        taskStore.tasks = await response.json()
    },
    removeTask: async (id: number) => {
        await fetch(`http://127.0.0.1:8080/tasks/${id}`, {
            method: "DELETE"
        }).then(response => {
            if (!response.ok) {
                console.error("Something went wrong")
                errorToast.description = response.statusText
                toaster.add(errorToast)
            } else {
                taskStore.tasks = taskStore.tasks.filter(task => task.id !== id)
            }
        })
    },
    completeTask: async (id: number, completed: boolean = true) => {
        await fetch(`http://127.0.0.1:8080/tasks/${id}/complete`, {
            method: "PATCH",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                completed: completed
            })
        }).then(response => {
            if (!response.ok) {
                console.error("Something went wrong")
            } else {
                taskStore.tasks = taskStore.tasks.map(task => {
                    if (task.id === id) {
                        task.completed = completed
                    }
                    return task
                })
            }
        })
    },
    createTask: async (task: TodoTask) => {
        await fetch("http://127.0.0.1:8080/tasks", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(task)
        }).then(response => {
            if (!response.ok) {
                console.error("Something went wrong")
                errorToast.description = response.statusText
                toaster.add(errorToast)
            } else {
                response.json().then(data => {
                    taskStore.tasks.push(data as TodoTask)
                })
                toaster.add({
                    title: "Task created",
                    description: "The task has been created successfully.",
                    color: "success",
                    icon: "material-symbols-light:check-circle-outline",
                });
            }
        })
    }
})

// Execute getTasks when store is created
taskStore.getTasks()