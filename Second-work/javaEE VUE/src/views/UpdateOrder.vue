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
            <el-menu-item index="1-1" disabled>查看所有订单</el-menu-item>
            <el-menu-item index="1-2" disabled>查看需处理订单</el-menu-item>
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
        <form action="/update" method="post">
          <div style="padding-top: 20px">
            订 单 号：
            <el-input type="text" v-model="num" readonly="readonly" style="margin-right: 50px; width: 50%"></el-input>
            <br>
          </div>
          <div style="padding-top: 10px">
            发 货 人：
            <el-input type="text" v-model="order.shipper" style="margin-right: 50px; width: 50%"></el-input>
            <br>
          </div>
          <div style="padding-top: 10px">
            发 货 地：
            <el-input type="text" v-model="order.origin" style="margin-right: 50px; width: 50%"></el-input>
            <br>
          </div>
          <div style="padding-top: 10px">
            目 的 地：
            <el-input type="text" v-model="order.destination" style="margin-right: 50px; width: 50%"></el-input>
            <br>
          </div>
          <div style="padding-top: 10px">
            货 物 名：
            <el-input type="text" v-model="order.goodsName" style="margin-right: 50px; width: 50%"></el-input>
            <br>
          </div>
          <div style="padding-top: 10px">
            货运公司：
            <el-input type="text" v-model="order.carriers" style="margin-right: 50px; width: 50%"></el-input>
            <br>
          </div>
          <div style="padding-top: 10px">
            运输状态：
            <el-input type="text" v-model="order.state" style="margin-right: 50px; width: 50%"></el-input>
            <br>
          </div>
          <div style="padding-top: 10px">
            快 递 员：
            <el-input type="text" v-model="order.transporter" style="margin-right: 50px; width: 50%"></el-input>
            <br>
          </div>
          <el-button style="margin-top: 10px" class="btn btn-primary btn-lg btn-block" type="submit"
                     @click="UpdateDate()">更新
          </el-button>
        </form>
      </div>
    </div>
  </el-container>

</template>

<script>
import axios from 'axios'

export default {
  name: 'UpdateOrder',
  data () {
    return {
      Num: this.$route.query.Num,
      form: {},
      order: {
        num: '',
        shipper: '',
        origin: '',
        destination: '',
        goodsName: '',
        carriers: '',
        state: '',
        transporter: ''
      },
      UserName: '',
      User: sessionStorage.getItem('username'),
    }
  },
  mounted () {
    this.select()
  },
  methods: {
    UpdateDate () {
      console.log(this.order)
      this.order.num = this.num
      console.log(this.order.state)
      axios.put('http://localhost:8081/orders', this.order).then(function (resp) {
        alert(resp.data.msg)
      }).catch(error => console.log)
      sessionStorage.removeItem('temp')
      this.$router.push({ path: 'MyOrder' })
    },
    select () {
      const _this = this

      axios.get('http://localhost:8081/orders/Select/' + this.num).then(function (resp) {
        _this.order.shipper = resp.data.data.shipper
        _this.order.origin = resp.data.data.origin
        _this.order.destination = resp.data.data.destination
        _this.order.goodsName = resp.data.data.goodsName
        _this.order.carriers = resp.data.data.carriers
        _this.order.state = resp.data.data.state
        _this.order.transporter= resp.data.data.transporter
      }).catch((error) => {
        console.log(error)
      })
    },
    Logout () {
      this.$router.push({ path: '/' })
    }

  }
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
