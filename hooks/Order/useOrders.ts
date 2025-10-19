import axiosInstance from "@/lib/axios";
// import { fetcher } from "@/lib/fetcher"
import useSWR from "swr"

// const postFetcher = async (url: string, body: any) => {
//     const response = await axiosInstance.post(url, body);
//     return response.data;
// };

export interface DetailOrderDTO {
  quantity: number;
  flowerTypeName: string;
  saleUnitName: string;
}

export interface Orders{
    id: string,
    customerName: string,
    dateOrder: Date,
    totalAmount: string,
    isPaid: boolean,
    details: DetailOrderDTO[];
    orderHistoryJson: OrderStatusHistory[] | string;
}

export interface OrderStatusHistory {
  newStatusId: number;
  note?: string;
  updatedAt?: string;
}

export interface Page<T> {
    content: T[];
    totalPages: number;
    totalElements: number;
    size: number;
    number: number;
}

// export const useOrders = (keyword: string) =>{
//     const {data, error, isLoading, mutate} = useSWR<Orders[]>(
//         ['/orders', keyword],
//         ([url, keyword]) => postFetcher(url, { keyword }))
//     // const {data, error, isLoading, mutate} = useSWR<Orders[]>('/orders',fetcher)
//     return{
//         orders: data,
//         isLoading,
//         isError: error,
//         mutate
//     }
// }

const getFetcher = async (url: string) => {
    const response = await axiosInstance.get(url);
    return response.data;
};
export const useOrders = (
    keyword: string,
    page: number,
    size: number,
    sortBy: string,
    sortDir: 'ASC' | 'DESC'
) => {
    const params = new URLSearchParams();
    params.append('page', page.toString());
    params.append('size', size.toString());
    params.append('sortBy', sortBy);
    params.append('sortDir', sortDir);
    if (keyword) {
        params.append('keyword', keyword);
    }
    
    const url = `/orders/pagination?${params.toString()}`;
    
    const { data, error, isLoading, mutate } = useSWR<Page<Orders>>(
        url,
        getFetcher
    );
    
    return {
        orders: data?.content,
        totalPages: data?.totalPages,
        isLoading,
        isError: error,
        mutate,
    };
};