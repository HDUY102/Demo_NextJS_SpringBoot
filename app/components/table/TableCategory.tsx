import React from 'react'
import { TableCategoryProps } from './table.item'

const TableCategory = <T,>({categories, selectedKey,onSelectCategory,searchText,onSearchChange,onKeyDown}: TableCategoryProps<T>) => {
  return (
    <div className='flex gap-2 text-sm justify-end mb-2'>
      <div className="relative mr-3">
        <input className="pl-3 pr-3 py-1.5 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-400 focus:border-blue-400 text-sm"
        type="text" placeholder="Search" value={searchText} onChange={(e) => onSearchChange(e.target.value)} onKeyDown={onKeyDown}/>
      </div>
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