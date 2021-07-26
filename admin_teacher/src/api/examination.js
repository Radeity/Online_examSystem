import request from '@/utils/request'

export function getExaminationList(params) {
  console.log('params', params)
  return request({
    url: '/tea/getexamlist',
    method: 'get',
    params
  })
}

export function examinationAddEdit(data) {
  return request({
    url: '/tea/saveorupdateExam',
    method: 'post',
    data
  })
}

export function examinationDelete(params) {
  return request({
    url: '/tea/delExam',
    method: 'delete',
    params
  })
}

export function examinationPublish(params) {
  return request({
    url: '/tea/publishexam',
    method: 'post',
    params
  })
}

export function examintionUnpublish(params) {
  return request({
    url: '/tea/unpublishexam',
    method: 'post',
    params
  })
}
