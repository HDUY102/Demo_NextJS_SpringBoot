import React from 'react'
import { TableActionProps } from './table.item'

export const TableAction: React.FC<TableActionProps> = ({orderId, onConfirm, onCancel}) => {
  return (
    <div className="flex gap-2">
        <button className='px-2 py-1 bg-green-700 text-white text-xs rounded hover:bg-green-600 hover:cursor-pointer'
            onClick={() => onConfirm(orderId)}
        >
            Xác nhận
        </button>
        <button className='px-2 py-1 bg-red-600 text-white text-xs rounded hover:bg-red-500 hover:cursor-pointer'
            onClick={() => onCancel(orderId)}
        >
            Hủy
        </button>
    </div>
  )
}