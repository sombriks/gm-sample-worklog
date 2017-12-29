<template>
  <md-layout md-gutter>
    <md-layout md-flex="10">{{eventlog.eventLogId}}</md-layout>
    <md-layout md-flex="20">{{eventlog.eventLogCreation | todate}}</md-layout>
    <md-layout md-flex="20">{{eventlog.type.eventTpeDescription}}</md-layout>
    <md-layout md-flex="50">{{eventlog.eventLogDescription}}</md-layout>
  </md-layout>
</template>

<script>
module.exports = {
  name: "EventLogResume",
  props: ["eventlog"]
};
</script>

<style>

</style>
