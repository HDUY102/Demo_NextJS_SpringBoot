import React from 'react'
import TopHeader from '../components/TopHeader'
import Sidebar from '../components/Sidebar'
import RightLine from '../components/RightSide'
import Footer from '../components/Footer'
const DashboardChildrent = ({children}:{children: React.ReactNode}) => {
  return (
    <div className='grid grid-cols-1 grid-rows-5 md:h-screen md:grid-cols-[auto_1fr_100px]
     md:grid-rows-[100px_1fr_150px] text-black bg-white'>
        <div className='row-start-1 bg-red-500 md:row-start-1 md:col-start-2 md:col-span-2'>
            <TopHeader/>
        </div>
        <div className='row-start-2 bg-amber-300 md:row-start-1 md:col-start-1 md:row-span-3'>
            <Sidebar/>
        </div>
        <div className='row-start-3 md:row-start-2 md:col-start-2'>{children}</div>
        <div className='row-start-4 bg-emerald-400 md:row-start-2 md:col-start-3 md:row-span-2'>
            <RightLine/>
        </div>
        <div className='row-start-5 bg-fuchsia-400 md:row-start-3 md:col-start-2'>
            <Footer/>
        </div>
    </div>
  )
}

export default DashboardChildrent