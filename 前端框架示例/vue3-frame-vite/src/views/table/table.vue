<template>
  <el-table :data="tableData" style="width: 100%" row-key="id">
    <!-- 自定义复选框列的表头 -->
    <el-table-column width="55">
      <template #header>
        <!-- 为复选框表头添加工具提示 -->
        <el-tooltip :content="'Select all rows'" placement="bottom">
          <span>
            <el-checkbox :indeterminate="isIndeterminate" :checked="isChecked" @change="toggleAll" />
          </span>
        </el-tooltip>
      </template>
      <template #default="{ row }">
          <span>
            <el-checkbox v-model="selectedRows" :label="row.id" />
          </span>
      </template>
    </el-table-column>

    <!-- 其他列 -->
    <el-table-column label="Name" prop="name" />
    <el-table-column label="Address" prop="address" />

  </el-table>
  <date-picker></date-picker>
  <el-select placeholder="请选择月份"></el-select>
</template>

<script lang="ts" setup>
import { ref } from 'vue'
import DatePicker from "../datepicker/datePicker.vue";

interface User {
  id: number
  name: string
  address: string
}

const tableData: User[] = [
  { id: 1, name: 'Tom', address: 'No. 189, Grove St, Los Angeles' },
  { id: 2, name: 'Jerry', address: 'No. 190, Grove St, Los Angeles' },
  { id: 3, name: 'Mike', address: 'No. 191, Grove St, Los Angeles' },
  { id: 4, name: 'Anna', address: 'No. 192, Grove St, Los Angeles' }
]

const selectedRows = ref<number[]>([])
const isChecked = ref(false)
const isIndeterminate = ref(false)

// 处理全选/取消全选
const toggleAll = () => {
  if (isChecked.value) {
    selectedRows.value = tableData.map(row => row.id)
  } else {
    selectedRows.value = []
  }
}
</script>
