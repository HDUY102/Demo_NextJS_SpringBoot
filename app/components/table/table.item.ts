// Table Column
export interface Column<T> {
  key: keyof T | string;
  label: string;
  render?: (item: T, index?: number) => React.ReactNode;
  isSortable?: boolean;
}

// Table Header
export interface TableHeaderProps<T> {
  columns: Column<T>[];
  onSort: (columnKey: string) => void;
  filterValues: Record<string, FilterOption[]>;
  // filterValues: Record<string, string[]>;
  onFilter: (key: string, value: string | null) => void;
}

// Sort Data
export type SortDirection = 'asc' | 'desc'
export interface SortState<T>{
  key: keyof T | string | null;
  direction: null | SortDirection;
}

// Table Body
export interface TableBodyProps<T> {
  data: T[];
  columns: Column<T>[];
  expandedRows?: Record<string, boolean>;
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
}

  // Table Filter
export interface TableFilterProps<T> {
  keyFilter: string;
  filterValues: FilterOption[];
  // filterValues: string[];
  onFilter: (key: string, value: string | null) => void;
}


export interface FilterOption {
  value: string; // yyyy-mm-dd
  label: string; // dd/mm/yyyy
}