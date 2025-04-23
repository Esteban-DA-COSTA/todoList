import './assets/TaskMain.css'

import { createApp } from 'vue'
import Application from "@/Application.vue";
import ui from '@nuxt/ui/vue-plugin'

const app = createApp(Application)

app.use(ui)

//#region Provide
app.provide('appName', "Task App")
//#endregion

// Mount the app
app.mount('#app')
