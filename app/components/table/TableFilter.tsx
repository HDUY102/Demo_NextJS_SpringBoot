"use client"
import * as React from "react"
import { FiFilter } from "react-icons/fi";
import { Button } from ".././ui/button"
import { DropdownMenu, DropdownMenuCheckboxItem, DropdownMenuContent, DropdownMenuLabel, DropdownMenuSeparator, DropdownMenuTrigger,} from "@/app/components/ui/dropdown-menu"
import { TableFilterProps } from "./table.item";

export function TableFilter<T>({ keyFilter, filterValues, onFilter,searchText,onSearchChange,onKeyDown}: TableFilterProps<T>) {
  const [selectedValue, setSelectedValue] = React.useState<string | null>(null);

  const handleSelect = (value: string | null) => {
    setSelectedValue(value);
    onFilter(keyFilter, value);
  };

  // Prevent default selected of shadcn
  const handleKeyDown = (event: React.KeyboardEvent<HTMLInputElement>) => {
      event.stopPropagation();
      onKeyDown?.(event);
  };

  return (
    <DropdownMenu>
      <DropdownMenuTrigger asChild>
        <Button variant="ghost" size="sm" className="p-1 mr-0.5"><FiFilter /></Button>
      </DropdownMenuTrigger>
      <DropdownMenuContent className="w-56">
        <div className="relative mr-3">
          <input className="w-[213px] pl-3 pr-3 py-1.5 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-400 focus:border-blue-400 text-sm"
          type="text" placeholder="Search" value={searchText} onKeyDown={handleKeyDown}
          onChange={(e) => onSearchChange(e.target.value)}/>
        </div>
        <DropdownMenuSeparator />
        <DropdownMenuLabel>Lọc theo</DropdownMenuLabel>
        <DropdownMenuSeparator />
        <DropdownMenuCheckboxItem
          checked={selectedValue === null}
          onCheckedChange={() => handleSelect(null)}
        >
          Tất cả
        </DropdownMenuCheckboxItem>
        {filterValues?.map((val, idx) => (
          <DropdownMenuCheckboxItem
            key={`${keyFilter}-${val.value}-${idx}`}
            checked={selectedValue === val.value}
            onCheckedChange={() => handleSelect(val.value)}
          >
            {val.label}
          </DropdownMenuCheckboxItem>
        ))}
      </DropdownMenuContent>
    </DropdownMenu>
  )
}