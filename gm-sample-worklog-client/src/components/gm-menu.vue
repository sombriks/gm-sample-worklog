<template>
  <md-toolbar>
    <h1 class="md-title" style="flex:1">GM Worklog</h1>
    <md-button href="#/eventlog-list" class="md-raised">
      <md-icon>event_note</md-icon> Events</md-button>
    <md-button href="#/user-list" class="md-raised">
      <md-icon>people_outline</md-icon> Users</md-button>
    <md-button href="#/worklog-list" class="md-raised" :disabled="!simplestore.user.userId">
      <md-icon>assignment</md-icon> Worklogs</md-button>
    <md-button href="#/auth-login" class="md-raised" v-if="!simplestore.user.userId">
      <md-icon>account_box</md-icon> Login</md-button>
    <md-button @click.native="logout" class="md-raised" v-if="simplestore.user.userId">
      <md-icon>account_box</md-icon> Logout</md-button>
  </md-toolbar>
</template>

<script>
const simplestore = require("./simplestore");
const api = require("./restapi");
module.exports = {
  name: "GmMenu",
  data() {
    return {
      simplestore
    };
  },
  methods: {
    logout() {
      simplestore.user = {};
      api.removeAuthToken();
      simplestore.persist();
      window.location.href = "#/";
    }
  }
};
</script>

<style>

</style>
