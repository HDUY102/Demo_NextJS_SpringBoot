'use client'
import React, { useState } from 'react'
import TableHeader from '../../components/table/TableHeader'
import { Orders, useOrders } from '@/hooks/useOrders';
import { Column } from '@/app/components/table/table.item';
import TableBody from '@/app/components/table/TableBody';
import axiosInstance from '@/lib/axios';
import TableCategory from '@/app/components/table/TableCategory';

export default function OrderPage () {
  const { orders, isLoading, isError,mutate } = useOrders();

  const [expandedRows, setExpandedRows] = useState<Record<string, boolean>>({});
  const toggleRow = (id: string) => {
    setExpandedRows((prev) => ({ ...prev, [id]: !prev[id] }));
  };

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

      console.log("✅ Đơn đã được xác nhận:");

      mutate();
  }catch (error: any) {
    console.error("❌ Lỗi khi xác nhận đơn:", error);
    alert("Cập nhật trạng thái thất bại!");
  }};

  const handleCancel = async (orderId: string | number) => {
    try{
      const newStatusId = 6; // New -> Canceled
      await axiosInstance.put(`orders/${orderId}/status`,{
          currentStatusId: newStatusId,
          note: 'Đã hủy đơn hàng'
        })
      console.log("❌ Đơn đã hủy:");

      mutate();
    }catch (error: any){
      console.error("❌ Lỗi khi xác nhận đơn:", error);
      alert("Cập nhật trạng thái thất bại!");
  }};

  const orderColumns: Column<Orders>[] = [
    {
      key: "number",
      label: "No",
      render: (_, index:any) => `${index + 1}`,
    },
    {
      key: "customerName",
      label: "Customer Name",
    },
    {
      key: "dateOrder",
      label: "Order Date",
      render: (order) =>
        new Date(order.dateOrder).toLocaleDateString("vi-VN"),
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

  if (isLoading) return <div className="p-4">Loading...</div>
  if (isError) return <div className="p-4 text-red-500">Failed to load orders</div>
  console.log(orders)
  return (
    <div className="p-4">
      <TableCategory />
      <div className="shadow-2xl border rounded">
        <table className="min-w-full divide-y divide-gray-200 text-sm">
          <TableHeader columns={orderColumns} />
          <TableBody data={orders || []} columns={orderColumns} expandedRows={expandedRows}/>
        </table>
      </div>
    </div>
  )
}