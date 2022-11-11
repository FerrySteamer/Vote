<template>
  <body id="login-page">
  <el-form class="login-container" label-position="left" label-width="0px">
    <h3 class="login_title" style="font-size: 25px; color: #42b983">登录页面</h3>
    <el-form-item>
      <el-input
        class="login-text"
        type="text"
        v-model="loginForm.loginName"
        auto-complete="off"
        placeholder="账号"
      ></el-input>
    </el-form-item>
    <el-form-item style="width: 100%">
      <el-button
        type="primary"
        style="width: 100%;  border: none"
        @click="login"
      >登录</el-button
      >
    </el-form-item>
  </el-form>
  </body>
</template>

<script>
import 'axios'

export default {
  name: "Login",
  data() {
    return {
      loginForm: {
        loginName: "",
        password: "",
      },
      responseResult: [],
    };
  },
  methods: {
    login () {
      this.$axios.post('/login', {
            loginName: this.loginForm.loginName,
          })
          .then(successResponse => {
            if (successResponse.data.code === 200) {
              this.$router.replace({path: '/'})
            }
          })
          .catch(failResponse => {
          })
      }
  },
};
</script>

<style scoped>
#login-page {
  /* background: url("../assets/chonghang.jpg") no-repeat;
  /* background-position: center; */
  /* background:fixed;  */
  margin-left: 7%;
  height: 90%;
  width: 85%;
  background-size: 90% 100%;
  position: fixed;
}

body {
  margin: 0px;
}

.login-container {
  border-radius: 15px;
  background-clip: padding-box;
  margin: 90px auto;
  width: 350px;
  padding: 35px 35px 15px 35px;
  background: rgba(225,225,225,0.4);
  border: 1px solid #eaeaea;
  box-shadow: 0 0 25px #cac6c6;
}

.login_title {
  margin: 0px auto 40px auto;
  text-align: center;
  color: #505458;
}
</style>

