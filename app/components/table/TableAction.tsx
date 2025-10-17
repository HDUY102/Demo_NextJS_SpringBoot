import React from 'react'
import { TableActionProps } from './table.item'
import { OrderStatusHistory } from '@/hooks/useOrders';

export const TableAction: React.FC<TableActionProps> = ({orderId, order, onConfirm, onCancel}) => {
    let currentStatusId: number | undefined = undefined;

  try {
    const history: OrderStatusHistory[] =
      typeof order.orderHistoryJson === 'string'
        ? JSON.parse(order.orderHistoryJson)
        : order.orderHistoryJson;

    currentStatusId = history?.at(-1)?.newStatusId;
  } catch (error) {
    console.warn('Lỗi đọc orderHistoryJson:', error);
  }
  if (currentStatusId === 7) { // Canceled
    return (
      <div className="text-gray-400 text-xs italic">
        Đã hủy
      </div>
    );
  }

  if (currentStatusId === 1) { // New Orders
    return (
      <div className="flex gap-2">
        <button
          className="px-2 py-1 text-blue-600 text-xs rounded hover:text-blue-400 hover:cursor-pointer"
          onClick={() => onConfirm(orderId)}
        >
          Xác nhận
        </button>
        <button
          className="px-2 py-1 text-red-600 text-xs rounded hover:text-red-400 hover:cursor-pointer"
          onClick={() => onCancel(orderId)}
        >
          Hủy
        </button>
      </div>
    );
  }

  if (currentStatusId === 2) { // Only Canceled when Order is Confirm
    return (
      <div className="flex gap-2">
        <button
          className="px-2 py-1 text-red-600 text-xs rounded hover:text-red-400 hover:cursor-pointer"
          onClick={() => onCancel(orderId)}
        >
          Hủy
        </button>
      </div>
    );
  }

  return <div className="text-gray-500 text-xs italic">--</div>;
}