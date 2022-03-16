<template>
  <el-menu :default-openeds="['1', '3']" style="min-height: 100%;overflow-x:hidden"
           background-color="rgb(48,65,86)"
           text-color="#fff"
           active-text-color="#ffd04b"
           :collapse-transition="false"
           :collapse="isCollapse"
           router
  >
    <div style="height: 60px; line-height: 60px; text-align: center">
      <img src="../assets/logo.png" style="width: 20px;margin-right: 5px;position: relative; top: 3px">
      <b style="color: #fff" v-show="logoTextShow">CRM System</b>
    </div>

    <el-menu-item index="/">
      <template slot="title">
        <i class="el-icon-s-home"></i>
        <span slot="title">Homepage</span>
      </template>
    </el-menu-item>

    <el-submenu index="/activity">
      <template slot="title">
        <i class="el-icon-s-cooperation"></i>
        <span slot="title">Activity</span>
      </template>
      <el-menu-item index="/activity"> Activity List</el-menu-item>
      <el-menu-item index="/activity"> Statistic Diagram</el-menu-item>
    </el-submenu>

    <el-submenu index="2">
      <template slot="title">
        <i class="el-icon-search"></i>
        <span slot="title">Clue</span>
      </template>
      <el-menu-item-group>
        <template slot="title">分组一</template>
        <el-menu-item index="2-1">选项1</el-menu-item>
        <el-menu-item index="2-2">选项2</el-menu-item>
      </el-menu-item-group>
    </el-submenu>
    <el-submenu index="3">
      <template slot="title">
        <i class="el-icon-s-custom"></i>
        <span slot="title">Customer</span>
      </template>
      <el-menu-item-group>
        <template slot="title">分组一</template>
        <el-menu-item index="3-1">选项1</el-menu-item>
        <el-menu-item index="3-2">选项2</el-menu-item>
      </el-menu-item-group>
    </el-submenu>
    <el-submenu index="4">
      <template slot="title">
        <i class="el-icon-phone"></i>
        <span slot="title">Contacts</span>
      </template>
      <el-menu-item index="4-1">选项1</el-menu-item>
    </el-submenu>
    <el-submenu index="5">
      <template slot="title">
        <i class="el-icon-s-marketing"></i>
        <span slot="title">Transaction</span>
      </template>
      <el-menu-item index="4-1">选项1</el-menu-item>
    </el-submenu>
  </el-menu>
</template>

<script>
export default {
  name: "Aside",
  props: {
    isCollapse: Boolean,
    logoTextShow: Boolean
  }
}
</script>

<style scoped>

</style>