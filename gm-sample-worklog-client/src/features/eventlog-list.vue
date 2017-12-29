<template>
  <md-layout md-gutter md-column>
    <gm-menu></gm-menu>
    <eventlog-header></eventlog-header>
    <eventlog-resume v-for="ev in events" :key="ev.eventLogId" :eventlog="ev"></eventlog-resume>
    <md-button class="md-raised md-primary" @click.native="list(10)">Load more</md-button>
  </md-layout>
</template>

<script>
const api = require("../components/restapi");
module.exports = {
  name: "EventLogList",
  data() {
    return {
      start: 0,
      events: []
    };
  },
  created() {
    this.list();
  },
  methods: {
    list(start) {
      if(start) this.start += start
      api
        .listEventLogs({ start: this.start })
        .then(ret => (this.events = this.events.concat(ret.data)));
    }
  }
};
</script>

<style>

</style>
