import { CategoryItem } from "@/app/components/table/table.item";
import { Orders, OrderStatusHistory } from "@/hooks/Order/useOrders";

// Get Status Id
function getLatestStatus(order: Orders): number | undefined {
  try {
    const history: OrderStatusHistory[] =
      typeof order.orderHistoryJson === 'string'
        ? JSON.parse(order.orderHistoryJson)
        : order.orderHistoryJson;

    console.log(`👀 Raw orderHistoryJson for order ${order.id}:`, history);
    
    const latestStatus = history.at(-1)?.newStatusId;
    console.log(`📦 Order ${order.id} → statusId:`, latestStatus);
    return latestStatus;
  } catch (err) {
    console.warn('⚠️ Lỗi phân tích orderHistoryJson:', err);
    return undefined;
  }
}

// Init Category Order
const orderCategories: CategoryItem<Orders>[] = [
  {
      key: 1,
      label: 'Đơn mới',
      filterFn: (order:any) => getLatestStatus(order) === 1,
  },
  {
      key: 2,
      label: 'Đã xác nhận',
      filterFn: (order:any) => getLatestStatus(order) === 2,
  },
  {
      key: 7,
      label: 'Đã hủy',
      filterFn: (order:any) => getLatestStatus(order) === 7,
  },
];

export default orderCategories