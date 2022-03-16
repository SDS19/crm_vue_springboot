<template>
  <div style="font-size: 12px;line-height: 60px; display: flex">

    <div style="flex: 1;font-size: 25px">

      <span :class="collapseBtnClass" style="cursor: pointer; " @click="collapse"></span>

      <el-breadcrumb separator="/" style="display: inline-block; margin-left: 10px">
        <el-breadcrumb-item :to="{ path: '/' }" >homepage</el-breadcrumb-item>
      </el-breadcrumb>

    </div>

    <el-dropdown style="width: 70px; cursor: pointer">
      <span>王小虎</span><i class="el-icon-arrow-down" style="margin-left: 5px"></i>
      <el-dropdown-menu slot="dropdown">
        <el-dropdown-item>Profil</el-dropdown-item>
        <el-dropdown-item>新增</el-dropdown-item>
        <el-dropdown-item>Sign out</el-dropdown-item>
      </el-dropdown-menu>
    </el-dropdown>

  </div>
</template>

<script>
export default {
  name: "Header",
  props: {
    collapseBtnClass: String,
    collapse: Boolean
  },
  data(){
    return {
      paths: []
    }
  }
}
</script>

<style scoped>

</style>