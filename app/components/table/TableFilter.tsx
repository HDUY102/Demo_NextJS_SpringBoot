"use client"
import * as React from "react"
import { FiFilter } from "react-icons/fi";
import { Button } from ".././ui/button"
import { DropdownMenu, DropdownMenuCheckboxItem, DropdownMenuContent, DropdownMenuLabel, DropdownMenuSeparator, DropdownMenuTrigger,} from "@/app/components/ui/dropdown-menu"
import { TableFilterProps } from "./table.item";

export function TableFilter<T>({ keyFilter, filterValues, onFilter }: TableFilterProps<T>) {
  const [selectedValue, setSelectedValue] = React.useState<string | null>(null);

  const handleSelect = (value: string | null) => {
    setSelectedValue(value);
    onFilter(keyFilter, value);
  };

  return (
    <DropdownMenu>
      <DropdownMenuTrigger asChild>
        <Button variant="ghost" size="sm" className="p-1"><FiFilter /></Button>
      </DropdownMenuTrigger>
      <DropdownMenuContent className="w-56">
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
  );
}