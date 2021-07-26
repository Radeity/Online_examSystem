import request from '@/utils/request'

export function getScoreList(params) {
  return request({
    url: '/tea/getallgrade',
    method: 'get',
    params
  })
}

export function getScoreDetail(params) {
  return request({
    url: '/tea/paperdetailfortea',
    method: 'get',
    params
  })
}

export function getTeacherList(params) {
  return request({
    url: '/tea/teacherquery',
    method: 'get',
    params
  })
}

export function scoreExport(params) {
  return request({
    url: '/tea/excel',
    method: 'get',
    responseType: 'blob',
    params
  })
}
