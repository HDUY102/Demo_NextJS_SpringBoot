// hooks/Order/useLogicOrder.ts
'use client'
import { useMemo, useState } from 'react'
import { Orders, useOrders } from './useOrders'
import axiosInstance from '@/lib/axios'
import { CategoryItem, FilterOption, SortState } from '@/app/components/table/table.item'
import orderCategories from '@/app/dashboard/order/OrderCategories'

export function useLogicOrder() {
  /** ─────────────── STATE CƠ BẢN ─────────────── **/
  const [searchText, setSearchText] = useState('')
  const [debouncedSearchText, setDebouncedSearchText] = useState('')
  const [currentPage, setCurrentPage] = useState(0)
  const [size, setSize] = useState(10)
  const [sortStatePagi, setSortStatePagi] = useState<SortState<Orders>>({ key: null, direction: 'desc' })

  const { orders, totalPages, isLoading, isError, mutate } = useOrders(
    debouncedSearchText,
    currentPage,
    size,
    sortStatePagi.key || '',
    sortStatePagi.direction === 'asc' ? 'ASC' : 'DESC'
  )

  /** ─────────────── SEARCH ─────────────── **/
  const [clientSearch, setClientSearch] = useState<Record<string, string>>({})

  const handleSearchChange = (value: string) => {
    setSearchText(value)
    if (value.trim() === '') setDebouncedSearchText('')
  }

  const handleSearchEnter = (e: React.KeyboardEvent<HTMLInputElement>) => {
    if (e.key === 'Enter') setDebouncedSearchText(searchText)
  }

  const handleSearchChangeClient = (key: string, value: string) => {
    setClientSearch(prev => ({ ...prev, [key]: value }))
  }

  /** ─────────────── EXPANDED ROWS ─────────────── **/
  const [expandedRows, setExpandedRows] = useState<Record<string, boolean>>({})
  const toggleRow = (id: string) => setExpandedRows(prev => ({ ...prev, [id]: !prev[id] }))

  /** ─────────────── CATEGORY FILTER ─────────────── **/
  const [selectedCategory, setSelectedCategory] = useState<CategoryItem<Orders>>(orderCategories[0])
  const filteredOrders = selectedCategory?.filterFn ? orders?.filter(selectedCategory.filterFn) : orders

  /** ─────────────── HANDLE ACTIONS ─────────────── **/
  const handleConfirm = async (orderId: string | number) => {
    try {
      await axiosInstance.put(`/orders/${orderId}/status`, {
        currentStatusId: 2,
        note: 'Xác nhận đơn hàng',
      })
      mutate()
    } catch (error: any) {
      console.error('❌ Lỗi khi xác nhận đơn:', error)
      alert('Cập nhật trạng thái thất bại!')
    }
  }

  const handleCancel = async (orderId: string | number) => {
    try {
      await axiosInstance.put(`/orders/${orderId}/status`, {
        currentStatusId: 7,
        note: 'Đã huỷ đơn hàng',
      })
      mutate()
    } catch (error: any) {
      console.error('❌ Lỗi khi huỷ đơn:', error)
      alert('Cập nhật trạng thái thất bại!')
    }
  }

  /** ─────────────── FILTER ─────────────── **/
  const [filters, setFilters] = useState<Record<string, string | null>>({})
  const handleFilter = (key: string, value: string | null) => {
    setFilters(prev => ({ ...prev, [key]: value }))
  }

  const getFilterValuesForColumn = (key: keyof Orders): FilterOption[] => {
    const unique = new Set<string>()
    orders?.forEach(order => {
      const val: any = order[key]
      if (val != null) {
        if (key === 'dateOrder') {
          const date = new Date(val)
          if (!isNaN(date.getTime())) unique.add(val.toString().split(' ')[0])
        } else unique.add(String(val))
      }
    })

    if (key === 'dateOrder') {
      return Array.from(unique).map(value => ({
        value,
        label: new Date(value).toLocaleDateString('vi-VN'),
      }))
    }

    return Array.from(unique).map(v => ({ value: v, label: v }))
  }

  const orderFilterValues = useMemo(
    () => ({
      customerName: getFilterValuesForColumn('customerName'),
      dateOrder: getFilterValuesForColumn('dateOrder'),
      isPaid: [
        { value: 'true', label: 'Đã thanh toán' },
        { value: 'false', label: 'Chưa thanh toán' },
      ],
    }),
    [orders]
  )

  /** ─────────────── CLIENT FILTER + SEARCH ─────────────── **/
  const filteredAndSearchedOrders = useMemo(() => {
    if (!filteredOrders) return []

    return filteredOrders.filter(order => {
      const matchesFilters = Object.entries(filters).every(([key, selectedValue]) => {
        if (!selectedValue) return true
        const value = order[key as keyof Orders]
        if (value == null) return false

        if (key === 'dateOrder') {
          const formatted = (value as string).split(' ')[0]
          return formatted === selectedValue
        }
        return String(value).toLowerCase() === selectedValue.toLowerCase()
      })

      const matchesClientSearch = Object.entries(clientSearch).every(([key, searchValue]) => {
        const orderValue = order[key as keyof Orders]
        if (orderValue == null) return false

        const normalizedSearchValue = searchValue.toLowerCase().trim()

        if (key === 'dateOrder' && typeof orderValue === 'string') {
          const date = new Date(orderValue)
          const formattedDate = date.toLocaleDateString('vi-VN')
          return formattedDate.includes(normalizedSearchValue)
        }

        return String(orderValue).toLowerCase().includes(normalizedSearchValue)
      })

      return matchesFilters && matchesClientSearch
    })
  }, [filteredOrders, filters, clientSearch])

  /** ─────────────── SORT ─────────────── **/
  const [sortState, setSortState] = useState<SortState<Orders>>({ key: null, direction: null })

  const handleSort = (columnKey: string) => {
    setSortState(prev => {
      if (prev.key === columnKey) {
        if (prev.direction === 'asc') return { key: columnKey, direction: 'desc' }
        else return { key: null, direction: null }
      }
      return { key: columnKey, direction: 'asc' }
    })
  }

  const sortedOrders = useMemo(() => {
    if (!sortState.key || !sortState.direction) return filteredAndSearchedOrders
    const sorted = [...filteredAndSearchedOrders].sort((a, b) => {
      const key = sortState.key as keyof Orders
      const aVal = a[key]
      const bVal = b[key]
      if (aVal == null || bVal == null) return 0

      if (typeof aVal === 'string') {
        return sortState.direction === 'asc'
          ? aVal.localeCompare(bVal as string)
          : (bVal as string).localeCompare(aVal)
      }
      if (typeof aVal === 'number' || typeof aVal === 'boolean' || aVal instanceof Date) {
        return sortState.direction === 'asc' ? (aVal > bVal ? 1 : -1) : (aVal < bVal ? 1 : -1)
      }
      return 0
    })
    return sorted
  }, [filteredAndSearchedOrders, sortState])

  /** ─────────────── RETURN ─────────────── **/
  return {
    orders: sortedOrders,
    isLoading,
    isError,
    totalPages,
    currentPage,
    setCurrentPage,
    // search
    searchText,
    handleSearchChange,
    handleSearchEnter,
    clientSearch,
    handleSearchChangeClient,
    // filters
    orderFilterValues,
    handleFilter,
    // sort
    handleSort,
    // actions
    handleConfirm,
    handleCancel,
    // category
    selectedCategory,
    setSelectedCategory,
    // expand
    expandedRows,
    toggleRow,
  }
}
