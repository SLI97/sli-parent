import axios from 'axios'
// import { Message } from 'element-ui'
// import store from '../store'
import { getToken } from '@/utils/auth'

// 创建axios实例
const service = axios.create({
  // baseURL: 'http://sli.com', // api的base_url
  // baseURL: 'http://192.168.96.160:9999', // api的base_url
  baseURL: 'http://localhost:9999', // api的base_url
  timeout: 15000, // 请求超时时间
  withCredentials: true // 跨域请求，允许保存cookie
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

// respone拦截器
service.interceptors.response.use(
  response => {
    /**
     * success为false 表示错误，code 错误码确定错误信息
     */
    const res = response.data
    if (!res.code === 200) {
      // message(res.msg, 'error')
      return Promise.reject('error')
    } else {
      return response.data
    }
  },
  error => {
    return Promise.reject(error)
  }
)

export default service
