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
          <el-sub-menu index="1">
            <template #title>查看订单</template>
            <el-menu-item index="1-1" @click="getDate">查看所有订单</el-menu-item>
            <el-menu-item index="1-2" v-if="show_button" @click="getStateDate">查看需处理订单</el-menu-item>
          </el-sub-menu>
          <el-menu-item index="2" @click="CreateOrder()">创建订单</el-menu-item>
          <el-menu-item index="3" @click="SelectOrder()">查找订单</el-menu-item>
          <el-sub-menu index="4">
            <template #title>{{ User }}</template>
            <el-menu-item index="4-1" @click="Logout()">登出</el-menu-item>
          </el-sub-menu>
        </el-menu>
      </div>
      <div>
        <el-main class="my_main">
            <el-table
              :data="tableDate"
              style="margin: auto; width: 90%">
              <el-table-column
                prop="num"
                label="Num"
              >
              </el-table-column>
              <el-table-column
                prop="shipper"
                label="发货人"
            >
            </el-table-column>
            <el-table-column
              prop="origin"
              label="发货地"
            >
            </el-table-column>
            <el-table-column
              prop="destination"
              label="目的地"
            >
            </el-table-column>
            <el-table-column
              prop="goodsName"
              label="货物名"
            >
            </el-table-column>
            <el-table-column
              prop="carriers"
              label="运输公司"
            >
            </el-table-column>
            <el-table-column
              prop="state"
              label="状态"
            >
            </el-table-column>
            <el-table-column
              prop="transporter"
              label="快递员"
              v-if="show_button"
            >
            </el-table-column>
            <el-table-column
              label="选项" width="200px">
              <template #default="scope">
                <div style="display:flex;justify-content: space-around">
                  <div v-show="show_button">
                    <el-button type="primary" @click="UpdateOrder(
                    // scope.row.Num,scope.row.Shipper,scope.row.Destination,scope.row.Origin,
                    // scope.row.GoodsName,scope.row.Carriers,scope.row.State
                    scope.row
                    )">修改
                    </el-button>
                  </div>
                  <el-button type="danger" @click="DeleteOrder(scope.row.num)">删除</el-button>
                </div>

              </template>
            </el-table-column>
          </el-table>
        </el-main>
      </div>
    </div>
  </el-container>

</template>

<script>
import axios from 'axios'
import update from '@/views/UpdateOrder.vue'

export default {
  name: 'MyOrder',
  computed: {
    update () {
      return update
    }
  },
  data () {
    return {
      tableDate: [],
      UserName: this.$route.query.UserName,
      show_button: true,
      User:sessionStorage.getItem('username')
    }
  },
  mounted () {
    this.getDate()
  },
  methods: {
    getDate () {
      const _this = this
      if (sessionStorage.getItem('identity') === 'user') {
        this.show_button = false
        axios.get('http://localhost:8081/orders/' + sessionStorage.getItem('username')).then(function (resp) {
          _this.tableDate = resp.data.data
        }).catch((error) => {
          console.log(error)
        })
      } else if (sessionStorage.getItem('identity') === 'admin') {
        axios.get('http://localhost:8081/orders').then(function (resp) {
          _this.tableDate = resp.data.data
        }).catch((error) => {
          console.log(error)
        })
      }
    },
    getStateDate () {
      const _this = this
      axios.get('http://localhost:8081/orders/State').then(function (resp) {
        _this.tableDate = resp.data.data
      }).catch((error) => {
        console.log(error)
      })
    },
    CreateOrder () {
      this.$router.push({ path: 'CreateOrder' })
    },
    SelectOrder () {
      this.$router.push({ path: 'SelectOrder' })
    },
    UpdateOrder (row) {
      this.$router.push({
        path: 'UpdateOrder',
        query: { num: row.num }
      })
    },
    DeleteOrder (Num) {
      const _this = this
      axios.delete('http://localhost:8081/orders/' + num).then(function (resp) {
        if (resp.data.code === 200) {
          window.location.reload()
          alert(resp.data.msg)
        } else if (resp.data.code === 401) {
          alert(resp.data.msg)
        }
      })
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
