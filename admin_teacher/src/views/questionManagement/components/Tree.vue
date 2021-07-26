<template>
  <el-card>
    <div slot="header">
      <span>题库分类</span>
      <!--el-button style="float: right; padding: 3px 0" type="text">删除选中</el-button-->
    </div>
    <el-input v-model="filterText" placeholder="请输入内容" />
    <div class="block">
      <el-tree
        ref="tree"
        :data="data"
        node-key="id"
        :filter-node-method="filterNode"
        @node-click="nodeClick"
      />
    </div>
  </el-card>
</template>

<script>
export default {
  props: {
    data: {
      type: Array,
      required: true
    },
    nodeClick: {
      type: Function,
      required: true
    }
  },
  data() {
    return {
      filterText: ''
    }
  },
  watch: {
    filterText(val) {
      this.$refs.tree.filter(val)
    }
  },
  methods: {
    filterNode(value, data) {
      if (!value) return true
      return data.label.indexOf(value) !== -1
    }
  }
}
</script>
<style>
  .custom-tree-node {
    flex: 1;
    display: flex;
    align-items: center;
    justify-content: space-between;
    font-size: 14px;
    padding-right: 8px;
  }
</style>
