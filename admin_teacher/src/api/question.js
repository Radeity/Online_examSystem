import request from '@/utils/request'

export function getQuestionList(params) {
  return request({
    url: '/tea/getquefrompaper',
    method: 'get',
    params
  })
}
export function getPaperByCourse(params) {
  return request({
    url: '/tea/getpaper',
    method: 'get',
    params
  })
}
export function paperAddEdit(data) {
  return request({
    url: '/tea/saveorupdatepaper',
    method: 'post',
    data
  })
}
export function paperDelete(params) {
  return request({
    url: '/tea/delpaper',
    method: 'DELETE',
    params
  })
}

export function studentChangeStatus() {
  return request()
}

export function studentChangePassword() {
  return request()
}
