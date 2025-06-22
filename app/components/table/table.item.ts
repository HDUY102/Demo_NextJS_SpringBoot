export interface Column<T> {
  key: keyof T | string;
  label: string;
  render?: (item: T, index?: number) => React.ReactNode;
}