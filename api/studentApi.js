import request from '@/utils/request'

export function studentLogin(id,password) {
  return request({
    url: '/login/StudentLogin/'+id+'/'+password,
    method: 'get'
  })
}

export function studentGetInfo(id , token) {
  return request({
    url: '/student/findStudent/' + id,
    method: 'get',
    headers:{
      "token" : token
    }
  })
}