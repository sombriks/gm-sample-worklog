<template>
  <md-layout md-gutter md-column>
    <md-button v-if="!showeditor" @click.native="showeditor=!showeditor" class="md-raised md-primary">New WorkLog</md-button>
  </md-layout>
</template>

<script>
const simplestore = require("../simplestore");
const moment = require("moment");
const mkworklog = _ => ({
  workLogStart: moment().toJSON(),
  user: {
    userId: simplestore.user.userId
  }
});

module.exports = {
  name: "WorkLogNew",
  data: _ => ({
    showeditor: false,
    newworklog: mkworklog()
  }),
  created() {},
  methods: {}
};
</script>

<style>

</style>
