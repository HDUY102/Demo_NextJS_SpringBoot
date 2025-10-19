'use client'
import React from 'react'
import TableHeader from '../../components/table/TableHeader'
import TableBody from '@/app/components/table/TableBody';
import TableCategory from '@/app/components/table/TableCategory';
import TablePagination from '@/app/components/table/TablePagination';
import orderColumns from './ColumnHeader';
import orderCategories from './OrderCategories';
import { useLogicOrder } from '@/hooks/Order/useLogicOrder';

export default function OrderPage () {
  const { orders, isLoading, isError, totalPages,
    currentPage, setCurrentPage,
    searchText, handleSearchChange, handleSearchEnter,
    clientSearch, handleSearchChangeClient,
    orderFilterValues, handleFilter,
    handleSort,
    handleConfirm,
    handleCancel,
    selectedCategory, setSelectedCategory,
    expandedRows, toggleRow,
  } = useLogicOrder()

  if (isLoading) return <div className="p-4">Loading...</div>
  if (isError) return <div className="p-4 text-red-500">Failed to load orders</div>
  return (
    <div className="p-4">
      <TableCategory categories={orderCategories} selectedKey={selectedCategory?.key ?? null} onSelectCategory={setSelectedCategory}
        searchText={searchText} onSearchChange={handleSearchChange} onKeyDown={handleSearchEnter}/>
      <div className="shadow-2xl border rounded">
        <table className="min-w-full divide-y divide-gray-200 text-sm">
          <TableHeader columns={orderColumns} filterValues={orderFilterValues}
            searchText={clientSearch} onSearchChange={handleSearchChangeClient}
            onSort={handleSort} onFilter={handleFilter} />
          <TableBody data={orders} records={orderColumns} expandedRows={expandedRows} onToggleRow={toggleRow} onConfirm={handleConfirm}
            onCancel={handleCancel} />
        </table>
      </div>
      <div className="flex flex-row-reverse mt-2">
        <TablePagination currentPage={currentPage} totalPages={totalPages} onPageChange={setCurrentPage} />
      </div>
    </div>
  )
}