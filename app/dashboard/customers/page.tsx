'use client';
import { useCategories } from '@/hooks/useCategories';

export default function CustomersPage() {
  const { categories, isLoading, isError } = useCategories();

  if (isLoading) return <p>Đang tải danh mục...</p>;
  if (isError) return <p>Lỗi khi tải dữ liệu</p>;

  return (
    <div className="p-4">
      <h1 className="text-2xl font-bold mb-4">Danh sách danh mục</h1>
      <ul className="space-y-2">
        {categories?.map((cat) => (
          <li key={cat.id} className="p-2 border rounded">
            <strong>{cat.name}</strong>
          </li>
        ))}
      </ul>
    </div>
  );
}
