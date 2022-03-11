<template>
  <div style="min-height: 100%">
    <el-container style="min-height: 100vh">

      <el-aside :width="sideWidth+'px'" style="background-color: rgb(238, 241, 246); min-height: 100vh;">
        <el-menu :default-openeds="['1', '3']" style="min-height: 100%;overflow-x:hidden"
                 background-color="rgb(48,65,86)"
                 text-color="#fff"
                 active-text-color="#ffd04b"
                 :collapse-transition="false"
                 :collapse="isCollapse"
        >
          <div style="height: 60px; line-height: 60px; text-align: center">
            <img src="../assets/logo.png" style="width: 20px;margin-right: 5px;position: relative; top: 3px">
            <b style="color: #fff" v-show="logoTextShow">CRM System</b>
          </div>
          <el-submenu index="1">
            <template slot="title">
              <i class="el-icon-message"></i>
              <span slot="title">Navigator One</span>
            </template>
            <el-menu-item-group>
              <template slot="title">分组一</template>
              <el-menu-item index="1-1">选项1</el-menu-item>
              <el-menu-item index="1-2">选项2</el-menu-item>
            </el-menu-item-group>
            <el-menu-item-group title="分组2">
              <el-menu-item index="1-3">选项3</el-menu-item>
            </el-menu-item-group>
            <el-submenu index="1-4">
              <template slot="title">选项4</template>
              <el-menu-item index="1-4-1">选项4-1</el-menu-item>
            </el-submenu>
          </el-submenu>
          <el-submenu index="2">
            <template slot="title">
              <i class="el-icon-menu"></i>
              <span slot="title">Navigator Two</span>
            </template>
            <el-menu-item-group>
              <template slot="title">分组一</template>
              <el-menu-item index="2-1">选项1</el-menu-item>
              <el-menu-item index="2-2">选项2</el-menu-item>
            </el-menu-item-group>
            <el-menu-item-group title="分组2">
              <el-menu-item index="2-3">选项3</el-menu-item>
            </el-menu-item-group>
            <el-submenu index="2-4">
              <template slot="title">选项4</template>
              <el-menu-item index="2-4-1">选项4-1</el-menu-item>
            </el-submenu>
          </el-submenu>
          <el-submenu index="3">
            <template slot="title">
              <i class="el-icon-setting"></i>
              <span slot="title">Navigator Three</span>
            </template>
            <el-menu-item-group>
              <template slot="title">分组一</template>
              <el-menu-item index="3-1">选项1</el-menu-item>
              <el-menu-item index="3-2">选项2</el-menu-item>
            </el-menu-item-group>
            <el-menu-item-group title="分组2">
              <el-menu-item index="3-3">选项3</el-menu-item>
            </el-menu-item-group>
            <el-submenu index="3-4">
              <template slot="title">选项4</template>
              <el-menu-item index="3-4-1">选项4-1</el-menu-item>
            </el-submenu>
          </el-submenu>
        </el-menu>

      </el-aside>

      <el-container>

        <el-header style="font-size: 12px;border-bottom: 1px solid rgb(238, 241, 246);line-height: 60px; display: flex">

          <div style="flex: 1;font-size: 25px">
            <span :class="collapseBtnClass" style="cursor: pointer; " @click="collapse"></span>
          </div>

          <el-dropdown style="width: 70px; cursor: pointer">
            <span>王小虎</span><i class="el-icon-arrow-down" style="margin-left: 5px"></i>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item>Profil</el-dropdown-item>
              <el-dropdown-item>新增</el-dropdown-item>
              <el-dropdown-item>Sign out</el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>

        </el-header>

        <el-main>

          <div style="margin: 10px 0">
            <el-input placeholder="name" suffix-icon="el-icon-search" style="width: 200px;" class="mr_5"></el-input>
            <el-input placeholder="start date" suffix-icon="el-icon-date" style="width: 200px;" class="mr_5"></el-input>
            <el-input placeholder="end date" suffix-icon="el-icon-date" style="width: 200px;" class="mr_5"></el-input>
            <el-button type="primary">Query</el-button>
          </div>

          <div style="margin: 10px 0">
            <el-button type="primary">Create<i class="el-icon-circle-plus-outline ml_5"></i></el-button>
            <el-button type="danger">Delete<i class="el-icon-remove-outline ml_5"></i></el-button>
          </div>

          <el-table :data="tableData" border stripe>
            <el-table-column width="35">
              <el-checkbox v-model="checked"></el-checkbox>
            </el-table-column>
            <el-table-column prop="date" label="日期" width="140">
            </el-table-column>
            <el-table-column prop="name" label="姓名" width="120">
            </el-table-column>
            <el-table-column prop="address" label="地址">
            </el-table-column>
            <el-table-column label="Operation">
              <template slot-scope="scope">
                <el-button type=""><i class="el-icon-edit"></i></el-button>
                <el-button type="danger"><i class="el-icon-delete"></i></el-button>
              </template>
            </el-table-column>
          </el-table>

          <div style="padding: 10px 0">
            <el-pagination
                :page-sizes="[5, 10, 15, 20]"
                :page-size="3"
                layout="total, sizes, prev, pager, next, jumper"
                :total="100">
            </el-pagination>
          </div>

        </el-main>

      </el-container>

    </el-container>
  </div>
</template>

<script>
// @ is an alias to /src
import HelloWorld from '@/components/HelloWorld.vue'

export default {
  name: 'HomeView',
  data() {
    const item = {
      date: '2016-05-02',
      name: '王小虎',
      address: '上海市普陀区金沙江路 1518 弄'
    };
    return {
      tableData: Array(10).fill(item),
      collapseBtnClass: "el-icon-s-fold",
      isCollapse: false,
      sideWidth: 200,
      logoTextShow: true
    }
  },
  methods: {
    collapse(){
      this.isCollapse = !this.isCollapse
      if (this.isCollapse) {
        this.sideWidth = 64
        this.collapseBtnClass = "el-icon-s-unfold"
        this.logoTextShow = false
      } else {
        this.sideWidth = 200
        this.collapseBtnClass = "el-icon-s-fold"
        this.logoTextShow = true
      }
    }
  }
}
</script>
