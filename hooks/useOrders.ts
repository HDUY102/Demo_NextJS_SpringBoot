import axiosInstance from "@/lib/axios";
import { fetcher } from "@/lib/fetcher"
import useSWR from "swr"

const postFetcher = async (url: string, body: any) => {
    const response = await axiosInstance.post(url, body);
    return response.data;
};

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

export const useOrders = (keyword: string) =>{
    const {data, error, isLoading, mutate} = useSWR<Orders[]>(
        ['/orders', keyword],
        ([url, keyword]) => postFetcher(url, { keyword }))
    // const {data, error, isLoading, mutate} = useSWR<Orders[]>('/orders',fetcher)
    return{
        orders: data,
        isLoading,
        isError: error,
        mutate
    }
}