// TableBody.tsx
import React from 'react';
import { Column } from './table.item';
import TableDetail from '@/app/components/table/TableDetail';

interface TableBodyProps<T> {
  data: T[];
  columns: Column<T>[];
  expandedRows?: Record<string, boolean>;
}

const TableBody = <T extends { id?: string | number }>({ data, columns,expandedRows = {}}: TableBodyProps<T>) => {
  return (
    <tbody>
      {data.map((row, index) => (
        <>
          <tr key={(row.id || index).toString()} className="hover:bg-gray-200 border-b">
            {columns.map((col, i) => (
              <td key={i} className="px-4 py-2 text-sm text-gray-700">
                {col.render ? col.render(row,index) : String((row as any)[col.key])}
              </td>
            ))}
          </tr>
          {"details" in row && expandedRows[row.id as string] && (
            <tr className="bg-gray-50">
              <td colSpan={columns.length} className="px-4 py-2">
                <TableDetail details={(row as any).details} />
              </td>
            </tr>
          )}
        </>
      ))}
    </tbody>
  );
};

export default TableBody;
