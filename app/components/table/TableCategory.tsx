import React from 'react'

const TableCategory = ({onChangeByOrder}) => {
  return (
    <div className="flex justify-end text-xs mb-2">
        <button className="p-2 hover:cursor-pointer border-1">Đơn Mới</button>
        <button className="p-2 hover:cursor-pointer border-1 ">Đơn Đã Xác Nhận</button>
        <button className="p-2 hover:cursor-pointer border-1">Đơn Đã Hủy</button>
    </div>
  )
}

export default TableCategory