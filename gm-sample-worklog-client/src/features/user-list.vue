<template>
  <md-layout md-gutter md-column>
    <gm-menu></gm-menu>
    <md-input-container>
      <label>Search</label>
      <md-input v-model="query" @input="list(0,true)"></md-input>
    </md-input-container>
    <user-header></user-header>
    <user-resume :user="u" v-for="u in users" :key="u.userId"></user-resume>
    <md-button class="md-raised md-primary" @click.native="list(10)">Load more</md-button>
  </md-layout>
</template>

<script>
const api = require("../components/restapi");
const simplestore = require("../components/simplestore");
module.exports = {
  name: "UserList",
  created() {
    this.list(0);
  },
  data() {
    return {
      start: 0,
      query: "",
      users: []
    };
  },
  methods: {
    list(p, clean) {
      if (clean) {
        this.users = [];
        this.start = 0;
      }
      this.start += p;
      const start = this.start;
      const query = this.query;
      api
        .listUsers({ start, query })
        .then(ret => (this.users = this.users.concat(ret.data)));
    }
  }
};
</script>

<style>

</style>
