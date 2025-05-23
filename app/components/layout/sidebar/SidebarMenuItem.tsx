import React from 'react'
import Link from 'next/link';

interface MenuItemProps{
    title: string;
    icon: React.ElementType;
    href: string;
}

const SidebarMenuItem = ({title, icon: Icon, href}:MenuItemProps) => {
  return (
    <Link href={href} className='flex p-2 hover:bg-gray-600'>
        <Icon size={20}/>
        <div className='ml-3 hidden'>{title}</div>
    </Link>
  )
}

export default SidebarMenuItem