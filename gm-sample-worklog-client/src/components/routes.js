module.exports = [
  { path:"/", redirect:"/eventlog-list" },
  { path:"/auth-login", component: require("../features/auth-login.vue") },
  { path:"/auth-register", component: require("../features/auth-register.vue") },
  { path:"/eventlog-list", component: require("../features/eventlog-list.vue") },
  { path:"/user-list", component: require("../features/user-list.vue") },
  { path:"/worklog-list", component: require("../features/worklog-list.vue") },
] 
