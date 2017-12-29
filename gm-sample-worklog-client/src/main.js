// app entry point

require("./main.css")

const moment = require("moment")

const Vue = require("vue")

const VueRouter = require("vue-router")
Vue.use(VueRouter)

const VueMaterial = require("vue-material")
Vue.use(VueMaterial)
Vue.material.registerTheme('default', {
  primary: 'light-green',
  accent: 'lime',
  warn: 'teal',
  background: 'white'
})

// a few components

Vue.component("gm-menu", require("./components/gm-menu.vue"))

Vue.component("eventlog-header", require("./components/eventlog/eventlog-header.vue"))
Vue.component("eventlog-resume", require("./components/eventlog/eventlog-resume.vue"))

Vue.component("user-header", require("./components/user/user-header.vue"))
Vue.component("user-resume", require("./components/user/user-resume.vue"))

Vue.component("worklog-header", require("./components/worklog/worklog-header.vue"))
Vue.component("worklog-new", require("./components/worklog/worklog-new.vue"))
Vue.component("worklog-resume", require("./components/worklog/worklog-resume.vue"))

// filters

Vue.filter("todate", val => moment(val).format("YYYY-MM-DD HH:mm"))

// and bootstrap the engine

window.rootviewmodel = new Vue({
  render: r => r(require("./components/mountpoint.vue")),
  el: "#app",
})