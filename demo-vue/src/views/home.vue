<template>
  <el-row :gutter="20" class="allContent">
    <el-col :span="6"><div class="grid-content bg-purple">
      创建投票
    </div></el-col>
    <el-col :span="6"><div class="grid-content bg-purple">
      关闭投票
    </div></el-col>
    <el-col :span="6"><div class="grid-content bg-purple">
      加入投票
    </div></el-col>
    <el-col :span="6"><div class="grid-content bg-purple">
      查看投票
    </div></el-col>
  </el-row>
</template>

<script>
export default {
  name: "Home",
  data() {
    return {
    };
  },
};
</script>

<style>
  .allContent {
    width: 90%;
    margin: 0 auto;
  }
  .el-row {
    margin-bottom: 20px;
  }
  .el-col {
    border-radius: 4px;
  }
  .bg-purple-dark {
    background: #99a9bf;
  }
  .bg-purple {
    background: #d3dce6;
  }
  .bg-purple-light {
    background: #e5e9f2;
  }
  .grid-content {
    border-radius: 4px;
    min-height: 36px;
    width: 200px;
  }
  .row-bg {
    padding: 10px 0;
    background-color: #f9fafc;
  }
</style>
