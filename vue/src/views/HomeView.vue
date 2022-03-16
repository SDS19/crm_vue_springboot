<template>
  <div style="min-height: 100%">
    <el-container style="min-height: 100vh">

      <!-- menu -->
      <el-aside :width="sideWidth+'px'" style="background-color: rgb(238, 241, 246); min-height: 100vh;">
        <Aside :isCollapse="isCollapse"  :logoTextShow="logoTextShow"/>
      </el-aside>

      <el-container>

        <el-header style="border-bottom: 1px solid rgb(238, 241, 246);">
          <Header :collapseBtnClass="collapseBtnClass" :collapse="isCollapse"/>
        </el-header>

        <el-main>
          <!-- 当前页面的子路由会在<router-view>中展示 -->
          <router-view />
        </el-main>

      </el-container>

    </el-container>
  </div>
</template>

<script>
// @ is an alias to /src
import Aside from "@/components/Aside";
import Header from "@/components/Header";

export default {
  name: 'HomeView',
  components: {Header, Aside},
  data() {
    return {
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
    },
  }
}
</script>
