'use client'
import React, { useMemo, useState } from 'react'
import TableHeader from '../../components/table/TableHeader'
import { Orders, OrderStatusHistory, useOrders } from '@/hooks/useOrders';
import { CategoryItem, Column, SortState} from '@/app/components/table/table.item';
import TableBody from '@/app/components/table/TableBody';
import axiosInstance from '@/lib/axios';
import TableCategory from '@/app/components/table/TableCategory';

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
  const { orders, isLoading, isError,mutate } = useOrders();

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
    ? orders?.filter(selectedCategory.filterFn)
    : orders;

  // Actions change state of Order
  const handleConfirm = async (orderId: string | number) => {
    try {
      const newStatusId = 2; // New -> Processing
      await axiosInstance.put( `/orders/${orderId}/status`,
        {
            currentStatusId: newStatusId,
            note: 'Xác nhận đơn hàng',
          // Use Params URL
          // params: {
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
  const orderColumns: Column<Orders>[] = [
    {
      key: "number",
      label: "No",
      render: (_, index:any) => `${index + 1}`,
    },
    {
      key: "customerName",
      label: "Customer Name",
      isSortable: true,
    },
    {
      key: "dateOrder",
      label: "Order Date",
      render: (order) =>
        new Date(order.dateOrder).toLocaleDateString("vi-VN"),
      isSortable: true,
    },
    {
      key: "details",
      label: "Details",
      render: (order) => (
        <button className="text-blue-700 underline hover:cursor-pointer" 
          onClick={() => toggleRow(order.id)}>
            {expandedRows[order.id] ? "Ẩn" : "Xem"}
        </button>
      ),
    },
    {
      key: "totalAmount",
      label: "Total Amount",
      isSortable: true,
    },
    {
      key: "isPaid",
      label: "Paid?",
      render: (order) => (order.isPaid ? 
        <div className='rounded-2xl bg-green-800 p-0.5 text-center'>
          Đã thanh toán
        </div> : <div className='rounded-2xl bg-amber-500 p-0.5 text-center'>
          Chưa thanh toán
        </div>),
      isSortable: true,
    },
    {
      key: "actions",
      label: "Actions",
      render: (order) => (
        <div >
          {<div className="flex gap-2">
              <button className="px-2 py-1 bg-green-700 text-white text-xs rounded hover:bg-green-600 hover:cursor-pointer"
                onClick={() => handleConfirm(order.id)}>
                Xác nhận
              </button>
              <button className="px-2 py-1 bg-red-600 text-white text-xs rounded hover:bg-red-500 hover:cursor-pointer"
                onClick={() => handleCancel(order.id)} > Hủy
              </button>
            </div>}
      </div>
      ),
    },
  ];

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
  if (!sortState.key || !sortState.direction) return filteredOrders ?? [];

  const sorted = [...(filteredOrders ?? [])].sort((a, b) => {
    const key = sortState.key as keyof Orders;
    const aVal = a[key];
    const bVal = b[key];

    if (aVal == null || bVal == null) return 0;

    if (typeof aVal === 'string') {
      return sortState.direction === 'asc'
        ? aVal.localeCompare(bVal as string)
        : (bVal as string).localeCompare(aVal);
    }

    if (typeof aVal === 'number' || typeof aVal === 'boolean' || aVal instanceof Date) {
      return sortState.direction === 'asc'
        ? (aVal > bVal ? 1 : -1)
        : (aVal < bVal ? 1 : -1);
    }

    return 0;
  });

  return sorted;
}, [filteredOrders, sortState]);


  if (isLoading) return <div className="p-4">Loading...</div>
  if (isError) return <div className="p-4 text-red-500">Failed to load orders</div>
  return (
    <div className="p-4">
      <TableCategory categories={orderCategories} selectedKey={selectedCategory?.key ?? null}
        onSelectCategory={setSelectedCategory}/>
      <div className="shadow-2xl border rounded">
        <table className="min-w-full divide-y divide-gray-200 text-sm">
          <TableHeader columns={orderColumns} onSort={handleSort}/>
          <TableBody data={sortedOrders} columns={orderColumns} expandedRows={expandedRows}/>
        </table>
      </div>
    </div>
  )
}