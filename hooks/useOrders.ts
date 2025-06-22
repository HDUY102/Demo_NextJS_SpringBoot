import { fetcher } from "@/lib/fetcher"
import useSWR from "swr"

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
    orderHistoryJson: JSON
}

export const useOrders = () =>{
    const {data, error, isLoading, mutate} = useSWR<Orders[]>('/orders',fetcher)
    return{
        orders: data,
        isLoading,
        isError: error,
        mutate
    }
}