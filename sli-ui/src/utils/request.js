import axios from 'axios'
import { Notification, MessageBox, Message } from 'element-ui'
// import store from '../store'
import { getToken } from '@/utils/auth'
import errorCode from '@/utils/errorCode'

// 创建axios实例
const service = axios.create({
  // baseURL: 'http://sli.com', // api的base_url
  // baseURL: 'http://192.168.96.160:9999', // api的base_url
  baseURL: 'http://localhost:4000', // api的base_url
  timeout: 15000, // 请求超时时间
  // withCredentials: true // 跨域请求，允许保存cookie
})

// request拦截器
service.interceptors.request.use(
  config => {
    if(getToken()){
      config.headers['Authorization'] = 'Bearer ' + getToken()
    }
    if (!config.headers['Content-type']) { // 指定content-type 则跳过
      config.headers['Content-Type'] = 'application/json; charset=utf-8'
    }

    return config
  },
  error => {
    // Do something with request error
    console.log(error) // for debug
    Promise.reject(error)
  }
)

// 响应拦截器
service.interceptors.response.use(res => {
  const code = res.data.code || 200;
  const message = errorCode[code] || res.data.msg || errorCode['default']
  if (code === 401) {
    MessageBox.confirm(
      '登录状态已过期，您可以继续留在该页面，或者重新登录',
      '系统提示',
      {
        confirmButtonText: '重新登录',
        cancelButtonText: '取消',
        type: 'warning'
      }
    ).then(() => {
      store.dispatch('LogOut').then(() => {
        location.reload() // 为了重新实例化vue-router对象 避免bug
      })
    })
  } else if (code === 500) {
    Message({
      message: message,
      type: 'error'
    })
    return Promise.reject(new Error(message))
  } else if (code !== 200) {
    Notification.error({
      title: message
    })
    return Promise.reject('error')
  } else {
    return res.data
  }
},
error => {
  console.log('err' + error)
  Message({
    message: error.message,
    type: 'error',
    duration: 5 * 1000
  })
  return Promise.reject(error)
}
)

export default service
