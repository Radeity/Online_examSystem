<template>
  <div>
    <el-table v-loading="data.loading" :data="data.tableData" @selection-change="data.handleSelectionChange">
      <el-table-column type="selection" />
      <el-table-column
        v-for="(item,index) in data.columnList"
        :key="index"
        :prop="item.prop"
        :label="item.label"
        show-overflow-tooltip
        sortable
      />
      <el-table-column label="操作" width="100">
        <template slot-scope="scope">
          <el-button type="primary" icon="el-icon-info" size="small" @click.native="data.handleDetail(scope.row)">详情</el-button>
          <!--el-dropdown trigger="click" @command="handleCommand">
            <span class="el-dropdown-link">
              下拉菜单
              <i class="el-icon-arrow-down el-icon--right" />
            </span>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item
                v-for="(item,index) in data.menuList"
                :key="index"
                :icon="item.icon"
                :command="{command:item.command,row:scope.row}"
              >{{ item.word }}</el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown-->
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
export default {
  props: {
    data: {
      type: Object,
      required: true
      /**
      tableData: Array,
      columnList: Array,
      menuList: Array,
      loading: Boolean
      handleSelectionChange
      */
    }
  },
  data() {
    return {
    }
  },
  methods: {
    handleCommand(command) {
      command.command(command.row)
    }
  }
}
</script>

<style lang="css">
.el-dropdown-link {
  cursor: pointer;
  color: #409eff;
}
.el-icon-arrow-down {
  font-size: 12px;
}
</style>
