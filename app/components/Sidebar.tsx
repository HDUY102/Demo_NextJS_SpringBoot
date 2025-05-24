'use client'
import React, { useState } from 'react'
import { menuItems } from './layout/sidebar/menu.items.config'
import SidebarMenuAccordionItem from './layout/sidebar/SidebarMenuAccordionItem'
import SidebarMenuItem from './layout/sidebar/SidebarMenuItem'
import { Menu, X } from 'lucide-react'

interface SidebarProps {
  isSidebarOpen: boolean;
  toggleSidebar: () => void;
}

const Sidebar = ({ isSidebarOpen, toggleSidebar }: SidebarProps) => {
  return (
    <div className="p-4 h-full">
      <div className="flex items-center justify-between">
        <SidebarMenuItem
          title={menuItems[0].title}
          icon={menuItems[0].icon}
          href={menuItems[0].href!}
        />
        {/* Chỉ hiển thị nút toggle ở md trở lên */}
        <button className="hidden md:block ml-2" onClick={toggleSidebar}>
          {isSidebarOpen ? <X size={20} /> : <Menu size={20} />}
        </button>
      </div>

      {menuItems.slice(1).map((item: any) =>
        item.children ? (
          <SidebarMenuAccordionItem icon={item.icon} title={item.title} key={item.title} children={item.children} />
        ) : (
          <SidebarMenuItem icon={item.icon} title={item.title} key={item.title} href={item.href} />
        )
      )}
    </div>
  )
}

export default Sidebar