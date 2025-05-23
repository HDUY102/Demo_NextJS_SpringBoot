import useSWR from 'swr';
import { fetcher } from '@/lib/fetcher';

// Các trường: id, name,... phải trùng tên với field trong DB thì mới show ra d.lieu
export interface Category {
  id: number;
  name: string;
}

export const useCategories = () => {
  const { data, error, isLoading, mutate } = useSWR<Category[]>('/categories', fetcher);

  return {
    categories: data,//data: là dữ liệu đã fetch.
    isLoading,
    isError: error,
    mutate, //hàm giúp bạn cập nhật hoặc làm mới dữ liệu. ≈ refetch: mutate (load lại dữ liệu)
  };
};
