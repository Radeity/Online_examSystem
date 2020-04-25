/**
 * 日期格式化
 * @param date
 * @param format
 * @returns {*}
 */
export function fmtDate (date, format) {
    return formatDate(new Date(date), format)
}

/**
 * 考试类型
 * @param type
 * @returns {*}
 */
export function examTypeFilter (type) {
    const examType = {
        0: '数学',
        1: '语文'
    }
    return examType[type]
}

  /**
   * 考试类型
   * @param type
   * @returns {*}
   */
  export function opTypeFilter (type) {
    const opType = {
        0: '参加考试',
        1: '成绩查询',
        2: '不可考试'
    }
    return opType[type]
}

  /**
   * 查看错题按钮颜色
 * @param status, 自动传入
 * @param expectStatus
 * @returns {string}
   */
  export function viewquestionTypeFilter (status, expectStatus) {
    return status === expectStatus ? 'success' : 'danger'
}
  /**
   * 考试类型
   * @param type
   * @returns {*}
   */
  export function viewquestionFilter (type) {
    const opType = {
        0: '仅查看错题',
        1: '查看所有题目'
    }
    return opType[type]
}

/**
 * 考试提交状态
 * @param type
 * @returns {*}
 */
export function submitStatusFilter (type) {
    const typeMap = {
        0: '可参加',
        1: '已结束',
        2: '已过期'
    }
    return typeMap[type]
}


/**
 * success状态
 * @param status, 自动传入
 * @param expectStatus
 * @returns {string}
 */
export function simpleTagStatusFilter (status, expectStatus) {
    return status === expectStatus ? 'warning' : 'success'
}


/*
/**
 * 日期格式化
 * @param date
 * @param format
 * @returns {*}
 *
export function fmtDate (date, format) {
    if (!isNotEmpty(date)) {
      return ''
    }
    return formatDate(new Date(date), format)
  }*/
  

  /**
   * 格式化时间戳
   * @param date
   * @param fmt
   * @returns {*}
   */
  export const formatDate = (date, fmt) => { 
    if (/(y+)/.test(fmt)) {
      fmt = fmt.replace(RegExp.$1, (date.getFullYear() + '').substr(4 - RegExp.$1.length))
    }
    let o = {
      'M+': date.getMonth() + 1,
      'd+': date.getDate(),
      'h+': date.getHours(),
      'm+': date.getMinutes(),
      's+': date.getSeconds()
    }
    for (let k in o) {
      if (new RegExp(`(${k})`).test(fmt)) {
        let str = o[k] + ''
        fmt = fmt.replace(RegExp.$1, (RegExp.$1.length === 1) ? str : padLeftZero(str))
      }
    }
    return fmt
  }


export const padLeftZero = (str) => {
    return ('00' + str).substr(str.length)
  }
  