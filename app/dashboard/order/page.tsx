'use client'
import React, { useState } from 'react'
import TableHeader from '../../components/table/TableHeader'
import { Orders, useOrders } from '@/hooks/useOrders';
import { Column } from '@/app/components/table/table.item';
import TableBody from '@/app/components/table/TableBody';

export default function OrderPage () {
  const { orders, isLoading, isError } = useOrders();
  const [expandedRows, setExpandedRows] = useState<Record<string, boolean>>({});

  const toggleRow = (id: string) => {
    setExpandedRows((prev) => ({ ...prev, [id]: !prev[id] }));
  };

  if (isLoading) return <div className="p-4">Loading...</div>
  if (isError) return <div className="p-4 text-red-500">Failed to load orders</div>

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
        <button
          className="text-blue-600 underline"
          onClick={() => toggleRow(order.id)}
        >
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
      render: (order) => (order.isPaid ? "Paid" : "Unpaid"),
    },
  ];

  return (
    <div className="p-4">
      <div className="shadow-2xl border rounded">
        <table className="min-w-full divide-y divide-gray-200 text-sm">
          <TableHeader columns={orderColumns} />
          <TableBody data={orders || []} columns={orderColumns} expandedRows={expandedRows}/>
        </table>
      </div>
    </div>
  )
}