import React from 'react'
import { headerItems } from './layout/sidebar/menu.items.config'
import SidebarMenuAccordionItem from './layout/sidebar/SidebarMenuAccordionItem'
import SidebarMenuItem from './layout/sidebar/SidebarMenuItem'

const TopHeader = () => {
  return (
    <div className='flex justify-evenly mt-2 p-1'>{headerItems.map((item:any)=>item.children?(
      <SidebarMenuAccordionItem icon={item.icon} title={item.title} key={item.title}  children={item.children} />
    ):(
      <SidebarMenuItem icon={item.icon} title={item.title} key={item.title} href={item.href}/>
    ))}</div>
  )
}

export default TopHeader