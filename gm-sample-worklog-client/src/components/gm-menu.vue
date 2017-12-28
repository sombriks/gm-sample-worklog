<template>
  <md-toolbar>
    <h1 class="md-title" style="flex:1">GM Worklog</h1>
    <md-button href="#/eventlog-list" class="md-raised">
      <md-icon>event_note</md-icon> Events</md-button>
    <md-button href="#/user-list" class="md-raised">
      <md-icon>people_outline</md-icon> Users</md-button>
    <md-button href="#/worklog-list" class="md-raised">
      <md-icon>assignment</md-icon> Worklog</md-button>
    <md-button href="#/auth-login" class="md-raised" v-if="!simplestore.user.userId">
      <md-icon>account_box</md-icon> Login</md-button>
    <md-button href="#/auth-login" class="md-raised" v-if="simplestore.user.userId">
      <md-icon>account_box</md-icon> Logout</md-button>
  </md-toolbar>
</template>

<script>
module.exports = {
  name: "GmMenu",
  data() {
    return {
      simplestore: require("./simplestore")
    };
  }
};
</script>

<style>

</style>
