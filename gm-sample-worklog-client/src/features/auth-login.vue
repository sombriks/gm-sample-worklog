<template>
  <form @submit.prevent="dologin">
    <br/>
    <br/>
    <br/>
    <md-card>
      <md-card-content>
        <md-input-container>
          <label>User login</label>
          <md-input v-model="login.userLogin" required></md-input>
        </md-input-container>
        <md-input-container md-has-password>
          <label>Password</label>
          <md-input v-model="pwd" type="password" required></md-input>
        </md-input-container>
      </md-card-content>
      <md-card-actions>
        <md-button href="#/">Back</md-button>
        <span style="flex:1"></span>
        <md-button type="submit">Submit</md-button>
      </md-card-actions>
    </md-card>
    <md-button class="md-raised md-warn" href="#/auth-register">Click here to register</md-button>
  </form>
</template>
<script>
const md5 = require("md5");
const simplestore = require("../components/simplestore");
const api = require("../components/restapi");
module.exports = {
  name: "AuthLogin",
  data() {
    return {
      login: {
        userLogin: "",
        userHash: ""
      },
      pwd: "",
      simplestore
    };
  },
  methods: {
    dologin() {
      let user;
      let token;
      this.login.userHash = md5(this.pwd);
      api
        .findByUserLogin(this.login.userLogin)
        .then(ret => {
          if (ret.status != 200) throw ret;
          user = ret.data[0];
          console.log(user)
          return api.login(this.login);
        })
        .then(ret => {
          if (ret.status != 200) throw ret;
          api.setAuthToken(ret.data);
          simplestore.user = user;
          console.log(ret.data);
        })
        .catch(err => {
          console.log(err);
          alert("Login failed");
        });
    }
  }
};
</script>
