import { TableColumn } from "@/app/components/table/table.item";
import { Orders } from "@/hooks/Order/useOrders";

// Fill data Order to table
const orderColumns: TableColumn<Orders, keyof Orders>[] = [
    {
      key: "number",
      label: "No",
      type: "view"
    },
    {
      key: "customerName",
      label: "Customer Name",
      isSortable: true,
      type: 'text'
    },
    {
      key: "dateOrder",
      label: "Order Date",
      isSortable: true,
      type: 'date'
    },
    {
      key: "details",
      label: "Details",
      type: 'expand'
    },
    {
      key: "totalAmount",
      label: "Total Amount",
      isSortable: true,
      type: 'text'
    },
    {
      key: "isPaid",
      label: "Paid?",
      isSortable: true,
      type: 'bool'
    },
    {
      key: "actions",
      label: "Actions",
      type: 'button'
    },
  ];

export default orderColumns