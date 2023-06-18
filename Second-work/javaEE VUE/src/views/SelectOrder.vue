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
            <el-menu-item index="1-1" disabled @click="getDate">查看所有订单</el-menu-item>
            <el-menu-item index="1-2" v-if="show_button" disabled >查看需处理订单</el-menu-item>
          </el-sub-menu>
          <el-menu-item index="2" @click="CreateOrder()">创建订单</el-menu-item>
          <el-menu-item index="3" @click="Refresh()">查找订单</el-menu-item>
          <el-sub-menu index="4">
            <template #title>{{ User }}</template>
            <el-menu-item index="4-1" @click="Logout()">登出</el-menu-item>
          </el-sub-menu>
        </el-menu>
      </div>
      <div class="my_main">
        <div style="padding-top: 20px">
          订单号:
          <el-input type="text" v-model="num" style="width: 50%"></el-input>
          <br>
        </div>
        <el-button style="margin-top: 20px" @click="select()" type="primary">查询</el-button>
        <br>

        <el-dialog v-model="dialogTableVisible" title="信息">
          <el-table :data="orderDate">
            <el-table-column property="num" label="订单号"></el-table-column>
            <el-table-column property="shipper" label="发货人"></el-table-column>
            <el-table-column property="origin" label="发货地"></el-table-column>
            <el-table-column property="destination" label="目的地"></el-table-column>
            <el-table-column property="goodsName" label="货物名"></el-table-column>
            <el-table-column property="carriers" label="货运公司"></el-table-column>
            <el-table-column property="state" label="状态"></el-table-column>
            <el-table-column property="transporter" label="快递员"></el-table-column>
          </el-table>
        </el-dialog>
        <el-dialog v-model="dialogTable" title="error">
          未找到订单，请重新尝试
        </el-dialog>
      </div>
    </div>
  </el-container>
</template>

<script>
import axios from 'axios'

export default {
  name: 'SelectOrder',
  data () {
    return {
      orderDate: [{}],
      num: '',
      dialogTableVisible: false,
      dialogTable: false,
      show_button: true,
      User:sessionStorage.getItem('username')
    }
  },
  mounted () {
    this.getIdentify()
  },
  methods: {
    select () {
      const _this = this
      axios.get('http://localhost:8081/orders/Select/' + this.num).then(function (resp) {
        _this.orderDate = [resp.data.data]
        if (_this.orderDate.toString() !== '') {
          console.log(_this.orderDate)
          _this.dialogTableVisible = true
        } else {
          _this.dialogTable = true
        }

      }).catch((error) => {
        console.log(error)
      })
    },
    CreateOrder () {
      this.$router.push({ path: 'CreateOrder' })
    },
    Refresh () {
      window.location.reload()
    },MyOrder () {
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
