<template>
  <md-layout md-gutter md-column>
    <md-button v-if="!showeditor" @click.native="showeditor=!showeditor" class="md-raised md-primary">New WorkLog</md-button>
    <form @submit.prevent="dosave" v-if="showeditor">
      <fieldset>
        <legend>Start</legend>
        <mu-date-picker hintText="Date" v-model="dateStart" autoOk/>
        <mu-time-picker hintText="Time" v-model="timeStart" format="24hr" autoOk/>
      </fieldset>
    </form>
  </md-layout>
</template>

<script>
const simplestore = require("../simplestore");
const moment = require("moment");

module.exports = {
  name: "WorkLogNew",
  data() {
    return {
      showeditor: false,
      worklog: {
        workLogStart: null,
        workLogFinish: null,
        user: {
          userId: simplestore.user.userId
        }
      },
      dateStart: moment().format("YYYY-MM-DD"),
      timeStart: moment().format("HH:mm")
    };
  },
  methods: {
    dosave() {
      console.log(this.worklog);
    }
  }
};
</script>

<style>

</style>
