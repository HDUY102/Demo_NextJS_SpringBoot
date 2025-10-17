import { Orders } from "@/hooks/useOrders"

// Define Type
type ViewFieldType = "view" //custom view on table
type ButtonFieldType = "button" //custom button on table
type TextFieldType = "text" //Fill data in table
type ExpandFieldType = 'expand' //Expand row hide on table
type BooleanFieldType = 'bool' //Set true/false field on table
type DateFieldType = 'date' //Set data has type Date

// Table Column
export interface TableColumn<M, K extends keyof M>{
  key: K
  label: string
  isSortable?: boolean
  type: ViewFieldType | ButtonFieldType | TextFieldType | ExpandFieldType | BooleanFieldType | DateFieldType
}

// Table Record
export interface TableRecord<T, K extends keyof T>{
  key: K
  type: ViewFieldType | ButtonFieldType | TextFieldType | ExpandFieldType | BooleanFieldType | DateFieldType
  renderCell?: (key: K, value: T[K], row: T) => React.ReactNode
}

// Table Header
export interface TableHeaderProps<T> {
  columns: TableColumn<T, keyof T>[];
  onSort: (columnKey: string) => void;
  filterValues: Record<string, FilterOption[]>;
  onFilter: (key: string, value: string | null) => void;
  searchText: Record<string, string>;
  onSearchChange: (key: string, value: string) => void;
  onKeyDown?: (key: string, event: React.KeyboardEvent<HTMLInputElement>) => void;
}

// Table Body
export interface TableBodyProps<T> {
  records: Array<TableRecord<T,keyof T>>
  data: T[];
  expandedRows?: Record<string, boolean>;
  onToggleRow: (id: string) => void;
  onConfirm: (orderId: string | number) => void;
  onCancel: (orderId: string | number) => void;
}

// Sort Data
export type SortDirection = 'asc' | 'desc'
export interface SortState<T>{
  key: keyof T | string | null;
  direction: null | SortDirection;
}

// Table Category
export interface CategoryItem<T> {
  key: keyof T | string | number | null;
  label: string;
  filterFn?: (item: T) => boolean;
}
export interface TableCategoryProps<T> {
  categories: CategoryItem<T>[];
  selectedKey: CategoryItem<T>['key'];
  onSelectCategory: (category: CategoryItem<T>) => void;
  searchText: string;
  onSearchChange: (value: string) => void;
  onKeyDown: (event: React.KeyboardEvent<HTMLInputElement>) => void;
}

// Table Filter
export interface TableFilterProps<T> {
  keyFilter: string;
  filterValues: FilterOption[];
  onFilter: (key: string, value: string | null) => void;
  searchText: string;
  onSearchChange: (value: string) => void;
  onKeyDown?: (event: React.KeyboardEvent<HTMLInputElement>) => void;
}
export interface FilterOption {
  value: string; // yyyy-mm-dd
  label: string; // dd/mm/yyyy
}

// Payment Order
export interface PaymentStatusBadgeProps {
  isPaid: boolean;
}

// Show Detail Order
export interface ShowDetailProps{
    orderId: string
    isExpanded: boolean
    onClick: (id: string) => void
}

// Table Action
export interface TableActionProps {
    orderId: string
    order: Orders
    onConfirm: (id: string) => void
    onCancel: (id: string) => void
}