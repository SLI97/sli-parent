import request from '@/utils/request'

export function getUser(params) {
  return request({
    url: '/uaa/user/get',
    method: 'get',
    data: params,
  })
}

export function createUser(params) {
  return request({
    url: '/user/insert',
    method: 'post',
    data: params,
  })
}

export function login(params) {
  return request({
    url: '/uaa/auth/login',
    method: 'post',
    headers: {
      'Authorization': 'Basic YnJvd3Nlcjpicm93c2Vy',
      // 'Content-type': 'application/x-www-form-urlencoded; charset=utf-8',
    },
    params: params,
  })
}

export function SetCok(params) {
  console.log("我执行了")
  return request({
    url: '/uaa/user/zzz',
    method: 'get',
    // headers: {
    //   'Authorization': 'Basic YnJvd3Nlcjpicm93c2Vy',
    //   // 'Content-type': 'application/x-www-form-urlencoded; charset=utf-8',
    // },
    // params: params,
  })
}
