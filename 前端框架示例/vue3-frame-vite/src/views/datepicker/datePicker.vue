<template>
  <div class="demo-datetime-picker">
    <div class="block">
      <el-date-picker
        v-model="value1"
        type="datetime"
        placeholder="Pick a Date"
        format="YYYY-MM-DD HH:mm:ss"
        date-format="MMM DD, YYYY"
        time-format="HH:mm"
      />
    </div>
    <div class="line"/>
    <div class="block">
      <el-date-picker
        v-model="value2"
        type="daterange"
        start-placeholder="Start date"
        end-placeholder="End date"
        format="YYYY-MM-DD"
        date-format="YYYY/MM/DD ddd"
        :disabled-date="disabledDate"
      />
    </div>
  </div>
</template>

<script lang="ts" setup>
import {ref} from 'vue'

const value1 = ref('')
const value2 = ref('')
const disabledDate = (date) => {
  const today = new Date();
  const threeMonthsBefore = new Date();
  const threeMonthsAfter = new Date();

  threeMonthsBefore.setMonth(today.getMonth() - 3); // 3 个月前
  threeMonthsAfter.setMonth(today.getMonth() + 3); // 3 个月后

  return date < threeMonthsBefore || date > threeMonthsAfter;
}
</script>
<style scoped>
.demo-datetime-picker {
  display: flex;
  width: 100%;
  padding: 0;
  flex-wrap: wrap;
  justify-content: space-around;
  align-items: stretch;
}

.demo-datetime-picker .block {
  padding: 30px 0;
  text-align: center;
}

.line {
  width: 1px;
  background-color: var(--el-border-color);
}
</style>
