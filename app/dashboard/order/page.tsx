'use client'
import React, { useMemo, useState } from 'react'
import TableHeader from '../../components/table/TableHeader'
import { Orders, OrderStatusHistory, useOrders } from '@/hooks/useOrders';
import { CategoryItem, TableColumn, FilterOption, SortState} from '@/app/components/table/table.item';
import TableBody from '@/app/components/table/TableBody';
import axiosInstance from '@/lib/axios';
import TableCategory from '@/app/components/table/TableCategory';
import TablePagination from '@/app/components/table/TablePagination';

// Get Status Id
function getLatestStatus(order: Orders): number | undefined {
  try {
    const history: OrderStatusHistory[] =
      typeof order.orderHistoryJson === 'string'
        ? JSON.parse(order.orderHistoryJson)
        : order.orderHistoryJson;

    console.log(`👀 Raw orderHistoryJson for order ${order.id}:`, history);
    
    const latestStatus = history.at(-1)?.newStatusId;
    console.log(`📦 Order ${order.id} → statusId:`, latestStatus);
    return latestStatus;
  } catch (err) {
    console.warn('⚠️ Lỗi phân tích orderHistoryJson:', err);
    return undefined;
  }
}

export default function OrderPage () {
  const [searchText, setSearchText] = useState('');
  const [debouncedSearchText, setDebouncedSearchText] = useState('');

  // Pagination
  const [currentPage, setCurrentPage] = useState(0);
  const [size, setSize] = useState(10);
  const [sortStatePagi, setSortStatePagi] = useState<SortState<Orders>>({ key: null, direction: 'desc' });
  const { orders, totalPages,isLoading, isError,mutate } = useOrders(debouncedSearchText,
        currentPage,
        size,
        sortStatePagi.key || '',
        sortStatePagi.direction === 'asc' ? 'ASC' : 'DESC');

  const [clientSearch, setClientSearch] = useState<Record<string, string>>({});// Client Search

  // Search Change (Get Text for Server-side)
  const handleSearchChange = (value: string) => {
    setSearchText(value);
    if (value.trim() === '') {
      setDebouncedSearchText('');
    }
  }
  // Press "Enter" for search (Call api Server-side)
  const handleSearchEnter = (e: React.KeyboardEvent<HTMLInputElement>) => {
    if (e.key === 'Enter') {
      setDebouncedSearchText(searchText);
    }
  }

  // Search Change (Get Text for Client side)
  const handleSearchChangeClient = (key: string, value: string) => {
    setClientSearch(prev => ({ ...prev, [key]: value }));
    if (value.trim() === '') {
      setDebouncedSearchText('');
    }
  }

  // Is Open Details?
  const [expandedRows, setExpandedRows] = useState<Record<string, boolean>>({});
  const toggleRow = (id: string) => {
    setExpandedRows((prev) => ({ ...prev, [id]: !prev[id] }));
  };

  // Init Category Order
  const orderCategories: CategoryItem<Orders>[] = [
    {
      key: 'all',
      label: 'Tất cả',
      filterFn: () => true,
    },
    {
      key: 1,
      label: 'Đơn mới',
      filterFn: (order:any) => getLatestStatus(order) === 1,
    },
    {
      key: 2,
      label: 'Đã xác nhận',
      filterFn: (order:any) => getLatestStatus(order) === 2,
    },
    {
      key: 6,
      label: 'Đã hủy',
      filterFn: (order:any) => getLatestStatus(order) === 7,
    },
  ];
  // Select Category Order
  const [selectedCategory, setSelectedCategory] = useState<CategoryItem<Orders>>(orderCategories[0]);
  const filteredOrders = selectedCategory?.filterFn
    ? orders?.filter(selectedCategory.filterFn) : orders;

  // Actions change state of Order
  const handleConfirm = async (orderId: string | number) => {
    try {
      const newStatusId = 2; // New -> Processing
      await axiosInstance.put( `/orders/${orderId}/status`,
        {
            currentStatusId: newStatusId,
            note: 'Xác nhận đơn hàng',
          // params: { // Use Params URL
          //   orderId,
          //   newStatusId,
          //   note: 'Xác nhận đơn hàng',
          // },
        },
      );
      mutate();
  }catch (error: any) {
    console.error("❌ Lỗi khi xác nhận đơn:", error);
    alert("Cập nhật trạng thái thất bại!");
  }};

  const handleCancel = async (orderId: string | number) => {
    try{
      const newStatusId = 7; // New -> Canceled
      await axiosInstance.put(`orders/${orderId}/status`,{
          currentStatusId: newStatusId,
          note: 'Đã hủy đơn hàng'
        })
      mutate();
    }catch (error: any){
      console.error("❌ Lỗi khi hủy đơn:", error);
      alert("Cập nhật trạng thái thất bại!");
  }};

  // Fill data Order to table
  // validate voi zod, co data type va constraints
  const orderColumns: TableColumn<Orders, keyof Orders>[] = [
    {
      key: "number",
      label: "No",
      type: "view"
    },
    {
      key: "customerName",
      label: "Customer Name",
      isSortable: true,
      type: 'text'
    },
    {
      key: "dateOrder",
      label: "Order Date",
      isSortable: true,
      type: 'date'
    },
    {
      key: "details",
      label: "Details",
      type: 'expand'
    },
    {
      key: "totalAmount",
      label: "Total Amount",
      isSortable: true,
      type: 'text'
    },
    {
      key: "isPaid",
      label: "Paid?",
      isSortable: true,
      type: 'bool'
    },
    {
      key: "actions",
      label: "Actions",
      type: 'button'
    },
  ];

  // Table Filter 
  const [filters, setFilters] = useState<Record<string, string | null>>({});
  const handleFilter = (key: string, value: string | null) => {
    setFilters((prev) => ({ ...prev, [key]: value }));
  };

  const getFilterValuesForColumn = (key: keyof Orders): FilterOption[] => {
  const unique = new Set<string>();
  orders?.forEach((order) => {
    const val:any = order[key];
    if (val != null) {
      if (key === "dateOrder") {
        const date = new Date(val);
        if (!isNaN(date.getTime())) {
          unique.add(val.toString().split(" ")[0]); // yyyy-mm-dd
        }
      } else {
        unique.add(String(val));
      }
    }
  });

    if (key === "dateOrder") {
      return Array.from(unique).map((value) => ({
        value,
        label: new Date(value).toLocaleDateString("vi-VN"),
      }));
    }

    return Array.from(unique).map((v) => ({ value: v, label: v }));
  };

  const filteredAndSearchedOrders = useMemo(() => {
    if (!filteredOrders) return [];
    
    return filteredOrders.filter((order) => {
      const matchesFilters = Object.entries(filters).every(([key, selectedValue]) => {
        if (!selectedValue) return true;
        const value = order[key as keyof Orders];
        if (value === null || value === undefined) return false;

        if (key === "dateOrder") {
          const formatted = (value as string).split(" ")[0];
          return formatted === selectedValue;
        }
        return String(value).toLowerCase() === selectedValue.toLowerCase();
      });
      
      // Logic Search Client
      const matchesClientSearch = Object.entries(clientSearch).every(([key, searchValue]) => {
        const orderValue = order[key as keyof Orders];
        if (orderValue === null || orderValue === undefined) return false;

        // Handle Search on col
        const normalizedSearchValue = searchValue.toLowerCase().trim();

        // Handle Search on col:"dateOrder"
        if (key === "dateOrder" && typeof orderValue === 'string') {
          const date = new Date(orderValue);
          const formattedDate = date.toLocaleDateString('vi-VN'); // formart date VN
          return formattedDate.includes(normalizedSearchValue)
        }

        // Search Other column
        return String(orderValue).toLowerCase().includes(normalizedSearchValue);
    });
      return matchesFilters && matchesClientSearch;
    });
  }, [filteredOrders, filters, clientSearch]);

  // Sort Order
  const [sortState, setOnSortState] = useState<SortState<Orders>>({ key: null, direction: null });
  const handleSort = (columnKey: string) =>{
    setOnSortState((prev:any) => {
      if(prev.key === columnKey){
        if(prev.direction === 'asc') return {key: columnKey, direction: 'desc'}
        else return { key: null, direction: null };
      }
      return { key: columnKey, direction: 'asc' };
    })
  }
  const sortedOrders = useMemo(() => {
    if (!sortState.key || !sortState.direction) return filteredAndSearchedOrders;

    const sorted = [...(filteredAndSearchedOrders ?? [])].sort((a, b) => {
      const key = sortState.key as keyof Orders;
      const aVal = a[key];
      const bVal = b[key];

      if (aVal == null || bVal == null) return 0;

      if (typeof aVal === 'string') {
        return sortState.direction === 'asc'
          ? aVal.localeCompare(bVal as string)
          : (bVal as string).localeCompare(aVal)
      }

      if (typeof aVal === 'number' || typeof aVal === 'boolean' || aVal instanceof Date) {
        return sortState.direction === 'asc'
          ? (aVal > bVal ? 1 : -1)
          : (aVal < bVal ? 1 : -1)
      }

      return 0;
    });

    return sorted;
  }, [filteredAndSearchedOrders, sortState]);

  if (isLoading) return <div className="p-4">Loading...</div>
  if (isError) return <div className="p-4 text-red-500">Failed to load orders</div>
  return (
    <div className="p-4">
      <TableCategory categories={orderCategories} selectedKey={selectedCategory?.key ?? null}
        onSelectCategory={setSelectedCategory} searchText={searchText} onSearchChange={handleSearchChange} onKeyDown={handleSearchEnter}/>
      <div className="shadow-2xl border rounded">
        <table className="min-w-full divide-y divide-gray-200 text-sm">
          <TableHeader<Orders> columns={orderColumns} onSort={(key) => setSortStatePagi((prev) => ({
                        key,
                        direction: prev.key === key && prev.direction === 'asc' ? 'desc' : 'asc'
                    }))} onFilter={handleFilter} filterValues={{
            customerName: getFilterValuesForColumn("customerName"),
            dateOrder: getFilterValuesForColumn("dateOrder"),
            isPaid: [
              { value: "true", label: "Đã thanh toán" },
              { value: "false", label: "Chưa thanh toán" }
            ]
          }}
          searchText={clientSearch}
          onSearchChange={handleSearchChangeClient}/>
          <TableBody<Orders> data={sortedOrders} records={orderColumns} expandedRows={expandedRows} onToggleRow={toggleRow}
          onConfirm={handleConfirm} onCancel={handleCancel}/>
        </table>
      </div>
      <div className='flex flex-row-reverse mt-2'>
        <TablePagination currentPage={currentPage} totalPages={totalPages} onPageChange={setCurrentPage}/>
      </div>
    </div>
  )
}