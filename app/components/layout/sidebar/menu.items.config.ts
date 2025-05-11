import {Archive,ArchiveX,LogOut,LayoutDashboard,LayoutList,ChartNoAxesCombined,Flower,Bell,Settings,HelpCircle,CircleUser} from "lucide-react";

type SidebarMenuItem = {
  title: string;
  icon: React.ElementType;
  href?: string;
  children?: [] | any[];
};

type HeaderMenuItem = {
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
    title: "hehe",
    icon: LayoutList ,
    children: [
      { title: "Hoa tươi", icon: Flower, href: "/dashboard/freshflowers" },
      { title: "Hoa giả", icon: Flower, href: "/dashboard/artificialflowers" },
      { title: "Phụ kiện", icon: Flower, href: "/dashboard/accessory" },
    ],
  },
  {
    title: "Thống kê",
    icon: ChartNoAxesCombined,
    // children: [
    //   { title: "Theo ngày", icon: ChartNoAxesCombined , href: "/dashboard/loainha" },
    //   { title: "Theo tháng", icon: ChartNoAxesCombined , href: "/dashboard/nha" },
    // ],
    href: "/statistical",
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

export const headerItems: HeaderMenuItem[] = [
  {
    title: "Thông báo",
    icon: Bell,
    href: "/notifications",
  },
  {
    title: "Tài khoản",
    icon: CircleUser,
    children: [
      { title: "Thông tin cá nhân", icon: CircleUser, href: "/account/profile" },
      { title: "Cài đặt", icon: LogOut, href: "/settings" },
      { title: "Trợ giúp", icon: LogOut, href: "/help" },
      { title: "Đăng xuất", icon: LogOut, href: "/logout" },
    ],
  }
];