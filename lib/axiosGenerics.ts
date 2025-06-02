import axiosInstance from "./axios"


export function axiosGenerics(baseUrl: string){
    return{
        create<TPayload, TResponse>(payload: TPayload){
            return axiosInstance.post<TResponse>(baseUrl, payload).then(res=>res.data)
        },
        update<TPayload, TResponse>(id: number | string, payload: TPayload){
            return axiosInstance.put<TResponse>(`${baseUrl}/${id}`,payload).then(res=>res.data)
        },
        delete<TResponse>(id: number|string){
            return axiosInstance.delete<TResponse>(`${baseUrl}/${id}`).then(res=>res.data)
        }
    }
}