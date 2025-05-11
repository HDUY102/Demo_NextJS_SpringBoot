import { Icon } from 'lucide-react';
import Link from 'next/link';
import React from 'react'

interface HeaderItemProps{
    title: string;
    icon: React.ElementType;
    href: string;
}

const SidebarMenuHeader = ({title, icon: Icon, href}:HeaderItemProps) => {
  return (
    <Link href={href} className='flex p-2 hover:bg-amber-500'>
        <Icon size={20}/>
        <div className='ml-3'>{title}</div>
    </Link>
  )
}

export default SidebarMenuHeader