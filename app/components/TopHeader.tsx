'use client'
import React from 'react'
import HeaderItems from './layout/header/HeaderItem'
import { headerItems } from './layout/header/header.items.config'
import { PanelRightClose, PanelRightOpen } from 'lucide-react'

interface Props {
  toggleSidebar: () => void;
}

const TopHeader = ({ toggleSidebar }: Props) => {
  return (
    <div className='flex items-center justify-between h-full px-4'>
      <div className="pt-6" onClick={toggleSidebar}>
        <div className='md:hidden'>
          <HeaderItems key={headerItems[0].title} href={headerItems[0].href} icon={PanelRightClose}/>
        </div>
      </div>
      <div className='flex justify-end p-6'>
        {headerItems.slice(1).map((item)=>(
          <HeaderItems key={item.title} href={item.href} icon={item.icon}/>
        ))}
      </div>
    </div>
  )
}

export default TopHeader