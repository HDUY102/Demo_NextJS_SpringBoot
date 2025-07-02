// Table Column
export interface Column<T> {
  key: keyof T | string;
  label: string;
  render?: (item: T, index?: number) => React.ReactNode;
}

// Table Header
export interface TableHeaderProps<T> {
  columns: Column<T>[];
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