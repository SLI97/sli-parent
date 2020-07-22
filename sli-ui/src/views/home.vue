<template>
  <div style="margin:30px 40px">
    <el-button type="primary" @click="getData">查询</el-button>
    <el-button type="primary" @click="dialogVisible = true">创建</el-button>
    <el-table :data="tableData" border style="width: 100%" v-loading="loading">
      <el-table-column prop="id" label="id"></el-table-column>
      <el-table-column prop="name" label="姓名"></el-table-column>
      <el-table-column prop="age" label="年龄" width="120"></el-table-column>
      <el-table-column prop="email" label="邮箱"></el-table-column>
      <el-table-column fixed="right" label="操作">
        <template slot-scope="scope">
          <el-button size="small">编辑</el-button>
          <el-button @click="handleClick(scope.row)" type="danger" size="small">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <el-dialog title="创建用户" :visible.sync="dialogVisible" width="30%">
      <el-form ref="form" :model="form" label-width="80px">
        <el-form-item label="姓名">
          <el-input v-model="form.name"></el-input>
        </el-form-item>
        <el-form-item label="年龄">
          <el-input v-model="form.age"></el-input>
        </el-form-item>
        <el-form-item label="邮箱">
          <el-input v-model="form.email"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="create()">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import { getUser, createUser } from "@/api/index";
// import {asd,obj1,obj2} from "@/test/indexa";

export default {
  name: "home",
  data() {
    return {
      tableData: [],
      loading: false,
      dialogVisible: false,
      form: {
        name: "",
        age: "",
        email: ""
      }
    };
  },
  mounted() {
    this.getData();
    // console.log(process)
  },
  methods: {
    getData() {
      // this.loading = true;
      getUser({HAHA:123})
        .then(res => {
          console.log(res);
          // this.loading = false;
          // this.tableData = res.data;
        })
        .catch(function(error) {
          console.log(error);
        });
    },
    create() {
      console.log(this.form)
      createUser(this.form).then(res => {
        console.log(res);
        if (res.code === 200) {
          this.$message({
            message: "创建成功",
            type: "success"
          });
        }
      });
    },
    handleClick() {}
  }
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
</style>
