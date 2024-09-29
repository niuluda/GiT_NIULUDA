import axios from 'axios'


// 实例
const service = axios.create({
  baseURL: import.meta.env.VITE_BASE_URL,
  timeout: 20000
})

async function POST(url, params, headers, config) {


  config.headers === undefined ? (config.headers = {}) : config.headers
  headers = Object.assign(config.headers, headers)

  return service
      .post(url, params, {
        headers,
        ...config
      })
      .then(res => {
        /* 此处作用很大，可以扩展很多功能。
         比如对接多个后台，数据结构不一致，可做接口适配器
         也可对返回日期/金额/数字等统一做集中处理*/
        return res
      })
}
export const httpMethod={
  httpPost: function (url, params, config = {}) {
    return POST(
        url,
        params,
        {'content-type': 'application/x-www-form-urlencoded'},
        config
    )
  },
}

export default service
