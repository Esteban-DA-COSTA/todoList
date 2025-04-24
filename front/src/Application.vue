<script setup lang="ts">

</script>

<template>
  <UApp>
    <Header/>
    <Suspense>
      <TaskList/>
    </Suspense>
  </UApp>
  
</template>

<style scoped>
main {
  display: flex;
  flex-direction: row;
}

</style>
