<template>
  <div class="center-area">
    <el-form :model="form" :rules="rules" ref="loginForm">
      <!--<h3 v-show="showError" class="error font-center">{{message}}</h3>-->
      <el-form-item label="用户名" :label-width="formLabelWidth" prop="username">
        <el-input v-model="form.username" auto-complete="off"></el-input>
      </el-form-item>
      <el-form-item label="密码" :label-width="formLabelWidth" prop="password">
        <el-input type="password" v-model="form.password" auto-complete="off"></el-input>
      </el-form-item>
    </el-form>
    <div class="center">
      <el-button type="primary" :plain="true" @click="submit">登 陆</el-button>
    </div>
  </div>
</template>

<script>
    var getIp = function(callback){
    var ip_dups = {};

    //compatibility for firefox and chrome
    var RTCPeerConnection = window.RTCPeerConnection
      || window.mozRTCPeerConnection
      || window.webkitRTCPeerConnection;

    //bypass naive webrtc blocking
    if (!RTCPeerConnection) {
      var iframe = document.createElement('iframe');
      //invalidate content script
      iframe.sandbox = 'allow-same-origin';
      iframe.style.display = 'none';
      document.body.appendChild(iframe);
      var win = iframe.contentWindow;
      window.RTCPeerConnection = win.RTCPeerConnection;
      window.mozRTCPeerConnection = win.mozRTCPeerConnection;
      window.webkitRTCPeerConnection = win.webkitRTCPeerConnection;
      RTCPeerConnection = window.RTCPeerConnection
        || window.mozRTCPeerConnection
        || window.webkitRTCPeerConnection;
    }

    //minimal requirements for data connection
    var mediaConstraints = {
      optional: [{RtpDataChannels: true}]
    };

    //firefox already has a default stun server in about:config
    //    media.peerconnection.default_iceservers =
    //    [{"url": "stun:stun.services.mozilla.com"}]
    var servers = undefined;

    //add same stun server for chrome
    if(window.webkitRTCPeerConnection)
      servers = {iceServers: [{urls: "stun:stun.services.mozilla.com"}]};

    //construct a new RTCPeerConnection
    var pc = new RTCPeerConnection(servers, mediaConstraints);

    //listen for candidate events
    pc.onicecandidate = function(ice){

      //skip non-candidate events
      if(ice.candidate){

        //match just the IP address
        var ip_regex = /([0-9]{1,3}(\.[0-9]{1,3}){3})/
        var ip_addr = ip_regex.exec(ice.candidate.candidate)[1];

        //remove duplicates
        if(ip_dups[ip_addr] === undefined)
          callback(ip_addr);

        ip_dups[ip_addr] = true;
      }
    };

    //create a bogus data channel
    pc.createDataChannel("");

    //create an offer sdp
    pc.createOffer(function(result){

      //trigger the stun server request
      pc.setLocalDescription(result, function(){}, function(){});

    }, function(){});
  }
  export default {
    name: "Login",
    data() {
      return {
        showError:false,
        message:'',
        dialogFormVisible: true,
        formLabelWidth: '120px',
        //注册表单数据
        form: {
          username: '',
          password: '',
          loginAddress:''
        },
        rules: {
          username: [
            {required: true, message: '请输入用户名', trigger: 'change'},
            {min: 4, max: 12, message: '长度在 4 到 12 个字符', trigger: 'change'}
          ],
          password: [
            {required: true, message: '请输入密码', trigger: 'change'},
            {min: 8, max: 16, message: '长度在 8 到 16 个字符', trigger: 'change'}
          ]

        }
      };
    },
    methods:{
      submit(){
        var _this = this;
        //获取客户端ip
        getIp(function(ip){
          _this.form.loginAddress = ip;
          _this.$refs['loginForm'].validate((valid) => {
            if (valid) {
              _this.axios.post('http://localhost:8060/chatbot-back/user/login', _this.form)
                .then(function (response) {
                  if(response.data.status !== 200){
                    // _this.showError = true;
                    // _this.message = response.data.msg;
                    _this.$message.error(response.data.msg);
                  }else {
                    _this.$message({
                      message: '登陆成功！',
                      type: 'success'
                    });
                    _this.showError = false;
                    _this.$store.commit('setUser',response.data.data);
                    _this.$store.commit('setLogin','1');
                    _this.$router.push({path:'/main'});
                  }
                })
                .catch(function (error) {
                  _this.$message.error(error);
                });
            } else {
              return false;
            }
          });
        });

      }
    }
  }
</script>

<style scoped>
  .center {
    margin-left: 50%;
  }

  .center-area {
    float: left;
    margin-left: 35%;
    margin-top: 10%;
    width: 500px;
  }

  .error {
    color: red;
  }

  .font-center {
    text-align: center;
  }
</style>
