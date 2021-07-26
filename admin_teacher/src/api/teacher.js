import request from '@/utils/request'

export function getTeacherList() {
  const role = 'TEA'
  return request({
    url: '/auth/userbyrole',
    method: 'get',
    params: { role: role }
  })
}
export function teacherAddEdit(data) {
  return request({
    url: '/auth/saveorupdateuser',
    method: 'post',
    data
  })
}
export function teacherDelete(params) {
  return request({
    url: '/auth/deleteuser',
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
