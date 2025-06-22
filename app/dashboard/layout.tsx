'use client'
import React, { useState } from 'react'
import TopHeader from '../components/TopHeader'
import Sidebar from '../components/Sidebar'
import RightLine from '../components/RightSide'
import Footer from '../components/Footer'

const DashboardChildrent = ({children}:{children: React.ReactNode}) => {
    const [isOpenSidebar, setIsOpenSidebar] = useState(false);
    const toggleSidebar = () => setIsOpenSidebar(!isOpenSidebar)
    return (
    <div className={`grid grid-cols-[100px_1fr] grid-rows-[100px_1fr_100px] h-screen md:grid-cols-[100px_1fr_100px]
        md:grid-rows-[80px_1fr_150px] text-white ${isOpenSidebar ? 'md:grid-cols-[auto_1fr_100px]' : 'md:grid-cols-[100px_1fr_100px]'}`}>
        <div className='row-start-1 col-start-2 bg-gray-800 md:row-start-1 md:col-start-2 md:col-span-2'>
            <TopHeader/>
        </div>
        <div className={`row-span-3 md:row-start-1 md:col-start-1 md:row-span-3 bg-gray-900 text-white ${isOpenSidebar? 'absolute h-full md:static z-50' : 'md:block'}`}>
            <Sidebar isOpenSidebar={isOpenSidebar} toggleSidebar={toggleSidebar}/>
        </div>
        <div className='row-start-2 col-start-2 md:row-start-2 md:col-start-2 text-black'>{children}</div>
        <div className='row-start-3 col-start-2 hidden bg-emerald-400 md:row-start-2 md:col-start-3 md:row-span-2 md:block'>
            <RightLine/>
        </div>
        <div className='row-start-3 col-start-2 bg-fuchsia-400 md:row-start-3 md:col-start-2'>
            <Footer/>
        </div>
    </div>
    ) 
}

export default DashboardChildrent