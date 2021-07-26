<template>
  <div class="pie_chart" :style="{height:height,width:width}" />
</template>

<script>
import echarts from 'echarts'

export default {
  props: {
    data: {
      type: Array,
      default: function() {
        return []
      }
    },
    width: {
      type: String,
      default: '250px'
    },
    height: {
      type: String,
      default: '250px'
    }
  },
  data() {
    return {
      chart: null
    }
  },
  watch: {
    data: {
      handler: function(newval, oldval) {
        this.initChart(newval)
      }
    }
  },
  mounted() {
    this.$nextTick(() => {
      this.initChart(this.data)
    })
  },
  beforeDestroy() {
    if (!this.chart) {
      return
    }
    this.chart.dispose()
    this.chart = null
  },
  methods: {
    initChart(data) {
      this.chart = echarts.init(this.$el)
      this.chart.setOption({
        title: {
          text: '知识点分布图',
          left: 'center',
          textStyle: {
            fontSize: 15,
            color: 'black'
          }
        },
        tooltip: {
          trigger: 'item',
          formatter: '{a} <br/>{b} : {c} ({d}%)'
        },
        series: [
          {
            name: '题目个数:',
            type: 'pie',
            label: {
              normal: {
                position: 'inside'
              }
            },
            radius: '60%',
            center: ['50%', '50%'],
            data: data
          }
        ]
      })
    }
  }
}
</script>
