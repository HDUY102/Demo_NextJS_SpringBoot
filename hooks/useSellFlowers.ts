import { fetcher } from "@/lib/fetcher";
import useSWR from "swr";

export interface SellFlowers{
    idFlower: number;
    nameFlowers: string;
    incomeInMonth: number;
    month: number
}

export const  useSellFlowers = ()=> {
    const {data,isLoading, error, mutate} = useSWR<SellFlowers[]>('/sellflowers',fetcher)
    
    return{
        sellFlowers: data,
        isLoading,
        isError: error,
        mutate
    }
}