import {Contact,Archive,ArchiveX,LayoutDashboard,LayoutList,ChartNoAxesCombined,Flower} from "lucide-react";

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
    icon: ChartNoAxesCombined,
    children: [
      { title: "Đã giao", icon: Archive , href: "/dashboard/orderdone" },
      { title: "Chưa giao", icon: ArchiveX , href: "/dashboard/order" },
    ],
  }
]