'use client'
import React, { useState } from 'react'
import TopHeader from '../components/TopHeader'
import Sidebar from '../components/Sidebar'
import RightLine from '../components/RightSide'
import Footer from '../components/Footer'
const DashboardChildrent = ({children}:{children: React.ReactNode}) => {
    const [isOpenSidebar, setIsOpenSidebar] = useState(false);
    const toggleSidebar = () => setIsOpenSidebar(!isOpenSidebar);
    return (
    <div className='grid grid-cols-1 grid-rows-[70px_1fr_100px] h-screen md:grid-cols-[auto_1fr_100px]
        md:grid-rows-[100px_1fr_150px] text-white'>
        <div className='row-start-1 bg-red-500 md:row-start-1 md:col-start-2 md:col-span-2'>
            <TopHeader toggleSidebar={toggleSidebar}/>
        </div>
        {/* <div className='row-start-2 hidden bg-gray-900 md:block md:row-start-1 md:col-start-1 md:row-span-3'> */}
        <div className={`
        z-50 bg-gray-900 h-full transition-transform duration-300
        ${isOpenSidebar ? 'translate-x-0' : '-translate-x-full'}
        fixed top-0 left-0 w-64 md:translate-x-0 md:static md:block
        row-start-2 md:row-start-1 md:col-start-1 md:row-span-3
      `}>

            <Sidebar isSidebarOpen={isOpenSidebar} toggleSidebar={toggleSidebar}/>
        </div>
        <div className='row-start-2 md:row-start-2 md:col-start-2 text-black'>{children}</div>
        <div className='row-start-3 hidden bg-emerald-400 md:row-start-2 md:col-start-3 md:row-span-2 md:block'>
            <RightLine/>
        </div>
        <div className='row-start-3 bg-fuchsia-400 md:row-start-3 md:col-start-2'>
            <Footer/>
        </div>
    </div>
    ) 
}

export default DashboardChildrent