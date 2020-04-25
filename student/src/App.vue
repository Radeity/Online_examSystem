<template>
  <div id="app">
    <o-header></o-header>
    <router-view clas="main"> </router-view>
  </div>
</template>

<script>
import OHeader from './components/common/header'
export default {
  name: 'app',
  components: {
    OHeader
  }
}
</script>

<style lang="scss" rel="stylesheet/scss">
  @import "assets/css/style.scss";
  #app {
    height: 100%;
  }
</style>
