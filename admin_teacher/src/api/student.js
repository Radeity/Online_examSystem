import request from '@/utils/request'

export function getStudentList() {
  const role = 'STU'
  return request({
    url: '/auth/userbyrole',
    method: 'get',
    params: { role: role }
  })
}
export function studentAddEdit(data) {
  return request({
    url: '/auth/saveorupdateuser',
    method: 'post',
    data
  })
}
export function studentDelete(params) {
  return request({
    url: '/auth/deleteuser',
    method: 'delete',
    params
  })
}

export function stateForbid(data) {
  return request({
    url: '/auth/stateforbid',
    method: 'post',
    data
  })
}

export function statePermit(data) {
  return request({
    url: '/auth/statepermit',
    method: 'post',
    data
  })
}

