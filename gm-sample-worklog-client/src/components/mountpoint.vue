<template>
<md-layout md-gutter md-align="center">
  <md-layout md-column md-flex-small="100" md-flex-medium="80" md-flex="70">
    <router-view></router-view>
  </md-layout>
</md-layout>
</template>

<script>
const VueRouter = require("vue-router")
const router = new VueRouter({
  routes:require("./routes")
})
module.exports = {
  name:"MountPoint",
  router
}
</script>
