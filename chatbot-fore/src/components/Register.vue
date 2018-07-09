<template>
  <div class="center-area">
    <el-form :model="form" :rules="rules" ref="registerForm">
      <!--<h3 v-show="showError" class="error font-center">{{message}}</h3>-->
      <el-form-item label="用户名" :label-width="formLabelWidth" prop="username">
        <el-input v-model="form.username" auto-complete="off"></el-input>
      </el-form-item>
      <el-form-item label="密码" :label-width="formLabelWidth" prop="password">
        <el-input type="password" v-model="form.password" auto-complete="off"></el-input>
      </el-form-item>
      <el-form-item label="重复密码" :label-width="formLabelWidth" prop="repassword">
        <el-input type="password" v-model="form.repassword" auto-complete="off"></el-input>
      </el-form-item>
      <el-form-item label="邮箱" :label-width="formLabelWidth" prop="email">
        <el-input v-model="form.email" auto-complete="off"></el-input>
      </el-form-item>
    </el-form>
    <div class="center">
      <el-button type="primary" :plain="true" @click="submit">注 册</el-button>
    </div>
  </div>
</template>

<script>
  export default {
    name: "Register",
    data() {
      var validateRepassword = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请再次输入密码'));
        } else if (value !== this.form.password) {
          callback(new Error('两次输入密码不一致!'));
        } else {
          callback();
        }
      };
      return {
        showError:false,
        message:'',
        dialogFormVisible: true,
        formLabelWidth: '120px',
        //注册表单数据
        form: {
          username: '',
          password: '',
          repassword: '',
          email: ''
        },
        rules: {
          username: [
            {required: true, message: '请输入用户名', trigger: 'change'},
            {min: 4, max: 12, message: '长度在 4 到 12 个字符', trigger: 'change'}
          ],
          password: [
            {required: true, message: '请输入密码', trigger: 'change'},
            {min: 8, max: 16, message: '长度在 8 到 16 个字符', trigger: 'change'}
          ],
          repassword: [
            {required: true, message: '请输入密码', trigger: 'change'},
            {validator: validateRepassword, trigger: 'change'}
          ],
          email: [
            {required: true, message: '请输入邮箱地址', trigger: 'blur'},
            {type: 'email', message: '请输入正确的邮箱地址', trigger: ['change']}
          ]

        }
      };
    },
    methods: {
      cancelForm() {
        this.dialogFormVisible = false;
        this.$refs['registerForm'].resetFields();
      },
      submit() {
        var _this = this;
        this.$refs['registerForm'].validate((valid) => {
          if (valid) {
            this.axios.post('http://localhost:8060/chatbot-back/user/register', this.form)
              .then(function (response) {
                if(response.data.status !== 200){
                  // _this.showError = true;
                  // _this.message = response.data.msg;
                  _this.$message.error(response.data.msg);

                }else {
                  _this.$message({
                    message: '注册成功！激活邮件已经发送到邮箱，请激活',
                    type: 'success'
                  });
                  //alert("注册成功！激活邮件已经发送到邮箱，请激活");
                  //_this.showError = false;
                  _this.$router.push({path:'/login'});
                }
              })
              .catch(function (error) {
                _this.$message.error(error);
              });
          } else {
            return false;
          }
        });

      }
    }
  }
</script>

<style scoped>
  .center{
    margin-left: 50%;
  }
  .center-area{
    float:left;
    margin-left: 35%;
    margin-top: 10%;
    width:500px;
  }
  .error{
    color:red;
  }
  .font-center{
    text-align: center;
  }
</style>
