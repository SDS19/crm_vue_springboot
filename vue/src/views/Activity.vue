<template>
  <div>
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
      <el-popconfirm
          confirm-button-text='OK'
          cancel-button-text='No, Thanks'
          icon="el-icon-info"
          icon-color="red"
          title="Are you sure to delete this?"
      >
        <el-button @click="delBatch" type="danger" slot="reference" class="ml_5">Delete<i class="el-icon-remove-outline ml_5"></i></el-button>
      </el-popconfirm>
    </div>

    <!-- table -->
    <el-table ref="multipleTable" :data="tableData" @selection-change="handleSelectionChange" border stripe>
      <el-table-column type="selection" width="40"></el-table-column>
      <el-table-column prop="id" v-if="false"></el-table-column>
      <el-table-column prop="name" label="Name" width="120"></el-table-column>
      <el-table-column prop="owner" label="Owner" width="120"></el-table-column>
      <el-table-column prop="startDate" label="Start Date" width="120"></el-table-column>
      <el-table-column prop="endDate" label="End Date" width="120"></el-table-column>
      <el-table-column label="Operation">
        <template slot-scope="scope">
          <el-button @click="edit(scope.row)" class="mr_5"><i class="el-icon-edit"></i></el-button>
          <el-popconfirm
              confirm-button-text='OK'
              cancel-button-text='No, Thanks'
              icon="el-icon-info"
              icon-color="red"
              title="Are you sure to delete this?"
          >
            <el-button @click="del(scope.row.id)" type="danger" slot="reference"><i class="el-icon-delete"></i></el-button>
          </el-popconfirm>
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
        <el-input v-model="activity.id" style="display: none"></el-input>
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
  </div>
</template>

<script>
export default {
  name: "Activity",
  data() {
    return {
      //query field
      name: '',
      owner: '',
      startDate: '',
      endDate: '',

      //table
      multipleSelection: [],//checkbox
      tableData: [],
      total: 0,
      pageNum: 1,
      pageSize: 5,

      //dialog
      title: "",
      dialogVisible: false,
      activity: {},
    }
  },
  created() {
    this.load();
  },
  methods: {
    load(){
      this.request.get("/activity",{
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
    reset(){
      this.name="";
      this.owner="";
      this.startDate="";
      this.endDate="";
      this.load();
    },

    //table
    edit(row){
      this.title = "Update Activity";
      document.getElementById("dialogBtn").innerHTML = "Update";
      this.activity = row;
      this.dialogVisible = true;
    },
    del(id){
      this.request.delete("/activity/"+id).then(res=>{
        if (res) {
          this.$message.success("Delete activity success!");
          this.load();
        } else this.$message.error("Delete activity failed!");
      })
    },
    handleSelectionChange(val) {
      this.multipleSelection = val;
    },
    delBatch(){
      let ids = this.multipleSelection.map(v => v.id); //[1,2,3]
      this.request.post("/activity/batch",ids).then(res=>{
        if (res) {
          this.$message.success("Delete activities success!");
          this.load();
        } else this.$message.error("Delete activities failed!");
      })
    },


    //dialog
    create(){
      this.title = "Create Activity";
      this.activity = {};
      document.getElementById("dialogBtn").innerHTML = "Save";
      this.dialogVisible = true;
    },

    save_update(){
      if (this.title == "Create Activity") {
        this.request.post("/activity",this.activity).then(res=>{
          if (res) {
            this.dialogVisible = false;
            this.$message.success("Create activity success!");
            this.load();
          }
          else this.$message.error("Create activity failed!");
        })
      }else {
        this.request.put("/activity",this.activity).then(res=>{
          if (res) {
            this.dialogVisible = false;
            this.$message.success("Update activity success!");
            this.load();
          }
          else this.$message.error("Update activity failed!");
        })
      }
      this.dialogVisible = false;
    },

    //pagination
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

<style scoped>

</style>