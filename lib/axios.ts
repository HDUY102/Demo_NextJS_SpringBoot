import axios from "axios";


const axiosInstance = axios.create({
  baseURL: process.env.NEXT_PUBLIC_API_BASE_URL,
  headers:{
    'Content-Type' : 'application/json'
  },
  timeout: 5000,
  withCredentials: false
})

export default axiosInstance;

axiosInstance.interceptors.request.use(
  (config) =>{
    const token = localStorage.getItem('access_token')
    if(token){
      config.headers.Authorization = `Bearer ${token}`
    }
    return config
  },
  (error) =>{
    return Promise.reject(error)
  }
)

axiosInstance.interceptors.response.use(
  (response) =>{
    return response
  },
  (error) =>{
    if(error.response){
      const {status} = error.response
      const handler = errorHandlers[status]
      if(handler){
        handler(error)
      }
    }else if(error.response){
      console.log('Không phản hồi từ server')
    }else{console.log('Lỗi axios request')}
    return Promise.reject(error)
  }
)

type ErrorHandler = (error:any) => void

const errorHandlers: Record<number, ErrorHandler> = {
  400:()=>{
    console.log('400')
  },
  401:()=>{
    console.log('401')
  },
  403:()=>{
    console.log('403')
  },
  404:()=>{
    console.log('404')
  },
  422:()=>{
    console.log('422')
  },
  500:()=>{
    console.log('500')
  },
  503:()=>{
    console.log('503')
  },
}
