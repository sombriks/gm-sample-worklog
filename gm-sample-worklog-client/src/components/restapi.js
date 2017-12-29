const axios = require("axios")

const api = axios.create({
  baseURL: "http://127.0.0.1:8080"
})

exports.setAuthToken = token =>
  api.defaults.headers.common['Authorization'] = token


exports.getAuthToken = _ => api.defaults.headers.common['Authorization']

exports.removeAuthToken = _ =>
  delete api.defaults.headers.common['Authorization']

exports.findByUserLogin = userLogin => api.get("/user", {
  params: {
    query: userLogin
  }
})

exports.login = user => api.post("/auth/login", user)

exports.register = user => api.post("/auth/register", user)

exports.listEventLogs = params => api.get("/eventlog", { params })

exports.listUsers = params => api.get("/user", { params })

exports.listWorkLogs = params => api.get("/worklog", { params })