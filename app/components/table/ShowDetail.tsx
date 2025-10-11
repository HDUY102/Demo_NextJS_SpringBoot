import React from 'react'
import { ShowDetailProps } from './table.item'

export const ShowDetail: React.FC<ShowDetailProps> = ({orderId,isExpanded,onClick}) => {
  return (
    <button className='text-blue-700 underline hover:cursor-pointer' onClick={() => onClick(orderId)}>
        {isExpanded ? "Ẩn" : "Xem"}
    </button>
  )
}