<template>
  <md-layout md-gutter>
    <md-layout md-flex="10">{{user.userId}}</md-layout>
    <md-layout md-flex="20">{{user.userLogin}}</md-layout>
    <md-layout md-flex="50">{{user.userName}}</md-layout>
    <md-layout md-flex="20">{{user.userCreation | todate}}</md-layout>
  </md-layout>
</template>

<script>
module.exports = {
  name:"UserResume",
  props:["user"]
}
</script>

<style>

</style>
