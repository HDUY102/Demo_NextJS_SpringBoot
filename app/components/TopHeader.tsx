'use client'
import React from 'react'
import HeaderItems from './layout/header/HeaderItem'
import { headerItems } from './layout/header/header.items.config'
import { Search } from 'lucide-react'

const TopHeader = () => {
  const getToday = new Date()
  const formattedDate = getToday.toLocaleDateString('en-US',{
    weekday: 'long',
    year: 'numeric',
    month: 'long',
    day: 'numeric'
  })
  
  return (
    <div className='flex items-center justify-between mt-3'>
      <div className='ml-3'>
        <p className='font-bold text-2xl'>Welcome, Admin</p>
        <p>{formattedDate}</p>
      </div>
      
      <div className='flex items-center justify-end h-full px-4'>
        <div className="relative mr-3">
          <span className="absolute inset-y-0 left-2 flex items-center text-white p-1">
            <Search size={18} />
          </span>
          <input
            type="text"
            placeholder="Search"
            className="pl-10 pr-3 py-1.5 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-400 focus:border-blue-400 text-sm"
          />
        </div>
        {headerItems.slice(1).map((item)=>(
          <HeaderItems key={item.title} href={item.href} icon={item.icon}/>
        ))}
      </div>
    </div>
  )
}

export default TopHeader