import React from 'react'
import { menuItems } from './layout/sidebar/menu.items.config'
import SidebarMenuAccordionItem from './layout/sidebar/SidebarMenuAccordionItem'
import SidebarMenuItem from './layout/sidebar/SidebarMenuItem'

const Sidebar = ({isOpenSidebar, toggleSidebar}:any) => {
  return (
    <div className='p-4'>
      {menuItems.map((item: any, index) =>
        item.children ? (
          <SidebarMenuAccordionItem icon={item.icon} title={item.title} key={item.title} children={item.children} toggleSidebar={toggleSidebar} isOpenSidebar={isOpenSidebar}/>
        ) : (
          <SidebarMenuItem icon={item.icon} title={item.title} key={item.title} href={item.href} isFirst={index === 0} isOpenSidebar={isOpenSidebar} toggleSidebar={toggleSidebar}/>
        )
      )}
    </div>
  )
}

export default Sidebar