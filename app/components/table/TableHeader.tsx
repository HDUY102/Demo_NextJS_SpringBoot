import { TableHeaderProps } from '@/app/components/table/table.item'
import { BiSort } from "react-icons/bi";
import { TableFilter } from './TableFilter';

const TableHeader = <T,>({ columns,onSort,filterValues,onFilter, searchText, onSearchChange, onKeyDown }: TableHeaderProps<T>)=> {
  return (
    <thead className='bg-gray-900 text-white'>
      <tr>
        {columns.map((col)=>(
          <th key={col.key.toString()} className={`px-4 py-2 text-sm font-medium ${
              col.key === 'isPaid' ? 'text-center' : 'text-left'
            }`}>
            {![ 'details', 'number', 'actions', 'totalAmount', 'isPaid' ].includes(String(col.key)) && (
              <TableFilter keyFilter={col.key.toString()}
                filterValues={filterValues[col.key.toString()] ?? []}
                onFilter={onFilter} searchText={searchText[col.key.toString()] || ''}
                onKeyDown={onKeyDown ? (e) => onKeyDown(col.key.toString(), e) : undefined}
                onSearchChange={(value) => onSearchChange(col.key.toString(), value)}/>
            )}
            {col.label}
            {col.isSortable && (<button className='ml-1 hover:cursor-pointer' onClick={()=> onSort(col.key.toString())}><BiSort/></button>)}
          </th>
        ))}
      </tr>
    </thead>
  )
}

export default TableHeader