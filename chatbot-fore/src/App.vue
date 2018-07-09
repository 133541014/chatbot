<template>
  <div id="app">
    <el-container>
      <el-header>
        <el-row class="right" v-show="showLogin">
          <el-col :span="3"><router-link to="/login"><el-button round>登录</el-button></router-link></el-col>
          <el-col :span="3" class="btn-space"><router-link to="/register"><el-button round>注册</el-button></router-link></el-col>
        </el-row>
        <el-row class="right" v-show="!showLogin">
          <el-col :span="12">欢迎您：{{username}}</el-col>
          <el-col :span="3" class="btn-space"><router-link to="/"><el-button round @click="logout">注销</el-button></router-link></el-col>
        </el-row>
      </el-header>
      <el-main>
         <router-view></router-view>
      </el-main>
    </el-container>
  </div>
</template>

<script>
  export default {
    name: 'App',
    computed:{
      showLogin(){
          return this.$store.getters.getLogin !== "1";
      },
      username(){
        if(this.$store.getters.getUser){

          return this.$store.getters.getUser.username;
        }else{
          return "";
        }
      }
    },
    methods:{
      logout(){
        var _this = this;
        this.axios.post('http://localhost:8060/chatbot-back/user/logout')
          .then(function (response) {
            // alert("注销成功");
            _this.$message({
              message: '注销成功',
              type: 'success'
            });
            _this.$store.commit('setLogin','');
            _this.$store.commit('setUser',null);
          })
          .catch(function (error) {
            alert(error);
          });
      }
    }

  }
</script>

<style>
  .center {
    margin-left:50%;
    font-size: 25px;
  }
  .right{
    padding-left:70%;
  }
  .btn-space{
    margin-left:15%;
  }
</style>
