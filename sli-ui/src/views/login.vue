<template>
  <div class="login">
    <el-form ref="loginForm" :model="loginForm" :rules="loginRules" class="login-form">
      <h3 class="title">97后台管理系统</h3>
      <el-form-item prop="username">
        <el-input v-model="loginForm.username" type="text" auto-complete="off" placeholder="账号">
          <svg-icon slot="prefix" icon-class="user" class="el-input__icon input-icon"/>
        </el-input>
      </el-form-item>
      <el-form-item prop="password">
        <el-input
            v-model="loginForm.password"
            type="password"
            auto-complete="off"
            placeholder="密码"
            @keyup.enter.native="handleLogin"
        >
          <svg-icon slot="prefix" icon-class="password" class="el-input__icon input-icon"/>
        </el-input>
      </el-form-item>
      <el-form-item prop="code">
        <el-input
            v-model="loginForm.code"
            auto-complete="off"
            placeholder="验证码"
            style="width: 63%"
            @keyup.enter.native="handleLogin"
        >
          <svg-icon slot="prefix" icon-class="validCode" class="el-input__icon input-icon"/>
        </el-input>
        <div class="login-code">
          <img :src="codeUrl" @click="getCode"/>
        </div>
      </el-form-item>
      <el-checkbox v-model="loginForm.rememberMe" style="margin:0px 0px 25px 0px;">记住密码</el-checkbox>
      <el-form-item style="width:100%;">
        <el-button
            :loading="loading"
            size="medium"
            type="primary"
            style="width:100%;"
            @click.native.prevent="handleLogin"
        >
          <span v-if="!loading">登 录</span>
          <span v-else>登 录 中...</span>
        </el-button>
      </el-form-item>
    </el-form>
    <!--  底部  -->
    <div class="el-login-footer">
      <span>Copyright © 2018-2019 97.vip All Rights Reserved.</span>
    </div>
  </div>
</template>

<script>
  import {login, getUser} from "@/api/index";
  import {setToken} from "@/utils/auth";
  // import {asd,obj1,obj2} from "@/test/indexa";
  import {getCodeImg} from "@/api/login";
  import Cookies from "js-cookie";
  import {encrypt, decrypt} from '@/utils/jsencrypt'

  export default {
    name: "Login",
    data() {
      return {
        clientId: "cafbac3150ce1951d339",
        clientSecret: "4778c8a8c733e931ebc597f968693b5bd21a3860",
        codeUrl: "",
        cookiePassword: "",
        loginForm: {
          username: "admin",
          password: "admin123",
          rememberMe: false,
          code: "",
          uuid: ""
        },
        loginRules: {
          username: [
            {required: true, trigger: "blur", message: "用户名不能为空"}
          ],
          password: [
            {required: true, trigger: "blur", message: "密码不能为空"}
          ],
          code: [{required: true, trigger: "change", message: "验证码不能为空"}]
        },
        loading: false,
        redirect: undefined
      };
    },
    watch: {
      $route: {
        handler: function (route) {
          this.redirect = route.query && route.query.redirect;
        },
        immediate: true
      }
    },
    created() {
      console.log("created~")
      this.changeWebTitle()
      this.getCode()
      this.getCookie()
    },
    methods: {
      getCode() {
        getCodeImg().then(res => {
          this.codeUrl = "data:image/gif;base64," + res.img
          this.loginForm.uuid = res.uuid
        });
      },
      getCookie() {
        const username = Cookies.get("username")
        const password = Cookies.get("password")
        const rememberMe = Cookies.get('rememberMe')
        this.loginForm = {
          username: username === undefined ? this.loginForm.username : username,
          password: password === undefined ? this.loginForm.password : decrypt(password),
          rememberMe: rememberMe === undefined ? false : Boolean(rememberMe)
        }
      },
      handleLogin() {
        this.$refs.loginForm.validate(valid => {
          if (valid) {
            this.loading = true;
            if (this.loginForm.rememberMe) {
              Cookies.set("username", this.loginForm.username, {expires: 30});
              Cookies.set("password", encrypt(this.loginForm.password), {expires: 30});
              Cookies.set('rememberMe', this.loginForm.rememberMe, {expires: 30});
            } else {
              Cookies.remove("username");
              Cookies.remove("password");
              Cookies.remove('rememberMe');
            }
            this.$store
            	.dispatch("Login", this.loginForm)
            	.then(() => {
            		this.$router.push({ path: this.redirect || "/" });
            	})
            	.catch(() => {
            		this.loading = false;
            		this.getCode();
            	});
          }
        });
      },
      // login() {
      // 	const params = {
      // 		...this.form
      // 	};
      // 	login(params)
      // 		.then(res => {
      // 			console.log(res);
      // 			const data = res.data;
      // 			const token = data.access_token;
      // 			setToken(token);
      // 			this.$router.push({path: "/home"});
      // 		})
      // 		.catch(function (error) {
      // 			console.log(error);
      // 		});
      // },
      // jump() {
      // 	let wd = window.open(
      // 		`https://github.com/login/oauth/authorize?client_id=${this.clientId}&redirect_uri=http://sli.com/uaa/oauth2/redirect`,
      // 		"_blank",
      // 		"width=600,height=400,menubar=no,toolbar=no,location=no"
      // 	);
      // 	// setTimeout(() => {
      // 	//     console.log(wd)
      // 	//     wd.close();
      // 	//     window.location.reload();
      // 	//   }, 5000);
      // },
      changeWebTitle() {
        let hiddenProperty =
            "hidden" in document
                ? "hidden"
                : "webkitHidden" in document
                ? "webkitHidden"
                : "mozHidden" in document
                    ? "mozHidden"
                    : null;
        let visibilityChangeEvent = hiddenProperty.replace(
            /hidden/i,
            "visibilitychange"
        );
        let onVisibilityChange = function () {
          if (!document[hiddenProperty]) {
            document.title = "激活状态！"
          } else {
            document.title = "未激活状态！"
          }
        }
        document.addEventListener(visibilityChangeEvent, onVisibilityChange)
      },
    }
  };
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style rel="stylesheet/scss" lang="scss">
  .login {
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100%;
    background-image: url("../assets/image/login-background.jpg");
    background-size: cover;
  }

  .title {
    margin: 0px auto 30px auto;
    text-align: center;
    color: #707070;
  }

  .login-form {
    border-radius: 6px;
    background: #ffffff;
    width: 400px;
    padding: 25px 25px 5px 25px;
    .el-input {
      height: 38px;
      input {
        height: 38px;
      }
    }
    .input-icon {
      height: 39px;
      width: 14px;
      margin-left: 2px;
    }
  }

  .login-tip {
    font-size: 13px;
    text-align: center;
    color: #bfbfbf;
  }

  .login-code {
    width: 33%;
    height: 38px;
    float: right;
    img {
      width:100%;
      height:100%;
      cursor: pointer;
      vertical-align: middle;
    }
  }

  .el-login-footer {
    height: 40px;
    line-height: 40px;
    position: fixed;
    bottom: 0;
    width: 100%;
    text-align: center;
    color: #fff;
    font-family: Arial;
    font-size: 12px;
    letter-spacing: 1px;
  }
</style>
