import {Contact,LayoutDashboard,LayoutList,ChartNoAxesCombined,Flower, Newspaper} from "lucide-react";
import { FaBoxesStacked  } from "react-icons/fa6";
import { RiCustomerService2Fill } from "react-icons/ri";

type SidebarMenuItem = {
  title: string;
  icon: React.ElementType;
  href?: string;
  children?: [] | any[];
};

export const menuItems: SidebarMenuItem[] = [
  {
    title: "Flower Garden",
    icon: LayoutDashboard,
    href: "/dashboard",
  },
  {
    title: "Sản phẩm",
    icon: LayoutList ,
    children: [
      { title: "Hoa tươi", icon: Flower, href: "/dashboard/freshflowers" },
      { title: "Hoa giả", icon: Flower, href: "/dashboard/artificialflowers" },
      { title: "Phụ kiện", icon: Flower, href: "/dashboard/accessory" },
    ],
  },
  {
    title: "Khách hàng",
    icon: Contact,
    href: "/dashboard/customers",
  },
  {
    title: "Thống kê",
    icon: ChartNoAxesCombined,
    href: "/dashboard/statistical",
  },
  {
    title: "Đơn hàng",
    icon: FaBoxesStacked,
    href: "/dashboard/order",
    // children: [
    //   { title: "Đơn hàng mới", icon: Newspaper , href: "/dashboard/order/new" },
    //   { title: "Đang xử lý", icon: MdPendingActions , href: "/dashboard/orderdone" },
    //   { title: "Chưa giao", icon: ArchiveX , href: "/dashboard/order" },
    // ],
  },
  {
    title: "Chăm sóc khác hàng",
    icon: RiCustomerService2Fill,
    href: "/dashboard/takecare",
  },
]