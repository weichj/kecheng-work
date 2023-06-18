<template>
  <el-container class="container">
    <div style="width: 100%">
      <div>
        <el-menu
          :default-active="activeIndex"
          class="el-menu-demo"
          mode="horizontal"
          :ellipsis="false"
        >
          <el-menu-item index="0" disabled>IKUN管理系统</el-menu-item>
          <div class="flex-grow"/>
          <el-sub-menu index="1" @click="MyOrder()">
            <template #title>查看订单</template>
            <el-menu-item index="1-1" disabled @click="MyOrder">查看所有订单</el-menu-item>
            <el-menu-item index="1-2" v-if="show_button" disabled @click="MyOrder">查看需处理订单</el-menu-item>
          </el-sub-menu>
          <el-menu-item index="2" @click="Refresh()">创建订单</el-menu-item>
          <el-menu-item index="3" @click="SelectOrder()">查找订单</el-menu-item>
          <el-sub-menu index="4">
            <template #title>{{ User }}</template>
            <el-menu-item index="4-1" @click="Logout()">登出</el-menu-item>
          </el-sub-menu>
        </el-menu>
      </div>
      <div class="my_main">
        <el-form action="/insert" method="post">
          <div style="padding-top: 40px">
            发货人：
            <el-input type="text" v-model="shipper" style="margin-right: 50px; width: 50%"
                      readonly="readonly"></el-input>
            <br>
          </div>
          <div style="padding-top: 10px">
            发货地：
            <el-input type="text" v-model="order.origin" style="margin-right: 50px; width: 50%"></el-input>
            <br>
          </div>
          <div style="padding-top: 10px">
            目的地：
            <el-input type="text" v-model="order.destination" style="margin-right: 50px; width: 50%"></el-input>
            <br>
          </div>
          <div style="padding-top: 10px">
            货物名：
            <el-input type="text" v-model="order.goodsName" style="margin-right: 50px; width: 50%"></el-input>
            <br>
          </div>
          <div style="padding-top: 10px">
            运输商：
            <el-input type="text" v-model="order.carriers" style="margin-right: 50px; width: 50%"></el-input>
            <br>
          </div>
          <el-button @click="reg()" type="primary" style="margin-top: 20px">创建</el-button>
        </el-form>
      </div>
    </div>
  </el-container>
</template>

<script>
import axios from 'axios'

export default {
  name: 'CreateOrder',
  data () {
    return {
      Shipper: sessionStorage.getItem('username'),
      form: {},
      order: {
        num: '',
        shipper: '',
        origin: '',
        destination: '',
        goodsName: '',
        carriers: '',
        state: ''
      },
      show_button: true,
      User:sessionStorage.getItem('username')
    }
  },
  mounted () {
    this.getIdentify()
  },
  methods: {
    reg () {
      this.order.Shipper = this.Shipper
      axios.post('http://localhost:8081/orders', this.order).then(function (resp) {
        alert(resp.data.msg)
      }).catch((error) => {
        console.log(error)
      })
      this.$router.replace('/MyOrder')
    },
    SelectOrder () {
      this.$router.push({ path: 'SelectOrder' })
    },
    Refresh () {
      window.location.reload()
    },
    MyOrder () {
      this.$router.push({ path: 'MyOrder' })
    },
    getIdentify () {
      const _this = this
      if (sessionStorage.getItem('identity') === 'user') {
        this.show_button = false

      }
    },
    Logout(){
      this.$router.push({ path: '/' })
    }
  },
}
</script>

<style scoped>
.app {
  height: 100%;
  position: absolute;
  top: 0px;
  left: 0px;
  width: 100%;
}

.container {
  height: 100%;
  margin: 0;
  padding: 0;
  width: 100%;
}

.my_header {
  background: cornflowerblue;
  color: #fff;
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-top: 0px;
  margin-left: 0px;
}

.my_sysName {
  font-size: 20px;
  font-weight: 500;
}

.my_header_right {
  display: flex;
  align-items: center;
  justify-content: space-around;
  width: 300px;
}

.my_main {
  margin-top: 0px;
  margin-left: 0px;
  background: #E4E7ED;
  height: 100%;
  weight: 100%;

}

.my_button {

  color: #fff;
  width: 50px;
  background-color: #80D0C7;
  justify-content: center;

}

.e_button {

  color: #0a0a0a;
  width: 100px;
  background-color: #80D0C7;

}

.flex-grow {
  flex-grow: 1;
}
</style>
