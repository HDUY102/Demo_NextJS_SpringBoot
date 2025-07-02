import React from 'react'
import { TableCategoryProps } from './table.item'

const TableCategory = <T,>({categories, selectedKey,onSelectCategory}: TableCategoryProps<T>) => {
  return (
    <div className='flex gap-2 text-sm justify-end mb-2'>
      {categories.map(category =>(
        <button key={category.key?.toString()} onClick={() => onSelectCategory(category)}
          className={`px-3 py-1 rounded border hover:bg-gray-200 transition ${
          selectedKey === category.key ? 'bg-gray-300 font-bold' : ''}`}>
            {category.label}
        </button>
      ))}
    </div>
  )
}

export default TableCategory