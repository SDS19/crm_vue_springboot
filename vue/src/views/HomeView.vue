<template>
  <div style="min-height: 100%">
    <el-container style="min-height: 100vh">

      <!-- menu -->
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
              <i class="el-icon-s-cooperation"></i>
              <span slot="title">Activity</span>
            </template>
            <el-menu-item class="el-icon-pie-chart" index="1-1"> Statistic Diagram</el-menu-item>
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

          <!-- query field -->
          <div style="margin: 10px 0">
            <el-input v-model="name" placeholder="name" suffix-icon="el-icon-search" style="width: 200px;" class="mr_5"></el-input>
            <el-input v-model="owner" placeholder="owner" suffix-icon="el-icon-user" style="width: 200px;" class="mr_5"></el-input>
            <el-date-picker v-model="startDate" type="date" placeholder="start date" value-format="yyyy-MM-dd" class="mr_5"></el-date-picker>
            <el-date-picker v-model="endDate" type="date" placeholder="end date" value-format="yyyy-MM-dd" class="mr_5"></el-date-picker>
            <el-button type="primary" @click="load">Query</el-button>
            <el-button @click="reset">Reset</el-button>
          </div>

          <div style="margin: 10px 0">
            <el-button type="primary" @click="create">Create<i class="el-icon-circle-plus-outline ml_5"></i></el-button>
            <el-button type="danger">Delete<i class="el-icon-remove-outline ml_5"></i></el-button>
          </div>

          <!-- table -->
          <el-table ref="multipleTable" :data="tableData" @selection-change="handleSelectionChange" border stripe>
            <el-table-column type="selection" width="40"></el-table-column>
            <el-table-column v-if="show" prop="id"></el-table-column>
            <el-table-column prop="name" label="Name" width="100"></el-table-column>
            <el-table-column prop="owner" label="Owner" width="100"></el-table-column>
            <el-table-column prop="startDate" label="Start Date" width="120"></el-table-column>
            <el-table-column prop="endDate" label="End Date" width="120"></el-table-column>
            <el-table-column label="Operation">
              <template slot-scope="scope">
                <el-button @click="edit"><i class="el-icon-edit"></i></el-button>
                <el-button type="danger"><i class="el-icon-delete"></i></el-button>
              </template>
            </el-table-column>
          </el-table>

          <!-- pagination -->
          <div style="padding: 10px 0">
            <el-pagination
                @size-change="handleSizeChange"
                @current-change="handleCurrentChange"
                :current-page="pageNum"
                :page-sizes="[5, 10, 15, 20]"
                :page-size="pageSize"
                layout="total, sizes, prev, pager, next, jumper"
                :total="total">
            </el-pagination>
          </div>

          <!-- dialog -->
          <el-dialog :title="title" :visible.sync="dialogVisible">
            <el-form :model="activity" label-width="100px">
              <el-form-item label="Owner">
                <el-select v-model="activity.owner" placeholder="Please select owner">
                  <el-option label="Zone No.1" value="shanghai"></el-option>
                </el-select>
              </el-form-item>
              <el-form-item label="Name">
                <el-input v-model="activity.name"></el-input>
              </el-form-item>
              <el-form-item label="Start Date">
                <el-date-picker v-model="activity.startDate" type="date" placeholder="start date" value-format="yyyy-MM-dd" class="mr_5"></el-date-picker>
              </el-form-item>
              <el-form-item label="End Date">
                <el-date-picker v-model="activity.endDate" type="date" placeholder="end date" value-format="yyyy-MM-dd" class="mr_5"></el-date-picker>
              </el-form-item>
              <el-form-item label="Cost">
                <el-input v-model="activity.cost"></el-input>
              </el-form-item>
              <el-form-item label="Description">
                <el-input type="textarea" v-model="activity.description"></el-input>
              </el-form-item>

            </el-form>
            <span slot="footer" class="dialog-footer">
              <el-button @click="dialogVisible = false">Cancel</el-button>
              <el-button id="dialogBtn" type="primary" @click="save_update"></el-button>
            </span>
          </el-dialog>

        </el-main>

      </el-container>

    </el-container>
  </div>
</template>

<script>
// @ is an alias to /src
import HelloWorld from '@/components/HelloWorld.vue'
import request from "@/utils/request";

export default {
  name: 'HomeView',
  data() {
    return {
      //query field
      name: '',
      owner: '',
      startDate: '',
      endDate: '',

      //table
      show: false,
      tableData: [],
      total: 0,
      pageNum: 1,
      pageSize: 5,
      multipleSelection: [],//checkbox

      //dialog
      title: "",
      dialogVisible: false,
      activity: {
        owner: '',
        name: '',
        startDate: '',
        endDate: '',
        cost: '',
        description: ''
      },

      collapseBtnClass: "el-icon-s-fold",
      isCollapse: false,
      sideWidth: 200,
      logoTextShow: true
    }
  },
  created() {
    this.load();
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
    handleSelectionChange(val) {
      this.multipleSelection = val;
    },
    reset(){
      this.name="";
      this.owner="";
      this.startDate="";
      this.endDate="";
    },

    create(){
      this.title = "Create Activity";
      this.activity = {};
      document.getElementById("dialogBtn").innerHTML = "Save";
      this.dialogVisible = true;
    },
    edit(){
      alert(this.id);
      this.title = "Update Activity";
      this.activity = {};
      document.getElementById("dialogBtn").innerHTML = "Update";
      this.dialogVisible = true;
    },
    save_update(){
      alert("hello");
      if (this.title == "Create Activity") {
        request.post("http://localhost:9090/activity",this.activity).then(res=>{
          if (res) this.$message.success("Create activity success!");
          else this.$message.error("Create activity failed!");
        })
      }else {
        request.put("http://localhost:9090/activity",this.activity).then(res=>{
          if (res) this.$message.success("Update activity success!");
          else this.$message.error("Update activity failed!");
        })
      }
      this.dialogFormVisible = false;
    },

    load(){
      request.get("http://localhost:9090/activity",{
        params: {
          pageNo: this.pageNum,
          pageSize: this.pageSize,
          name: this.name,
          owner: this.owner,
          startDate: this.startDate,
          endDate: this.endDate
        }
      }).then(res=>{
        this.tableData = res.list;
        this.total = res.total;
      });
    },
    handleSizeChange(pageSize){
      this.pageSize = pageSize;
      this.load();
    },
    handleCurrentChange(pageNum){
      this.pageNum = pageNum;
      this.load();
    }
  }
}
</script>
