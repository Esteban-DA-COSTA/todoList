<script setup lang="ts">
import type { NavigationMenuItem } from '@nuxt/ui'
import { ref } from 'vue'

const items = ref<NavigationMenuItem[]>([
  {
    label: 'Home',
    to: '/'
  }
])

</script>

<template>
    <UNavigationMenu :items="items"/>
</template>

<style scoped>

</style>