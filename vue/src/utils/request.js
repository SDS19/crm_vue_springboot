import axios from 'axios'

const request = axios.create({
    baseURL: 'http://localhost:9090',
    timeout: 5000
})

// request interceptor
// 可以自请求发送前对请求做一些处理
// 比如统一加token，对请求参数统一加密
request.interceptors.request.use(config => {
    config.headers['Content-Type'] = 'application/json;charset=utf-8';

    // config.headers['token'] = user.token;  // 设置request header
    return config
}, error => {
    return Promise.reject(error)
});

// response interceptor
// 可以在接口响应后统一处理结果
request.interceptors.response.use(
    response => {
        let res = response.data;
        // if return file
        if (response.config.responseType === 'blob') {
            return res
        }
        // if return string, then transform JSON
        if (typeof res === 'string') {
            res = res ? JSON.parse(res) : res
        }
        return res;
    },
    error => {
        console.log('err' + error) // for debug
        return Promise.reject(error)
    }
)


export default request

