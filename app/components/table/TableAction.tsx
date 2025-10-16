import React from 'react'
import { TableActionProps } from './table.item'

export const TableAction: React.FC<TableActionProps> = ({orderId, onConfirm, onCancel}) => {
  return (
    <div className="flex gap-2">
        <button className='px-2 py-1 text-blue-600 text-xs rounded hover:text-blue-400 hover:cursor-pointer'
            onClick={() => onConfirm(orderId)}
        >
            Xác nhận
        </button>
        <button className='px-2 py-1 text-red-600 text-xs rounded hover:text-red-400 hover:cursor-pointer'
            onClick={() => onCancel(orderId)}
        >
            Hủy
        </button>
    </div>
  )
}