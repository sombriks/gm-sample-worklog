const axios = require("axios")

const api = axios.create({
  baseURL: "http://127.0.0.1:8080"
})

exports.setAuthToken = token =>
  api.defaults.headers.common['Authorization'] = `Bearer ${token}`

exports.findByUserLogin = userLogin => api.get("/user", {
  params: {
    query: userLogin
  }
})

exports.login = user => api.post("/auth/login", user)

exports.register = user => api.post("/auth/register", user)