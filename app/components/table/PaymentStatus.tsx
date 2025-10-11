import React from 'react'
import { PaymentStatusBadgeProps } from './table.item';

export const PaymentStatus: React.FC<PaymentStatusBadgeProps> = ({ isPaid }) => {
  return (
    <div className={`rounded-2xl p-0.5 text-center ${isPaid ? 'bg-green-800' : 'bg-amber-500'}`}>
      {isPaid ? 'Đã thanh toán' : 'Chưa thanh toán'}
    </div>
  );
};