import useSWR from 'swr';
import { fetcher } from '@/lib/fetcher';

export interface Category {
  id: number;
  name: string;
}

export const useCategories = () => {
  const { data, error, isLoading, mutate } = useSWR<Category[]>('/categories', fetcher);

  return {
    categories: data,
    isLoading,
    isError: error,
    mutate,
  };
};
