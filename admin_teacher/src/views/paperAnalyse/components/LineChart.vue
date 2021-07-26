<template>
  <div class="line_chart" :style="{width: width,height: height}" />
</template>

<script>
import echarts from 'echarts'

export default {
  name: 'LineChart',
  props: {
    xAxisData: {
      required: true,
      type: Array
    },
    data: Array,
    width: {
      type: String,
      default: '350px'
    },
    height: {
      type: String,
      default: '250px'
    }
    // color: String
  },
  data() {
    return {
      chart: null
    }
  },
  watch: {
    xAxisData() {
      this.drawLine()
    }
  },
  mounted() {
    this.drawLine()
  },
  methods: {
    drawLine() {
      // echarts.init(this.$el, 'macarons')
      this.chart = echarts.init(this.$el)
      this.chart.setOption({
        title: {
          text: '知识点正确率',
          left: 'center',
          textStyle: {
            fontSize: 16,
            color: 'black'
          }
        },
        xAxis: [
          {
            splitLine: {
              show: false
            },
            type: 'category',
            data: this.xAxisData
          }
        ],
        yAxis: [
          {
            splitLine: {
              show: false
            },
            type: 'value',
            splitNumber: 10
          }
        ],
        tooltip: {
          trigger: 'axis'
        },

        series: [{
          data: this.data,
          type: 'line',
          smooth: true,
          symbol: 'circle',
          symbolSize: 8,
          itemStyle: {
            normal: {
              color: this.color,
              borderColor: this.color
            }
          }
        }]
      })
    }
  }
}
</script>

<style scoped>

</style>
