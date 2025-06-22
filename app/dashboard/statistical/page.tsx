'use client'
import { useSellFlowers } from '@/hooks/useSellFlowers'
import React, { useMemo } from 'react'
import {AreaChart, Area, XAxis, YAxis,  CartesianGrid,  Tooltip,  Legend,  ResponsiveContainer} from 'recharts'

// const data = [
//   {
//     month: 'Tháng 1',
//     hoatuoi: 4000,
//     hoagia: 2400,
//     phukien: 2400,
//   },
//   {
//     month: 'Tháng 2',
//     hoatuoi: 3000,
//     hoagia: 1398,
//     phukien: 2210,
//   },
//   {
//     month: 'Tháng 3',
//     hoatuoi: 2000,
//     hoagia: 9800,
//     phukien: 2290,
//   },
//   {
//     month: 'Tháng 4',
//     hoatuoi: 2780,
//     hoagia: 3908,
//     phukien: 2000,
//   },
//   {
//     month: 'Tháng 5',
//     hoatuoi: 2780,
//     hoagia: 3908,
//     phukien: 2000,
//   },
//   {
//     month: 'Tháng 6',
//     hoatuoi: 2780,
//     hoagia: 3208,
//     phukien: 2000,
//   },
//   {
//     month: 'Tháng 7',
//     hoatuoi: 2780,
//     hoagia: 3908,
//     phukien: 2000,
//   },
//   {
//     month: 'Tháng 8',
//     hoatuoi: 2780,
//     hoagia: 7908,
//     phukien: 8000,
//   },
//   {
//     month: 'Tháng 9',
//     hoatuoi: 2780,
//     hoagia: 3908,
//     phukien: 7000,
//   },
//   {
//     month: 'Tháng 10',
//     hoatuoi: 2780,
//     hoagia: 3908,
//     phukien: 2000,
//   },
//   {
//     month: 'Tháng 11',
//     hoatuoi: 2780,
//     hoagia: 3908,
//     phukien: 3000,
//   },
//   {
//     month: 'Tháng 12',
//     hoatuoi: 2780,
//     hoagia: 7000,
//     phukien: 2000,
//   },
// ]

export default function StatisticalPage() {
  const {sellFlowers, isLoading, isError} = useSellFlowers();
  
  // get name of all flowers
  const flowerTypes = useMemo(()=>{
    const getFlowers =  new Set<string>()
    sellFlowers?.forEach(({nameFlowers})=>{
      if(nameFlowers){
        getFlowers.add(nameFlowers.toLowerCase())
      }
    })
    return Array.from(getFlowers)
  },[sellFlowers])

  const chartData = useMemo(()=>{
    if (!sellFlowers || !Array.isArray(sellFlowers)) return []
    const monthMap: Record<number,any>={}
    sellFlowers?.forEach(({month, nameFlowers, incomeInMonth})=>{
      if (!month || isNaN(Number(month)) || !nameFlowers || incomeInMonth === undefined) return

      if(!monthMap[month]){
        monthMap[month] = { month: `Tháng ${month}`}

        flowerTypes.forEach(flower => {
          monthMap[month][flower] = 0;
        })
      }

      const key = nameFlowers.toLowerCase()
      if(flowerTypes.includes(key)){
        monthMap[month][key] += incomeInMonth
      }
    })

    return Object.values(monthMap)
  },[sellFlowers,flowerTypes])
  console.log('d ', sellFlowers)
  if (isLoading) return <p>Đang tải dữ liệu...</p>
  if (isError) return <p>Lỗi khi tải dữ liệu</p>

  return (
    <div className="p-6">
      <h2 className="text-xl font-bold mb-4">Thống kê doanh thu</h2>
      <div className="w-full h-[400px] p-4 rounded-3xl shadow-2xl">
        <ResponsiveContainer width="100%" height="100%">
          <AreaChart data={chartData} margin={{ top: 10, right: 30, left: 0, bottom: 0 }}>
            <CartesianGrid strokeDasharray="3 3" />
            <XAxis dataKey="month" />
            <YAxis />
            <Tooltip />
            <Legend />
            {flowerTypes.map((type,index)=>(
              <Area key={type} type="monotone" dataKey={type} stackId="1" stroke={getColor(index)} fill={getColor(index,0.4)} />
            ))}
            {/* <Area type="monotone" dataKey="hoahong" stackId="1" stroke="#8884d8" fill="#8884d8" />
            <Area type="monotone" dataKey="hoaly" stackId="1" stroke="#82ca9d" fill="#82ca9d" />
            <Area type="monotone" dataKey="hoagiay" stackId="1" stroke="#ffc658" fill="#ffc658" /> */}
          </AreaChart>
        </ResponsiveContainer>
      </div>
    </div>
  )
}

function getColor(index: number, opacity: number = 1): string {
  const colors = [
    '#8884d8', '#82ca9d', '#ffc658', '#ff7f50', '#00bfff',
    '#ff69b4', '#8a2be2', '#a52a2a', '#5f9ea0', '#ff6347'
  ]
  const color = colors[index % colors.length]
  return opacity < 1
    ? color.replace(')', `, ${opacity})`).replace('rgb', 'rgba')
    : color
}
