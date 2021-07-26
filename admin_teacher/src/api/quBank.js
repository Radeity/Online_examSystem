import request from '@/utils/request'

export function getQuBankList(params) {
  return request({
    url: '/tea/getbank',
    method: 'get',
    params
  })
}

export function getQuestionOfBank(params) {
  return request({
    url: '/tea/questionintegratedquery',
    method: 'get',
    params
  })
}

export function deleteQuesFromBank(params) {
  return request({
    url: '/tea/delequestion',
    method: 'delete',
    params
  })
}

export function questionAddEdit(data) {
  return request({
    url: '/tea/saveorupdatequestion',
    method: 'post',
    data
  })
}
export function quBankAdd(params) {
  return request({
    url: '/tea/addbank',
    method: 'post',
    params
  })
}

export function quBankEdit(params) {
  return request({
    url: '/tea/updatebank',
    method: 'post',
    params
  })
}

export function deleteQuBank(params) {
  return request({
    url: '/tea/delebank',
    method: 'delete',
    params
  })
}
