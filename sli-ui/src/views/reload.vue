<template>
  <div>success!</div>
</template>

<script>
import { SetCok, getUser } from "@/api/index";
import { setToken } from "@/utils/auth";
// import {asd,obj1,obj2} from "@/test/indexa";

export default {
  name: "HelloWorld",
  data() {
    return {
      clientId: "cafbac3150ce1951d339",
      clientSecret: "4778c8a8c733e931ebc597f968693b5bd21a3860",
      form: {
        username: "",
        password: ""
      }
    };
  },
  mounted() {
    // console.log(window.opener);
    const token = this.$route.query.access_token
    if(token){
      console.log(token)
       setToken(token);
    }
    window.opener.location.href = window.opener.location.href
    window.close()
  },
  methods: {}
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
</style>
