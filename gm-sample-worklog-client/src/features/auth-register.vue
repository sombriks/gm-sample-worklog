<template>
  <form @submit.prevent="doregister">
    <br/>
    <br/>
    <br/>
    <md-card>
      <md-card-header>
        <div class="md-title">Register</div>
      </md-card-header>
      <md-card-content>
        <md-input-container>
          <label>User login</label>
          <md-input v-model="newuser.userLogin" required></md-input>
        </md-input-container>
        <md-input-container>
          <label>User name</label>
          <md-input v-model="newuser.userName" required></md-input>
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
  </form>
</template>

<script>
const md5 = require("md5");
const simplestore = require("../components/simplestore");
const api = require("../components/restapi");
module.exports = {
  name: "AuthRegister",
  data: _ => ({
    newuser: {
      userLogin: "",
      userName: "",
      userHash: ""
    },
    pwd: ""
  }),
  methods: {
    doregister() {
      this.newuser.userHash = md5(this.pwd);
      api
        .register(this.newuser)
        .then(ret => {
          if (ret.status != 200) throw ret;
          simplestore.user = ret.data;
          return api.login(this.newuser);
        })
        .then(ret => {
          if (ret.status != 200) throw ret;
          api.setAuthToken(`Bearer ${ret.data}`);
          simplestore.persist();
          window.location.href = "#/";
        })
        .catch(e => {
          console.log(e);
          alert("Register failed");
        });
    }
  }
};
</script>

