import request from '@/utils/request'

export function getCourseList() {
  return request({
    url: '/tea/getsubject',
    method: 'get'
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

export function getPaperDetail(params) {
  return request({
    url: '/tea/paperdetailfortea',
    method: 'get',
    params
  })
}
export function addQuestionToPaper(params) {
  return request({
    url: '/tea/addquetopaper',
    method: 'post',
    params
  })
}

export function deleteQuestionFromPaper(params) {
  return request({
    url: '/tea/delequefrompaper',
    method: 'delete',
    params
  })
}

export function studentChangeStatus() {
  return request()
}

export function studentChangePassword() {
  return request()
}
