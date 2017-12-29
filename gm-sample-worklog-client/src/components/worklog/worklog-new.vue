<template>
  <md-layout md-gutter md-column :class="siz">
    <md-button v-if="siz == 'btn'" @click.native="siz = 'form'" class="md-raised md-primary">New WorkLog</md-button>
    <form @submit.prevent="dosave" v-if="siz =='form'" md-flex="100">
      <label>Start</label>
      <md-layout md-flex="100">
        <mu-date-picker v-model="dateStart" hintText="Date" autoOk okLabel="Ok" cancelLabel="Cancel" :dateTimeFormat="dateTimeFormat" required/>
        <mu-time-picker v-model="timeStart" hintText="Time" autoOk okLabel="Ok" cancelLabel="Cancel" format="24hr" required/>
      </md-layout>
      <label>Finish</label>
      <md-layout md-flex="100">
        <mu-date-picker v-model="dateFinish" hintText="Date" autoOk okLabel="Ok" cancelLabel="Cancel" :dateTimeFormat="dateTimeFormat" required :minDate="dateStart" />
        <!-- timePicker missing :minTime. https://github.com/museui/muse-ui/issues/822 -->
        <mu-time-picker v-model="timeFinish" hintText="Time" autoOk okLabel="Ok" cancelLabel="Cancel" format="24hr" required/>
      </md-layout>
      <md-button class="md-raised md-warn" @click.native="clear">Cancel</md-button>
      <md-button class="md-raised md-primary" type="submit">Create</md-button>
    </form>
  </md-layout>
</template>

<script>
const muDateFormat = require("../mu-dateformat");
const simplestore = require("../simplestore");
const api = require("../restapi");
const moment = require("moment");
module.exports = {
  name: "WorkLogNew",
  data() {
    return {
      siz: "btn",
      dateTimeFormat: muDateFormat(),
      worklog: this.mkworklog(),
      dateStart: moment().format("YYYY-MM-DD"),
      timeStart: moment().format("HH:mm"),
      dateFinish: "",
      timeFinish: ""
    };
  },
  methods: {
    dosave() {
      let t1 = `${this.dateStart}T${this.timeStart}:00`;
      t1 += moment().format("Z"); // yes yes offset
      // t1 = moment(t1)
      this.worklog.workLogStart = t1;
      if (this.dateFinish != "" && this.timeFinish != "") {
        let t2 = `${this.dateFinish}T${this.timeFinish}:00`;
        t2 += moment().format("Z");
        // t2 = moment(t2)
        this.worklog.workLogFinish = t2;
      }
      api
        .saveWorkLog(this.worklog)
        .then(ret => {
          if (ret.status != 200) throw ret;
          this.$emit("onsaveworklog", ret.data);
          this.clear();
        })
        .catch(err => {
          console.log(err);
        });
      console.log(this.worklog);
    },
    clear() {
      this.dateStart = moment().format("YYYY-MM-DD");
      this.timeStart = moment().format("HH:mm");
      this.dateFinish = "";
      this.timeFinish = "";
      this.worklog = this.mkworklog();
      this.siz = 'btn';
    },
    mkworklog() {
      return {
        workLogStart: null,
        workLogFinish: null,
        user: {
          userId: simplestore.user.userId
        },
        status: {
          logStatusId: 1
        }
      };
    }
  }
};
</script>

<style scoped>
.form {
  min-height: 240px;
}
.btn {
  min-height: auto;
}
</style>
