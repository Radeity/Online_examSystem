<template>
  <div>
    <el-table v-loading="data.loading" :data="data.tableData" @selection-change="data.handleSelectionChange">
      <el-table-column type="selection" />
      <el-table-column
        v-for="(item,index) in columnList"
        :key="index"
        :prop="item.prop"
        :label="item.label"
        sortable
      />
      <el-table-column label="状态" sortable>
        <template slot-scope="scope">
          <el-switch
            v-model="scope.row.state"
            active-color="#13ce66"
            inactive-color="#ff4949"
            active-value="启用"
            inactive-value="禁用"
            active-text="启用"
            inactive-text="禁用"
            @change="handleChange(scope.row)"
          />
        </template>
      </el-table-column>
      <el-table-column fixed="right" label="操作" width="100">
        <template slot-scope="scope">
          <el-dropdown @command="handleCommand">
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
          </el-dropdown>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
import { stateForbid, statePermit } from '@/api/student'
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
      columnList: [
        {
          prop: 'id',
          label: 'Id'
        },
        {
          prop: 'teacherName',
          label: '教师姓名'
        },
        {
          prop: 'teacherNumber',
          label: '工号'
        },
        {
          prop: 'college',
          label: '学院'
        },
        {
          prop: 'gender',
          label: '性别'
        }
      ]
    }
  },
  methods: {
    handleCommand(command) {
      command.command(command.row)
    },
    handleChange(row) {
      console.log(row)
      const { state, id } = row
      if (state === '启用') {
        console.log('qoyong')
        statePermit({ id: id }).then(response => {
          console.log(id)
        })
      } else if (state === '禁用') {
        stateForbid({ id: id }).then(response => {
          console.log(id)
        })
      }
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
