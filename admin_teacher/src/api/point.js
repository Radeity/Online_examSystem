import request from '@/utils/request'

export function getPointList(params) {
  return request({
    url: '/tea/getpointbysubjectid',
    method: 'get',
    params
  })
}
