
const api = require("./restapi")

const store = {
  user: {},
  persist() {
    localStorage.setItem("gm-user", JSON.stringify(store.user))
    localStorage.setItem("gm-token", api.getAuthToken())
  },
  reload() {
    let gmUser = localStorage.getItem("gm-user")
    store.user = gmUser ? JSON.parse(gmUser) : {}
    let gmToken = localStorage.getItem("gm-token")
    if (gmToken == "undefined") gmToken = null
    if (gmToken)
      api.setAuthToken(gmToken)
    else
      api.removeAuthToken()
  }
}

store.reload()

module.exports = store
