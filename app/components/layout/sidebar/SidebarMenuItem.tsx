'use client'
import React, {useState} from 'react'
import Link from 'next/link';
import { PanelRightClose, PanelRightOpen } from 'lucide-react'

interface MenuItemProps{
    title: string;
    icon: React.ElementType;
    href: string;
    isFirst?: boolean
    isOpenSidebar?: boolean
    toggleSidebar: ()=>void
}

const SidebarMenuItem = ({title, icon: Icon, href, isFirst=false, isOpenSidebar, toggleSidebar}:MenuItemProps) => {
  
  return (
    <Link href={href} className={`flex justify-between items-center p-2 ${isFirst ? '' : 'md:hover:text-black md:hover:bg-gray-500 md:hover:rounded-3xl'}`}>
      <div className="flex items-center">
        <Icon size={20} />
        {isOpenSidebar ? (<div className='ml-2 md::bg-amber-300'>{title}</div>) : ''}
      </div>

      {isFirst && (
        <div onClick={(e)=>{e.preventDefault(); if (toggleSidebar) toggleSidebar();}} className="text-white ml-2.5">
          {isOpenSidebar?(
            <PanelRightOpen size={20} />
          ):(
            <PanelRightClose size={20} />
          )}
        </div>
      )}
    </Link>
  )
}

export default SidebarMenuItem