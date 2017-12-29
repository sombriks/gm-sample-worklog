<template>
  <md-layout md-gutter md-column>
    <gm-menu></gm-menu>
    <worklog-new></worklog-new>
    <worklog-header></worklog-header>
    <worklog-resume v-for="w in worklogs" :key="w.workLogId" :worklog="w"></worklog-resume>
    <md-button class="md-raised md-primary" @click.native="list(10)">Load more</md-button>
  </md-layout>
</template>

<script>
const api = require("../components/restapi");
const simplestore = require("../components/simplestore");
module.exports = {
  name: "WorkLogList",
  // which style is better? still undecided
  data: _ => ({
    start: 0,
    simplestore,
    worklogs: []
  }),
  created() {
    if (!simplestore.user.userId) window.location.href = "#/";
    this.list(0);
  },
  methods: {
    list(p, reset) {
      this.start += p;
      const start = this.start;
      const userId = simplestore.user.userId;
      api
        .listWorkLogs({ userId, start })
        .then(ret => (this.worklogs = this.worklogs.concat(ret.data)));
    }
  }
};
</script>

<style>

</style>
