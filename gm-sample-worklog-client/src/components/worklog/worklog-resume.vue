<template>
  <md-layout md-gutter>
    <md-layout md-flex="10">{{workLog.workLogId}}</md-layout>
    <md-layout md-flex="10">{{workLog.user.userLogin}}</md-layout>
    <md-layout md-flex="20">{{workLog.workLogStart | todate}}</md-layout>
    <md-layout md-flex="50">{{workLog.workLogFinish | todate}}</md-layout>
    <md-layout md-flex="20">{{workLog.workLogCreation | todate}}</md-layout>
  </md-layout>
</template>

<script>
module.exports = {
  name: "WorkLogResume"
};
</script>

<style>

</style>
