//vuex+localStorage存储用户信息
import Vue from 'vue';
import Vuex from 'vuex';

Vue.use(Vuex);

const store = new Vuex.Store({

   state:{
     user:null,
     isLogin:''
   },
  mutations:{
     setUser(state,msg){
       state.user = msg;
       localStorage.setItem("user",JSON.stringify(msg));
     },
    setLogin(state,msg){
       state.isLogin = msg;
       localStorage.setItem("isLogin",msg);
    }
  },
  getters:{
     getUser(state){
        if(!state.user){
          state.user = JSON.parse(localStorage.getItem('user'));
        }
        return state.user;
     },
    getLogin(state){
      if(!state.isLogin){
        state.isLogin = localStorage.getItem('isLogin');
      }
      return state.isLogin;
    }
  }


});

export default store
