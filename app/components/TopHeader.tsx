'use client'
import React from 'react'
import HeaderItems from './layout/header/HeaderItem'
import { headerItems } from './layout/header/header.items.config'
import { PanelRightClose, PanelRightOpen } from 'lucide-react'

const TopHeader = () => {
  return (
    <div className='flex items-center justify-end h-full px-4'>
      {headerItems.slice(1).map((item)=>(
        <HeaderItems key={item.title} href={item.href} icon={item.icon}/>
      ))}
    </div>
  )
}

export default TopHeader