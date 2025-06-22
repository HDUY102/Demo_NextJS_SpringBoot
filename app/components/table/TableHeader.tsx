import { Column } from '@/app/components/table/table.item'

interface TableHeaderProps<T> {
  columns: Column<T>[];
}

const TableHeader = <T,>({ columns }: TableHeaderProps<T>)=> {
  return (
    <thead>
      <tr>
        {columns.map((col)=>(
          <th key={col.key.toString()} className="px-4 py-2 text-left text-sm font-medium text-black">
            {col.label}
          </th>
        ))}
      </tr>
    </thead>
  )
}

export default TableHeader