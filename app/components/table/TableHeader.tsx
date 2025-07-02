import { TableHeaderProps } from '@/app/components/table/table.item'

const TableHeader = <T,>({ columns }: TableHeaderProps<T>)=> {
  return (
    <thead className='bg-gray-900 text-white'>
      <tr>
        {columns.map((col)=>(
          <th key={col.key.toString()} className={`px-4 py-2 text-sm font-medium ${
              col.key === 'isPaid' ? 'text-center' : 'text-left'
            }`}>
            {col.label}
          </th>
        ))}
      </tr>
    </thead>
  )
}

export default TableHeader