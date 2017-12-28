// app entry point

require("./main.css")

const Vue = require("vue")

const VueRouter = require("vue-router")
Vue.use(VueRouter)

const VueMaterial = require("vue-material")
Vue.use(VueMaterial)

Vue.component("gm-menu", require("./components/gm-menu.vue"))

window.rootviewmodel = new Vue({
  render: r => r(require("./components/mountpoint.vue")),
  el: "#app",
})