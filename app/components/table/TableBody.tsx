import React from 'react';
import {TableBodyProps, TableRecord } from './table.item';
import TableDetail from '@/app/components/table/TableDetail';
import { PaymentStatus } from './PaymentStatus';
import { TableAction } from './TableAction';
import { ShowDetail } from './ShowDetail';
import { Orders } from '@/hooks/Order/useOrders';

const TableBody = <T,>({records, data, expandedRows, onToggleRow, onConfirm, onCancel} : TableBodyProps<T>) =>{
  return(
    <tbody className="bg-gray-900 text-white">
      {data.map((row, rowIndex)=>{
        const rowId = (row as any).id as string
        const isCurrentRowExpanded = expandedRows?.[rowId] || false;
        return(
          <React.Fragment key={(row as any).id}>
          <tr key={rowIndex} className='hover:bg-gray-800 transition duration-150'>
            {records.map((col: TableRecord<T, keyof T>)=>{
              const value = row[col.key]
              let cellContent: React.ReactNode
              
              if(col.renderCell){
                cellContent = col.renderCell(col.key, value, row)
              }else{
                switch(col.type){
                  case 'bool':
                    cellContent = <PaymentStatus isPaid={Boolean(value)} />
                    break;
                  case 'button':
                    cellContent = <TableAction orderId={rowId} order={row as Orders} onConfirm={onConfirm} onCancel={onCancel}/>
                    break;
                  case 'expand':
                    cellContent = <ShowDetail orderId={rowId} isExpanded={isCurrentRowExpanded} onClick={onToggleRow} />
                    break;
                  case 'text':
                    cellContent = <span>{String(value ?? "")}</span>;
                    break;
                  case 'view':
                    cellContent = <span className="text-center">{rowIndex + 1}</span>;
                    break;
                  case 'date':
                    cellContent = <span> {new Date((row as any).dateOrder).toLocaleDateString("vi-VN")} </span>
                    break;
                  default: 
                    cellContent = <span> - </span>
                }
              }
              return (
                <td key={String(col.key)} className={`px-2 py-4 text-sm ${
                      col.key === "isPaid" ? "text-center" : "text-left" }`}
                >
                  {cellContent}
                </td>
            )})}
          </tr>
          {isCurrentRowExpanded && (
            <tr>
              <td colSpan={records.length} className="bg-gray-700 p-0">
                <TableDetail details={(row as Orders).details || []}/>
              </td>
            </tr>
          )}
        </React.Fragment>
        )
      })}
    </tbody>
  )
}

export default TableBody;