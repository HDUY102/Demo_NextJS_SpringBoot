import React from 'react'
import { menuItems } from './layout/sidebar/menu.items.config'
import SidebarMenuAccordionItem from './layout/sidebar/SidebarMenuAccordionItem'
import SidebarMenuItem from './layout/sidebar/SidebarMenuItem'

const Sidebar = () => {
  return (
    <div>{menuItems.map((item:any)=>item.children?(
      <SidebarMenuAccordionItem icon={item.icon} title={item.title} key={item.title}  children={item.children} />
    ):(
      <SidebarMenuItem icon={item.icon} title={item.title} key={item.title} href={item.href}/>
    ))}</div>
  )
}

export default Sidebar